import java.util.function.*;

public class Predicate8 {
  public static void main(String[] args) {
    Predicate<Integer> gradeA = score -> score >= 90;
    System.out.println(gradeA.test(80));
    System.out.println(gradeA.test(90));
  }
}
