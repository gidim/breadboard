package Main;

/**
 * Created by Gideon on 5/2/15.
 */

import Tutorial.*;
import Tutorial.LED;
import com.google.common.collect.MinMaxPriorityQueue;
import org.opencv.core.*;

import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BreadCrumblerTester extends JFrame {
    private static boolean click = false;
    private JPanel contentPane;
    private List<Point2D> pointsToDraw = new ArrayList<Point2D>();
    private ArrayList<Rectangle2D> rectanglesToDraw = new ArrayList<Rectangle2D>();
    private static ArrayList<String> stringsToDraw = new ArrayList<String>();
    //ArrayList<RectangleMatch> matches = new ArrayList<RectangleMatch>();
    MinMaxPriorityQueue<RectangleMatch> matches =
            MinMaxPriorityQueue.maximumSize(3000).create();


    int counter = 0;
    private static int numOfIter = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws InterruptedException {


        //START VIDEO FRAME
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BreadCrumblerTester frame = new BreadCrumblerTester();
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
    public BreadCrumblerTester() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 960);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        pointsToDraw = new ArrayList<Point2D>();


        this.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        new MyThread().start();
    }

    VideoCap videoCap = new VideoCap();

    public void paint(Graphics ga){
        //get values from contur finder
        BufferedImage image = videoCap.getOneMirrorFrame();
        Graphics2D g = image.createGraphics();

        Rectangle2D.Double resistor = null;

        Mat retMat = null;
        //run 30 times
        if(numOfIter < 20) {
            retMat = ContourFinder.getComponentContours(videoCap.getOneMirrorMat(), matches);
            numOfIter ++;
        }

        if(numOfIter == 20) {
            //sort by count
            resistor = matches.peekFirst().rect;
            //retMat = ContourFinder.getComponentContours(videoCap.getOneMirrorMat());
        }


        if(resistor != null) {
            ga.setColor(Color.yellow);
            ga.drawRect((int) resistor.getX(), (int) resistor.getY(), (int) resistor.getWidth(), (int) resistor.getHeight());
            //Core.rectangle(retMat, new org.opencv.core.Point(resistor.x, resistor.y), new org.opencv.core.Point(resistor.x + resistor.width, resistor.y + resistor.height), new Scalar(255, 255, 255));

        }

        //finalize and draw
        g.finalize();
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