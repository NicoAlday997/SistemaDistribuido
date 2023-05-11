package servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cliente.ClientFrame;

public class ServidorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;

	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ServidorFrame(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ServidorFrame()
	{
		init();
		(new Thread(new HiloDelServidor())).start();
		new ClientFrame().setExtendedState(ICONIFIED);

	}
	
	
	void init() {
		setTitle("Servidor");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setToolTipText("");
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "IP", "Procesador", "Frecuancia (Ghz)", "Ram Disp", "Espacio Disp", "Rank"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(92);
		table.setBounds(10, 36, 752, 314);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(table, BorderLayout.CENTER);
		contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
		contentPane.add(table);
	}
}