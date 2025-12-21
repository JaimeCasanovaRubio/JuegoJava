package com.jayas.topDown.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.jayas.topDown.utils.Cons;

public class Assets {
    private static AssetManager manager;

    public static void load() {
        manager = new AssetManager();

        // BUTTONS
        manager.load(Cons.Images.BUTTON_HOVER, Texture.class);
        manager.load(Cons.Images.SMALL_BUTTON_HOVER, Texture.class);

        // PLAYER
        manager.load(Cons.Images.PLAYER_IDLE, Texture.class);
        manager.load(Cons.Images.PLAYER_RUN, Texture.class);
        manager.load(Cons.Images.PLAYER_HIT, Texture.class);
        manager.load(Cons.Images.PLAYER_JUMP, Texture.class);
        manager.load(Cons.Images.PLAYER_WALL_JUMP, Texture.class);
        manager.load(Cons.Images.PLAYER_DOUBLE_JUMP, Texture.class);
        manager.load(Cons.Images.PLAYER_FALL, Texture.class);

        // MENU
        manager.load(Cons.Images.MENU_BACKGROUND, Texture.class);
        manager.load(Cons.Images.MENU_PLAY1_BUTTON, Texture.class);
        manager.load(Cons.Images.MENU_PLAY2_BUTTON, Texture.class);
        manager.load(Cons.Images.MENU_EXIT1_BUTTON, Texture.class);
        manager.load(Cons.Images.MENU_EXIT2_BUTTON, Texture.class);
        manager.load(Cons.Images.MENU_SETTINGS1_BUTTON, Texture.class);
        manager.load(Cons.Images.MENU_SETTINGS2_BUTTON, Texture.class);
        manager.load(Cons.Images.MENU_OPTIONS_BACKGROUND, Texture.class);

        // SETTINGS
        manager.load(Cons.Images.SETTINGS_BOTONES_BACKGROUND, Texture.class);
        manager.load(Cons.Images.SETTINGS_SONIDO_BACKGROUND, Texture.class);
        manager.load(Cons.Images.SETTINGS_TITLE, Texture.class);
        manager.load(Cons.Images.SETTINGS_CUADRO_CERRAR, Texture.class);
        manager.load(Cons.Images.BOTON_CERRAR1, Texture.class);
        manager.load(Cons.Images.BOTON_CERRAR2, Texture.class);
        manager.load(Cons.Images.BOTON_CONTROLES1, Texture.class);
        manager.load(Cons.Images.BOTON_CONTROLES2, Texture.class);
        manager.load(Cons.Images.BOTON_CREDITOS1, Texture.class);
        manager.load(Cons.Images.BOTON_CREDITOS2, Texture.class);

        // PLAYING
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
