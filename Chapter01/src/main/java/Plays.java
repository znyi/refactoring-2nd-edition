import java.util.HashMap;

public class Plays {
    private final HashMap<String, Play> plays = new HashMap<>();

    public void addPlay(Play play){
        plays.put(play.getPlayID(), play);
    }

    public HashMap<String, Play> getPlays() {
        return plays;
    }
}
