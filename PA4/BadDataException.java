// Name:Youhao Wang
// USC loginid:youhaowa
// CS 455 PA4
// Fall 2016

import java.io.IOException;

/**
   This class reports bad input argument.
*/
public class BadDataException extends IOException
{
   public BadDataException() {}
   public BadDataException(String message)
   {
      super(message);
   }
}
