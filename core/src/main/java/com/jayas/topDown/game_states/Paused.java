package com.jayas.topDown.game_states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paused implements Statemethods {
    private SpriteBatch batch;

    public Paused(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void dispose() {

    }
}
