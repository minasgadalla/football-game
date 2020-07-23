import java.util. * ;
import java.io. * ;
import javax.swing. * ;
import java.awt. * ;
public class Game {
	static int Rounds; // variable for rounds of the game
	static int Goals; // variable for the goals of the game
	public Game MyGame = null;
	public static ArrayList < Team > ListOfTeams = new ArrayList < Team > (); // list which will contain the teams
	public Game() {
		Rounds = 0;
		Team A = new Team(); // create the first Team class
		Team B = new Team(); // create the second Team class
		// add the 2 teams into a list
		ListOfTeams.add(A); 
		ListOfTeams.add(B);
		
		// ask for info of the 2 teams
		Main.inputDataForTeams(A, B);
		Main.inputDataForTeamsTag(A, B);
		
		while (!Main.imputDataForRoundsAndGoals()) {}
		File.MyFile(A, B);
		A.teamAorB = false;
		B.teamAorB = true;
		Field newfield = new Field();
		setDefenderStriker(A, B);
		SetGoalKeeprs(A, B);
		Ball myBall = new Ball((ListOfTeams.get(0)).ListOfPlayers.get(0));
		Field.printField();
		myBall.Assign((ListOfTeams.get(0)).ListOfPlayers.get(0));
	}
	// using random increase the goals of the teams
	public static void runTurn(Team A, Team B) {
		Random rand = new Random();
		boolean Turnteam = rand.nextBoolean(); // false A, true B
		if (Turnteam == false) {
			Team.action(A);
			if (Math.abs(Ball.getBallx() - A.ListOfPlayers.get(0).getPxx()) == 1) {
				A.Goals++;
			}
		}
		else {
			Team.action(B);
			if (Math.abs(Ball.getBallx() - B.ListOfPlayers.get(0).getPxx()) == 1) {
				B.Goals++;
			}
		}

	}
	// check if there is a teamate nearby
	public static boolean checkforplaymate(Team C, int line, int position) 
	{
		for (Iterator < Player > it = C.ListOfPlayers.iterator(); it.hasNext();) {
			Player ListOfPlayers = it.next();
			if (ListOfPlayers.getPxx() == line && ListOfPlayers.getPyy() == position) {
				return true;
			}
		}
		return false;
	}
	// set the goalkeepers of the teams
	protected static void SetGoalKeeprs(Team A, Team B) {
		Setter2(A, 0, 0, 2, Field.getY() / 2 - 1, false);
		Setter2(B, 0, Field.Fieldy - 1, Field.getY() - 3, (Field.getY()) / 2 - 1, true);
	}
	// set the defender and the strikers of the teams
	protected static void setDefenderStriker(Team A, Team B) {
		int i;
		Random SetRandom = new Random();
		for (i = 1; i < 3; i++) {
			Setter(A, i, 2, (Field.getX() - 1) / 2, false);
		}
		Setter(A, 3, 3, (Field.getX() - 1) / 2, false);
		for (i = 4; i < 6; i++) {
			int rand1 = SetRandom.nextInt((Field.getX() - 1) - (Field.getX() - 3)) + Field.getX() - 1;
			Setter(A, i, (Field.getX()) / 2, rand1, false);
		}
		for (i = 6; i < 8; i++) {
			Setter(A, i, (Field.getX() - 3), (Field.getX() - 1), false);
		}
		Setter(A, 8, (Field.getX() - 3), (Field.getX() - 1), false);
		for (i = 9; i < 11; i++) {
			int rand1 = SetRandom.nextInt((Field.getX() - 1) - (Field.getX() - 3)) + Field.getX() - 3;
			Setter(A, i, rand1, Field.getX() - 2, false);
		}
		for (i = 1; i < 3; i++) {
			Setter(B, i, Field.getX() - 3, (Field.getX() - 2) / 2, true);
		}
		Setter(B, 3, Field.getX() - 4, (Field.getX()) / 2, true);
		for (i = 4; i < 6; i++) {
			int rand1 = SetRandom.nextInt(3);
			Setter(B, i, (Field.getX() - 1) / 2, rand1, true);
		}
		for (i = 6; i < 8; i++) {
			Setter(B, i, 2, 0, true);
		}
		Setter(B, 8, 1, 0, true);
		for (i = 9; i < 11; i++) {
			int rand1 = SetRandom.nextInt(5 - 1) + 1;
			int rand2 = SetRandom.nextInt(4);
			Setter(B, i, rand1, rand2, true);
		}
	}
	// the following method sets the roles of the players
	public static void Setter(Team C, int i, int MyLineRunning, int MyLineGoal, boolean TeamAorB) {
		Random SetRandom = new Random(); {
			C.ListOfPlayers.get(i).setRunningLine(MyLineRunning);
			C.ListOfPlayers.get(i).setLineGoal(MyLineGoal);
			C.ListOfPlayers.get(i).x = MyLineRunning;
			int rand = SetRandom.nextInt(Field.getY() - 1); // arxikh topothetish twn paiktwn
			while ((Field.FieldArray[MyLineRunning][rand].getText()).equals(Field.nullField()) == false) {
				rand = SetRandom.nextInt(Field.getY() - 1);
			}
			Field.FieldArray[MyLineRunning][rand].setText(conc(C.TeamNameTag, C.ListOfPlayers.get(i).getNumberToString()));
			C.ListOfPlayers.get(i).y = rand;
			Field.FieldArrayOfPlayers[MyLineRunning][rand] = C.ListOfPlayers.get(i);
			C.ListOfPlayers.get(i).AorB = TeamAorB;
		}
	}
	public static void Setter2(Team C, int i, int MyLineRunning, int MyLineGoal, int z, boolean TeamAorB) // z = thesh termatofulaka 
	{
		Random SetRandom = new Random();
		C.ListOfPlayers.get(i).setRunningLine(MyLineRunning);
		C.ListOfPlayers.get(i).setLineGoal(MyLineGoal);
		C.ListOfPlayers.get(i).x = MyLineRunning;
		Field.FieldArray[MyLineRunning][z].setText(conc(C.TeamNameTag, C.ListOfPlayers.get(i).getNumberToString()));
		C.ListOfPlayers.get(i).y = z;
		Field.FieldArrayOfPlayers[MyLineRunning][z] = C.ListOfPlayers.get(i);
		C.ListOfPlayers.get(i).AorB = TeamAorB;
	}
	
	public int getPx(Team C, int i) 
	{
		return C.ListOfPlayers.get(i).x;
	}
	
	public int getPy(Team C, int i) 
	{
		return C.ListOfPlayers.get(i).y;
	}
	
	public static String conc(String str1, String str2) {
		StringBuilder ST = new StringBuilder();
		ST.append(str1).append("-" + str2 + "-");
		return ST.toString();
	}
	// check if a player holds the ball
	public boolean checkCurrentPlayer(Team C, Player P) {
		if (C.ListOfPlayers.get(C.ListOfPlayers.indexOf(P)) == Ball.getCurrentPlayer()) {
			return true;
		}
		return false;
	}
	
	public static int getTeamFromPlayer(int x, int y) {
		int sent = 0;
		for (int z = 0; z < 2; z++) {
			for (Iterator < Player > it = Game.ListOfTeams.get(z).ListOfPlayers.iterator(); it.hasNext();) {
				Player ListOfPlayers = it.next();
				if ((ListOfPlayers.getPxx() == x) && (ListOfPlayers.getPyy() == y)) {
					sent = z;
				}
			}
		}
		return sent;
	}
}