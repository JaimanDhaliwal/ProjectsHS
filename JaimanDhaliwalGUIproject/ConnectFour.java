
//importing the different libraries allowing me to make the game and add extra features
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This code is for a game of connect four.
 * The game offers sound, score, resets, rules, muting/playing 
 * of sound, and ofcourse the game of connect four.
 * 
 * The code is similar to the tic tac toe code that was =posted on the site.
 * The tic tac toe code was used for how I would set up my game,
 * how the checking of wins would work, how switching turns would work
 * and probably a lot more.
 *
 *For extra features such as sounds, I used the internet and under 
 *some code I pasted the link of the source.
 *
 *
 * Jaiman Dhaliwal
 * 2020/01/13
 * 
 * 
 *  
 * 
 * 
 */

public class ConnectFour extends JPanel implements ActionListener 
{
    //my cardlayout name for calling the different screens
    CardLayout myCardLayout = new CardLayout ();

    int rows = 6;
    //number of rows / the range of y on an x,y grid
    int columns = 7;
    //number of coloumns / the range of x on an x,y grid
    JButton btn[] [] = new JButton [rows] [columns];
    //the main grid that will be played on
    int butval[] [] = {{9, 9, 9 ,9 ,9 ,9, 9},{9, 9, 9 ,9 ,9 ,9, 9}, {9, 9, 9 ,9 ,9 ,9, 9}, {9, 9, 9 ,9 ,9 ,9, 9}, {9, 9, 9 ,9 ,9 ,9, 9}, {9, 9, 9 ,9 ,9 ,9, 9} };
    //the butval array allows me to set default values to the button to make checking for wins easier later on

    int turn = 0;
    //the turn variable allows me to change turns
    int startingTurn;
    //allows me to set what the starting turn is
    int Startplay = 0;
    //allows me to prompt pick colour screen without resetting board

    int col[]={5,5,5,5,5,5,5};
    //the col1,2,3... allow me to check how much a coloumn is filled up and stop the user from playing in that coloumn

    int first=0;
    int second=0;
    JLabel points;
    JLabel points2;
    //these variables keep track of the score and they allow me to change the score

    JPanel scoreP1;
    JPanel scoreP2;
    //these allow me to chnage their background colour
    //i can use colours to highlight the score of who's turn it is

    JButton Change;
    //allows me to change the colour of it to show what the current coulour is

    JButton MuteorPlay;
    //this allows me to change the background of the mute/play button
    public static Clip clip;
    //this allows me to access the sound clip around the class, allowing for muting or playing of the sound
    public static Clip win;
    //this allows me to access the sound clip around the class, allowing for muting or playing of the sound
    int sound = 0;
    //sound allows me to track if the sound is muted or being played

    public static void main(String[] args){
        //setting up the frame
        ConnectFour content = new ConnectFour();

        JFrame window = new JFrame("Connect Four");
        window.setContentPane(content);
        window.setSize(917,639);
        window.setLocation(100,100);
        window.setVisible(true);

    }

    public ConnectFour(){
        //opening different card screens
        //setting the main layout card layout
        setLayout (myCardLayout);

        Menu();
        gameScreen();
        Settings();
        ChangeColour();

    }

