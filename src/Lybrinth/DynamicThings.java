package Lybrinth;

import java.awt.*;
/**
 * Represents dynamic entities in the game world that can move and interact with their environment.
 * This class extends {@link AnimatedThings} to include movement capabilities and collision detection.
 */
public class DynamicThings extends AnimatedThings {
    /**
     * Constructs a new Lybrinth.DynamicThings object with specified dimensions and position.
     *
     * @param x The X-coordinate of the dynamic object.
     * @param y The Y-coordinate of the dynamic object.
     * @param width The width of the dynamic object.
     * @param height The height of the dynamic object.
     */
    public DynamicThings(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
    /**
     * Constructs a new Lybrinth.DynamicThings object with an associated image.
     *
     * @param x The X-coordinate of the dynamic object.
     * @param y The Y-coordinate of the dynamic object.
     * @param image The image to be associated with this dynamic object.
     */
    public DynamicThings(int x, int y, Image image) {
        super(x, y, image);
    }
    
    /**
     * Moves the object within the dungeon if possible.
     * The movement is subject to collision detection with solid objects in the dungeon.
     *
     * @param dx The displacement along the x-axis.
     * @param dy The displacement along the y-axis.
     * @param dungeon The dungeon in which the object resides.
     */
    public void moveIfPossible(double dx, double dy, Dungeon dungeon) {
        // Method implementation
    }
    
    /**
     * Sets a new image for the dynamic object.
     *
     * @param image The new image to be set for the object.
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
