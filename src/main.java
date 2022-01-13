import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class main {
    public static void main(String[] args) {

        final int WIDTH = 256;
        final int HEIGHT = 256;

        //Load image, scale down, convert to greyscale
        BufferedImage image = null;
        String filename = "deadpool";
        File input = new File("res/" + filename + ".jpg");
        try {
            image = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
        BufferedImage greyScale = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = greyScale.getGraphics();
        g.drawImage(scaled, 0, 0, null);

        //Ascii Character Set
        String asciiOrderedByDensity = "   ,:;Il!i~+_-?][}{1)(|/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        String asciiReversed = new StringBuilder(asciiOrderedByDensity).reverse().toString();

        String outputNormal = "";
        String outputInverted= "";

        //Bit shift 256-bit greyscale value right by 2 as we have 64 ascii values to choose from
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Color col = new Color(greyScale.getRGB(x, y));
                int greyVal = col.getRed() >> 2;
                outputNormal = outputNormal.concat(asciiOrderedByDensity.substring(greyVal, greyVal+1));
                outputInverted = outputInverted.concat(asciiReversed.substring(greyVal, greyVal+1));
            }
            outputNormal = outputNormal.concat("\n");
            outputInverted = outputInverted.concat("\n");
        }

        //Write string to file with timestamp to prevent overwriting
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("res/" + filename +  (System.currentTimeMillis() / 1000) + ".txt"));
            writer.write(outputInverted);
            writer = new BufferedWriter(new FileWriter("res/" + filename + "INVERTED" +   (System.currentTimeMillis() / 1000) + ".txt"));
            writer.write(outputNormal);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}




