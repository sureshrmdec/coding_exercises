import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import java.util.Optional;


public class OptionalExample {
  private static Map<String, Player> players = new HashMap<String, Player>() {{
    put("John", new Player("John", 10));
    put("David", new Player("David", 15));
    put("Matt", new Player("Matt", 20));
    put("Erica", new Player("Erica", 25));
  }};

  private static Optional<Player> findPlayerByName(String name) {
    Player player = players.get(name);
    return player == null ? Optional.empty() : Optional.of(player);
  }

  private static Optional<Integer> getAge2(Optional<Player> player) {
    return player.isPresent() ? Optional.of(player.get().age) : Optional.empty();
  }

  private static Integer getAge1(Player player) {
    return player.age;
  }


  public static void main(String[] args) {
    findPlayerByName("John").ifPresent(System.out::println);
    Player player = findPlayerByName("Jeff").orElse(new Player("Jeff", 40));
    System.out.println("orElse " + player);
    Integer age = findPlayerByName("Jeff").map(OptionalExample::getAge1).orElse(-1);
    System.out.println("Jeff age " + age);
  }
}

