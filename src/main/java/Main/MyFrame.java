package Main;

/**
 * Created by Gideon on 5/2/15.
 */
import com.google.common.collect.MinMaxPriorityQueue;
import Tutorial.*;
import Tutorial.LED;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame {
    private static boolean click = false;
    private JPanel contentPane;
    private List<Point2D> pointsToDraw = new ArrayList<Point2D>();
    private ArrayList<Rectangle2D> rectanglesToDraw = new ArrayList<Rectangle2D>();
    private static ArrayList<String> stringsToDraw = new ArrayList<String>();
    public volatile boolean findAResistor = false;
    public volatile ArrayList<Rectangle2D.Double> resistors = null;
    public volatile Rectangle2D.Double resistor = null;
    private static int numOfIter = 0;
    MinMaxPriorityQueue<RectangleMatch> matches =
            MinMaxPriorityQueue.maximumSize(3000).create();
    private static MyFrame instance;

    //private ArrayList<String> stringsToDraw = new ArrayList<String>();
    private BreadBoard bb;
    private volatile static String currentInstruction;
    private volatile static Step currentStep;

    int counter = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws InterruptedException {

        new BreadBoard();

        //START VIDEO FRAME
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

        while(!BreadBoard.getInstance().ready) //wait till breadboard finishes instantiation
                Thread.sleep(1);

        //CONFIGURE TUTORIAL
        Circuit circuit = new Circuit();
        //step 1
        Step step1 = new Step(1);
        Wire wire1 = new Wire("I","3", "R+","3","Yellow");
        //Wire wire1 = new Wire("L+","60", "A","60","Yellow");
        step1.setPart(wire1);
        //step 2
        Wire wire2 = new Wire("J","9", "J","13","Yellow");
        //Wire wire2 = new Wire("B","52", "C","56","Yellow");
        Step step2 = new Step(2);
        step2.setPart(wire2);

        Step step3 = new Step(3);
        Wire wire3 = new Wire("J","16","R-","16","Yellow");
        step3.setPart(wire3);

        Step step4 = new Step(4);
        Wire wire4 = new Wire("E","6","F","6","Yellow");
        step4.setPart(wire4);

        Step step5 = new Step(5);
        Wire wire5 = new Wire("E","9","G","9","Yellow");
        step5.setPart(wire5);


        //step 3
        LED led = new LED("I","13","I","16");
        Step step6 = new Step(6);
        step6.setPart(led);
        //step 4

        Resistor res = new Resistor("G","3","G","6", "Yellow Purple Red Gold");
        Step step7 = new Step(7);
        step7.setPart(res);


        //step 5
        Switch sw = new Switch("A","6","D","9");
        Step step8 = new Step(8);
        step8.setPart(sw);

        /*
        Step step1 = new Step(1);
        Wire wire1 = new Wire("J","16", "R-","16","Yellow");
        //Wire wire1 = new Wire("L+","60", "A","60","Yellow");
        step1.setPart(wire1);
        //step 2
        Wire wire2 = new Wire("J","13", "J","9","Green");
        //Wire wire2 = new Wire("B","52", "C","56","Yellow");
        Step step2 = new Step(2);
        step2.setPart(wire2);
        //step 3
        LED led = new LED("I","13","I","16");
        Step step3 = new Step(3);
        step3.setPart(led);
        //step 4

        Resistor res = new Resistor("J","6","R+","6", "Yellow Purple Red Gold");
        Step step4 = new Step(4);
        step4.setPart(res);
        //step 5
        Switch sw = new Switch("F","6","I","9");
        Step step5 = new Step(5);
        step5.setPart(sw);
        */

        circuit.addStep(step1);
        circuit.addStep(step2);
        circuit.addStep(step3);
        circuit.addStep(step4);
        circuit.addStep(step5);
        circuit.addStep(step6);
        circuit.addStep(step7);
        circuit.addStep(step8);

        //circut/Tutorial is in the system

        //now iterate over each step, prompt the user, wait till he finishes, verify and continue
        for(Step step : circuit.getSteps()){
            //Prompt User
            instruct(step);

            //Wait till user finishes
            //BreadBoard.getInstance().blockTillDone();
            //verify that the part is in the right place

            //temp: stop until click refreshes holes
            System.out.println("entering click loop");
            while(!MyFrame.click) {
                Thread.sleep(1);
            }
            System.out.println("exited click loop");
            MyFrame.click = false;


            while(!step.isValid()){
                click = false;
                instruct(Messages.stepFailedMessage()); //say/draw failed

                instruct(step);

                while(!click) {
                    Thread.sleep(1);
                }
                click = false;
            }
            instruct(Messages.stepCompleteMessage());
        }
        currentInstruction = null;
        currentStep = null;
    }

    /**
     * Instructs a String on all media channels
     * @param instruction
     */
    private static void instruct(String instruction) {
        currentInstruction = instruction;
        System.out.println(instruction);
        Utils.speak(instruction);
    }

    /**
     * Instructs a Step instruction on all media channels
     * @param step
     */
    private static void instruct(Step step) {
        currentStep = step;
        currentInstruction = step.getInstruction();
        System.out.println(step.getInstruction());
        Utils.speak(step.getVocalInstruction());
    }


    /**
     * Create the frame.
     */
    public MyFrame() {
        this.instance = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 960);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        pointsToDraw = new ArrayList<Point2D>();



        //todo: add keyboard listener that allows user to skip step

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentStep.setBypas(true);
                click = true;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Pressed " + e.getKeyChar());
            }
        });

        this.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

