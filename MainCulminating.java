/*ARE YOU SMARTER THEN A COMPUTER?
BY: Rebecca DiFilippo and Aiden Robionson
MAIN CULMINATING FILE
CULMINATING 2020 ICS4U MRS ALFANO
*/
import java.awt.*;
import java.io.*;
import hsa.Console;

public class MainCulminating
{
    static Console c;           // The output console

    public static void main (String[] args) throws IOException
    {
	c = new Console (); //console window

	//variables
	Color lightBlue = new Color (51, 204, 255);  // create a light blue colour
	String name;
	String timesPlayed;
	boolean inputTimesPlayed;
	String subjectFile;
	String correctAnswer;
	int mark = 0;
	int compMark = 0;
	String difficulty;
	Font courierBold20 = new Font ("Courier", Font.BOLD, 20);
	Font courierBold30 = new Font ("Courier", Font.BOLD, 30); //creates new font
	Font courierBold16 = new Font ("Courier", Font.BOLD, 16); //creates new font
	int count;
	int count2;
	int num;
	//arrays of records
	Question[] quiz = new Question [20];
	Computer[] robot = new Computer [20];

	welcome (); //calls the welcome method
	delay (100);

	c.setTextBackgroundColor (lightBlue); //sets text background (so white box doesnt appear)
	c.setTextColor (Color.red); //sets text colour to red

	//while loop that breaks when the user inputs Y/N (error checking)
	while (inputTimesPlayed = true)
	{
	    c.setFont (courierBold20);
	    c.clear ();
	    setTitleBackground ();
	    c.drawString ("Is this your first time playing ?(Y/N)", 20, 20);
	    c.setCursor (5, 5);
	    timesPlayed = c.readLine ();
	    inputTimesPlayed = introduction (timesPlayed); //calls the introduction method
	    c.clear ();

	    //if loop that breaks the while loop if user chose Y OR N
	    if (inputTimesPlayed == false)
	    {
		break;
	    }
	}

	c.clear ();
	subjectFile = setSubject (); //calls the set subject varible and saves the reuternd string value into a variable


	setQuiz (quiz, subjectFile); //creates the quiz with the 20 questions by calling the quiz method
	c.clear ();

	difficulty = chooseDifficulty (); //calls the choose difficulty method and saves the returend string value into a varible

	c.setFont (courierBold20); //sets font


	//do while loop that repeats until the user enters a name thats <15 charactes
	//or until the user picks a name that hasant been taken
	//2 different error cheking happening
	do
	{
	    setTitleBackground ();
	    c.drawString ("pick your username", 20, 20);
	    c.setCursor (5, 5); //sets cursor to row 5 coloumn 5
	    name = c.readString ();

	    //if loop that runs if the user chose a name that is greater than 15 charachters
	    if (name.length () > 15)
	    {
		setIntroductionBackground ();
		c.drawString ("name must be less than 15 charcters", 20, 20);
		delay (3000);
		c.clear ();
	    }

	    num = checkForName (subjectFile, name); //calls method that searchs specfic binary file for the users name

	    //if loop that runs if the user chose a name that has been taken already (for that specfic subject)
	    if (num == 1)
	    {
		setIntroductionBackground ();
		c.drawString ("that username has been taken", 20, 20);
		delay (3000);
		c.clear ();
	    }


	}
	while (name.length () > 15 || num == 1);


	countdown (name); //calls countdown method






	//fills the array of records by calling the construcotor from the computer class

	for (int i = 0 ; i < 20 ; i++)
	{
	    robot [i] = new Computer (difficulty);
	}




	//**********BASIS OF CODE*********************************************************************************************
	//runs 20 times since theres 20 questions in a quiz
	//prints the question and options onto the screen
	//asks the user for their answer and compares it to the correctAnswer
	//gets the computers answer and compares it to the correctAnswer
	//calculates the user's mark and the computers mark

	for (int i = 0 ; i < 20 ; i++)
	{
	    c.setFont (courierBold16);

	    correctAnswer = printQuestionAndOptions (quiz, i); //calls method that prints questions to screen,and saves the returned value into a varible

	    count = getUserAnswer (quiz, correctAnswer, i); //gets the users choice of the questions, and saves returned value into variable

	    //if the user choose one of the options it calcualte the users current mark
	    if (count >= 0)
	    {
		mark = mark + count;
	    }

	    //error check to make sure the user chose one of the options
	    //while loop breaks when the users input is A B C OR D
	    while (count < 0)
	    {
		c.clear ();
		setIntroductionBackground ();
		c.drawString ("you didnt choose any of the options, try again", 24, 450);
		c.setFont (courierBold20);
		delay (2000);
		c.clear ();
		correctAnswer = printQuestionAndOptions (quiz, i);
		count = getUserAnswer (quiz, correctAnswer, i);

	    }
	    //gets the computers randomized choice and calulates the mark
	    count2 = robot [i].setChoice (difficulty);
	    compMark = compMark + count2;
	    c.clear ();


	    setTitleBackground ();
	    c.setFont (courierBold30);
	    c.drawString (name + ":", 25, 40);
	    c.drawString ("Computer:", 300, 40);
	    c.setFont (courierBold16);

	    //if the user got the question right
	    if (count == 1)
	    {
		c.setColor (Color.green);
		c.drawString ("CORRECT", 25, 60);

	    }
	    //if the user got the question wrong
	    else
	    {
		c.setColor (Color.red);
		c.drawString ("INCORRECT", 25, 60);

	    }
	    //if the computer got the question right
	    if (count2 == 1)
	    {
		c.setColor (Color.green);
		c.drawString ("CORRECT", 310, 60);

	    }
	    //if the computer got the question wrong
	    else
	    {
		c.setColor (Color.red);
		c.drawString ("INCORRECT", 300, 60);

	    }
	    //prints current mark of the user and the computer
	    c.setColor (Color.black);
	    c.drawString ("CURRENT SCORE:" + mark + "/20", 20, 200);
	    c.drawString ("CURRENT SCORE:" + compMark + "/20", 300, 200);
	    c.drawString ("Correct Answer: " + correctAnswer, 150, 300); // prints the corret answer
	    delay (2000);
	}
	c.clear ();

	String binaryFile = setLeaderboard (name, mark, subjectFile); //calls the setLeaderbored method and saves the returend value into a varible
	getLeaderBoard (binaryFile, name); //calls the setLeaderbored method, which prints the leaderbored to the screen
	delay (5000);
	c.clear ();


	outro (mark, compMark); //calls the outro method





    } // main method


