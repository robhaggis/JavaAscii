import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class main {
    public static void main(String[] args){

        //Load image, scale to 256x256, convert to greyscale
        BufferedImage image = null;
        File input = new File("res/deadpool.jpg");
        try {
            image = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = image.getScaledInstance(256, 256, Image.SCALE_DEFAULT);
        BufferedImage greyScale = new BufferedImage(256, 256, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = greyScale.getGraphics();
        g.drawImage(scaled, 0, 0, null);

        String output = "";
        //Assign each value an ascii character and append to string
        for(int x=0;x<256;x++){
            for(int y=0;y<256;y++){
                Color col = new Color(greyScale.getRGB(y,x));
                int greyVal = col.getRed()>>5;
                switch(greyVal){
                    case 0: output = output.concat("@");break;
                    case 1: output = output.concat("%");break;
                    case 2: output = output.concat("#");break;
                    case 3: output = output.concat("*");break;
                    case 4: output = output.concat("+");break;
                    case 5: output = output.concat("=");break;
                    case 6: output = output.concat(":");break;
                    case 7: output = output.concat(" ");break;
                }
            }
            output = output.concat("\n");
        }

        //Write string to file with timestamp to prevent overwriting
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("res/output" + (System.currentTimeMillis()/1000) +  ".txt"));
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