//                BreadBoard.getInstance().refreshHolesState(videoCap.getOneMirrorMat());
//                Hole h = BreadBoard.getInstance().getHole(new Point.Double(e.getX() - 3, e.getY() + 18));
//                if(h != null) {
//                    System.out.println("Clicked on " + h.getCol() + h.getRow());
//                }
//                else {
//                    System.out.println("Click! Not on hole");
//                }
                MyFrame.click = true;

            }
        });

//        this.getContentPane().addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("Click!" + e);
//                BreadBoard.getInstance().setA1Pixel(e.getX()-3,e.getY()+18);
//                BreadBoard.getInstance().updateHoleMatrix();
//                //pointsToDraw.add(new Point2D.Double(e.getX()-3,e.getY()+18));
//
//            }
//        });


        bb = new BreadBoard();
        new MyThread().start();
    }

    VideoCap videoCap = new VideoCap();

    public void paint(Graphics ga){
        BufferedImage image = videoCap.getOneMirrorFrame(); // get frame from camera
        Graphics2D g = image.createGraphics();

        Color stepHoleIndicatorColor = Color.MAGENTA;

        //draw very important parallel line
        drawParallelLine(g,image);

        //update breadboard with the new data
        bb.refresh(videoCap.getOneMirrorMat());

        //get new data to draw from updated breadboard
        rectanglesToDraw = new ArrayList<Rectangle2D>(bb.getRects());

        //draw new data

        //draw holes
        g.setColor(Color.green);
        for(Rectangle2D rec : rectanglesToDraw)
            g.drawRect((int) rec.getX(), (int) rec.getY(),(int)rec.getWidth(),(int)rec.getHeight());

        //draw resistor
        if(resistor !=null)
            g.drawRect((int) resistor.getX(), (int) resistor.getY(), (int) resistor.getWidth(), (int) resistor.getHeight());


        //draw step graphics
        if(currentStep != null) {
            g.setColor(stepHoleIndicatorColor);
            Rectangle2D fromRect = bb.getHole(currentStep.getPart().fromCol, currentStep.getPart().fromRow).getRect();
            Rectangle2D toRect = bb.getHole(currentStep.getPart().toCol, currentStep.getPart().toRow).getRect();
            g.drawRect((int) fromRect.getMinX(), (int) fromRect.getMinY(), (int) fromRect.getWidth(), (int) fromRect.getHeight());
            g.drawRect((int) toRect.getMinX(), (int) toRect.getMinY(), (int) toRect.getWidth(), (int) toRect.getHeight());

            Color stepHoleFillAlpha = new Color(stepHoleIndicatorColor.getRed(), stepHoleIndicatorColor.getGreen(), stepHoleIndicatorColor.getBlue(), 128);
            g.setColor(stepHoleFillAlpha);
            g.fillRect((int) fromRect.getMinX(), (int) fromRect.getMinY(), (int) fromRect.getWidth(), (int) fromRect.getHeight());
            g.fillRect((int) toRect.getMinX(), (int) toRect.getMinY(), (int) toRect.getWidth(), (int) toRect.getHeight());

        }

        //draw bounding box
        g.setColor(Color.yellow);
        Rectangle2D box = bb.getBoundingBox();
        g.drawRect((int)box.getX(),(int)box.getY(),(int)box.getWidth(),(int)box.getHeight());

        //draw instruction string
        float stringPosX = image.getMinX() + 55;
        float stringPosY = image.getHeight() - 200;
        Font instructionFont = new Font("Arial", Font.BOLD, 20);
        g.setFont(instructionFont);
        FontMetrics fm = g.getFontMetrics();
        if(currentInstruction != null) {
            if(currentInstruction.equals(Messages.stepCompleteMessage())) {
                g.setColor(Color.GREEN);
                g.drawString(currentInstruction, stringPosX, stringPosY);
            }
            else if(currentInstruction.equals(Messages.stepFailedMessage())) {
                g.setColor(Color.RED);
                g.drawString(currentInstruction, stringPosX, stringPosY);
            }
            else {
                //instruction. We want to paint hole names same as step hole indicators
                String[] instructionFragmented = currentStep.getInstructionArr();
                float x = stringPosX;
                for(int i = 0; i < instructionFragmented.length; i++) {
                    if(i == 1 || i == 3) {
                        //hole names
                        g.setColor(stepHoleIndicatorColor);
                    }
                    else {
                        //rest of string
                        g.setColor(Color.ORANGE);
                    }
                    g.drawString(instructionFragmented[i], x, stringPosY);
                    x += fm.stringWidth(instructionFragmented[i]);
                }
                //g.drawString(Utils.strArrTo(instructionFragmented), stringPosX, stringPosY);
            }

        }
        else {
            g.setColor(Color.PINK);
            g.drawString(Messages.noMoreStepsMessage(), stringPosX, stringPosY);
        }

        if(findAResistor){
            Mat retMat = null;
            //run 30 times
            if(numOfIter < 25) {
                retMat = ContourFinder.getComponentContours(videoCap.getOneMirrorMat(), matches);
                numOfIter ++;
            }

            if(numOfIter == 25) {
                int maxCount = matches.peekFirst().count;
                resistors = new ArrayList<Rectangle2D.Double>();
/*
                while(matches.peekFirst().count == maxCount){
                    resistors.add(matches.removeFirst().rect);
                }
  */
                for(RectangleMatch m : matches){
                    if(m.count == maxCount)
                        resistors.add(m.rect);
                }
                System.out.println();
            }
            if(resistors != null) {
                //g.setColor(Color.white);
                //g.drawRect((int) resistor.getX(), (int) resistor.getY(), (int) resistor.getWidth(), (int) resistor.getHeight());
                //Utils.saveBufferedImage(image,"withRes");
                findAResistor = false;
                numOfIter = 0;
                //Core.rectangle(retMat, new org.opencv.core.Point(resistor.x, resistor.y), new org.opencv.core.Point(resistor.x + resistor.width, resistor.y + resistor.height), new Scalar(255, 255, 255));
            }
        }


        //finalize and draw
        g.finalize();
        g.dispose();
        ga.drawImage(image, 0, 0, this);
    }

    private void drawParallelLine(Graphics2D g, BufferedImage image) {
        g.setColor(Color.cyan);
        int parallelLineX1 = image.getWidth() / 4;
        int parallelLineX2 = parallelLineX1 * 3;
        int parallelLineY1 = image.getHeight() / 4;
        int parallelLineY2 = parallelLineY1 * 3;
        g.drawLine(parallelLineX1, parallelLineY1, parallelLineX2, parallelLineY1);
        g.drawLine(parallelLineX1, parallelLineY1, parallelLineX1, parallelLineY2);
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

    public static MyFrame getInstance(){
        return instance;
    }
}