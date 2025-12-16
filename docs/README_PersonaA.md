# 📋 Plan de Implementación - Persona A
## 🎮 Enfoque: Interfaz de Usuario y Sistema de Audio

---

## 📌 Resumen del Rol

**Persona A** se enfoca en la experiencia del usuario: menús, HUD, sistema de audio, y estados del juego. Tu trabajo hará que el juego se sienta completo y pulido.

---

## 🎯 Tareas Asignadas

### 1. Sistema de Menú Principal (`Menu.java`)
**Prioridad:** 🔴 Alta | **Dificultad:** Media

#### Objetivo
Crear un menú principal funcional con botones interactivos.

#### Implementación
```java
// Ubicación: game_states/Menu.java
- [ ] Crear fondo del menú (reutilizar Background o crear uno nuevo)
- [ ] Añadir título del juego
- [ ] Botón "Jugar" → Cambia a GameState.PLAYING
- [ ] Botón "Opciones" → Cambia a estado de opciones (opcional)
- [ ] Botón "Salir" → Gdx.app.exit()
```

#### Archivos a Modificar/Crear
| Archivo | Acción |
|---------|--------|
| `game_states/Menu.java` | Modificar |
| `assets/ui/button_play.png` | Crear |
| `assets/ui/button_exit.png` | Crear |
| `manager/Assets.java` | Añadir assets del menú |

#### Recursos LibGDX
- `Stage` y `Table` para layout
- `TextButton` o `ImageButton` para botones
- `Skin` para estilos (opcional)

---

### 2. HUD (Heads-Up Display)
**Prioridad:** 🔴 Alta | **Dificultad:** Media

#### Objetivo
Mostrar información vital del jugador durante el gameplay.

#### Implementación
```java
// Crear nueva clase: ui/HUD.java
- [ ] Barra de vida del jugador
- [ ] Contador de monedas/puntos
- [ ] Indicador de nivel actual
- [ ] Posible minimapa (avanzado)
```

#### Estructura Propuesta
```
ui/
├── HUD.java           # Controlador principal del HUD
├── HealthBar.java     # Barra de vida
└── ScoreDisplay.java  # Contador de puntos
```

#### Archivos a Modificar
| Archivo | Acción |
|---------|--------|
| `ui/HUD.java` | Crear |
| `game_states/Playing.java` | Integrar HUD |
| `entities/Player.java` | Añadir sistema de vida |

---

### 3. Sistema de Pausa (`Paused.java`)
**Prioridad:** 🟡 Media | **Dificultad:** Baja

#### Objetivo
Implementar pantalla de pausa funcional.

#### Implementación
```java
// Ubicación: game_states/Paused.java
- [ ] Detectar tecla ESC o P para pausar
- [ ] Mostrar overlay semi-transparente
- [ ] Botón "Continuar" → Volver a PLAYING
- [ ] Botón "Menú Principal" → Ir a MENU
- [ ] Botón "Salir" → Cerrar juego
```

#### Archivos a Modificar
| Archivo | Acción |
|---------|--------|
| `game_states/Paused.java` | Modificar |
| `game_states/Playing.java` | Añadir lógica de pausa |

---

### 4. Pantalla de Game Over (`GameOver.java`)
**Prioridad:** 🟡 Media | **Dificultad:** Baja

#### Objetivo
Mostrar pantalla cuando el jugador pierde.

#### Implementación
```java
// Ubicación: game_states/GameOver.java
- [ ] Mostrar mensaje "Game Over"
- [ ] Mostrar puntuación final
- [ ] Botón "Reintentar" → Reiniciar nivel
- [ ] Botón "Menú Principal" → Ir a MENU
```

---

### 5. Sistema de Audio
**Prioridad:** 🟡 Media | **Dificultad:** Media

#### Objetivo
Añadir música de fondo y efectos de sonido.

#### Implementación
```java
// Crear nueva clase: manager/AudioManager.java
- [ ] Cargar y reproducir música de fondo (loop)
- [ ] Efectos de sonido para:
      - Salto del jugador
      - Recoger objetos
      - Daño recibido
      - Game Over
- [ ] Control de volumen
- [ ] Mute/Unmute
```

#### Archivos a Crear
| Archivo | Descripción |
|---------|-------------|
| `manager/AudioManager.java` | Gestión centralizada de audio |
| `assets/audio/music/` | Carpeta para música |
| `assets/audio/sfx/` | Carpeta para efectos |

#### Ejemplo de Uso
```java
// En Player.java al saltar:
AudioManager.playSound("jump");

// En Playing.java al iniciar:
AudioManager.playMusic("level1");
```

---

### 6. Sistema de Puntuación
**Prioridad:** 🟡 Media | **Dificultad:** Baja

#### Objetivo
Implementar sistema de puntos y monedas.

#### Implementación
```java
// Crear nueva clase: manager/ScoreManager.java
- [ ] Contador de puntos
- [ ] Contador de monedas
- [ ] Guardar high score (preferencias)
- [ ] Mostrar en HUD
```

---

## 📅 Cronograma Sugerido

| Día | Tarea | Dependencias |
|-----|-------|--------------|
| 1-2 | Sistema de Menú | Ninguna |
| 2-3 | HUD básico | Ninguna |
| 3-4 | Sistema de Pausa | Menú completado |
| 4-5 | Game Over | HUD + Pausa |
| 5-6 | AudioManager | Ninguna |
| 6-7 | Integración y testing | Todo lo anterior |

---

## 🔗 Dependencias con Persona B

| Tu Tarea | Depende de Persona B |
|----------|---------------------|
| Mostrar vida en HUD | Sistema de daño del Player |
| Efecto de sonido al recoger | Sistema de items/coleccionables |
| Mostrar nivel en HUD | Sistema de múltiples niveles |

---

## 📚 Recursos Útiles

- [LibGDX Scene2D UI](https://libgdx.com/wiki/graphics/2d/scene2d/scene2d-ui)
- [LibGDX Audio](https://libgdx.com/wiki/audio/audio)
- [Free Game Music](https://freesound.org/)
- [OpenGameArt](https://opengameart.org/)

---

## ✅ Checklist de Verificación

Antes de dar por completada cada tarea:

- [ ] El código compila sin errores
- [ ] Las transiciones entre estados funcionan
- [ ] Los assets están correctamente cargados
- [ ] El audio no causa lag ni errores
- [ ] El HUD está siempre visible durante el gameplay
- [ ] Los botones responden al click
- [ ] Se dispone correctamente de los recursos (`dispose()`)
