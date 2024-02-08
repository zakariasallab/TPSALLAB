package Lybrinth;

import java.awt.*;
/**
 * Represents a solid object in the game.
 * This class extends {@link Things} to include a hitbox, enabling collision detection.
 * Lybrinth.SolidThings can be used for objects that the player or other entities cannot pass through.
 */
public class SolidThings extends Things {
    protected HitBox hitBox; // Lybrinth.HitBox for handling collision detection
    
    /**
     * Constructs a new Lybrinth.SolidThings object with specified dimensions and position.
     * Initializes a hitbox for the object.
     *
     * @param x The X-coordinate of the solid object.
     * @param y The Y-coordinate of the solid object.
     * @param width The width of the solid object.
     * @param height The height of the solid object.
     */
    public SolidThings(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.hitBox = new HitBox(x, y, width, height);
    }
    
    /**
     * Constructs a new Lybrinth.SolidThings object with an associated image.
     * Initializes a hitbox based on the image dimensions.
     *
     * @param x The X-coordinate of the solid object.
     * @param y The Y-coordinate of the solid object.
     * @param image The image to be associated with this solid object.
     */
    public SolidThings(int x, int y, Image image) {
        super(x, y, image);
        this.hitBox = new HitBox(x, y, width, height);
    }
    
    /**
     * Retrieves the hitbox associated with this solid object.
     *
     * @return The hitbox of the solid object.
     */
    public HitBox getHitBox() {
        return this.hitBox;
    }
}
