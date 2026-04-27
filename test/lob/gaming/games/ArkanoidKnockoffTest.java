package lob.gaming.games;

import lob.physics.shapes.Circle;
import lob.physics.shapes.Rectangle;
import lob.physics.shapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArkanoidKnockoffTest {

    private ArkanoidKnockoff game;

    @BeforeEach
    void setUp() { game = new ArkanoidKnockoff(); }

    @Test
    void hasExpectedName() { assertEquals("Arkanoid Knockoff", game.getName()); }

    @Test
    void hasInstructions() { assertNotNull(game.getInstructions()); }

    @Test
    void resetCreatesBricksPaddleAndBall() {
        long circles = 0, rects = 0;
        for (Shape s : game.getShapes()) {
            if (s instanceof Circle) circles++;
            else if (s instanceof Rectangle) rects++;
        }
        assertEquals(1, circles);
        // 3 walls + paddle + 32 bricks = 36
        assertEquals(36, rects);
        assertEquals(32, game.getBricksLeft());
    }

    @Test
    void notLaunchedAtStart() { assertFalse(game.isLaunched()); }

    @Test
    void notWonOrLostAtStart() {
        assertFalse(game.isWon());
        assertFalse(game.isLost());
    }

    @Test
    void startGameLaunchesBall() {
        game.startGame();
        assertTrue(game.isLaunched());
    }

    @Test
    void movePaddleDoesNotThrowWhenOutOfBounds() {
        assertDoesNotThrow(() -> game.movePaddle(-1000));
        assertDoesNotThrow(() -> game.movePaddle(10_000));
    }
}
