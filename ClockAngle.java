import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class ClockAngle {
   // hour 0-11, minute 0-59, angle 0-179
   public static int angle(int hour, int minute) {
      hour = Math.abs(hour);
      minute = Math.abs(minute);
      return (hour * 30 - minute * 6) % 180;
   }

   private static void Test() { 
      assertEquals(0, angle(12, 0));
      assertEquals(60, angle(3, 5));
      assertEquals(0, angle(6, 0));
   } 

   private static void assertEquals(long expected, long actual) {
      if (expected != actual) {
         throw new Error("Expected " + expected + ", but was " + actual);
      }
   }
   public static void main(String[] args) {
      Test();
   }
}

