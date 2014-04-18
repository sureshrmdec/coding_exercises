import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class GamePartition {
  public static void main(String[] args) {
    Collection<Player> players = Arrays.asList(new Player("John", 10), new Player("David", 15), new Player("Matt", 20), new Player("Dan", 30), new Player("Erica", 5));
    Map<Boolean, List<Player>> playersByAge = players.stream().collect(groupingBy(player -> player.age >= 18));
    System.out.println(playersByAge);
  }
}


