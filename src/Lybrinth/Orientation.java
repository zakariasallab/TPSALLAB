package Lybrinth;

/**
 * Enumerates the possible orientations for entities in the game.
 * Used to represent directional states such as facing up, down, left, or right.
 */
public enum Orientation {
    DOWN(0),
    LEFT(1),
    UP(2),
    RIGHT(3);
    
    private final int i; // Numeric representation of the orientation
    
    /**
     * Constructs an Lybrinth.Orientation with a specified integer value.
     *
     * @param i The integer value associated with the orientation.
     */
    Orientation(int i) {
        this.i = i;
    }
    
    /**
     * Gets the integer value associated with the orientation.
     *
     * @return The integer value of the orientation.
     */
    public int getI() {
        return i;
    }
}
