package Lybrinth;

import java.awt.*;

/**
 * Represents a trap in the game, which is a type of SolidThing that can inflict damage.
 * When triggered, it decreases the health of the hero (The hero becomes invincible for 2 seconds, check {@link Hero} ).
 */
public class Trap extends SolidThings {
    private final int damage; // The amount of damage this trap can inflict
    
    /**
     * Constructs a Lybrinth.Trap with specified position, image, and damage.
     *
     * @param x The X-coordinate of the trap.
     * @param y The Y-coordinate of the trap.
     * @param image The image associated with the trap.
     * @param damage The amount of damage this trap can inflict on the hero.
     */
    public Trap(int x, int y, Image image, int damage) {
        super(x, y, image);
        this.damage = damage;
    }
    
    /**
     * Triggers the trap, causing damage to the hero.
     * This method is called when the hero encounters the trap.
     *
     * @param hero The hero who has encountered the trap.
     */
    public void trigger(Hero hero) {
        hero.decreaseHealth(damage);
    }
}
