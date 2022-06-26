package lol.magix.notasusbot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public final class Constants {
    public static final String BOT_AUTHORIZATION = System.getenv("TOKEN");
    public static final String BOT_ADMINISTRATOR = System.getenv("ADMIN");
    public static final String BOT_PREFIX = System.getenv("PREFIX");
    
    public static final BufferedImage SOURCE_IMAGE;

    public static final String[] SUB_REDDITS = {
            "hentai", "GenshinImpactHentai"
    };
    public static final String[] ALLOWED_KEYWORDS = {
            "rule34", "r34", "hentai", "porn", "sex"
    };
    
    public static final Color EMBED_COLOR = Color.PINK;

    static {
        BufferedImage image;
        
        try {
            // Try to read the source image from the JAR's resources.
            image = ImageIO.read(NotASuspiciousBot.class.getResourceAsStream("source-2.jpg"));
        } catch (Exception ignored) {
            try {
                // Try to read the source image from 'crepe.moe'.
                image = ImageIO.read(new URL("https://crepe.moe/c/WX8KweJ5"));
            } catch (Exception ignored1) {
                // Exit if unable to the image.
                NotASuspiciousBot.getLogger().error("Unable to read the '/sex' source image.");
                image = null;  System.exit(1);
            }
        }
        
        SOURCE_IMAGE = image;
    }
}