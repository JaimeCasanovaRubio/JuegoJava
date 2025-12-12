package com.jayas.topDown.game_states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jayas.topDown.controllers.MovementController;
import com.jayas.topDown.entities.Player;
import com.jayas.topDown.manager.Assets;

import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;

public class Playing implements Statemethods {
    private SpriteBatch batch;
    private Texture background;
    private Player player;
    private MovementController movController;

    // CAMARA
    private OrthographicCamera camera;
    private Viewport viewport;

    public Playing(SpriteBatch batch) {
        this.batch = batch;
        initClasses();
    }

    private void initClasses() {
        background = Assets.getTexture(BACKGROUND);
        player = new Player(SMALL_WINDOW_WIDTH / 2, SMALL_WINDOW_HEIGHT / 2);
        movController = new MovementController(player);
        Gdx.input.setInputProcessor(movController);
        camera = new OrthographicCamera();
        viewport = new FitViewport(SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT, camera);

        viewport.apply();
        camera.position.set(player.getxPosition(), player.getyPosition(), 0);
        camera.update();
    }

    @Override
    public void update(float delta) {
        player.update(delta);
        camera.position.x = player.getxPosition();
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        // Fondo fijo en el mundo (en posición 0,0) para que el jugador se mueva
        // TODO -> según el nivel en el que esté, el tamaño del fondo debe cambiar
        batch.draw(background, 0, 0, SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT);
        player.draw(batch);
    }

    // Importante: manejar resize
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
    }

}