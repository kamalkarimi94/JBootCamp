package game;

import board.Board;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TournamentClassic extends Tournament {
    private Scanner scanner;
    private int gameCnt;
    private HashMap<String, Integer> players;

    public TournamentClassic() {
        scanner = new Scanner(System.in);
        this.gameCnt = 0;
        players = new HashMap<String, Integer>();

    }

    public int getGameCnt() {
        return gameCnt;
    }

    public void setGameCnt(int gameCnt) {
        this.gameCnt = gameCnt;
    }

    private int gameCnt(){
        System.out.println("How many game?");
        return scanner.nextInt();
    }

    @Override
    public void setup() {
        setGameCnt(gameCnt());
        initialTournament();
    }

    @Override
    public void run() {
        startTournament();
        for (String p: players.keySet()) {
            System.out.println(p + ":" + players.get(p));
        }
    }

    @Override
    public boolean evaluate(Board board) {
        return false;
    }

    private void initialTournament(){
        ArrayList<Game> game = getTournament();
        for (int i=0 ; i<getGameCnt() ; i++ ){
            game.add(new ClassicGame());
        }
    }

    private void startTournament(){
        for (int i=0 ; i<getTournament().size() ; i++){
            getTournament().get(i).setup();
            getTournament().get(i).run();
            Player p = getTournament().get(i).getWinPlayer();
            if (players.containsKey(p.getName())){
                int j = players.get(p.getName());
                players.put(p.getName(),++j);
            }else {
                players.put(p.getName(),1);
            }
        }
    }
}