    public void Menu(){
        //the main start screen
        JPanel card1 = new JPanel();
        card1.setLayout(null);
        //setting up the name and the layout

        JLabel title= new JLabel ("Connect 4");
        title.setBounds(305,1,339,59);
        title.setFont(new Font("Arial", Font.BOLD ,65));
        //the main title

        JLabel img = new JLabel(createImageIcon( "connectfourimg.jpg"));
        img.setBounds(237,60,460,460);
        //creating the image

        card1.setBackground (Color.white);
        //setting background to white

        JButton next = new JButton ("Start Game");
        next.setFont(new Font("Arial", Font.PLAIN ,45));
        next.setBackground(new Color(36, 177, 224));
        next.setActionCommand ("game");
        next.addActionListener (this);
        next.setBounds(265,520,400,50);
        //making the button which opens the game card

        JButton Rules = new JButton ("Rules");
        Rules.setFont(new Font("Arial", Font.PLAIN ,35));
        Rules.setBackground(new Color(36, 177, 224));
        Rules.setActionCommand ("rules");
        Rules.addActionListener (this);
        Rules.setBounds(670,520,200,50);
        //the button which call opens the rules otpionpane

        JButton Settings = new JButton ("Settings");
        Settings.setFont(new Font("Arial", Font.PLAIN ,35));
        Settings.setBackground(new Color(36, 177, 224));
        Settings.setActionCommand ("settings");
        Settings.addActionListener (this);
        Settings.setBounds(60,520,200,50);
        //the button which opens the settings card

        card1.add(title);
        card1.add(img);
        card1.add (next);
        card1.add(Rules);
        card1.add(Settings);
        //adding everything

        add ("menu", card1);
        //addding card and it's name which i can use in actionperformed to open it
    }

    public void Settings(){
        //the settings screen
        JPanel card3 = new JPanel();
        card3.setLayout(null);
        //setting up the layout and name
        card3.setBackground (Color.white);
        //setting background to white

        JLabel title= new JLabel ("Connect 4");
        title.setBounds(305,1,339,59);
        title.setFont(new Font("Arial", Font.BOLD ,65));
        //the title of the game

        MuteorPlay = new JButton("Mute Sound");
        MuteorPlay.setFont(new Font("Arial", Font.PLAIN ,35));
        MuteorPlay.setBackground(Color.green);
        MuteorPlay.setActionCommand ("mute");
        MuteorPlay.addActionListener (this);
        MuteorPlay.setBounds(218,110,250,250);
        //setting up the mute button

        Change = new JButton("Change starting colour");
        Change.setFont(new Font("Arial", Font.PLAIN ,20));
        Change.setBackground(Color.red);
        Change.setActionCommand ("colour");
        Change.addActionListener (this);
        Change.setBounds(470,110,250,250);

        JButton Game = new JButton ("Game");
        Game.setFont(new Font("Arial", Font.PLAIN ,45));
        Game.setBackground(new Color(36, 177, 224));
        Game.setActionCommand ("game");
        Game.addActionListener (this);
        Game.setBounds(265,520,400,50);
        //setting up the button which opens the game

        JButton Menu = new JButton ("Menu");
        Menu.setFont(new Font("Arial", Font.PLAIN ,35));
        Menu.setBackground(new Color(36, 177, 224));
        Menu.setActionCommand ("menu");
        Menu.addActionListener (this);
        Menu.setBounds(670,520,200,50);
        //setting up the button which opens the menu

        JButton Restart = new JButton ("Restart");
        Restart.setFont(new Font("Arial", Font.PLAIN ,35));
        Restart.setBackground(new Color(36, 177, 224));
        Restart.setActionCommand ("restart");
        Restart.addActionListener (this);
        Restart.setBounds(60,520,200,50);
        //setting up the restart button which clears everything

        card3.add(title);
        card3.add(MuteorPlay);
        card3.add(Change);
        card3.add (Game);
        card3.add(Menu);
        card3.add(Restart);
        //adding everything

        add ("settings", card3);
        //adding the card and its name for actionperformed
    }

