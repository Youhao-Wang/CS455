/* *
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;
public class CoinTossSimulator {

  private static  int num_trials;
  private  static int num_twoheads;
  private  static int num_twotails;
  private static  int num_headtails;
  

   /* *
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      num_trials =0;
      num_headtails = 0;
      num_trials =0;
      num_twoheads = 0;

   }


   /* *
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 0
    */
   public void run(int numTrials) {
      num_trials = num_trials + numTrials;
      Random generator1 = new Random();
      Random generator2 = new Random();
      num_twotails = 0;
      num_headtails = 0;
      num_twoheads = 0;
      for (int i =0; i< num_trials; i++)
      {
        int coin1 =  generator1.nextInt(2);
        int coin2 =  generator2.nextInt(2);
        int coin_total = coin1 + coin2;
        switch (coin_total )
        {
          case 0 : num_twotails++; break;
          case 1 : num_headtails++; break;
          case 2 : num_twoheads++; break;

        }

      }

 
   }


   /* *
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return num_trials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /* *
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return num_twoheads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /* *
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return num_twotails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /* *
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return num_headtails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /* *
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      num_trials =0;
      num_headtails = 0;
      num_twotails =0;
      num_twoheads = 0;
   }
      
      public boolean check(){
    	  return (num_trials == (num_headtails + num_twotails + num_twoheads ));
      }
      

  

}
