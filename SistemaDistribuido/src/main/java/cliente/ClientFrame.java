package cliente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	static JRadioButton statusconexion;
	static JTable table;
	static JLabel hostname;
	static JLabel hostip;
	public static ClientFrame cliente;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cliente=new ClientFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ClientFrame() {
		init();
		(new Thread(new HiloDelCliente())).start();
	}

	public void init(){
		setTitle("Cliente");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		statusconexion = new JRadioButton("Estado de Conexion");
		statusconexion.setEnabled(false);
		statusconexion.setBackground(SystemColor.menu);
		statusconexion.setForeground(Color.BLACK);
		
		JLabel label1 = new JLabel("Nombre del Host: ");
		
		hostname = new JLabel("");
		
		JLabel label2 = new JLabel("IP:");
		
		hostip = new JLabel("");
		
		JLabel lblNewLabel = new JLabel("Informacion de la PC");
		
		table = new JTable();
		table.setEnabled(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{" S.O", null},
				{" Procesador", null},
				{" Velocidad del Procesador", null},
				{" Ram Total", null},
				{" Ram Disponible", null},
				{" Espacio Total", null},
				{" Espacio Libre", null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(label1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(hostname)
							.addPreferredGap(ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
							.addComponent(statusconexion))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(label2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(hostip)
							.addContainerGap(408, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addContainerGap(365, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(statusconexion, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(label1)
						.addComponent(hostname))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label2)
						.addComponent(hostip))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}