    public void ChangeColour(){
        //the settings screen
        JPanel card4 = new JPanel();
        card4.setLayout(null);
        //setting up the layout and name
        card4.setBackground (Color.white);
        //setting background to white

        JLabel title= new JLabel ("Connect 4");
        title.setFont(new Font("Arial", Font.BOLD ,65));
        title.setBounds(305,1,339,59);
        //the title of the game

        JLabel change= new JLabel ("Change the starting colour (resets game):");
        change.setBounds(120,65,700,39);
        change.setFont(new Font("Arial", Font.BOLD ,35));

        JButton Red = new JButton("Red");
        Red.setFont(new Font("Arial", Font.PLAIN ,45));
        Red.setBackground(Color.red);
        Red.setActionCommand ("red");
        Red.addActionListener (this);
        Red.setBounds(218,120,250,250);

        JButton Yellow = new JButton("Yellow");
        Yellow.setFont(new Font("Arial", Font.PLAIN ,45));
        Yellow.setBackground(Color.yellow);
        Yellow.setActionCommand ("yellow");
        Yellow.addActionListener (this);
        Yellow.setBounds(470,120,250,250);
        //setting up the mute button

        JButton Game = new JButton ("Game");
        Game.setFont(new Font("Arial", Font.PLAIN ,45));
        Game.setBackground(new Color(36, 177, 224));
        Game.setActionCommand ("game");
        Game.addActionListener (this);
        Game.setBounds(265,520,400,50);
        //setting up the button which opens the game

        JButton Menu = new JButton ("Menu");
        Menu.setFont(new Font("Arial", Font.PLAIN ,35));
        Menu.setBackground(new Color(36, 177, 224));
        Menu.setActionCommand ("menu");
        Menu.addActionListener (this);
        Menu.setBounds(670,520,200,50);
        //setting up the button which opens the menu

        JButton Restart = new JButton ("Settings");
        Restart.setFont(new Font("Arial", Font.PLAIN ,35));
        Restart.setBackground(new Color(36, 177, 224));
        Restart.setActionCommand ("settings");
        Restart.addActionListener (this);
        Restart.setBounds(60,520,200,50);
        //setting up the restart button which clears everything

        card4.add(title);
        card4.add(change);
        card4.add(Red);
        card4.add(Yellow);
        card4.add (Game);
        card4.add(Menu);
        card4.add(Restart);
        //adding everything

        add ("colour", card4);
        //adding the card and its name for actionperformed
    }

