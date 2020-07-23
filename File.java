import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
abstract public class File
{
  public static void MyFile(Team A,Team B)
    {
        String FileChars;
        boolean Next=false;
        int i=1;
        Random rand = new Random();       
        BufferedReader FileR = null;       
        try {
        FileR = new BufferedReader(new FileReader("players.txt"));
        Main.printer("File found");
        }
        catch (FileNotFoundException e)
        {
            Main.printer("the file doesnt exist "+e);
            Main.printer("exiting....");
            System.exit(0);
        }       
        try{
        while ((FileChars = FileR.readLine()) != null)
        { 
           String[] pl = FileChars.split(" ");
           if (Next == false){
           if (i==1)
               {
                   A.ListOfPlayers.add(new GoalKeeper(pl[0],Integer.parseInt(pl[1])));
               }
           if (i==2)
               {
                   A.ListOfPlayers.add(new GoodDefender(pl[0],Integer.parseInt(pl[1])));
               }
           if (i>2 && i<=4)
               {
                   A.ListOfPlayers.add(new Defender(pl[0],Integer.parseInt(pl[1])));
               }
           if(i==5)
           {
               A.ListOfPlayers.add(new BadDefender(pl[0],Integer.parseInt(pl[1])));
               //System.out.println(pl[0]+" kai "+pl[1]);
           }
           if (i>5 && i<12)
               {
                   A.ListOfPlayers.add(new Striker(pl[0],Integer.parseInt(pl[1])));
                   //System.out.println(pl[0]+" kai "+pl[1]);
               }
           if(i==12)
               {
              Next=true;
               }
               i++;
            }
           if (Next == true)
            {
             if (i==12)
               {
                   B.ListOfPlayers.add(new GoalKeeper(pl[0],Integer.parseInt(pl[1])));
               }
             if (i==13)
               {
                   B.ListOfPlayers.add(new GoodDefender(pl[0],Integer.parseInt(pl[1])));
               }
             if (i>13 && i<16)
               {
                   B.ListOfPlayers.add(new Defender(pl[0],Integer.parseInt(pl[1])));
               }
             if (i==16)
               {
                   B.ListOfPlayers.add(new BadDefender(pl[0],Integer.parseInt(pl[1])));
               }
             if(i>16 && i<=23)
               {
                   B.ListOfPlayers.add(new Striker(pl[0],Integer.parseInt(pl[1])));   
               }
               i++;
            }
            }
            Main.printer("The file read successfully");
           }
           catch (Exception e2)
           {
            Main.printer("something went wrong, error: "+ e2);Main.printer("Exiting...");System.exit(0);
           }
            finally
            {
            try{FileR.close();}
            catch (Exception e3)
            {Main.printer("something went wrong, error: "+e3);Main.printer("Exiting...");System.exit(0);}
            }  
}
}