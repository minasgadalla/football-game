import java.util. * ;
import java.io. * ;
import javax.swing. * ;
import java.awt. * ;
public class Field {
	public static int Fieldx,
	Fieldy;
	public static boolean BFieldx,
	BFieldy;
	public static JPanel[][] JFieldArray;
	public static JTextArea[][] FieldArray;
	public static Player[][] FieldArrayOfPlayers;
	public static String nullfield = "      ";
	public Field() {
		this.Fieldx = getX();
		this.Fieldy = getY();
		FieldUser();
		JFieldArray = new JPanel[Field.getX()][Field.getY()];
		FieldArray = new JTextArea[Field.getX()][Field.getY()];
		FieldArrayOfPlayers = new Player[Field.getX()][Field.getY()];
		for (int i = 0; i < Field.getX(); i++)
		for (int j = 0; j < Field.getY(); j++) {
			JFieldArray[i][j] = new JPanel();
			FieldArray[i][j] = new JTextArea();
			FieldArray[i][j].setLayout(null);
			FieldArray[i][j].setEditable(false);
			JFieldArray[i][j].add(FieldArray[i][j]);
		}
		setField();
		Main.printer(" the gamefield created successfully");
	}
	public static void FieldUser() {
		while (!Main.inputDataForField(8, 14, 8, 13)) {}
	}
	public static void setX(int x) {
		Fieldx = x;
	}
	public static void setY(int y) {
		Fieldy = y;
	}
	public static int getX() {
		return Fieldx;
	}
	public static int getY() {
		return Fieldy;
	}
	public void setField() {
		for (int i = 0; i < Field.getX(); i++) {
			for (int j = 0; j < Field.getY(); j++) {
				FieldArray[i][j].setText(nullField()); // ALLAGH KAI STHN WHILE THS PLAYERS
			}
		}
	}
	public static void printField() {
		for (int i = 0; i < Field.getX(); ++i) {
			for (int j = 0; j < Field.getY(); ++j) {
				if (CheckForBall(i, j) == false) {
					FieldArray[i][j].setBackground(Color.white);
					FieldArray[i][j].setText(FieldArray[i][j].getText());
				}
				else {
					FieldArray[i][j].setBackground(Color.red);
					FieldArray[i][j].setText("" + Field.FieldArray[i][j].getText() + "");
				}
			}
		}
	}
	public static boolean CheckForBall(int i, int j) {
		if (i == Ball.x && j == Ball.y) {
			return true;
		}
		return false;
	}
	public static String nullField() {
		return nullfield;
	}
	public static Player getPlayerFromField2(int newx, int newy) {
		for (int z = 0; z < 2; z++) {
			for (int i = 0; i < 10; i++) {
				if ((((Game.ListOfTeams.get(z)).ListOfPlayers.get(i).getPxx()) == newx) && (Game.ListOfTeams.get(z)).ListOfPlayers.get(i).getPyy() == newy) {
					return (Game.ListOfTeams.get(z)).ListOfPlayers.get(i);
				}
			}
		}
		return null;
	}

}