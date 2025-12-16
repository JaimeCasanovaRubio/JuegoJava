package com.jayas.topDown.entities;

import java.util.ArrayList;
import static com.jayas.topDown.utils.Cons.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    protected ArrayList<Animation<TextureRegion>> animations;
    protected ArrayList<Texture> textures;
    protected int currentAnimation;
    protected float stateTime;
    protected boolean movingRight;

    protected Rectangle hitbox;
    protected float hitboxOffsetX;
    protected float hitboxOffsetY;

    protected float xPosition;
    protected float yPosition;

    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected boolean invincible = false;
    protected float invincibleTimer = 0;

    public Entity(float xPosition, float yPosition) {
        this.dead = false;
        this.maxHealth = 3;
        this.health = maxHealth;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.currentAnimation = 0;
        this.stateTime = 0f;
        this.textures = new ArrayList<>();
        this.animations = new ArrayList<>();
    }

    protected void loadAnimations() {

        addAnimation("player/movement/Run (32x32).png", 12, 0.07f);

        addAnimation("player/idle/Idle (32x32).png", 11, 0.07f);

        addAnimation("player/hit/Hit (32x32).png", 7, 0.07f);

        addAnimation("player/jump/Jump (32x32).png", 1, 0.07f);

        addAnimation("player/Jump/Wall Jump (32x32).png", 5, 0.07f);

        addAnimation("player/Jump/Double Jump (32x32).png", 6, 0.07f);
    }

    // Añade una animación desde un spritesheet horizontal
    protected void addAnimation(String path, int frameCount, float frameDuration) {
        Texture sheet = new Texture(path);
        textures.add(sheet);

        int frameWidth = sheet.getWidth() / frameCount;
        int frameHeight = sheet.getHeight();

        TextureRegion[][] tmp = TextureRegion.split(sheet, frameWidth, frameHeight);
        animations.add(new Animation<>(frameDuration, tmp[0]));
    }

    // Cambiar animación por índice
    protected void setAnimation(int index) {
        if (index >= 0 && index < animations.size() && currentAnimation != index) {
            currentAnimation = index;
            stateTime = 0f; // Reiniciar desde el primer frame
        }
    }

    // HITBOX
    /**
     * Inicializa el hitbox con un tamaño específico.
     * Los offsets centran el hitbox dentro del sprite.
     * 
     * @param width        Ancho del hitbox en píxeles del mundo
     * @param height       Alto del hitbox en píxeles del mundo
     * @param spriteWidth  Ancho del sprite escalado
     * @param spriteHeight Alto del sprite escalado
     */
    protected void initHitbox(float width, float height, float spriteWidth, float spriteHeight) {
        // Calcular offset para centrar el hitbox horizontalmente
        this.hitboxOffsetX = (spriteWidth - width) / 2f;
        // Offset Y = 0 para que el hitbox esté anclado en los pies
        // Cuando la altura cambie, solo varía la parte superior
        this.hitboxOffsetY = 1 * SCALE;

        this.hitbox = new Rectangle(
                xPosition + hitboxOffsetX,
                yPosition + hitboxOffsetY,
                width,
                height);
    }

    /**
     * Sincroniza la posición del hitbox con la posición de la entidad.
     * IMPORTANTE: Llamar esto DESPUÉS de mover la entidad.
     */
    protected void updateHitbox() {
        if (hitbox != null) {
            hitbox.setPosition(xPosition + hitboxOffsetX, yPosition + hitboxOffsetY);
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    // Gestión daño
    public void takeDamage(int damage) {
        if (!invincible) {
            health -= damage;
            invincible = true;
            invincibleTimer = 2f; // 2 segundos de invencibilidad
            if (health <= 0) {
                dead = true;
            }
        }
    }

}