    public static void setTitleBackground ()
    {
	Color lightBlue = new Color (51, 204, 255);  // create a light blue colour
	c.setColor (lightBlue); //sets color
	c.fillRect (0, 0, 650, 500); //fills background
	c.setColor (Color.red); //sets color
    }


    public static void setIntroductionBackground ()
    {
	Color lightPink = new Color (255, 60, 110);  // create a light blue colour
	c.setColor (lightPink); //sets color
	c.fillRect (0, 0, 650, 500);  //fills background
	c.setColor (Color.black); //sets color
    }


    public static boolean introduction (String timesPlayed)
    {

	setIntroductionBackground ();
	Font courierBold20 = new Font ("Courier", Font.BOLD, 20); //creates new font
	Font courierBold13 = new Font ("Courier", Font.BOLD, 13); //creates new font
	c.setFont (courierBold20);
	timesPlayed = timesPlayed.toUpperCase (); //so that they dont have to put in capital Y or N

	//if the user has played before
	if (timesPlayed.compareTo ("N") == 0)
	{
	    c.drawString ("Welcome back", 20, 200);
	    delay (2000);
	    c.clear ();
	    return false; //returns falseto main(boolean)

	}

	//if the user has not played before
	else if (timesPlayed.compareTo ("Y") == 0)
	{
	    //prints instructions
	    c.drawString ("Welcome to ARE YOU SMARTER THAN A COMPUTER", 10, 100);
	    c.setFont (courierBold13);
	    c.drawString ("You will be answering 20 multiple choice questions on a subject of your choice", 10, 130);
	    c.drawString ("You will be competing against a computer with a difficuly of your choice too", 10, 160);
	    c.drawString ("Your score is saved and compared to previous players on a leaderboard", 10, 190);
	    c.drawString ("Goodluck", 10, 210);
	    c.drawString ("(Press any key to continue)", 10, 240);
	    c.getChar ();
	    c.clear ();
	    return false; //returns false to main(boolean)

	}

	//if the user did not enter y or n
	else
	{
	    c.drawString ("you did not enter Y/N, please try again", 20, 200);
	    delay (2000);
	    c.clear ();
	    return true; //returns true to main(boolean)

	}


    }


