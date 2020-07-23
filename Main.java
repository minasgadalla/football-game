import javax.swing. * ;
import java.util. * ;
import java.awt. * ;
import java.io. * ;
import java.awt.event. * ;

public class Main extends JFrame {
	public JSplitPane splitV; // V = VERTICAL
	public JSplitPane splitH; // H = Horizontial
	public JPanel panel1;
	public JPanel panel2;
	public JPanel panel3;
	JButton buttonReset;
	JButton buttonQuit;
	static JOptionPane optionPane = new JOptionPane();

	public static void main() {
		Game MyGame = new Game();
		Main fr = new Main(); // fr is the UI of the game
		fr.pack();
		fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fr.setVisible(true);
		rungame();
		printer("Thanks for trying the game :)");
	}

	public Main() {
		setTitle("A football game - Java");
		// create the UI 
		setBackground(Color.gray);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		CPL1(); // info for the teams
		CPL2(); // info for the teams
		CPL3(); // info for the teams
		// create 2 splitpane, to split the gamefield
		splitV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitV, BorderLayout.CENTER);
		splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitH.setLeftComponent(panel2);
		splitH.setRightComponent(panel1);
		splitV.setLeftComponent(splitH);
		splitV.setRightComponent(panel3);
	}

	public static void rungame() {
		int i = 0;
		boolean end = false;
		while (i < Game.Rounds && end == false) {
			try {
				Thread.sleep(0);
			}
			catch(Exception e) {}
			Game.runTurn(Game.ListOfTeams.get(0), Game.ListOfTeams.get(1));
			try {
				Thread.sleep(1000);
			}
			catch(Exception e) {}
			i++;
			Field.printField();
			if (Game.ListOfTeams.get(0).Goals >= Game.Goals || Game.ListOfTeams.get(1).Goals >= Game.Goals) {
				end = true;
			}
		}
	}
	// data for the field
	public static boolean inputDataForField(int minC, int maxC, int minR, int maxR) {
		JTextField FieldX = new JTextField();
		JTextField FieldY = new JTextField();
		Object[] message = {
			"Horizontal lines:",
			FieldX,
			"Vertical lines:",
			FieldY
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Diastaseis ghpedou", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (FieldX.getText() == null || FieldY.getText() == null) {
				printer("incorrect input");
				return false;
				//inputDataForField(4,9,7,13);
			}
			else if (((FieldX.getText()).matches("\\d+") == false) || ((FieldY.getText()).matches("\\d+") == false)) {
				printer("incorrect input, should be integer");
				return false;
				//inputDataForField(4,9,7,13);
			}
			else {
				if (FieldX.getText().length() > 0 && FieldY.getText().length() > 0) {
					if (((Integer.parseInt(FieldX.getText())) >= minC) && (Integer.parseInt(FieldX.getText())) <= maxC && (Integer.parseInt(FieldY.getText())) >= minR && (Integer.parseInt(FieldY.getText())) <= maxR) {
						Field.setX(Integer.parseInt(FieldX.getText()));
						Field.setY(Integer.parseInt(FieldY.getText()));
						printer("Initialization completed");
						return true;
					}
					else {
						printer("The horizonital lines must be bigger than " + minC + " and less than " + maxC + " and the  the veritcal bigger than " + minR + " and less than  " + maxR);
						return false;
						//inputDataForField(4,9,7,13);    
					}
				}
			}
		} else {
			printer("Exiting");
			System.exit(0);
		}
		return false;
	}
	// tags for the 2 teams
	public static void inputDataForTeamsTag(Team A, Team B) {
		JTextField TeamATag = new JTextField();
		JTextField TeamBTag = new JTextField();
		Object[] message = {
			"Tag of the first team:",
			TeamATag,
			"Tag of the second team:",
			TeamBTag
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Team Tags", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			A.TeamNameTag = TeamATag.getText();
			B.TeamNameTag = TeamBTag.getText();
			if (TeamATag.getText().equals(TeamBTag.getText())) {
				printer("The tags inserted are same, they should be different");
				inputDataForTeamsTag(A, B);
			} else {
				if (TeamATag.getText().length() > 0 && TeamBTag.getText().length() > 0) {
					printer("Tags inserted! ");
				}
				else {
					printer("please fill both of the fields");
					inputDataForTeamsTag(A, B);
				}
			}
		} else {
			printer("Exiting..");
			System.exit(0);
		}
	}
	// names for the 2 teams
	public static void inputDataForTeams(Team A, Team B) {
		JTextField TeamAName = new JTextField();
		JTextField TeamBName = new JTextField();
		Object[] message = {
			"Name of the first team:",
			TeamAName,
			"Name of the second team",
			TeamBName
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Onomata Omadwn", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			A.TeamName = TeamAName.getText();
			B.TeamName = TeamBName.getText();
			if (TeamAName.getText().equals(TeamBName.getText())) {
				printer("Team names should not be the same");
				inputDataForTeams(A, B);
			} else {
				if (TeamAName.getText().length() > 0 && TeamBName.getText().length() > 0) {
					printer("Done!");
				}
				else {
					printer("please fill both of the fields");
					inputDataForTeams(A, B);
				}
			}
		} else {
			printer("Exiting...");
			System.exit(0);
		}
	}
	// method for printing strings
	public static void printer(String mess) {
		JOptionPane.showMessageDialog(null, mess, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	// create the field UI
	public void CPL1() {
		panel1 = new JPanel();
		GridLayout lay = new GridLayout(Field.getX(), Field.getY());
		panel1.setLayout(lay);
		for (int i = 0; i < Field.getX(); i++)
		for (int j = 0; j < Field.getY(); j++) {
			panel1.add(Field.JFieldArray[i][j]);
		}
	}
	
	public void CPL2() {
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		JLabel lb1 = new JLabel();
		lb1.setText("<html>Team Field.getPx();<br>TEST2<br>");
		panel2.add(lb1);
	}
	
	// create an Reset button which resets the game
	public void CPL3() {
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		JButton buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Ball.ChangeFromUser();
					printer("Done");
				}
				catch(Exception e2) {
					printer("something went wrong, error: " + e2);
				}
			}
		});
		panel3.add(buttonReset, BorderLayout.NORTH);
		JButton buttonQuit = new JButton("Quit");
		panel3.add(buttonQuit, BorderLayout.SOUTH);
		buttonQuit.addActionListener(new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
				printer("Exiting...");
				System.exit(0);
			}
		});
	}
	// insert data for number of rounds and goals
	public static boolean imputDataForRoundsAndGoals() {
		JTextField Rounds = new JTextField();
		JTextField Goals = new JTextField();
		Object[] message = {
			"Number of rounds (10-10000):",
			Rounds,
			"Maximum goals of the game (10-21):",
			Goals
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Rounds and Goals", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if (Rounds.getText() == null || Goals.getText() == null) {
				printer("wrong input");
				return false;
				//inputDataForField(4,9,7,13);
			}
			else if (((Rounds.getText()).matches("\\d+") == false) || ((Goals.getText()).matches("\\d+") == false)) {
				printer("wrong input, please insert int");
				return false;
				//inputDataForField(4,9,7,13);
			}
			else {
				if (Rounds.getText().length() > 0 && Goals.getText().length() > 0) {
					if (((Integer.parseInt(Rounds.getText())) >= 100) && (Integer.parseInt(Rounds.getText())) <= 10000 && (Integer.parseInt(Goals.getText())) >= 10 && (Integer.parseInt(Goals.getText())) <= 20) {
						Game.Rounds = (Integer.parseInt(Rounds.getText()));
						Game.Goals = (Integer.parseInt(Goals.getText()));
						printer("Done!");
						return true;
					}
					else {
						printer("Rounds must be over " + 100 + " and less than " + 10000 + " and the goals bigger than " + 10 + " and less than " + 21);
						return false;
						//inputDataForField(4,9,7,13);    
					}
				}
			}
		} else {
			printer("Exiting...");
			System.exit(0);
		}
		return false;
	}
}