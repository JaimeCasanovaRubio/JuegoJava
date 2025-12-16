# 📋 Plan de Implementación - Persona B
## 🎮 Enfoque: Gameplay y Mecánicas del Juego

---

## 📌 Resumen del Rol

**Persona B** se enfoca en las mecánicas core del juego: enemigos, items, sistema de daño, múltiples niveles, y mejoras del jugador. Tu trabajo hará que el juego sea divertido y desafiante.

---

## 🎯 Tareas Asignadas

### 1. Sistema de Enemigos
**Prioridad:** 🔴 Alta | **Dificultad:** Alta

#### Objetivo
Crear enemigos con IA básica que representen un desafío para el jugador.

#### Implementación
```java
// Crear nueva clase: entities/Enemy.java
- [ ] Heredar de Entity
- [ ] Cargar animaciones de enemigo
- [ ] Implementar movimiento patrulla (izquierda-derecha)
- [ ] Detección del jugador (rango)
- [ ] Sistema de hitbox y colisiones
- [ ] Comportamiento al ser derrotado
```

#### Estructura Propuesta
```
entities/
├── Entity.java         # (existente)
├── Player.java         # (existente)
├── Enemy.java          # Clase base para enemigos
├── enemies/
│   ├── SlimeEnemy.java     # Enemigo básico
│   ├── FlyingEnemy.java    # Enemigo volador
│   └── BossEnemy.java      # Jefe (avanzado)
```

#### Archivos a Modificar/Crear
| Archivo | Acción |
|---------|--------|
| `entities/Enemy.java` | Crear |
| `entities/enemies/*.java` | Crear |
| `controllers/EnemyController.java` | Crear |
| `game_states/Playing.java` | Integrar enemigos |
| `assets/enemies/` | Añadir sprites |

#### Patrón de Movimiento Básico
```java
// Patrulla simple de un lado a otro
if (movingRight) {
    x += speed;
    if (x >= rightBound) movingRight = false;
} else {
    x -= speed;
    if (x <= leftBound) movingRight = true;
}
```

---

### 2. Sistema de Daño y Vida
**Prioridad:** 🔴 Alta | **Dificultad:** Media

#### Objetivo
Implementar sistema de vida para el jugador y enemigos.

#### Implementación
```java
// Modificar: entities/Entity.java
- [ ] Añadir variable `health` y `maxHealth`
- [ ] Método `takeDamage(int amount)`
- [ ] Método `heal(int amount)`
- [ ] Invencibilidad temporal tras recibir daño
- [ ] Animación de "hit" al recibir daño
- [ ] Evento de muerte cuando health <= 0
```

#### Modificar Player.java
```java
// Añadir a Player.java
private int health = 3;
private int maxHealth = 3;
private boolean invincible = false;
private float invincibleTimer = 0;

public void takeDamage(int damage) {
    if (!invincible) {
        health -= damage;
        invincible = true;
        invincibleTimer = 2f; // 2 segundos de invencibilidad
        setAnimation(HIT);
        if (health <= 0) {
            die();
        }
    }
}
```

---

### 3. Sistema de Items/Coleccionables
**Prioridad:** 🔴 Alta | **Dificultad:** Media

#### Objetivo
Crear items que el jugador pueda recoger.

#### Implementación
```java
// Crear carpeta: items/
- [ ] Item.java (clase base)
- [ ] Coin.java (monedas/puntos)
- [ ] Heart.java (recuperar vida)
- [ ] PowerUp.java (habilidades temporales)
```

#### Estructura Propuesta
```
items/
├── Item.java           # Clase base abstracta
├── Coin.java           # Da puntos
├── Heart.java          # Recupera vida
└── SpeedBoost.java     # Velocidad temporal
```

#### Sistema de Colisión con Items
```java
// En Playing.java o ItemController.java
for (Item item : items) {
    if (player.getHitbox().overlaps(item.getHitbox())) {
        item.collect(player);
        items.remove(item);
    }
}
```

#### Cargar Items desde Tiled
- [ ] Crear capa de objetos "items" en el mapa .tmx
- [ ] Leer posiciones al cargar el mapa
- [ ] Instanciar items en las posiciones correctas

---

### 4. Sistema de Múltiples Niveles
**Prioridad:** 🟡 Media | **Dificultad:** Media

#### Objetivo
Permitir cambiar entre diferentes mapas/niveles.

#### Implementación
```java
// Modificar: controllers/MapController.java
- [ ] ArrayList de mapas disponibles
- [ ] Método loadNextLevel()
- [ ] Método loadLevel(int index)
- [ ] Detectar fin del nivel (trigger zone)
- [ ] Transición entre niveles
```

