package com.jayas.topDown.manager;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jayas.topDown.manager.buttons.MenuButton;

public class HelpMethods {
    public static boolean isIn(int screenX, int screenY, MenuButton b, Viewport viewport, Vector3 touchPoint) {
        viewport.unproject(touchPoint.set(screenX, screenY, 0));

        return b.getHitbox().contains(touchPoint.x, touchPoint.y);
    }
}
