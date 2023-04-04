public class Wall implements Obstacle{
    int height = (int) (Math.random() * 100) + 1;

    @Override
    public int getValue() {
//        System.out.println("Inside of Wall initialization");
        return height;
    }
}
