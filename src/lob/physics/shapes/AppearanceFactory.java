package lob.physics.shapes;

/**
 * Factory that maps a logical shape name (e.g. {@code "ball"},
 * {@code "wall"}, {@code "target"}) to a concrete {@link Appearance}.
 *
 * <p>Design pattern: <b>Factory Method</b>. Lets the physics engine stay
 * appearance-agnostic: it only knows shape names, the GUI layer plugs in
 * a real factory that produces the actual visual.
 */
public interface AppearanceFactory {

    /**
     * Returns the appearance associated with a logical name.
     *
     * @param shapeName logical name of the shape (e.g. {@code "ball"}).
     * @return the matching {@link Appearance} &mdash; never {@code null}.
     */
    Appearance getAppearance(String shapeName);
}
