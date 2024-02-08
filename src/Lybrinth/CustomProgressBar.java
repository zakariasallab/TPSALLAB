package Lybrinth;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

/**
 * Custom progress bar class that extends JProgressBar to provide a customized visual appearance.
 * This class allows customization of the background and progress color of the progress bar.
 */
public class CustomProgressBar extends JProgressBar {
    
    /**
     * Constructs a Lybrinth.CustomProgressBar with specified minimum and maximum values, and custom colors.
     *
     * @param min The minimum value of the progress bar.
     * @param max The maximum value of the progress bar.
     * @param backgroundColor The color for the background of the progress bar.
     * @param progressColor The color for the progress indicator of the progress bar.
     */
    public CustomProgressBar(int min, int max, Color backgroundColor, Color progressColor) {
        super(min, max);
        setUI(new BasicProgressBarUI() {
            protected void paintDeterminate(Graphics g, JComponent c) {
                if (!(g instanceof Graphics2D)) {
                    return;
                }
                
                Insets b = progressBar.getInsets();
                int width = progressBar.getWidth();
                int height = progressBar.getHeight();
                int barRectWidth = width - (b.right + b.left);
                int barRectHeight = height - (b.top + b.bottom);
                
                g.setColor(backgroundColor);
                g.fillRect(b.left, b.top, barRectWidth, barRectHeight);
                
                int amountFull = getAmountFull(b, barRectWidth, barRectHeight);
                
                g.setColor(progressColor);
                g.fillRect(b.left, b.top, amountFull, barRectHeight);
                
                if (progressBar.isStringPainted()) {
                    Graphics2D g2d = (Graphics2D) g;
                    String progressText = progressBar.getString();
                    FontMetrics fm = g.getFontMetrics();
                    int stringWidth = fm.stringWidth(progressText);
                    int stringHeight = fm.getAscent();
                    
                    int textX = (progressBar.getWidth() / 2) - (stringWidth / 2);
                    int textY = (progressBar.getHeight() / 2) + (stringHeight / 2);
                    
                    g2d.setColor(progressBar.getForeground());
                    g2d.drawString(progressText, textX, textY);
                }
            }
        });
    }
    
    /**
     * Overrides the getPreferredSize method to provide a custom preferred size for the progress bar.
     *
     * @return The preferred dimension of the progress bar.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 15);
    }
}
