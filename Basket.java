/**
 *The Basket class moves the Basket actor around and collects ingredients.
 *From the ingredients collected, it can deduce whether the player has lost the game or not.
 *@author Jordan Lee & Kimberley Yu
 *Period: 2
 *Date: 05-28-13
 */
 
package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Basket extends Actor
{
	private ArrayList<Actor> yummies; //ArrayList of ingredients
	
	/**
	 *Constructs a Basket actor and instantiates the ArrayList of ingredients
	 */
	public Basket()
	{
		yummies = new ArrayList<Actor>();
	}
		
	/**
	 *Makes the Basket move and collect ingredients
	 *@param loc the location clicked
	 */	
	public void jump(Location loc)
	{
		if (getGrid().get(loc) != null)
		{
			collect(loc);			
		}
		else
			super.moveTo(loc);
	}
	
	/**
	 *Checks yummies for each type of ingredient and only adds an ingredient
	 *when it does not already exist in yummies
	 *@param loc the location clicked
	 */
	public void collect(Location loc)
	{
		Grid gr = getGrid();
		int flour = 0; int sugar = 0; int apples = 0; int butter = 0;
		if (yummies.size() != 0)
		{
			for (int x = 0; x < yummies.size(); x++)
			{
				if (yummies.get(x) instanceof Flour)
					flour++;
				if (yummies.get(x) instanceof Butter)
					butter++;
				if (yummies.get(x) instanceof Apples)
					apples++;
				if (yummies.get(x) instanceof Sugar)
					sugar++;
			}
		}
		
		Actor i = (Actor) (gr.get(loc));
		if (flour == 0 && i instanceof Flour)
		{
			yummies.add(i);
			i.removeSelfFromGrid();
			super.moveTo(loc);
		}	
		if (butter == 0 && i instanceof Butter)
		{
			yummies.add(i);
			i.removeSelfFromGrid();
			super.moveTo(loc);
		}
		if (apples == 0 && i instanceof Apples)
		{
			yummies.add(i);
			i.removeSelfFromGrid();
			super.moveTo(loc);
		}
		if (sugar == 0 && i instanceof Sugar)
		{
			yummies.add(i);
			i.removeSelfFromGrid();
			super.moveTo(loc);
		}
	}
	
	/**
	 *Checks to see if yummies contains all 4 ingredients
	 *@return true if all ingredients are in yummies
	 */
	public boolean ingredientsCollected()
	{
		if (yummies.size() == 4)
			return true;
		return false;
	}
	
	/**
	 *Checks to see if each type of ingredient still exists in yummies or in the grid
	 *@return true if they've lost, meaning at least one ingredient is neither in yummies or the grid
	 */
	public boolean lose()
	{
		//Checks to see which ingredients are already in the ArrayList
		int flour = 0; int sugar = 0; int apples = 0; int butter = 0;
		if (yummies.size() != 0)
		{
			for (int x = 0; x < yummies.size(); x++)
			{
				if (yummies.get(x) instanceof Flour)
					flour++;
				if (yummies.get(x) instanceof Butter)
					butter++;
				if (yummies.get(x) instanceof Apples)
					apples++;
				if (yummies.get(x) instanceof Sugar)
					sugar++;
			}
		}
		
		//Checks to see how many of each ingredient is left in the grid
		int flour2 = 0; int sugar2 = 0; int apples2 = 0; int butter2 = 0;
		ArrayList<Location> occ = getGrid().getOccupiedLocations();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for (Location loc : occ)
		{
			if (!(getGrid().get(loc) instanceof Critter) && !(getGrid().get(loc) instanceof Basket))
				actors.add(getGrid().get(loc));
		}
		if (actors.size() != 0)
		{
			for (int x = 0; x < actors.size(); x++)
			{
				if (actors.get(x) instanceof Flour)
					flour2++;
				if (actors.get(x) instanceof Butter)
					butter2++;
				if (actors.get(x) instanceof Apples)
					apples2++;
				if (actors.get(x) instanceof Sugar)
					sugar2++;
			}
		}		
		
		if (flour == 0 && flour2 == 0)
			return true;
		if (butter == 0 && butter2 == 0)
			return true;	
		if (apples == 0 && apples2 == 0)
			return true;
		if (sugar == 0 && sugar2 == 0)
			return true;
		return false;			
	}
	
	
	/**
	 * Keeps original colors of the actor's image.
	 * @return the color of this actor
	 */
	public Color getColor()
	{
		return null;
	}
	
}
