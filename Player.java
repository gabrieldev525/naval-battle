import java.io.IOException;
import java.util.ArrayList;

public class Player {
    // storage the positions that the player already play
    private ArrayList<String> playedMoves = new ArrayList<String>();
    private int id;

    public Player(int id) {
        this.id = id;
    }

    public ArrayList<String> getPlayedMoves() {
        return playedMoves;
    }

    public boolean addPlayedMoves(String position) {
        // Player can't play in the same position
        if(playedMoves.contains(position))
            return false;

        playedMoves.add(position);

        // storage the players moves in a file
        try {
            Utils.appendDataToFile("./data/attack" + id + ".txt", position + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
