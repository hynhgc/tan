package tankwar;

import java.awt.*;
import java.util.List;

public class Tank extends GameObject{

    private boolean attackCoolDown =true;
    private int attackCoolDownTime =1000;    
    private String upImage; 
    private String downImage;
    private String rightImage;
    private String leftImage;
    boolean alive = true;
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
    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImage);
        if(!hitWall(x-speed, y) && !moveToBorder(x-speed, y)){
            this.x -= speed;
        }
    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImage);
        if(!hitWall(x+speed, y) && !moveToBorder(x+speed, y)){
            this.x += speed;
        }
    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImage);
        if(!hitWall(x, y-speed) && !moveToBorder(x, y- speed)){
            this.y -= speed;
        }
    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImage);
        if(!hitWall(x, y+speed) && !moveToBorder(x, y+speed)){
            this.y += speed;
        }
    }
    public void attack(){
        Point p = getHeadPoint();
        if(attackCoolDown && alive){
            Bullet bullet = new Bullet("E:\\Ì¹¿Ë´óÕ½\\images\\bulletGreen.gif",p.x,p.y,direction, this.gamePanel);
            this.gamePanel.bulletList.add(bullet);
            attackCoolDown = false;
            new AttackCD().start();
        }
    }

    public class AttackCD extends Thread{
        public void run(){
            attackCoolDown=false;
            try{
                Thread.sleep(attackCoolDownTime);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            attackCoolDown=true;
            this.stop();
        }
    }
    public Point getHeadPoint(){
        switch (direction){
            case UP:
                return new Point(x + width/2, y );
            case LEFT:
                return new Point(x, y + height/2);
            case DOWN:
                return new Point(x + width/2, y + height);
            case RIGHT:
                return new Point(x + width, y + height/2);
            default:
                return null;
        }
    }

    public boolean hitWall(int x, int y){
        Rectangle next = new Rectangle(x, y, width, height);
        List<Wall> walls = this.gamePanel.wallList;
        for(Wall w:walls){
            if(w.getRec().intersects(next)){
                return true;
            }
        }
        return false;
    }

    public boolean moveToBorder(int x, int y){
        if(x < 0){
            return true;
        }else if(x > this.gamePanel.getWidth()-width){
            return true;
        }
        if(y < 0){
            return true;
        }else if(y > this.gamePanel.getHeight()-height){
            return true;
        }
        return false;
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
