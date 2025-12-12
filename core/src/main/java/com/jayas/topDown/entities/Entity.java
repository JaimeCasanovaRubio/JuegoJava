package com.jayas.topDown.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity {
    protected ArrayList<Animation<TextureRegion>> animations;
    protected ArrayList<Texture> textures;
    protected int currentAnimation;
    protected float stateTime;

    protected float xPosition;
    protected float yPosition;

    public Entity(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.currentAnimation = 0;
        this.stateTime = 0f;
    }

    public void loadAnimations() {

        addAnimation("player/movement/Run (32x32).png", 12, 0.07f);

        addAnimation("player/idle/Idle (32x32).png", 11, 0.07f);

        addAnimation("player/hit/Hit (32x32).png", 7, 0.07f);

        addAnimation("player/jump/Jump (32x32).png", 1, 0.07f);

        addAnimation("player/Jump/Wall Jump (32x32).png", 5, 0.07f);

        addAnimation("player/Jump/Double Jump (32x32).png", 6, 0.07f);
    }

    // Añade una animación desde un spritesheet horizontal
    private void addAnimation(String path, int frameCount, float frameDuration) {
        Texture sheet = new Texture(path);
        textures.add(sheet);

        int frameWidth = sheet.getWidth() / frameCount;
        int frameHeight = sheet.getHeight();

        TextureRegion[][] tmp = TextureRegion.split(sheet, frameWidth, frameHeight);
        animations.add(new Animation<>(frameDuration, tmp[0]));
    }

    // Cambiar animación por índice
    public void setAnimation(int index) {
        if (index >= 0 && index < animations.size() && currentAnimation != index) {
            currentAnimation = index;
            stateTime = 0f; // Reiniciar desde el primer frame
        }
    }
}
