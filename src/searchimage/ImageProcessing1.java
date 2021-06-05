package searchimage;

import java.io.File;// type file 
import java.util.ArrayList;// array list 
import java.util.Collection;// taba3 litahto 
import java.util.Collections;// type fihe fonctions kima bach rfadna min w max men la liste 
import java.util.Iterator;// list b les adress 
import java.util.List;// list
import java.util.Vector;// vector liste victorial
import org.opencv.core.Core;// core bach t initialisé l opencv 
import org.opencv.core.CvType;// bach tchangé type 
import static org.opencv.core.CvType.CV_32F;//type 32 float
import org.opencv.core.Mat;// matrice kima taftah les images b imgproc.imread
import org.opencv.core.MatOfFloat;// mat float bach tkharaj les conteur 
import org.opencv.core.MatOfInt;// kif lifo9ha mais hadi enitier 
import org.opencv.core.MatOfPoint;// kifkif mais hadi type ta3ha point ya3ni point hhhh
import org.opencv.core.MatOfPoint2f;// hadi type point mais resultat float m3a tkharajlo ghir les 2 numero ba3d virgule 9,08 // 2f== 0.08
import org.opencv.core.Point;// type point 
import org.opencv.core.Scalar;// scaler hada bach tarssam la ligne d'histogram mais awnahinaha 
import org.opencv.core.Size;// bach tdir new Size(w,h) bach trisiziha 
import org.opencv.imgcodecs.Imgcodecs;// bach tdir lire l image 
import org.opencv.imgproc.Imgproc;// fiha des fonction kima compareHis ta3 les histogram

public class ImageProcessing1 {

    public static Double dd_2;
    public static Double d3;
    public static Double d2;
    public static Double d1;

    public ImageProcessing1() {

    }

