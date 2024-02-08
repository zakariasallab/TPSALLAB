package Lybrinth;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Represents a basic object in the game.
 * This class serves as a base for various entities in the game,
 * providing fundamental properties like position and dimensions.
 * It can also handle an associated image for graphical representation.
 */
public class Things {
    protected double x, y; // X and Y coordinates
    protected double width, height; // Width and height of the object
    protected Image image; // Image representing the object visually
    
    /**
     * Constructs a new Lybrinth.Things object with specified dimensions and position.
     *
     * @param x The X-coordinate of the object.
     * @param y The Y-coordinate of the object.
     * @param width The width of the object.
     * @param height The height of the object.
     */
    public Things(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Constructs a new Lybrinth.Things object with an associated image.
     * The dimensions of the object are derived from the dimensions of the image.
     *
     * @param x The X-coordinate of the object.
     * @param y The Y-coordinate of the object.
     * @param image The image to be associated with this object.
     */
    public Things(int x, int y, Image image) {
        ImageObserver observer = (img, infoflags, x1, y1, width, height) -> false;
        this.x = x;
        this.y = y;
        this.width = image.getWidth(observer);
        this.height = image.getHeight(observer);
        this.image = image;
    }
    
    /**
     * Draws the object using its associated image.
     * This method is called to render the object on the screen.
     *
     * @param g The graphics context used for drawing.
     */
    public void draw(Graphics g){
        g.drawImage(image, (int) x, (int) y, null);
    }
}
