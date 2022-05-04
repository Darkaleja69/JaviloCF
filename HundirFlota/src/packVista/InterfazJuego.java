package packVista;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packModelo.CPU;
import packModelo.Casilla;
import packModelo.GestorJuego;
import packModelo.Jugador;
import packModelo.Tablero;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JRadioButton;

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
	private ButtonGroup g =new ButtonGroup();
	private ButtonGroup g2 =new ButtonGroup();
	private ButtonGroup g3=new ButtonGroup();
	private JRadioButton Disparar;
	private ArrayList<Label> listaLJug;
	private ArrayList<Label> listaLPC;
	private Controler controler = null;
	private JRadioButton Escudo;
	private boolean fin;
	FinPartida frame2;
	private JRadioButton Radar;
	private JRadioButton Misil;
	private JPanel panel;
	private JLabel Dinero;
	private JRadioButton Horizontal;
	private JPanel panel_1;

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
		listaLJug=new ArrayList<Label>();
		listaLPC=new ArrayList<Label>();
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
		g2.add(Fragata);
		g2.add(Submarino);
		g2.add(Portaviones);
		g2.add(Destructor);
		g3.add(Escudo);
		g3.add(Disparar);
		g3.add(Radar);
		GestorJuego.getMiGestorJuego().addObserver(this);
		Jugador.getJugador().addObserver(this);
		CPU.getMiCPU().addObserver(this);
		
		fin = false;
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
			Acciones.add(getDisparar());
			Acciones.add(getRadar());
			Acciones.add(getMisil());
			Acciones.add(getEscudo());
		}
		return Acciones;
	}
	
	private void crearJLabels(JPanel p1, JPanel p2)
    {	
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {	Label label1=clb(i, j, true);
            	Label label2=clb(i, j, false);
                p1.add(label1, BorderLayout.CENTER,i*10+j);
                p2.add(label2, BorderLayout.CENTER,i*10+j);
                listaLJug.add(label1);
            	listaLPC.add(label2);
            }
        }
    }
	
    private Label clb(int pX, int pY, boolean pEsJugador)
    {	
        Label lbl = new Label(pX, pY, pEsJugador);
        lbl.setBorder(BorderFactory.createLineBorder(Color.white));
        lbl.setOpaque(true);
        lbl.setBackground(Color.cyan);
        lbl.addMouseListener(getControler());
        return lbl;
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
			Orientacion.add(getPanel());
			Orientacion.add(getPanel_1());
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
			Portaviones = new JRadioButton("Portaviones("+GestorJuego.getMiGestorJuego().barcosPorColocar(4)+")");
		}
		return Portaviones;
	}
	private JRadioButton getSubmarino() {
		if (Submarino == null) {
			Submarino = new JRadioButton("Submarino("+GestorJuego.getMiGestorJuego().barcosPorColocar(3)+")");
		}
		return Submarino;
	}
	private JRadioButton getDestructor() {
		if (Destructor == null) {
			Destructor = new JRadioButton("Destructor("+GestorJuego.getMiGestorJuego().barcosPorColocar(2)+")");
		}
		return Destructor;
	}
	private JRadioButton getFragata() {
		if (Fragata == null) {
			Fragata = new JRadioButton("Fragata ("+GestorJuego.getMiGestorJuego().barcosPorColocar(1)+")");
		}
		return Fragata;
	}
	private JRadioButton getDisparar() {
		if (Disparar == null) {
			Disparar = new JRadioButton("Disparar");
		}
		return Disparar;
	}
	private JRadioButton getEscudo() {
		if (Escudo == null) {
			Escudo = new JRadioButton("Escudo ("+GestorJuego.getMiGestorJuego().escudosPorColocar()+")");
		}
		return Escudo;
	}
	private JRadioButton getRadar() {
		if (Radar == null) {
			Radar = new JRadioButton("Radar ("+GestorJuego.getMiGestorJuego().radaresPorColocar()+")");
		}
		return Radar;
	}
	
	private JRadioButton getMisil() {
		if (Misil == null) {
			Misil = new JRadioButton("Misil");
		}
		return Misil;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getVertical());
			panel.add(getHorizontal());
		}
		return panel;
	}
	private JLabel getDinero() {
		if (Dinero == null) {
			Dinero = new JLabel("Dinero:500");
		}
		return Dinero;
	}
	private JRadioButton getHorizontal() {
		if (Horizontal == null) {
			Horizontal = new JRadioButton("Horizontal");
		}
		return Horizontal;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getDinero());
		}
		return panel_1;
	}
	
	private Label obtJLabel(int pPos, boolean pJug)
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
		
		boolean conEscudo=false;
		
		if(arg1 instanceof Integer) 
		{	Integer x=(Integer) arg1;
			fin = true;
			if(x==1) {
				frame2=new FinPartida("el Jugador");
				frame2.setVisible(true);
			}else {
				frame2=new FinPartida("la IA");
				frame2.setVisible(true);
			}
		}
		//si se trata de Jugador
		if((arg0 instanceof Jugador || arg0 instanceof GestorJuego || arg0 instanceof CPU) && !(arg1 instanceof Integer))
		{
			ArrayList<Casilla> casillas = (ArrayList<Casilla>) arg1;
			if(casillas.size()>0) {
				if((casillas.size()>1 || !casillas.get(0).estaTocada())) {
					if(casillas.size()==1) {
						Fragata.setText("Fragata("+GestorJuego.getMiGestorJuego().barcosPorColocar(1)+")");
					}else if(casillas.size()==2) {
						Destructor.setText("Destructor("+GestorJuego.getMiGestorJuego().barcosPorColocar(2)+")");
					}else if(casillas.size()==3) {
						Submarino.setText("Submarino("+GestorJuego.getMiGestorJuego().barcosPorColocar(3)+")");
					}else {
						Portaviones.setText("Portaviones("+GestorJuego.getMiGestorJuego().barcosPorColocar(4)+")");
					}
				}
			}
			
			
			for(int i = 0; i < casillas.size(); i++)
			{
				Casilla c = casillas.get(i);
				int pos = (c.getFila() * 10) + c.getColumna();
				boolean jug=false;
				
				if(arg0 instanceof Jugador) {
					jug=true;
				}
				Label lbl = this.obtJLabel(pos, jug);
				
				if(c.tieneRadar()) {
					c.quitarRadar();
					
					if(!c.estaTocada()) {
						lbl.setBackground(Color.green);	
						if((c.tieneBarco())) {
							lbl.setBackground(Color.DARK_GRAY);
						}
					
					}
					
				}
				else if(c.tieneBarco())
				{
					if (c.getBarco().tieneEscudo())
					{
						lbl.setBackground(Color.white);
						conEscudo=true;
					}
					else if(c.estaTocada())
					{
						lbl.setBackground(Color.red);
						if(c.getBarco().estaHundido()) {
							ArrayList<Casilla> casillasBarco=new ArrayList<Casilla>();
							casillasBarco=GestorJuego.getMiGestorJuego().marcarBarcoHundido(c, jug);
							for(int j=0;j<casillasBarco.size();j++) {
								int posicion=(casillasBarco.get(j).getFila()*10)+casillasBarco.get(j).getColumna();
								Label lblBarco= obtJLabel(posicion,jug);
								lblBarco.setBackground(Color.YELLOW);
							}
						}
					}
					else
					{
						lbl.setBackground(Color.gray);
					}
				}
				else
				{
					if(c.estaTocada())
					{
						lbl.setBackground(Color.blue);
					}
					else
					{
						lbl.setBackground(Color.cyan);
					}
				}
			}
			if(conEscudo) {
				Escudo.setText("Escudo ("+GestorJuego.getMiGestorJuego().escudosPorColocar()+")");
			}
		}	
	}
				
	
	private class Controler implements MouseListener  {

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			
			if(!fin) 
			{
				Label l = (Label) e.getSource();
				if(!(Jugador.getJugador().barcosColocados()) && l.esJugador()) 
				{
					int x = l.getCoordX();
					int y = l.getCoordY();
					int longitud;
					
					if(Fragata.isSelected())
					{
						longitud = 1;
					}
					else if(Destructor.isSelected())
					{
						longitud = 2;
					}
					else if(Submarino.isSelected())
					{
						longitud = 3;
					}
					else
					{
						longitud = 4;
					}
					
					GestorJuego.getMiGestorJuego().colocarBarcos(Horizontal.isSelected(), x, y, longitud);
				}
				else if((l.esJugador()) && Jugador.getJugador().barcosColocados() && Escudo.isSelected())
				{
					int x = l.getCoordX();
					int y = l.getCoordY();
					GestorJuego.getMiGestorJuego().colocarEscudo(x,y);
				}
				else if(!(l.esJugador()) && Jugador.getJugador().barcosColocados())
				{	
					int x = l.getCoordX();
					int y = l.getCoordY();
					if(Disparar.isSelected()) 
					{
						GestorJuego.getMiGestorJuego().disparar(x, y);
					}
					else if(Radar.isSelected()) {
						GestorJuego.getMiGestorJuego().radar();
						Radar.setText("Radar ("+GestorJuego.getMiGestorJuego().radaresPorColocar()+")");
					}else if(Misil.isSelected()) {
						GestorJuego.getMiGestorJuego().misil(x, y);
						Dinero.setText("Dinero: "+GestorJuego.getMiGestorJuego().dineroRestanteJug());
					}
				}
			}	
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