    public void conv_gray(Mat src, Mat dst) {
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);
    }


    public Mat calcHisto_gray(Mat src) {

        Mat dstt = new Mat(400, 400, CvType.CV_32F); 
        this.conv_gray(src, dstt);
        Imgproc.resize(src, src, new Size(400, 400));
        Vector<Mat> bgr_planes = new Vector<>();
        Core.split(src, bgr_planes);

        MatOfInt histSize = new MatOfInt(256);

        final MatOfFloat histRange = new MatOfFloat(0f, 256f);

        boolean accumulate = false;

        Mat hist = new Mat();

        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
        int hist_w = 512;
        int hist_h = 15000;
        long bin_w;
        bin_w = Math.round((double) (hist_w / 256));

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);
        Core.normalize(hist, hist, 3, histImage.rows(), Core.NORM_MINMAX);
        return hist;
    }

    void listRepertoire(File repertoire, ArrayList<String> listImgBDD, String pathBDD) {
        String[] listFichier;
        listFichier = repertoire.list();
        for (int i = 0; i < listFichier.length; i++) {
            if (listFichier[i].endsWith(".jpg") == true || listFichier[i].endsWith(".png") == true || listFichier[i].endsWith(".tif") == true || listFichier[i].endsWith(".tiff") == true || listFichier[i].endsWith(".jpeg") == true || listFichier[i].endsWith(".bmp") == true) {
                listImgBDD.add(pathBDD + "\\" + listFichier[i]);
            }
        }
    }

    public ArrayList<String> CompHisto(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Histogram");
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        src.convertTo(src, CvType.CV_32F);
        //this.conv_gray(src, src);
        Mat hist_img_src = calcHisto_gray(src);
        for (String path : listImgBDD) {
            System.out.println("img " + path);
            Mat Image_DB = Imgcodecs.imread(path, 1);
            Image_DB.convertTo(Image_DB, CvType.CV_32F);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src, hist_img_DB, Imgproc.CV_COMP_CORREL);// takherajlak -1 < res < 1
            Double d = new Double(res * 100); //twalli hna -100 < res < 100  
            ListResult.add(d);// ajoutiwha f la liste des resultat 
        }
        System.out.println("liste general size " + ListResult.size());

        classement_result(ListImageOutPut, listImgBDD, ListResult);

        return ListImageOutPut;
    }

    public ArrayList<Double> CompHisto1(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Histogram");
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        src.convertTo(src, CvType.CV_32F);
        //this.conv_gray(src, src);
        Mat hist_img_src = calcHisto_gray(src);
        for (String path : listImgBDD) {
            System.out.println("img " + path);
            Mat Image_DB = Imgcodecs.imread(path, 1);
            Image_DB.convertTo(Image_DB, CvType.CV_32F);
            Mat hist_img_DB = calcHisto_gray(Image_DB);
            double res = Imgproc.compareHist(hist_img_src, hist_img_DB, Imgproc.CV_COMP_CORREL);// takherajlak -1 < res < 1
            Double d = new Double(res * 100); //twalli hna -100 < res < 100  
            ListResult.add(d);// ajoutiwha f la liste des resultat 
            
        }
        return ListResult;
    }

    public void classement_result(ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;

        Double seul = 70.0;

        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max = Collections.max(ListResult);
            if (max > seul) {

                for (Double item : ListResult) {
                    if (max == item) {
                        String image = listImgBDD.get(l);
                        System.out.println("l'image ="+image+"    valeur ="+max);
                        ListImageOutPut.add(image);
                        ListResult.set(l, -1000.0);
                        break;
                    }
                    l++;
                }
            } else {
                break;
            }
            l = 0;
        }
    }

    public void classement_result_con(double d3, ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;
        System.out.println("d3" + d3);
        for (int i = 0; i < ListResult.size(); i++) {
            ListResult.set(i, (100 * Math.abs(ListResult.get(i) - d3) / d3));
        }
        ArrayList<Double> ListResult2 = new ArrayList<>();
        Double seul = 10.0;

        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max1 = Collections.min(ListResult);
            if (max1 < seul) {
                for (Double item : ListResult) {
                    if (max1 == item) {
                        String image = listImgBDD.get(l);

                        ListImageOutPut.add(image);
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

    }

    public void classement_result_Text(ArrayList<String> ListImageOutPut, ArrayList<String> listImgBDD, ArrayList<Double> ListResult) {
        int l = 0;

        Double seul = 6.0;
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
        System.out.println("d1" + d1);
        System.out.println("d2" + d2);
        System.out.println("d3" + d3);
        ArrayList<String> ImageOutPut = new ArrayList<String>();
        for (int i = 0; i < ListResult.size(); i++) {
            ListResult.set(i, 100 * ListResult.get(i) / d1);
        }

        for (int i = 0; i < ListResult.size(); i++) {
            ListResult1.set(i, 100 - (100 * Math.abs(ListResult1.get(i) - d2) / d2));
        }

        double seul = 95;

        for (int i = 0; i < listImgBDD.size(); i++) {

            Double max = Collections.max(ListResult);
            Double max1 = Collections.max(ListResult1);
            Double max2 = Collections.max(ListResult2);

            if (max > seul || max1 > seul || max2 > seul) {
                if (max2 >= max1 || max2 >= max) {
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
                } else if (max1 >= max || max1 >= max2) {
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
                } else {

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
            } else {
                break;
            }
        }
        int i = 0;
        for (String string : ListImageOutPut) {
            if (i == 0) {
                ImageOutPut.add(string);
            } else {
                if (filtres(string, ImageOutPut)) {
                    ImageOutPut.add(string);
                }
            }
            i++;
        }

        ListImageOutPut.clear();
        ListImageOutPut.addAll(ImageOutPut);

    }

    public boolean filtres(String image, ArrayList<String> lists) {
        int i = 0;
        for (String list : lists) {
            if (list.equals(image)) {
                i = -1;
            } else {
                i++;
            }
        }
        if (i == lists.size()) {
            return true;
        } else {
            return false;
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
        Mat dsttt = new Mat(src.height(), src.width(), CvType.CV_8U);
        src.convertTo(dsttt, CvType.CV_8U); //////type convert
        Mat dstt = new Mat(src.height(), src.width(), CvType.CV_8U);
        this.conv_gray(dsttt, dstt);
        Imgproc.threshold(dstt, dstt, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
        Imgproc.resize(dstt, dstt, new Size(600, 600));
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();//liste matrice ta3 les point 
        Mat hierarchy = new Mat();
        //contours.clear();
        int mode;//
        if (externalOnly) {
            mode = Imgproc.RETR_EXTERNAL;
        } else {
            mode = Imgproc.RETR_LIST;//RETR_LIST;//RETR_TREE;//CV_RETR_FLOODFILL
        }
        int method = Imgproc.CHAIN_APPROX_NONE;
        Imgproc.findContours(dstt, contours, hierarchy, mode, method);
        return contours;
    }


    public ArrayList<String> CompConteur(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Conteur");
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        List<MatOfPoint> conteur_img_src = findContours(src, false);
        double d1 = comppcon(conteur_img_src, conteur_img_src);//
        Double dd2 = new Double(d1);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            List<MatOfPoint> conteur_img_BD = findContours(Image_DB, false);

            double res = comppcon(conteur_img_src, conteur_img_BD);
            Double d = new Double(res);
            ListResult.add(d);
        }
        //classement_result(ListImageOutPut, listImgBDD, ListResult);
        classement_result_con(dd2, ListImageOutPut, listImgBDD, ListResult);// classement des resultat hna bach naffichiwham 
        return ListImageOutPut;
    }

    public ArrayList<Double> CompConteur1(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Conteur");
        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        List<MatOfPoint> conteur_img_src = findContours(src, false);
        double d1 = comppcon(conteur_img_src, conteur_img_src);
        Double dd2 = new Double(d1);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            List<MatOfPoint> conteur_img_DB = findContours(Image_DB, false);
            double res = comppcon(conteur_img_src, conteur_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }
        dd_2 = dd2;
        return ListResult;
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
        System.out.println("img_source  "+src_ar.toString());
        System.out.println("img_bdd  "+DB_ar.toString());
        src_ar.retainAll(DB_ar);// defferent bin les conteur ta3 l'images source w le image DB
        res = src_ar.size();// hadi hiya la distance la taille ta3 la liste hiya la distance bin descripteur ta3 l'image source w limage bdd
        //System.out.println("src_ar "+res);
        return res;
    }


   
    public ArrayList<String> CompTexture(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Texture");

        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        double ecrt_type_img_src = TextureImg(src);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            
            double ecrt_type_img_DB = TextureImg(Image_DB);
            double res = compTex(ecrt_type_img_src, ecrt_type_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }
        classement_result_Text(ListImageOutPut, listImgBDD, ListResult);

        return ListImageOutPut;
    }

    public ArrayList<Double> CompTexture1(Mat src, ArrayList<String> listImgBDD) {
        System.out.println("Demaré la recherch Texture");

        ArrayList<String> ListImageOutPut = new ArrayList<String>();
        ArrayList<Double> ListResult = new ArrayList<>();
        double ecrt_type_img_src = TextureImg(src);
        for (String path : listImgBDD) {
            //System.out.println("Image  " + path);
            Mat Image_DB = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            double ecrt_type_img_DB = TextureImg(Image_DB);
            double res = compTex(ecrt_type_img_src, ecrt_type_img_DB);
            Double d = new Double(res);
            ListResult.add(d);
        }

        return ListResult;
    }

    public double TextureImg(Mat src) {
       
        Imgproc.resize(src, src, new Size(700, 700));
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
        for (int i = 100; i < 400; i++) {
            for (int j = 100; j < 400; j++) {
                double[] data = dstt1.get(i, j);
                moyenne = moyenne + data[0];
                //System.out.println("data " + data.length);
            }
        }
        moyenne = moyenne / 90000;
        System.out.println("moyenne " + moyenne);

        for (int i = 100; i < 400; i++) {
            for (int j = 100; j < 400; j++) {
                double[] data = dstt1.get(i, j);
                ecart = ecart + (moyenne - data[0]) * (moyenne - data[0]); //nombre des pixel
            }
        }
        ecart_type = Math.sqrt(ecart / 90000);
        System.out.println("ecart type  " + ecart_type);
        return ecart_type;
    }

    public double compTex(double src, double DB) {
        return Math.abs(src - DB);
    }

}
