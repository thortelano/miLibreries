/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miComboBox;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComboBox;

/**
 *
 * @author anhor
 */
public class miComboBox extends JComboBox{
    private Color colorBorder=new Color(21, 164, 250);
    private Color colorBackgroundSelect=new Color(243, 244, 249);
    private Color colorBackgroundUnSelect=new Color(240, 240, 240);
    
    /**
     * CONTRUCTOR
     * @param c -COLOR BORDE
     * @param bs - COLOR FONDO SELECCIONADO
     * @param bu - COLOR FONDO DESELECCIONADO
     */
    public miComboBox(Color c,Color bs, Color bu){
        super();
        colorBorder=c;
        colorBackgroundSelect=bs;
        colorBackgroundUnSelect=bu;
        
        this.addFocusListener(inicializarListenerFocus(this));
        setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,Color.WHITE));
        setOpaque(true);
        setAutoscrolls(true);
        setBackground(colorBackgroundUnSelect);
    }
    
    private FocusListener inicializarListenerFocus(JComboBox b){
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBorder()));
                    b.setBackground(getColorBackgroundSelect());
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (b.isEnabled()){
                    b.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,Color.WHITE));
                    b.setBackground(getColorBackgroundUnSelect());
                }
            }
        };
    }

    @Override
    public void setEnabled(boolean bln) {
        super.setEnabled(bln); //To change body of generated methods, choose Tools | Templates.
        if(bln){
            setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2,Color.WHITE));
            setBackground(getColorBackgroundUnSelect());
        }else{
            setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, getColorBackgroundUnSelect()));
            setBackground(getColorBackgroundUnSelect());
        }
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
     * @param colorBorder the colorBorder to set
     */
    public void setColorBorder(Color colorBorder) {
        this.colorBorder = colorBorder;
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
    }
    
    
    
}