    public static void countdown (String name)
    {

	Font courierBold100 = new Font ("Courier", Font.BOLD, 100); //creates new font
	Font courierBold30 = new Font ("Courier", Font.BOLD, 30); //creates new font

	setIntroductionBackground ();
	c.setFont (courierBold30);
	c.drawString ("Good luck " + name, 100, 100);
	delay (2000);
	c.clear ();
	//prints coundown to the screen
	for (int i = 10 ; i > 0 ; i--)
	{
	    String num;
	    num = Integer.toString (i); //calls toString from Integer class
	    setTitleBackground ();
	    c.setFont (courierBold30);
	    c.drawString ("STARTING IN...", 20, 20);
	    c.setFont (courierBold100);
	    c.drawString (num, 270, 250);
	    delay (1000);
	    c.clear ();
	}


    }







    public static void welcome ()
    {
	Font courierBold80 = new Font ("Courier", Font.BOLD, 80); //creates new font
	Font courierBold50 = new Font ("Courier", Font.BOLD, 50); //creates new font
	Font courierBold20 = new Font ("Courier", Font.BOLD, 20); //creates new font
	String word = "WELCOME";
	int x = 70; //creates varible that holds the starting x cordinate
	c.setFont (courierBold80);
	setTitleBackground ();
	//prints welcome to the screen letter by letters
	for (int i = 0 ; i < word.length () ; i++)
	{
	    c.drawString (word.charAt (i) + "", x + 60, 200);
	    delay (200);
	    x = x + 60;

	}


	delay (500);
	c.clear ();

	//prints title to the screen
	setTitleBackground ();
	c.setFont (courierBold50);
	c.drawString ("ARE", 10, 100);
	delay (200);
	c.drawString ("YOU", 50, 150);
	delay (200);
	c.drawString ("SMARTER", 120, 200);
	delay (200);
	c.drawString ("THEN", 220, 250);
	delay (200);
	c.drawString ("A", 300, 300);
	delay (200);
	c.drawString ("COMPUTER?", 330, 350);
	c.setFont (courierBold20); //sets font
	c.drawString ("By: Rebecca DiFilippo And Aiden Robinson", 20, 450);
	c.setColor (Color.white);
	c.drawString ("Press any key to continue", 20, 470);
	c.getChar ();
	c.clear ();

    }


    public static String setSubject ()
    {
	String subject;
	String subjectFile;
	//runs until a user enters a correct subject
	do
	{
	    setTitleBackground ();
	    Font serifBold40 = new Font ("Serif", Font.BOLD, 40); //creates new font
	    c.setFont (serifBold40);

	    c.drawString ("What subject do you want to play", 50, 60);
	    Font courierBold20 = new Font ("Courier", Font.BOLD, 20); //creates new font
	    c.setColor (Color.white);
	    c.drawString ("Math", 230, 140);
	    c.drawString ("Science", 230, 240);
	    c.drawString ("Geography", 230, 340);
	    c.drawString ("History", 230, 440);

	    Font courierBold15 = new Font ("Courier", Font.BOLD, 15); //creates new font
	    c.setFont (courierBold15);
	    c.setColor (Color.black);
	    c.drawString ("Press 1 of Math, 2 for Science, 3 for Geography, and 4 for History", 20, 470);
	    c.setCursor (25, 40);
	    subject = c.readString (); //gets user's choice of subject

	    //changes 1,2,3, or 4 to word equivelent(we did this so the user didnt have to type in the full subject name)
	    if (subject.compareTo ("1") == 0)
	    {
		subject = "Math";
		c.clear ();
		break;
	    }
	    else if (subject.compareTo ("2") == 0)
	    {
		subject = "Science";
		c.clear ();
		break;
	    }
	    else if (subject.compareTo ("3") == 0)
	    {
		subject = "Geography";
		c.clear ();
		break;
	    }
	    else if (subject.compareTo ("4") == 0)
	    {
		subject = "History";
		c.clear ();
		break;
	    }
	    //error checks if the user didnt enter 1,2,3 or 4
	    else
	    {
		c.drawString ("subject chosen was not an option", 100, 500);
		delay (3000);
		c.clear ();
	    }

	}
	while (subject.compareTo ("Math") != 0 || subject.compareTo ("Science") != 0 || subject.compareTo ("Geography") != 0 || subject.compareTo ("History") != 0);

	//returns the file name depedning on whcih subject the user wnts to play
	if (subject.compareTo ("Math") == 0)
	{
	    subjectFile = "Math.txt";
	    return subjectFile;
	}


	else if (subject.compareTo ("Science") == 0)
	{
	    subjectFile = "Science.txt";
	    return subjectFile;
	}


	else if (subject.compareTo ("Geography") == 0)
	{
	    subjectFile = "Geography.txt";
	    return subjectFile;
	}


	else
	{
	    subjectFile = "History.txt";
	    return subjectFile;
	}

    }


