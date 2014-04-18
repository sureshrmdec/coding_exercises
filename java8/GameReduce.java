import java.util.Arrays;
import java.util.Collection;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class GameReduce {
  public static void main(String[] args) {
    Collection<Player> players = Arrays.asList(new Player("John", 10), new Player("David", 15), new Player("Matt", 20), new Player("Dan", 30), new Player("Erica", 5));
    double averageAge1 = players.stream().mapToInt(player -> player.age).average().getAsDouble();
    double averageAge2 = players.stream().mapToInt(player -> player.age).reduce(0, Integer::sum) / players.size();
    double averageAge3 = players.stream().mapToInt(player -> player.age).reduce(0, (sum, age) -> sum + age) / players.size();
    System.out.println("average age " + averageAge1 + ", " + averageAge2 + ", or " + averageAge3);
  }
}


