import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JanelaExcluir extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaExcluir frame = new JanelaExcluir();
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
	public JanelaExcluir() {
		setClosable(true);
		setBounds(100, 100, 250, 150);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(59, 8, 60, 20);
		getContentPane().add(spinner);
		
		JLabel imagem = new JLabel("");
		imagem.setIcon(new ImageIcon(JanelaExcluir.class.getResource("")));
		imagem.setBounds(129, 11, 95, 98);
		getContentPane().add(imagem);
		
		JButton btnNewButton = new JButton("Excluir");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Connection conexao = null;
				PreparedStatement comando = null;
				
				try {
					conexao = ClasseConexao.Conectar();
					String sql = "DELETE FROM pedidos WHERE id = ?";
					comando = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					comando.setInt(1, (int) spinner.getValue());
					
					if(comando.executeUpdate()>0) {
						imagem.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ok.png")).getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH)));						
					}
				}
				catch(SQLException erro) {
					erro.printStackTrace();
				}
				finally {
					ClasseConexao.FecharConexao(conexao);
					try {
						comando.close();
					}
					catch(SQLException erro) {
						erro.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(10, 36, 109, 23);
		getContentPane().add(btnNewButton);
		
	
	

	}
}
