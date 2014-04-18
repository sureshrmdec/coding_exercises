import java.util.Arrays;
import java.util.Collection;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class GameFilter {
  public static void main(String[] args) {
    Collection<Game> games = Arrays.asList(new Game("Birdie", Game.Type.AGE_ALL), new Game("Draw", Game.Type.AGE_ALL), new Game("Poker", Game.Type.AGE_18_OR_ABOVE), new Game("Torpedo", Game.Type.AGE_13_OR_ABOVE));
    Collection<Game> suitableForAll = games.stream().filter(Game::suitableForAll).collect(toList());
    System.out.println("suitable for all");
    suitableForAll.stream().forEach(System.out::println);
    Collection<Game> adultOnly = games.stream().filter(game -> game.type == Game.Type.AGE_18_OR_ABOVE).limit(10).collect(toList());
    System.out.println("suitable for adults only");
    adultOnly.stream().forEach(System.out::println);
  }
}


