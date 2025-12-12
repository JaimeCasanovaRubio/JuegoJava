package com.jayas.topDown.game_states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Statemethods {
    public void update(float delta);

    public void render(SpriteBatch batch);

    public void dispose();
}
