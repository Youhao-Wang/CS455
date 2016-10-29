/* *
 * The CoinSimComponent class displays the drawing.
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JComponent;

public class CoinSimComponent extends JComponent{
	
	private static int number;
	private static int num_trials;
	private static int num_twoheads;
	private static int num_headtails;
	private static int num_twotails;
	private static final int BAR_WIDTH = 50; 
	
	/* *
	 * 1. error-checking  error check whether a positive value is entered. 
	 * 2. if the input value is legal, this method will run the CoinTossSimulator and get the result. 
	 */
	public int putinnum(){
		System.out.print("Pleas enter the number of trials:  ");
		Scanner in = new Scanner (System.in);
		number = in.nextInt();
		if (number <= 0)
		{
			System.out.println("ERROR: Number entered must be greater than 0");
			return -1;
		}
		CoinTossSimulator simul = new CoinTossSimulator();
		simul.run(number);
		 num_trials = simul.getNumTrials();
		 num_twoheads = simul.getTwoHeads();
		 num_headtails = simul.getHeadTails();
		 num_twotails = simul.getTwoTails();
		return number;
	}
	
	
	/* *
	 * This component draws three bars with label
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{	
		
		
		Graphics2D g2 = (Graphics2D) g;
		int width =  getWidth();
		int height = getHeight();

		
		int precent1 = 100 * num_twoheads / num_trials;
		int precent2 = 100 * num_headtails / num_trials;
		int precent3 = 100 * num_twotails / num_trials;

		double height1 = (height - 100) * num_twoheads / num_trials;
		double height2 = (height - 100) * num_headtails / num_trials;
		double height3 = (height - 100) * num_twotails / num_trials;
		
		int bottom1 = height - 60 - (int)height1;
		int bottom2 = height - 60 - (int)height2;
		int bottom3 = height - 60 - (int)height3;
		


		
		Bar bar1 = new Bar(bottom1, width/4-BAR_WIDTH/2, BAR_WIDTH, num_twoheads, 
	              height1, Color.RED, "Two Head: "+num_twoheads+"("+precent1+"%)");

			Bar bar2 = new Bar(bottom2, width*2/4-BAR_WIDTH/2, BAR_WIDTH, num_headtails, 
	              height2, Color.GREEN, "A Heand and a Tail: "+num_headtails+"("+precent2+"%)");

			Bar bar3 = new Bar(bottom3, width*3/4-BAR_WIDTH/2, BAR_WIDTH, num_twotails, 
	              height3, Color.BLUE, "Two Tails: "+num_twotails+"("+precent3+"%)");
		
		bar1.draw(g2);
		bar2.draw(g2);
		bar3.draw(g2);
	}
	


}
