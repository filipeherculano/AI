package ai.worlds.vacuum;

import ai.worlds.Location;
import java.util.*;

public class ModelAgent extends VacuumAgent{
	public int hor = -1, ver = -1;
	public boolean trans = false;
	public boolean lastRow = false;
	
	public void determineAction(){
		Vector p = (Vector) percept;
		if(p.elementAt(1) == "dirt") action = "suck";
		else if(p.elementAt(0) == "bump"){
			if(body.heading.x == 1 && body.heading.y == 0) {action = "turn left"; trans = true;}
			else if(body.heading.x == -1 && body.heading.y == 0) {action = "turn right"; trans = true;}
			else if(body.heading.x == 0 && body.heading.y == 1) {
				if(lastRow == true) action = "shut-off";
				else {
					if(body.loc.x >= body.loc.y) action = "turn left";
					else if(body.loc.x < body.loc.y) action = "turn right";
					lastRow = true;
				}
			}
		} else {
			if(body.heading.x == 0 && body.heading.y == 1) {
				if(trans == true){
					action = "forward";
					trans = false;
				} else {
					if(body.loc.x > body.loc.y) action = "turn left";
					else if(body.loc.x < body.loc.y) action = "turn right";
				}
			} else action = "forward";
		}
	}
}