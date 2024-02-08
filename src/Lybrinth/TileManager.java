package Lybrinth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Manages tiles for rendering in the game.
 * This class is responsible for loading and managing a tileset from an image file.
 */
public class TileManager {
    private final int height; // Height of each tile
    private final int width;  // Width of each tile
    
    private Image[][] tiles; // Array of tiles extracted from the tile sheet
    
    private BufferedImage tileSheet; // Image containing the entire tileset
    
    /**
     * Constructs a Lybrinth.TileManager with specified tile height and width, and loads tiles from a default image file.
     *
     * @param height The height of each tile in pixels.
     * @param width The width of each tile in pixels.
     */
    public TileManager(int height, int width) {
        this.height = height;
        this.width = width;
        setTiles(width, height, "assets/images/tileSet.png");
    }
    
    /**
     * Constructs a Lybrinth.TileManager with specified tile dimensions and loads tiles from a specified image file.
     *
     * @param width The width of each tile in pixels.
     * @param height The height of each tile in pixels.
     * @param fileName The file path of the tileset image.
     */
    public TileManager(int width, int height, String fileName){
        this.width = width;
        this.height = height;
        setTiles(width, height, fileName);
    }
    
    /**
     * Gets the height of each tile.
     *
     * @return The height of tiles in pixels.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Gets the width of each tile.
     *
     * @return The width of tiles in pixels.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Loads and splits the tileset image into individual tiles.
     *
     * @param width The width of each tile in pixels.
     * @param height The height of each tile in pixels.
     * @param fileName The file path of the tileset image.
     */
    private void setTiles(int width, int height, String fileName){
        try{
            tileSheet = ImageIO.read(new File(fileName));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        tiles = new Image[tileSheet.getWidth()/width][tileSheet.getHeight()/height];
        for(int y=0;y+height<tileSheet.getHeight();y=y+height) {
            for (int x = 0; x +width < tileSheet.getWidth(); x = x + width) {
                tiles[x / width][y / height] = tileSheet.getSubimage(x, y, width, height);
            }
        }
    }
    
    /**
     * Retrieves a specific tile from the tileset based on x and y indices.
     *
     * @param x The x index of the tile.
     * @param y The y index of the tile.
     * @return The image of the specified tile, or null if indices are out of bounds.
     */
    public Image getTile(int x, int y) {
        if (x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length) {
            return tiles[y][x];
        } else {
            return null;
        }
    }
}
