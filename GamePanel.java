package tankwar;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GamePanel extends JFrame {
	static URL url;
	static AudioClip ac;private static final long serialVersionUID = 1L;




    private Image offScreenImage = null;
    public int state= 0;
    private int a = 1;
    public int count = 0;
    private int width = 800;
    private int height = 610;
    private int enemyCount = 0;
    private int y = 150;
    private boolean start = false;
    public List<Bullet> bulletList = new ArrayList<>();
    public List<Bot> botList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    public List<Wall> wallList = new ArrayList<>();
    public List<Bullet> removeList = new ArrayList<>();
    public List<Base> baseList = new ArrayList<>();
    public List<BlastObj> blastList = new ArrayList<>();
    public Image background = Toolkit.getDefaultToolkit().getImage("E:\\̹�˴�ս\\images\\background.jpg");
    private Image select = Toolkit.getDefaultToolkit().getImage("E:\\̹�˴�ս\\images\\selecttank.gif");
    private Base base = new Base("E:\\̹�˴�ս\\images\\star.gif", 365, 560, this);
    private PlayerOne playerOne = new PlayerOne("E:\\̹�˴�ս\\images\\R-B.gif", 120, 510,
            "E:\\̹�˴�ս\\images\\R-B.gif","E:\\̹�˴�ս\\images\\R-B3.gif",
            "E:\\̹�˴�ս\\images\\R-B4.gif","E:\\̹�˴�ս\\images\\R-B2.gif", this);
    private PlayerTwo playerTwo = new PlayerTwo("E:\\̹�˴�ս\\images\\R-C (1) (1).gif", 620, 510,
            "E:\\̹�˴�ս\\images\\R-C (1) (1).gif","E:\\̹�˴�ս\\images\\R-C (1) (3).gif",
            "E:\\̹�˴�ս\\images\\R-C (1) (4).gif","E:\\̹�˴�ս\\images\\R-C (1) (2).gif", this);

    public void launch(){
        setTitle("̹�˴�ս");
        setSize(width, height);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        this.addKeyListener(new GamePanel.KeyMonitor());
        for(int i = 0; i< 14; i ++){
            wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", i*60 ,170, this ));
        }
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 305 ,560,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 305 ,500,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 365 ,500,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 425 ,500,this ));
        wallList.add(new Wall("E:\\̹�˴�ս\\images\\walls.gif", 425 ,560,this ));
        baseList.add(base);

        while (true){
            if(botList.size() == 0 && enemyCount == 10){
                state = 5;
            }
            if(tankList.size() == 0 && (state == 1 || state == 2)){

                state = 4;
            }
            if(state == 1 || state == 2){
                if (count % 100 == 1 && enemyCount < 10) {
                    Random r = new Random();
                    int rnum =r.nextInt(800);
                    botList.add(new Bot("E:\\̹�˴�ս\\images\\enemy1U.gif", rnum, 110,
                            "E:\\̹�˴�ս\\images\\enemy1U.gif","E:\\̹�˴�ս\\images\\enemy1D.gif",
                            "E:\\̹�˴�ս\\images\\enemy1L.gif","E:\\̹�˴�ս\\images\\enemy1R.gif", this));
                    enemyCount++;
                }
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
        gImage.setColor(Color.gray);
        gImage.fillRect(0, 0, width, height);
        gImage.setColor(Color.yellow);
        gImage.setFont(new Font("����",Font.BOLD,50));
        if(state == 0){
            gImage.drawString("ѡ����Ϸģʽ",220,100);
            gImage.drawString("������Ϸ",220,200);
            gImage.drawString("˫����Ϸ",220,300);
            gImage.drawString("��1��2ѡ��ģʽ�����س���ʼ��Ϸ",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            gImage.setColor(Color.red);
            gImage.setFont(new Font("����",Font.BOLD,20));
            gImage.drawString("WASD�����ƶ�",0,510);
            gImage.drawString("�ո����",0,550);
            if(state == 2){
                gImage.drawString("����������ƶ�",575,510);
                gImage.drawString("K���",575,550);
            }

            for(Tank tank : tankList){
                tank.paintSelf(gImage);
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
            for(Base base : baseList){
                base.paintSelf(gImage);
            }
            for(BlastObj blast : blastList){
                blast.paintSelf(gImage);
            }
            count++;
        }
        else if(state == 3){
            gImage.drawString("��Ϸ��ͣ",220,200);
        }
        else if(state == 4){
            gImage.drawString("��Ϸʧ��",220,200);
        }
        else if(state == 5){
            gImage.drawString("��Ϸʤ��",220,200);
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    y = 150;
                    a = 1;
                    break;
                case KeyEvent.VK_2:
                    y = 250;
                    a = 2;
                    break;
                case KeyEvent.VK_ENTER:
                    state = a;
                    if(state == 1 && !start){
                        tankList.add(playerOne);
                    }else{
                        tankList.add(playerOne);
                        tankList.add(playerTwo);
                    }
                    start = true;
                    break;
                case KeyEvent.VK_P:
                    if(state != 3){
                        a = state;
                        state = 3;
                    }
                    else{
                        state = a;
                        if(a == 0) {
                            a = 1;
                        }
                    }
                    break;
                default:
                    playerOne.keyPressed(e);
                    playerTwo.keyPressed(e);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
            playerTwo.keyReleased(e);
        }
    }

    public static void main(String[] args) throws MalformedURLException {
    	File f1 = new File("E:\\QQ����\\ex.wav");
    	url = f1.toURL();
    	ac = Applet.newAudioClip(url);
    	ac.loop();

        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}