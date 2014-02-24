/**
 *The PieWorld class sets up the grid filled with MunchCritter objects and a Basket object at the start of the game.
 *It enables the user to move the Basket around by clicking with the mouse.
 *@author Jordan Lee & Kimberley Yu
 *Period: 2
 *Date: 05-28-13
 */

package info.gridworld.actor;

import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import info.gridworld.actor.MunchCritter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PieWorld extends World<Actor>
{
	// size of grid
	private static final int NUM_ROW = 10;
	private static final int NUM_COL = 10;
	
	// instance variables
	public Basket b;
	public ArrayList<MunchCritter> ants;
	public boolean lose;
	
	// special messages
	private static final String START_MSG = "Welcome to the Ultimate Pie Baker! Scroll down to read all instructions.\n"
		+ "The goal of the game is to collect all 4 ingredients: butter, sugar, apple, and flour.\n" +
		"Move the basket around the grid by clicking on its adjacent locations. But HURRY! \n" +
			"The ants will eat all your ingredients!";

	/** 
	 * Instantiates a grid with a Basket object and designates a certain number of MunchCritters to be put in the grid
	 * depending on the inputted difficulty level
	 */
	public PieWorld() 
	{		
		super(new BoundedGrid<Actor>(NUM_ROW, NUM_COL));
		setMessage(START_MSG);
		
		b = new Basket();
		Location loc = getRandomEmptyLocation();
        if (loc != null)
            b.putSelfInGrid(getGrid(), loc);
        
        //User inputs level of difficulty, designating the number of MunchCritters in the grid
        int antNumber = 0;
        while (antNumber == 0)
        {
        	String inputStr = JOptionPane.showInputDialog(null, "Enter difficulty level (1, 2, or 3)");
			try
			{
				int level = Integer.parseInt(inputStr);
				if (level == 1)
					antNumber = 5;
				if (level == 2)
					antNumber = 15;
				if (level == 3)
					antNumber = 30;
					
				if (level > 3 || level < 1)
					throw new NumberFormatException("Level must be 1, 2, or 3");
			}	
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Level must be 1, 2, or 3");
			}
        }
        
        //Adds MunchCritters to the grid
        ants = new ArrayList<MunchCritter>();
        for (int x = 0; x <= antNumber; x++)
        {
        	ants.add(new MunchCritter());
        }
        
        for (MunchCritter mc : ants)
        {
        	Location loc2 = getRandomEmptyLocation();
      		if (loc2 != null)
            	mc.putSelfInGrid(getGrid(), loc2);
        }    	
        	
        lose = false;  	          
	}
	
	/**
	 * Moves Basket to adjacent location clicked and makes MunchCritters act with every click
	 * Calls win() or lose() methods when game has been won or lost
	 * @param loc the grid location that the user selected
	 * @return true so that nothing happens when loc is clicked
	 */
	public boolean locationClicked(Location loc)
	{
		if (lose)
		{
			clearGrid();
			JOptionPane.showMessageDialog(null, "GAME OVER, THE ANTS HAVE EATEN YOUR INGREDIENTS!");
		}
		else
		{
			ArrayList<Location> locs = getGrid().getValidAdjacentLocations(b.getLocation());
			for (Location l : locs)
			{
				if (l.equals(loc))
				{
					b.jump(loc);			
					if (b.ingredientsCollected())
					{
						win();
					}
					else
					{
						for (MunchCritter mc : ants)
						{
							mc.act();
						}					
						if (b.lose())
						{
							lose = true;
						}
					}
					return true;
				}
			}
		}
		return true;
	}
	
	/**
	 * Adds actors to the grid
	 */
	public void add(Actor occupant)
    {
        Location loc = getRandomEmptyLocation();
        if (loc != null)
            occupant.putSelfInGrid(getGrid(), loc);
    }
    
   	/**
	 * Clears grid and fills it with apple pies when the player has won the game
	 */
	private void win()
	{
		clearGrid();
		JOptionPane.showMessageDialog(null, "CONGRATULATIONS, YOU HAVE BAKED A YUMMY DELICIOUS APPLE PIE!!!!");
		for (int r = 0; r < getGrid().getNumRows(); r++)
		{
			for (int c = 0 ; c < getGrid().getNumCols(); c++)
			{
				Location loc = new Location(r,c);
				getGrid().put(loc, new ApplePie());
			}
		}
	}
    
    /**
     * Clears the grid of all actors
     */
    private void clearGrid() 
	{
		for (Location loc : getGrid().getOccupiedLocations())
		{
			getGrid().remove(loc);
			
		}
	}
}