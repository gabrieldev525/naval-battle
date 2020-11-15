import java.util.ArrayList;

public class Player {
    // storage the positions that the player already play
    private ArrayList<String> playedMoves = new ArrayList<String>();

    public ArrayList<String> getPlayedMoves() {
        return playedMoves;
    }

    public boolean addPlayedMoves(String position) {
        // Player can't play in the same position
        if(playedMoves.contains(position))
            return false;

        playedMoves.add(position);
        return true;
    }
}
