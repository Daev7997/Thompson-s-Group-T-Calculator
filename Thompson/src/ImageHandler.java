import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class ImageHandler 
{
	File imgFile;
	BufferedImage img;
	int height;
	int width;
	Graphics2D g;

	/**
	 * Makes an image handler for a specified PNG
	 * @param filename  Name of image file
	 * @throws IOException  File not found.
	 */
	public ImageHandler(String filename)
	{
		System.out.println("Now printing to " + filename);
		imgFile = new File(filename);
		try
		{
			img = ImageIO.read(imgFile);
		}
		catch(Exception e)
		{
			try
			{
				imgFile.createNewFile();
			}
			catch(Exception ex)
			{
				System.out.println("Your file system is borked");
			}
		}
		height = img.getHeight();
		width = img.getWidth();
		g = img.createGraphics();
		g.setBackground(Color.WHITE);
		clear();
	}
	
	/**
	 * Saves progress on drawing.  Called in other methods when they're done making changes.
	 */
	private void saveDrawing()
	{
		try
		{
			ImageIO.write(img, "png", imgFile);
		}
		catch(Exception e)
		{
			System.out.println("404");
		}	
	}
	
	/**
	 * Clears the image.
	 */
	public void clear()
	{
		g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public void graph(PLHom p, Color c, String name)
	{
		System.out.println(name + ":\n" + p);
        g.setColor(c);
		g.setStroke(new BasicStroke(2));
		
		p.remRed();
		LinkedList<Corner> corners = p.getCorners();
		for(int i = 0; i < corners.size() - 1; i++)
		{
			if(!p.wrappingInterval(i))
			{
				g.drawLine((int)(corners.get(i).getX().toDouble()*width), height - (int)(corners.get(i).getY().toDouble()*height), (int)(corners.get(i+1).getX().toDouble()*width), height - (int)(corners.get(i+1).getY().toDouble()*height));
			}
		}
		
		saveDrawing();
	}
}
