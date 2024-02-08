package Lybrinth;

/**
 * Test class for demonstrating the functionality of the Lybrinth.HitBox class.
 * This class tests collision detection between different hitboxes and also displays a dungeon representation.
 */
public class TestHitBox {
    
    /**
     * Main method to execute hitbox tests.
     *
     * @param args Command line arguments (not used in this test).
     */
    public static void main(String[] args) {
        // Creating hitboxes for testing
        HitBox box1 = new HitBox(10, 20, 100, 100);
        HitBox box2 = new HitBox(50, 50, 50, 50);
        HitBox box3 = new HitBox(200, 200, 50, 50);
        
        // Creating and displaying a dungeon
        Dungeon dungeon = new Dungeon(30, 30);
        dungeon.displayDungeonInConsole(box1);
        
        // Testing and printing the result of collision detection between hitboxes
        System.out.println("Box1 intersects Box2: " + box1.intersect(box2)); // Expected to be true
        System.out.println("Box1 intersects Box3: " + box1.intersect(box3)); // Expected to be false
    }
}
