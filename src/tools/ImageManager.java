package tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageManager {

    private static final ImageManager instance = new ImageManager();

    private HashMap<String, Color> tintTargets;
    private HashMap<String, Double> colorTolerances;
    private HashMap<String, Double> sizePercents;
    private HashMap<String, HashMap<Color, BufferedImage>> images;

    public static ImageManager getInstance() {
        return instance;
    }

    public void setImageInfo(String filename, double percent, Color from,
            double tolerance) {
        sizePercents.put(filename, percent);
        tintTargets.put(filename, from);
        colorTolerances.put(filename, tolerance);
    }

    public BufferedImage get(String filename) {
        return get(filename, null);
    }

    public BufferedImage get(String filename, Color tint) {
        // Add HashMap if needed
        if (!images.containsKey(filename)) {
            images.put(filename, new HashMap<Color, BufferedImage>());
        }

        // Add BufferedImage if needed
        if (!images.get(filename).containsKey(tint)) {
            BufferedImage image;
            try {
                double sizePercent = 1;
                double colorTolerance = 0;
                Color tintTarget = Color.white;
                if (sizePercents.containsKey(filename)) {
                    sizePercent = sizePercents.get(filename);
                    colorTolerance = colorTolerances.get(filename);
                    tintTarget = tintTargets.get(filename);
                }

                // Add original image if needed (optimization)
                if (!images.get(filename).containsKey(null)) {
                    BufferedImage original = ImageIO.read(new File(filename));
                    if (sizePercent != 1)
                        original = ImageTools.resizeImage(original,
                                sizePercent);
                    images.get(filename).put(null, original);
                }

                image = images.get(filename).get(null);
                if (tint != null)
                    image = ImageTools.tintImage(image, tintTarget, tint,
                            colorTolerance);
                images.get(filename).put(tint, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return images.get(filename).get(tint);
    }

    private ImageManager() {
        tintTargets = new HashMap<String, Color>();
        colorTolerances = new HashMap<String, Double>();
        sizePercents = new HashMap<String, Double>();
        images = new HashMap<String, HashMap<Color, BufferedImage>>();
    }
}
