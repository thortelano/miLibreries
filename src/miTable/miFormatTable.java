/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miTable;

/**
 *
 * @author anhor
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;


public class miFormatTable extends DefaultTableCellRenderer{
    private Component componente;
    private Dimension headerSize = new Dimension(30,30);
    
    private Font textNormal = new Font( "Helvetica",Font.PLAIN,10);
    private Font textHeader = new Font( "Helvetica",Font.BOLD,11);
    
    private Color colorHeader=new Color(242,247,253);
    private Color colorHeaderText = Color.BLACK;
    private Color colorPares = new Color(242,247,253);
    private Color colorImpares = new Color(255,255,255);
    private Color colorSelect= new Color(204,233,251);
    private boolean gradient=true;
    
    private MyCellRenderer cellRender=new MyCellRenderer(colorHeader,colorHeaderText);
    
    
    public miFormatTable(){
        super();
        inicializar();
    }
    public miFormatTable(Color c){
        super();
        
        colorHeader=c;
        inicializar();
    }
    
    private void inicializar(){
        cellRender.setFuente(textHeader);
    }
    
    public void setColorImpares(Color c){
        colorImpares=c;
        this.repaint();
    }
    public void setColorPares(Color c){
        colorPares=c;
        this.repaint();
    }
    public void setColorSelect(Color c){
        colorSelect=c;
        this.repaint();
    }
    public void setColorHeaderText(Color c){
        colorHeaderText=c;
        cellRender.setColorForeground(c);
        this.repaint();
    }
    public void setColorHeader(Color c){
        colorHeader=c;
        cellRender.setColorBackground(c);
        this.repaint();
    }
    public void setGradient(boolean b){
        gradient=b;
        cellRender.setGradientVisible(b);
        repaint();
    }
    public void setHeaderSize(Dimension d){
        headerSize=d;
        repaint();
    }
    public void setTextNormal(Font textNormal) {
        this.textNormal = textNormal;
        repaint();
    }
    public void setTextHeader(Font textHeader) {
        this.textHeader = textHeader;
        repaint();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
        myDefaultTableModel modelo = (myDefaultTableModel)table.getModel();
        
        //ENCABEZADO
        table.getTableHeader().setPreferredSize(new Dimension(30,30));  //establece el tamaño preferido
        table.getTableHeader().setDefaultRenderer(cellRender);          //colora Gradint en el colorHeader
        table.getTableHeader().setReorderingAllowed(false);             //para no cambiar orden de columnas
        
        setHorizontalAlignment(2);//Izquierda
        
        //INSERTAMOS EL COLOR DEL TEXTO Y DEL FONDO DE LAS FILAS
            componente.setForeground(modelo.getColor(row));
            componente.setFont(textNormal);
            boolean oddRow = (row%2==0);
            if(oddRow){
                componente.setBackground(colorPares); 
            }else{
                componente.setBackground(colorImpares);
            }

            //la fila que esta seleccionada
            if (isSelected){
                componente.setBackground(colorSelect);
            }
        
        return this;
    }

    
}