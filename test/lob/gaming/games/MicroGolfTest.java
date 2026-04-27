package lob.gaming.games;

import lob.physics.Vector2D;
import lob.physics.shapes.Circle;
import lob.physics.shapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicroGolfTest {

    private MicroGolf game;

    @BeforeEach
    void setUp() { game = new MicroGolf(); }

    @Test
    void hasExpectedName() { assertEquals("Micro Golf", game.getName()); }

    @Test
    void hasInstructions() { assertNotNull(game.getInstructions()); }

    @Test
    void resetCreatesABall() {
        long n = 0;
        for (Shape s : game.getShapes()) if (s instanceof Circle) n++;
        assertEquals(1, n);
    }

    @Test
    void initialStrokesIsZero() { assertEquals(0, game.getStrokes()); }

    @Test
    void notWonAtStart() { assertFalse(game.isWon()); }

    @Test
    void strikeIncrementsStrokes() {
        Circle before = findCircle();
        game.strikeBall(new Vector2D(before.getPosition().x() + 100,
                                     before.getPosition().y()));
        assertEquals(1, game.getStrokes());
    }

    @Test
    void strikeAtSamePointIsIgnored() {
        Circle before = findCircle();
        game.strikeBall(before.getPosition());
        assertEquals(0, game.getStrokes());
    }

    private Circle findCircle() {
        for (Shape s : game.getShapes()) if (s instanceof Circle c) return c;
        return null;
    }
}
