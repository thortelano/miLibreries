package miProgressBar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author toni
 */
public class miProgresBar extends JPanel{
    public static final int MODE_CIRCULAR=0;
    public static final int MODE_LINEAL=1;
    
    private int margen=2;
    private int espesor=10;
    private Color color= new Color(100,240,100);
    private Color colorText= new Color(0,0,0);
    private Color colorF2= new Color(230,230,230);
    private Color colorF1= new Color(255,255,255);
    private Font font = new Font("Arial",1,16);
    private JLabel img=null;
    private JLabel text = new JLabel("0/0");
    private int valueMin=0;
    private int value=50;
    private int valueMax=100;
    private ImageIcon logo=null;
    private int tipo=0;
    
    public miProgresBar(){
        inicializar();
    }
    
    private void inicializar(){
        this.setOpaque(false);
        
        setFont(font);
        setText(value + "/" + valueMax);
        setIcon(null);
        
        this.add(text);
        
        repaint();
    }
    
    public void posicionObjetos(){
        if (logo==null){
            text.setLocation((this.getWidth()/2)-(text.getWidth()/2),(this.getHeight()/2)-(text.getHeight()/2));
        }else{
            ImageIcon ic=new ImageIcon(logo.getImage().getScaledInstance(logo.getIconWidth()/3,logo.getIconHeight()/3 ,Image.SCALE_FAST));
            img.setIcon(ic);
            img.setSize(this.getWidth()/3,this.getHeight()/3);
            if (!"".equals(text.getText())){                
                int y=(this.getHeight()/2)-((img.getHeight()+text.getHeight())/2);
                img.setLocation((this.getWidth()/2)-(img.getWidth()/2),y);
                text.setLocation((this.getWidth()/2)-(text.getWidth()/2),(y+img.getHeight()));
            }else{
                img.setLocation((this.getWidth()/2)-(img.getWidth()/2),(this.getHeight()/2)-(img.getHeight()/2));
            }
        }
    }
    
    public void setText(String t){
        text.setText(t);
        posicionObjetos();
    }
    public void setIcon(ImageIcon i){
        logo=i;
        img = new JLabel(logo);
        this.add(img);
    }
    public void setValue(int valor){
        value=valor;
        if (value>valueMax) value=valueMax;
        if (value<valueMin) value=valueMin;
    }
    public void setMaxValue(int valor){
        valueMax=valor;
    }
    public void setMinValue(int valor){
        valueMin=valor;
    }
    public void setColor(Color c){
        color=c;
        repaint();
    }
    public void setColorText(Color c){
        colorText=c;
        text.setForeground(c);
        repaint();
    }
    public void setColorF1(Color c){
        colorF1=c;
        repaint();
    }
    public void setColorF2(Color c){
        colorF2=c;
    }
    public void setEspesor(int valor){
        espesor=valor;
    }
    public void setMargen(int valor){
        margen=valor;
    }
    public void setTipo(int tip){
        tipo=tip;
        
        repaint();
    }
    @Override
    public void setFont(Font font) {
        //super.setFont(font); //To change body of generated methods, choose Tools | Templates.
        this.font=font;
        if (text!=null) text.setFont(font);
    }
    
    public int getValue(){
        return value;
    }
    public int getMaxValue(){
        return valueMax;
    }
    public int getMinValue(){
        return valueMin;
    }
    @Override
    public Font getFont(){
        return font;
    }
    public int getTipo(){
        return tipo;
    }
    public Color getColor(){
        return color;
    }
    public Color getColorF1(){
        return colorF1;
    }
    public Color getColorF2(){
        return colorF2;
    }
    public Color getColorText(){
        return colorText;
    }
    public int getMargen(){
        return margen;
    }
    public int getEspesor(){
        return espesor;
    }
    public ImageIcon getIcon(){
        return logo;
    }
    public String getText(){
        return text.getText();
    }
    

    public void rePaint(){
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        switch (tipo){
            case 0:
                paintCircular(g);
                break;
            case 1:
                paintLineal(g);
                break;
        }
        
        posicionObjetos();
        //super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void paintCircular(Graphics g){
        //g.clearRect(0, 0, this.getWidth(),this.getHeight());
        
        int x=margen;
        int y=margen;
        int valor = value;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //circulo vacio
        if (valueMax==0){
            g2.setColor(colorF2);
        }else{
            g2.setColor(color);
        }    
        g2.fillOval(x, y, this.getWidth()-(x*2), this.getHeight()-(y*2));
        
        //arco de relleno
        double arc =(360f/valueMax)*valor;
        arc = 360-arc;
        g2.setColor(colorF2);
        g2.fillArc(x, y, this.getWidth()-(x*2), this.getHeight()-(y*2), 90,(int)arc);
        
        //circulo interior
        x=margen+espesor;
        y=margen+espesor;
        g2.setColor(colorF1);
        g2.fillOval(x, y, this.getWidth()-(x*2), this.getHeight()-(y*2));
        for (int i=margen;i>0;i--){
            g2.setStroke(new BasicStroke(i));
            g2.setColor(new Color(colorF1.getRed(),colorF1.getGreen(),colorF1.getBlue(),(i*2*10)));
            g2.drawOval(x, y, this.getWidth()-(x*2), this.getHeight()-(y*2));
        }
        
        
        //circulo exterior
        x=margen;
        y=margen;
        g2.setStroke(new BasicStroke(margen));
        g2.setColor(colorF1);
        g2.drawOval(x, y, this.getWidth()-(x*2), this.getHeight()-(y*2));
        x-=margen;
        y-=margen;
        for (int i=margen;i>0;i--){
            g2.setStroke(new BasicStroke(i));
            g2.setColor(new Color(colorF1.getRed(),colorF1.getGreen(),colorF1.getBlue(),(i*2*10)));
            g2.drawOval(x, y, this.getWidth()-(x*2), this.getHeight()-(y*2));
        }
        
        
        //TEXTO
        //g2.setFont(font);
        //g2.setColor(colorText);
        //g2.drawString(text.getText(), (this.getWidth()/2)-(g2.getFontMetrics().stringWidth(text.getText())/2), (this.getHeight()/2)-(g2.getFont().getSize()/2));
    }
    private void paintLineal(Graphics g){
        //g.clearRect(0, 0, this.getWidth(),this.getHeight());
        
        int x=2;
        int y=2;
        int tam = this.getWidth()-(x*2)-1;
                
        Graphics2D g2 = (Graphics2D) g;
        
        //PINTAMOS RECTANGULO DE FONDO BARRA
        g2.setColor(colorF2);
        g2.fillRect(x, y, this.getWidth()-(x*2)-2,this.getHeight()- (y*2));
        
        //PINTAMOS EL RELLENO DE LA BARRA
        double posX= (tam*value)/valueMax;
        g2.setColor(color);
        g2.fillRect(x, y,(int)posX ,this.getHeight()- (y*2));
        
        //PINTAMOS EL MARCO
        g2.setStroke(new BasicStroke(2));
        g2.setColor(colorF1);
        g2.drawRect(x, y, this.getWidth()-(x*2)-1,this.getHeight()- (y*2)-1);
        
        //TEXTO
        //g2.setFont(font);
        //g2.setColor(colorText);
        //g2.drawString(text.getText(), (this.getWidth()/2)-(g2.getFontMetrics().stringWidth(text.getText())/2), (this.getHeight()/2)-(g2.getFont().getSize()/2));
        
    }
}
