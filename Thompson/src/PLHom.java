import java.util.Collections;
import java.util.LinkedList;

public class PLHom
{
	LinkedList<Corner> corners;
	
	public PLHom()
	{
		corners = new LinkedList<Corner>();
	}
	
	public PLHom copy()
	{
		PLHom p = new PLHom();
		for(int i = 0; i < corners.size(); i++)
		{
			p.addCorner(corners.get(i));
		}
		return p;
	}
	
	public Corner getCorner(int i)
	{
		return corners.get(i);
	}
	
	public int size()
	{
		return corners.size();
	}
	
	public void addCorner(Corner c)
	{
		corners.add(c);
		remDup();
	}
	
	public void addCorner(Dyadic x, Dyadic y)
	{
		addCorner(new Corner(x, y));
	}
	
	public void addCorner(double x, double y)
	{
		addCorner(new Corner(x, y));
	}

	public void addCorner(Dyadic y)
	{
		for(int i = 0; i < corners.size() - 1; i++)
		{
			if(!wrappingInterval(i))
			{
				Interval in = new Interval(corners.get(i), corners.get(i+1));
				if(in.withinY(y))
				{
					addCorner(in.fInv(y), y);
					return;
				}
			}
		}
	}
	
	public Dyadic fInv(Dyadic y)
	{
		for(int i = 0; i < corners.size() - 1; i++)
		{
			if(!wrappingInterval(i))
			{
				Interval in = new Interval(corners.get(i), corners.get(i+1));
				if(in.withinY(y))
				{
					return in.fInv(y);
				}
			}
		}
		return null;
	}
	
	public Dyadic f(Dyadic x)
	{
		for(int i = 0; i < corners.size() - 1; i++)
		{
			if(!wrappingInterval(i))
			{
				Interval in = new Interval(corners.get(i), corners.get(i+1));
				if(in.withinX(x))
				{
					return in.f(x);
				}
			}
		}
		return x;
	}
	
	public LinkedList<Corner> getCorners()
	{
		return corners;
	}
	
	public void addRedundantCorners(PLHom p)
	{
		for(int i = 0; i < corners.size(); i++)
		{
			p.addCorner(corners.get(i).getX());
		}
	}

	public PLHom compose(PLHom q)
	{
		PLHom p = q.copy();
		remRed();
		p.remRed();
		addRedundantCorners(p);
		
		LinkedList<Corner> points = p.getCorners();
		PLHom r = new PLHom();
		for(int i = 0; i < points.size(); i++)
		{
			Corner c = new Corner(points.get(i).getX(), f(points.get(i).getY()));
			r.addCorner(c);
			if(c.getY().equals(new Dyadic(1))) //&& !c.getX().equals(new Dyadic(1)) && !c.getX().equals(new Dyadic(0)))
			{
				r.addCorner(new Corner(c.getX(), new Dyadic(0)));
			}
		}
		r.remRed();
		p.remRed();
		
		return r;
	}
	
	public void remDup()
	{
		Collections.sort(corners);
		for(int i = 0; i < corners.size()-1; i++)
		{
			if(corners.get(i).equals(corners.get(i+1)))
			{
				corners.remove(i+1);
				i--;
			}
		}
	}
	
	public void remRed()
	{
		remDup();
		for(int i = 0; i < corners.size() - 2; i++)
		{
			if(!wrappingInterval(i) && !wrappingInterval(i+1))
			if(new Interval(corners.get(i), corners.get(i+1)).equals(new Interval(corners.get(i+1), corners.get(i+2))))
			{
				corners.remove(i+1);
				i--;
			}
		}
	}
	
	public String toString()
	{
		String r = "";
		for(int i = 0; i < corners.size() - 1; i++)
		{
			if(!wrappingInterval(i))
			{
				r += new Interval(corners.get(i), corners.get(i+1)) + "\n";
			}
		}
		return r;
	}
	
	public String corners()
	{
		String r = "";
		for(int i = 0; i < corners.size(); i++)
		{
			r += corners.get(i) + "\n";
		}
		return r;
	}

	public boolean wrappingInterval(int i)
	{
		return corners.get(i).getX().equals(corners.get(i+1).getX());
	}

	public boolean equals(PLHom p)
	{
		remRed();
		p.remRed();
		LinkedList<Corner> corners2 = p.getCorners();
		if(corners.size() != corners2.size())
		{
			return false;
		}
		
		for(int i = 0; i < corners.size(); i++)
		{
			if(!corners.get(i).equals(corners2.get(i)))
			{
				return false;
			}
		}
		return true;
		
	}

	public PLHom invert()
	{
		remRed();
		PLHom r = new PLHom();
		for(int i = 0; i < corners.size(); i++)
		{
			r.addCorner(corners.get(i).getY(), corners.get(i).getX());
		}
		r.remRed();
		return r;
	}
	
	public boolean isElement()
	{
		remRed();
		if(!corners.get(corners.size()-1).getX().equals(new Dyadic(1)))
		{
			return false;
		}
		else if(!corners.get(0).getX().equals(new Dyadic(0)))
		{
			return false;
		}

		for(int i = 0; i < corners.size() - 1; i++)
		{
			if(!wrappingInterval(i))
			{
				double slope = new Interval(corners.get(i), corners.get(i+1)).getM();
				if(Math.floor(Math.log(slope)/Math.log(2))!= Math.log(slope)/Math.log(2))
				{
					return false;
				}
				else if(slope <= 0)
				{
					return false;
				}
			}
		}
		return true;
	}
}
