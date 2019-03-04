/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miColorChooser;

import miColorBar.miColorBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import miButton.miButton;
import miTextField.miTextField;


/**
 *
 * @author anhor
 */
public class miColorChooser extends JDialog {
    public static final int OK_OPTION = 0;
    public static final int CANCEL_OPTION = 1;
    public static final int ERROR_OPTION = -1;
    
    protected int dialogResultValue = ERROR_OPTION;
    
    private static final int ANCHO = 430;
    private static final int ALTO = 400;
    private static final int COLORES=13;
    
    private JDialog ventanaPadre;
    private int tag=0;
    private int r,g,b,h;
    
    private boolean actualizaH;
    
    private Color colorBorder=new Color(21, 164, 250);
    private Color colorActual;
    private Color colorSelec;
    private Color colorTemp;
    
    private Dimension dim1;
    private Dimension dim2;
    private Dimension dim3;
    private Dimension dimS;
    private Dimension dimB;
    
    JPanel pnlColores;
    JPanel pnlLista;
    JPanel pnlRGB;
    JPanel pnlBotones;
    
    JPanel btnSeleccionado;
    JPanel btnActual;
    
    miColorBar barraColores;
    
    JSlider sldColorR;
    JSlider sldColorG;
    JSlider sldColorB;
    JSlider sldColorH;
    JLabel lblMuestras;
    JLabel lblR;
    JLabel lblG;
    JLabel lblB;
    JLabel lblH;
    JLabel lblHexa;
    
    miTextField txtR;
    miTextField txtG;
    miTextField txtB;
    miTextField txtH;
    miTextField txtHexa;
    
    miButton btnAceptar;
    miButton btnCancelar;
    
    JButton btnColores[];
    
    public miColorChooser(){
        super();
        ventanaPadre=null;
        
        inicializar();
    }
    public miColorChooser(JDialog f){
        super(f);
        ventanaPadre=f;
        
        inicializar();
    }
    
