import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class Main extends Canvas {
    static int width = 700, height = 700;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // сглаживание
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        try {
            URLConnection openConnection = new URL("https://i.downloadatoz.com/download/icon2/1/1/1/24bbf1ea7ddad40c6c8f520b40109011.jpg").openConnection();
            BufferedImage image = ImageIO.read(openConnection.getInputStream());
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int y = 0, x = 0;
            while (true) { // Двигаемся по диагонали, с очисткой области через паузу
                if (x > width || y > height) {
                    y = 0;
                    x = 0;
                }
                g2.drawImage(image, x, y, this);
                TimeUnit.MILLISECONDS.sleep(30);
                g2.clearRect(x, y, imageWidth, imageHeight);
                x+=3;
                y+=3;
            }
        } catch (IOException | InterruptedException e) {
            g.drawString("Ошибка", 10,10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Машина");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width/2-width/2,dim.height/2-height/2, width,height);
        Main m = new Main();
        frame.add(m);
        frame.setVisible(true);
    }
}
