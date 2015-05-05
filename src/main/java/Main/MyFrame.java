package Main;

/**
 * Created by Gideon on 5/2/15.
 */
import Tutorial.LED;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame {
    private JPanel contentPane;
    private List<Point2D> pointsToDraw = new ArrayList<Point2D>();
    private List<Rectangle> rectanglesToDraw = new ArrayList<Rectangle>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFrame frame = new MyFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 960);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        new MyThread().start();
    }

    VideoCap videoCap = new VideoCap();

    public void paint(Graphics ga){
        BufferedImage image =videoCap.getOneFrame(); // get frame from camera
        Graphics2D g = image.createGraphics();

        BreadBoard bb = new BreadBoard(videoCap.mat2Img.getMat(),null);
        g.setColor(Color.red);
        List<Point2D> points = LED.searchInAreaHSB(BreadBoard.getInstance().getRawMatrix(),null);
        if(points.size() != 0)
            pointsToDraw = new ArrayList<Point2D>(points);
        for(Point2D point : pointsToDraw)
            g.drawOval((int)point.getX() - 7,(int)point.getY()- 7,15,15);


        g.setColor(Color.yellow);
        Rectangle2D box = bb.getBoundingBox();
        g.drawRect((int)box.getX(),(int)box.getY(),(int)box.getWidth(),(int)box.getHeight());


        //g.finalize();
        g.dispose();


        ga.drawImage(image, 0, 0, this);
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for (;;){
                repaint();
                try { Thread.sleep(30);
                } catch (InterruptedException e) {    }
            }
        }
    }
}