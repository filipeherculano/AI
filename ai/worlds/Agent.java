package ai.worlds;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
/**
 * A generic agent.
 * All agents must implement determineAction and takeAction methods
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public abstract class Agent
{
	/**
	 * The body of the agent
	 */
	public  AgentBody body;	
	/**
	* The current score
	*/
	public  int score;	
	
	public  int bumps;
	
	public int repeat;
	
	public int reach;
	
	public Set<Point> coord;
	/**
	* The current percept
	*/
	public  Object percept; 
	/**
	* The current action
	*/
	public  String action;	


	public Agent()
	{
		body = new AgentBody();
		coord = new HashSet<Point>();
		score = 0;
		bumps = 0;
		reach = 0;
		repeat = 0;
	}

	/**
	 * Determines the next action to be taken by the agent.
	 * The action is stored as a string in the field 'action'.
	 */
	public abstract void determineAction();
	/**
	 * Perfom the current action.
	 * @param e is the environment the agent is within
	 */
	public abstract void takeAction(Environment e);

}