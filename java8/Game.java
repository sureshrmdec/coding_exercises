import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntPredicate;


public class Game implements IntPredicate {
  enum Type {
    AGE_ALL,
    AGE_13_OR_ABOVE,
    AGE_18_OR_ABOVE
  }

  public final String name;
  public final Type type;
  public final Collection<Player> players = new ArrayList<>();


  public Game(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  public boolean suitableForAll() {
    return type == Type.AGE_ALL;
  }
  public void add(Player player) {
    if (test(player.age)) {
      this.players.add(player);
    }
  }

  @Override
  public boolean test(int age) {
    switch (type) {
      case AGE_18_OR_ABOVE:
        return age >= 18;
      case AGE_13_OR_ABOVE:
        return age >= 13;
      default:
        return true;
    }
  }
  @Override 
  public String toString() {
    return name;
  }
}

