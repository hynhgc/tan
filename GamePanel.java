package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JFrame {

    Image offScreenImage = null;
    int state= 0;
    private boolean start = false;
    int a = 1;
    int width = 800;
    int height = 610;
    public List<Tank> tankList = new ArrayList<>();
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
        while (true){
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
        gImage.setColor(Color.green);
        gImage.fillRect(0, 0, width, height);
        gImage.setColor(Color.BLUE);
        gImage.setFont(new Font("����",Font.BOLD,50));
        if(state == 0){
            gImage.drawString("ѡ����Ϸģʽ",220,100);
            gImage.drawString("����ģʽ",220,200);
            gImage.drawString("����ģʽ",220,300);
            gImage.drawString("��1 2ѡ��ģʽ,���س���ʼ��Ϸ",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){  
            gImage.drawString("",220,300);
            if(state == 1){
                gImage.drawString("",220,200);
            }
            else{
                gImage.drawString("",220,200);
            }
            playerOne.paintSelf(gImage);
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
                    break;
                case KeyEvent.VK_2:
                    if(!start){
                        y = 250;
                        a = 2;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    tankList.add(playerOne);
                    state = a;
                    start = true;
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}