package com.jayas.topDown.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.jayas.topDown.manager.Assets;
import com.jayas.topDown.manager.buttons.MenuButton;

import static com.jayas.topDown.utils.Cons.Images.*;
import static com.jayas.topDown.utils.Cons.*;

public class Menu implements Statemethods {
    private MenuButton[] buttons;
    private Texture background, optionsBackground;
    private Texture playButton1, playButton2;
    private Texture exitButton1, exitButton2;
    private Texture settingsButton1, settingsButton2;
    private Texture buttonHover;

    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private com.badlogic.gdx.utils.viewport.Viewport viewport;
    private com.badlogic.gdx.math.Vector3 touchPoint;

    float centerX = SMALL_WINDOW_WIDTH / 2f;
    float centerY = SMALL_WINDOW_HEIGHT / 2f;

    public Menu() {
        initClasses();
    }

    private void initClasses() {
        camera = new com.badlogic.gdx.graphics.OrthographicCamera();
        viewport = new com.badlogic.gdx.utils.viewport.FitViewport(SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT, camera);
        camera.position.set(SMALL_WINDOW_WIDTH / 2f, SMALL_WINDOW_HEIGHT / 2f, 0);
        camera.update();

        touchPoint = new com.badlogic.gdx.math.Vector3();

        background = Assets.getTexture(MENU_BACKGROUND);
        optionsBackground = Assets.getTexture(MENU_OPTIONS_BACKGROUND);
        buttonHover = Assets.getTexture(BUTTON_HOVER);

        playButton1 = Assets.getTexture(MENU_PLAY1_BUTTON);
        playButton2 = Assets.getTexture(MENU_PLAY2_BUTTON);
        exitButton1 = Assets.getTexture(MENU_EXIT1_BUTTON);
        exitButton2 = Assets.getTexture(MENU_EXIT2_BUTTON);
        settingsButton1 = Assets.getTexture(MENU_SETTINGS1_BUTTON);
        settingsButton2 = Assets.getTexture(MENU_SETTINGS2_BUTTON);

        // Buttons

        float spacing = MENU_BUTTON_HEIGHT * UI_SCALE * 1.5f;
        float x = centerX - MENU_BUTTON_WIDTH * UI_SCALE / 2;

        buttons = new MenuButton[3];
        buttons[0] = new MenuButton(playButton1, playButton2, Gamestate.PLAYING,
                x,
                centerY - MENU_BUTTON_HEIGHT * UI_SCALE / 2 + spacing, MENU_BUTTON_WIDTH * UI_SCALE,
                MENU_BUTTON_HEIGHT * UI_SCALE);
        buttons[1] = new MenuButton(settingsButton1, settingsButton2, Gamestate.SETTINGS,
                x,
                centerY - MENU_BUTTON_HEIGHT * UI_SCALE / 2, MENU_BUTTON_WIDTH * UI_SCALE,
                MENU_BUTTON_HEIGHT * UI_SCALE);
        buttons[2] = new MenuButton(exitButton1, exitButton2, Gamestate.EXIT,
                x,
                centerY - MENU_BUTTON_HEIGHT * UI_SCALE / 2 - spacing, MENU_BUTTON_WIDTH * UI_SCALE,
                MENU_BUTTON_HEIGHT * UI_SCALE);
    }

    @Override
    public void update(float delta) {
        for (MenuButton button : buttons) {
            button.update(delta);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        float optionsW = optionsBackground.getWidth() * UI_SCALE * 1.25f;
        float optionsH = optionsBackground.getHeight() * UI_SCALE * 1.25f;

        batch.draw(background, 0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
        batch.draw(optionsBackground, centerX - optionsW / 2, centerY - optionsH / 2, optionsW, optionsH);

        for (MenuButton button : buttons) {
            if (button.isMouseOver()) {
                float gapX = (buttonHover.getWidth() * UI_SCALE - button.getWidth()) / 2;
                float gapY = (buttonHover.getHeight() * UI_SCALE - button.getHeight()) / 2;

                batch.draw(buttonHover, button.getX() - gapX,
                        button.getY() - gapY,
                        buttonHover.getWidth() * UI_SCALE, buttonHover.getHeight() * UI_SCALE);
            }
            button.render(batch);
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        background.dispose();
        optionsBackground.dispose();
        playButton1.dispose();
        playButton2.dispose();
        exitButton1.dispose();
        exitButton2.dispose();
        settingsButton1.dispose();
        settingsButton2.dispose();
    }

    public void keyReleased(int keyCode) {
        if (keyCode == Input.Keys.ESCAPE) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    public void mousePressed(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));

        for (MenuButton b : buttons) {
            if (isIn(screenX, screenY, b) && button == Input.Buttons.LEFT) {
                b.setMousePressed(true);
            }
        }
    }

    public void mouseReleased(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));

        for (MenuButton b : buttons) {
            if (isIn(screenX, screenY, b) && button == Input.Buttons.LEFT) {
                b.applyGamestate();
            }
        }
        resetButtons();
    }

    public void mouseMoved(int screenX, int screenY) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));

        for (MenuButton b : buttons) {
            if (isIn(screenX, screenY, b)) {
                b.setMouseOver(true);
            } else {
                b.setMouseOver(false);
            }
        }
    }

    private void resetButtons() {
        for (MenuButton b : buttons) {
            b.resetAll();
        }
    }

    private boolean isIn(int screenX, int screenY, MenuButton b) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));

        return b.getHitbox().contains(touchPoint.x, touchPoint.y);
    }

}
