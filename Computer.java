public class Computer
{
    int choice;
    String difficulty;

    public Computer (String diff)
    {
	difficulty = diff;
	choice = 0;
    }


    public void setDifficulty (String diff)
    {
	difficulty = diff;
    }


    public int setChoice (String diff)
    {
	difficulty = diff;


	if (difficulty.compareTo ("EASY") == 0)
	{
	    //25%
	    choice = (int) (Math.random () * 4) + 1;
	    if (choice == 1)
	    {
		return 1;
	    }
	    else
	    {
		return 0;
	    }

	}
	else if (difficulty.compareTo ("NORMAL") == 0)
	{
	    //33.33%
	    choice = (int) (Math.random () * 3) + 1;
	    if (choice == 1)
	    {
		return 1;
	    }
	    else
	    {
		return 0;
	    }

	}

	// (difficulty.compareTo ("HARD") == 0)
	else
	{
	    //50%
	    choice = (int) (Math.random () * 2) + 1;

	    if (choice == 1)
	    {
		return 1;
	    }
	    else
	    {
		return 0;
	    }

	}
    }


    public String getDifficulty ()
    {
	return difficulty;
    }


    public int getChoice ()
    {
	return choice;
    }
}

