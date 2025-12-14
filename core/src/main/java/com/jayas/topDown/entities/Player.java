package com.jayas.topDown.entities;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jayas.topDown.manager.Assets;

import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;

public class Player extends Entity {

    private ArrayList<Animation<TextureRegion>> animations;
    private ArrayList<Texture> textures;
    private int currentAnimation;
    private float stateTime;
    private boolean right, left, jump;
    private boolean facingRight = true; // Por defecto mira a la derecha

    private float speed, speedJump;

    public Player(float xPosition, float yPosition) {
        super(xPosition, yPosition);
        this.animations = new ArrayList<>();
        this.textures = new ArrayList<>();
        speedJump = 15f;
        facingRight = true;
        speed = PLAYER_SPEED;

        float hitboxWidth = 30 * SCALE; // Más estrecho que el width del sprite
        float hitboxHeight = 28 * SCALE; // Casi igual de alto que el height del sprite
        initHitbox(hitboxWidth, hitboxHeight, PLAYER_WIDTH, PLAYER_HEIGHT);
        loadAnimations();
    }

    public void loadAnimations() {

        addAnimation(PLAYER_RUN, getSpriteCount(RUN), 0.07f);

        addAnimation(PLAYER_IDLE, getSpriteCount(IDLE), 0.07f);

        addAnimation(PLAYER_HIT, getSpriteCount(HIT), 0.07f);

        addAnimation(PLAYER_JUMP, getSpriteCount(JUMP), 0.07f);

        addAnimation(PLAYER_WALL_JUMP, getSpriteCount(WALL_JUMP), 0.07f);

        addAnimation(PLAYER_DOUBLE_JUMP, getSpriteCount(DOUBLE_JUMP), 0.07f);

    }

    // Añade una animación desde un spritesheet horizontal
    private void addAnimation(String path, int frameCount, float frameDuration) {
        Texture sheet = Assets.getTexture(path);
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

        float width = frame.getRegionWidth() * SCALE;
        float height = frame.getRegionHeight() * SCALE;
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

        updateHitbox();
    }

    public void dispose() {

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