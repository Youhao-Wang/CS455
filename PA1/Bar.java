
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/* *
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {

	private int bottom;
	private int left;
	private int width;
	private int numUnits;
	private int height;
	private Color color;
	private String label;
   /* *
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter unitsPerPixel). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar
      @param numUnits  height of the bar in application units
      @param unitsPerPixel  how many application units per pixel for height
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */

	
   public Bar(int bottom, int left, int width, int numUnits, 
              double unitsPerPixel, Color color, String label) {
	   this.bottom = bottom;
	   this.left = left;
	   this.width = width;
	   this.numUnits = numUnits;
	   this.height = (int)unitsPerPixel;
	   this.color = color;
	   this.label = label;
	   
   }
   

   /* *
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   Rectangle rect = new Rectangle(left,bottom,width,height);

	   g2.setColor(Color.BLACK);
	   int strWidth = g2.getFontMetrics().stringWidth(label);
	   g2.drawString(label,left-strWidth / 2 + 25, height + bottom +20);
	   g2.setColor(color);
	   g2.fill(rect);
	   
	   

   }
}
