// Name: Youhao Wang
// USC loginid:  youhaowa
// CS 455 PA2 
// Fall 2016


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {

    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
      poly = new ArrayList<Term>();
      assert isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
      poly = new ArrayList<Term>();
      poly.add(term);
      assert isValidPolynomial();


    }


/**
       Return the size of the Poly
     */
    private int size()
    {
      return poly.size();
    }

/**
       return the i.th element in the arraylist
     */
    private Term get(int i)
    {
      return poly.get(i);
    }


/**
        add the new term to the ploy arraylist
     */
    private void enlink(Term value)
    {
       poly.add(value);
    }

    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {
	     int len1 = this.size();
         int len2 = b.size();
         Polynomial result = new Polynomial();

        int index1=0;
        int index2=0;
        
        while(index1 < len1 || index2 < len2)
        {
         if(index1 == len1){
        		  int expon2 = b.get(index2).getExpon();
        		  double coeff2 = b.get(index2).getCoeff();
        		  Term temp = new Term(coeff2, expon2);
                  result.enlink(temp);
                  index2 ++;
        	  }
        	  
        else if( index2 == len2 ){
        		  int expon1 = this.get(index1).getExpon();
        		  double coeff1 = this.get(index1).getCoeff();
        		  Term temp = new Term(coeff1, expon1);
                  result.enlink(temp);
                  index1++;
        		  
        	  }
        	  
        else{ 
            int expon1 = this.get(index1).getExpon();
            int expon2 = b.get(index2).getExpon();
            double coeff1 = this.get(index1).getCoeff();
            double coeff2 = b.get(index2).getCoeff();

            if(expon1 == expon2){
              double coeff_new = coeff1 + coeff2;
              if(coeff_new != 0){
                Term temp = new Term(coeff_new ,expon1);
                result.enlink(temp);
              }
              index1 ++;
              index2 ++;
            }

            else if(expon1 > expon2) {
              Term temp = new Term(coeff1, expon1);
              result.enlink(temp);
              index1 ++;
            }

            else{
              Term temp = new Term(coeff2, expon2);
              result.enlink(temp);
              index2 ++;
            }
          }
        }
          assert this.isValidPolynomial();
          assert b.isValidPolynomial();
          assert result.isValidPolynomial();
          return result; 
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
      double result =0;
       int len = this.size();
       for (int i =0; i < len; i++){
          int expon = this.get(i).getExpon();
          double coeff = this.get(i).getCoeff();
          result = result + coeff * Math.pow(x,expon);
       }
       assert this.isValidPolynomial();
       return result;
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
    	String result = "";
    	 int len = this.size();
    	 int flag = 1;

    	 if(len ==0)
    		 return result+= "0.0";
    	 int expon = this.get(0).getExpon();
       double coeff = this.get(0).getCoeff();
       if(coeff ==0)
        flag = 0;
       result = result + everyToString(coeff, expon); 

    	 for (int i =1; i < len; i++){
             expon = this.get(i).getExpon();
             coeff = this.get(i).getCoeff();
             if ( flag != 0)
               result = result + " + ";
             result = result + everyToString(coeff, expon);
             if(coeff ==0)
                 flag = 0;   
          }
    	 assert this.isValidPolynomial();
    	return result;
    }


    /** 
    Return the string for each term. 
    this method is only called by oFormattedString();
    */
    private String everyToString(double coeff, int expon)
    {
      String value = "";
      if( expon == 0 && coeff ==0 ) // null
        value = value;
      else if (expon == 0 && coeff !=0)     //   4
        value = value +  coeff;

      else if (expon == POSITIVE_ONE){
          if ( coeff ==0)     // null
            value = value;
          else if( coeff == POSITIVE_ONE)  //  x
            value = "x"; 
          else if ( coeff == NEGATIVE_ONE)  //-x
            value = "-x";
          else   //12x
            value = coeff + "x";
      }

      else{
          if ( coeff ==0)     // null
            value = value;
          else if( coeff == POSITIVE_ONE)  //  x^3
            value = "x^" + expon; 
          else if ( coeff == NEGATIVE_ONE)  //-x^3
            value = "-x^" + expon;
          else   //12x^3
            value = coeff + "x^" + expon;
      }     
      return value;
    }


    /**
       Returns true iff the poly data is in a valid state.
    */
    private boolean isValidPolynomial() {
     int len = this.size();
	   //int[] expon = new int[len];
     if(len == 0)
        return true;
      else if (len ==1){
        if (this.get(0).getExpon() >= 0)
          return true;
        else 
          return false;
      }
      else{
         for( int i =1; i < len; i++){
           int expon1 = this.get(i-1).getExpon();
          int  expon2 = this.get(i).getExpon();
           if(expon2<0)
              return false;
            if(expon2 >= expon1)
              return false;
         }
      }
     
        return true;
    }


    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)
    private ArrayList<Term> poly;        //the arraylist represent the Polynomial

    private final static int POSITIVE_ONE = 1;
    private final static int NEGATIVE_ONE = -1;
    /**
        Use a arraylist representation:

     Representation invariant:
      -- Poly is the representation of Polynomial.
      -- Exponents in all ploy term must be non-negative.
      -- the terms of poly are ordered from highest power term to lowest.
      -- number of terms stored is poly.size().
      -- if the coefficient of one terms is zero, 
          we don't normally show that as part of the polynomial
      -- besides no zero terms, we won't ever have two terms with the same exponent.
    */


}
