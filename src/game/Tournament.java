package game;

import board.Board;

import java.util.ArrayList;

public abstract class Tournament extends Game {

    private ArrayList<Game> tournament;

    public Tournament() {
        tournament = new ArrayList<Game>();
    }

    public ArrayList<Game> getTournament() {
        return tournament;
    }

    public void setTournament(ArrayList<Game> tournament) {
        this.tournament = tournament;
    }

    @Override
    public abstract void setup();

    @Override
    public abstract void run();

    @Override
    public abstract boolean evaluate(Board board);
}
