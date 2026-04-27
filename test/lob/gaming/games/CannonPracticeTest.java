package lob.gaming.games;

import lob.physics.Vector2D;
import lob.physics.shapes.Circle;
import lob.physics.shapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CannonPracticeTest {

    private CannonPractice game;

    @BeforeEach
    void setUp() { game = new CannonPractice(); }

    @Test
    void hasExpectedName() { assertEquals("Cannon Practice", game.getName()); }

    @Test
    void hasInstructions() { assertNotNull(game.getInstructions()); }

    @Test
    void hasReasonableDimensions() {
        assertTrue(game.getWidth()  > 0);
        assertTrue(game.getHeight() > 0);
    }

    @Test
    void resetGameClearsCounters() {
        game.fire(new Vector2D(100, 100));
        assertEquals(1, game.getShots());
        game.resetGame();
        assertEquals(0, game.getShots());
        assertEquals(0, game.getHits());
    }

    @Test
    void fireCreatesACannonball() {
        assertEquals(0, countCircles());
        game.fire(new Vector2D(300, 100));
        assertEquals(1, countCircles());
    }

    @Test
    void fireTwiceReplacesPreviousBall() {
        game.fire(new Vector2D(100, 100));
        game.fire(new Vector2D(400, 100));
        assertEquals(2, game.getShots());
        assertEquals(1, countCircles());
    }

    @Test
    void fireAimDeterminesDirection() {
        game.fire(new Vector2D(500, 100));
        Circle ball = (Circle) findCircle();
        assertNotNull(ball);
        Vector2D v = ball.getVelocity();
        assertTrue(v.x() > 0);
        assertTrue(v.y() < 0);
    }

    private long countCircles() {
        long n = 0;
        for (Shape s : game.getShapes()) if (s instanceof Circle) n++;
        return n;
    }

    private Shape findCircle() {
        for (Shape s : game.getShapes()) if (s instanceof Circle) return s;
        return null;
    }
}
