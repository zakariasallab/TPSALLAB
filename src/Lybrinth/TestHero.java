package Lybrinth;

/**
 * Test class for demonstrating the singleton pattern implementation in the Lybrinth.Hero class.
 * This class tests and verifies that only one instance of Lybrinth.Hero can be created.
 */
public class TestHero {
    
    /**
     * Main method to execute the test.
     *
     * @param args Command line arguments (not used in this test).
     */
    public static void main(String[] args) {
        // Attempting to create two instances of Lybrinth.Hero
        Hero hero1 = Hero.getInstance(0, 0, 50, 50);
        Hero hero2 = Hero.getInstance(10, 10, 50, 50);
        
        // Printing the result of the comparison to verify if both references point to the same instance
        System.out.println(hero1 == hero2); // Expected to print 'true' if Lybrinth.Hero is correctly implemented as a singleton
    }
}