    public static void setQuiz (Question[] quiz, String subjectFile) throws IOException
    {
	BufferedReader fr = new BufferedReader (new FileReader (subjectFile)); //opens certain text file depeding on the subjectFile

	//fills in array of records or type question (quiz)
	for (int i = 0 ; i < 20 ; i++)
	{
	    String question = fr.readLine ();
	    String[] opt = new String [4];
	    for (int x = 0 ; x < 4 ; x++)
	    {
		opt [x] = fr.readLine ();
	    }

	    quiz [i] = new Question (question, opt, fr.readLine ());
	}

    }



    public static String printQuestionAndOptions (Question[] quiz, int x)
    {

	Font courierBold16 = new Font ("Courier", Font.BOLD, 16); //creates new font
	String question = quiz [x].getQuestion (); //sets up curret question in the records array into local variable
	String[] opt = new String [4]; //creates a string array
	opt = quiz [x].getOptions (); //gets current questions 4 options andreads to an array
	int yCor = 50; //sets up starting y cordinate
	setTitleBackground ();
	c.setFont (courierBold16);
	c.drawString (question, 20, 30); //draws qustion to screen
	//draws the questions to the screen
	for (int i = 0 ; i < 4 ; i++)
	{
	    c.drawString (opt [i], 60, yCor);
	    yCor = yCor + 50;
	}
	c.drawString ("Question:" + (x + 1) + "/20", 430, 400);

	String correctAnswer = quiz [x].getCorrectAnswer (); //sets up current questions answer into variable
	return correctAnswer; //returns the correctAnswer varaible to main
    }


    public static int getUserAnswer (Question[] quiz, String correctAnswer, int x)
    {
	//metionining that this method is in a while loop in main, so this method will keep getting called until the method returns 1 or 0

	Font courierBold16 = new Font ("Courier", Font.BOLD, 16);
	c.setFont (courierBold16); //creates new font
	String userChoice;
	String[] opt = new String [4]; //creates a string array
	c.drawString ("Choose your answer by pressing A,B,C,D", 20, 455);
	c.setCursor (23, 52);
	userChoice = c.readString ();
	userChoice = userChoice.toUpperCase (); //error checks if the user puts in lowercase
	opt = quiz [x].getOptions (); //gets current questions 4 options andreads to an array

	//changes the choice from the letter they choice to the actual String value
	if (userChoice.compareTo ("A") == 0)
	{
	    userChoice = opt [0];
	    c.clear ();

	}


	else if (userChoice.compareTo ("B") == 0)
	{
	    userChoice = opt [1];
	    c.clear ();

	}


	else if (userChoice.compareTo ("C") == 0)
	{
	    userChoice = opt [2];
	    c.clear ();

	}


	else if (userChoice.compareTo ("D") == 0)
	{
	    userChoice = opt [3];
	    c.clear ();

	}

	//error checking if the user didnt input A B C or D
	else
	{

	    return -1; //returns -1 to main
	}

	//returns number based on if the user got the correct answer or not

	if (userChoice.compareTo (correctAnswer) == 0)
	{
	    return 1; //returns 1 to main if the user got the answer right
	}
	else
	{
	    return 0; //returns 0 to main if the user got the answer right
	}



    }


    public static String chooseDifficulty ()
    {
	String difficulty;
	//keeps repating the do while loop until the user enters one of the options (1,2,3,4)
	//error checking
	do
	{
	    setTitleBackground ();

	    Font courierBold20 = new Font ("Courier", Font.BOLD, 20);
	    c.setFont (courierBold20);
	    c.drawString ("What level do you want to play?", 20, 20);
	    c.drawString ("Easy = 1, Normal =2, Hard = 3", 20, 40);
	    c.setCursor (5, 5);
	    difficulty = c.readString (); //gets users choice of difficulty

	    //changes 1,2,3, or 4 to the word equivelent (we did this so the user didnt have to type in the diffuculty letter by letter)
	    if (difficulty.compareTo ("1") == 0)
	    {
		difficulty = "EASY";
		break;

	    }

	    else if (difficulty.compareTo ("2") == 0)
	    {
		difficulty = "NORMAL";
		break;

	    }



	    else if (difficulty.compareTo ("3") == 0)
	    {
		difficulty = "HARD";
		break;

	    }



	    else
	    {
		setIntroductionBackground ();
		c.drawString ("That was not an option", 20, 300);
		delay (2000);
		c.clear ();

	    }
	    c.setFont (courierBold20);
	}
	while (difficulty.compareTo ("EASY") != 0 || difficulty.compareTo ("NORMAL") != 0 || difficulty.compareTo ("HARD") != 0);

	return difficulty; //returns the users chosen difficulty to main
    }


