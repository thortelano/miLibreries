/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miTable;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anhor
 */
public class myDefaultTableModel extends DefaultTableModel {
    private ArrayList<Color> color;
    
    public myDefaultTableModel(){
        super();
        color = new ArrayList();
    }

    @Override
    public void addRow(Object[] os) {
        super.addRow(os); //To change body of generated methods, choose Tools | Templates.
        Color c = Color.BLACK;
        color.add(c);
    }
    

    public void addRow(Object[] os,Color col) {
        super.addRow(os); //To change body of generated methods, choose Tools | Templates.
        color.add(col);
    }

    @Override
    public void removeRow(int i) {
        super.removeRow(i); //To change body of generated methods, choose Tools | Templates.
        color.remove(i);
    }
    
    public Color getColor(int n){
        return color.get(n);
    }
}
