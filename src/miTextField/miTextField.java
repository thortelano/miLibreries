/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miTextField;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;


/**
 *
 * @author anhor
 */
public class miTextField extends JTextField{
    //private Color colorBorder=new Color(255, 153, 51);
    private Color colorBorder=new Color(21, 164, 250);
    private Color colorBackgroundSelect=new Color(252, 255,252);
    private Color colorBackgroundUnSelect=new Color(240, 240,240);
    
    public miTextField(){
        super();
        inicializar();
    }
    public miTextField(String nom){
        super(nom);
        inicializar();
    }
    public miTextField(String nom,Color c,Color f){
        super(nom);
        colorBorder =c;
        colorBackgroundSelect=f;
        inicializar();
    }
    
    public miTextField(Color borde,Color fondo){
        super();
        colorBorder =borde;
        colorBackgroundSelect=fondo;
        
        inicializar();
    }
    
    private void inicializar(){
        setOpaque(true);
        setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,Color.WHITE));
        setBackground(colorBackgroundUnSelect);
        this.addFocusListener(inicializarListenerFocus(this));
    }
    
    public Color getColorBorder(){
        return colorBorder;
    }
    public Color getColorBackgroundSelect(){
        return colorBackgroundSelect;
    }
    public Color getColorBackgroundUnSelect(){
        return colorBackgroundUnSelect;
    }
    
    public void setColorBorder(Color c){
        colorBorder=c;
    }
    public void setColorBackgroundSelect(Color c){
        colorBackgroundSelect=c;
    }
    public void setColorBackgroundUnSelect(Color c){
        colorBackgroundUnSelect=c;
    }

    @Override
    public void setEditable(boolean bln) {
        super.setEditable(bln); //To change body of generated methods, choose Tools | Templates.
        
        if(bln){
            setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,Color.WHITE));
            setBackground(colorBackgroundUnSelect);
        }else{
            setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,colorBackgroundUnSelect));
            setBackground(colorBackgroundUnSelect);
        }
    }
    
    public FocusListener inicializarListenerFocus(JTextField p){
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (p.isEditable()){
                    setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,colorBorder));
                    setBackground(colorBackgroundSelect);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (p.isEditable()){
                    setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,Color.WHITE));
                    setBackground(colorBackgroundUnSelect);
                }
            }
        };
    }
    
}
