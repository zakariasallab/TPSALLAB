package Lybrinth;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Represents the dungeon in the game, holding the layout, entities, and other elements.
 * This class manages the map of the dungeon, along with rendering and interaction of various elements.
 */
public class Dungeon {
    private final char[][] map; // The map of the dungeon
    private final int height; // Height of the dungeon
    private final int width; // Width of the dungeon
    private final TileManager tileManager; // Manager for handling tiles
    private final ArrayList<Things> renderList = new ArrayList<>(); // List of renderable objects in the dungeon
    private SolidThings exitPosition; // Position of the exit in the dungeon
    
    /**
     * Constructs a Lybrinth.Dungeon with a specified height and width and fills it with a default layout.
     *
     * @param height The height of the dungeon.
     * @param width The width of the dungeon.
     */
    public Dungeon(int height, int width) {
        this.height = height;
        this.width = width;
        this.tileManager = new TileManager(32,32);
        this.map = new char[width][height];
        fillDefaultMap();
        respawnListOfThings();
    }
    
    /**
     * Constructs a Lybrinth.Dungeon from a specified file, using a Lybrinth.TileManager for rendering.
     *
     * @param fileName The name of the file containing the dungeon layout.
     * @param tileManager The Lybrinth.TileManager used to manage tiles in the dungeon.
     */
    public Dungeon(String fileName, TileManager tileManager) {
        this.tileManager = tileManager;
        int tempWidth = 0;
        int tempHeight = 0;
        boolean fileReadSuccessfully = true;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tempHeight++;
                tempWidth = Math.max(line.length(), tempWidth);
            }
        } catch (IOException e) {
            System.out.println("Error reading the level file: " + e.getMessage());
            fileReadSuccessfully = false;
        }
        
        if (!fileReadSuccessfully) {
            tempWidth = 45;
            tempHeight = 25;
        }
        
        this.width = tempWidth;
        this.height = tempHeight;
        this.map = new char[this.width][this.height];
        
        if (fileReadSuccessfully) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int y = 0;
                while ((line = reader.readLine()) != null && y < this.height) {
                    for (int x = 0; x < line.length() && x < this.width; x++) {
                        this.map[x][y] = line.charAt(x);
                    }
                    y++;
                }
            } catch (IOException e) {
                System.out.println("Error reading the level file: " + e.getMessage());
                fillDefaultMap();
            }
        } else {
            fillDefaultMap();
        }
        respawnListOfThings();
    }
    
    /**
     * Fills the map with a default layout. Used when loading from a file fails or for default initialization.
     */
    private void fillDefaultMap() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1) {
                    this.map[x][y] = 'W';
                } else {
                    this.map[x][y] = ' ';
                }
            }
        }
    }
    
    /**
     * Displays the dungeon layout in the console, including the hero's position.
     *
     * @param hero The hero's hitbox to indicate the hero's position on the map.
     */
    public void displayDungeonInConsole(HitBox hero) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == (int) hero.getY() && j == (int) hero.getX()) {
                    System.out.print('H');
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
    }
    
    
    /**
     * Respawns and updates the list of renderable things in the dungeon based on the current map layout.
     */
    public void respawnListOfThings() {
        renderList.clear();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                renderList.add(new Things(i* tileManager.getWidth(),j* tileManager.getHeight(), tileManager.getTile(19,48)));
                switch (this.map[i][j]){
                    case ' ' :
                        break;
                    case 'W' :
                        renderList.add(new SolidThings(i* tileManager.getWidth(),j* tileManager.getHeight(), tileManager.getTile(14,17)));
                        break;
                    case 'T' :
                        AnimatedThings torch = new AnimatedThings(i * tileManager.getWidth(), j * tileManager.getHeight(), 32, 32);
                        Image[] torchFrames = new BufferedImage[3];
                        for (int k = 0; k < 3; k++) {
                            torchFrames[k] = tileManager.getTile(18,58 + k);
                        }
                        torch.setFrames(torchFrames);
                        renderList.add(torch);
                        break;
                    case 'X':
                        Image trapImage = tileManager.getTile(44,61);
                        int trapDamage = 10;
                        Trap trap = new Trap(i * tileManager.getWidth(), j * tileManager.getHeight(), trapImage, trapDamage);
                        renderList.add(trap);
                        break;

                    case 'E':
                        exitPosition = new SolidThings(i* tileManager.getWidth(),j* tileManager.getHeight(), tileManager.getTile(15,30));
                        renderList.add(exitPosition);
                        break;
                    }
                }
            }
        }
    
    /**
     * Returns the height of the dungeon.
     *
     * @return The height of the dungeon.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Returns the width of the dungeon.
     *
     * @return The width of the dungeon.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the list of renderable things in the dungeon.
     *
     * @return A list of Lybrinth.Things that can be rendered in the dungeon.
     */
    public ArrayList<Things> getRenderList() {
        return renderList;
    }
    
    /**
     * Removes a specific thing from the render list.
     *
     * @param thing The thing to be removed from rendering.
     */
    public void removeFromRenderList(Things thing) {
        renderList.remove(thing);
    }
    
    /**
     * Returns the position of the exit in the dungeon.
     *
     * @return A Lybrinth.SolidThings object representing the exit's position.
     */
    public SolidThings getExitPosition() {
        return exitPosition;
    }
}
