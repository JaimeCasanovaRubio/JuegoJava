package com.jayas.topDown.controllers;

import com.jayas.topDown.Game;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.jayas.topDown.game_states.Gamestate;

public class InputController implements InputProcessor {
    private Game game;

    public InputController(Game g) {
        this.game = g;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (Gamestate.state) {
            case PLAYING:
                if (keycode == Keys.A) {
                    game.getPlaying().getPlayer().setLeft(true);
                } else if (keycode == Keys.D) {
                    game.getPlaying().getPlayer().setRight(true);
                } else if (keycode == Keys.SPACE) {
                    game.getPlaying().getPlayer().setJump(true);
                } else if (keycode == Keys.J) {
                    game.getPlaying().getPlayer().attack(); // Inicia el ataque con su timer
                }
                break;
            case MENU:
                break;
            case PAUSED:
                break;
            case GAME_OVER:
                break;
            case SETTINGS:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (Gamestate.state) {
            case PLAYING:
                if (keycode == Keys.A) {
                    game.getPlaying().getPlayer().setLeft(false);
                } else if (keycode == Keys.D) {
                    game.getPlaying().getPlayer().setRight(false);
                } else if (keycode == Keys.SPACE) {
                    game.getPlaying().getPlayer().setJump(false);
                } else if (keycode == Keys.ESCAPE) {
                    Gamestate.state = Gamestate.MENU;
                }
                break;
            case MENU:
                game.getMenu().keyReleased(keycode);
                break;
            case PAUSED:
                break;
            case GAME_OVER:
                break;
            case EXIT:
                break;
            case SETTINGS:
                game.getSettings().keyReleased(keycode);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mousePressed(screenX, screenY, pointer, button);
                break;
            case PAUSED:
                break;
            case GAME_OVER:
                break;
            case EXIT:
                break;
            case SETTINGS:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mouseReleased(screenX, screenY, pointer, button);
                break;
            case PAUSED:
                break;
            case GAME_OVER:
                break;
            case EXIT:
                break;
            case SETTINGS:
                break;
            default:
                break;
        }
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
        switch (Gamestate.state) {
            case MENU:
                game.getMenu().mouseMoved(screenX, screenY);
                break;
            case PAUSED:
                break;
            case GAME_OVER:
                break;
            case EXIT:
                break;
            case SETTINGS:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
