package com.jayas.topDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jayas.topDown.controllers.InputController;
import com.jayas.topDown.game_states.GameOver;
import com.jayas.topDown.game_states.Gamestate;
import com.jayas.topDown.game_states.Menu;
import com.jayas.topDown.game_states.Paused;
import com.jayas.topDown.game_states.Playing;
import com.jayas.topDown.game_states.Settings;
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
    private Settings settings;
    private SpriteBatch batch;
    private InputController inputController;

    @Override
    public void create() {
        Assets.load();
        Assets.finishLoading();
        inputController = new InputController(this);
        batch = new SpriteBatch();
        playing = new Playing(inputController);
        menu = new Menu();
        paused = new Paused();
        game_over = new GameOver();
        settings = new Settings();
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw();
    }

    private void update(float delta) {
        switch (Gamestate.state) {
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
            case EXIT:
                Gdx.app.exit();
                break;
            case SETTINGS:
                settings.update(delta);
                break;
            default:
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        playing.resize(width, height);
        menu.resize(width, height);
        settings.resize(width, height);
    }

    private void draw() {
        // Background
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        switch (Gamestate.state) {
            case PLAYING:
                playing.render(batch);
                // Renderizar TiledMap ANTES del SpriteBatch (tiene su propio renderer)
                if (Gamestate.state == Gamestate.PLAYING) {
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
            case EXIT:
                break;
            case SETTINGS:
                settings.render(batch);
                break;
            default:
                break;
        }
        // Importante cerrar el batch
        batch.end();

        // Renderizar debug DESPUÉS de cerrar el batch (ShapeRenderer separado)
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.renderDebug();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        playing.dispose();
        menu.dispose();
        game_over.dispose();
        paused.dispose();
        settings.dispose();
        Assets.dispose();
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public Paused getPaused() {
        return paused;
    }

    public GameOver getGameOver() {
        return game_over;
    }

    public Settings getSettings() {
        return settings;
    }

}
