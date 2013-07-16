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
               int ndx = book.indexOf(contextParts[i][1]);
               if (m.find()) {
                  book = book.substring(0, m.start());
               } else {                     
                  matched = false;
               }
            }
            book = book.trim();
            if (matched && book.length() > 0 && result.indexOf(book) == -1) {
               System.err.println(i + "context " + contexts[i] + ", tweet " + tweet + ", matched " + book  + ", first '" + contextParts[i][0] + "', second '" + contextParts[i][1] + "'");
               result.add(book);
               break;
               //System.err.println(i + "DIDN'T match context " + contexts[i] + ", tweet " + tweet + ", first '" + contextParts[i][0] + "', second '" + contextParts[i][1] + "'");
            }
         }
      }
      Collections.sort(result);
      for (String book : result) {
         System.out.println(book);
      }
   }
   public static void main(String[] args) {
      String[] contexts = new String[] { "read  -TITLE- on Kindle","read -TITLE- ","-TITLE- is a good reading"}; //"planks -TITLE- "," -TITLE- to","It's -TITLE- easy","chicken leg -TITLE- is","Rice -TITLE- is","fine -TITLE- punch","beside -TITLE- the parked","and -TITLE- garbage","us -TITLE- ","sell -TITLE- ","The -TITLE- boy was there when","A rod -TITLE- is"," -TITLE- of","and follow through -TITLE- ","woman -TITLE- get","of -TITLE- tea helps","lack -TITLE- flame and heat","broke the man's fall -TITLE- ","breeze -TITLE- came across from the","The girl -TITLE- at","small -TITLE- pup gnawed a","twisted and turned -TITLE- on"," -TITLE- and","swan dive was -TITLE- far"," -TITLE- the","the -TITLE- tank","useless -TITLE- trash"," -TITLE- reared","and hailed -TITLE- the","loud -TITLE- for pleasure","to your left -TITLE- shoulder","winding -TITLE- path to reach the","size of -TITLE- the","grease -TITLE- off his","coat before you -TITLE- go","was -TITLE- badly","cat -TITLE- gave birth to","The young -TITLE- girl","was -TITLE- cooked before","living -TITLE- ","ruled -TITLE- the","reef -TITLE- ","home -TITLE- the third week","The -TITLE- wide road shimmered in","lay -TITLE- in the","stone -TITLE- over","at -TITLE- once","and -TITLE- plunge in","drug -TITLE- store","keeps -TITLE- chicks inside"};
      String[] tweets  = new String[]  {"I've just read Beautiful Code","Yay! I've read The Old Man and the Sea on Kindle","Thinking, Fast and Slow is a good reading"}; //"The birch canoe slid on the smooth planks","The birch canoe slid on the smooth planks After Many a Summer Dies the Swan","Glue the sheet to the dark blue background","Glue the sheet Ah, Wilderness! to the dark blue background","It's easy to tell the depth of a well","It's All Passion Spent easy to tell the depth of a well","These days a chicken leg is a rare dish","These days a chicken leg All the King's Men is a rare dish","Rice is often served in round bowls","Rice Alone on a Wide, Wide Sea is often served in round bowls","The juice of lemons makes fine punch","The juice of lemons makes fine An Acceptable Time punch","The box was thrown beside the parked truck","The box was thrown beside Antic Hay the parked truck","The hogs were fed chopped corn and garbage","The hogs were fed chopped corn and An Evil Cradling garbage","Four hours of steady work faced us","Four hours of steady work faced us Arms and the Man","A large size in stockings is hard to sell","A large size in stockings is hard to sell As I Lay Dying","The boy was there when the sun rose","The A Time to Kill boy was there when the sun rose","A rod is used to catch pink salmon","A rod Behold the Man is used to catch pink salmon","The source of the huge river is the clear spring","The source Beneath the Bleeding of the huge river is the clear spring","Kick the ball straight and follow through","Kick the ball straight and follow through Beyond the Mexique Bay","Help the woman get back to her feet","Help the woman Blithe Spirit get back to her feet","A pot of tea helps to pass the evening","A pot of Blood's a Rover tea helps to pass the evening","Smoky fires lack flame and heat","Smoky fires lack Bonjour Tristesse flame and heat","The soft cushion broke the man's fall","The soft cushion broke the man's fall Brandy of the Damned","The salt breeze came across from the sea","The salt breeze Brave New World came across from the sea","The girl at the booth sold fifty bonds","The girl Bury My Heart at Wounded Knee at the booth sold fifty bonds","The small pup gnawed a hole in the sock","The small Butter In a Lordly Dish pup gnawed a hole in the sock","The fish twisted and turned on the bent hook","The fish twisted and turned Cabbages and Kings on the bent hook","Press the pants and sew a button on the vest","Press the pants Clouds of Witness and sew a button on the vest","The swan dive was far short of perfect","The swan dive was A Confederacy of Dunces far short of perfect","The beauty of the view stunned the young boy","The beauty of Consider Phlebas the view stunned the young boy","Two blue fish swam in the tank","Two blue fish swam in the Consider the Lilies tank","Her purse was full of useless trash","Her purse was full of useless Cover Her Face trash","The colt reared and threw the tall rider","The colt The Cricket on the Hearth reared and threw the tall rider","It snowed, rained, and hailed the same morning","It snowed, rained, and hailed The Curious Incident of the Dog in the Night-Time the same morning","Read verse out loud for pleasure","Read verse out loud The Daffodil Sky for pleasure","Hoist the load to your left shoulder","Hoist the load to your left Dance Dance Dance shoulder","Take the winding path to reach the lake","Take the winding A Darkling Plain path to reach the lake","Note closely the size of the gas tank","Note closely the size of Death Be Not Proud the gas tank","Wipe the grease off his dirty face","Wipe the grease The Doors of Perception off his dirty face","Mend the coat before you go out","Mend the coat before you Down to a Sunless Sea go out","The wrist was badly strained and hung limp","The wrist was Dulce et Decorum Est badly strained and hung limp","The stray cat gave birth to kittens","The stray cat Dying of the Light gave birth to kittens","The young girl gave no clear response","The young East of Eden girl gave no clear response","The meal was cooked before the bell rang","The meal was Ego Dominus Tuus cooked before the bell rang","What joy there is in living","What joy there is in living Endless Night","A king ruled the state in the early days","A king ruled Everything is Illuminated the state in the early days","The ship was torn apart on the sharp reef","The ship was torn apart on the sharp reef Eyeless in Gaza","Sickness kept him home the third week","Sickness kept him home Fair Stood the Wind for France the third week","The wide road shimmered in the hot sun","The Fame Is the Spur wide road shimmered in the hot sun","The lazy cow lay in the cool grass","The lazy cow lay A Fanatic Heart in the cool grass","Lift the square stone over the fence","Lift the square stone The Far-Distant Oxus over the fence","The rope will bind the seven books at once","The rope will bind the seven books at A Farewell to Arms once","Hop over the fence and plunge in","Hop over the fence and Far From the Madding Crowd plunge in","The friendly gang left the drug store","The friendly gang left the drug Fear and Trembling store","Mesh wire keeps chicks inside","Mesh wire keeps For a Breath I Tarry chicks inside","The frosty air passed through the coat","The crooked maze failed to fool the mouse","Adding fast leads to wrong sums","The show was a flop from the very start","A saw is a tool used for making boards","The wagon moved on well oiled wheels","March the soldiers past the next hill","A cup of sugar makes sweet fudge","Place a rosebush near the porch steps","Both lost their lives in the raging storm","We talked of the side show in the circus","Use a pencil to write the first draft","He ran half way to the hardware store","The clock struck to mark the third period","A small creek cut across the field","Cars and busses stalled in snow drifts","The set of china hit the floor with a crash","This is a grand season for hikes on the road","The dune rose from the edge of the water","Those words were the cue for the actor to leave","A yacht slid around the point into the bay","The two met while playing on the sand","The ink stain dried on the finished page","The walled town was seized without a fight","The lease ran out in sixteen weeks","A tame squirrel makes a nice pet","The horn of the car woke the sleeping cop","The heart beat strongly and with firm strokes","The pearl was worn in a thin silver ring","The fruit peel was cut in thick slices","The Navy attacked the big task force","See the cat glaring at the scared mouse","There are more than two factors here","The hat brim was wide and too droopy","The lawyer tried to lose his case","The grass curled around the fence post","Cut the pie into large parts","Men strive but seldom get rich","Always close the barn door tight","He lay prone and hardly moved a limb","The slush lay deep along the street","A wisp of cloud hung in the blue air","A pound of sugar costs more than eggs","The fin was sharp and cut the clear water","The play seems dull and quite stupid","Bail the boat to stop it from sinking","The term ended in late june that year","A Tusk is used to make costly gifts","Ten pins were set in order","The bill was paid every third week"};
      extract_books(contexts, tweets);
   } 
}

