package Main;

/**
 * Created by Gideon on 5/2/15.
 */

import Tutorial.*;
import Tutorial.LED;

import java.awt.*;
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

        circuit.addStep(step1);
        circuit.addStep(step2);
        circuit.addStep(step3);
//        circuit.addStep(step4);
        circuit.addStep(step5);

        //circut/Tutorial is in the system

        //now iterate over each step, prompt the user, wait till he finishes, verify and continue
        for(Step step : circuit.getSteps()){
            //Prompt User
//            currentStep = step;
//            currentInstruction = step.getInstruction();
//            System.out.printf(currentInstruction);
//            Utils.speak(step.getVocalInstruction());

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

            //temp:
            instruct(Messages.stepCompleteMessage());


//            currentInstruction = Messages.stepCompleteMessage();
//            Utils.speak(Messages.stepCompleteMessage());
//            Thread.sleep(5);


//            while(!step.isValid()){
//                click = false;
//                instruct(Messages.stepFailedMessage()); //say/draw failed
//
//                instruct(step);
//
//                while(!click) {
//                    Thread.sleep(1);
//                }
//                click = false;
//            }
//            instruct(Utils.stepCompleteMessage());
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
}