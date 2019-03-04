/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miColorBar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author anhor
 */
public class miColorBar extends JComponent {
    private int posicion;
    private double tamañoX;
    private int ancho=200;
    private int alto=80;
    private int escala=10;
    private static final int SECCIONES=6;
    private static final int PUNTOSX=255*SECCIONES;
    private Color color;
    private double inc;
    private BufferedImage fondo;
    
    
    public miColorBar(){
        super();
        inicializar();
    }
    
    public void inicializar(){
        this.setSize(ancho,alto);
        
        posicion=100;
        tamañoX=0;
        color=new Color(255,0,0);
        
        cargarFondo();
        
        this.addMouseListener(inicializarMouseClick());
        this.addMouseMotionListener(inicializarMouseMove());
    }
    
    private void calcularColor(){
        double pos = posicion*(PUNTOSX/(double)this.getWidth());
        
        int sec=(int)pos/255;
        int resto=(int)pos-(sec*255);
        
        int r,g,b;
        r=255;
        g=0;
        b=0;
        
        switch (sec){
            case 0:
                r=255;
                g=0;
                b=resto;
                break;
            case 1:
                r=255-resto;
                g=0;
                b=255;
                break;
            case 2:
                r=0;
                g=resto;
                b=255;
                break;
            case 3:
                r=0;
                g=255;
                b=255-resto;
                break;
            case 4:
                r=resto;
                g=255;
                b=0;
                break;
            case 5:
                r=255;
                g=255-resto;
                b=0;
                break;
        }
        
        color=new Color(r,g,b);
    }
    private void cargarFondo(){
        double R=0;
        double G=0;
        double B=0;
        fondo= new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g =(Graphics2D)fondo.getGraphics();
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        tamañoX=(double)this.getWidth();
        
        if (tamañoX>0){
            tamañoX=tamañoX/(PUNTOSX/escala);
            if (tamañoX<1){tamañoX=1;}
            
            inc=(PUNTOSX/(PUNTOSX/escala));
            
            int i;
            int tam=(255/escala)+1;
            double pos=0;
            
            R=255;
            G=0;
            B=0;
            
            for (i =0;i<tam;i++){
                //PINTAMOS PUNTO
                g.setColor(new Color((int)R,(int)G,(int)B));
                g.fillRect((int)pos,0, (int)(tamañoX+0.5),this.getHeight());
                    
                //INCREMENTARMOS COLOR AZUL
                B+=inc;
                if(B>255) B=255;
                pos+=tamañoX;
            }
            for (i =0;i<tam;i++){
                //PINTAMOS PUNTO
                g.setColor(new Color((int)R,(int)G,(int)B));
                g.fillRect((int)pos,0, (int)(tamañoX+0.5),this.getHeight());
                    
                //DECREMENTAMOS COLOR ROJO
                R-=inc;
                if(R<0) R=0;
                pos+=tamañoX;
            }
            for (i =0;i<tam;i++){
                //PINTAMOS PUNTO
                g.setColor(new Color((int)R,(int)G,(int)B));
                g.fillRect((int)pos,0, (int)(tamañoX+0.5),this.getHeight());
                    
                //INCREMENTAMOS COLOR VERDE
                G+=inc;
                if(G>255) G=255;
                pos+=tamañoX;
            }
            for (i =0;i<tam;i++){
                //PINTAMOS PUNTO
                g.setColor(new Color((int)R,(int)G,(int)B));
                g.fillRect((int)pos,0, (int)(tamañoX+0.5),this.getHeight());
                    
                //DECREMENTAMOS COLOR AZUL
                B-=inc;
                if(B<0) B=0;
                pos+=tamañoX;
            }
            for (i =0;i<tam;i++){
                //PINTAMOS PUNTO
                g.setColor(new Color((int)R,(int)G,(int)B));
                g.fillRect((int)pos,0, (int)(tamañoX+0.5),this.getHeight());
                    
                //INCREMENTAMOS COLOR ROJO
                R+=inc;
                if(R>255) R=255;
                pos+=tamañoX;
            }
            for (i =0;i<tam;i++){
                //PINTAMOS PUNTO
                g.setColor(new Color((int)R,(int)G,(int)B));
                g.fillRect((int)pos,0, (int)(tamañoX+0.5),this.getHeight());
                    
                //DECREMENTAMOS COLOR VERDE
                G-=inc;
                if(G<0) G=0;
                pos+=tamañoX;
            }
        }
    }
    
    public int getPosicion(){
        return posicion;
    }
    public double getTamañoPunto(){
        return tamañoX;
    }
    public int getEscala(){
        return escala;
    }
    public Color getColor(){
        calcularColor();
        return color;
    }
    
    public void setPosicion(int p){
        posicion=p;
    }
    public void setTamañoPunto(double p){
        tamañoX=p;
        cargarFondo();
        repaint();
    }
    public void setEscala(int e){
        escala=e;
        cargarFondo();
        repaint();
    }
    
    
    public MouseListener inicializarMouseClick(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                posicion=me.getX();
                if (posicion<0) posicion=0;
                if (posicion>ancho) posicion=ancho;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    public MouseMotionListener inicializarMouseMove(){
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                posicion=me.getX();
                if (posicion<0) posicion=0;
                if (posicion>ancho) posicion=ancho;
                
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                /*if (){
                    posicion=me.getX();
                    calcularColor();
                    repaint();
                }*/
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
        //REVISAMOS SI A CAMBIADO EL TAMAÑO
        if (ancho!=this.getWidth() || alto!=this.getHeight()){
            ancho=this.getWidth();
            alto=this.getHeight();
            cargarFondo();
        }
        
        //PINTAMOS EL FONDO
        g2.drawImage(fondo, 0, 0, this);
        
        //POSICION ACTUAL
        int pos = posicion;
        if (pos==0) pos=1;
        if (pos==ancho) pos=ancho-2;
        
        g2.setColor(Color.black);
        g.drawRoundRect(pos-2, 1,5, this.getHeight()-3,2,2);
        //g2.drawRect(pos-2, 1,5, this.getHeight()-2);
        g2.setColor(Color.WHITE);
        //g2.drawRect(pos-1, 2,3, this.getHeight()-4);
        g.drawRoundRect(pos-1, 2,3, this.getHeight()-5,1,1);
        
        
        //MARCO EXTERIOR
        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(0, 0,this.getWidth()-1, this.getHeight()-1);
    }

}
