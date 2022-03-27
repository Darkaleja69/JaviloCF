package packVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class InterfazJuego extends JFrame {

	private JPanel contentPane;
	private JPanel TableroPC;
	private JPanel TableroJugador;
	private JPanel PosicionBarco;
	private JPanel Acciones;

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
		crearButtons(getTableroJugador(),getTableroPC());
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
		}
		return PosicionBarco;
	}
	private JPanel getAcciones() {
		if (Acciones == null) {
			Acciones = new JPanel();
		}
		return Acciones;
	}
	
	private void crearButtons(JPanel p1, JPanel p2)
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                p1.add(cbt(), BorderLayout.CENTER,i*10+j);
                p2.add(cbt(), BorderLayout.CENTER,i*10+j);
            }
        }
    }
	
    private JButton cbt()
    {
        JButton btnNewButton = new JButton();
        return btnNewButton;
    }
    
    
	
}
