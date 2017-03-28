package ai.worlds.vacuum;

import ai.worlds.Location;
import java.util.*;

public class ModelAgent extends VacuumAgent{
	public int base = -1, altura = -1, state = -1;
	public boolean trans = false;
	public boolean lastRow = false;
	
	public void determineAction(){
		Vector p = (Vector) percept;
		if(p.elementAt(1) == "dirt") action = "suck";
		else if(p.elementAt(0) == "bump"){
			if(this.base == -1){
				this.base = body.loc.x;
				this.action = "turn left";
			} else if(this.altura == -1){
				this.altura = body.loc.y;
				this.action = "turn left";
				
				if(this.altura % 2 == 0) this.state = 0;
				else if(this.base % 2 == 0) this.state = 1;
				else this.state = 2;
				
				if(this.altura % 2 != 0) this.trans = true;
			} 
		} else {
			if(trans == true){
				action = "forward";
				trans = false;
			} else {
				switch(state){
				case 0:
					if(body.heading.x == 0 && body.heading.y == -1){
						if(body.loc.x == 1) action = "turn left";
						else action = "turn right";
					}
					else if(body.loc.forward(body.heading).x == base) {
						action = "turn right";
						trans = true;
					}
					else if(body.loc.forward(body.heading).x == 0) {
						action = "turn left";
						trans = true;
					} else if(body.loc.x == 1 && body.loc.y == 1) action = "shut-off";
					else action = "forward";
					break;
				case 1:
					if(body.heading.x == -1 && body.heading.y == 0){
						if(body.loc.y == 2) action = "turn right";
						else action = "turn left";
					}
					else if(body.loc.forward(body.heading).y == 1) {
						if(body.loc.x == 1) action = "forward";
						else {
							action = "turn right";
							trans = true;
						}
					}
					else if(body.loc.forward(body.heading).y > altura) {
						action = "turn left";
						trans = true;
					} else if(body.loc.x == 1 && body.loc.y == 1) action = "shut-off";
					else action = "forward";
					break;
				case 2:
					if(body.heading.x == -1 && body.heading.y == 0){
						if(body.loc.y == altura-1) action = "turn right";
						else action = "turn left";
					}
					else if(body.loc.y == altura && body.heading.x == 0 && body.heading.y == 1) {
						action = "turn left";
						if(!(body.loc.x == 1 && body.loc.y == altura)) trans = true;
					}
					else if(body.loc.y == altura-1 && body.heading.x == 0 && body.heading.y == -1) {
						if(body.loc.x == 1) {
							action = "forward";
							state = 0;
						} else {
							action = "turn right";
							trans = true;
						}
					} else if(body.loc.x == 1 && body.loc.y == altura) {
						if(!(body.heading.x == 0 && body.heading.y == -1)) action = "turn left";
						else action = "forward";
					}
					else action = "forward";
					break;
					default: action = "forward";
				}
			}
			
		}
	}
}


