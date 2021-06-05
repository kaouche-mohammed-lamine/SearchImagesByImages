/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimg;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import static searchimg.index1.resize;

/**
 *
 * @author ACER
 */
public class Thread_Mix implements Runnable {
    ImageProcessing1 ImgPro = null;
    index1 index = null;
    public Thread_Mix(index1 index){
        this.index = index;
        ImgPro = new ImageProcessing1();
    }
    
    @Override
    public void run() {
        index.jPanel1.removeAll();
        ;
        long start = System.currentTimeMillis();
        int k = 0;
        int wth = 20;
        int hght = 0;
        int wth1 = 0;
        int hght1 = 0;
        int loop = 0;
        for (String resu : index.imgProce.CompMix(index.Image_src, index.listImgBDD)) {
            //System.out.println(" Result image : " + resu);
            if (loop < 9) {//nahiha ila haba taffichiham kamal 
                if (k % 3 == 0) {
                    Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                    ImageIcon imagg;
                    imagg = new ImageIcon(index.toBufferedImage(Image));
                    index.image = resize(imagg, 250, 220);
                    ImagePanel1 panel = new ImagePanel1(index.image.getImage());
                    panel.setLocation(5, wth);
                    index.jPanel1.add(panel);

                }
                if (k % 3 == 1) {
                    Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                    ImageIcon imagg;
                    imagg = new ImageIcon(index.toBufferedImage(Image));
                    index.image = resize(imagg, 250, 220);
                    ImagePanel1 panel = new ImagePanel1(index.image.getImage());
                    panel.setLocation(260, wth);
                    index.jPanel1.add(panel);

                }
                if (k % 3 == 2) {
                    Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                    ImageIcon imagg;
                    imagg = new ImageIcon(index.toBufferedImage(Image));
                    index.image = resize(imagg, 250, 220);
                    ImagePanel1 panel = new ImagePanel1(index.image.getImage());
                    panel.setLocation(515, wth);
                    index.jPanel1.add(panel);

                    wth += 225;
                }
                k++;
                loop++;//hata lahnna 
            } else {
                break;
            }
        }
                    long end = System.currentTimeMillis();
            NumberFormat f = new DecimalFormat("#0.00000");
            index.jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
    }
    
    
}
