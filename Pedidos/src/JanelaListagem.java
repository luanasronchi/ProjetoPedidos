import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JanelaListagem extends JInternalFrame {
	private JTable tabela1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaListagem frame = new JanelaListagem();
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
	public JanelaListagem() {
		setBounds(100, 100, 300, 190);
		getContentPane().setLayout(null);
		
		tabela1 = new JTable();
		tabela1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabela1.getSelectedRow();
				
				String id = tabela1.getModel().getValueAt(row,0).toString();
				String nome = tabela1.getModel().getValueAt(row,1).toString();
				String quantidade = tabela1.getModel().getValueAt(row,2).toString();
				String total = tabela1.getModel().getValueAt(row,3).toString();
				
				JOptionPane.showMessageDialog(null, "Codigo: " + id + "\n Nome: " + nome + "\n Quantidade: " + quantidade + "\n Pre�o: " + total); 
				
			}
		});
		tabela1.setBounds(10, 11, 264, 105);
		getContentPane().add(tabela1);
		
		JButton btnNewButton = new JButton("Listar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = null;
				PreparedStatement comando = null;
				ResultSet rs;
				conexao = ClasseConexao.Conectar();
				String sql = "SELECT * FROM pedidos";
				try {
					comando = conexao.prepareStatement(sql);
					rs = comando.executeQuery();
					tabela1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null, erro);
				}
			}
		});
		btnNewButton.setBounds(185, 127, 89, 23);
		getContentPane().add(btnNewButton);

	}

}
