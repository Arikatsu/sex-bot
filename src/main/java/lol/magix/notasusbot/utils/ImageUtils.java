package lol.magix.notasusbot.utils;

import lol.magix.notasusbot.NotASuspiciousBot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class ImageUtils {
    private ImageUtils() {
        // This class is not meant to be instantiated.
    }

    /**
     * Copies a buffered image.
     * @param image The image to copy.
     * @return The copied image.
     */
    public static BufferedImage copy(BufferedImage image) {
        var cm = image.getColorModel();
        var isAlphaPremultiplied = cm.isAlphaPremultiplied();
        var raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    /**
     * Converts a BufferedImage to a byte array.
     * @param image The image to convert.
     * @param imageToAppend The image to append.
     * @param x The X coordinate of the corner to append the image to.
     * @param y The Y coordinate of the corner to append the image to.
     * @return The new image.
     */
    public static BufferedImage appendToImage(BufferedImage image, BufferedImage imageToAppend, int x, int y) {
        var copy = ImageUtils.copy(image);
        var graphics = copy.createGraphics();
        
        graphics.drawImage(imageToAppend, x, y, null);
        graphics.dispose(); return copy;
    }

    /**
     * Converts a BufferedImage to a byte array.
     * @param source The image to convert.
     * @param x The X coordinate of the corner to crop the image from.
     * @param y The Y coordinate of the corner to crop the image from.
     * @return The new image.
     */
    public static BufferedImage resize(BufferedImage source, int x, int y) {
        var scaledInstance = source.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        var finalImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);

        var g2d = finalImage.createGraphics();
        g2d.drawImage(scaledInstance, 0, 0, null);
        
        g2d.dispose(); return finalImage;
    }

    /**
     * Writes an image to a file.
     * @param image The image to write.
     * @param writeTo The file to write to.
     */
    public static void writeToFile(BufferedImage image, File writeTo) {
        try {
            ImageIO.write(image, "png", writeTo);
        } catch (Exception exception) {
            NotASuspiciousBot.getLogger().warn("Unable to save image.", exception);
        }
    }
}