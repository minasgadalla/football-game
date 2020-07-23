import java.math. * ;
import java.awt.Point;
import java.util. * ;
import java.io. * ;
import javax.swing. * ;
public class Defender extends Player {
	int YellowCard;
	static int Steals;
	//Random rand = new Math.random();
	public Defender(String Name, int Number) {
		super(Name, Number);
		this.YellowCard = 0;
		this.x = 0;
		this.y = 0;
		this.remove = false;
	}
	public void Transfer(Team C) {
		super.Transfer(C);
	}
	public void SpecialMovement(Team C) {
		double a = Math.random();
		if (check(C)) {
			if (a < 0.70) {
				Steals++;
				Ball.sentBall(C, this.getPxx(), this.getPyy(), this.getLineGoal(), getPyy());
				this.setremove(false);
			}
			else if (a < 0.20) {
				YellowCard++;
				if (YellowCard == 2) {
					Field.FieldArray[this.getPxx()][this.getPyy()].setText(Field.nullField());
					this.setremove(true);
				}
			}
		}
	}
	public void Movement(Team C) {
		super.Movement(C);
	}
	// check if the ball is around the player
	public boolean check(Team C) 
	{
		for (int i = -1; i < 2; i++) {
			if ((this.RunningLine + i == Ball.x) && (Ball.ballAorB != C.teamAorB)) {
				return true;
			}
		}
		return false;
	}

}