import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Participant> participants = new ArrayList<>();

        participants.add(new Human("Bob"));
        participants.add(new Human("Nate"));
        participants.add(new Cat("Rubber"));
        participants.add(new Cat("Knot"));
        participants.add(new Robot("12-AG43"));
        participants.add(new Robot("HF32F-F"));


        ArrayList<Obstacle> obstacles = new ArrayList<>();

        obstacles.add(new Treadmill());
        obstacles.add(new Treadmill());
        obstacles.add(new Treadmill());
        obstacles.add(new Wall());
        obstacles.add(new Wall());
        obstacles.add(new Wall());

        for (Participant p : participants
        ) {
            for (Obstacle o : obstacles
            ) {
                if (!p.run(o.getValue(), o)) break;
                if (!p.jump(o.getValue(), o)) break;
            }
        }
    }
}
