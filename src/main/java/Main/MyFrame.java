package Main;

/**
 * Created by Gideon on 5/2/15.
 */
import Tutorial.LED;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
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

        this.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click!" + e);
                BreadBoard.getInstance().setA1Pixel(e.getX()-3,e.getY()+18);
                BreadBoard.getInstance().updateHoleMatrix();
                //pointsToDraw.add(new Point2D.Double(e.getX()-3,e.getY()+18));

            }
        });

        new MyThread().start();
    }

    VideoCap videoCap = new VideoCap();

    public void paint(Graphics ga){
        BufferedImage image =videoCap.getOneFrame(); // get frame from camera
        Graphics2D g = image.createGraphics();

        BreadBoard bb = new BreadBoard(videoCap.mat2Img.getMat(),null);
        //find LED
        g.setColor(Color.red);
        List<Point2D> points = LED.searchInAreaHSB(BreadBoard.getInstance().getRawMatrix(), null);
        if(points.size() != 0)
            pointsToDraw = new ArrayList<Point2D>(points);
        for(Point2D point : pointsToDraw)
            g.drawRect((int) point.getX(), (int) point.getY(),4,4);

        //draw bounding box
        g.setColor(Color.yellow);
        Rectangle2D box = bb.getBoundingBox();
        g.drawRect((int)box.getX(),(int)box.getY(),(int)box.getWidth(),(int)box.getHeight());

        //draw holes
        g.setColor(Color.green);


        for (Rectangle2D rec : ConturFinder.getHoldesFromImage(bb.getMatImage()))
            g.drawRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight());





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