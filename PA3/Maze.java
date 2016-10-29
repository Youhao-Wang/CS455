// Name:  Youhao Wang
// USC loginid: youhaowa
// CS 455 PA3
// Fall 2016

import java.util.LinkedList;


/* *
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
  private boolean[][] mazeMatrix;
  private MazeCoord start;
  private MazeCoord end;
  private LinkedList<MazeCoord> path;

   /* *
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc)
   {
      mazeMatrix = mazeData;
      start = startLoc;
      end = endLoc;
   }


   /* *
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return mazeMatrix.length;   // DUMMY CODE TO GET IT TO COMPILE
   }

   
   /* *
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return mazeMatrix[0].length;   // DUMMY CODE TO GET IT TO COMPILE
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      int row = loc.getRow();
      int col = loc.getCol();
      if(mazeMatrix[row][col] == true ){
        return true;
      }
      else{
        return false; 
      }
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return start;   // DUMMY CODE TO GET IT TO COMPILE
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return end;   // DUMMY CODE TO GET IT TO COMPILE
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {
	   
      return path;   

   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  {  
      path = new LinkedList<MazeCoord>();
      if(this.hasWallAt(start) || this.hasWallAt(end))
          return false;
      boolean[][] visited = new boolean[this.numRows()][this.numCols()];
          return helper(visited, start, end);
   }


   /**
      Write the help functions and use the backtrack to find the valid path.
      @param visited:  the boolean matrix that record whether a cube has been visited
      @param current:  record the current cube we backtrack  
      @param end:  the end cube
      @return whether path was found.
    */
   private boolean helper(boolean[][] visited, MazeCoord current, MazeCoord end){
      int currentRow = current.getRow();
      int currentCol = current.getCol();
      if(visited[currentRow][currentCol] == true)
        return false;
      visited[currentRow][currentCol] = true;
      if(this.hasWallAt(current))
        return false;
      if(current.equals(end)){
        path.add(current);
        return true;
      }

      path.add(current);
      MazeCoord left = new MazeCoord(currentRow, currentCol-1);
      MazeCoord right = new MazeCoord(currentRow, currentCol+1);
      MazeCoord up = new MazeCoord(currentRow-1, currentCol);
      MazeCoord down = new MazeCoord(currentRow+1, currentCol);
      if(isVaild(left) && helper(visited, left, end) == true)
          return true;
      if(isVaild(right) && helper(visited, right, end) == true)
          return true;
      if(isVaild(up) && helper(visited, up, end) == true)
          return true;
      if(isVaild(down) && helper(visited, down, end) == true)
          return true;

      path.removeLast();
      return false;
   }

   
   /**
   Determine whether a cube is valid?
   @return whether the cube is valid.
 */
   private boolean isVaild(MazeCoord current){
      int currentRow = current.getRow();
      int currentCol = current.getCol();
      if( currentRow < 0 || currentRow >= this.numRows())
        return false;
      if ( currentCol < 0 || currentCol >= this.numCols())
        return false;

      return true;
   }
   

}
