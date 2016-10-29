import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class MazeTester {
   
   private static final char WALL_CHAR = '1';
   private static final char FREE_CHAR = '0';

   public static void main(String[] args)  {

      String fileName = "";

      try {

         if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
         }
         else {
            fileName = args[0];     
            Maze maze = readMazeFile(fileName);
            LinkedList<MazeCoord> path = new LinkedList<MazeCoord>();
            maze.search();
            path = maze.getPath();
            
            if(path == null){
            		System.out.println("No path out of maze.");
            		return;
            }
            		
            ListIterator<MazeCoord> iter = path.listIterator();
            while(iter.hasNext()){
            	System.out.println(iter.next().toString()+"  ");
            }

         }

      }
      catch (FileNotFoundException exc) {
         System.out.println("File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
   }

   /* *
    readMazeFile reads in maze from the file whose name is given and 
    returns a MazeFrame created from it.
   
   @param fileName
             the name of a file to read from (file format shown in class comments, above)
   @returns a MazeFrame containing the data from the file.
        
   @throws FileNotFoundException
              if there's no such file (subclass of IOException)
   @throws IOException
              (hook given in case you want to do more error-checking.
               that would also involve changing main to catch other exceptions)
   */
   private static Maze readMazeFile(String fileName) throws IOException {

        File infile = new File(fileName);
        Scanner in = new Scanner(infile);
       try{
            readData(in);
            return mazeData;
       }
       finally{
        in.close();
       }

   }

   /* *
      Reads all data.
      @param in the scanner that scans the data
   */

  private static void readData (Scanner in){
      int startRow = 0, startCol = 0;
      int endRow = 0, endCol = 0;

      int row = in.nextInt();
      int col = in.nextInt();
      boolean [][] mazeMatrix = new boolean[row][col];
      for(int i = 0; i < row ; i ++){
        String str = in.next();
        for(int j = 0; j < col; j ++){
        	char value = str.charAt(j);
          if(value == FREE_CHAR)
            mazeMatrix[i][j] = false;
          else if( value == WALL_CHAR)
            mazeMatrix[i][j] = true;
        }
      } 

      startRow = in.nextInt();
      startCol = in.nextInt();
      endRow = in.nextInt();
      endCol = in.nextInt();

      MazeCoord start = new MazeCoord(startRow,startCol);
      MazeCoord end = new MazeCoord(endRow,endCol);
      mazeData = new Maze(mazeMatrix, start, end);

   }

  private static Maze mazeData;
}
