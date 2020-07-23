import java.awt. * ;
import javax.swing. * ;
public class Ball {
	public static Player CurrentPlayer;
	public static Player PreviousPlayer;
	public static int x;
	public static int y;
	public static boolean ballAorB; // which team holds the ball, false for team A and true for team B
	public static int CurrentPlayerN; // the number of the current player
	public Ball(Player P) {
		x = 0;
		y = 0;
		CurrentPlayer = P;
		PreviousPlayer = null;
	}
	public static void Assign(Player P) {
		if (P == null) {
			setPreviousPlayer(getCurrentPlayer());
			setCurrentPlayer(P);
		} else if (P != null) {
			setBallx(P.getPxx());
			setBally(P.getPyy());
			setPreviousPlayer(getCurrentPlayer());
			setCurrentPlayer(P);
			if ((getPreviousPlayer() != null) && (getCurrentPlayer() != null)) {
				if (getCurrentPlayer().AorB == getPreviousPlayer().AorB) {
					int team = Game.getTeamFromPlayer(P.getPxx(), P.getPyy());
					Game.ListOfTeams.get(team).Passes++;
				}
				else if (getCurrentPlayer().AorB != getPreviousPlayer().AorB) {
					int team = Game.getTeamFromPlayer(getPreviousPlayer().getPxx(), getPreviousPlayer().getPyy());
					Game.ListOfTeams.get(team).BadPass++;
				}
			}
			Field.printField();
		}

	}
	public static void Assign() {
		if (Closer1() >= Closer2()) {
			setPreviousPlayer(getCurrentPlayer());
			setCurrentPlayer((Field.getPlayerFromField2(getBallx(), Closer2())));
		}
		else {
			setPreviousPlayer(getCurrentPlayer());
			setCurrentPlayer((Field.getPlayerFromField2(getBallx(), Closer1())));
		}
	}
	public static void ChangeFromUser() {
		setCurrentPlayer(Game.ListOfTeams.get(1).ListOfPlayers.get(0));
		setPreviousPlayer(null);
		setBallx(Game.ListOfTeams.get(1).ListOfPlayers.get(0).getPxx());
		setBally(Game.ListOfTeams.get(1).ListOfPlayers.get(0).getPyy());
	}
	public static int Closer1() {
		int closer1;
		for (int j = 0; j < Field.getY(); j++) {
			if ((Field.getY() + j) < Field.getY()) {
				if (Field.FieldArray[getBallx()][getBally() + j].getText() != Field.nullfield) {
					return getBally() + j;
				}
			}
		}
		return 0;
	}
	public static int Closer2() {
		int closer2;
		for (int j = 0; j < Field.getY(); j++) {
			if ((Field.getY() - j) >= 0) {
				if (Field.FieldArray[getBallx()][getBally() + j].getText() != Field.nullfield) {
					return getBally() - j;
				}
			}
		}
		return 0;
	}

	public static void setCurrentPlayer(Player P) {
		CurrentPlayer = P;
	}
	public static Player getCurrentPlayer() {
		return CurrentPlayer;
	}
	public static void setPreviousPlayer(Player P) {
		PreviousPlayer = P;
	}
	public static Player getPreviousPlayer() {
		return PreviousPlayer;
	}
	public static void sentBall(Team C, int oldx, int oldy, int newX, int newY) {
		setBallx(newX);
		setBally(newY);
		Assign(Field.getPlayerFromField2(newX, newY));

	}
	public static void setBallx(int newX) {
		x = newX;
	}
	public static void setBally(int newY) {
		y = newY;
	}
	public static int getBallx() {
		return x;
	}
	public static int getBally() {
		return y;
	}
}