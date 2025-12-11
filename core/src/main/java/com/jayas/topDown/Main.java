package com.jayas.topDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Texture logo;

    @Override
    public void create() {
        batch = new SpriteBatch();
        logo = new Texture("logo.png");
        image = new Texture("libgdx.png");
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw();
    }

    private void update(float delta) {
        // Para actualizar posiciones, input, colisiones, etc
    }

    private void draw() {
        // Background
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        // Aquí dentro se dibuja todo
        batch.draw(image, (960 / 2) - image.getWidth() / 2, 540 / 2 - image.getHeight() / 2);
        batch.draw(logo, (960 / 3) - image.getWidth() / 2, 540 / 2 - logo.getHeight() / 2);

        // Importante cerrar el batch
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
