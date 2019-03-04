/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;

/**
 *
 * @author anhor
 */
public class miTable extends JTable{
    private Dimension headerSize = new Dimension(30,30);
    
    private Font text = new Font( "Helvetica",Font.PLAIN,10);
    private Font textHeader = new Font( "Helvetica",Font.BOLD,11);
    
    private Color colorHeader=new Color(186,195,212);
    private Color colorHeaderText = Color.BLACK;
    private Color colorPares = new Color(242,247,253);
    private Color colorImpares = new Color(255,255,255);
    private Color colorSelect= new Color(204,233,251);
    
    private miFormatTable formatTable = new miFormatTable(colorHeader);
    
    private boolean gradient=true;
    
    
    public miTable(){
        super();
        inicializar();
    }
    
    private void inicializar(){
        inicializarFormat();
        
        setDefaultRenderer(Object.class,formatTable);

        setFont(text);//tipo de fuente
        setForeground(Color.BLACK);//color de texto
        setRowHeight(25);
        setFocusable(false);
        setShowHorizontalLines(false);
    }
    
    private void inicializarFormat(){
        formatTable.setColorHeader(colorHeader);
        formatTable.setColorHeaderText(colorHeaderText);
        formatTable.setColorPares(colorPares);
        formatTable.setColorImpares(colorImpares);
        formatTable.setColorSelect(colorSelect);
        
        formatTable.setGradient(gradient);
        formatTable.setHeaderSize(headerSize);
    }
    
    public void setHeaderSize(Dimension headerSize) {
        this.headerSize = headerSize;
        inicializarFormat();
    }
    public void setText(Font text) {
        this.text = text;
        inicializarFormat();
    }
    public void setTextHeader(Font textHeader) {
        this.textHeader = textHeader;
        inicializarFormat();
    }
    public void setColorHeader(Color colorHeader) {
        this.colorHeader = colorHeader;
        inicializarFormat();
    }
    public void setColorHeaderText(Color colorHeaderText) {
        this.colorHeaderText = colorHeaderText;
        inicializarFormat();
    }
    public void setColorPares(Color colorPares) {
        this.colorPares = colorPares;
        inicializarFormat();
    }
    public void setColorImpares(Color colorImpares) {
        this.colorImpares = colorImpares;
        inicializarFormat();
    }
    public void setColorSelect(Color colorSelect) {
        this.colorSelect = colorSelect;
        inicializarFormat();
    }
    public void setGradient(boolean gradient) {
        this.gradient = gradient;
        inicializarFormat();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){ 
        return false;
    }

    
}
