import java.util. * ;
import java.io. * ;
import javax.swing. * ;
// this class is for the striker
public class Striker extends Player {
	public Striker(String Name, int Number) {
		super(Name, Number);
		setPxx(0);
		setPyy(0);
	}
	// the striker has a 'special movement'
	public void SpecialMovement(Team C) {
		if (checkforplaymate(C)) {
			int closer = Team.checkEnemyposition(!C.teamAorB, this.getPxx(), this.getRunningLine());
			if (closer >= 0) {
				if (this.y + 2 <= Field.getY() - 1) {
					inversion(C, this.x, this.y, this.y + 2);
				}
				else {
					inversion(C, this.x, this.y, this.y - 2);
				}
			}
			else {
				if (this.y - 2 > 0) {
					inversion(C, this.x, this.y, this.y - 2);
				}
				else {
					inversion(C, this.x, this.y, this.y + 2);
				}
			}
		}
	}
	
	public void Transfer(Team C) {
		Random randy = new Random();
		int sentY = randy.nextInt(Field.getY() - 2);
		if (C.ListOfPlayers.get(C.ListOfPlayers.indexOf(this)) == Ball.getCurrentPlayer()) {
			Ball.sentBall(C, this.getPxx(), this.getPyy(), this.getLineGoal(), sentY);
			Ball.setPreviousPlayer(C.ListOfPlayers.get(C.ListOfPlayers.indexOf(this)));
		}
	}
	
	public void Movement(Team C) {
		super.Movement(C);
	}
	
	public boolean checkforplaymate(Team C) // an exei thn mpala sumpaikths
	{
		for (Iterator < Player > it = C.ListOfPlayers.iterator(); it.hasNext();) {
			Player ListOfPlayers = it.next();
			if (ListOfPlayers.getPxx() == Ball.getBallx() && (ListOfPlayers.getPyy() == Ball.getBally())) {
				return true;
			}
		}
		return false;
	}
}