public class GoodDefender extends Defender {
	public GoodDefender(String Name, int Number) {
		super(Name, Number);
		this.YellowCard = 0;
	}
	public void SpecialMovement(Team C) {
		double a = Math.random();
		if (check(C)) {
			if (a < 0.90) {
				Steals++;
				Ball.sentBall(C, this.getPxx(), this.getPyy(), this.getLineGoal(), getPyy());
				this.setremove(false);
			}
			else {
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
	public void Transfer(Team C) {
		super.Transfer(C);
	}
}