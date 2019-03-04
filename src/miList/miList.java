/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miList;

import miButton.miButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 *
 * @author anhor
 */
public class miList extends JScrollPane{
    private JPanel panel;
    private ArrayList<miButton> items;
    private GroupLayout.ParallelGroup hGroup;
    private GroupLayout.SequentialGroup vGroup;
    private int ancho;
    private int alto;
    private ActionListener listener;
    private Color colorBackgroundUnSelect= new Color(53,64,82);
    private Color colorBackgroundSelect= new Color(69,83,106);
    private Color colorForeground= Color.WHITE;
    private Color colorBorder= new Color(255,153,51);
    
    private int select;
    
    public miList(){
        super();
        
        inicializar();
    }
    
    private void inicializar(){
        items=new ArrayList();
        ancho=250;
        alto=45;
        select=-1;
        listener=null;
        
        inicializarUI();
    }
    private void inicializarUI(){
        panel = new JPanel();
        panel.setBorder(null);
        panel.setOpaque(true);
        panel.setBackground(colorBackgroundUnSelect);
        
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);
        
        hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
	vGroup = layout.createSequentialGroup();
        
        for (int i=0;i<items.size();i++){
            insertItemUI(items.get(i));
        }
        
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
        
        panel.setLayout(layout);
        
        setViewportView(panel);
        setOpaque(true);
        setBackground(colorBackgroundUnSelect);
        setBorder(null);
        setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    private void insertItemUI(miButton b){
        hGroup.addComponent(b,javax.swing.GroupLayout.PREFERRED_SIZE, ancho, javax.swing.GroupLayout.PREFERRED_SIZE);
        vGroup.addComponent(b,javax.swing.GroupLayout.PREFERRED_SIZE, alto, javax.swing.GroupLayout.PREFERRED_SIZE);
    }
    
    public void clear(){
        items.clear();
        inicializarUI();
    }
    
    public int getSelect(){
        return select;
    }
    public int getCantidad(){
        return items.size();
    }
    public String getSelectText(){
        return items.get(select).getText();
    }
    public String getSelectText(int n){
        return items.get(n).getText();
    }
    public int getAncho(){
        return ancho;
    }
    public int getAlto(){
        return alto;
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
     * @return the colorForeground
     */
    public Color getColorForeground() {
        return colorForeground;
    }
    /**
     * @return the colorBorder
     */
    public Color getColorBorder() {
        return colorBorder;
    }
    
    public void setSeleccionado(int n, boolean b){
        select=n;
        items.get(n).setSeleccionado(b);
    }
    public void setAncho(int Ancho){
        ancho=Ancho;
        this.setSize(ancho,this.getHeight());
    }
    public void setALto(int Alto){
        alto=Alto;
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
    }
    /**
     * @param colorForeground the colorForeground to set
     */
    public void setColorForeground(Color colorForeground) {
        this.colorForeground = colorForeground;
    }
    /**
     * @param colorBorder the colorBorder to set
     */
    public void setColorBorder(Color colorBorder) {
        this.colorBorder = colorBorder;
    }
    
    public void setColors(Color border, Color backgroundS,Color backgroundU, Color foreground){
        colorBorder=border;
        colorBackgroundSelect=backgroundS;
        colorBackgroundUnSelect=backgroundU;
        colorForeground=foreground;
    }
    
    public miButton newItem(String texto){
        miButton b= new miButton(1,texto, getColorBorder());
            b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            b.setColorBackgroundSelect(getColorBackgroundSelect());
            b.setColorBackgroundUnSelect(getColorBackgroundUnSelect());
            b.setForeground(getColorForeground());
            b.repaint();
            if (listener!=null) b.addActionListener(listener);
            b.addActionListener(inicializarListenerMenu(items.size()));
            
        items.add(b);
        insertItemUI(b);

        return b;
    }
    public miButton newItem(String texto, Color colorTex){
        
        miButton b= new miButton(1,texto, getColorBorder());
            b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            b.setColorBackgroundSelect(getColorBackgroundSelect());
            b.setColorBackgroundUnSelect(getColorBackgroundUnSelect());
            b.setForeground(colorTex);
            b.repaint();
            
            if (listener!=null) b.addActionListener(listener);
            b.addActionListener(inicializarListenerMenu(items.size()));
            
        items.add(b);
        insertItemUI(b);

        return b;
    }
    
    private ActionListener inicializarListenerMenu(int n){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (select>-1){
                    items.get(select).setSeleccionado(false);
                }
                select=n;
                items.get(select).setSeleccionado(true);
            }
        };
    }

    public void addListener(ActionListener l){
        listener=l;
        
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        
        if (ancho!= this.getWidth()){
            ancho=this.getWidth();
            
            inicializarUI();
        }
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs); //To change body of generated methods, choose Tools | Templates.
        if (ancho!= this.getWidth()){
            ancho=this.getWidth();
            
            inicializarUI();
        }
    }

}
