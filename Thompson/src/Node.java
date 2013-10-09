import java.util.LinkedList;

public class Node
{
	public Corner c;
	public Node left;
	public Node right;
	private LinkedList<Word> words;
	
	public Node(Corner nc)
	{
		words = new LinkedList<Word>();
		c = nc;
	}
	
	public void add(Word w)
	{
		words.add(w);
	}
	
	
	public Word search(PLHom p, PLHom c, PLHom[] gen)
	{
		for(int i = 0; i < words.size(); i++)
		{
			if(p.equals(c.compose(words.get(i).getPLHom(gen))))
			{
				return words.get(i);
			}
		}
		return null;
	}
	
	public String toString(PLHom[] gen)
	{
		String r = "Node " + c.toString() + ":\n";
		for(int i = 0; i < words.size(); i++)
		{
			r += words.get(i).toString(gen) + "\n";
		}
		if(left != null)
		{
			r += left.toString(gen);
		}
		if(right != null)
		{
			r += right.toString(gen);
		}
		return r;
	}
}
