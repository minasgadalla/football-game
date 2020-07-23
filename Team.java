import java.util. * ;
public class Team {
    
	String TeamName; // name of the team
	String TeamNameTag; // tag of the team
	static int Goals; // goals of the team
	static int Passes; 
	static int BadPass; // wrong action of the teams
	public Team A = null;
	public Team B = null;
	public static boolean teamAorB; // A == false, B == true
	public ArrayList < Player > ListOfPlayers = new ArrayList < Player > ();
	Iterator < Player > it = ListOfPlayers.iterator();
	
	public Team() {
		this.Goals = 0;
		this.Passes = 0;
		this.BadPass = 0;
	}
	// return the team as a class
	public boolean getTeanAorB(Team C) {
		return C.teamAorB;
	}
	// set the name of the team
	public void SetTeamName(String TeamName) {
		this.TeamName = TeamName;
	}
	// get the name of the team
	public String GetTeamName() {
		return this.TeamName;
	}
	// using probabilities decide what will happen
	public static void action(Team C) {
		Iterator < Player > it = C.ListOfPlayers.iterator();
		while (it.hasNext()) {
			double number = Math.random();
			Player LIST = it.next();
			if (number < 0.35) {
				LIST.Movement(C); // move on the field
			}
			else if (number >= 0.35 && number < 0.70) {
				LIST.Transfer(C); // give the ball on a teamate
			}
			else {
				LIST.SpecialMovement(C);
				if (LIST.getremove()) {
					it.remove();
				}
			}
		}
		int i = 0;
	}
	// chech the position of the other team playters
	public static int checkEnemyposition(boolean teamaorb, int x, int runningline) {
		int closer = 0;
		if (teamaorb == false) {
			for (int i = 1; i < Field.getY(); i++) {
				if (x + i < Field.getY()) {
					if (Field.FieldArray[runningline][x + i].getText() != Field.nullfield) {
						if (Game.checkforplaymate(Game.ListOfTeams.get(0), runningline, x + i) == false) {
							closer = x - (x + i);
						}
					}
				}
			}
		}
		else {
			for (int i = 1; i < Field.getY(); i++) {
				if (x - i >= 0) {
					if (Field.FieldArray[runningline][x - i].getText() != Field.nullfield) {
						if (Game.checkforplaymate(Game.ListOfTeams.get(1), runningline, x - i) == false) {
							closer = x - (x - i);
						}
					}
				}
			}
		}
		return closer;
	}
}