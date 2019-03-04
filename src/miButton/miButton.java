/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Action;
import javax.swing.JButton;


/**
 * @version 2.00
 * @author anhor
 */
public class miButton extends JButton{
    private boolean selected=false;
    private Color colorBorder=new Color(21, 164, 250);
    private Color colorBackgroundSelect=new Color(240, 240,240);
    private Color colorBackgroundUnSelect=this.getBackground();
    
    private int tipo=0;
    
    public miButton(){
        super();
        inicializar();
    }
    public miButton(int t){
        super();
        tipo=t;
        inicializar();
    }
    public miButton(String nom){
        super(nom);
        inicializar();
    }
    public miButton(int t,String nom){
        super(nom);
        tipo=t;
        inicializar();
    }
    public miButton(String nom, Color c){
        super(nom);
        colorBorder=c;
        inicializar();
    }
    public miButton(int t,String nom, Color c){
        super(nom);
        tipo=t;
        colorBorder=c;
        
        inicializar();
    }
    public miButton(Action nom){
        super(nom);
        inicializar();
    }
    
    private void inicializar(){
        setContentAreaFilled(false);
        setOpaque(true);
        setPreferredSize(new Dimension(120,50));
        setFocusPainted(false);
        setMargin(new java.awt.Insets(2, 5, 2, 5));
        
        if (tipo==0){
            setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
            setBackground(getColorBackgroundUnSelect());
            addMouseListener(inicializarListenerTipo0());
            this.addFocusListener(inicializarListenerFocus0());
        }else if(tipo==1){
            setBorder(null);
            setBackground(getColorBackgroundUnSelect());
            addMouseListener(inicializarListenerTipo1());
        }
        //gradient= new ImageIcon(getClass().getResource("/Images/gradient.png"));
        
    }
    
    public boolean isSeleccionado(){
        return selected;
    }
    public Color getColor(){
        return getColorBorder();
    }
    public int getTipo(){
        return tipo;
    }
    /**
     * @return the colorBorder
     */
    public Color getColorBorder() {
        return colorBorder;
    }
    /**
     * @return the colorBackgroundSelect
     */
    public Color getColorBackgroundSelect() {
        return colorBackgroundSelect;
    }
    /**
     * @return the colorBackgroundUnSelect
     */
    public Color getColorBackgroundUnSelect() {
        return colorBackgroundUnSelect;
    }
    
    /**
     * @deprecated 
     * @param c 
     */
    @Deprecated
    public void setColor(Color c){
        setColorBorder(c);
    }
    /**
     * @deprecated 
     * @param c 
     */
    @Deprecated
    public void setColorFondo1(Color c){
        setColorBackgroundSelect(c);
    }
    /**
     * @deprecated 
     * @param c 
     */
    @Deprecated
    public void setColorFondo2(Color c){
        setColorBackgroundUnSelect(c);
        setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
        setBackground(getColorBackgroundUnSelect());
        repaint();
    }
    
    @Override
    public void setEnabled(boolean bln) {
        super.setEnabled(bln); //To change body of generated methods, choose Tools | Templates.
        if (tipo==0){
            setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
            setBackground(getColorBackgroundUnSelect());
        }else if(tipo==1){
            setBackground(getColorBackgroundUnSelect());
        }
        repaint();
    }
    public void setSeleccionado(boolean b){
        selected=b;
        repaint();
    }
    public void setTipo(int Tipo){
        tipo =Tipo;
        inicializar();
        repaint();
    }
    /**
     * @param colorBorder the colorBorder to set
     */
    public void setColorBorder(Color colorBorder) {
        this.colorBorder = colorBorder;
        repaint();
    }
    /**
     * @param colorBackgroundSelect the colorBackgroundSelect to set
     */
    public void setColorBackgroundSelect(Color colorBackgroundSelect) {
        this.colorBackgroundSelect = colorBackgroundSelect;
    }
    /**
     * @param colorBackgroundUnSelect the colorBackgroundUnSelect to set
     */
    public void setColorBackgroundUnSelect(Color colorBackgroundUnSelect) {
        this.colorBackgroundUnSelect = colorBackgroundUnSelect;
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
        this.setBackground(colorBackgroundUnSelect);
        repaint();
    }
    
    private MouseListener inicializarListenerTipo0(){
        return new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)me.getComponent();
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, getColorBorder()));
                    
                    b.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)me.getComponent();
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBorder()));
                    //b.setBackground(colorBackgroundSelect);
                    b.repaint();
                }
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                miButton b =(miButton)me.getComponent();
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBorder()));
                    b.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)me.getComponent();
                b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
                b.setBackground(getColorBackgroundUnSelect());
                b.repaint();
            }  
        };
    }
    private MouseListener inicializarListenerTipo1(){
        return new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)me.getComponent();
                if (b.isEnabled()){
                    b.setBackground(getColorBackgroundSelect());
                    b.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)me.getComponent();
                if (b.isEnabled()){
                    
                    b.setBackground(getColorBackgroundSelect());
                    b.repaint();
                }
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                miButton b =(miButton)me.getComponent();
                if (b.isEnabled()){
                    
                    b.setBackground(getColorBackgroundSelect());
                    b.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)me.getComponent();
                b.setBackground(getColorBackgroundUnSelect());
                b.repaint();
            }  
        };
    }
    public FocusListener inicializarListenerFocus0(){
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)fe.getComponent();
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBorder()));
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miButton b =(miButton)fe.getComponent();
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
                    b.setBackground(getColorBackgroundUnSelect());
                }
            }
        };
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        
        if (selected){
            grphcs.setColor(getColorBorder());
            grphcs.fillRect(0, 0, 5, this.getHeight());
        }
    }    
}
