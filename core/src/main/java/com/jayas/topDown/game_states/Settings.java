package com.jayas.topDown.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;
import static com.jayas.topDown.manager.HelpMethods.*;

import com.jayas.topDown.manager.Assets;
import com.jayas.topDown.manager.buttons.MenuButton;

public class Settings implements Statemethods {
    private Texture background;
    private Texture soundBackground, buttonsBackground, closeBox;
    private Texture settingsTitle;
    private Texture closeButton1, closeButton2;
    private Texture controlsButton1, controlsButton2;
    private Texture creditsButton1, creditsButton2;
    private Texture buttonHover, smallButtonHover;

    private MenuButton[] controlButtons;
    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private com.badlogic.gdx.utils.viewport.Viewport viewport;
    private com.badlogic.gdx.math.Vector3 touchPoint;

    float centerX = SMALL_WINDOW_WIDTH / 2f;
    float centerY = SMALL_WINDOW_HEIGHT / 2f;
    float soundW, soundH;
    float buttonsW, buttonsH, buttonsX, buttonsY, buttonsXmid, buttonsYmid;
    float closeW, closeH, closeX, closeY, closeXmid, closeYmid;
    float titleW, titleH;

    public Settings() {
        initClasses();
    }

    private void initClasses() {
        camera = new com.badlogic.gdx.graphics.OrthographicCamera();
        viewport = new com.badlogic.gdx.utils.viewport.FitViewport(SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT, camera);
        camera.position.set(centerX, centerY, 0);
        camera.update();

        touchPoint = new com.badlogic.gdx.math.Vector3();

        background = Assets.getTexture(MENU_BACKGROUND);
        soundBackground = Assets.getTexture(SETTINGS_SONIDO_BACKGROUND);
        buttonsBackground = Assets.getTexture(SETTINGS_BOTONES_BACKGROUND);
        closeBox = Assets.getTexture(SETTINGS_CUADRO_CERRAR);
        settingsTitle = Assets.getTexture(SETTINGS_TITLE);
        closeButton1 = Assets.getTexture(BOTON_CERRAR1);
        closeButton2 = Assets.getTexture(BOTON_CERRAR2);
        controlsButton1 = Assets.getTexture(BOTON_CONTROLES1);
        controlsButton2 = Assets.getTexture(BOTON_CONTROLES2);
        creditsButton1 = Assets.getTexture(BOTON_CREDITOS1);
        creditsButton2 = Assets.getTexture(BOTON_CREDITOS2);
        buttonHover = Assets.getTexture(BUTTON_HOVER);
        smallButtonHover = Assets.getTexture(SMALL_BUTTON_HOVER);

        soundW = soundBackground.getWidth() * UI_SCALE;
        soundH = soundBackground.getHeight() * UI_SCALE;

        buttonsW = buttonsBackground.getWidth() * UI_SCALE;
        buttonsH = buttonsBackground.getHeight() * UI_SCALE;

        buttonsX = centerX + soundW / 2 - (3.25f * UI_SCALE);
        buttonsY = centerY - soundH / 2.5f;
        buttonsXmid = buttonsX + buttonsW / 2 + (7.5f * UI_SCALE);
        buttonsYmid = buttonsY + buttonsH / 2;

        closeW = closeBox.getWidth() * UI_SCALE;
        closeH = closeBox.getHeight() * UI_SCALE;
        closeX = centerX - soundW / 2 - closeW + (3.25f * UI_SCALE);
        closeY = centerY;
        closeXmid = centerX - soundW / 2 - closeW + (3.25f * UI_SCALE) + closeW / 2 - (10 * UI_SCALE);
        closeYmid = centerY + closeH / 2;

        titleW = settingsTitle.getWidth() * UI_SCALE;
        titleH = settingsTitle.getHeight() * UI_SCALE;

        float closeButtonW = closeButton1.getWidth() * UI_SCALE;
        float closeButtonH = closeButton1.getHeight() * UI_SCALE;

        float buttonsW = controlsButton1.getWidth() * UI_SCALE;
        float buttonsH = controlsButton1.getHeight() * UI_SCALE;

        controlButtons = new MenuButton[3];
        controlButtons[0] = new MenuButton(closeButton1, closeButton2, Gamestate.MENU, closeXmid - closeButtonW / 2,
                closeYmid - closeButtonH / 2, closeButtonW, closeButtonH);
        controlButtons[1] = new MenuButton(controlsButton1, controlsButton2, Gamestate.CONTROLS,
                buttonsXmid - buttonsW / 2,
                buttonsYmid + (7 * UI_SCALE), buttonsW, buttonsH);
        controlButtons[2] = new MenuButton(creditsButton1, creditsButton2, Gamestate.CREDITS,
                buttonsXmid - buttonsW / 2,
                buttonsYmid - (22 * UI_SCALE), buttonsW, buttonsH);
    }

