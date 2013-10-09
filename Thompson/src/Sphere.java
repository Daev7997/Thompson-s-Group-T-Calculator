import java.util.LinkedList;

public class Sphere
{
	private int[] inverses;
	private PLHom[] gen;
	private String[] genS;
	
	public int radius;
	private PLHom center;
	
	private LinkedList<Word> words;
	
	public Sphere(int[] inv, PLHom[] g, String[] gS, PLHom p)
	{
		inverses = inv;
		gen = g;
		genS = gS;
		center = p;
		radius = 1;
		words = new LinkedList<Word>();
		for(int i = 0; i < g.length; i++)
		{
			words.add(new Word(i));
		}
	}
	
	public void expand()
	{
		LinkedList<Word> n = new LinkedList<Word>();
		for(int i = 0; i < words.size(); i++)
		{
			for(int j = 0; j < gen.length; j++)
			{
				if(words.get(i).getLast() != inverses[j])
				{
					Word w = words.get(i).clone();
					w.add(j);
					n.add(w);
				}
			}
		}
		words = n;
		radius++;
	}
	
	public void write(OutputHandler text)
	{
		text.writeCayley(words, genS);
	}
	
	public BST toBST()
	{
		BST r = new BST(gen, center);
		for(int i = 0; i < words.size(); i++)
		{
			r.add(words.get(i));
		}
		return r;
	}
	
	public Word getWord(int i)
	{
		return words.get(i);
	}
	
	public PLHom getPLHom(int i)
	{
		return center.compose(words.get(i).getPLHom(gen));
	}
	
	public int size()
	{
		return words.size();
	}
}
