/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimg;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author ACER
 */
public class index1 extends javax.swing.JFrame {
    static int nbb;
    public static int nbt=4;
    ImageProcessing1 imgProce = null;
    ImageIcon image = null;
    String path = null;
    String name = null;
    static String PathDATABASE = "C:\\Users\\lamine-pacificatory\\Downloads\\DIP3E_CH01_Original_Images";
    ArrayList<String> listImgBDD = null;
    Mat Image_src = null; // matrice ta3 image li selectioninaha

    /**
     * Creates new form index
     */
    public index1() {
        //changer_theme(0);
        initComponents();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //activi library
        
    }

    //------------- Theme --------------------
    
    
    
    
    
    public void changer_theme(int i){
        PrintStream sor = null;
        try {
            sor = new PrintStream(new File("src/char_theme.txt"));
            sor.print(i);
           nbt=i;
            if(i==1){
             
                try {
                    UIManager.setLookAndFeel(new AluminiumLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
                }
                SwingUtilities.updateComponentTreeUI(this);
                this.pack();
                /*jRadioButtonMenuItem1.setSelected(true);
                jRadioButtonMenuItem2.setSelected(false);
                jRadioButtonMenuItem3.setSelected(false);
                jRadioButtonMenuItem4.setSelected(false);*/
            }else if(i==2){
               try {
                    
                    UIManager.setLookAndFeel(new NimRODLookAndFeel());
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
            /*jRadioButtonMenuItem1.setSelected(false);*/
            //jRadioButtonMenuItem2.setSelected(true);
           /* jRadioButtonMenuItem3.setSelected(false);
            jRadioButtonMenuItem4.setSelected(false);*/
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else if(i==3){
                try {
                    
                    UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
             /*jRadioButtonMenuItem1.setSelected(false);
            jRadioButtonMenuItem2.setSelected(false);*/
            //jRadioButtonMenuItem3.setSelected(true);
            /*jRadioButtonMenuItem4.setSelected(false);*/
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else if(i==4){
               try {
                    
                    UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
            /* jRadioButtonMenuItem1.setSelected(false);
            jRadioButtonMenuItem2.setSelected(false);
            jRadioButtonMenuItem3.setSelected(false);*/
            //jRadioButtonMenuItem1.setSelected(true);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sor.close();
        }
    }
    public static void theme_dem(int i){
        if(i==1){
         
                  try {
                
                UIManager.setLookAndFeel(new AluminiumLookAndFeel());
               
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(i==2){
           try {
                
                UIManager.setLookAndFeel(new NimRODLookAndFeel());
                
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(i==3){
            try {
                
                UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
               
        
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else if(i==4){
           try {
                
                UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
               
        
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    
    
    
    
    //---------------Fin Theme -------
    
    
    
    
    
    
    
    
    public static ImageIcon resize(ImageIcon imageicon, int width, int height) {  //resize les image

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));

        g2d.drawImage(imageicon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);

    }

    public Image toBufferedImage(Mat matrix) { // t7awel image matrice image baferimage
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); // get all the pixels
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
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
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Color", "Contour", "Texeture", "Mix(Tous Methods)" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Le Temps : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3, jComboBox1, jLabel2, jLabel3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
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
            .addGap(0, 2815, Short.MAX_VALUE)
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

        jMenuItem1.setText("Save");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Avec Thread");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Color");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Conteur");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Texteur");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Mix (Tous Methodes)");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Themes");

        jMenuItem9.setText("Original Theme");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem6.setText("Theme 1");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Theme 2");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Theme 3");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        this.setSize(1080, 800);
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
        JFileChooser filechoose = new JFileChooser(new File(PathDATABASE));
        filechoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "bmp", "tif", "tiff"));
        int resultatEnregistrer = filechoose.showDialog(this, "Open");

        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) {
            f = filechoose.getSelectedFile();
            path = f.getAbsolutePath();
            name = f.getName();
        }
        System.out.println("print path :" + path);
        Image_src = Imgcodecs.imread(path, 1);
        imgProce = new ImageProcessing1();
        //Mat dst = new Mat(Image_src.height(), Image_src.width(), CvType.CV_8UC2);;
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
        listImgBDD = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton3.enable(false);
        
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
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
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
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
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
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
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
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(5, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 1) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
                        panel.setLocation(260, wth);
                        jPanel1.add(panel);

                    }
                    if (k % 3 == 2) {
                        Mat Image = Imgcodecs.imread(resu, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        ImageIcon imagg;
                        imagg = new ImageIcon(toBufferedImage(Image));
                        image = resize(imagg, 250, 220);
                        ImagePanel1 panel = new ImagePanel1(image.getImage());
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

        String pathh = null;
        System.out.println("path :" + this.path);
        JFileChooser filechosser = new JFileChooser(new File(path));
        filechosser.setDialogTitle("Save a File ");
        filechosser.setSelectedFile(new File(name));
        filechosser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        filechosser.setName(name);
        int result = filechosser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            try {
                File f = filechosser.getSelectedFile();
                pathh = f.getAbsolutePath();
                DataInputStream dis = new DataInputStream(new FileInputStream(this.path));//ta9ra  data ta3 fichier selectioné 
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();//yconverti data l buffer

                int nRead;
                byte[] data = new byte[dis.available()]; // y cree byte bach yhatt data 

                while ((nRead = dis.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);// y ktab buffer f data 
                }

                buffer.flush();// yrattabhom b flush 

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dataOutStream = new DataOutputStream(baos);
                dataOutStream.write(data);// yaktab data f fichier ta3na 

                OutputStream outputStream = new FileOutputStream(pathh);
                baos.writeTo(outputStream);// hna y 3awad yhatt les buffer f fichier lajdid
                baos.close(); //Lets close some streams 
                dataOutStream.close();
                outputStream.close();
                buffer.close();
                dis.close();

            } catch (IOException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            }
        }

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        String pathh = null;
        System.out.println("path :" + this.path);
        JFileChooser filechosser = new JFileChooser(new File(path));
        filechosser.setDialogTitle("Save a File ");
        filechosser.setSelectedFile(new File(name));
        filechosser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        filechosser.setName(name);
        int result = filechosser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            try {
                File f = filechosser.getSelectedFile();
                pathh = f.getAbsolutePath();
                DataInputStream dis = new DataInputStream(new FileInputStream(this.path));
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[dis.available()];

                while ((nRead = dis.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dataOutStream = new DataOutputStream(baos);
                dataOutStream.write(data);

                OutputStream outputStream = new FileOutputStream(pathh);
                baos.writeTo(outputStream);
                baos.close(); //Lets close some streams 
                dataOutStream.close();
                outputStream.close();
                buffer.close();
                dis.close();

            } catch (IOException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            }
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Thread_Color Th_C = new Thread_Color(this);// crré object men class Thread_color
        Thread th = new Thread(Th_C);// creé Thread w implementilo la class Thread_color 

        th.start();// demaré thread 
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        Thread_Conteur Th_C = new Thread_Conteur(this);
        Thread th = new Thread(Th_C);

        th.start();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        Thread_Texteur Th_C = new Thread_Texteur(this);
        Thread th = new Thread(Th_C);

        th.start();

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Thread_Mix Th_C = new Thread_Mix(this);
        Thread th = new Thread(Th_C);

        th.start();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed


        changer_theme(1);

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        changer_theme(2);

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        changer_theme(3);

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        changer_theme(4);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

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
            java.util.logging.Logger.getLogger(index1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(index1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(index1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(index1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            
            
            public void run() {
                theme_dem(4);
                new index1().setVisible(true);
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
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
