import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import java.util.Comparator;


public class GameSort {
  public static void main(String[] args) {
    List<Player> players = Arrays.asList(new Player("John", 10), new Player("David", 15), new Player("Matt", 20), new Player("Dan", 30), new Player("Erica", 5));
    players.sort(Comparator.comparing(player -> player.age));
    System.out.println(players);
  }
}



