public class Shuffle {
   public static int random(int lower, int higher) {
      return lower + (int) (Math.random() * (higher-lower+1));
   }
   public static void shuffle(int[] cards) {
      for (int i = 0; i < cards.length; i++) {
         int ndx = random(i, cards.length-1);
         //System.out.println("Picked " + ndx + " between " + i + " " + (cards.length-1));
         int temp = cards[i];
         cards[i] = cards[ndx];
         cards[ndx] = temp;
      }
   }

   public static void main(String[] args) {
      int[] cards = new int[20];
      for (int i=0; i<cards.length; i++) {
         cards[i] = i;
      }
      shuffle(cards);
      for (int i=0; i<cards.length; i++) {
         System.out.println(i + " = " + cards[i]);
      }
   }
}