    private void inicializar(){
        this.setTitle("Seleccion de Color");
        this.setSize(ANCHO, ALTO);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        
        inicializarVariables();
        inicializarBarras();
        inicializarTextos();
        inicializarBotones();
        
        inicializarListener();
        
        cargarColoresBase();
        actualizarBarras();
        
        InicializarUI();
    }
    private void inicializarVariables(){
        r=0;
        g=0;
        b=0;
        h=0;
        actualizaH=false;
        
        colorActual= new Color(r,g,b);
        colorTemp= new Color(r,g,b);
        colorSelec= new Color(r,g,b);
        
        barraColores= new miColorBar();
        
        dim1=new Dimension(90,30); //TAMAÑO DEL TITULO DE LA BARRA
        dim2=new Dimension(35,30); //TAMAÑO NUMERO DE LAS BARRAS
        dim3=new Dimension(100,20); //TAMAÑO LISTA MUESTRAS
        dimS=new Dimension(150,30); //TAMAÑO DE LAS BARRAS
        dimB=new Dimension(120,35); //TAMAÑO DE LOS BOTONES
    }
    private void inicializarBarras(){
        sldColorR= new JSlider();
        sldColorR.setMinimum(0);
        sldColorR.setMaximum(255);
        sldColorR.setValue(r);
        sldColorR.setPaintTicks(false);
        sldColorR.setPaintTrack(false);
        sldColorR.setBorder(BorderFactory.createEtchedBorder());
        sldColorR.setPreferredSize(dim1);
        sldColorR.setOpaque(true);
        sldColorR.setBackground(new Color(r,0,0));
       
        
        sldColorG= new JSlider();
        sldColorG.setMinimum(0);
        sldColorG.setMaximum(255);
        sldColorG.setValue(g);
        sldColorG.setPaintTicks(false);
        sldColorG.setPaintTrack(false);
        sldColorG.setBorder(BorderFactory.createEtchedBorder());
        sldColorG.setPreferredSize(dim1);
        sldColorG.setOpaque(true);
        sldColorG.setBackground(new Color(r,0,0));
        
        sldColorB = new JSlider();
        sldColorB.setMinimum(0);
        sldColorB.setMaximum(255);
        sldColorB.setValue(b);
        sldColorB.setPaintTicks(false);
        sldColorB.setPaintTrack(false);
        sldColorB.setBorder(BorderFactory.createEtchedBorder());
        sldColorB.setPreferredSize(dim1);
        sldColorB.setOpaque(true);
        sldColorB.setBackground(new Color(r,0,0));
        
        sldColorH = new JSlider();
        sldColorH.setMinimum(0);
        sldColorH.setMaximum(100);
        sldColorH.setValue(h);
        sldColorH.setPaintTicks(false);
        sldColorH.setPaintTrack(false);
        sldColorH.setBorder(BorderFactory.createEtchedBorder());
        sldColorH.setPreferredSize(dim1);
        sldColorH.setOpaque(true);
        sldColorH.setBackground(new Color(0,0,0));
    }
    private void inicializarBotones(){
        btnActual= new JPanel();
        btnActual.setBackground(colorActual);
        btnActual.setPreferredSize(dimB);
        btnActual.setOpaque(true);
        
        
        btnSeleccionado= new JPanel();
        btnSeleccionado.setBackground(colorSelec);
        btnSeleccionado.setPreferredSize(dimB);
        btnSeleccionado.setOpaque(true);
        
        btnAceptar= new miButton("Aceptar",colorBorder);
            btnAceptar.setColorBackgroundUnSelect(Color.white);
            btnAceptar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnAceptar.setIcon(new ImageIcon(getClass().getResource("ok.png")));
            
        btnCancelar= new miButton("Cancelar",colorBorder);
            btnCancelar.setColorBackgroundUnSelect(Color.white);
            btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnCancelar.setIcon(new ImageIcon(getClass().getResource("cancel.png")));
            
        
        btnColores= new JButton[COLORES];
        for (int i =0;i<COLORES;i++){
            btnColores[i]= new JButton();
            btnColores[i].setPreferredSize(new Dimension(100,20));
            btnColores[i].setFocusPainted(true);
            btnColores[i].setContentAreaFilled(false);
            btnColores[i].setOpaque(true);
        }
        
    }
    private void inicializarTextos(){
        lblMuestras= new JLabel("Muestras");
        lblMuestras.setPreferredSize(dim1);
        lblMuestras.setHorizontalAlignment(JLabel.LEFT);
        
        txtR= new miTextField(colorBorder,Color.WHITE);
        txtR.setPreferredSize(dim2);
        txtR.setHorizontalAlignment(JLabel.CENTER);
        
        txtG= new miTextField(colorBorder,Color.WHITE);
        txtG.setPreferredSize(dim2);
        txtG.setHorizontalAlignment(JLabel.CENTER);
        
        txtB= new miTextField(colorBorder,Color.WHITE);
        txtB.setPreferredSize(dim2);
        txtB.setHorizontalAlignment(JLabel.CENTER);
        
        
        txtH= new miTextField(colorBorder,Color.WHITE);
        txtH.setPreferredSize(dim2);
        txtH.setHorizontalAlignment(JLabel.CENTER);
        
        txtHexa= new miTextField(colorBorder,Color.WHITE);
        txtHexa.setPreferredSize(dim2);
        txtHexa.setHorizontalAlignment(JLabel.CENTER);
        txtHexa.setFont(new Font(txtHexa.getFont().getName(),0,12));
        
        lblR= new JLabel("Red : ");
        lblR.setPreferredSize(dim2);
        lblG= new JLabel("Green : ");
        lblG.setPreferredSize(dim2);
        lblB= new JLabel("Blue : ");
        lblB.setPreferredSize(dim2);
        lblH= new JLabel("Saturacion : ");
        lblH.setPreferredSize(dim2);
        lblHexa= new JLabel("Hexadecimal : ");
        lblHexa.setPreferredSize(dim2);
    }
    
