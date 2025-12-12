package com.jayas.topDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jayas.topDown.controllers.MovementController;
import com.jayas.topDown.entities.Player;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Player player;
    private MovementController movController;

    @Override
    public void create() {

        batch = new SpriteBatch();

        image = new Texture("tileSet/2 Background/Background.png");
        player = new Player(960 / 2, 540 / 2, 5, 4f);
        movController = new MovementController(player);
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw();
    }

    private void update(float delta) {
        // Para actualizar posiciones, input, colisiones, etc
        movController.checkKeyboard();
        player.update(delta);
    }

    private void draw() {
        // Background
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        // Aquí dentro se dibuja todo
        batch.draw(image, (960 / 2) - image.getWidth() / 2, 540 / 2 - image.getHeight() / 2);
        player.draw(batch);
        // Importante cerrar el batch
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

}
