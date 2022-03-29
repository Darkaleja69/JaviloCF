package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packModelo.Casilla;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class InterfazJuego extends JFrame implements Observer {
	
	private static InterfazJuego miInterfaz;
	private JPanel contentPane;
	private JPanel TableroPC;
	private JPanel TableroJugador;
	private JPanel PosicionBarco;
	private JPanel Acciones;
	private JPanel SelecBarco;
	private JPanel Orientacion;
	private JRadioButton Vertical;
	private JRadioButton Portaviones;
	private JRadioButton Submarino;
	private JRadioButton Destructor;
	private JRadioButton Fragata;
	private JRadioButton Horizontal;
	private ButtonGroup g =new ButtonGroup();
	private ButtonGroup g2 =new ButtonGroup();
	private JLabel lblNewLabel;
	private JRadioButton Disparar;
	private ArrayList<JLabel> listaLJug;
	private ArrayList<JLabel> listaLPC;
	private Controler controler = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazJuego frame = new InterfazJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazJuego() {
		listaLJug=new ArrayList<JLabel>();
		listaLPC=new ArrayList<JLabel>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		contentPane.add(getTableroJugador());
		contentPane.add(getTableroPC());
		contentPane.add(getPosicionBarco());
		contentPane.add(getAcciones());
		crearJLabels(getTableroJugador(),getTableroPC());
		g.add(Vertical);
		g.add(Horizontal);
		g2.add(Fragata);
		g2.add(Submarino);
		g2.add(Portaviones);
		g2.add(Destructor);
	}
	
	public static InterfazJuego getMiInterfazJuego() {
		if(miInterfaz==null) {
			miInterfaz=new InterfazJuego();
		}return miInterfaz;
	}
	private JPanel getTableroPC() {
		if (TableroPC == null) {
			TableroPC = new JPanel();
			TableroPC.setLayout(new GridLayout(10, 10, 0, 0));
		}
		return TableroPC;
	}
	private JPanel getTableroJugador() {
		if (TableroJugador == null) {
			TableroJugador = new JPanel();
			TableroJugador.setLayout(new GridLayout(10, 10, 0, 0));
		}
		return TableroJugador;
	}
	private JPanel getPosicionBarco() {
		if (PosicionBarco == null) {
			PosicionBarco = new JPanel();
			PosicionBarco.setLayout(new GridLayout(1, 2, 0, 0));
			PosicionBarco.add(getSelecBarco());
			PosicionBarco.add(getOrientacion());
		}
		return PosicionBarco;
	}
	private JPanel getAcciones() {
		if (Acciones == null) {
			Acciones = new JPanel();
			Acciones.setLayout(new GridLayout(2, 1, 0, 0));
			Acciones.add(getLblNewLabel());
			Acciones.add(getDisparar());
		}
		return Acciones;
	}
	
	private Iterator<JLabel> getItr(){
		return (this.listaLJug.iterator());
	}
	
	private int buscarJLabel(JLabel pLabel) {
		int cont=0;
		Iterator<JLabel> itr=this.getItr();
		boolean enc=false;
		while(itr.hasNext() && !enc) {
			JLabel x=itr.next();
			if(x.equals(pLabel)) {
				enc=true;
			}
			cont++;
		}
		return cont;
	}
	private void crearJLabels(JPanel p1, JPanel p2)
    {	
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {	JLabel label1=clb();
            	JLabel label2=clb();
            	listaLJug.add(label1);
            	listaLPC.add(label2);
                p1.add(label1, BorderLayout.CENTER,i*10+j);
                p2.add(label2, BorderLayout.CENTER,i*10+j);
            }
        }
    }
	
    private JLabel clb()
    {	
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.white));
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(Color.cyan);
        lblNewLabel.addActionListener(getControler());
        return lblNewLabel;
    }

	private JPanel getSelecBarco() {
		if (SelecBarco == null) {
			SelecBarco = new JPanel();
			SelecBarco.setLayout(new GridLayout(4, 1, 0, 0));
			SelecBarco.add(getFragata());
			SelecBarco.add(getDestructor());
			SelecBarco.add(getSubmarino());
			SelecBarco.add(getPortaviones());
		}
		return SelecBarco;
	}
	private JPanel getOrientacion() {
		if (Orientacion == null) {
			Orientacion = new JPanel();
			Orientacion.setLayout(new GridLayout(2, 1, 0, 0));
			Orientacion.add(getVertical());
			Orientacion.add(getHorizontal());
		}
		return Orientacion;
	}
	private JRadioButton getVertical() {
		if (Vertical == null) {
			Vertical = new JRadioButton("Vertical");
		}
		return Vertical;
	}
	private JRadioButton getPortaviones() {
		if (Portaviones == null) {
			Portaviones = new JRadioButton("Portaviones(4)");
		}
		return Portaviones;
	}
	private JRadioButton getSubmarino() {
		if (Submarino == null) {
			Submarino = new JRadioButton("Submarino(3)");
		}
		return Submarino;
	}
	private JRadioButton getDestructor() {
		if (Destructor == null) {
			Destructor = new JRadioButton("Destructor(2)");
		}
		return Destructor;
	}
	private JRadioButton getFragata() {
		if (Fragata == null) {
			Fragata = new JRadioButton("Fragata(1)");
		}
		return Fragata;
	}
	private JRadioButton getHorizontal() {
		if (Horizontal == null) {
			Horizontal = new JRadioButton("Horizontal");
		}
		return Horizontal;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Que quieres hacer?");
		}
		return lblNewLabel;
	}
	private JRadioButton getDisparar() {
		if (Disparar == null) {
			Disparar = new JRadioButton("Disparar");
		}
		return Disparar;
	}
	
	private JLabel obtJLabel(int pPos, boolean pJug)
	{
		if(pJug)
		{
			return this.listaLJug.get(pPos);
		}
		else
		{
			return this.listaLPC.get(pPos);
		}
	}
	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	public void update(Observable arg0, Object arg1) {
		Casilla c = (Casilla) arg1;
		int pos = (c.getFila() * 10) + c.getColumna();
		boolean esJ = c.esJugador();
		
		JLabel lbl = this.obtJLabel(pos, esJ);
		
		if(c.tieneBarco())
		{
			if(c.estaTocada())
			{
				lblNewLabel.setBackground(Color.red);
			}
			else
			{
				lblNewLabel.setBackground(Color.gray);
			}
		}
		else
		{
			if(c.estaTocada())
			{
				lblNewLabel.setBackground(Color.blue);
			}
			else
			{
				lblNewLabel.setBackground(Color.cyan);
			}
		}
	}
	
	private class Controler implements ActionListener  {
		
		public void actionPerformed (ActionEvent e){
			Object control=e.getSource();
			if(control instanceof JLabel){
				System.out.println("Se ha pulsado un botón");
			}
			//JLabel labelClicado = (JLabel) e.getSource();
			//int coord =InterfazJuego.getMiInterfazJuego().buscarJLabel(labelClicado);
			//int x=coord/10;
			//int y=coord % 10;
			
			
		}
	}

	

   
}