/* *
 * class CoinTossSimulatorTester 
 * 
 * Using the given date to test my CoinTossSimulator class 
 * independently from its use in the CoinTossViewer program. 
 */
public class CoinTossSimulatorTester {
	public static void main(String[] args)
	{
		CoinTossSimulator test1 = new CoinTossSimulator ();
		
		System.out.println("After constructor:");
		System.out.print("Number of trials: " );
		System.out.println(test1.getNumTrials());
		//test1.run(0);
		System.out.print("Two-head tosses: " );
		System.out.println(test1.getTwoHeads());
		System.out.print("Two-tail tosses: " );
		System.out.println(test1.getTwoTails());
		System.out.print("One-head one-tail tosses: " );
		System.out.println(test1.getHeadTails());
		System.out.print("Tosses add up correctly?: ");
		System.out.println(test1.check());
		System.out.println();
		
		System.out.println("After run(1): ");
		test1.run(1);
		System.out.print("Number of trials: " );
		System.out.println(test1.getNumTrials());
		System.out.print("Two-head tosses: " );
		System.out.println(test1.getTwoHeads());
		System.out.print("Two-tail tosses: " );
		System.out.println(test1.getTwoTails());
		System.out.print("One-head one-tail tosses: " );
		System.out.println(test1.getHeadTails());
		System.out.print("Tosses add up correctly?: ");
		System.out.println(test1.check());
		System.out.println();
		
		System.out.println("After run(10): ");
		test1.run(10);
		System.out.print("Number of trials: " );
		System.out.println(test1.getNumTrials());
		System.out.print("Two-head tosses: " );
		System.out.println(test1.getTwoHeads());
		System.out.print("Two-tail tosses: " );
		System.out.println(test1.getTwoTails());
		System.out.print("One-head one-tail tosses: " );
		System.out.println(test1.getHeadTails());
		System.out.print("Tosses add up correctly?: ");
		System.out.println(test1.check());
		System.out.println();
		
		System.out.println("After run(100): ");
		test1.run(100);
		System.out.print("Number of trials: " );
		System.out.println(test1.getNumTrials());
		System.out.print("Two-head tosses: " );
		System.out.println(test1.getTwoHeads());
		System.out.print("Two-tail tosses: " );
		System.out.println(test1.getTwoTails());
		System.out.print("One-head one-tail tosses: " );
		System.out.println(test1.getHeadTails());
		System.out.print("Tosses add up correctly?: ");
		System.out.println(test1.check());
		System.out.println();
		
		System.out.println("After reset:");
		test1.reset();
		System.out.print("Number of trials: " );
		System.out.println(test1.getNumTrials());
		//test1.run(0);
		System.out.print("Two-head tosses: " );
		System.out.println(test1.getTwoHeads());
		System.out.print("Two-tail tosses: " );
		System.out.println(test1.getTwoTails());
		System.out.print("One-head one-tail tosses: " );
		System.out.println(test1.getHeadTails());
		System.out.print("Tosses add up correctly?: ");
		System.out.println(test1.check());
		System.out.println();
		
		System.out.println("After run(1000): ");
		test1.run(1000);
		System.out.print("Number of trials: " );
		System.out.println(test1.getNumTrials());
		System.out.print("Two-head tosses: " );
		System.out.println(test1.getTwoHeads());
		System.out.print("Two-tail tosses: " );
		System.out.println(test1.getTwoTails());
		System.out.print("One-head one-tail tosses: " );
		System.out.println(test1.getHeadTails());
		System.out.print("Tosses add up correctly?: ");
		System.out.println(test1.check());
		System.out.println();
		
		
		
		
	}

}
