/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import jdk.nashorn.internal.ir.BreakNode;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_32F;
import static org.opencv.core.CvType.CV_32SC1;
import static org.opencv.core.CvType.CV_8UC1;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author DELL
 */
public class ImageProcessing_1 {

    public ImageProcessing_1(){
        
    }
    
    public Mat erode(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.erode(input, outputImage, element);
        return outputImage;
    }

    public Mat dilate(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.dilate(input, outputImage, element);
        return outputImage;
    }

    public Mat open(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.morphologyEx(input, outputImage, Imgproc.MORPH_OPEN,
                element);
        return outputImage;
    }

    public Mat close(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.morphologyEx(input, outputImage, Imgproc.MORPH_CLOSE,
                element);
        return outputImage;
    }

    private Mat getKernelFromShape(int elementSize, int elementShape) {// ycréé kernal (noyou) bach y appliqué hado les mthode lifo9 
        return Imgproc.getStructuringElement(elementShape, new Size(elementSize * 2 + 1, elementSize * 2 + 1), new Point(elementSize,
                elementSize));
    }

    public void conv_gray(Mat src, Mat dst) {
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
    }

    public void conv_HSV(Mat src, Mat dst) {
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2HSV);
    }

    public void conv_HLS(Mat src, Mat dst) {
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2HLS);
    }

    public void conv_Ycrcb(Mat src, Mat dst) {
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2YCrCb);
    }

    public Mat calcHisto_colo(Mat src) {

        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);
        dstt = src;
        //this.conv_gray(src, dstt);
        //Imgproc.cvtColor(src, dstt, Imgproc.COLOR_RGB2GRAY);
        ArrayList<Mat> bgr_planes = new ArrayList<>();
        Core.split(dstt, bgr_planes);

        MatOfInt histSize = new MatOfInt(256);

        final MatOfFloat histRange = new MatOfFloat(0f, 256f);

        boolean accumulate = false;

        Mat b_hist = new Mat();
        //Mat g_hist = new Mat();
        //Mat r_hist = new Mat();

        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), b_hist, histSize, histRange, accumulate);
        //Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), g_hist, histSize, histRange, accumulate);
        //Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), r_hist, histSize, histRange, accumulate);
        int hist_w = 512;
        int hist_h = 1500;
        long bin_w;
        bin_w = Math.round((double) (hist_w / 256));

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);

        Core.normalize(b_hist, b_hist, 3, histImage.rows(), Core.NORM_MINMAX);
        //Core.normalize(g_hist, g_hist, 3, histImage.rows(), Core.NORM_MINMAX);
        //Core.normalize(r_hist, r_hist, 3, histImage.rows(), Core.NORM_MINMAX);

        for (int i = 1; i < 256; i++) {
            //Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(b_hist.get(i - 1, 0)[0])), new Point(bin_w * (i), hist_h - Math.round(Math.round(b_hist.get(i, 0)[0]))), new Scalar(255, 0, 0), 2, 8, 0);
            //Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(g_hist.get(i - 1, 0)[0])), new Point(bin_w * (i), hist_h - Math.round(Math.round(g_hist.get(i, 0)[0]))), new Scalar(0, 255, 0), 2, 8, 0);
            //Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(r_hist.get(i - 1, 0)[0])), new Point(bin_w * (i), hist_h - Math.round(Math.round(r_hist.get(i, 0)[0]))), new Scalar(0, 0, 255), 2, 8, 0);

        }
        return histImage;
    }

    public Mat calcHisto_gray(Mat src) {
        Imgproc.resize(src, src, new Size(400, 400));

        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);

        this.conv_gray(src, dstt);
        //Imgproc.cvtColor(src, dstt, Imgproc.COLOR_RGB2GRAY);
        Vector<Mat> bgr_planes = new Vector<>();
        Core.split(dstt, bgr_planes);

        MatOfInt histSize = new MatOfInt(256);

        final MatOfFloat histRange = new MatOfFloat(0f, 256f);

        boolean accumulate = false;

        Mat hist = new Mat();

        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
        int hist_w = 512;
        int hist_h = 1500;
        long bin_w;
        bin_w = Math.round((double) (hist_w / 256));

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);

        //Core.normalize(b_hist, b_hist, 3, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(hist, hist, 3, histImage.rows(), Core.NORM_MINMAX);
        /*int hist_w = 512;
        int hist_h = 1500;
        long bin_w;
        bin_w = Math.round((double) (hist_w / 256));

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);

        Core.normalize(b_hist, b_hist, 3, histImage.rows(), Core.NORM_MINMAX);

        for (int i = 1; i < 256; i++) {
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(b_hist.get(i - 1, 0)[0])), new Point(bin_w * (i), hist_h - Math.round(Math.round(b_hist.get(i, 0)[0]))), new Scalar(255, 0, 0), 2, 8, 0);

        }
        return histImage;*/
        return hist;
    }

    void listRepertoire(File repertoire, ArrayList<String> listImgBDD, ArrayList<String> listImgBDD1, ArrayList<String> listImgBDD2, String pathBDD) {
        String[] listFichier;
        listFichier = repertoire.list();
        for (int i = 0; i < listFichier.length/3; i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD.add(pathBDD + "\\" + listFichier[i]);
            }
        }
                for (int i = 0; i < listFichier.length/3; i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD.add(pathBDD + "\\" + listFichier[i]);
            }
        }
                        for (int i = 0; i < listFichier.length/3; i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD.add(pathBDD + "\\" + listFichier[i]);
            }
        }
    }

    public ArrayList<String> CompHisto(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Histogram");

        /*Thread TH = new Thread(){
            for (int l = 0 ; l < 10 ;l++) {
            Mat Image_DB = Imgcodecs.imread(path, 1);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src, hist_img_DB, Imgproc.CV_COMP_CORREL);
            Double d = new Double(res * 100);
            ListResult.add(d);
        }
        };
        TH.start();
        
        */
        
        
        
        
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        Mat hist_img_src = calcHisto_gray(src);
        for (String path : listImgBDD) {
            Mat Image_DB = Imgcodecs.imread(path, 1);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src, hist_img_DB, Imgproc.CV_COMP_CORREL);
            Double d = new Double(res * 100);
            ListResult.add(d);
        }
        classement_result(ListImageOutPut, listImgBDD, ListResult);

        return ListImageOutPut;
    }

    public void classement_result(ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;
        for (int i = 0; i < listImgBDD.size(); i++) {
            System.out.println("res == " + listImgBDD.get(i) + "  ;  valeur == " + ListResult.get(i));
        }
        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max = Collections.max(ListResult);
            for (Double item : ListResult) {
                if (max == item) {
                    String image = listImgBDD.get(l);
                    ListImageOutPut.add(image);
                    ListResult.set(l, -1000.0);
                    break;
                }
                l++;
            }
            l = 0;
        }
    }

    public void classement_result_con(double d3, ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;
        for (int i = 0; i < ListResult.size(); i++) {
            System.out.println("before = "+ListResult.get(i));
            ListResult.set(i,  (100 * Math.abs(ListResult.get(i) - d3)) / d3);
            System.out.println("after  = "+(100 * Math.abs(ListResult.get(i) - d3)) / d3);
        }
        ArrayList<Double> ListResult2 = new ArrayList<>();
        for (int i = 0; i < listImgBDD.size(); i++) {

            Double min = Collections.min(ListResult);
            for (Double item : ListResult) {
                if (min == item) {
                    String image = listImgBDD.get(l);
                    ListImageOutPut.add(image);
                    ListResult2.add(min);
                    ListResult.set(l, +1000.0);
                    break;
                }
                l++;
            }
            l = 0;
        }
        for (int i = 0; i < ListImageOutPut.size(); i++) {
            System.out.println("res == " + ListImageOutPut.get(i) + "  ;  valeur == " + ListResult2.get(i));
        }
    }

    public void classement_result_Text(ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;

        for (int i = 0; i < listImgBDD.size(); i++) {
            System.out.println("res == " + listImgBDD.get(i) + "  ;  valeur == " + ListResult.get(i));
        }
        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max = Collections.min(ListResult);
            for (Double item : ListResult) {
                if (max == item) {
                    String image = listImgBDD.get(l);
                    ListImageOutPut.add(image);
                    ListResult.set(l, 1000000.0);
                    break;
                }
                l++;
            }
            l = 0;
        }
    }

    public void classement_result_mix(Double d1, Double d2, Double d3, ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult, ArrayList<Double> ListResult1, ArrayList<Double> ListResult2) {
        int l = 0;
        System.out.println("12");
        for (int i = 0; i < ListResult.size(); i++) {
            ListResult.set(i, 100 * ListResult.get(i) / d1);
        }
        System.out.println("122");
        for (int i = 0; i < ListResult.size(); i++) {
            System.out.println("Beffor "+ListResult1.get(i));
            ListResult1.set(i, 100 - (100 * Math.abs(ListResult1.get(i)-d2) / d2));
            System.out.println(" after "+(100 - (100 * Math.abs(ListResult1.get(i)-d2) / d2)));
        }
        System.out.println("123");
        for (int i = 0; i < listImgBDD.size(); i++) {
            System.out.println("1230");
            Double max = Collections.max(ListResult);
            Double max1 = Collections.max(ListResult1);
            Double max2 = Collections.max(ListResult2);
            if (max2 >= max1 || max2 >= max) {
                System.out.println("1231       i = "+i+"        max2="+max2);
                for (Double item : ListResult2) {
                    if (max2 == item) {
                        String image = listImgBDD.get(l);
                        ListImageOutPut.add(image);
                        ListResult2.set(l, -100.0);
                        break;
                    }
                    l++;
                }
                ;
            }else if (max1 >= max || max1 >= max2) {
                System.out.println("1232       i = "+i+"        max2="+max1);
                for (Double item : ListResult1) {
                    if (max1 == item) {
                        String image = listImgBDD.get(l);
                        ListImageOutPut.add(image);
                        ListResult1.set(l, -100.0);
                        break;
                    }
                    l++;
                }
                ;
            }else {
                System.out.println("1233");
                for (Double item : ListResult) {
                    if (max == item) {
                        String image = listImgBDD.get(l);
                        ListImageOutPut.add(image);
                        ListResult.set(l, -100.0);
                        break;
                    }
                    l++;
                }
                    
            }
            l = 0;
        }
    }

    public ArrayList<String> CompMix(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Mix");
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        ArrayList<Double> ListResult1 = new ArrayList<>();
        ArrayList<Double> ListResult2 = new ArrayList<>();

        List<MatOfPoint> hist_img_src1 = findContours(src, false);
        double res2 = comppcon(hist_img_src1, hist_img_src1);
        Double d2 = new Double(res2);

        for (String path : listImgBDD) {
            ////System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            List<MatOfPoint> hist_img_DB = findContours(Image_DB, false);
            double res = comppcon(hist_img_src1, hist_img_DB);
            Double d = new Double(res);
            ListResult1.add(d);
        }

        double hist_img_src = TextureImg(src);
        double res1 = compTex(hist_img_src, hist_img_src);
        Double d1 = new Double(res1);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            double hist_img_DB = TextureImg(Image_DB);
            double res = compTex(hist_img_src, hist_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }

        Mat hist_img_src2 = calcHisto_gray(src);
        double res3 = Imgproc.compareHist(hist_img_src2, hist_img_src2, Imgproc.CV_COMP_CORREL);
        Double d3 = new Double(res3 * 100);
        for (String path : listImgBDD) {
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src2, hist_img_DB, Imgproc.CV_COMP_CORREL);
            Double d = new Double(res * 100);
            ListResult2.add(d);
        }

        classement_result_mix(d1, d2, d3, ListImageOutPut, listImgBDD, ListResult, ListResult1, ListResult2);

        return ListImageOutPut;
    }

    public List<MatOfPoint> findContours(Mat src, boolean externalOnly) {
        
        src.convertTo(src, CvType.CV_8U);
        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);
        this.conv_gray(src, dstt);

        Imgproc.resize(dstt, dstt, new Size(600, 600));
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        //contours.clear();
        int mode;
        if (externalOnly) {
            mode = Imgproc.RETR_EXTERNAL;
        } else {
            mode = Imgproc.RETR_LIST;//RETR_LIST;//RETR_TREE;//CV_RETR_FLOODFILL
        }
        int method = Imgproc.CHAIN_APPROX_SIMPLE;
        Imgproc.findContours(dstt, contours, hierarchy, 1, method);
        return contours;
    }

    public ArrayList<String> CompConteur(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Conteur");

        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        List<MatOfPoint> hist_img_src = findContours(src, false);
        double d1 = comppcon(hist_img_src, hist_img_src);
        Double dd2 = new Double(d1);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            List<MatOfPoint> hist_img_DB = findContours(Image_DB, false);
            double res = comppcon(hist_img_src, hist_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }
        classement_result_con(dd2, ListImageOutPut, listImgBDD, ListResult);// classement des resultat hna bach naffichiwham 
        return ListImageOutPut;
    }

    public double comppcon(List<MatOfPoint> hist_img_src, List<MatOfPoint> hist_img_DB) {
        double res = 0.0;
        ArrayList<Double> src_ar = new ArrayList<>();
        ArrayList<Double> DB_ar = new ArrayList<>();
        Iterator<MatOfPoint> conteur_src = hist_img_src.iterator();
        Iterator<MatOfPoint> conteur_DB = hist_img_DB.iterator();
        while (conteur_src.hasNext()) {
            MatOfPoint contour = conteur_src.next();
            double ar = Imgproc.contourArea(contour);
            src_ar.add(ar);
        }
        while (conteur_DB.hasNext()) {
            MatOfPoint contour1 = conteur_DB.next();
            double ar1 = Imgproc.contourArea(contour1);
            DB_ar.add(ar1);
        }
        src_ar.retainAll(DB_ar);// defferent bin les conteur ta3 l'images source w le image DB
        res = src_ar.size();
        //System.out.println("src_ar "+res);
        return res;
    }

    public ArrayList<String> CompTexture(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Texture");

        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        double hist_img_src = TextureImg(src);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            double hist_img_DB = TextureImg(Image_DB);
            double res = compTex(hist_img_src, hist_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }
        classement_result_Text(ListImageOutPut, listImgBDD, ListResult);

        return ListImageOutPut;
    }

    public double TextureImg(Mat src) {
        // Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);
        Imgproc.resize(src, src, new Size(400, 400));
        src.convertTo(src, CvType.CV_32F);
        double sig = 7.0, th = 31, lm = 5.0, gm = 0.50, ps = 0;//wavelentation(lambda);orientation(theta);gaussvar(sigma);phaseoffset(phi);aspectratio(gamma)
        int kernel_size = 5;
        double theta = Math.PI / 4;
        Mat kernal = Imgproc.getGaborKernel(new Size(kernel_size, kernel_size), sig, theta, lm, gm, ps, CV_32F);
        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_32F);
        Imgproc.filter2D(src, dstt, CV_32F, kernal);
        Mat dstt1 = new Mat(dstt.height(), dstt.width(), CvType.CV_32F);
        this.conv_gray(dstt, dstt1);
        double moyenne = 0;
        double ecart_type = 0;
        double ecart = 0;
        for (int i = 100; i < 300; i++) {
            for (int j = 100; j < 300; j++) {
                double[] data = dstt1.get(i, j);
                moyenne = moyenne + data[0];
                //System.out.println("data " + data.length);
            }
        }
        moyenne = moyenne / 40000;
        //System.out.println("moyenne " + moyenne);

        for (int i = 100; i < 300; i++) {
            for (int j = 100; j < 300; j++) {
                double[] data = dstt1.get(i, j);
                ecart = ecart + (moyenne - data[0]) * (moyenne - data[0]);
            }
        }
        ecart_type = Math.sqrt(ecart / 40000);
        //System.out.println("ecart type  " + ecart_type);
        return ecart_type;
    }

    public double compTex(double src, double DB) {
        return Math.abs(src - DB);
    }


}
