import java.util. * ;
import java.io. * ;
import javax.swing. * ;
import java.awt. * ;

abstract class Player {
    String Name; //name of the player
    int Number; // number of the player
    int RunningLine; // current horizontal line of the player
    int LineGoal; 
    int x,y;
    boolean AorB; // players' team
    boolean remove;
    public Player(String Name, int Number) {
        this.Name = Name;
        this.Number = Number;
        this.setPxx(0);
        this.setPyy(0);
        this.setRunningLine(0);
        this.setLineGoal(0);
    }
    // set the number of the player
    public void setNumber(int Number) {
        this.Number = Number;
    }
    // get the number of the player
    public int getNumber() {
        return this.Number;
    }
    // set the name of the player
    public void setName(String Name) {
        this.Name = Name;
    }
    // get the name of the player
    public String getName() {
        return this.Name;
    }
    // horintal and verical lines the players
    public void setPxx(int x) {
        this.x = x;
    }
    public int getPxx() {
        return this.x;
    }
    public void setPyy(int y) {
        this.y = y;
    }
    public int getPyy() {
        return this.y;
    }
    
    public String getNumberToString() {
        return Integer.toString(this.Number);
    }
    // set the line of the player
    public void setRunningLine(int RunningLine) {
        this.RunningLine = RunningLine;
        this.x = RunningLine;
    }
    // get the line of the player
    public int getRunningLine() {
        return this.RunningLine;
    }
    
    public void setLineGoal(int LineGoal) {
        this.LineGoal = LineGoal;
    }
    
    public int getLineGoal() {
        return this.LineGoal;
    }
    
    public void inversion(Team C, int oldx, int oldy, int newy) {
        String temp = Field.FieldArray[oldx][newy].getText();
        Field.FieldArray[oldx][newy].setText(Field.FieldArray[oldx][oldy].getText());
        Field.FieldArray[oldx][oldy].setText(temp);
        if (Ball.getBallx() == oldx && Ball.getBally() == oldy) {
            //Ball.sentBall(C,oldx,oldy,oldx,newy);
            Ball.setBallx(oldx);
            Ball.setBally(newy);
            //Ball.Assignment(this);
            Field.FieldArray[oldx][oldy].setBackground(Color.white);
            Field.FieldArray[oldx][newy].setBackground(Color.red);
        }
        this.y = newy;
    }
    
    public void Movement(Team C) 
    {
        Random LOR = new Random(); // LOR == Left OR right 
        boolean rand = LOR.nextBoolean(); // false for left, true for right
        boolean move = false;
        while (move == false) {
            if (rand == false) {
                if ((this.y - 1) < 0) {
                    rand = true;
                }
                else {
                    inversion(C, this.x, this.y, this.y - 1);
                    move = true;
                }
            }
            if (rand == true) {
                if ((this.y + 1) > (Field.getY() - 1)) {
                    rand = false;
                }
                else {
                    inversion(C, this.x, this.y, this.y + 1);
                    move = true;
                }
            }
        }
        move = false;
    }
    public void SpecialMovement(Team C) {

    }

    public boolean getremove() {
        return this.remove;
    }
    
    public void setremove(boolean bool) {
        this.remove = bool;
    }
    // 
    public void Transfer(Team C) {
        Random randy = new Random();
        int sentY = randy.nextInt(Field.getY() - 3);
        if ((C.ListOfPlayers.get(C.ListOfPlayers.indexOf(this)).getPxx()) == Ball.getBallx() && (C.ListOfPlayers.get(C.ListOfPlayers.indexOf(this)).getPyy() == Ball.getBally())) {
            Ball.sentBall(C, this.getPxx(), this.getPyy(), this.getLineGoal(), sentY);
            Ball.setPreviousPlayer(C.ListOfPlayers.get(C.ListOfPlayers.indexOf(this)));
            //Ball.CurrentPlayer = null;
        }
    }
}