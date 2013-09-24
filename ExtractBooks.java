/*

Challenge: Extract book titles

In the previous challenge we have implemented context pruning and obtained high-precision contexts. These contexts have a high probability of identifying book titles.

Having a high-precision context

read -TITLE- on

and a tweet

“I’ve just read Design Patterns: Elements of Reusable Object-Oriented Software on my new Kindle!”

we can induce that Design Patterns: Elements of Reusable Object-Oriented Software is the name of a book because it is surrounded by the left side and the right side of a high-precision context.

Given a list of contexts sorted in decreasing order by their precision and a list of tweets

Your task is to

    * write a function that prints to the standard output (stdout) all book titles extracted from the tweets using the given contexts
    * please print one unique book title per line sorted in lexicographical order (alphabet order)
    * if a tweet matches multiple contexts please use only the one with the highest precision to extract the book title
    * If the same context matches a tweet multiple times, please pick the first match from left to right

Note that your function will receive the following arguments:

    * contexts
          o which is an array of strings giving a list of contexts
          o each context is a string of tokens containing the keyword "-TITLE-" (separated by spaces) which delimits the left side of the context and the right side of it
    * tweets
          o which is an array of strings representing tweets

Data constraints

    * the length of the contexts array will not exceed 1000
    * the length of a context string will not exceed 1000 characters
    * the length of the tweets array will not exceed 1000
    * the length of a tweet string will not exceed 140 characters
    * all string comparisons are case-sensitive

Efficiency constraints

    * your function is expected to print the requested result and return in less than 2 seconds

  



contexts: "read -TITLE- on Kindle", "read -TITLE- ", "-TITLE- is a good reading"

tweets: 
"I've just read Beautiful Code", 
"Yay! I've read The Old Man and the Sea on Kindle", 
"Thinking, Fast and Slow is a good reading"
 
*/


import java.util.*;
import java.util.regex.*;

