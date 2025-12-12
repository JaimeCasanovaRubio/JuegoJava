package com.jayas.topDown.game_states;

import com.badlogic.gdx.Game;

public abstract class State {
    private Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGameState(GameState state) {
        GameState.state = state;
    }
}
