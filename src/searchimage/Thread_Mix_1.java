/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author ACER
 */
public class Thread_Mix_1 implements Runnable {

    static double d1 = 0;
    static double d2 = 0;
    static double d3 = 0;
    index1 index = null;
    ArrayList<Double> listresult = null;
    ArrayList<Double> listresult1 = null;
    ArrayList<Double> listresult2 = null;

    public Thread_Mix_1(index1 index) {
        this.index = index;
    }

    @Override
    public void run() {
        Mat coun = Imgcodecs.imread(index.path, 1);
        Mat tex = Imgcodecs.imread(index.path, 1);
        Mat his = Imgcodecs.imread(index.path, 1);
         Mat coun1 = Imgcodecs.imread(index.path, 1);
        Mat tex1 = Imgcodecs.imread(index.path, 1);
        Mat his1 = Imgcodecs.imread(index.path, 1);
        List<MatOfPoint> hist_img_src1 = index.imgProce.findContours(coun, false);
        double res2 = index.imgProce.comppcon(hist_img_src1, hist_img_src1);
        d2 = new Double(res2);
        double hist_img_src = index.imgProce.TextureImg(tex);
        double res1 = index.imgProce.compTex(hist_img_src, hist_img_src);
        d1 = new Double(res1);
        Mat hist_img_src2 = index.imgProce.calcHisto_gray(his);
        double res3 = Imgproc.compareHist(hist_img_src2, hist_img_src2, Imgproc.CV_COMP_CORREL);
        d3 = new Double(res3 * 100);
        this.listresult2 = index.imgProce.CompHisto1(his1, index1.listImgBDD1);
        this.listresult1 = index.imgProce.CompConteur1(coun1, index1.listImgBDD1);
        this.listresult = index.imgProce.CompTexture1(tex1, index1.listImgBDD1);
    }

}