class MyClass {
   public static void extract_books(String[] contexts, String[] tweets) {
      Pattern titleP = Pattern.compile("\\s?.TITLE.\\s?");
      String[][] contextParts = new String[contexts.length][];
      for (int i=0; i<contexts.length; i++) {
         Matcher m = titleP.matcher(contexts[i]);
         if (m.find()) {
            contextParts[i] = new String[] {contexts[i].substring(0, m.start()), contexts[i].substring(m.end())};
         }
      }
      List<String> result = new ArrayList<String>();
      for (String tweet : tweets) {
         for (int i=0; i<contexts.length; i++) {
            boolean matched = true;
            String book = new String(tweet);
            if (contextParts[i][0].length() > 0) {
               Pattern p = Pattern.compile("\\b" + contextParts[i][0] + "\\b");
               Matcher m = p.matcher(book);
               if (m.find()) {
                  book = book.substring(m.end());
               } else {                  
                  matched = false;
               }
            }
            if (contextParts[i][1].length() > 0) {
               Pattern p = Pattern.compile("\\b" + contextParts[i][1] + "\\b");
               Matcher m = p.matcher(book);
               if (m.find()) {
                  String nbook = book;
                  book = book.substring(0, m.start());
               } else {
                  matched = false;
               }
            }
            book = book.trim();
            if (matched && book.length() > 0) {
               if (result.indexOf(book) == -1) {
                  result.add(book);
               }
               break;
            }
         }
      }
      Collections.sort(result);
      for (String book : result) {
         System.out.println(book);
      }
   }
   public static void main(String[] args) {
      String[] contexts = new String[] {"on the smooth -TITLE- planks","sheet -TITLE- to the dark","It's -TITLE- easy to tell the","leg -TITLE- is a"," -TITLE- is","of -TITLE- lemons"," -TITLE- thrown","chopped corn -TITLE- and","work faced us -TITLE- ","A -TITLE- large size"};
      String[] tweets  = new String[]  {"The birch canoe slid on the smooth planks","The birch canoe slid on the smooth After Many a Summer Dies the Swan planks","Glue the sheet to the dark blue background","Glue the sheet Ah, Wilderness! to the dark blue background","It's easy to tell the depth of a well","It's All Passion Spent easy to tell the depth of a well","These days a chicken leg is a rare dish","These days a chicken leg All the King's Men is a rare dish","Rice is often served in round bowls","Rice Alone on a Wide, Wide Sea is often served in round bowls","The juice of lemons makes fine punch","The juice of An Acceptable Time lemons makes fine punch","The box was thrown beside the parked truck","The box was Antic Hay thrown beside the parked truck","The hogs were fed chopped corn and garbage","The hogs were fed chopped corn An Evil Cradling and garbage","Four hours of steady work faced us","Four hours of steady work faced us Arms and the Man","A large size in stockings is hard to sell","A As I Lay Dying large size in stockings is hard to sell","The boy was there when the sun rose","A rod is used to catch pink salmon","The source of the huge river is the clear spring","Kick the ball straight and follow through","Help the woman get back to her feet","A pot of tea helps to pass the evening","Smoky fires lack flame and heat","The soft cushion broke the man's fall","The salt breeze came across from the sea","The girl at the booth sold fifty bonds","The small pup gnawed a hole in the sock","The fish twisted and turned on the bent hook","Press the pants and sew a button on the vest","The swan dive was far short of perfect","The beauty of the view stunned the young boy","Two blue fish swam in the tank","Her purse was full of useless trash","The colt reared and threw the tall rider","It snowed, rained, and hailed the same morning","Read verse out loud for pleasure","Hoist the load to your left shoulder","Take the winding path to reach the lake","Note closely the size of the gas tank","Wipe the grease off his dirty face","Mend the coat before you go out","The wrist was badly strained and hung limp","The stray cat gave birth to kittens","The young girl gave no clear response","The meal was cooked before the bell rang","What joy there is in living","A king ruled the state in the early days","The ship was torn apart on the sharp reef","Sickness kept him home the third week","The wide road shimmered in the hot sun","The lazy cow lay in the cool grass","Lift the square stone over the fence","The rope will bind the seven books at once","Hop over the fence and plunge in","The friendly gang left the drug store","Mesh wire keeps chicks inside","The frosty air passed through the coat","The crooked maze failed to fool the mouse","Adding fast leads to wrong sums","The show was a flop from the very start","A saw is a tool used for making boards","The wagon moved on well oiled wheels","March the soldiers past the next hill","A cup of sugar makes sweet fudge","Place a rosebush near the porch steps","Both lost their lives in the raging storm","We talked of the side show in the circus","Use a pencil to write the first draft","He ran half way to the hardware store","The clock struck to mark the third period","A small creek cut across the field","Cars and busses stalled in snow drifts","The set of china hit the floor with a crash","This is a grand season for hikes on the road","The dune rose from the edge of the water","Those words were the cue for the actor to leave","A yacht slid around the point into the bay","The two met while playing on the sand","The ink stain dried on the finished page","The walled town was seized without a fight","The lease ran out in sixteen weeks","A tame squirrel makes a nice pet","The horn of the car woke the sleeping cop","The heart beat strongly and with firm strokes","The pearl was worn in a thin silver ring","The fruit peel was cut in thick slices","The Navy attacked the big task force","See the cat glaring at the scared mouse","There are more than two factors here","The hat brim was wide and too droopy","The lawyer tried to lose his case","The grass curled around the fence post","Cut the pie into large parts","Men strive but seldom get rich","Always close the barn door tight","He lay prone and hardly moved a limb","The slush lay deep along the street","A wisp of cloud hung in the blue air","A pound of sugar costs more than eggs","The fin was sharp and cut the clear water","The play seems dull and quite stupid","Bail the boat to stop it from sinking","The term ended in late june that year","A Tusk is used to make costly gifts","Ten pins were set in order","The bill was paid every third week"};
      extract_books(contexts, tweets);
   } 
}

