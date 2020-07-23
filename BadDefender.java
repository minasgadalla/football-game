public class BadDefender extends Defender {
	public BadDefender(String Name, int Number) {
		super(Name, Number);
		this.YellowCard = 0;
		this.x = 0;
		this.y = 0;
	}
	public void SpecialMovement(Team C) {
		double a = Math.random();
		if (check(C)) {
			if (a < 0.20) {
				Steals++;
				Ball.sentBall(C, this.getPxx(), this.getPyy(), this.getLineGoal(), getPyy());
				this.setremove(false);
				//Ball.sentBall(getPxx(),getPyy());
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