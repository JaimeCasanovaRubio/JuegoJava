package com.jayas.topDown.game_states;

public enum Gamestate {
    PLAYING,
    MENU,
    PAUSED,
    GAME_OVER,
    EXIT,
    SETTINGS;

    public static Gamestate state = MENU;
}
