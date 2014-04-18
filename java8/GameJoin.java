import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class GameJoin {
  public static void main(String[] args) {
    Stream<Player> players = Stream.of(new Player("John", 10), new Player("David", 15), new Player("Matt", 20), new Player("Dan", 30), new Player("Erica", 5));
    System.out.println(players.map(Player::toString).collect(joining(",", "[", "]")));
  }
}


