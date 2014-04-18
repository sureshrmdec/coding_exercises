interface Vehicle {
  float getMaxSpeedMPH();
  public static String getType(Vehicle v) {
    return v.getClass().getSimpleName();
  }
}

interface Car extends Vehicle {
  void drive();
  public default float getMaxSpeedMPH() {
    return 200;
  }
}
interface Boat extends Vehicle {
  void row();
  public default float getMaxSpeedMPH() {
    return 100;
  }
}

interface Plane extends Vehicle {
  void fly();
  public default float getMaxSpeedMPH() {
    return 500;
  }
}

public class AmphiFlyCar implements Car, Boat, Plane {
  @Override
  public void drive() {
    System.out.println("drive");
  }
  @Override
  public void row() {
    System.out.println("row");
  }
  @Override
  public void fly() {
    System.out.println("fly");
  }
  public float getMaxSpeedMPH() {
    return Plane.super.getMaxSpeedMPH();
  }
  public static void main(String[] args) {
    AmphiFlyCar v = new AmphiFlyCar();
    System.out.println(Vehicle.getType(v) + ": " + v.getMaxSpeedMPH());
  }
}


