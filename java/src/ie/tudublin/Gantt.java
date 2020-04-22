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
		float buffer = width / 16;
		float rHeight = 40;
		for(int i = 0; i < tasks.size(); i++)
		{
			fill(255);
			float y = map(i, 0, tasks.size(), 2 * buffer, height - buffer);
			text(tasks.get(i).getTask(), buffer * 2, y);
			noStroke();
			float color = map(i, 0, tasks.size(), 0, 255);
			fill(color, 255, 255);
	
			float BottomRect = map(tasks.get(i).getStart(), 1, 30, 180, 760);
			float RightRect = map(tasks.get(i).getEnd(), 1, 30, 180, 760);
			float LeftRect = RightRect - BottomRect;
			rect(BottomRect, y - rHeight / 2, LeftRect, rHeight);
        }
	}
	
	public void draw()
	{			
		background(0);
		drawGrid();
		drawTasks();
	}
}