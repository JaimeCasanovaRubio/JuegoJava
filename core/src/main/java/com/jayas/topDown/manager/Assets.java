package com.jayas.topDown.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.jayas.topDown.utils.Cons;

public class Assets {
    private static AssetManager manager;

    public static void load() {
        manager = new AssetManager();

        // PLAYER
        manager.load(Cons.Images.PLAYER_IDLE, Texture.class);
        manager.load(Cons.Images.PLAYER_RUN, Texture.class);
        manager.load(Cons.Images.PLAYER_HIT, Texture.class);
        manager.load(Cons.Images.PLAYER_JUMP, Texture.class);
        manager.load(Cons.Images.PLAYER_WALL_JUMP, Texture.class);
        manager.load(Cons.Images.PLAYER_DOUBLE_JUMP, Texture.class);

        manager.load(Cons.Images.BACKGROUND, Texture.class);
    }

    // Bloquea hasta que todo esté cargado (para empezar rápido)
    public static void finishLoading() {
        manager.finishLoading();
    }

    // Atajo para texturas
    public static Texture getTexture(String path) {
        return manager.get(path, Texture.class);
    }

    public static void dispose() {
        manager.dispose(); // Libera TODO automáticamente
    }
}
