package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import java.util.ArrayList;

public class Gantt extends PApplet
{	
	public ArrayList<Task> tasks = new ArrayList<Task>();

	private void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");
		for(TableRow row:table.rows())
		{
			Task t = new Task(row);
			tasks.add(t);
		}
	}
	

	public void printTasks()
	{
		for(Task t:tasks)
        {
            System.out.println(t);
		}
		System.out.println();
	}

	
	public void settings()
	{
		size(800, 600);
	}
	
	public void mousePressed()
	{
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}
		
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void drawGrid()
    {
		//Vertical Lines
		colorMode(HSB);
        float border = width * 0.05f;
        float paddingTop = 30;
        float paddingBottom = 570;
        float lineStart = 160;
        stroke(0, 0, 255);
        textAlign(CENTER, CENTER);

        for(int i = 1; i < 31; i++)
        {
            float x = map(i, 0, 30, lineStart, 760);
            line(lineStart + (20 * i), paddingTop, lineStart+ (20 * i), paddingBottom);
            text(i, x, border / 2);
		}
	}
	
	float offset = 0;
	public void drawTasks() 
	{
		int margin = 26;
		for(int i = 0 ; i < 9 ; i ++)
        {
			int totalTime = tasks.get(i).getEnd()-tasks.get(i).getStart();
			float y = map(i, 0, tasks.size(), 2 * margin, height - margin);
			colorMode(HSB);
			float cGap = 255 /(float) (i*50);
			float c = (cGap * (i*50) + offset) % 255; 
			fill(c, 255, 255);
			rect(180+((tasks.get(i).getStart()*20)-(20)), y, totalTime * 20, 30, 7);
			String text = tasks.get(i).getTask();
			text(text, 100, y);
        }
	}
	
	public void draw()
	{			
		background(0);
		drawGrid();
		drawTasks();
		// StringConcat();
	}
}