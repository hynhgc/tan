package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GamePanel extends JFrame {
    Image offScreenImage = null;
    int state= 0;
    private boolean start = false;
    int a = 1;
    public int count = 0;
    int width = 800;
    int height = 610;
    public List<Bullet> bulletList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    public List<Bot> botList = new ArrayList<>();
    public List<Bullet> removeList = new ArrayList<>();
    public List<Wall> wallList = new ArrayList<>();
    Image select = Toolkit.getDefaultToolkit().getImage("E:\\̹�˴�ս\\images\\selecttank.gif");
    int y = 150;
    private PlayerOne playerOne = new PlayerOne("E:\\̹�˴�ս\\images\\p1tankU.gif", 125, 510, 
            "E:\\̹�˴�ս\\images\\p1tankD.gif","E:\\̹�˴�ս\\images\\p1tankL.gif",
            "E:\\̹�˴�ս\\images\\p1tankR.gif","E:\\̹�˴�ս\\images\\p1tankU.gif",this);

        public void launch(){
        setTitle("̹�˴�ս");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);
        setVisible(true);
        this.addKeyListener(new GamePanel.KeyMonitor());
        for(int i = 0; i< 14; i ++){
            wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", i*60 ,170, this ));
        }
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 305 ,560,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 305 ,500,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 365 ,500,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 425 ,500,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 425 ,560,this ));

        while (true){
            if (count % 100 == 1) {
                Random r = new Random();
                int rnum =r.nextInt(800);
                botList.add(new Bot("E:\\̹�˴�ս\\images", rnum, 110,
                        "E:\\̹�˴�ս\\images","E:\\̹�˴�ս\\images",
                        "E:\\̹�˴�ս\\images","E:\\̹�˴�ս\\images", this));            
            }
            repaint();
            try {
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint(Graphics g) {
                if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        Graphics gImage= offScreenImage.getGraphics();       
        gImage.setColor(Color.white);    
        gImage.fillRect(0, 0, width, height);    
        gImage.setColor(Color.red);   
        gImage.setFont(new Font("����",Font.BOLD+Font.ITALIC,80));
        if(state == 0){           
            gImage.drawString("ѡ����Ϸģʽ",220,100);
            gImage.drawString("����ģʽ",220,200);
            gImage.drawString("����ģʽ",220,300);
            gImage.drawString("��1 2ѡ��ģʽ,���س���ʼ��Ϸ",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
        
            gImage.drawString("��Ϸ��ʼ",220,100);
            if(state == 1){
                gImage.drawString("����ģʽ",220,200);
            }
            else{
                gImage.drawString("����ģʽ",220,200);
            }
            
            for(Tank player: tankList){
                player.paintSelf(gImage);
            }
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot: botList){
                bot.paintSelf(gImage);
            }
            for (Wall wall: wallList){
                wall.paintSelf(gImage);
            }
           count++;
        }
            g.drawImage(offScreenImage, 0, 0, null);
    }
    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    if(!start){
                        y = 150;
                        a = 1;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_2:
                    if(!start){
                        y = 250;
                        a = 2;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_ENTER:
                    tankList.add(playerOne);
                    state = a;
                    start = true;
                    //System.out.println("state:"+""+state);
                    break;
                default:
                    playerOne.keyPressed(e);
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
        }
    }
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}
