package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
	
	// public void mousePressed()
	// {
	// 	println("Mouse pressed");	
	// 	for(int i = 0 ; i < products.size() ; i ++)
    //     {
    //         float y = map(i, 0, products.size(), border, height - border);
    //         if (mouseX > left && mouseX < left + w 
    //             && mouseY > y && mouseY < y + h                 
    //             )
    //             {
    //                 bill.add(products.get(i));
    //                 break;
    //             }
    //     }
	// }

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

		
		//Horizontal Lines
		for(int j = 0; j < 10; j++)
		{
			float x = map(j, 0, 9, 20, 100);
			noStroke();
            line(lineStart, paddingTop + (50 * j), 760, paddingTop + (50 * j));
            text(tasks, x, border / 2);
		}
	}
	
	public void drawTasks() 
	{
		for(int i = 0 ; i < 9 ; i ++)
        {
			colorMode(HSB);
			
        }
	}
	
	public void draw()
	{			
		background(0);
		drawGrid();
	}
}