package com.jayas.topDown.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jayas.topDown.controllers.CollisionManager;

import static com.jayas.topDown.utils.Cons.*;
import static com.jayas.topDown.utils.Cons.Images.*;

public class Player extends Entity {

    // Variables heredadas de Entity: currentAnimation, stateTime, animations, etc.
    private boolean right, left, jump, fall;
    private boolean facingRight = true; // Por defecto mira a la derecha

    private boolean attack = false;
    private float verticalVelocity = 0;
    private float speed, speedJump;
    private float hitAnimationTimer = 0; // Tiempo que dura la animación de daño
    private float attackTimer = 0; // Tiempo que dura la animación de ataque

    // Constructor y carga animaciones
    public Player(float xPosition, float yPosition) {
        super(xPosition, yPosition);
        speedJump = 19f;
        facingRight = true;
        speed = PLAYER_SPEED;

        float hitboxWidth = 18 * SCALE; // Más estrecho que el width del sprite
        float hitboxHeight = 25 * SCALE; // Casi igual de alto que el height del sprite
        float spriteWidth = PLAYER_WIDTH * SCALE; // Sprite escalado
        float spriteHeight = PLAYER_HEIGHT * SCALE;
        initHitbox(hitboxWidth, hitboxHeight, spriteWidth, spriteHeight);
        loadAnimations();
    }

    public void loadAnimations() {

        addAnimation(PLAYER_RUN, getSpriteCount(RUN), 0.07f);

        addAnimation(PLAYER_IDLE, getSpriteCount(IDLE), 0.07f);

        addAnimation(PLAYER_HIT, getSpriteCount(HIT), 0.07f);

        addAnimation(PLAYER_JUMP, getSpriteCount(JUMP), 0.07f);

        addAnimation(PLAYER_WALL_JUMP, getSpriteCount(WALL_JUMP), 0.07f);

        addAnimation(PLAYER_DOUBLE_JUMP, getSpriteCount(DOUBLE_JUMP), 0.07f);

        addAnimation(PLAYER_FALL, getSpriteCount(FALL), 0.07f);

    }

    // Update y Draw
    @Override
    public void update(float deltaTime, CollisionManager collisionManager) {

        if (dead) {
            return;
        }
        if (attackTimer > 0) {
            attackTimer -= deltaTime;
            if (attackTimer <= 0) {
                attack = false;
            }
        }
        // Actualizar temporizador de animación de daño
        if (hitAnimationTimer > 0) {
            hitAnimationTimer -= deltaTime;
        }

        if (invincible) {
            invincibleTimer -= deltaTime;
            if (invincibleTimer <= 0) {
                invincible = false;
            }
        }
        updateAnimation();
        updatePosition(collisionManager);
        stateTime += deltaTime;

    }

    @Override
    public void updateAnimation() {
        if (dead) {
            setAnimation(2);
            return;
        }
        if (attackTimer > 0) {
            setAnimation(5);
            return;
        }
        // Mostrar animación de daño mientras el temporizador esté activo
        if (hitAnimationTimer > 0) {
            setAnimation(2);
            return; // Importante: no continuar para que no se sobrescriba
        }
        if (right || left) {
            setAnimation(0);
        } else if (jump) {
            setAnimation(3);
        } else if (fall) {
            setAnimation(6);
        } else {
            setAnimation(1);
        }
    }

    @Override
    public void updatePosition(CollisionManager collisionManager) {
        // --- MOVEMENT HORIZONTAL ---
        float nextX = xPosition;
        if (right) {
            nextX += speed;
            facingRight = true;
        }
        if (left) {
            nextX -= speed;
            facingRight = false;
        }

        // Check Horizontal Collision
        Rectangle testHitbox = new Rectangle(hitbox);
        testHitbox.setPosition(nextX + hitboxOffsetX, yPosition + hitboxOffsetY);
        if (!collisionManager.checkCollisions(testHitbox)) {
            xPosition = nextX;
        }

        // --- MOVEMENT VERTICAL ---
        // Gravity & Jump
        if (jump && !fall) { // saltar solo si estamos en el suelo
            verticalVelocity = speedJump;
            fall = true;
        }

        verticalVelocity -= GRAVITY; // Gravedad (Ajustar valor si es necesario)

        // Terminal velocity (limite de velocidad de caida)
        if (verticalVelocity < -20)
            verticalVelocity = -20;

        float nextY = yPosition + verticalVelocity;

        // Check Vertical Collision
        testHitbox.setPosition(xPosition + hitboxOffsetX, nextY + hitboxOffsetY);

        if (collisionManager.checkCollisions(testHitbox)) {
            // Colision detectada
            if (verticalVelocity < 0) {
                // Cayendo -> Aterrizar
                fall = false;
                verticalVelocity = 0;
            } else if (verticalVelocity > 0) {
                // Saltando -> Techo
                verticalVelocity = 0;
            }
            // No actualizamos yPosition si choca
        } else {
            // Aire libre
            yPosition = nextY;
            fall = true;
        }

        // Sincronizar hitbox con posición final
        updateHitbox();
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

    @Override
    public void takeDamage(int damage) {
        if (!invincible) {
            super.takeDamage(damage);
            hitAnimationTimer = 0.5f; // Mostrar animación de daño durante 0.5 segundos
            stateTime = 0f; // Reiniciar la animación desde el principio
        }
    }

    public void attack() {
        // Solo iniciar ataque si no hay uno en progreso
        if (!attack) {
            attack = true;
            invincible = true;
            invincibleTimer = 0.5f;
            attackTimer = 0.5f;
            stateTime = 0f; // Reiniciar la animación desde el principio
        }
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

    public boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }
}