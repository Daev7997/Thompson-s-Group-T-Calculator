/**
 * Class representing a dyadic rational number; i.e. a/2^n
 * @author David McKlveen
 */
public class Dyadic implements Comparable<Dyadic>
{
	private int a;
	private int b;
	
	public Dyadic(int a, int b)
	{
		this.a = a;
		this.b = b;
	}
	
	//Somewhat less reliable of a constructor for really tiny numbers
	public Dyadic(double d)
	{
		b = 0;
		while(Math.floor(d) < d)
		{
			b++;
			d = d*2;
		}
		a = (int)d;
	}

	public int getA()
	{
		return a;
	}

	public int getB()
	{
		return b;
	}
	
	public void fixD(Dyadic d)
	{
		reduce();
		d.reduce();
		
		//i.e. 1/2 vs. 3/4 want to work with 1/2
		if(d.getB() < b)
		{
			d.fixD(this);
		}
		else
		{
			while(d.getB() > b)
			{
				b++;
				a = a*2;
			}
		}
	}
	
	public void reduce()
	{
		if(a != 0)
		{
			while(a % 2 == 0)
			{
				a = a/2;
				b--;
			}
		}
		else
		{
			b = 0;
		}
	}
	
	@Override
	public String toString()
	{
		reduce();
		if(a == 0)
		{
			return "0";
		}
		else if(a == 1 && b == 0)
		{
			return "1";
		}
		int d = (int)Math.pow(2, b);
		return a + "/" + d;
	}

	public Dyadic add(Dyadic d)
	{
		fixD(d);
		return new Dyadic(a+d.getA(), b);
	}
	
	public Dyadic subtract(Dyadic d)
	{
		fixD(d);
		return new Dyadic(a-d.getA(), b);
	}
	
	//Only works for powers of 2 and whole numbers.
	public Dyadic multiply(double d)
	{	
		int p = (int)(Math.log(d)/Math.log(2));
		if(p < 0)
		{
			return new Dyadic(a, b - p);
		}
		else
		{
			return new Dyadic(a*(int)d, b);
		}
	}
	
	public double divide(Dyadic d)
	{
		fixD(d);
		return (double)a/(double)d.getA();
	}

	@Override
	public int compareTo(Dyadic arg0) 
	{
		fixD(arg0);
		if(a < arg0.a)
		{
			return -1;
		}
		else if(arg0.a == a)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public boolean equals(Dyadic d)
	{
		fixD(d);
		return a == d.getA();
	}

	public double toDouble()
	{
		return a/Math.pow(2, b);
	}
}
