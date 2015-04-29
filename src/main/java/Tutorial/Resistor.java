package Tutorial;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class Resistor extends Part {

    ArrayList<Bands> bands;
    private Color[][] rangedRawMatrix; //subset of raw image data of this part's area only




    public static ArrayList<Bands> getBandsFromImage(Color [][] rawImageData){

        //convert rawImageData to OpenCV.MAT
        //


    }

    public static Mat doMagic(final Mat image) {

        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);
        // Mat small = image.submat(image.rows()/2-100,
        // image.rows()/2+100,image.cols()/2-200,image.cols()/2+200);
        // Mat small = image.submat(100,image.rows()-100,50,image.cols()-50);
        // Mat small = image;
        Mat small = image.submat(50, image.rows() - 50, 50, image.cols() - 50);

        Scalar mean = Core.mean(small);

        // Scalar lastcolor = new Scalar(0,0,0,0);
        int nums[] = new int[small.cols()];
        double avgy[] = new double[small.cols()];
        // double colors[][] = new double[small.cols()][4];
        for (int x = 0; x < small.cols(); x += 1) {

            int num = 0;
            for (int y = 0; y < small.rows(); y += 1) {
                double res[] = small.get(y, x);
                if (res == null) {
                    continue;
                }
                if (res[0] > mean.val[0] * 0.5 && res[1] > mean.val[1] * 0.5 && res[2] > mean.val[2] * 0.5) {
                    // small.put(y,x,0,0,0);
                }
                else {
                    num++;
                    avgy[x] += y;
                }
            }
            if (num > 2) {
                nums[x] = num;
                avgy[x] /= num;
            }
        }

        double avgnum = 0;
        int avgnumnum = 0;
        for (int x = 0; x < small.cols(); x += 1) {
            if (nums[x] > 0) {
                avgnum += nums[x];
                avgnumnum++;
            }
        }
        int minx = -1;
        int maxx = 0;
        if (avgnumnum > 0) {
            avgnum /= avgnumnum * 1.0;

            for (int x = 0; x < small.cols(); x += 1) {
                if (nums[x] > avgnum + 15) {
                    if (minx < 0) {
                        minx = x;
                    }
                    maxx = x;

                }
            }
        }

        if (minx >= 0) {
            int miny = (int) avgy[minx];
            int maxy = (int) avgy[maxx];


            int minx2 = minx - 20;
            if (minx2 < 0) {
                minx2 = 0;
            }
            if (minx2 > avgy.length - 1) {
                minx2 = avgy.length - 1;
            }
            int maxx2 = maxx + 20;
            if (maxx2 < 0) {
                maxx2 = 0;
            }
            if (maxx2 > avgy.length - 1) {
                maxx2 = avgy.length - 1;
            }
            double a = (avgy[maxx2] - avgy[minx2]) / ((maxx2) - (minx2));

            miny = (int) (a * (minx - minx2) + avgy[minx2]);
            maxy = (int) (a * (maxx - maxx2) + avgy[maxx2]);

            // Mat tiny = small.submat(y1, y2, minx, maxx);

            int w = maxx - minx;

            if (w > 10 && a < 0.7 && a > -0.7) {

                for (int x = 0; x < w; x++) {
                    int y = (int) (a * x) + miny;
                    if (true) {// y>40 && y<small.rows()-40){
                        double a2 = -1.0 / a;

                        double res[] = new double[3];

                        int x2 = x + minx;

                        int oy = 15;
                        int ox = (int) (oy / a2);

                        double col1[] = small.get(y + oy, x2 + ox);
                        small.put(y + oy, x2 + ox, 0.0, 0.0, 255.0);

                        oy = -15;
                        ox = (int) (oy / a2);

                        double col2[] = small.get(y + oy, x2 + ox);
                        small.put(y + oy, x2 + ox, 0.0, 0.0, 255.0);

                        oy = 25;
                        ox = (int) (oy / a2);

                        double col3[] = small.get(y + oy, x2 + ox);
                        small.put(y + oy, x2 + ox, 0.0, 0.0, 255.0);

                        oy = -25;
                        ox = (int) (oy / a2);

                        double col4[] = small.get(y + oy, x2 + ox);
                        small.put(y + oy, x2 + ox, 0.0, 0.0, 255.0);

                        if (col1 != null && col2 != null && col3 != null && col4 != null) {
                            res[0] = (col1[0] + col2[0] + col3[0] + col4[0]) / 4;
                            res[1] = (col1[1] + col2[1] + col3[1] + col4[1]) / 4;
                            res[2] = (col1[2] + col2[2] + col3[2] + col4[2]) / 4;
                            image.put(0, x, res);
                        }


                    }
                }
                Mat res = new Mat();
                Imgproc.resize(image.submat(new Rect(0, 0, w, 1)), res, new Size(image.cols(), 50), 0, 0,
                        Imgproc.INTER_NEAREST);
                res.copyTo(image.submat(new Rect(0, 0, res.cols(), res.rows())));

                detect(image, res);

            }

            Core.line(small, new Point(minx, miny), new Point(maxx, maxy), new Scalar(255, 0, 0), 4);

        }

        return image;
    }



}
