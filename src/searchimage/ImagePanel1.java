/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchimage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImagePanel1 extends JPanel {
 
  private Image img;
  int x , y ;
  public ImagePanel1(String img ) {
    this(new ImageIcon(img).getImage() ); // appel l constructure ltype ta3o image
  }
 
  public ImagePanel1(Image img ) {
    this.img = img;

    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
 
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);// yresem image 
      
  }
 }