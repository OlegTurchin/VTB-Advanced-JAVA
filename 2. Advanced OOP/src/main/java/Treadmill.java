public class Treadmill implements Obstacle{
    int length = (int) (Math.random() * 1500) + 1;

    @Override
    public int getValue() {
//        System.out.println("Inside of Treadmill initialization");
        return length;
    }
}
