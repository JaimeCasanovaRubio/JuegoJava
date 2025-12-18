package com.jayas.topDown.manager.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jayas.topDown.game_states.Gamestate;

public class MenuButton {
    private Texture pressed, notPressed, texture;
    private float x, y;
    private float width, height;
    private boolean mouseOver, mousePressed;
    private Rectangle hitbox;
    private Gamestate state;

    public MenuButton(Texture notPressed, Texture pressed, Gamestate state, float x, float y, float width,
            float height) {
        this.pressed = pressed;
        this.notPressed = notPressed;
        this.x = x;
        this.y = y;
        this.state = state;
        this.width = width;
        this.height = height;
        this.mouseOver = false;
        this.mousePressed = false;
        this.texture = notPressed;
        this.hitbox = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        if (mousePressed) {
            texture = pressed;
        } else {
            texture = notPressed;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetAll() {
        mouseOver = false;
        mousePressed = false;
    }

    // GETTERS / SETTERS

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
