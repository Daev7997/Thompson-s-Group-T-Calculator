import java.util.LinkedList;

public class Word
{
	private LinkedList<Integer> w;
	
	public Word(int i)
	{
		w = new LinkedList<Integer>();
		w.add(i);
	}
	
	public Word(int[] ar)
	{
		w = new LinkedList<Integer>();
		for(int i = 0; i<ar.length; i++)
		{
			w.add(ar[i]);
		}
	}
	
	public void add(int i)
	{
		w.add(new Integer(i));
	}
	
	public int getLast()
	{
		if(w.size() != 0)
		{
			return w.getLast().intValue();
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	public Word clone()
	{
		Word r = new Word(w.get(0));
		for(int i = 1; i < w.size(); i++)
		{
			r.add(w.get(i));
		}
		return r;
	}
	
	public PLHom getPLHom(PLHom[] g)
	{
		PLHom[] r = new PLHom[w.size()];
		for(int i = 0; i < w.size(); i++)
		{
			r[i] = g[w.get(i)];
		}
		return Driver.compose(r);
	}

	@Override
	public String toString()
	{
		String r = "";
		for(int i = 0; i < w.size(); i++)
		{
			r += w.get(i);
		}
		r += " " + w.size() + " ";
		return r;
	}
	
	public String toString(String[] gen)
	{
		String r = "";
		for(int i = 0; i < w.size(); i++)
		{
			r += w.get(i);
		}
		r += " " + w.size() + " ";

		for(int i = 0; i < w.size(); i++)
		{
			r += gen[w.get(i)];
		}
		return r;
	}
	
	public String toString(PLHom[] gen)
	{
		String r = "";
		for(int i = 0; i < w.size(); i++)
		{
			r += w.get(i);
		}
		r += " " + w.size() + "\n" + getPLHom(gen).corners();

		return r;
	}
	
	public int getDist()
	{
		return w.size();
	}
	
	public Word invert(int[] inv)
	{
		Word r = new Word(inv[w.getLast().intValue()]);
		for(int i = w.size() - 2; i >= 0; i--)
		{
			r.add(inv[w.get(i).intValue()]);
		}
		return r;
	}
	
	public LinkedList<Integer> getW()
	{
		return w;
	}
	
	public Word merge(Word wo)
	{
		LinkedList<Integer> l = wo.getW();
		Word r = this.clone();
		for(int i = 0; i < l.size(); i++)
		{
			r.add(l.get(i).intValue());
		}
		return r;
	}
	
	public boolean equals(Word wo)
	{
		if(wo.getDist() != this.getDist())
		{
			return false;
		}
		else
		{
			LinkedList<Integer> l = wo.getW();
			for(int i = 0; i < l.size(); i++)
			{
				if(!l.get(i).equals(w.get(i)))
				{
					return false;
				}
			}
		}
		return true;
	}
}
