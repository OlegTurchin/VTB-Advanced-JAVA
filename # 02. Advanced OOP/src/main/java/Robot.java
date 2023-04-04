public class Robot implements Runner, Jumper, Participant{

    String name;

    int minRunLength = 1000;
    int maxRunLength = 3000;
    int actualRunLength = (int) (Math.random()*(maxRunLength - minRunLength)) + minRunLength;

    int minJumpHeight = 100;
    int maxJumpHeight = 200;
    int actualJumpHeight = (int) (Math.random()*(maxJumpHeight - minJumpHeight)) + minJumpHeight;

    Robot (String name){
        this.name = name;
    }

    @Override
    public boolean run(int length, Obstacle obstacle) {
        if (obstacle instanceof Treadmill) {
            if (actualRunLength >= length) {
                System.out.println(name + " ran " + length + "m.");
                return true;
            } else {
                System.out.println(name + " failed to run " + length + "m.");
                return false;
            }
        } else return true;
    }

    @Override
    public boolean jump(int height, Obstacle obstacle){
        if (obstacle instanceof Wall) {
            if (actualJumpHeight >= height) {
                System.out.println(name + " jumped " + height + "cm.");
                return true;
            } else {
                System.out.println(name + " failed to jump " + height + "cm.");
                return false;
            }
        } else return true;
    }
}
