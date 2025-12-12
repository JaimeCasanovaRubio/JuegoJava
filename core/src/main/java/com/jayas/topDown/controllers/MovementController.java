package com.jayas.topDown.controllers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.jayas.topDown.entities.Player;

public class MovementController implements InputProcessor {
    private Player player;

    public MovementController(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.A) {
            player.setLeft(true);
        } else if (keycode == Keys.D) {
            player.setRight(true);
        } else if (keycode == Keys.SPACE) {
            player.setJump(true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.A) {
            player.setLeft(false);
        } else if (keycode == Keys.D) {
            player.setRight(false);
        } else if (keycode == Keys.SPACE) {
            player.setJump(false);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
