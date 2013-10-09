import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

public class OutputHandler
{
	private PrintWriter out;
	
	public OutputHandler(String filename)
	{
		try
		{
			out = new PrintWriter(new FileWriter(filename));
		}
		catch(Exception e)
		{
			System.out.println("IOException");
		}
	}
	
	public void writeCayley(LinkedList<Word> sphere, String[] gen)
	{
		for(int i = 0; i < sphere.size(); i++)
		{
			out.println(sphere.get(i).toString(gen));
		}
	}
	
	public void writeWord(Word w, String[] gen)
	{
		out.println(w.toString(gen));
	}
	
	public void close()
	{
		out.close();
	}
}
