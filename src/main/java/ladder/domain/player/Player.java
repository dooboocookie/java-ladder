package ladder.domain.player;

public class Player {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }

}