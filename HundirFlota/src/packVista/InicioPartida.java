package packVista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;

public class InicioPartida extends JFrame {

	private JPanel contentPane;
	private JButton ComienzoPartida;
	private JTextField txtEscribeTuNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioPartida frame = new InicioPartida();
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
	public InicioPartida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getComienzoPartida());
		contentPane.add(getTxtEscribeTuNombre());
	}
	private class Imagen extends javax.swing.JPanel {
		 
		public Imagen() {
		this.setSize(450, 251); //se selecciona el tamaño del panel
		}
		 
		//Se crea un método cuyo parámetro debe ser un objeto Graphics
		 
		public void paint(Graphics grafico) {
		Dimension height = getSize();
		 
		//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
		 
		ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/hundir-la-flota-juego-de-mesa.jpg")); 
		 
		//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
		 
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		 
		setOpaque(false);
		super.paintComponent(grafico);
		}
		
		}
	private JButton getComienzoPartida() {
		if (ComienzoPartida == null) {
			ComienzoPartida = new JButton("Comenzar la partida");
			ComienzoPartida.setBounds(0, 218, 428, 26);
		}
		return ComienzoPartida;
	}
	private JTextField getTxtEscribeTuNombre() {
		if (txtEscribeTuNombre == null) {
			txtEscribeTuNombre = new JTextField();
			txtEscribeTuNombre.setText("Escribe tu nombre");
			txtEscribeTuNombre.setBounds(0, 192, 428, 26);
			txtEscribeTuNombre.setColumns(10);
		}
		return txtEscribeTuNombre;
	}
	
	private class BackgroundPanel extends JPanel
	{
	    private Image background;
	 
	    public BackgroundPanel(Image background)
	    {
	        this.background = background;
	        setLayout( new BorderLayout() );
	    }
	 
	    @Override
	    protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	 
	        g.drawImage(background, 0, 0, null);
	    }
	 
	    @Override
	    public Dimension getPreferredSize()
	    {
	        return new Dimension(background.getWidth(this), background.getHeight(this));
	    }
	}
}
