/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import searchimage.ImagePanel1;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import java.awt.Graphics2D;// pour ajouté ( ترسم) les images resultat // Graphics2D est une classe qui étend les possibilités de dessin de la cGraphicsenlasse  2 dimensions.
import java.awt.Image;// pour ajouté limage dans ImageIcon -> jLabel
import java.awt.RenderingHints;//pour améliorer l'image (matakhessarch la qualtié )est un autre attribut graphique utilisé par la classe Graphics2D.
import java.awt.image.BufferedImage;// list (ou bien Table) de donnes image   il est utilisé pour gérer et manipuler les donnees d'image 
import java.awt.image.DataBufferByte;// list (ou bien table ) de byte donnes  stocke les données en interne sous forme d'octets
import java.io.ByteArrayOutputStream;// array list ta3 output 
import java.io.DataInputStream;// pour read data dimage 
import java.io.DataOutputStream;// pour ecrir data d'image 
import java.io.File;// pour cree et lire fichier ou bien dossier
import java.io.FileInputStream;// pour ecrir dans le fichier
import java.io.FileNotFoundException;// try catch exception pour fichier
import java.io.FileOutputStream;// pour lire un fichier
import java.io.IOException;// try catch exception input output 
import java.io.OutputStream;// lire un ficher
import java.io.PrintStream;// afficher 
import java.text.DecimalFormat;// pour laffichage de temps 
import java.text.NumberFormat;// pour laffichage de temps
import java.text.ParseException;// try catch pour le dicemal et number format 
import java.util.ArrayList;// liste 
import java.util.Collection;// pour classé resultat (collections.max ou bien min)
import java.util.Set;//Set pour lire le map donnes  
import java.util.Iterator;// interpritor pour get data in Map 
import java.util.Map; // type de donnes (Key , Valeur)
import java.util.logging.Level;//mayhamouch
import java.util.logging.Logger;//mayhamouch
import javax.swing.ImageIcon;//pour ajouter limage dans JLabel
import javax.swing.JFileChooser;// pour chaoisé le fichier (image selectioné)
import javax.swing.JOptionPane;// afficher les message area 
import javax.swing.SwingUtilities;//theme
import javax.swing.UIManager;//theme
import javax.swing.UnsupportedLookAndFeelException;//exception try catch ta3 theme
import javax.swing.filechooser.FileNameExtensionFilter;// pour afficher les type images (jpg,png ...)
import org.opencv.core.Core;// pour demaré opencv
import org.opencv.core.Mat;// type Mat (Matrice) des image overir 
import org.opencv.imgcodecs.Imgcodecs;// un class fiha des methode par exmple imread imshow ...


public class index1 extends javax.swing.JFrame {

    static int nbb;//utilisé dans les function them
    public static int nbt = 4;//utilisé dans les function them
    ImageProcessing1 imgProce = null;// cree objet la class ImageProcessing1
    ImageProcessing_thread imgProce1 = null;// cree objet la class ImageProcessing_thread
    ImageIcon image = null; // pour ajouté limage dans l'interface
    String path = null;//path image source
    String name = null;//name image source
    static String PathDATABASE = "D:\\lamine\\Photo\\Camera\\New Folder\\My picture"; //path BDD images
    static ArrayList<String> listImgBDD = null;//list image de BDD
    static ArrayList<String> listImgBDD1 = null;// list image de BDD/3
    static ArrayList<String> listImgBDD2 = null;// list image de BDD/3
    static ArrayList<String> listImgBDD3 = null;// list image de BDD/3
    static Mat Image_src = null; // matrice ta3 image li selectioninaha