    @Override
    public void update(float delta) {
        for (MenuButton b : controlButtons) {
            b.update(delta);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());

        batch.draw(soundBackground, centerX - soundW / 2, centerY - soundH / 2,
                soundW, soundH);

        batch.draw(buttonsBackground, buttonsX, buttonsY, buttonsW, buttonsH);

        batch.draw(closeBox, closeX, closeY, closeW, closeH);

        batch.draw(settingsTitle, buttonsXmid - titleW / 2, buttonsY + buttonsH + (10 * UI_SCALE), titleW, titleH);

        float gapHoverX = (buttonHover.getWidth() * UI_SCALE - controlsButton1.getWidth() * UI_SCALE) / 2;
        float gapHoverY = (buttonHover.getHeight() * UI_SCALE - controlsButton1.getHeight() * UI_SCALE) / 2;
        float gapSmallHoverX = (smallButtonHover.getWidth() * UI_SCALE - closeButton1.getWidth() * UI_SCALE) / 2;
        float gapSmallHoverY = (smallButtonHover.getHeight() * UI_SCALE - closeButton1.getHeight() * UI_SCALE) / 2;

        for (MenuButton b : controlButtons) {
            b.render(batch);
            if (b.isMouseOver()) {
                if (b.getWidth() == closeButton1.getWidth() * UI_SCALE)
                    batch.draw(smallButtonHover, b.getX() - gapSmallHoverX, b.getY() - gapSmallHoverY,
                            smallButtonHover.getWidth() * UI_SCALE, smallButtonHover.getHeight() * UI_SCALE);
                else
                    batch.draw(buttonHover, b.getX() - gapHoverX, b.getY() - gapHoverY,
                            buttonHover.getWidth() * UI_SCALE,
                            buttonHover.getHeight() * UI_SCALE);
            }
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        background.dispose();
        soundBackground.dispose();
        buttonsBackground.dispose();
        closeBox.dispose();
        settingsTitle.dispose();
        closeButton1.dispose();
        closeButton2.dispose();
        controlsButton1.dispose();
        controlsButton2.dispose();
        creditsButton1.dispose();
        creditsButton2.dispose();
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
        if (key == Input.Keys.ESCAPE) {
            Gamestate.state = Gamestate.MENU;
        }
    }

    public void mousePressed(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));
        for (MenuButton b : controlButtons) {
            if (isIn(screenX, screenY, b, viewport, touchPoint) && button == Input.Buttons.LEFT) {
                b.setMousePressed(true);
            }
        }
    }

    public void mouseReleased(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));

        for (MenuButton b : controlButtons) {
            if (isIn(screenX, screenY, b, viewport, touchPoint) && button == Input.Buttons.LEFT) {
                b.applyGamestate();
            }
        }
        resetButtons();
    }

    public void mouseMoved(int screenX, int screenY) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));
        for (MenuButton b : controlButtons) {
            if (isIn(screenX, screenY, b, viewport, touchPoint)) {
                b.setMouseOver(true);
            } else {
                b.setMouseOver(false);
            }
        }
    }

    private void resetButtons() {
        for (MenuButton b : controlButtons) {
            b.resetAll();
        }
    }
}
