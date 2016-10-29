// Name:  Youhao Wang
// USC loginid: youhaowa
// CS 455 PA3
// Fall 2016

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.awt.geom.Line2D;
import java.util.ListIterator;
import java.util.Scanner;
/* *
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   private int startRow ,startCol, endRow , endCol ;
   private MazeCoord start,end;

   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
   private static final int HALF = 10;
                    // how much smaller on each side to make entry/exit inner box
   
   
   /* *
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
      this.maze = maze;
      start = maze.getEntryLoc();
      end = maze.getExitLoc();

      startRow = start.getRow();
      startCol = start.getCol();
      endRow = end.getRow();
      endCol = end.getCol();
   }

   
   /* *
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;

      int rows = maze.numRows();
      int cols = maze.numCols();
      int height = BOX_HEIGHT * rows;
      int width = BOX_WIDTH * cols;
      
      for(int i = 0; i <rows; i++){
        for(int j = 0; j < cols; j++){
            MazeCoord loc = new MazeCoord(i, j);
            Rectangle cube = new Rectangle(START_X + j * BOX_WIDTH, 
              START_Y + i * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);

            if(maze.hasWallAt(loc)){
              g2.setColor(Color.BLACK);
            }
            else{
              g2.setColor(Color.WHITE);
            }
            g2.fill(cube);

            if(loc.equals(start)){
              Rectangle innerStart = new Rectangle(START_X + j * BOX_WIDTH + INSET, 
                START_Y + i * BOX_HEIGHT + INSET, BOX_WIDTH - 2*INSET, BOX_HEIGHT - 2*INSET);
              g2.setColor(Color.YELLOW);
              g2.fill(innerStart);
            }

            if(loc.equals(end)){
              Rectangle innerEnd = new Rectangle(START_X + j * BOX_WIDTH + INSET, 
                START_Y + i * BOX_HEIGHT + INSET, BOX_WIDTH - 2*INSET, BOX_HEIGHT - 2*INSET);
              g2.setColor(Color.GREEN);
              g2.fill(innerEnd);
            }
        }
      }
      g2.setColor(Color.BLACK);
      Rectangle total = new Rectangle(START_X,START_Y, width, height);
      g2.draw(total);

      // draw the path
      if(maze.getPath() != null)
          paintPath(g2);
   }


   /* *
     Draws the path of maze 
     @param g2 the graphics 2D
   */
   private void paintPath(Graphics2D g2){
        g2.setColor(Color.BLUE);
        Line2D.Double line = new Line2D.Double();
        LinkedList<MazeCoord> path = maze.getPath();
        ListIterator<MazeCoord> iter = path.listIterator();
        
        if(!iter.hasNext())
        		return;
        MazeCoord pre = iter.next();    //the start
        while(iter.hasNext()){
           MazeCoord next = iter.next();
           int preRow = pre.getRow();
           int preCol = pre.getCol();

           if(next.getRow() - pre.getRow() == 1){  //down
              line = new Line2D.Double(START_X + preCol * BOX_WIDTH + HALF, 
                START_Y + preRow * BOX_WIDTH + HALF, START_X + preCol * BOX_WIDTH + HALF, START_Y + (preRow+1) * BOX_WIDTH + HALF);
           } 

           else if(next.getRow() - pre.getRow() == -1){ //up
               line = new Line2D.Double(START_X + preCol * BOX_WIDTH + HALF, 
                START_Y + preRow * BOX_WIDTH + HALF, START_X + preCol * BOX_WIDTH + HALF, START_Y + (preRow-1) * BOX_WIDTH + HALF);
           }
           else if(next.getCol() - pre.getCol() == 1){ //right
               line = new Line2D.Double(START_X + preCol * BOX_WIDTH + HALF, 
                START_Y + preRow * BOX_WIDTH + HALF, START_X + (preCol+1) * BOX_WIDTH + HALF, START_Y + preRow * BOX_WIDTH + HALF);
           }
           else if(next.getCol() - pre.getCol() == -1){ //left
                line = new Line2D.Double(START_X + preCol * BOX_WIDTH + HALF, 
                START_Y + preRow * BOX_WIDTH + HALF, START_X + (preCol-1) * BOX_WIDTH + HALF, START_Y + preRow * BOX_WIDTH + HALF);
           }
           g2.draw(line);
           pre = next;
        }
      }
}



