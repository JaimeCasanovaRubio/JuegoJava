package com.jayas.topDown.game_states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jayas.topDown.controllers.MapController;
import com.jayas.topDown.controllers.MovementController;
import com.jayas.topDown.entities.Player;
import com.jayas.topDown.entities.monsters.GroundEnemy;
import com.jayas.topDown.manager.Assets;

import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;

public class Playing implements Statemethods {
    private Texture background;
    private Player player;
    private GroundEnemy enemy;
    private MovementController movController;
    private MapController mapController;

    // CAMARA
    private OrthographicCamera camera;
    private Viewport viewport;

    // DEBUG
    private ShapeRenderer shapeRenderer;
    private boolean debugMode = false;

    public Playing() {
        initClasses();
    }

    private void initClasses() {

        background = Assets.getTexture(BACKGROUND);
        mapController = new MapController();
        mapController.loadMap("maps/prueba.tmx");
        player = new Player(200f, 300f);
        enemy = new GroundEnemy(270f, 642f, 120);
        movController = new MovementController(player);
        Gdx.input.setInputProcessor(movController);
        camera = new OrthographicCamera();
        viewport = new FitViewport(SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT, camera);

        viewport.apply();
        camera.position.set(player.getxPosition(), player.getyPosition(), 0);
        camera.update();

        // Debug renderer
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {
        // Toggle debug con F3
        if (Gdx.input.isKeyJustPressed(Keys.F3)) {
            debugMode = !debugMode;
            System.out.println("Debug mode: " + (debugMode ? "ON" : "OFF"));
        }

        enemy.update(delta, mapController.getCollisionManager());
        player.update(delta, mapController.getCollisionManager());
        camera.position.x = player.getxPosition();
        camera.position.y = player.getyPosition();
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        // Fondo fijo en el mundo (en posición 0,0) para que el jugador se mueva
        // TODO -> según el nivel en el que esté, el tamaño del fondo debe cambiar
        batch.draw(background, 0, 0, mapController.getMapWidth(), mapController.getMapHeight());
        player.draw(batch);
        enemy.draw(batch);
    }

    // Renderiza el mapa TiledMap (debe llamarse FUERA del SpriteBatch)
    public void renderMap() {
        mapController.render(camera);
    }

    /**
     * Renderiza hitboxes y rectángulos de colisión para debug.
     * Debe llamarse DESPUÉS de batch.end() y renderMap().
     */
    public void renderDebug() {
        if (!debugMode)
            return;

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Dibujar rectángulos de colisión del mapa (ROJO)
        shapeRenderer.setColor(Color.RED);
        for (Rectangle rect : mapController.getCollisionManager().getCollisionRects()) {
            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }

        // Dibujar hitbox del jugador (VERDE)
        shapeRenderer.setColor(Color.GREEN);
        Rectangle playerHitbox = player.getHitbox();
        if (playerHitbox != null) {
            shapeRenderer.rect(playerHitbox.x, playerHitbox.y,
                    playerHitbox.width, playerHitbox.height);
        }

        shapeRenderer.end();
    }

    // Importante: manejar resize
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        shapeRenderer.dispose();
    }
}