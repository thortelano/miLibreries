/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author anhor
 */
public class MyCellRenderer extends JPanel implements TableCellRenderer {
    private static final float SIDE = 38;
    private JLabel label = new JLabel();
    private Font font;
   
    private Color colorBackground = new Color(242,247,253);
    private Color colorForeground = Color.BLACK;
   
    private ImageIcon gradient= new ImageIcon(getClass().getResource("/miTable/gradient.png"));
    private boolean separador=true;
    private boolean gradientVisible =true;

    public MyCellRenderer() {
      super();
      
      inicializar();
   }
    public MyCellRenderer(Color c) {
      super();
      
      colorBackground=c;
      inicializar();
   }
    public MyCellRenderer(Color c, Color t) {
      super();
      colorBackground=c;
      colorForeground=t;
      inicializar();
   }
   
    private void inicializar(){
      this.setOpaque(true);
      this.setLayout(new BorderLayout());
      this.add(label, BorderLayout.WEST);
      
      label.setHorizontalAlignment(SwingConstants.CENTER);
      font = label.getFont();
   }
   
    public void setFuente(Font f ){
       font =f;
   }
    public void setColorBackground(Color aColorBackground) {
        colorBackground = aColorBackground;
    }
    public void setColorForeground(Color aColorForeground) {
        colorForeground = aColorForeground;
    }
    public void setSeparador(boolean separador) {
        this.separador = separador;
    }
    public void setGradientVisible(boolean gradientVisible) {
        this.gradientVisible = gradientVisible;
    }
   
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocused, int arg4, int arg5) {
      label.setText(value.toString());
      label.setForeground(colorForeground);
      label.setBorder(null);
      label.setFont(font);
      
      return this;
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      Graphics2D g2 = (Graphics2D) g;
      
        //PINTA EL COLOR FONDO
        g2.setColor(colorBackground);
        g2.fillRect(0, 0, this.getWidth(),this.getHeight());
        
        //PINTAEL DEGRADADO
        if (gradientVisible) g2.drawImage(gradient.getImage(),0 , 0, this.getWidth(), this.getHeight(), this);
      
        //DIBUJA LINEA INFERIOR TITULO
        
        //g2.setPaint(Color.DARK_GRAY);
        if (separador){
            g2.setPaint(Color.DARK_GRAY);
            g2.drawLine(getWidth()-1, 0,getWidth()-1, getHeight());
        }
        
   }

    
    
}