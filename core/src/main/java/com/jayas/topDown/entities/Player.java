package com.jayas.topDown.entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

    private ArrayList<Animation<TextureRegion>> animations;
    private ArrayList<Texture> textures;
    private int currentAnimation;
    private float stateTime;

    private float xPosition;
    private float yPosition;
    private float speed;

    public Player(float xPosition, float yPosition, float speed) {

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.speed = speed;
        this.animations = new ArrayList<>();
        this.textures = new ArrayList<>();
        this.currentAnimation = 0;
        this.stateTime = 0f;
    }

    // Llamar desde create() de Main
    public void loadAnimations() {

        addAnimation("player_idle.png", 2, 0.5f);

        addAnimation("player_walk.png", 4, 0.15f);

        addAnimation("player_attack.png", 6, 0.1f);

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

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    public void draw(SpriteBatch batch) {
        Animation<TextureRegion> anim = animations.get(currentAnimation);
        TextureRegion frame = anim.getKeyFrame(stateTime, true);
        batch.draw(frame, xPosition, yPosition);
    }

    // Cambiar animación por índice
    public void setAnimation(int index) {
        if (index >= 0 && index < animations.size() && currentAnimation != index) {
            currentAnimation = index;
            stateTime = 0f; // Reiniciar desde el primer frame
        }
    }

    public void dispose() {
        for (Texture t : textures) {
            t.dispose();
        }
    }

    // Getters y setters...
    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}