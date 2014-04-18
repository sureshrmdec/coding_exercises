import java.util.Arrays;
import java.util.Collection;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class GameMap {
  public static void main(String[] args) {
    Collection<Game> games = Arrays.asList(new Game("Birdie", Game.Type.AGE_ALL), new Game("Draw", Game.Type.AGE_ALL), new Game("Poker", Game.Type.AGE_18_OR_ABOVE), new Game("Torpedo", Game.Type.AGE_13_OR_ABOVE));
    Collection<Player> players = Arrays.asList(new Player("John", 10), new Player("David", 15), new Player("Matt", 20), new Player("Dan", 30), new Player("Erica", 5));
    for (Game game : games) {
      for (Player player : players) {
        game.add(player);
      }
    }
    //
    Collection<Game.Type> types = games.stream().map(game -> game.type).collect(toList());
    System.out.println("types:");
    types.stream().forEach(System.out::println);
    Collection<Player> allPlayers = games.stream().flatMap(game -> game.players.stream()).collect(toList());
    System.out.println("\nplayers:");
    players.stream().forEach(System.out::println);
  }
}


