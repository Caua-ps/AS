# LOBs — LotsOfBalls

Segundo trabalho prático de Arquitetura de Software (FCUP).

## Estrutura

```
src/
├── lob/
│   ├── LotsOfBallsException.java       # exceção verificada do projeto
│   ├── physics/                         # motor de física 2D
│   │   ├── Vector2D.java
│   │   ├── shapes/                      # Circle, Rectangle, Shape (sealed), Appearance
│   │   ├── engine/                      # PhysicsWorld, SimpleCollisionManager
│   │   ├── forces/                      # Strategy: Gravity, Friction, NoForce
│   │   ├── events/                      # Observer: CollisionEvent, EscapeEvent
│   │   └── actions/                     # Command/deferred mutations no PhysicsWorld
│   ├── quadtree/                        # Composite: Trie / NodeTrie / LeafTrie
│   ├── gaming/                          # GameAnimation + Singletons + Reflection
│   │   └── games/                       # Cannon, Dribbling, Golf, Arkanoid
│   └── guis/                            # GUIs Swing fornecidas
test/                                    # testes JUnit 5
```

## Padrões de desenho

| Padrão            | Onde                                                                 |
|-------------------|----------------------------------------------------------------------|
| Strategy          | `forces/ForceStrategy` (Gravity / Friction / NoForce)                |
| Observer          | `events/PhysicsObserver` + `PhysicsSubject`; `add{Collision,Escape}Listener` em `PhysicsWorld` |
| Singleton         | `Players.getInstance()` e `LeaderboardManager.getInstance()`         |
| Composite         | `Trie` ← `LeafTrie` / `NodeTrie`                                     |
| Factory + Reflection | `ReflectGameFactory`                                              |
| Template Method   | `GameAnimation` (define `loop`, delega `step`/`resetGame`)            |
| Command (deferred)| `actions/PendingActions` (mutações adiadas durante `update`)         |

## Como compilar e testar

1. Importar este projeto no IntelliJ IDEA.
2. Marcar `test/` como **Test Resources Root** (FAQ).
3. Adicionar **JUnit 5.8+** ao classpath.
4. Garantir que o ficheiro `test/lob/quad/locais.txt` é incluído como recurso
   de teste (necessário para `PointQuadtreeTest`).
5. Executar todos os testes — devem passar todos.

## Jogos implementados

Cada jogo regista listeners de colisão e de escape no `PhysicsWorld`, em vez
de fazer polling ao mundo, e expõe estado de jogo (`isWon`, `isLost`, etc.):

- **CannonPractice** — gravidade + alvo fixo.
- **DribblingMaster** — gravidade + chão.
- **MicroGolf** — atrito horizontal + buraco; só permite golpe quando bola parada.
- **ArkanoidKnockoff** — sem força; remove cada tijolo via colisão; deteta vitória e derrota.