    public void gameScreen()
    {
        //the main game screen

        JPanel card2 = new JPanel();
        card2.setBackground(Color.black);
        card2.setLayout(null);
        //setting up the card 

        JPanel game = new JPanel();
        //making the game board
        game.setBackground(Color.white);
        game.setLayout(new GridLayout (6,7));
        //the grid of connect four in real life is 7,6 so it is 6 rows and 7 columns in this
        for (int x = 0 ; x < rows ; x++){//this is the makes of rows
            for (int y = 0 ; y < columns ; y++){//this makes the coloumns 
                btn [x] [y] = new JButton ("   ");
                btn[x][y] = new JButton (createImageIcon( "Square100px.png"));
                //adds the image as the button inside the grid
                btn [x] [y].addActionListener (this);
                //adds the action listener for the buttons
                btn [x] [y].setActionCommand ("" + y);
                //makes the action command the coloumn and allows me to play inside of them
                //the col1,2,3... varibles control the height of each of these coloumns

                btn [x] [y].setPreferredSize(new Dimension(100, 100));
                //sets the dimesnion of each button to 100,100
                btn[x][y].setBackground(Color.white);
                //setting the colour to white
                game.add (btn [x] [y]);
                //adds the games to the panel
            }
        }

        game.setBounds(0,1,700,600);
        //sets bounds of the panel on the game card
        card2.add(game);
        //adds the panel of the game to the card

        JPanel title = new JPanel();
        //adding a panel to display the title
        title.setBackground(new Color(36, 177, 224));
        JLabel name = new JLabel("Connect");
        name.setFont(new Font("Arial", Font.BOLD ,45));
        name.setBounds(0,0,50,200);
        JLabel four = new JLabel("4");
        four.setFont(new Font("Arial", Font.BOLD ,90));
        four.setBounds(65,0,80,200);
        title.setBounds(700,1,200,199);
        title.add(name);
        title.add(four);
        card2.add(title);
        //just making the connect for title
        //adding and custimizing everything to make it look professional

        JPanel score = new JPanel();
        //adding a panel to display the score
        score.setLayout(null);
        score.setBackground(new Color(2, 237, 131));
        JLabel name2 = new JLabel("Score");
        name2.setFont(new Font("Arial", Font.PLAIN ,45));
        name2.setBounds(40,0,200,99);
        score.setBounds(700,201,200,99);
        score.add(name2);
        card2.add(score);
        //adding and custimizing the content inside the panel

        first= 0;
        //setting score of red to 0
        scoreP1 = new JPanel();
        //making the panel to hold the value of the score of red
        points = new JLabel(""+first);
        points.setFont(new Font("Arial", Font.BOLD ,80));
        points.setForeground(Color.red);
        //making the colour red to show how's score it is without needing a label to say a colour
        scoreP1.setBounds(700,301,99,99);
        scoreP1.setBackground(Color.white);
        //highlights this to show it is red's turn
        scoreP1.add(points);
        card2.add(scoreP1);
        //adding and custiminzing everything inside of the panel

        second = 0;
        //setting score of yellow to 0
        scoreP2 = new JPanel();
        //making the panel to hold the value of the score of yellow
        points2 = new JLabel(""+second);
        points2.setFont(new Font("Arial", Font.BOLD ,80));
        points2.setForeground(Color.yellow);
        //making the colour yellow to show how's score it is without needing a label to say a colour
        scoreP2.setBounds(801,301,99,99);
        scoreP2.setBackground(Color.black);
        scoreP2.add(points2);
        card2.add(scoreP2);
        //adding and custiminzing everything inside of the panel

        JPanel widgets = new JPanel();
        //making a panel that contains all the extra feautures
        widgets.setLayout(new GridLayout (2,2));
        //2,2 is in form of y,x 2 rows then 2 colms
        JButton widgets1[] [] = new JButton [2] [2];
        //initalizing the array to house the different widgets
        widgets1 [0] [0] = new JButton ("Settings");
        widgets1[0][0].setActionCommand("settings");
        widgets1 [0] [1] = new JButton ("Clear");
        widgets1[0][1].setActionCommand("clear");
        widgets1 [1] [0] = new JButton ("Menu");
        widgets1[1][0].setActionCommand ("menu");
        widgets1 [1] [1] = new JButton ("Rules");
        widgets1[1][1].setActionCommand("rules");
        //naming all the widgets and their action command as wel as their location inside of the array
        for (int x = 0 ; x < 2 ; x++){

            for (int y = 0 ; y < 2 ; y++){
                //2x2 grid
                widgets1 [x] [y].addActionListener (this);
                //gives all of them action listeners
                widgets1 [x] [y].setForeground (Color.black);
                widgets1 [x] [y].setBackground (new Color(2, 237, 131));
                widgets1 [x] [y].setPreferredSize(new Dimension(99,99));
                widgets1[x][y].setFont(new Font("Arial", Font.ITALIC ,15));
                widgets.add (widgets1 [x] [y]);
            }//end nested for
        }
        widgets.setBounds(700,401,201,201);
        card2.add(widgets);
        //just adding and custimizing everything which set bounds and colours

        add ("game", card2);
        //adding the game cards as well as it's name for actionperformed
    }

