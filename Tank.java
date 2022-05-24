package tankwar;

import java.awt.*;

public class Tank extends GameObject{

    private String upImage;
    private String downImage;
    private String rightImage;
    private String leftImage;
    int width = 40;
    int height = 50;
    Direction direction = Direction.UP;
    private int speed = 3;
    public Tank(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
        this.upImage = upImage;
        this.leftImage = leftImage;
        this.downImage = downImage;
        this.rightImage = rightImage;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}