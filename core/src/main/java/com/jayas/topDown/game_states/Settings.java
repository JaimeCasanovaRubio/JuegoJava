package com.jayas.topDown.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;
import com.jayas.topDown.manager.Assets;

public class Settings implements Statemethods {
    private Texture background;
    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private com.badlogic.gdx.utils.viewport.Viewport viewport;
    private com.badlogic.gdx.math.Vector3 touchPoint;

    public Settings() {
        initClasses();
    }

    private void initClasses() {
        camera = new com.badlogic.gdx.graphics.OrthographicCamera();
        viewport = new com.badlogic.gdx.utils.viewport.FitViewport(SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT, camera);
        camera.position.set(SMALL_WINDOW_WIDTH / 2f, SMALL_WINDOW_HEIGHT / 2f, 0);
        camera.update();

        touchPoint = new com.badlogic.gdx.math.Vector3();

        background = Assets.getTexture(MENU_BACKGROUND);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
        if (key == Input.Keys.ESCAPE) {
            Gamestate.state = Gamestate.MENU;
        }
    }
}
