package lob.gaming.games;

import lob.physics.Vector2D;
import lob.physics.shapes.Circle;
import lob.physics.shapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DribblingMasterTest {

    private DribblingMaster game;

    @BeforeEach
    void setUp() { game = new DribblingMaster(); }

    @Test
    void hasExpectedName() { assertEquals("Dribbling Master", game.getName()); }

    @Test
    void hasInstructions() { assertNotNull(game.getInstructions()); }

    @Test
    void resetCreatesABall() {
        long n = 0;
        for (Shape s : game.getShapes()) if (s instanceof Circle) n++;
        assertEquals(1, n);
    }

    @Test
    void notWonOrLostAtStart() {
        assertFalse(game.isWon());
        assertFalse(game.isLost());
    }

    @Test
    void strikeNearBallChangesVelocity() {
        Circle before = findCircle();
        // click directly under the ball at distance ~30 (within strike radius)
        Vector2D click = new Vector2D(before.getPosition().x(),
                                      before.getPosition().y() + 30);
        game.strikeBall(click);
        Circle after = findCircle();
        assertNotEquals(before.getVelocity(), after.getVelocity());
        // click below ball → impulse pushes it up (negative y)
        assertTrue(after.getVelocity().y() < before.getVelocity().y());
    }

    @Test
    void strikeFarFromBallIsIgnored() {
        Circle before = findCircle();
        // click far away (1000 px from ball)
        game.strikeBall(new Vector2D(2000, 2000));
        Circle after = findCircle();
        assertEquals(before.getVelocity(), after.getVelocity());
    }

    private Circle findCircle() {
        for (Shape s : game.getShapes()) if (s instanceof Circle c) return c;
        return null;
    }
}
