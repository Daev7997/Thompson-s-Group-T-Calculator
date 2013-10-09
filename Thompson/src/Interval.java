import java.util.Arrays;

public class Interval
{
	private double m;
	private Dyadic b;
	private Corner[] corners;
	
	public Interval(Corner ca, Corner cb)
	{
		m = (ca.getY().subtract(cb.getY())).divide(ca.getX().subtract(cb.getX()));
		corners = new Corner[2];
		corners[0] = ca;
		corners[1] = cb;
		Arrays.sort(corners);
		b = ca.getY().subtract(ca.getX().multiply(m));
	}
	
	public Dyadic f(Dyadic d)
	{
		if(withinX(d))
		{
			return (d.multiply(m)).add(b);
		}
		else
		{
			return null;
		}
	}

	public Dyadic fInv(Dyadic d)
	{
		if(withinY(d))
		{
			return (d.subtract(b)).multiply(1.0/m);
		}
		else
		{
			return null;
		}
	}

	public double getM() 
	{
		return m;
	}

	public Dyadic getB() 
	{
		return b;
	}
	
	@Override
	public String toString()
	{
		return "[" + corners[0].getX() + ", " + corners[1].getX() + "]->[" + corners[0].getY() + ", " + corners[1].getY() + "]";
	}
	
	public boolean equals(Interval l2)
	{
		return l2.getM() == m && l2.getB().equals(b);
	}

	public boolean withinX(Dyadic d)
	{
		return (d.compareTo(corners[0].getX()) != -1)&&(d.compareTo(corners[1].getX()) != 1);
	}
	
	public boolean withinY(Dyadic d)
	{
		return (d.compareTo(corners[0].getY()) != -1)&&(d.compareTo(corners[1].getY()) != 1);
	}
}
