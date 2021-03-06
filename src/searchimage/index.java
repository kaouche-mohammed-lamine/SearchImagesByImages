/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.StyleConstants.Size;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/**
 *
 * @author lamine-pacificatory
 */
public class index extends javax.swing.JFrame {

    ImageProcessing imgProce = null;
    ImageIcon image = null;
    String path = null;
    String name = null;
    static String PathDATABASE = "C:\\Users\\lamine-pacificatory\\Downloads\\DIP3E_CH01_Original_Images";
    static int nbb;
    public static int nbt = 4;
    //static String PathDATABASE = "C:\\Users\\ACER\\Pictures\\BDD_IMAGE";
    ArrayList<String> listImgBDD = null;
    ArrayList<String> listImgBDD1 = null;
    ArrayList<String> listImgBDD2 = null;
    ArrayList<String> listImgBDD3 = null;
    Mat Image_src = null; // matrice ta3 image li selectioninaha

    /**
     * Creates new form index
     */
    public index() {
        initComponents();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static ImageIcon resize(ImageIcon imageicon, int width, int height) {

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));

        g2d.drawImage(imageicon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);

    }

    public Image toBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); // get all the pixels
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.
                rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().
                getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }

    //Mat newImage = Imgcodecs.imread(filePath);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ouvrire une image", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 13), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CBIR");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("Ouvrire Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 153, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Color", "Texeteur", "Nssato" }));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Indiquer la Classe");

        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 153));
        jButton3.setText("Rechercher");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addGap(51, 51, 51))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3, jComboBox1, jLabel2, jLabel3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(29, 29, 29)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton3, jComboBox1, jLabel2, jLabel3});

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);

        jPanel4.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultat de Recherche", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2337, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jScrollPane1.setViewportView(jPanel4);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        this.setSize(1080, 720);
        this.jScrollPane1.setSize(780, 700);
        this.jPanel1.setSize(780, 3000);
        this.jPanel4.setSize(780, 3000);
        this.jPanel2.setSize(290, 700);
        this.jPanel3.setSize(256, 288);
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        imgProce = null;
        File f;
        FileInputStream fis;
        JFileChooser filechoose = new JFileChooser(PathDATABASE);
        filechoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        int resultatEnregistrer = filechoose.showDialog(this, "Open");

        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) {
            f = filechoose.getSelectedFile();
            path = f.getAbsolutePath();
            name = f.getName();
        }

        Image_src = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        imgProce = new ImageProcessing();
        Mat dst = new Mat(Image_src.height(), Image_src.width(), CvType.CV_8UC2);;
        // Mat dst = null;
//ImageIcon imagg = new ImageIcon(toBufferedImage(this.close(Image, 3,Imgproc.MORPH_RECT)));
        ImageIcon imagg;
        //dst = imgProce.calcHisto_gray(Image_src);
        imagg = new ImageIcon(toBufferedImage(Image_src));

        image = resize(imagg, 256, 288);

        jLabel1.setIcon(image);
        //Image loadedImage = toBufferedImage(image);
        //imageView.setIcon(new ImageIcon(loadedImage));
        //jTextField1.setText(path+" ; "+name);
        File repertoire = new File(PathDATABASE);
        this.listImgBDD = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        imgProce.listRepertoire1(repertoire, listImgBDD1, listImgBDD2, listImgBDD3, PathDATABASE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        jPanel1.removeAll();
        System.out.println("Selected " + jComboBox1.getSelectedIndex());
        if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please Select Type of search");
        } else if (jComboBox1.getSelectedIndex() == 1) {
            long start = System.currentTimeMillis();
            int k = 0;
            int wth = 20;
            int hght = 0;
            int wth1 = 0;
            int hght1 = 0;
           // int loop = 0;
            for (String resu : imgProce.CompHisto(Image_src, listImgBDD)) {
                //System.out.println(" Result image : " + resu);
              //  if (loop < 9) {//nahiha ila haba taffichiham kamal 
                    if (k % 3 == 0) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(515, wth);
                        jPanel1.add(panel);

                        wth += 225;
                    }
              //      k++;
              //      loop++;//hata lahnna 
              //  } else {
              //      break;
               // }
            }
            long end = System.currentTimeMillis();
            NumberFormat f = new DecimalFormat("#0.00000");
            jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
            this.jPanel4.setSize(780, wth);
        } else if (jComboBox1.getSelectedIndex() == 2) {
            long start = System.currentTimeMillis();
            int k = 0;
            int wth = 20;
            int hght = 0;
            int wth1 = 0;
            int hght1 = 0;
           // int loop = 0;
            for (String resu : imgProce.CompConteur(Image_src, listImgBDD)) {
                //System.out.println(" Result image : " + resu);
              //  if (loop < 9) {
                    if (k % 3 == 0) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(515, wth);
                        jPanel1.add(panel);

                        wth += 225;
                    }
                    k++;
                 //   loop++;
               // } else {
              //      break;
              //  }
            }
            long end = System.currentTimeMillis();
            NumberFormat f = new DecimalFormat("#0.00000");
            jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
            this.jPanel4.setSize(780, wth);
        } else if (jComboBox1.getSelectedIndex() == 3) {
            long start = System.currentTimeMillis();
            int k = 0;
            int wth = 20;
            int hght = 0;
            int wth1 = 0;
            int hght1 = 0;
           // int loop = 0;
            for (String resu : imgProce.CompTexture(Image_src, listImgBDD)) {
                //System.out.println(" Result image : " + resu);
               // if (loop < 9) {
                    if (k % 3 == 0) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(515, wth);
                        jPanel1.add(panel);

                        wth += 225;
                    }
                    k++;
                //    loop++;
             //   } else {
             //       break;
             //   }
            }
            long end = System.currentTimeMillis();
            NumberFormat f = new DecimalFormat("#0.00000");
            jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
            this.jPanel4.setSize(780, wth);
        } else if (jComboBox1.getSelectedIndex() == 4) {
            long start = System.currentTimeMillis();
            int k = 0;
            int wth = 20;
            int hght = 0;
            int wth1 = 0;
            int hght1 = 0;
          //  int loop = 0;
            for (String resu : imgProce.CompMix(Image_src, listImgBDD)) {
               // if (loop < 9) {
                    //System.out.println(" Result image : " + resu);
                    if (k % 3 == 0) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel panel = new ImagePanel(image.getImage());
                        panel.setLocation(515, wth);
                        jPanel1.add(panel);

                        wth += 225;
                    }
                    k++;
                 //   loop++;
              //  } else {
                 //   break;
              //  }
            }
            long end = System.currentTimeMillis();
            NumberFormat f = new DecimalFormat("#0.00000");
            jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
            this.jPanel4.setSize(780, wth);
        }

//780, 700
        jButton3.enable(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
