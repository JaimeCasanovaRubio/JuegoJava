package com.jayas.topDown.entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {

    private ArrayList<Animation<TextureRegion>> animations;
    private ArrayList<Texture> textures;
    private int currentAnimation;
    private float stateTime;
    private boolean right, left, jump;
    private boolean facingRight = true; // Por defecto mira ala derecha

    private float scale, speedJump;

    public Player(float xPosition, float yPosition, float speed, float scale) {
        super(xPosition, yPosition, speed);
        this.scale = scale;
        this.animations = new ArrayList<>();
        this.textures = new ArrayList<>();
        speedJump = 15f;
        facingRight = true;

        loadAnimations();
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

    public void update(float deltaTime) {
        updateAnimation();
        updatePosition();
        stateTime += deltaTime;

    }

    public void updateAnimation() {
        if (right || left) {
            setAnimation(0);
        } else if (jump) {
            setAnimation(3);
        } else {
            setAnimation(1);
        }
    }

    public void draw(SpriteBatch batch) {
        Animation<TextureRegion> anim = animations.get(currentAnimation);
        TextureRegion frame = anim.getKeyFrame(stateTime, true);

        float width = frame.getRegionWidth() * scale;
        float height = frame.getRegionHeight() * scale;
        // Invertir el frame si mira a la izquierda y no está ya invertido, o viceversa
        boolean needsFlip = (facingRight && frame.isFlipX()) || (!facingRight && !frame.isFlipX());
        if (needsFlip) {
            frame.flip(true, false);
        }
        batch.draw(frame, xPosition, yPosition, width, height);
    }

    // Cambiar animación por índice
    public void setAnimation(int index) {
        if (index >= 0 && index < animations.size() && currentAnimation != index) {
            currentAnimation = index;
            stateTime = 0f; // Reiniciar desde el primer frame
        }
    }

    public void updatePosition() {
        if (right) {
            xPosition += speed;
            facingRight = true;
        }
        if (left) {
            xPosition -= speed;
            facingRight = false;
        }
        if (jump) {
            yPosition += speedJump;
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

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}