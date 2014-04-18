import java.util.Arrays;
import java.util.Collection;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import java.util.Comparator;


public class GameMinMax {
  public static void main(String[] args) {
    Collection<Player> players = Arrays.asList(new Player("John", 10), new Player("David", 15), new Player("Matt", 20), new Player("Dan", 30), new Player("Erica", 5));
    Player min = players.stream().min(Comparator.comparing(player -> player.age)).get();
    Player max = players.stream().max(Comparator.comparing(player -> player.age)).get();
    System.out.println("min " + min + ", max " + max);
  }
}


