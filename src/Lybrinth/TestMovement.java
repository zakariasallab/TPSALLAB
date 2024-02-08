package Lybrinth;

/**
 * Test class for demonstrating the movement functionality of the Lybrinth.Hero class within a Lybrinth.Dungeon.
 * This class tests the Lybrinth.Hero's ability to move in the dungeon, including collision handling with walls.
 */
public class TestMovement {
    
    /**
     * Main method to execute movement tests.
     *
     * @param args Command line arguments (not used in this test).
     */
    public static void main(String[] args) {
        // Creating a dungeon for the movement test
        Dungeon dungeon = new Dungeon(10, 10);
        
        // Obtaining an instance of Lybrinth.Hero and testing movement
        Hero hero = Hero.getInstance(0, 0, 50, 50);
        
        // Attempting to move the hero to an open space
        hero.moveIfPossible(100, 100, dungeon); // Towards an open space
        
        // Attempting to move the hero towards a wall
        hero.moveIfPossible(10, 10, dungeon); // Towards a wall
        
        // Printing the final position of the hero after movement attempts
        System.out.println("Position du héros: x=" + hero.x + ", y=" + hero.y); // Expected to print "Position du héros: x=100, y=100"
    }
}
