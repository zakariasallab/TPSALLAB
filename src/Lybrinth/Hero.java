package Lybrinth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * The `Lybrinth.Hero` class represents the player's character in the game. It is a singleton class
 * responsible for managing the hero's attributes, movement, health, and interactions with
 * other game objects.
 *
 * The hero can move in different orientations, has a health attribute, and can interact with
 * traps, food, and the exit in the game's dungeon.
 */
public final class Hero extends DynamicThings {
    private int health;
    private static volatile Hero instance = null;
    private Orientation orientation = Orientation.DOWN;
    private boolean isWalking = false;
    private static final int FRAME_DURATION = 125;
    private static final int NUM_FRAMES = 10;
    private long lastCollisionTime;
    private static final int INVINCIBILITY_DURATION = 2000; // 2 seconds in milliseconds
    private boolean isInvincible;
    private int level = 1;
    public final int MAX_LEVEL = 3;
    private boolean hasSucceeded = false;
    
    /**
     * Private constructor for the Lybrinth.Hero class. It initializes the hero's position and
     * loads its image from a file.
     *
     * @param x      The initial x-coordinate of the hero.
     * @param y      The initial y-coordinate of the hero.
     * @param width  The width of the hero's bounding box.
     * @param height The height of the hero's bounding box.
     */
    private Hero(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.health = 100;
        this.lastCollisionTime = 0;
        this.isInvincible = false;
        try{this.setImage(ImageIO.read(new File("assets/images/heroTileSet.png")));}
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Get the instance of the Lybrinth.Hero class using the singleton pattern.
     *
     * @param x      The initial x-coordinate of the hero.
     * @param y      The initial y-coordinate of the hero.
     * @param width  The width of the hero's bounding box.
     * @param height The height of the hero's bounding box.
     * @return The singleton instance of the Lybrinth.Hero class.
     */
    public static Hero getInstance(double x, double y, double width, double height) {
        if (instance == null) {
            synchronized (Hero.class) {
                if (instance == null) {
                    instance = new Hero(x, y, width, height);
                }
            }
        }
        return instance;
    }
    
    /**
     * Set the orientation of the hero, which determines the direction the hero is facing.
     *
     * @param orientation The new orientation of the hero.
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    /**
     * Set whether the hero is currently walking or not.
     *
     * @param walking `true` if the hero is walking, `false` otherwise.
     */
    public void setWalking(boolean walking) {
        isWalking = walking;
    }
    
    /**
     * Check if the hero is currently walking.
     *
     * @return `true` if the hero is walking, `false` otherwise.
     */
    public boolean isWalking() {
        return isWalking;
    }
    
    /**
     * Get the current orientation of the hero.
     *
     * @return The current orientation of the hero.
     */
    public Orientation getOrientation() {
        return orientation;
    }
    
    /**
     * Draw the hero on the screen using the provided graphics context.
     *
     * @param g The graphics context on which to draw the hero.
     */
    @Override
    public void draw(Graphics g) {
        int attitude = orientation.getI();
        long currentTime = System.currentTimeMillis();
        
        // Calculate the current frame index
        int index = isWalking ? (int) ((currentTime / FRAME_DURATION) % NUM_FRAMES) : 0;
        
        // Coordinates on the sprite sheet (assuming the sprite sheet layout is horizontal for each animation)
        int sx1 = index * 96;
        int sx2 = sx1 + 96;
        int sy1 = 100 * attitude;
        int sy2 = sy1 + 100;
        
        // Coordinates on the screen
        int dx1 = (int) x;
        int dx2 = dx1 + 48;
        int dy1 = (int) y;
        int dy2 = dy1 + 52;
        
        // Draw the current frame
        g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }
    
    /**
     * Get the current health of the hero.
     *
     * @return The current health of the hero.
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Decrease the health of the hero by the specified amount.
     * Used when the hero passes into a trap.
     *
     * @param amount The amount by which to decrease the hero's health.
     */
    public void decreaseHealth(int amount) {
        if (this.health >= 10) {
            this.health -= amount;
        }
        if (this.health == 0) {
            new GameOverScreen().setVisible(true);
        }
    }

    /**
     * Move the hero if possible based on the specified changes in x and y coordinates.
     * Check if the hero gets into a trap or picks up food.
     *
     * @param dx      The change in the x-coordinate.
     * @param dy      The change in the y-coordinate.
     * @param dungeon The game's dungeon where collisions are checked.
     */
    @Override
    public void moveIfPossible(double dx, double dy, Dungeon dungeon) {
        boolean isMovePossible = true;
        this.getHitBox().move(dx, dy);
        long currentTime = System.currentTimeMillis();
        // Check if the invincibility period has expired
        if (currentTime - lastCollisionTime > INVINCIBILITY_DURATION) {
            isInvincible = false;
        }
        for (Things thing : dungeon.getRenderList()) {
            if (thing instanceof Trap) {
                if(((Trap) thing).getHitBox().intersect(this.getHitBox())){
                    if (!isInvincible) {
                        ((Trap) thing).trigger(this);
                        isInvincible = true;
                        lastCollisionTime = currentTime;
                    }
                    break;
                }
            }


            if (thing instanceof SolidThings) {
                if(((SolidThings) thing).getHitBox().intersect(this.getHitBox())){
                    isMovePossible=false;
                    break;
                }
            }
        }
        if (isMovePossible) {
            this.x += dx;
            this.y += dy;
        } else {
            this.getHitBox().move(-dx, -dy);
        }
    }
    
    /**
     * Check if the hero has reached the exit in the dungeon.
     *
     * @param dungeon The game's dungeon.
     * @return `true` if the hero has reached the exit, `false` otherwise.
     */
    public boolean hasReachedExit(Dungeon dungeon) {
        SolidThings exitPosition = dungeon.getExitPosition();
        this.getHitBox().move(10, 0);
        boolean hasReachedExit = this.getHitBox().intersect(exitPosition.getHitBox());
        if (hasReachedExit) {
            return true;
        } else {
            this.getHitBox().move(-10, 0);
            return false;
        }
    }
    
    /**
     * Move the hero to the next level of the game if possible.
     * This method increments the hero's level.
     */
    public void nextLevel() {
        if (level < MAX_LEVEL) {
            level += 1;
        }
    }
    
    /**
     * Get the current level of the hero.
     *
     * @return The current level of the hero.
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Set whether the hero has succeeded in passing all the levels.
     *
     * @param hasSucceeded `true` if the hero has succeeded, `false` otherwise.
     */
    public void setHasSucceeded(boolean hasSucceeded) {
        this.hasSucceeded = hasSucceeded;
    }
    
    /**
     * Check if the hero has succeeded in passing all the levels.
     *
     * @return `true` if the hero has succeeded, `false` otherwise.
     */
    public boolean getHasSucceeded() {
        return hasSucceeded;
    }
}