#### Archivos a Crear
| Archivo | Descripción |
|---------|-------------|
| `assets/maps/level1.tmx` | Primer nivel |
| `assets/maps/level2.tmx` | Segundo nivel |
| `assets/maps/level3.tmx` | Tercer nivel |

#### Trigger de Fin de Nivel
```java
// En Tiled: crear capa "triggers" con objeto "exit"
// En código: detectar colisión con trigger
if (player.getHitbox().overlaps(exitTrigger)) {
    mapController.loadNextLevel();
}
```

---

### 5. Mejoras del Jugador
**Prioridad:** 🟡 Media | **Dificultad:** Media

#### Objetivo
Añadir habilidades adicionales al jugador.

#### Implementación
```java
// Modificar: entities/Player.java
- [ ] Doble salto
- [ ] Wall Jump (ya hay animación)
- [ ] Dash/Sprint
- [ ] Ataque cuerpo a cuerpo
```

#### Doble Salto
```java
private int jumpsRemaining = 2;
private final int maxJumps = 2;

public void jump() {
    if (jumpsRemaining > 0) {
        verticalVelocity = speedJump;
        jumpsRemaining--;
        if (jumpsRemaining == 1) {
            setAnimation(JUMP);
        } else {
            setAnimation(DOUBLE_JUMP);
        }
    }
}

// Al tocar el suelo:
public void land() {
    jumpsRemaining = maxJumps;
}
```

---

### 6. Sistema de Spawn de Enemigos
**Prioridad:** 🟡 Media | **Dificultad:** Media

#### Objetivo
Controlar dónde y cuándo aparecen los enemigos.

#### Implementación
```java
// Crear: controllers/SpawnController.java
- [ ] Leer puntos de spawn desde Tiled
- [ ] Crear enemigos en posiciones definidas
- [ ] Respawn de enemigos (opcional)
- [ ] Limitar número de enemigos activos
```

#### En Tiled
- Crear capa de objetos "enemies"
- Añadir objetos con propiedades:
  - `type`: "slime", "flying", etc.
  - `patrolRange`: distancia de patrulla

---

### 7. Límites del Mapa y Muerte por Caída
**Prioridad:** 🟡 Media | **Dificultad:** Baja

#### Objetivo
Detectar cuando el jugador cae fuera del mapa.

#### Implementación
```java
// En Playing.java o Player.java
- [ ] Definir límite inferior del mapa
- [ ] Si player.y < limitBottom → muerte
- [ ] Reiniciar nivel o mostrar Game Over
```

---

## 📅 Cronograma Sugerido

| Día | Tarea | Dependencias |
|-----|-------|--------------|
| 1-2 | Sistema de Daño/Vida | Ninguna |
| 2-3 | Enemy básico (Slime) | Sistema de Daño |
| 3-4 | Items básicos (Coin, Heart) | Ninguna |
| 4-5 | Múltiples niveles | Items para poblar |
| 5-6 | Mejoras del jugador | Ninguna |
| 6-7 | Integración y testing | Todo lo anterior |

---

## 🔗 Dependencias con Persona A

| Tu Tarea | Depende de Persona A |
|----------|---------------------|
| Sistema de vida | HUD para mostrar vida |
| Items/Monedas | Sistema de puntuación |
| Fin de nivel | Transición visual |
| Daño al jugador | Efecto de sonido |

---

## 📚 Recursos Útiles

- [LibGDX Collision Detection](https://libgdx.com/wiki/articles/collision-detection)
- [Game AI Patterns](https://www.gamedeveloper.com/design/the-basics-of-ai-for-games)
- [Tiled Object Layers](https://doc.mapeditor.org/en/stable/manual/objects/)
- [OpenGameArt Enemies](https://opengameart.org/art-search?keys=enemy)

---

## ✅ Checklist de Verificación

Antes de dar por completada cada tarea:

- [ ] Los enemigos se mueven correctamente
- [ ] Las colisiones funcionan en ambos sentidos
- [ ] Los items desaparecen al recogerlos
- [ ] El jugador muere al perder toda la vida
- [ ] Los niveles se cargan correctamente
- [ ] Las hitbox están bien alineadas (usar debug F3)
- [ ] No hay memory leaks (dispose de texturas)
- [ ] El framerate es estable (no hay lag)

---

## 🔧 Tips de Debug

Recuerda que ya existe un modo debug (F3) que muestra:
- Hitboxes del jugador (verde)
- Colisiones del mapa (rojo)

Añade tus propios colores para:
- Hitboxes de enemigos (amarillo)
- Hitboxes de items (azul)
- Triggers/zonas (magenta)
