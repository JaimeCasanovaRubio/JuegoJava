package com.jayas.topDown.entities.monsters;

import com.jayas.topDown.entities.Entity;
import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jayas.topDown.controllers.CollisionManager;

public class GroundEnemy extends Entity {
    private float leftBound;
    private float rightBound;
    private boolean facingRight = true;

    public GroundEnemy(float xPosition, float yPosition, int patrolRange) {

        super(xPosition, yPosition);
        this.maxHealth = 5;
        this.health = maxHealth;
        this.leftBound = xPosition - patrolRange;
        this.rightBound = xPosition + patrolRange;

        // Initialize hitbox (using similar dimensions to player for now)
        float hitboxWidth = 18 * SCALE;
        float hitboxHeight = 25 * SCALE;
        float spriteWidth = 32 * SCALE; // Assuming 32x32 sprites
        float spriteHeight = 32 * SCALE;
        initHitbox(hitboxWidth, hitboxHeight, spriteWidth, spriteHeight);

        loadAnimations();
    }

    protected void loadAnimations() {

        addAnimation(PLAYER_IDLE, getSpriteCount(IDLE), 0.07f);
    }

    @Override
    public void update(float delta, CollisionManager collisionManager) {
        super.update(delta, collisionManager);
        patrol(delta, collisionManager);

        // Actualizar el hitbox después de mover
        updateHitbox();
    }

    public void patrol(float delta, CollisionManager collisionManager) {
        // Mover en la dirección actual
        if (movingRight && !collisionManager.checkCollisions(hitbox)) {
            facingRight = true;
            xPosition += MONSTER_SPEED;
            if (xPosition >= rightBound) {
                movingRight = false; // Dar la vuelta
            }
        } else {
            facingRight = false;
            if (!collisionManager.checkCollisions(hitbox)) {
                xPosition -= MONSTER_SPEED;
                if (xPosition <= leftBound) {
                    movingRight = true; // Dar la vuelta
                }
            }
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
}