    private void InicializarUI(){
        inicializarUIColores();
        inicializarUIEditor();
        inicializarUILista();
        inicializarUIBotones();
        
        this.add(pnlColores,BorderLayout.NORTH);
        this.add(pnlRGB,BorderLayout.CENTER);
        this.add(pnlLista,BorderLayout.WEST);
        this.add(pnlBotones,BorderLayout.SOUTH);
    }
    private void inicializarUIColores(){
        pnlColores= new JPanel();
        pnlColores.setLayout(new GridLayout(1,2));
        pnlColores.add(btnSeleccionado);
        pnlColores.add(btnActual);
    }
    private void inicializarUIEditor(){
        pnlRGB= new JPanel();
        
        
        GroupLayout layout = new GroupLayout(pnlRGB);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(barraColores,javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblR,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorR,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtR,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblG,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorG,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtG,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblB,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorB,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtB,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblH,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorH,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtH,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHexa,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHexa,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.width, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(25)
                .addComponent(barraColores,javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblR,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorR,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtR,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblG,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorG,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtG,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblB,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorB,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtB,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblH,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sldColorH,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtH,javax.swing.GroupLayout.PREFERRED_SIZE, dim2.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(lblHexa,javax.swing.GroupLayout.PREFERRED_SIZE, dim1.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHexa,javax.swing.GroupLayout.PREFERRED_SIZE, dimS.height, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
        );
        pnlRGB.setLayout(layout);
    }
    private void inicializarUILista(){
        pnlLista= new JPanel();
        //pnlLista.setBackground(Color.WHITE);
        
        GroupLayout layout = new GroupLayout(pnlLista);
        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);
        
        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
	GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        
        hGroup.addComponent(lblMuestras,javax.swing.GroupLayout.PREFERRED_SIZE, dim3.width, javax.swing.GroupLayout.PREFERRED_SIZE);
        vGroup.addComponent(lblMuestras,javax.swing.GroupLayout.PREFERRED_SIZE, dim3.height, javax.swing.GroupLayout.PREFERRED_SIZE);
        vGroup.addGap(5);
        
        for (int i=0;i<COLORES;i++){
            hGroup.addComponent(btnColores[i],javax.swing.GroupLayout.PREFERRED_SIZE, dim3.width, javax.swing.GroupLayout.PREFERRED_SIZE);
            vGroup.addComponent(btnColores[i],javax.swing.GroupLayout.PREFERRED_SIZE, dim3.height, javax.swing.GroupLayout.PREFERRED_SIZE);
        }
        
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
        
        pnlLista.setLayout(layout);
    }
    private void inicializarUIBotones(){
        pnlBotones= new JPanel();
        pnlBotones.setBackground(Color.WHITE);
        
        GroupLayout layout = new GroupLayout(pnlBotones);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(false);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(btnAceptar,javax.swing.GroupLayout.PREFERRED_SIZE, dimB.width, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCancelar,javax.swing.GroupLayout.PREFERRED_SIZE, dimB.width, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        
        layout.setVerticalGroup(layout.createParallelGroup()
            .addComponent(btnAceptar,javax.swing.GroupLayout.PREFERRED_SIZE, dimB.height, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCancelar,javax.swing.GroupLayout.PREFERRED_SIZE, dimB.height, javax.swing.GroupLayout.PREFERRED_SIZE)   
        );
        pnlBotones.setLayout(layout);
    }
    
    private void actualizarBarras(){
        sldColorR.setValue(r);
        sldColorG.setValue(g);
        sldColorB.setValue(b);
        sldColorH.setValue(h);
        
        txtR.setText(String.valueOf(r));
        txtG.setText(String.valueOf(g));
        txtB.setText(String.valueOf(b));
        txtH.setText(String.valueOf(h));
        
        txtHexa.setText(getHexadecimalSelect());
    }
    private void cargarColorBarra(){
        setSeleccionado(barraColores.getColor());
    }
    private void cargarColoresBase(){
        btnColores[0].setBackground(Color.BLACK);
        btnColores[1].setBackground(Color.BLUE);
        btnColores[2].setBackground(Color.CYAN);
        btnColores[3].setBackground(Color.DARK_GRAY);
        btnColores[4].setBackground(Color.GRAY);
        btnColores[5].setBackground(Color.GREEN);
        btnColores[6].setBackground(Color.LIGHT_GRAY);
        btnColores[7].setBackground(Color.MAGENTA);
        btnColores[8].setBackground(Color.ORANGE);
        btnColores[9].setBackground(Color.PINK);
        btnColores[10].setBackground(Color.RED);
        btnColores[11].setBackground(Color.WHITE);
        btnColores[12].setBackground(Color.YELLOW);
        
    }
    
    private void mostrarColor(){
        int base=0;
        
        sldColorR.setBackground(new Color(r,base,base));
        sldColorG.setBackground(new Color(base,g,base));
        sldColorB.setBackground(new Color(base,base,b));
        sldColorH.setBackground(colorSelec);
        
        btnSeleccionado.setBackground(colorSelec);
        
        actualizarBarras();
    }
    
    public Color getActual() {
        return colorActual;
    }
    public Color getSeleccionado() {
        return colorSelec;
    }
    public int getDialogResultValue(){
        return dialogResultValue;
    }
    public String getHexadecimalSelect(){
        return "#"+Integer.toHexString(colorSelec.getRGB()).substring(2);
    }
    public String getHexadecimalActual(){
        return "#"+Integer.toHexString(colorActual.getRGB()).substring(2);
    }
    public int getTag(){
        return tag;
    }
    
    public void setActual(Color color) {
        colorActual = color;
        btnActual.setBackground(color);
    }
    public void setSeleccionado(Color color) {
        colorSelec = color;
        colorTemp=color;
        r=color.getRed();
        g=color.getGreen();
        b=color.getBlue();
        
        mostrarColor();
    }
    public void setTag(int n){
        tag=n;
    }
    public void setListenerAceptar(ActionListener l){
        btnAceptar.addActionListener(l);
    }
    public void setListenerCancelar(ActionListener l){
        btnCancelar.addActionListener(l);
    }
    
    private void inicializarListener(){
        sldColorR.addChangeListener(inicializarListenerR());
        sldColorG.addChangeListener(inicializarListenerG());
        sldColorB.addChangeListener(inicializarListenerB());
        sldColorH.addChangeListener(inicializarListenerH());
        
        //btnAceptar.addActionListener(inicializarListenerAceptar());
        //btnCancelar.addActionListener(inicializarListenerCancelar());
        
        btnActual.addMouseListener(inicializarListenerActual());
        
        for (int i =0;i<COLORES;i++){
            btnColores[i].addActionListener(inicializarListenerColor());
        }
        
        barraColores.addMouseListener(inicializarListenerBarraColores());
        
        btnAceptar.addActionListener(inicializarListenerAceptar());
        btnCancelar.addActionListener(inicializarListenerCancelar());
        
        txtR.addKeyListener(inicializarListenerKey(1));
        txtG.addKeyListener(inicializarListenerKey(2));
        txtB.addKeyListener(inicializarListenerKey(3));
        txtH.addKeyListener(inicializarListenerKey(4));
        txtHexa.addKeyListener(inicializarListenerKey(5));
    }
    
    private ChangeListener inicializarListenerR(){
        return new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                r=sldColorR.getValue();
                h=100;
                colorSelec= new Color(r,g,b);
                colorTemp=colorSelec;
                
                mostrarColor();
            }
        };
    }
    private ChangeListener inicializarListenerG(){
        return new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                g=sldColorG.getValue();
                h=100;
                colorSelec= new Color(r,g,b);
                colorTemp=colorSelec;
                
                mostrarColor();
            }
        };
    }
    private ChangeListener inicializarListenerB(){
        return new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                b=sldColorB.getValue();
                h=100;
                colorSelec= new Color(r,g,b);
                colorTemp=colorSelec;
                
                mostrarColor();
            }
        };
    }
    private ChangeListener inicializarListenerH(){
        return new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //if (actualizaH=true){
                //    adaptarColores();
                //}
                
                h=sldColorH.getValue();
                colorSelec=colorSaturacion(colorTemp,h);
                
                mostrarColor();
            }
        };
    }
    
    public ActionListener inicializarListenerAceptar(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                aceptar();
            }};
    }
    public ActionListener inicializarListenerCancelar(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                cancelar();
            }};
    }
    
    private MouseListener inicializarListenerActual(){
        return new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                setSeleccionado(colorActual);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    private ActionListener inicializarListenerColor(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                JButton j= (JButton)evt.getSource();
                setSeleccionado(j.getBackground());
            }};
    }
    private MouseListener inicializarListenerBarraColores(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //cargarColorBarra();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                cargarColorBarra();
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
    
    private KeyListener inicializarListenerKey(int n){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                miTextField t=(miTextField)ke.getComponent();
                int num = Integer.valueOf(t.getText());
                if (ke.getKeyCode()==KeyEvent.VK_ENTER){
                    switch (n){
                        case 1:
                            //COLOR R
                            if (num<0)num=0;
                            if (num>255)num=255;
                            
                            r=num;
                            h=100;
                            colorSelec= new Color(r,g,b);
                            colorTemp=colorSelec;
                            break;
                        case 2:
                            //COLOR G
                            if (num<0)num=0;
                            if (num>255)num=255;
                            g=num;
                            h=100;
                            colorSelec= new Color(r,g,b);
                            colorTemp=colorSelec;
                            break;
                        case 3:
                            //COLOR G
                            if (num<0)num=0;
                            if (num>255)num=255;
                            b=num;
                            h=100;
                            colorSelec= new Color(r,g,b);
                            colorTemp=colorSelec;
                            break;
                        case 4:
                            //SATURACION
                            if (num<0)num=0;
                            if (num>100)num=100;
                            h=num;
                            colorSelec=colorSaturacion(colorTemp,h);
                            
                            break;
                        case 5:
                            //HEXADECIMAL
                            colorSelec=Color.decode(txtHexa.getText());
                            colorTemp=colorSelec;
                            
                            r=colorSelec.getRed();
                            g=colorSelec.getBlue();
                            b=colorSelec.getGreen();
                            h=100;
                            break;
                    }
                    t.setText(String.valueOf(num));
                    
                    mostrarColor();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    private int aceptar(){
        colorActual=colorSelec;
        dialogResultValue=OK_OPTION;
        
        dispose();
        return OK_OPTION;
    }
    private int cancelar(){
        //ocultarVentana();
        dialogResultValue=CANCEL_OPTION;
        
        dispose();
        return CANCEL_OPTION;
    }
    
    private void adaptarColores(){
        double incR=0;
        double incG=0;
        double incB=0;
        double val=0;
        
        int auxR=r;
        int auxG=g;
        int auxB=b;
                
        if (sldColorH.getValue()>h){
            val=255-sldColorH.getValue();
            incR=(255-auxR)/val;
            incG=(255-auxG)/val;
            incB=(255-auxB)/val;
            if(incR==0) incR=1;
            if(incG==0) incG=1;
            if(incB==0) incB=1;
            
            int i=sldColorH.getValue()-h;
            
            auxR=(int)(auxR+(incR*i));
            auxG=(int)(auxG+(incG*i));
            auxB=(int)(auxB+(incB*i));
            if(auxR>255) auxR=255;
            if(auxG>255) auxG=255;
            if(auxB>255) auxB=255;
        }else{
            val=sldColorH.getValue();
            incR=(auxR)/val;
            incG=(auxG)/val;
            incB=(auxB)/val;
            if(incR==0) incR=1;
            if(incG==0) incG=1;
            if(incB==0) incB=1;
            int i=h-sldColorH.getValue();
            auxR=(int)(auxR-(incR*i));
            auxG=(int)(auxG-(incG*i));
            auxB=(int)(auxB-(incB*i));
            if(auxR<0) auxR=0;
            if(auxG<0) auxG=0;
            if(auxB<0) auxB=0;
        }
        
        actualizaH=false;
        sldColorR.setValue(auxR);
        sldColorG.setValue(auxG);
        sldColorB.setValue(auxB);
    }
    
    private int upColor(int inicial , int incremento){
        int color= inicial+incremento;
        if (color>255) color =255;
        
        return color;
    }
    private Color colorSaturacion(Color c , int saturacion){
        Color sat=c;
        double r=c.getRed();
        double g=c.getGreen();
        double b=c.getBlue();
        
        double max=r;
        if (max<g) max=g;
        if (max<b) max=b;
        
        double iR= (max-r)/100;
        double iG= (max-g)/100;
        double iB= (max-b)/100;
       
        r = r + ((100-saturacion)*iR);
        g = g + ((100-saturacion)*iG);
        b = b + ((100-saturacion)*iB);
        
        sat= new Color((int)r,(int)g,(int)b);
        
        return sat;      
    }
    
}