    public index1() {
        //changer_theme(0);
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //activi library

        initComponents();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //activi library

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

    //------------- Theme --------------------
    public void changer_theme(int i) {
        PrintStream sor = null;
        try {
            sor = new PrintStream(new File("src/char_theme.txt"));
            sor.print(i);
            nbt = i;
            if (i == 1) {

                try {
                    UIManager.setLookAndFeel(new AluminiumLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
                }
                SwingUtilities.updateComponentTreeUI(this);
                this.pack();

            } else if (i == 2) {
                try {

                    UIManager.setLookAndFeel(new NimRODLookAndFeel());
                    SwingUtilities.updateComponentTreeUI(this);
                    this.pack();

                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (i == 3) {
                try {

                    UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
                    SwingUtilities.updateComponentTreeUI(this);
                    this.pack();

                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (i == 4) {
                try {

                    UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
                    SwingUtilities.updateComponentTreeUI(this);
                    this.pack();

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

    public static void theme_dem(int i) {
        if (i == 1) {

            try {

                UIManager.setLookAndFeel(new AluminiumLookAndFeel());

            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (i == 2) {
            try {

                UIManager.setLookAndFeel(new NimRODLookAndFeel());

            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (i == 3) {
            try {

                UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());

            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (i == 4) {
            try {

                UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());

            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //---------------Fin Theme ---------------------------------------
    public static ImageIcon resize(ImageIcon imageicon, int width, int height) {  //resize les imagepour ajoutiha f jLabel

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);// bufferImage bach y3awad ydirlha resize
        Graphics2D g2d = (Graphics2D) bi.createGraphics();// bach y3awad yarssam limage 
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));// bach matrohch la qualité

        g2d.drawImage(imageicon.getImage(), 0, 0, width, height, null);// yarssam limage jdiddaa
        g2d.dispose();//y3awad yssatafha 
        return new ImageIcon(bi);//resultat litakheraj wnhatoha f jLabel

    }

    public Image toBufferedImage(Mat matrix) { // t7awel image matrice image baferimage
        int type = BufferedImage.TYPE_BYTE_GRAY;// kitkon limage gris
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;// kitkon limage RGb
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows(); // la taille ta3 buffer
        byte[] buffer = new byte[bufferSize];//table byte[]  pour les donnes image 
        matrix.get(0, 0, buffer); // get all the pixels
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type); //cree neveau image bufferImage
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();//get pixels buffer 
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);//copy pixels f buffer 
        return image;// resultat pour ajouté f jLabel
    }

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
        jLabel5 = new javax.swing.JLabel();
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

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ouvrire une image", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 13), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel2.setFont(new java.awt.Font("David", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
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
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Ouvrire Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 153, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Couleur", "Contour", "Texeture", "(Couleur/Contour/Texeture)" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("Indiquer la Classe");

        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 51));
        jButton3.setText("Rechercher");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("Le Temps : ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setText("Résultat Trouvé :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
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
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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

        ImageIcon imagg;

        imagg = new ImageIcon(toBufferedImage(Image_src));

        image = resize(imagg, 256, 288);

        jLabel1.setIcon(image);
        imgProce1 = new ImageProcessing_thread();
        File repertoire = new File(PathDATABASE);
        listImgBDD = new ArrayList<String>();
        listImgBDD1 = new ArrayList<String>();
        listImgBDD2 = new ArrayList<String>();
        listImgBDD3 = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        imgProce1.listRepertoire(repertoire, listImgBDD1, listImgBDD2, listImgBDD3, PathDATABASE);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//activi opencv
        File repertoire = new File(PathDATABASE);
        listImgBDD = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        jButton3.enable(false);
        if (path != null) {
            Image_src = Imgcodecs.imread(path, 1);
            jPanel1.removeAll();
            jPanel1.revalidate();
            jPanel1.repaint();
            if (jComboBox1.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "SVP Selectionner Type recherche !!");
            } else if (jComboBox1.getSelectedIndex() == 1) {

                long start = System.currentTimeMillis();//demari calculé le temps de exicution
                int k = 0;
                int wth = 20;
                int loop = 0;
                ArrayList<String> resultat = imgProce.CompHisto(Image_src, listImgBDD);
                jLabel5.setText("Trouvé : " + resultat.size() + " resultat.");
                for (String resu : resultat) {
                    if (loop < listImgBDD.size()) {
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
                        loop++;//hata lahnna 
                    } else {
                        break;
                    }
                }
                long end = System.currentTimeMillis();
                NumberFormat f = new DecimalFormat("#0.00000");
                jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
                this.jPanel4.setSize(780, wth);
            } else if (jComboBox1.getSelectedIndex() == 2) {

                long start = System.currentTimeMillis();
                int k = 0;
                int wth = 20;
                int loop = 0;

                ArrayList<String> result = imgProce.CompConteur(Image_src, listImgBDD);
                jLabel5.setText("Trouvé : " + result.size() + " resultat.");
                for (String resu : result) {
                    if (loop < listImgBDD.size()) {
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
                        loop++;
                    } else {
                        break;
                    }
                }
                long end = System.currentTimeMillis();
                NumberFormat f = new DecimalFormat("#0.00000");
                jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
                this.jPanel4.setSize(780, wth);
            } else if (jComboBox1.getSelectedIndex() == 3) {

                long start = System.currentTimeMillis();
                int k = 0;
                int wth = 20;                
                int loop = 0;
                ArrayList<String> resultat = imgProce.CompTexture(Image_src, listImgBDD);
                jLabel5.setText("Trouvé : " + resultat.size() + " resultat.");
                for (String resu : imgProce.CompTexture(Image_src, listImgBDD)) {
                    if (loop < listImgBDD.size()) {
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
                        loop++;
                    } else {
                        break;
                    }
                }
                long end = System.currentTimeMillis();
                NumberFormat f = new DecimalFormat("#0.00000");
                jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
                this.jPanel4.setSize(780, wth);
            } else if (jComboBox1.getSelectedIndex() == 4) {

                long start = System.currentTimeMillis();
                int k = 0;
                int wth = 20;
                int loop = 0;
                ArrayList<String> resultat = imgProce.CompMix(Image_src, listImgBDD);
                jLabel5.setText("Trouvé : " + resultat.size() + " resultat.");
                for (String resu : imgProce.CompMix(Image_src, listImgBDD)) {
                    if (loop < listImgBDD.size()) {
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
                        loop++;
                    } else {
                        break;
                    }
                }
                long end = System.currentTimeMillis();
                NumberFormat f = new DecimalFormat("#0.00000");
                jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
                this.jPanel4.setSize(780, wth);
            }

//780, 700
            jButton3.enable(true);
        } else {
            JOptionPane.showMessageDialog(null, "SVP selectionner l'Image !!");
        }
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
if (this.path != null){
        String pathh = null;
        System.out.println("path :" + this.path);
        JFileChooser filechosser = new JFileChooser(new File(path));
        filechosser.setDialogTitle("Sauvegarder l'Image Source");
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
                JOptionPane.showMessageDialog(null, "L'image est bien Sauvegarder ");
            } catch (IOException ex) {
                Logger.getLogger(index1.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            }
        }
        
}else{
    JOptionPane.showMessageDialog(null, "SVP Selectionner l'Image Source !! ");
}
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Image_src = Imgcodecs.imread(path, 1);
        File repertoire = new File(PathDATABASE);
        listImgBDD = new ArrayList<String>();
        listImgBDD1 = new ArrayList<String>();
        listImgBDD2 = new ArrayList<String>();
        listImgBDD3 = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        imgProce1.listRepertoire(repertoire, listImgBDD1, listImgBDD2, listImgBDD3, PathDATABASE);
        ArrayList<Double> listeresult;//cree liste ndiro fiha les resultat
        listeresult = new ArrayList<>();

        long start = System.currentTimeMillis();
        Thread_Color Th_C = new Thread_Color(this);// crré object men class Thread_color
        Thread th = new Thread(Th_C);// creé Thread w implementilo la class Thread_color 
        th.start();// demaré thread 

        Thread_Color1 Th_C1 = new Thread_Color1(this);
        Thread th1 = new Thread(Th_C1);
        th1.start();

        Thread_Color2 Th_C2 = new Thread_Color2(this);
        Thread th2 = new Thread(Th_C2);
        th2.start();

        try {
            th.join();    //join : ki ykmelo les thred f tratement
        } catch (Exception e) {
        }

        try {
            th1.join();

        } catch (Exception e) {
        }

        try {
            th2.join();
        } catch (Exception e) {
        }

        listeresult.addAll(Th_C.listresult);
        listeresult.addAll(Th_C1.listresult);
        listeresult.addAll(Th_C2.listresult);
        System.out.println("list Thread 1 " + Th_C.listresult.size());
        System.out.println("list Thread 2 " + Th_C1.listresult.size());
        System.out.println("list Thread 3 " + Th_C2.listresult.size());

        System.out.println("list Thread general" + listeresult.size());

        System.out.println("list Thread general" + listeresult);
        ArrayList<String> ListImageOutPut = new ArrayList<String>();//cree liste sort ta3 classement
        imgProce.classement_result(ListImageOutPut, listImgBDD, listeresult);
        jPanel1.removeAll();
        int k = 0;
        int wth = 20;
        int hght = 0;
        int wth1 = 0;
        int hght1 = 0;
        //  int loop = 0;
        jLabel5.setText("Trouvé : " + ListImageOutPut.size() + " resultat.");
        for (String resu : ListImageOutPut) {
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
        th.stop();
        th1.stop();
        th2.stop();

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Image_src = Imgcodecs.imread(path, 1);
        File repertoire = new File(PathDATABASE);
        listImgBDD = new ArrayList<String>();
        listImgBDD1 = new ArrayList<String>();
        listImgBDD2 = new ArrayList<String>();
        listImgBDD3 = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        imgProce1.listRepertoire(repertoire, listImgBDD1, listImgBDD2, listImgBDD3, PathDATABASE);
        long start = System.currentTimeMillis();
        Thread_Conteur Th_C = new Thread_Conteur(this);// crré object men class Thread_contour
        Thread th = new Thread(Th_C);// creé Thread w implementilo la class Thread_contour 
        th.start();// demaré thread 

        Thread_Conteur1 Th_C1 = new Thread_Conteur1(this);
        Thread th1 = new Thread(Th_C1);
        th1.start();

        Thread_Conteur2 Th_C2 = new Thread_Conteur2(this);
        Thread th2 = new Thread(Th_C2);
        th2.start();
        try {
            th.join();    //join : ki ykmelo les thred f tratement
        } catch (Exception e) {
        }
        try {
            th1.join();
        } catch (Exception e) {
        }
        try {
            th2.join();
        } catch (Exception e) {
        }

        System.out.println("list Thread 1 " + Th_C.listresult.size());
        System.out.println("list Thread 2 " + Th_C1.listresult.size());
        System.out.println("list Thread 3 " + Th_C2.listresult.size());

        ArrayList<Double> listeresult = new ArrayList<>();//cree liste ndiro fiha les resultat
        listeresult.addAll(Th_C.listresult);// resultat men thread 1 ajoutiwham f liste listeresult
        listeresult.addAll(Th_C1.listresult);// resultat men thread 2 ajoutiwham f liste listeresult
        listeresult.addAll(Th_C2.listresult);// resultat men thread 3 ajoutiwham f liste listeresult
        ArrayList<String> ListImageOutPut = new ArrayList<String>();//cree liste sort ta3 classement
        System.out.println("dd_2" + ImageProcessing1.dd_2);
        imgProce.classement_result_con(ImageProcessing1.dd_2, ListImageOutPut, listImgBDD, listeresult);// classement des resultat conteur avec les thread 
        jPanel1.removeAll();
        System.out.println(ListImageOutPut);
        int k = 0;
        int wth = 20;
        int hght = 0;
        int wth1 = 0;
        int hght1 = 0;
        //  int loop = 0;
        jLabel5.setText("Trouvé : " + ListImageOutPut.size() + " resultat.");
        for (String resu : ListImageOutPut) {
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
        th.stop();
        th1.stop();
        th2.stop();

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Image_src = Imgcodecs.imread(path, 1);
File repertoire = new File(PathDATABASE);
        listImgBDD = new ArrayList<String>();
        listImgBDD1 = new ArrayList<String>();
        listImgBDD2 = new ArrayList<String>();
        listImgBDD3 = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        imgProce1.listRepertoire(repertoire, listImgBDD1, listImgBDD2, listImgBDD3, PathDATABASE);
        long start = System.currentTimeMillis();
        Thread_Texteur Th_C = new Thread_Texteur(this);// crré object men class Thread_texteur
        Thread th = new Thread(Th_C);// creé Thread w implementilo la class Thread_texteur 
        th.start();// demaré thread 

        Thread_Texteur1 Th_C1 = new Thread_Texteur1(this);
        Thread th1 = new Thread(Th_C1);
        th1.start();

        Thread_Texteur2 Th_C2 = new Thread_Texteur2(this);
        Thread th2 = new Thread(Th_C2);
        th2.start();// demaré thread 
        ArrayList<Double> listeresult = new ArrayList<>();
        try {
            th.join();    //join : ki ykmelo les thred f tratement

        } catch (Exception e) {
        }
        listeresult.addAll(Th_C.listresult);
        try {
            th1.join();
        } catch (Exception e) {
        }
        listeresult.addAll(Th_C1.listresult);
        try {
            th2.join();
        } catch (Exception e) {
        }

        listeresult.addAll(Th_C2.listresult);
        System.out.println("list Thread 1 " + Th_C.listresult.size());
        System.out.println("list Thread 2 " + Th_C1.listresult.size());
        System.out.println("list Thread 3 " + Th_C2.listresult.size());
        ArrayList<String> ListImageOutPut = new ArrayList<String>();//cree liste sort ta3 classement
        imgProce.classement_result_Text(ListImageOutPut, listImgBDD, listeresult);
        jPanel1.removeAll();

        int k = 0;
        int wth = 20;
        int hght = 0;
        int wth1 = 0;
        int hght1 = 0;
        int loop = 0;
        jLabel5.setText("Trouvé : " + ListImageOutPut.size() + " resultat.");
        for (String resu : ListImageOutPut) {
            if (loop < ListImageOutPut.size()) {
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
                loop++;
            } else {
                break;
            }
        }
        long end = System.currentTimeMillis();
        NumberFormat f = new DecimalFormat("#0.00000");
        jLabel4.setText("Le Temps : " + f.format((end - start) / 1000d) + " seconds");
        this.jPanel4.setSize(780, wth);
        th.stop();
        th1.stop();
        th2.stop();

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Image_src = Imgcodecs.imread(path, 1);
        File repertoire = new File(PathDATABASE);
        listImgBDD = new ArrayList<String>();
        listImgBDD1 = new ArrayList<String>();
        listImgBDD2 = new ArrayList<String>();
        listImgBDD3 = new ArrayList<String>();
        imgProce.listRepertoire(repertoire, listImgBDD, PathDATABASE);
        imgProce1.listRepertoire(repertoire, listImgBDD1, listImgBDD2, listImgBDD3, PathDATABASE);
        ArrayList<String> ImageOutPut = new ArrayList<String>();

        ArrayList<Double> listeresult = new ArrayList<>();//cree liste ndiro fiha les resultat
        ArrayList<Double> listeresult1 = new ArrayList<>();
        ArrayList<Double> listeresult2 = new ArrayList<>();
        long start = System.currentTimeMillis();
        Thread_Mix Th_C = new Thread_Mix(this);// crré object men class Thread_mix
        Thread th = new Thread(Th_C);// creé Thread w implementilo la class Thread_mix
        th.start();// demaré thread 

        Thread_Mix1 Th_C1 = new Thread_Mix1(this);
        Thread th1 = new Thread(Th_C1);
        th1.start();

        Thread_Mix2 Th_C2 = new Thread_Mix2(this);
        Thread th2 = new Thread(Th_C2);
        th2.start();

        try {
            th.join();    //join : ki ykmelo les thred f tratement
        } catch (Exception e) {
        }
        try {
            th1.join();

        } catch (Exception e) {
        }
        try {
            th2.join();
        } catch (Exception e) {
        }

        listeresult.addAll((ArrayList<Double>) Th_C.listresult);
        listeresult.addAll((ArrayList<Double>) Th_C1.listresult);
        listeresult.addAll((ArrayList<Double>) Th_C2.listresult);
        listeresult1.addAll((ArrayList<Double>) Th_C.listresult1);
        listeresult1.addAll((ArrayList<Double>) Th_C1.listresult1);
        listeresult1.addAll((ArrayList<Double>) Th_C2.listresult1);
        ArrayList<String> ListImageOutPut = new ArrayList<String>();//cree liste sort ta3 classement
        listeresult2.addAll((ArrayList<Double>) Th_C.listresult2);
        listeresult2.addAll((ArrayList<Double>) Th_C1.listresult2);
        listeresult2.addAll((ArrayList<Double>) Th_C2.listresult2);
        System.out.println("list " + listeresult.size());
        System.out.println("list1 " + listeresult1.size());
        System.out.println("list2 " + listeresult2.size());
        System.out.println("d1" + Th_C.d1);
        System.out.println("d2" + Th_C.d2);
        System.out.println("d3" + Th_C.d3);
        imgProce.classement_result_mix(Th_C.d1, Th_C.d2, Th_C.d3, ListImageOutPut, listImgBDD, listeresult, listeresult1, listeresult2);

        jPanel1.removeAll();
        System.out.println(ListImageOutPut);

        int i = 0;
        jLabel5.setText("Trouvé : " + ListImageOutPut.size() + " resultat.");
        for (String string : ListImageOutPut) {
            if (i == 0) {
                ImageOutPut.add(string);
            } else {
                if (filtres(string, ImageOutPut)) {
                    ImageOutPut.add(string);
                    System.out.println("img " + string);
                }
            }
            i++;
        }

        ListImageOutPut.clear();
        ListImageOutPut.addAll(ImageOutPut);

        int k = 0;
        int wth = 20;
        int hght = 0;
        int wth1 = 0;
        int hght1 = 0;
        //  int loop = 0;
        for (String resu : ListImageOutPut) {
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
        th.stop();
        th1.stop();
        th2.stop();
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

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

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
    public javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
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