    public static String setLeaderboard (String name, int mark, String subjectFile) throws IOException
    {

	String binName;
	//sets the binary file name depending on wich subject was chosen ( subjectFile tells us which subject)
	if (subjectFile.compareTo ("Math.txt") == 0)
	{
	    binName = "Math.bin";

	}
	else if (subjectFile.compareTo ("Science.txt") == 0)
	{
	    binName = "Science.bin";

	}
	else if (subjectFile.compareTo ("History.txt") == 0)
	{
	    binName = "History.bin";

	}
	else
	{
	    binName = "Geography.bin";

	}

	RandomAccessFile raf = new RandomAccessFile (binName, "rw"); //opens binary file depending on which subject it will open a different file

	byte[] nameBytes = new byte [15]; //creates byte array
	name.getBytes (0, name.length (), nameBytes, 0);

	raf.seek (raf.length ()); //seeks to the end of the file

	raf.write (nameBytes); //writes users name to the file
	raf.writeInt (mark); //writes users mark to the file

	raf.close (); //closes and saves changes to file

	return binName; //returns which bianry file has been opened to the main


    }


    public static void getLeaderBoard (String binName, String name) throws IOException
    {


	RandomAccessFile raf = new RandomAccessFile (binName, "rw"); //opens binary file depnding on which subject

	long fileLength = raf.length (); //local variable for file length
	int length = (int) (long) fileLength; //converts file length to an integer
	int entries = length / 19;// calculates how many entries there are, or how many people have played, (19 is how many bytes each record takes up in the binary)
	Scoreboard leaderboard[] = new Scoreboard [entries]; //creates an array  of type scoreboard with the amount of entries as the number of records




	for (int i = 0 ; i < entries ; i++)
	{
	    raf.seek (i * 19);
	    byte[] nameBytes = new byte [15];//restricts the name to 15 bytes
	    raf.read (nameBytes); //reads in the name
	    String userName = new String (nameBytes, 0);//converts the name in binary to a String
	    int score = raf.readInt (); //reads in the score
	    leaderboard [i] = new Scoreboard (score, userName.trim ());//fills one record in the array of records, also trims the name if it is under 15 characters
	}

	//sorting algorithm
	int Switch = 1;// a variable to determine if a switch has been made per pass of this sort
	Scoreboard Temp;// temporary place holder that stores a record address, of type Scoreboard

	while (Switch != 0)//will continue sorting until no more swaps have been made
	{
	    Switch = 0;
	    for (int j = 0 ; j < entries - 1 ; j++)//goes throuh the array (one pass at a time)
	    {
		if (leaderboard [j].getScore () < leaderboard [j + 1].getScore ())//compares if the score to the right of it is greater
		{
		    Temp = leaderboard [j];                             //if so, it will swap them
		    leaderboard [j] = leaderboard [j + 1];
		    leaderboard [j + 1] = Temp;
		    Switch = 1;//indicates that a switch has been made
		}
	    }
	}
	c.clear ();
	setTitleBackground ();  //resets the screen
	c.drawString ("LEADERBORED", 60, 20);
	c.drawString ("PLAYER          SCORE", 0, 50);

	if (entries > 10)//if there are more then 10 people on the leaderboard, it will only display the top 10
	{
	    int yCor = 70;

	    for (int i = 0 ; i < 10 ; i++)
	    {
		c.drawString (leaderboard [i].getName (), 0, yCor);//prints the name of a player
		String score = Integer.toString (leaderboard [i].getScore ()); //converts the score to a string
		c.drawString (score, 200, yCor);//prints the score of a player
		yCor = yCor + 20;//moves down a line
	    }
	}
	else            //if there are under 10 people on the leaderboard, it will just show all of them
	{
	    int yCor = 70;
	    for (int i = 0 ; i < entries ; i++)
	    {

		c.drawString (leaderboard [i].getName (), 0, yCor);//prints the name of a player
		String score = Integer.toString (leaderboard [i].getScore ());//converts the score to a string
		c.drawString (score, 200, yCor);//prints the score of a player
		yCor = yCor + 20;//moves dowm a line
	    }
	}
	delay (5000);
	c.clear ();
	setTitleBackground ();

	//searching algorithm
	int location = -1;      //declares the location variable

	for (int i = 0 ; i < entries ; i++)//passes through each record
	{
	    if (name.compareTo (leaderboard [i].getName ()) == 0) //compares the user's name, to where it is in the array of records
	    {
		location = i;       //determines the ranking the user is on the array of records (placement)
		break;
	    }



	}
	c.drawString ("YOU PLACED " + (location + 1), 20, 20);//prints their placement
	c.drawString ("out of " + entries + " people who have played this subject", 20, 50);


    }


