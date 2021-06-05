/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author ACER
 */
public class Thread_Texteur2 implements Runnable {

    index1 index = null;
    ArrayList<Double> listresult = null;

    public Thread_Texteur2(index1 index) {
        this.index = index;

    }

    @Override
    public void run() {

        this.listresult =  index.imgProce.CompTexture1(index1.Image_src, index1.listImgBDD3) ;
    }
}