    public void actionPerformed (ActionEvent e){

        if (e.getActionCommand ().equals ("menu"))
            myCardLayout.show (this, "menu");
        //opens the menu

        else if (e.getActionCommand ().equals ("game")){

            if(Startplay==0){
                myCardLayout.show(this, "colour");
            }//this forces you to choose the start colour if it is the first time playing

            else if(Startplay>0){
                myCardLayout.show (this, "game");
            }//this allows you to go straight to the game without reseting everything

        }

        else if (e.getActionCommand ().equals("settings"))
            myCardLayout.show(this, "settings");
        //prompts the settings card

        else if (e.getActionCommand ().equals("colour"))
            myCardLayout.show(this, "colour");
        //prompts the colour pane

        else if (e.getActionCommand ().equals ("rules")){
            JOptionPane.showMessageDialog (null,"The goal of the game is to get 4 of the same coloured dots in a row, whether that be diagonal, straight or horizontal. \nYou can only play one dot per turn before the opponent has to play. \nThe opponent is to also think strategically about where to place dots in order to not give an advantage to their opponent. \nThe game also involves blocking players to stop a player from getting 4 in a row. \nA draw can also happen and that is only when all of the spaces are filled with no 'four in a rows'.",
                "Rules", JOptionPane.INFORMATION_MESSAGE);
            //https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
            //prompts the option pane which has the rules in it
        }

        else if(e.getActionCommand().equals("restart")){
            Clear(0);
            reset(0);
            //clears everything and resets everything
        }

        else if(e.getActionCommand().equals("clear")){
            Clear(startingTurn);
            //clears everthing without resetting the turns back to the start
        }

        else if(e.getActionCommand().equals("mute")){

            sound = (sound+1)%2;
            //uses the same way to change numbers as the turns by having either 1 or 0 as answer
            if(sound==1){
                clip.stop();
                MuteorPlay.setBackground(Color.red);
                MuteorPlay.setText("Play Sound");
                //if 1 the sound gets muted and the words and colour changes
            }

            else if(sound==0){
                clip.start();
                MuteorPlay.setBackground(Color.green);
                MuteorPlay.setText("Mute Sound");
                //if 0 the sound is allowed to be played and the colour changes with the words
            }

        }

        else if(e.getActionCommand().equals("red")){
            //this gets used if the user decides to change colour to red
            if (Startplay==0){
                turn=0;
                startingTurn = 0;
                Clear(0);
                Startplay=1;
                Change.setBackground(Color.red);
                myCardLayout.show (this, "game");
                //everything get set back to normal but startplay changes
                //it chnages because if it were to be 0 the start screen would loop
                //1 allows you to start play because if it were 0 you would have to change your colour
            }

            else if (Startplay>0){
                reset(1);
                turn=0;
                startingTurn = 0;
                Clear(0);
                Startplay=1;
                Change.setBackground(Color.red);
                //resets the game if startplay is larger than 0
            }

        }

        else if(e.getActionCommand().equals("yellow")){
            //this gets used if the user decides to change colour to yellow
            if (Startplay ==0){
                turn=1;
                startingTurn = 1;
                Clear(1);
                Startplay=1;
                Change.setBackground(Color.yellow);
                myCardLayout.show (this, "game");
                //everything gets set to where yellow chips get placed instead of red
                //the startplay is 1 because of the same reason as before
            }

            else if (Startplay>0){
                reset(1);
                turn=1;
                startingTurn = 1;
                Clear(1);
                Startplay=1;
                Change.setBackground(Color.yellow);
                //resets the board if you change your colour after the first time
            }

        }

        else if(e.getActionCommand().equals("0")||e.getActionCommand().equals("1")||e.getActionCommand().equals("2")||e.getActionCommand().equals("3")||e.getActionCommand().equals("4")||e.getActionCommand().equals("5")||e.getActionCommand().equals("6")){
            //sound file from https://freesound.org/people/lebaston100/sounds/192270/
            //the string just initalizes the name of the file
            String filepath="Click1.wav";
            //the audio method plays the file in the filepath string
            if (sound==0)
                audio(filepath);
            //turn can be either 1 or 0, 1 being red and 2 being yellow
            turn = (turn+1)%2;


            for(int j=0;j<=6;j++){
                //loops 7 times, 1 for each column
                String k = ""+j;
                //makes the int into a string that i can use as an actionCommand
                if(e.getActionCommand().equals(k)){

                    if(col[j]==-1){
                        JOptionPane.showMessageDialog (null,"Column Full. Cannot place in this column, pick a different column",
                            "Column Full", JOptionPane.INFORMATION_MESSAGE);
                        turn = (turn+1)%2;
                        //gives error for a full column and makes sure the turn doesn't switch
                    }

                    if (turn ==1){
                        btn[col[j]][j].setBackground(Color.red);
                        //sets a value for red if red plays
                        scoreP1.setBackground(Color.black);
                        scoreP2.setBackground(Color.white);
                        //highlights who's turn it is
                    }
                    else if(turn==0){
                        btn[col[j]][j].setBackground(Color.yellow);
                        //sets a value for yellow if yellow plays
                        scoreP1.setBackground(Color.white);
                        scoreP2.setBackground(Color.black);
                        //highlights whos turn it is
                    }

                    butval[col[j]][j]=turn;
                    //sets the value of the button as the turn it was played on, 1 or 0
                    if(col[j]>=0){
                        col[j]--;
                        //subtracts one from height of column until at -1
                        //this allows me to start from placing at the bottom and moving my way up to the top
                        //once the column is full errors will be displayed in an option pane 
                    }

                    if(col[0]==-1&&col[1]==-1&&col[2]==-1&&col[3]==-1&&col[4]==-1&&col[5]==-1&&col[6]==-1){
                        JOptionPane.showMessageDialog (null,"Board Full. Cannot place anywhere. Board Reset",
                            "Board Full", JOptionPane.INFORMATION_MESSAGE);
                        Clear(startingTurn);
                    }//this method clears board if it gets filled up
                }
            }

            //checking win now:
            //most of the code here follows the basic layout of the tic tac toe code
            //this is different as it checks the entire board and not 3x3 grid
            //the plus one and etc allows me to check everything

            //this checks all
            for (int x=0; x<6; x++) {

                for (int y=0; y<4; y++) {

                    if (butval[x][y] != 9   //making sure values are not equal to the basic amount
                    &&  butval[x][y] == butval[x][y+1] // making sure the value 1 to the right is equal to the previous
                        //the values are checking from left to right all across the board
                        //the +1,2,3 is just checking to make sure all the values are the same to confirma horizontal win
                    && butval[x][y] == butval[x][y+2] &&butval[x][y] == butval[x][y+3]) 

                    //comparing values to make sure they are all equal

                    {
                        //calls the method which prompts the win message option pane
                        win(turn);
                    }
                }
            }

            //this one checks for wins vertically as now the height (x) is 4 long
            //the length is now the full board
            for (int x=0; x<3; x++) {

                for (int y=0; y<7; y++) {
                    //the loop checks the entire board for verical wins
                    if (butval[x][y] != 9  &&//just checking to make sure the value inside isn't the basic non filled cell value
                    butval[x][y] == butval[x+1][y] &&
                    butval[x][y] == butval[x+2][y] &&
                        //these +1,2,3 are used for vertical wins starting from top to bottom 
                        //they make sure all values are equal which tells us if there is a win veritcally
                        //red and yellow have seperate values and this ensures there is no mix up in values  
                    butval[x][y] == butval[x+3][y]) {

                        //calls the win method which prompts the option pane
                        win(turn);

                    }

                }

            }

            //this loop checks the win going from top left to bottom right
            for (int x=0; x<3; x++) {

                for (int y=0; y<4; y++) {

                    if (butval[x][y] != 9 && //just making sure values are net set to their default
                    butval[x][y] == butval[x+1][y+1] && butval[x][y] == butval[x+2][y+2] &&butval[x][y] == butval[x+3][y+3])
                    //this is just adding one to x and y
                    //this allows the comp. to check for wins descending from top left to bottom right
                    {
                        //calls the win method which prompts the option pane
                        win(turn);

                    }

                }

            }

            //this loop checks for wins going from bottom right to top left
            for (int x=3; x<6; x++) {

                for (int y=0; y<4; y++) {

                    if (butval[x][y] != 9 && //making sure buttons not set to default values
                    butval[x][y] == butval[x-1][y+1] && butval[x][y] == butval[x-2][y+2] && butval[x][y] == butval[x-3][y+3])
                    //subtracting one from each allowing the comp. to check across the board
                    //the check comes from bottom right to top left
                    {
                        //calls the win method which prompts the option pane
                        win(turn);

                    }

                }

            }

        }
    }

