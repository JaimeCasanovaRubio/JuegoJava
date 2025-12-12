package com.jayas.topDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jayas.topDown.game_states.GameOver;
import com.jayas.topDown.game_states.GameState;
import com.jayas.topDown.game_states.Paused;
import com.jayas.topDown.game_states.Playing;
import com.jayas.topDown.manager.Assets;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Game extends ApplicationAdapter {
    private Playing playing;
    private Menu menu;
    private Paused paused;
    private GameOver game_over;
    private SpriteBatch batch;

    @Override
    public void create() {
        Assets.load();
        Assets.finishLoading();
        batch = new SpriteBatch();
        playing = new Playing();
        menu = new Menu();
        paused = new Paused();
        game_over = new GameOver();
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw();
    }

    private void update(float delta) {
        switch (GameState.state) {
            case PLAYING:
                playing.update(delta);
                break;
            case MENU:
                menu.update(delta);
                break;
            case PAUSED:
                paused.update(delta);
                break;
            case GAME_OVER:
                game_over.update(delta);
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        playing.resize(width, height);
    }

    private void draw() {
        // Background
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        switch (GameState.state) {
            case PLAYING:
                playing.render(batch);
                // Renderizar TiledMap ANTES del SpriteBatch (tiene su propio renderer)
                if (GameState.state == GameState.PLAYING) {
                    playing.renderMap();
                }
                break;
            case MENU:
                menu.render(batch);
                break;
            case PAUSED:
                paused.render(batch);
                break;
            case GAME_OVER:
                game_over.render(batch);
                break;
        }
        // Importante cerrar el batch
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playing.dispose();
        menu.dispose();
        game_over.dispose();
        paused.dispose();
        Assets.dispose();
    }

}
