package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class FinPartida extends JFrame {

	private JPanel contentPane;
	private JLabel GanadorPartida;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				FinPartida frame = new FinPartida();
	//				frame.setVisible(true);
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
		//});
	//}

	/**
	 * Create the frame.
	 */
	public FinPartida(String pGanador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getGanadorPartida());
		this.getGanadorPartida().setText("El Ganador es "+pGanador);
	}
	private JLabel getGanadorPartida() {
		if (GanadorPartida == null) {
			GanadorPartida = new JLabel("Ganador x");
		}
		return GanadorPartida;
	}
}
