public class Corner implements Comparable<Corner>
{
	private Dyadic x;
	private Dyadic y;
	
	public Corner(Dyadic x, Dyadic y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Corner(double xd, double yd)
	{
		x = new Dyadic(xd);
		y = new Dyadic(yd);
	}
	
	public Dyadic getX()
	{
		return x;
	}

	public Dyadic getY()
	{
		return y;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int compareTo(Corner o)
	{
		if(x.compareTo(o.getX()) != 0)
		{	
			return x.compareTo(o.getX());
		}
		else
		{
			return y.compareTo(o.getY())*(-1);
		}
	}
	
	public boolean equals(Corner c)
	{
		return x.equals(c.getX()) && y.equals(c.getY());
	}
}
