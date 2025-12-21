package com.jayas.topDown.game_states;

public enum Gamestate {
    PLAYING,
    MENU,
    PAUSED,
    GAME_OVER,
    EXIT,
    CONTROLS,
    CREDITS,
    SETTINGS;

    public static Gamestate state = SETTINGS;
}