    public static int checkForName (String subjectFile, String name) throws IOException
    {
	String binName;
	//sets binary file name variable to specfic name dependig on which subject the user wants to play
	if (subjectFile.compareTo ("Math.txt") == 0)
	{
	    binName = "Math.bin";

	}
	else if (subjectFile.compareTo ("Science.txt") == 0)
	{
	    binName = "Science.bin";

	}
	else if (subjectFile.compareTo ("History.txt") == 0)
	{
	    binName = "History.bin";

	}
	else
	{
	    binName = "Geography.bin";

	}

	RandomAccessFile raf = new RandomAccessFile (binName, "rw"); //opens binary file depending on which subject
       
	long fileLength = raf.length ();
	int length = (int) (long) fileLength;


	//returns 0 if there is no one whos saved into the bianry file(empty)
	if (length == 0)
	{
	    raf.close ();
	    return 0;
	}
	//if the bianry file isnt empty , compare the users name to each name in the binary file of the subject
	//and if the name the user choice is equal to a prevouis players name it will return 1
	else
	{
	    for (int i = 0 ; i < length ; i++)
	    {
		raf.seek (i * 19);
		byte[] nameBytes = new byte [15];
		raf.read (nameBytes);
		String userName = new String (nameBytes, 0);
		userName = userName.trim ();
		//checks to see if the current name we are veiwing in the file is the same as the users desired user name
		if (userName.compareTo (name) == 0)
		{
		    raf.close ();
		    return 1; // returns 1 if the users name is found

		}


	    }

	}
	raf.close ();
	return 0; //returns 0 since the users name was not found
    }


    public static void outro (int mark, int compMark)
    {
   
	Font courierBold50 = new Font ("Courier", Font.BOLD, 50); //creates new font
       
	int pointDiff = mark - compMark; //finds the difference between the user and the computers mark
	pointDiff = Math.abs (pointDiff); //returns the abosutle value so there is not negative point differnce
	setTitleBackground ();
       
	c.drawString ("YOUR FINAL SCORE:" + mark + "/20", 20, 200);//prints final score of user to the sreen
	c.drawString ("COMPUTERS FINAL SCORE:" + compMark + "/20", 300, 200);//prints final score of computer to the sreen
	delay (5000);
	c.clear ();
	//if the user beat the computer
	if (mark > compMark)
	{
	    setTitleBackground ();
	    c.drawString ("congrats you defeated the computer by: " + pointDiff + " points", 20, 20);
	    delay (4000);

	}
	//if the computer beat the user
	else if (mark < compMark)
	{
	    setTitleBackground ();
	    c.drawString ("The computer defeated you  by: " + pointDiff + " points", 20, 20);
	    delay (4000);
	}
	//if the computer and the user tied
	else
	{
	    setTitleBackground ();
	    c.drawString ("You tied the computer", 20, 20);
	    delay (4000);
	}
	c.clear ();
	setIntroductionBackground ();
	String word = "GOODBYE";
	c.setFont (courierBold50);
	int x = 70; //sets the starting x value cordinate
       
	//prints out goodbye letter by letter
	for (int i = 0 ; i < word.length () ; i++)
	{
	    c.drawString (word.charAt (i) + "", x , 200);
	    delay (200);
	    x = x + 60;

	}
	delay (100);
	c.clear ();



    }

    public static void delay (int millisecs)  // Delay Method
    {
	try
	{
	    Thread.currentThread ().sleep (millisecs);
	}


	catch (InterruptedException e)
	{
	}
    }
} // MainCulminating class
