package ai.worlds.vacuum;

import java.util.Vector;

public class SimpleAgent extends VacuumAgent{
	
	public void determineAction(){
		Vector p = (Vector) percept;
		if(p.elementAt(1) == "dirt") action = "suck";
		else if(p.elementAt(0) == "bump"){
		    int i = (int)Math.floor(Math.random()*2);
		    switch (i) {
				case 0: action = "turn right"; 
					break;
				case 1: action = "turn left";
		    }	
		} else {
		    int i = (int)Math.floor(Math.random()*5);
		    switch (i) {
			case 0: action = "forward"; break;
			case 1: action = "forward"; break;
			case 2: action = "forward"; break;
			case 3: action = "turn right"; break;
			case 4: action = "turn left"; 
		    }
		}
	}
}
