package com.jayas.topDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jayas.topDown.controllers.MapController;
import com.jayas.topDown.controllers.MovementController;
import com.jayas.topDown.entities.Player;

import static com.jayas.topDown.utils.Cons.*;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Player player;
    private MovementController movController;
    private MapController mapController;
    private OrthographicCamera camera;

    @Override
    public void create() {

        batch = new SpriteBatch();

        // Configurar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT);

        image = new Texture("tileSet/2 Background/Background.png");
        player = new Player(SMALL_WINDOW_WIDTH / 2, SMALL_WINDOW_HEIGHT / 2, 5);
        movController = new MovementController(player);
        mapController = new MapController();
        mapController.loadMap("maps/prueba.tmx");

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

        // Actualizar la cámara
        camera.update();

        // Renderizar el mapa ANTES del batch (el renderer tiene su propio batch
        // interno)
        mapController.render(camera);

        // Ahora dibujamos el resto con nuestro batch
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Aquí dentro se dibuja todo (personaje, UI, etc.)
        // Si quieres mantener el fondo estático, descomenta la siguiente línea:
        // batch.draw(image, 0, 0, SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT);
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
