/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author ACER
 */
public class Thread_Mix2 implements Runnable {
    index1 index = null;
            ArrayList<Double> listresult = null;
ArrayList<Double> listresult1 = null;
ArrayList<Double> listresult2 = null;

    public Thread_Mix2(index1 index){
        this.index = index;
    }
    
    @Override
    public void run() {

         this.listresult2 = index.imgProce.CompHisto1(index1.Image_src, index1.listImgBDD3);
this.listresult1 = index.imgProce.CompConteur1(index1.Image_src, index1.listImgBDD3);
this.listresult = index.imgProce.CompTexture1(index1.Image_src, index1.listImgBDD3);
    }
    
    
}