    public void win(int a){
        //the method takes in 1 or 0 and that determines the colour
        String filepath="winSound.wav";
        if (sound==0)
                win(filepath);
        //.wav file of a win sound
        String WinColour="null";
        //initalizing the name of the colour
        if (a==1){
            WinColour= "red";
            first++;
            points.setText(""+first);

        }
        //if 1 it is red and red increase by one in score
        else if (a==0){
            WinColour= "yellow";
            second++;
            points2.setText(""+second);

        }//if 0 it is red and red increase by one in score

        JOptionPane.showMessageDialog (null, 
            "Game Over! " + WinColour +  " won!", 
            "Game Over!", 
            JOptionPane.INFORMATION_MESSAGE);
        //this displays the message of who won

        Clear(startingTurn);
        //calls the clear method, which clears the board
    }//the method displays the win message

    public void Clear(int a){
        for (int x = 0 ; x < rows ; x++){
            for (int y = 0 ; y < columns ; y++){
                butval[x] [y] = 9;
            }
        }//sets values of grid to default
        for(int k =0; k<=6;k++){
            col[k]=5;
        }
        //sets the coloumn heights back to zero
        for (int x = 0 ; x < rows ; x++){
            for (int y = 0 ; y < columns ; y++){
                btn[x][y].setBackground(Color.white);
            }
        }//sets the background of the grid to zero to show no played chips
        turn = a;
        //turn back to normal
        if (a==0){
            scoreP1.setBackground(Color.white);
            scoreP2.setBackground(Color.black);
        }//allows me to turn the colour to show who's turn it is back to normal

        else if (a==1){
            scoreP1.setBackground(Color.black);
            scoreP2.setBackground(Color.white);
        }//allows me to turn the colour to show who's turn it is back to normal

        //setting back to normal
    }//clears the grid and sets values back to default except for the score

