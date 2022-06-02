public class Question
{
    String question;
    String options[] = new String [4];
    String correctAnswer;

    public Question (String q, String opt[], String answer)
    {
	question = q;
	options = opt;
	correctAnswer = answer;
    }


    public void setQuestion (String q)
    {
	question = q;
    }


    public void setOptions (String opt[])
    {
	options = opt;
    }


    public void setCorrectAnswer (String answer)
    {
	correctAnswer = answer;
    }


    public String getQuestion ()
    {
	return question;
    }


    public String[] getOptions ()
    {
	return options;
    }


    public String getCorrectAnswer ()
    {
	return correctAnswer;
    }
}


