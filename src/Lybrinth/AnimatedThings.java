package Lybrinth;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents animated objects in the game world.
 * This class extends {@link SolidThings} and adds animation capabilities.
 * It handles animations through an array of image frames and updates the current frame based on time.
 */
public class AnimatedThings extends SolidThings {
    private static final int FRAME_DURATION = 125; // Duration of each frame in milliseconds
    private static final int NUM_FRAMES = 3; // Number of frames in the animation
    private Image[] frames; // Array of images for the animation frames
    private int currentFrameIndex; // Index of the current frame in the animation
    
    /**
     * Constructs a new Lybrinth.AnimatedThings object with specified dimensions and position.
     *
     * @param x The X-coordinate of the animated object.
     * @param y The Y-coordinate of the animated object.
     * @param width The width of the animated object.
     * @param height The height of the animated object.
     */
    public AnimatedThings(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.frames = new Image[NUM_FRAMES];
        this.currentFrameIndex = 0;
    }
    
    /**
     * Constructs a new Lybrinth.AnimatedThings object with an associated image.
     * Initializes the frames array with the given image.
     *
     * @param x The X-coordinate of the animated object.
     * @param y The Y-coordinate of the animated object.
     * @param image The image to be associated with this animated object.
     */
    public AnimatedThings(int x, int y, Image image) {
        super(x, y, image);
        this.frames = new BufferedImage[NUM_FRAMES];
        this.currentFrameIndex = 0;
    }
    
    /**
     * Updates the current frame index based on the elapsed time to create an animation effect.
     */
    public void updateFrame() {
        long currentTime = System.currentTimeMillis();
        this.currentFrameIndex = (int) ((currentTime / FRAME_DURATION) % NUM_FRAMES);
    }
    
    /**
     * Draws the current frame of the animation.
     *
     * @param g The graphics context used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        updateFrame();
        Image currentFrame = frames[currentFrameIndex];
        g.drawImage(currentFrame, (int) x, (int) y, null);
    }
    
    /**
     * Sets the array of frames to be used for the animation.
     *
     * @param frames An array of {@link Image} objects representing the frames of the animation.
     */
    public void setFrames(Image[] frames) {
        this.frames = frames;
    }
}