    public void reset(int b){
        first=0;
        points.setText(""+first);
        second=0;
        points2.setText(""+second);
        turn = 0;
        clip.start();
        startingTurn = 0;
        Startplay=b;
        myCardLayout.show(this, "menu");
    }//sets all the score to zero and the turn to zero

    //the code for the music is from this, https://www.youtube.com/watch?v=TErboGLHZGA
    //the code allows me to play a clip when i press i button which calls this method
    public void audio(String audioFileName){
        //try and catch checks if the code works and if not it runs an error message
        try {
            File musicPath = new File(audioFileName);
            //this variable is the path of the file
            if (musicPath.exists()){

                //this gets the audio input from the file
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(ConnectFour.class.getResource(audioFileName));

                //this makes it a clip and this variable is from the global variable
                clip = AudioSystem.getClip();

                //this opens the sound file
                clip.open(audioInput);

                //this starts the clip
                clip.start();

            }
            else
            {System.out.println("Can't find the file");}
            //this message is displayed if the file path isn't found

        } catch (Exception ex) {
            System.out.println("Error");
            //this is ran if the code just doesn't end up running
        }
    }
    
     public void win(String WinName){
         //this method plays the win sound
        //try and catch checks if the code works and if not it runs an error message
        try {
            File winPath = new File(WinName);
            //this variable is the path of the file
            if (winPath.exists()){

                //this gets the audio input from the file
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(ConnectFour.class.getResource(WinName));

                //this makes it a clip and this variable is from the global variable
                win = AudioSystem.getClip();

                //this opens the sound file
                win.open(audioInput);

                //this starts the clip
                win.start();

            }
            else
            {System.out.println("Can't find the file");}
            //this message is displayed if the file path isn't found

        } catch (Exception ex) {
            System.out.println("Error");
            //this is ran if the code just doesn't end up running
        }
    }

    //the method that allows me to have images in my application
    protected static ImageIcon createImageIcon (String path) {
        java.net.URL imgURL = ConnectFour.class.getResource (path);
        if (imgURL != null) {
            return new ImageIcon (imgURL);
        } else {
            System.err.println ("Couldn't find file: " + path);
            return null;
        }
    }//end createImageIcon

}
