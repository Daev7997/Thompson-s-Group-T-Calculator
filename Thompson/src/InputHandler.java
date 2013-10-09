import java.io.BufferedReader;
import java.io.FileReader;

public class InputHandler
{
	private BufferedReader in;
	
	public InputHandler(String name)
	{
		try
		{
			in = new BufferedReader(new FileReader(name));
		}
		catch(Exception e)
		{
			System.out.println("Your file system is borked");
		}
	}
	
	public Word nextWord()
	{
		try
		{
			String s = in.readLine();
			if(s != null)
			{
				///Figure out
				int space1 = s.indexOf(' ');
				String sub = s.substring(0, space1);
				Word r = new Word(Integer.parseInt(sub.substring(0, 1)));
				for(int i = 1; i < sub.length(); i++)
				{
					r.add(Integer.parseInt(sub.substring(i, i+1)));
				}
				return r;
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Your file system is borked");
			return null;
		}
	}
	
	public void close()
	{
		try
		{
			in.close();
		}
		catch(Exception e)
		{
			System.out.println("Your file system is borked");
		}
	}
}
