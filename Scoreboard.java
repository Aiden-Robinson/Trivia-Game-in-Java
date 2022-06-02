public class Scoreboard
{

    int score;      //variables that go into the scoreboard
    String name;

    public Scoreboard (int score, String name)      //contructor
    {
	this.score = score;
	this.name = name;
    }

    //mehtods
    public int getScore ()      //returns score
    {
	return score;
    }


    public void setScore (int score)        //sets score
    {
	this.score = score;
    }


    public String getName ()    //returns name
    {
	return name;
    }


    public void setName (String name)   //sets name
    {
	this.name = name;
    }


    public String toString ()   //prints out name and score
    {
	return getName () + "       "+ getScore ();
    }
}


