package Lybrinth;

/**
 * Represents a hitbox used for collision detection.
 * The hitbox is defined by its position (x, y) and dimensions (width, height).
 */
public class HitBox {
    private double x, y; // The x and y coordinates of the hitbox
    private final double width, height; // The width and height of the hitbox
    
    /**
     * Constructs a default Lybrinth.HitBox with zero width, height, and at position (0,0).
     */
    public HitBox() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }
    
    /**
     * Constructs a Lybrinth.HitBox with specified position and dimensions.
     *
     * @param x The x coordinate of the hitbox.
     * @param y The y coordinate of the hitbox.
     * @param width The width of the hitbox.
     * @param height The height of the hitbox.
     */
    public HitBox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Gets the x coordinate of the hitbox.
     *
     * @return The x coordinate.
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Gets the y coordinate of the hitbox.
     *
     * @return The y coordinate.
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Checks if this hitbox intersects with another hitbox.
     *
     * @param anotherHitBox The other hitbox to check intersection with.
     * @return {@code true} if the hitboxes intersect; {@code false} otherwise.
     */
    public boolean intersect(HitBox anotherHitBox) {
        boolean xOverlap = (this.x + this.width > anotherHitBox.x) && (anotherHitBox.x + anotherHitBox.width > this.x);
        boolean yOverlap = (this.y + this.height > anotherHitBox.y) && (anotherHitBox.y + anotherHitBox.height > this.y);
        
        return xOverlap && yOverlap;
    }
    
    /**
     * Moves the hitbox by a specified amount along the x and y axes.
     *
     * @param dx The amount to move along the x axis.
     * @param dy The amount to move along the y axis.
     */
    public void move(double dx, double dy){
        x= x + dx;
        y= y + dy;
    }
    
    /**
     * Sets the x coordinate of the hitbox.
     *
     * @param x The new x coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * Sets the y coordinate of the hitbox.
     *
     * @param y The new y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }
}
