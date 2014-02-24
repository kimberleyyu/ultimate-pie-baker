/**
 *The MunchCritter class makes MunchCritters eat the ingredients that are directly in front of it.
 *It also turns in the direction that it moves.
 *@author Jordan Lee & Kimberley Yu
 *Period: 2
 *Date: 05-28-13
 */
 
package info.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class MunchCritter extends Critter
{
	/**
	 * Eats actors that are neither Basket objects nor Critter objects that are directly in front of it
	 */
	public void processActors(ArrayList<Actor> actors)
	{
		for (Actor a : actors)
		{
			if (getDirection() == getLocation().getDirectionToward(a.getLocation()))
			{
				if (!(a instanceof Basket) && !(a instanceof Critter))
					a.removeSelfFromGrid();
			}
		}
	}
	
	/**
	 * Turns towards the new location as it moves.
	 */
	public void makeMove(Location loc)
	{
		setDirection(getLocation().getDirectionToward(loc));
		super.makeMove(loc);
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
