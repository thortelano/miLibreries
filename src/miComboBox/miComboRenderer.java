/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miComboBox;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author anhor
 */
public class miComboRenderer extends JLabel implements ListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
        String texto = e.toString();
        //setMinimumSize(new Dimension(100,30));
        setPreferredSize(new Dimension(100,30));
        setOpaque(true);
            
        if (!texto.equals("") && !texto.equals("SIN ICONO") ){

            ImageIcon icon =new ImageIcon(getClass().getResource("/Images/" + texto));
            setIcon(icon);
        }else{
            setIcon(null);
        }
        
        setText(texto);
        return this;
    }
    
    
}
