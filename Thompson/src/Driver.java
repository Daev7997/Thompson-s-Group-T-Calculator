public class Driver
{
	public static void main(String[] args) 
	{
		PLHom c0 = getC0();
		PLHom x0 = getX0();
		PLHom x0i = x0.invert();
		PLHom x1 = getX1();
		PLHom x1i = x1.invert();

//		PLHom[] genX = {x0, x0i, x1, x1i};
//		String[] genXS = {"x0 ", "x0i ", "x1 ", "x1i "};
//		int[] invX = {1, 0, 3, 2};
		
		PLHom[] genC = {x0, x0i, x1, x1i, c0};
		String[] genCS = {"x0 ", "x0i ", "x1 ", "x1i ", "c0 "};
		int[] invC = {1, 0, 3, 2, 4};
		
		PLHom[] p = {x0, x0, x0, x1i, x0i, x1, x1, getX(2).invert(), x1i, x0i};
		PLHom test = compose(p);
		
		System.out.println(findGeo(test, genC, invC, genCS, 15).toString(genCS));

		
//		int dist = 8;
//		InputHandler in = new InputHandler("Cayley.txt");
//		OutputHandler out = new OutputHandler("Stuff.txt");
//		Word w = in.nextWord();
//		
//		while(w != null)
//		{
//			if(w.getDist() == dist)
//			{
//				PLHom p = w.getPLHom(genC);
//				
//				Word w2 = findGeo(p, genC, invC, genCS, 10);
//				if(w2.equals(w))
//				{
//						out.writeWord(w, genCS);
//				}
//			}
//			else if(w.getDist() > dist)
//			{
//				System.out.println("Done with elements of radius " + dist);
//				dist++;
//			}
//			if(dist <= 8)
//			{
//				w = in.nextWord();
//			}
//			else
//			{
//				w = null;
//			}
//		}
//		in.close();
//		out.close();
	}
	
	public static PLHom getC0()
	{
		PLHom c0 = new PLHom();
		c0.addCorner(0, 1/2.0);
		c0.addCorner(1/2.0, 1);
		c0.addCorner(1/2.0, 0);
		c0.addCorner(1, 1/2.0);
		return c0;
	}
	
	public static PLHom getX0()
	{
		PLHom x0 = new PLHom();
		x0.addCorner(0, 0);
		x0.addCorner(0, 1);
		x0.addCorner(1/2.0, 1/4.0);
		x0.addCorner(3/4.0, 1/2.0);
		x0.addCorner(1, 0);
		x0.addCorner(1, 1);
		return x0;
	}
	
	public static PLHom getX1()
	{
		PLHom x1 = new PLHom();
		x1.addCorner(0, 0);
		x1.addCorner(0, 1);
		x1.addCorner(1/2.0, 1/2.0);
		x1.addCorner(3/4.0, 5/8.0);
		x1.addCorner(7/8.0, 3/4.0);
		x1.addCorner(1, 0);
		x1.addCorner(1, 1);
		return x1;
	}
	
	public static PLHom getX(int i)
	{
		PLHom x0 = getX0();
		PLHom x0i = x0.invert();
		PLHom x1 = getX1();
		
		PLHom[] ele = new PLHom[2*i - 1];
		for(int j = 0; j < ele.length; j++)
		{
			if(j < ele.length/2)
			{
				ele[j] = x0i;
			}
			else if(j > ele.length/2)
			{
				ele[j] = x0;
			}
			else
			{
				ele[j] = x1;
			}
		}
		return compose(ele);
	}
	
	public static PLHom getE()
	{
		PLHom c0 = new PLHom();
		c0.addCorner(0, 0);
		c0.addCorner(0, 1);
		c0.addCorner(1, 0);
		c0.addCorner(1, 1);
		return c0;
	}
	
	public static PLHom compose(PLHom[] p)
	{
		PLHom r = getE();
		for(int i = p.length - 1; i >= 0; i--)
		{
			r = p[i].compose(r);
		}
		return r;
	}

	public static Word findGeo(PLHom p, PLHom[] gen, int[] inv, String[] genS, int maxDist)
	{
		return findGeo(p, getE(), gen, inv, genS, maxDist);
	}
	
	public static Word findGeo(PLHom p, PLHom q, PLHom[] gen, int[] inv, String[] genS, int maxDist)
	{
		Sphere s1 = new Sphere(inv, gen, genS, q);
		Sphere s2 = new Sphere(inv, gen, genS, p);

		BST b;
		boolean exp1 = true;
		
		for(int i = 0; i < gen.length; i++)
		{
			if(p.equals(gen[i]))
			{
				return new Word(i);
			}
		}
		
		while(s1.radius + s2.radius <= maxDist)
		{
			b = s1.toBST();
			for(int i = 0; i < s2.size(); i++)
			{
				Word w2 = s2.getWord(i);
				Word w1 = b.search(s2.getPLHom(i));
				
				if(w1 != null)
				{
					return w1.merge(w2.invert(inv));
				}	
			}
			if(exp1)
			{
				s1.expand();
				exp1 = false;
			}
			else
			{
				s2.expand();
				exp1 = true;
			}
		}
		return null;
	}
}
