/**
 *The PieBaker class runs the Ultimate Pie Baker and adds the appropriate amount of ingredients to the grid.
 *@author Jordan Lee & Kimberley Yu
 *Period: 2
 *Date: 05-28-13
 */
 
import info.gridworld.actor.PieWorld;
import info.gridworld.actor.Flour;
import info.gridworld.actor.Apples;
import info.gridworld.actor.Butter;
import info.gridworld.actor.Sugar;
import info.gridworld.grid.Location;
import java.awt.Color;

public class PieBaker
{
	public static void main(String[] args)
	{
		PieWorld world = new PieWorld();
		
		world.add(new Flour());
		world.add(new Flour());
		world.add(new Flour());
		
		world.add(new Sugar());
		world.add(new Sugar());
		world.add(new Sugar());
		
		world.add(new Butter());
		world.add(new Butter());
		world.add(new Butter());
		
		world.add(new Apples());
		world.add(new Apples());
		world.add(new Apples());	
		
		world.show();
	}
}