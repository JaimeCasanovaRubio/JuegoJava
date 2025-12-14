package com.jayas.topDown.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import static com.jayas.topDown.utils.Cons.SCALE;

public class MapController {
    private static final float UNIT_SCALE = SCALE; // Usar la misma escala que el juego

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private TmxMapLoader mapLoader;
    private OrthographicCamera camera;
    private CollisionManager collisionManager;

    public MapController() {
        mapLoader = new TmxMapLoader();
        collisionManager = new CollisionManager();
    }

    public void loadMap(String mapPath) {
        if (map != null) {

        }if(renderer!=null) {
            renderer.dispose();
        {

        }
        map = mapLoader.load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);
        collisionManager.loadCollitions(map);
    }

    public void render(OrthographicCamera mainCamera) {
        this.camera = mainCamera;
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        if (map != null) {
            map.dispose();
        }

        if (renderer != null) {
            renderer.dispose();
        }
    }

    public TiledMap getMap() {
        return map;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
}
