public class Player {
  public final String name;
  public final int age;
  public Player(String name, int age) {
    this.name = name;
    this.age = age;
  }
  @Override 
  public String toString() {
    return name;
  }
}

