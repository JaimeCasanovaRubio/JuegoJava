package com.jayas.topDown.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.jayas.topDown.entities.Player;

public class MovementController {
    private Player player;

    public MovementController(Player player) {
        this.player = player;
    }

    public void checkKeyboard() {
        player.setJump(false);

        if (Gdx.input.isKeyPressed(Keys.A)) {
            player.setLeft(true);
        } else {
            player.setLeft(false);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            player.setRight(true);
        } else {
            player.setRight(false);
        }
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            player.setJump(true);
        }
    }
}
