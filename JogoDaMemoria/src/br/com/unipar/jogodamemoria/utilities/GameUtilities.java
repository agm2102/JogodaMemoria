
package br.com.unipar.jogodamemoria.utilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GameUtilities {

    public ImageIcon imageScaler(ImageIcon imageIcon, int size) {
        Image image;
        image = imageIcon.getImage();
        image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);

        return imageIcon;
    }

    public BufferedImage[] loadImages(String path, int maxIndex, int imageSize) throws IOException {
        BufferedImage[] images = new BufferedImage[maxIndex];

        for (int i = 0; i < maxIndex; i++) {
            images[i] = ImageIO.read(getClass().getResourceAsStream(path + i + ".png"));
            BufferedImage resizedImage = new BufferedImage(imageSize, imageSize, images[i].getType());
            Graphics2D g2d = resizedImage.createGraphics();
            //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            //g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(images[i], 0, 0, imageSize, imageSize, null);
            g2d.dispose();
            images[i] = resizedImage;
        }
        return images;
    }
}
