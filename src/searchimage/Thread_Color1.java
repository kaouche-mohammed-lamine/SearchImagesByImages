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
public class Thread_Color1 implements Runnable {// implimenté interface Runnable {ta3 thread}

    index1 index = null;
    ArrayList<Double> listresult = null;
    public Thread_Color1(index1 index) { // constricteur the class implementé index1 xlass m3ah 
        this.index = index;
    }

    @Override
    public void run() { // bach kinmachiw thread ymachi had la function 
        ImageProcessing1 imgProce = new ImageProcessing1();
        this.listresult = imgProce.CompHisto1(index1.Image_src, index1.listImgBDD2);
    }
}
