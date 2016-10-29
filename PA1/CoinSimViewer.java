/* *
 * class CoinSimViewer 
 * 
 * TheCoinSimViewer class shows a frame that contains a CoinSimComponent.

 */
import javax.swing.JFrame;
import  java.util.Scanner;

public class CoinSimViewer {

	/* *
	 * creates the JFrame containing the CoinSimComponent. 
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 500); 
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CoinSimComponent component = new CoinSimComponent();
		if (component.putinnum() == -1)
			return;
		frame.add(component);
		
		frame.setVisible(true);
		
	}
}
