public class BST
{
	private Node start;
	private PLHom[] gen;
	private PLHom center;
	
	public BST(PLHom[] g, PLHom c)
	{
		gen = g;
		center = c;
		start = new Node(new Corner(1/2.0, 1/2.0));
	}
	
	public void add(Word w)
	{
		PLHom p = center.compose(w.getPLHom(gen));
		Corner c = p.getCorner((p.size()/2) - 1);
		
		Node n = start;
		boolean keepGoing = true;
		while(keepGoing)
		{
			if(c.compareTo(n.c) == -1)
			{
				if(n.left != null)
				{
					n = n.left;
				}
				else
				{
					n.left = new Node(c);
					n.left.add(w);
					keepGoing = false;
				}
			}
			else if(c.compareTo(n.c) == 1)
			{
				if(n.right != null)
				{
					n = n.right;
				}
				else
				{
					n.right = new Node(c);
					n.right.add(w);
					keepGoing = false;
				}
			}
			else
			{
				n.add(w);
				keepGoing = false;
			}
		}
	}
	
	public Word search(PLHom p)
	{
		Corner c = p.getCorner((p.size()/2) - 1);
		return searchHelper(c, start, p);
	}
	
	private Word searchHelper(Corner c, Node n, PLHom p)
	{
		if(c.compareTo(n.c) == -1)
		{
			if(n.left != null)
			{
				return searchHelper(c, n.left, p);
			}
			else
			{
				return null;
			}
		}
		else if(c.compareTo(n.c) == 1)
		{
			if(n.right != null)
			{
				return searchHelper(c, n.right, p);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return n.search(p, center, gen);
		}
	}
	
	@Override
	public String toString()
	{
		return start.toString(gen);
	}
}
