import java.util. * ;
import java.io. * ;
import javax.swing. * ;
public class GoalKeeper extends Player {
	boolean LORKepper;
	public GoalKeeper(String Name, int Number) {
		super(Name, Number);
		setLOR(true);
	}
	public void Movement(Team C) {
		if (this.getLOR() == true) {
			super.inversion(C, this.x, this.y, this.y + 1);
			this.setLOR(false);
		}
		else if (this.getLOR() == false) {
			{
				super.inversion(C, this.x, this.y, this.y - 1);
				this.setLOR(true);
			}
		}
	}
	public void SpecialMovement(Team C) {
		super.SpecialMovement(C);
	}
	public void Transfer(Team C) {
		super.Transfer(C);
	}
	public void setLOR(boolean LOR) {
		this.LORKepper = LOR;
	}
	public boolean getLOR() {
		return this.LORKepper;
	}
}