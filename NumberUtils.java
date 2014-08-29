/**
 * Given a String, write a routine that converts the string to an long, 
 * without using the built in Java functions that would do this. Describe 
 * what (if any) limitations the code has. 
 * 
 * Testing instructions:
 *  javac NumberUtils.java
 *  java -cp . NumberUtils
 *
 *
 * @author Shahzad Bhatti
 */

// custom exception for throwing overflow errors as default behavior in java is to ignore overflow 
// declared this class as packaged (non-public) because Java can only have one public class per file 
// and I didn't want to add multiple files for the problem.
class NumberOverflowException extends NumberFormatException {
   NumberOverflowException(String m) {
      super(m);
   }
}
class NumberUnderflowException extends NumberFormatException {
   NumberUnderflowException(String m) {
      super(m);
   }
}


public class NumberUtils {
   /**
    * This method converts a stringified integer number to long value 
    * Limitations:
    *   As long types in java is 64-bit, and it can store values from -2^63 to 2^63 - 1.
    *   This means if string contains number outside this range, this method would throw exception 
    *   as opposed to silently overflowing or underflowing it, which is default behavior in Java (C/C++).
    *   For arbitrary long numbers, you would need something like java.math.BigInteger class in Java.
    *
    * @param s - input string 
    * @return - long value equivalent of string numbers 
    * @throws - IllegalArgumentException if input string is null
    *         - NumberFormatException if input string does not contain valid numeric digits 
    *         - NumberOverflowException if input string contains a positive value that cannot fit into long type
    *         - NumberUnderflowException if input string contains a negative value that cannot fit into long type 
    */
   public long StringToLong(String s) {
      // validation 
      // we need to define what to do when string is empty or null in our specification but
      // I am throwing IllegalArgumentException when string is null and NumberFormatException if string 
      // is empty or does not have valid numbers.
      if (s == null) {
         throw new IllegalArgumentException("string not specified!");
      }
      if (s .length() == 0) {
         throw new NumberFormatException("Empty string is not allowed!");
      }

      // I am not removing any spaces at beginning or end of string so any white characters will be considered
      // invalid
      boolean isNegativeNumber = false;
      long result = 0;
      int len = s.length();
      int i=0;
      char nextNumberChar = s.charAt(i);
      if (nextNumberChar == '+') {
         i++;
      } else if (nextNumberChar == '-') {
         isNegativeNumber = true;
         i++;
      }

      // go through each character in the string
      for (; i<len; i++) {
         nextNumberChar = s.charAt(i);
         // validate numeric characters 
         if (nextNumberChar < '0' || nextNumberChar > '9') {
            throw new NumberFormatException("Character " + nextNumberChar + " in " + s + " is not a valid digit!");
         }
         int digit = (nextNumberChar - '0');
         if (result != 0 && result > Long.MAX_VALUE / 10) {
            throw new NumberOverflowException("Number " + s + " overflowed for long type");
         }
         if (result != 0 && result < Long.MIN_VALUE / 10) {
            throw new NumberUnderflowException("Number " + s + " underflowed for long type");
         }
         // multiply previous result by 10 and then add numeric digit to the result 
         result = result * 10 + digit;
      }

      if (isNegativeNumber) {
         long prevResult = result;
         result = -result;
         if (result > prevResult) {
            throw new NumberUnderflowException("Number " + s + " underflowed for long type");
         }
      }
      return result;
   }

   // optionally, we can define a method that is more forgiving for user-input, e.g.
   // we can remove non-digit characters from our input.
   /**
    * This method converts a stringified integer number to long value, but it removes 
    *    non-numeric characters from input before conversion
    * @param s - input string 
    * @return - long value equivalent of string numbers, if input is null or empty, then it returns 0
    * @throws - NumberFormatException if input string does not contain valid numeric digits 
    *         - NumberOverflowException if input string contains a positive value that cannot fit into long type
    *         - NumberUnderflowException if input string contains a negative value that cannot fit into long type 
    */
   public long RelaxedStringToLong(String s) {
      if (s == null) {
         return 0;
      }
      s = s.replaceAll("[^-\\d]", ""); // replace anything that is not negative sign or numeric digit with empty string
      if (s.length() == 0) {
         return 0;
      }
      return StringToLong(s);
   }


   // Though tests would be defined using unit testing framework but here are
   // examples of a few tests.
   public void Test() { 
      // happy path
      assertEquals(123L, StringToLong("0123")); 
      assertEquals(123L, StringToLong("123")); 
      assertEquals(-123L, StringToLong("-123")); 
      assertEquals(Integer.MAX_VALUE, StringToLong(String.valueOf(Integer.MAX_VALUE))); 
      assertEquals(Integer.MIN_VALUE, StringToLong(String.valueOf(Integer.MIN_VALUE))); 
      assertEquals(Long.MAX_VALUE, StringToLong(String.valueOf(Long.MAX_VALUE))); 
      assertEquals(Long.MIN_VALUE, StringToLong(String.valueOf(Long.MIN_VALUE))); 
      assertEquals((long) Double.MAX_VALUE, StringToLong(String.valueOf((long)Double.MAX_VALUE))); 
      assertEquals((long) Double.MIN_VALUE, StringToLong(String.valueOf((long)Double.MIN_VALUE))); 
   } 


   public void TestOverflow() { 
      try {
         StringToLong(String.valueOf(Long.MAX_VALUE) + "1");
         throw new Error("Should have thrown overflow");
      } catch (NumberOverflowException e) {
      }
   }


   public void TestUnderflow() { 
      try {
         StringToLong(String.valueOf(Long.MIN_VALUE) + "1");
         throw new Error("Should have thrown underflow");
      } catch (NumberUnderflowException e) {
      }
   }

   public void TestInvalidCharacters() { 
      try {
         StringToLong("++123"); 
         throw new Error("Should have thrown NumberFormatException");
      } catch (NumberFormatException e) {
      }
      try {
         StringToLong("+-123"); 
         throw new Error("Should have thrown NumberFormatException");
      } catch (NumberFormatException e) {
      }
      try {
         StringToLong(null); 
         throw new Error("Should have thrown IllegalArgumentException");
      } catch (IllegalArgumentException e) {
      }
      try {
         StringToLong(" "); 
         throw new Error("Should have thrown NumberFormatException");
      } catch (NumberFormatException e) {
      }
      try {
         StringToLong(""); 
         throw new Error("Should have thrown NumberFormatException");
      } catch (NumberFormatException e) {
      }
      try {
         StringToLong("o123"); 
         throw new Error("Should have thrown NumberFormatException");
      } catch (NumberFormatException e) {
      }
   }

   public void TestInvalidCharactersWithRelaxedConversion() { 
      assertEquals(123L, RelaxedStringToLong("oooxxxx123")); 
      assertEquals(123L, RelaxedStringToLong("++0123")); 
      assertEquals(-123L, RelaxedStringToLong("+-123")); 
      assertEquals(0, RelaxedStringToLong(null)); 
      assertEquals(0, RelaxedStringToLong("")); 
      assertEquals(0, RelaxedStringToLong(" ")); 
   } 


   private static void assertEquals(long expected, long actual) {
      if (expected != actual) {
         throw new Error("Expected " + expected + ", but was " + actual);
      }
   }

   public static void main(String[] args) {
      NumberUtils numberUtils = new NumberUtils();
      numberUtils.Test();
      numberUtils.TestOverflow();
      numberUtils.TestUnderflow();
      numberUtils.TestInvalidCharacters();
      numberUtils.TestInvalidCharactersWithRelaxedConversion();
   }
}
