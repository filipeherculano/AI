package ai.worlds.vacuum;


import java.awt.Point;
import java.util.Vector;
import ai.worlds.*;
import javax.swing.*;
/**
 * A Vacuum environment. 
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */
public class VacuumWorld extends GridEnvironment
{
    public VacuumWorld(Agent[] a, int xsize, int ysize, double probDirt, JFrame f) {
    	super(a,xsize,ysize, f);
    	fillGrid(0.45,(new Dirt()).getClass());
    }
    
    /**
     * Determine if an action is legal.
     * @param a is an action string.
     */   
    public boolean legalAction(String a) {
    	if (a == null)return false;
    	return (a.equals("suck"))||(a.equals("forward"))||(a.equals("turn right"))||(a.equals("turn left"))||(a.equals("shut-off"));
    }
    
    /**
     * Get the next percept.
     * @param a is the agent.
     */  
    public Object getPercept(Agent a) {
    	Location loc = a.body.loc;
    	Vector v = new Vector(4);
    	Vector gr = (Vector) grid[loc.x][loc.y];
    	if (a.body.bump) v.addElement("bump");
    		else v.addElement("");
    	if (contains(gr,(new Dirt()).getClass())) v.addElement("dirt");
    		else v.addElement("");
    	if (loc.x == 1 && loc.y == 1) v.addElement("home");
    		else v.addElement("");
    	return v;
    }
   
    /**
     * Determine performance of the agent.
     * @param a is the agent.
     */  
    public int performanceMeasure(Agent a) {
    	AgentBody body = a.body;
    	Vector p = (Vector) a.percept;
    	Point e = new Point(a.body.loc.x, a.body.loc.y);
    	if(a.coord.add(e) == false) a.repeat++;
    	if (p.elementAt(0) == "bump") a.bumps++;
    	if (step == 200) a.reach = 1; 
    	int score = 100 * body.container.size() - step - (a.bumps * 20) - (a.reach * 300) - (a.repeat * 5);
    	if (! body.alive && !(body.loc.x==1 && body.loc.y==1)) score = score - 1000;
    	a.score=score;
    	return score;
    }
    
}

