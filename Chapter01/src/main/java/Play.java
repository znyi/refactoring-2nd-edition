public class Play {
    private String playID;
    private String name;
    private String type;

    public Play(String playID, String name, String type) {
        this.playID = playID;
        this.name = name;
        this.type = type;
    }

    public String getPlayID() {
        return playID;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
