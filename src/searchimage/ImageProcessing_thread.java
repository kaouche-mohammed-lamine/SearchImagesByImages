package searchimage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import jdk.nashorn.internal.ir.BreakNode;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_32F;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageProcessing_thread {

    public static Double dd_2;
    public static Double d3;
    public static Double d2;
    public static Double d1;

    public ImageProcessing_thread() {

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
        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), b_hist, histSize, histRange, accumulate);
        int hist_w = 512;
        int hist_h = 1500;
        long bin_w;
        bin_w = Math.round((double) (hist_w / 256));

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);

        Core.normalize(b_hist, b_hist, 3, histImage.rows(), Core.NORM_MINMAX);

        return histImage;
    }

    public Mat calcHisto_gray(Mat src) {
        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);
        src.convertTo(src, CvType.CV_8U);
        this.conv_gray(src, dstt);
        Imgproc.resize(dstt, dstt, new Size(400, 400));
        //Imgproc.cvtColor(src, dstt, Imgproc.COLOR_RGB2GRAY);
        Vector<Mat> bgr_planes = new Vector<>();
        Core.split(dstt, bgr_planes);

        MatOfInt histSize = new MatOfInt(256);

        final MatOfFloat histRange = new MatOfFloat(0f, 256f);

        boolean accumulate = false;

        Mat hist = new Mat();

        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
        return hist;
    }

    void listRepertoire(File repertoire, ArrayList<String> listImgBDD, ArrayList<String> listImgBDD1, ArrayList<String> listImgBDD2, String pathBDD) {
        String[] listFichier;
        listFichier = repertoire.list();
        for (int i = 0; i < (listFichier.length / 3); i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD.add(pathBDD + "\\" + listFichier[i]);
            }
        }
        for (int i = (listFichier.length / 3); i < ((listFichier.length + listFichier.length) / 3); i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD1.add(pathBDD + "\\" + listFichier[i]);
            }
        }
        for (int i = ((listFichier.length + listFichier.length) / 3); i < listFichier.length; i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD2.add(pathBDD + "\\" + listFichier[i]);
            }
        }
    }

    public ArrayList<Double> CompHisto(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Histogram");
        ArrayList<Double> ListResult = new ArrayList<>();
        src.convertTo(src, CvType.CV_32F);
        Mat hist_img_src = calcHisto_gray(src);
        for (String path : listImgBDD) {
            Mat Image_DB = Imgcodecs.imread(path, 1);
            Image_DB.convertTo(Image_DB, CvType.CV_32F);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src, hist_img_DB, Imgproc.CV_COMP_CORREL);
            Double d = new Double(res * 100);
            ListResult.add(d);
        }
        //classement_result(ListImageOutPut, listImgBDD, ListResult);

        return ListResult;//ListImageOutPut;
    }

    public void classement_result(ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;

        Double seul = 50.0;
        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max = Collections.max(ListResult);
            if (max > seul) {
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
            } else {
                break;
            }
        }
    }

    public void classement_result_con(double d3, ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;
        for (int i = 0; i < ListResult.size(); i++) {
            ListResult.set(i, (100 * Math.abs(ListResult.get(i) - d3)) / d3);
        }
        ArrayList<Double> ListResult2 = new ArrayList<>();
        Double seul = 50.0;

        for (int i = 0; i < listImgBDD.size(); i++) {

            Double min = Collections.min(ListResult);
            if (min < seul) {
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
            } else {
                break;
            }
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
        Double seul = 50.0;
        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max = Collections.min(ListResult);
            if (max < seul) {
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
            } else {
                break;
            }
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
            ListResult1.set(i, 100 - (100 * Math.abs(ListResult1.get(i) - d2) / d2));
        }
        System.out.println("123");
        double seul = 50;

        for (int i = 0; i < listImgBDD.size(); i++) {
            System.out.println("1230");
            Double max = Collections.max(ListResult);
            Double max1 = Collections.max(ListResult1);
            Double max2 = Collections.max(ListResult2);
            System.out.println("res == " + listImgBDD.get(l) + "  ;  valeur == " + max + "  ;  valeur1 == " + max1 + "  ;  valeur2 == " + max2);
            if (max > seul || max1 > seul || max2 > seul) {
                if (max2 >= max1 || max2 >= max) {
                    for (Double item : ListResult2) {
                        if (max2 == item) {
                            String image = listImgBDD.get(l);
                            System.out.println("res == " + image + "  ;  valeur == " + item);

                            ListImageOutPut.add(image);
                            ListResult2.set(l, -100.0);
                            break;
                        }
                        l++;
                    }
                    ;
                } else if (max1 >= max || max1 >= max2) {
                    for (Double item : ListResult1) {
                        if (max1 == item) {
                            String image = listImgBDD.get(l);
                            System.out.println("res == " + image + "  ;  valeur == " + item);

                            ListImageOutPut.add(image);
                            ListResult1.set(l, -100.0);
                            break;
                        }
                        l++;
                    }
                    ;
                } else {
                    System.out.println("1233");
                    for (Double item : ListResult) {
                        if (max == item) {
                            String image = listImgBDD.get(l);
                            System.out.println("res == " + image + "  ;  valeur == " + item);

                            ListImageOutPut.add(image);
                            ListResult.set(l, -100.0);
                            break;
                        }
                        l++;
                    }

                }
                l = 0;
            } else {
                break;
            }
        }


    }
    public Map CompMix(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Mix");
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        ArrayList<Double> ListResult1 = new ArrayList<>();
        ArrayList<Double> ListResult2 = new ArrayList<>();
        Map listfinal = new HashMap();
        List<MatOfPoint> hist_img_src1 = findContours(src, false);
        double res2 = comppcon(hist_img_src1, hist_img_src1);
        d2 = new Double(res2);

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
        d1 = new Double(res1);
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
        d3 = new Double(res3 * 100);
        for (String path : listImgBDD) {
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src2, hist_img_DB, Imgproc.CV_COMP_CORREL);
            Double d = new Double(res * 100);
            ListResult2.add(d);
        }

        listfinal.put("1", ListResult);
        listfinal.put("2", ListResult1);
        listfinal.put("3", ListResult2);
        //classement_result_mix(d1, d2, d3, ListImageOutPut, listImgBDD, ListResult, ListResult1, ListResult2);
        return listfinal;
    }

    public List<MatOfPoint> findContours(Mat src, boolean externalOnly) {
        Mat dsttt = new Mat(src.height(), src.width(), CvType.CV_8U);

        src.convertTo(dsttt, CvType.CV_8U); //////type convert
        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);
        this.conv_gray(dsttt, dstt);
        Imgproc.threshold(dstt, dstt, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
        //Imgproc.resize(bw, bw, new Size(600, 600));
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
        int method = Imgproc.CHAIN_APPROX_NONE;
        Imgproc.findContours(dstt, contours, hierarchy, mode, method);
        return contours;
    }

    public ArrayList<Double> CompConteur(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Conteur");

        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        List<MatOfPoint> hist_img_src = findContours(src, false);
        double d1 = comppcon(hist_img_src, hist_img_src);
        dd_2 = new Double(d1);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            List<MatOfPoint> hist_img_DB = findContours(Image_DB, false);
            double res = comppcon(hist_img_src, hist_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }
        //classement_result_con(dd_2, ListImageOutPut, listImgBDD, ListResult);// classement des resultat hna bach naffichiwham 
        return ListResult;
    }

    public double comppcon(List<MatOfPoint> img_src, List<MatOfPoint> img_DB) {
        double res = 0.0;
        ArrayList<Double> src_ar = new ArrayList<>();
        ArrayList<Double> DB_ar = new ArrayList<>();
        Iterator<MatOfPoint> conteur_src = img_src.iterator();
        Iterator<MatOfPoint> conteur_DB = img_DB.iterator();
        while (conteur_src.hasNext()) {
            MatOfPoint contour = conteur_src.next();
            double ar = Imgproc.contourArea(contour);
            src_ar.add(ar);
        }
        while (conteur_DB.hasNext()) {
            MatOfPoint contour1 = conteur_DB.next();
            double ar1 = Imgproc.contourArea(contour1);//
            DB_ar.add(ar1);
        }
        src_ar.retainAll(DB_ar);// defferent bin les conteur ta3 l'images source w le image DB
        res = src_ar.size();
        //System.out.println("src_ar "+res);
        return res;
    }

    public ArrayList<Double> CompTexture(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Texture");

        //ArrayList<String> ListImageOutPut = new ArrayList<String>();
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
        //classement_result_Text(ListImageOutPut, listImgBDD, ListResult);

        return ListResult;
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