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
import javax.swing.JButton;

public class InterfazJuego extends JFrame implements Observer {
	
	private static InterfazJuego miInterfaz;
	private JPanel contentPane;
	private JPanel TableroPC;
	private JPanel TableroJugador;
	private JPanel Acciones;
	private ButtonGroup g =new ButtonGroup();
	private ButtonGroup g2 =new ButtonGroup();
	private ButtonGroup g3=new ButtonGroup();
	private ButtonGroup g4=new ButtonGroup();
	private ArrayList<Label> listaLJug;
	private ArrayList<Label> listaLPC;
	private Controler controler = null;
	private JRadioButton Escudo;
	private boolean fin;
	FinPartida frame2;
	private JRadioButton Radar;
	private JRadioButton Misil;
	private JPanel panel;
	private JPanel posBarco;
	private JPanel panel_1;
	private JPanel panel_3;
	private JRadioButton Fragata;
	private JRadioButton Destructor;
	private JPanel panelTienda;
	private JLabel Dinero;
	private JRadioButton Submarino;
	private JRadioButton Portaviones;
	private JRadioButton Horizontal;
	private JRadioButton Vertical;
	private JRadioButton RepararBarco;
	private JButton RecolocarRadar;
	private JButton ComprarBomba;
	private JButton ComprarMisil;
	private JButton ComprarEscudo;
	private JButton ComprarReparacionBarco;
	private JRadioButton Bomba;
	private JLabel Aviso;

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
		contentPane.add(getPanel_2());
		contentPane.add(getAcciones());
		crearJLabels(getTableroJugador(),getTableroPC());
		g2.add(Fragata);
		g2.add(Destructor);
		g2.add(Submarino);
		g2.add(Portaviones);
		g3.add(Escudo);
		g3.add(Radar);
		g3.add(Misil);
		g3.add(Bomba);
		g3.add(RecolocarRadar);
		g3.add(RepararBarco);
		GestorJuego.getMiGestorJuego().addObserver(this);
		Jugador.getJugador().addObserver(this);
		CPU.getMiCPU().addObserver(this);
		panelTienda.setVisible(false);
		Aviso.setVisible(false);
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
	private JPanel getAcciones() {
		if (Acciones == null) {
			Acciones = new JPanel();
			Acciones.setLayout(new GridLayout(3, 1, 0, 0));
			Acciones.add(getBomba());
			Acciones.add(getRadar());
			Acciones.add(getMisil());
			Acciones.add(getRepararBarco());
			Acciones.add(getEscudo());
			Acciones.add(getRecolocarRadar());
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
	private JRadioButton getEscudo() {
		if (Escudo == null) {
			Escudo = new JRadioButton("Escudo ("+GestorJuego.getMiGestorJuego().armasPorUsar(2)+")");
		}
		return Escudo;
	}
	private JRadioButton getRadar() {
		if (Radar == null) {
			Radar = new JRadioButton("Radar ("+GestorJuego.getMiGestorJuego().armasPorUsar(4)+")");
		}
		return Radar;
	}
	
	private JRadioButton getMisil() {
		if (Misil == null) {
			Misil= new JRadioButton("Misil ("+GestorJuego.getMiGestorJuego().armasPorUsar(1)+")");
		}
		return Misil;
	}

	private JRadioButton getBomba() {
		if (Bomba == null) {
			Bomba = new JRadioButton("Bomba ("+GestorJuego.getMiGestorJuego().armasPorUsar(5)+")");
		}
		return Bomba;
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
	
	private JPanel getPanel_2() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPosBarco(), BorderLayout.CENTER);
			panel.add(getPanel_5(), BorderLayout.SOUTH);
		}
		return panel;
	}
	private JPanel getPosBarco() {
		if (posBarco == null) {
			posBarco = new JPanel();
			posBarco.setLayout(new GridLayout(1, 2, 0, 0));
			posBarco.add(getPanel_1());
			posBarco.add(getPanel_3());
		}
		return posBarco;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(4, 1, 0, 0));
			panel_1.add(getFragata());
			panel_1.add(getDestructor());
			panel_1.add(getSubmarino());
			panel_1.add(getPortaviones());
		}
		return panel_1;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(2, 1, 0, 0));
			panel_3.add(getHorizontal());
			panel_3.add(getVertical());
		}
		return panel_3;
	}
	private JRadioButton getFragata() {
		if (Fragata == null) {
			Fragata = new JRadioButton("Fragata ("+GestorJuego.getMiGestorJuego().barcosPorColocar(1)+")");
		}
		return Fragata;
	}
	private JRadioButton getDestructor() {
		if (Destructor == null) {
			Destructor = new JRadioButton("Destructor ("+GestorJuego.getMiGestorJuego().barcosPorColocar(2)+")");
		}
		return Destructor;
	}
	
	private JRadioButton getSubmarino() {
		if (Submarino == null) {
			Submarino = new JRadioButton("Submarino ("+GestorJuego.getMiGestorJuego().barcosPorColocar(3)+")");
		}
		return Submarino;
	}
	private JRadioButton getPortaviones() {
		if (Portaviones == null) {
			Portaviones = new JRadioButton("Portaviones ("+GestorJuego.getMiGestorJuego().barcosPorColocar(4)+")");
		}
		return Portaviones;
	}
	private JRadioButton getHorizontal() {
		if (Horizontal == null) {
			Horizontal = new JRadioButton("Horizontal");
		}
		return Horizontal;
	}
	private JRadioButton getVertical() {
		if (Vertical == null) {
			Vertical = new JRadioButton("Vertical");
		}
		return Vertical;
	}
	private JLabel getAviso() {
		if (Aviso == null) {
			Aviso = new JLabel("¡Compre armas!");
		}
		return Aviso;}
	
	private JPanel getPanel_5() {
		if (panelTienda == null) {
			panelTienda = new JPanel();
			panelTienda.setLayout(new GridLayout(4, 0, 0, 0));
			panelTienda.add(getComprarBomba());
			panelTienda.add(getComprarMisil());
			panelTienda.add(getComprarEscudo());
			panelTienda.add(getComprarReparacionBarco());
			panelTienda.add(getAviso());
			panelTienda.add(getDinero());
		}
		return panelTienda;
	}
	private JLabel getDinero() {
		if (Dinero == null) {
			Dinero = new JLabel("Dinero Jugador: "+GestorJuego.getMiGestorJuego().dineroRestanteJug());
		}
		return Dinero;
	}
	private JRadioButton getRepararBarco() {
		if (RepararBarco == null) {
			RepararBarco = new JRadioButton("Reparar barco ("+GestorJuego.getMiGestorJuego().armasPorUsar(3)+")");
		}
		return RepararBarco;
	}
	private JRadioButton getRecolocarRadar() {
		if (RecolocarRadar == null) {
			RecolocarRadar = new JRadioButton("Recolocar Radar");
		}
		return RecolocarRadar;
	}
	private JButton getComprarBomba() {
		if (ComprarBomba == null) {
			ComprarBomba = new JButton("Comprar Bomba");
			ComprarBomba.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					GestorJuego.getMiGestorJuego().comprarArmamento(5);
					Bomba.setText("Bomba ("+GestorJuego.getMiGestorJuego().armasPorUsar(5)+")");
					Dinero.setText("Dinero Jugador: "+GestorJuego.getMiGestorJuego().dineroRestanteJug());
					if(Aviso.isVisible()) {
						Aviso.setVisible(false);
					}
				}
				});
		}
		return ComprarBomba;
	}
	private JButton getComprarMisil() {
		if (ComprarMisil == null) {
			ComprarMisil = new JButton("Comprar misil");
			ComprarMisil.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					GestorJuego.getMiGestorJuego().comprarArmamento(1);
					Misil.setText("Misil ("+GestorJuego.getMiGestorJuego().armasPorUsar(1)+")");
					Dinero.setText("Dinero Jugador: "+GestorJuego.getMiGestorJuego().dineroRestanteJug());
					if(Aviso.isVisible()) {
						Aviso.setVisible(false);
					}
				}
				});
			
		}
		return ComprarMisil;
	}
	private JButton getComprarEscudo() {
		if (ComprarEscudo == null) {
			ComprarEscudo = new JButton("Comprar Escudo");
			ComprarEscudo.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					GestorJuego.getMiGestorJuego().comprarArmamento(2);
					Escudo.setText("Escudo ("+GestorJuego.getMiGestorJuego().armasPorUsar(2)+")");
					Dinero.setText("Dinero Jugador: "+GestorJuego.getMiGestorJuego().dineroRestanteJug());
					if(Aviso.isVisible()) {
						Aviso.setVisible(false);
					}
				}
				});
		}
		
		return ComprarEscudo;
	}
	private JButton getComprarReparacionBarco() {
		if (ComprarReparacionBarco == null) {
			ComprarReparacionBarco = new JButton("Comprar Reparacion Barco");
			ComprarReparacionBarco.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					GestorJuego.getMiGestorJuego().comprarArmamento(3);
					RepararBarco.setText("Reparar barco ("+GestorJuego.getMiGestorJuego().armasPorUsar(3)+")");
					Dinero.setText("Dinero Jugador: "+GestorJuego.getMiGestorJuego().dineroRestanteJug());
					if(Aviso.isVisible()) {
						Aviso.setVisible(false);
					}
				}
				});
		
		
		}
		return ComprarReparacionBarco;
	}
	
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof Integer) 
		{	Integer x=(Integer) arg1;
			
			if(x==1) {
				fin = true;
				frame2=new FinPartida("el Jugador");
				frame2.setVisible(true);
			}else if(x==2){
				fin = true;
				frame2=new FinPartida("la IA");
				frame2.setVisible(true);
			}else {
				Aviso.setVisible(true);
			}
		}
		//si se trata de Jugador
		if((arg0 instanceof Jugador || arg0 instanceof GestorJuego || arg0 instanceof CPU) && !(arg1 instanceof Integer) && !(arg1 instanceof Boolean))
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
		}
		
		if(arg1 instanceof Boolean) {
			posBarco.setVisible(false);
			panelTienda.setVisible(true);
		}
	}
				
	
	private class Controler implements MouseListener  {

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			
			if(!fin) 
			{
				if(!(e.getSource() instanceof JButton)) {
					Label l = (Label) e.getSource();
					if(!(GestorJuego.getMiGestorJuego().barcosColocados()) && l.esJugador()) 
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
						if(GestorJuego.getMiGestorJuego().barcosColocados()) {
							GestorJuego.getMiGestorJuego().barcosColocadosTienda();
						}
					}else if((l.esJugador()) && GestorJuego.getMiGestorJuego().barcosColocados()) {
						int x = l.getCoordX();
						int y = l.getCoordY();
						if(Escudo.isSelected()) {
						
							GestorJuego.getMiGestorJuego().turnoJugador(2,x,y);
							Escudo.setText("Escudo ("+GestorJuego.getMiGestorJuego().armasPorUsar(2)+")");
						}else if(RepararBarco.isSelected()) {
								GestorJuego.getMiGestorJuego().turnoJugador(3,x,y);
								RepararBarco.setText("Reparar barco ("+GestorJuego.getMiGestorJuego().armasPorUsar(3)+")");
						}
					}
					else if(!(l.esJugador()) && GestorJuego.getMiGestorJuego().barcosColocados())
					{	
						int x = l.getCoordX();
						int y = l.getCoordY();
						if(Bomba.isSelected()) 
						{
							GestorJuego.getMiGestorJuego().turnoJugador(5,x, y);
							Bomba.setText("Bomba ("+GestorJuego.getMiGestorJuego().armasPorUsar(5)+")");
						}
						else if(Radar.isSelected()) {
							GestorJuego.getMiGestorJuego().turnoJugador(4,x,y);
							Radar.setText("Radar ("+GestorJuego.getMiGestorJuego().armasPorUsar(4)+")");
						}else if(Misil.isSelected()) {
							GestorJuego.getMiGestorJuego().turnoJugador(1,x,y);
							Misil.setText("Misil ("+GestorJuego.getMiGestorJuego().armasPorUsar(1)+")");
						}else if(RecolocarRadar.isSelected()) {
							//Código para Recolocar el radar
						}
					}
				}
				else {
					
					
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
