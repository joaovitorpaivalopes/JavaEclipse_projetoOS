package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conecsao.ConexaoBanco;

public class SistemaParaOs extends JFrame {
	
	ConexaoBanco dao = new ConexaoBanco();
	
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private Connection con;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	public void logar() {
		String sql = "select * from credenciais_usuarios where login=? and senha =?";
		//preparando a consulta ao banco com as caixas de texto
		//? é substituido pelo conteudo das caixas de texto
		Date dataLogin =  new Date();
		Timestamp timestamp = new Timestamp(dataLogin.getTime());
	    Connection con = null; // Inicialize aqui para que possa ser usada em todo o método

		
		try {
			con = dao.conectar();
			pst = con.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, new String(txtSenha.getPassword()));
			//executando a query
			rs = pst.executeQuery();
			//se existir
			if (rs.next()) {
				int userId = rs.getInt("id_user");
				
				String sqlNovoLogin = "UPDATE credenciais_usuarios SET ultimo_login = ? WHERE id_user = ?";
	            PreparedStatement pstUpdate = con.prepareStatement(sqlNovoLogin);
				pstUpdate.setTimestamp(1, timestamp);
				pstUpdate.setInt(2, userId);
				pstUpdate.executeUpdate();
				pstUpdate.close();
				
	            boolean isAdmin = dao.isAdmin(userId);
                String nomeUsuario = dao.getNomeUsuario(userId); 
				
				Principal principal = new Principal(isAdmin, nomeUsuario);
				principal.setVisible(true);
				this.dispose();
				
			} else { //se não 
				JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválido");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close(); // Feche a conexão aqui
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SistemaParaOs frame = new SistemaParaOs();
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
	public SistemaParaOs() {
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setTitle("Projeto OS");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(278, 675, 550, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(49, 56, 62));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
        	public void mouseEntered(MouseEvent e) {
				btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});
		btnLogin.setForeground(SystemColor.text);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo logar
				logar();
			}
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(150, 405, 246, 49);
		btnLogin.setBackground(new Color(107, 132, 142));
		contentPane.add(btnLogin);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(SystemColor.text);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(154, 121, 81, 24);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(SystemColor.text);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(154, 231, 66, 24);
		contentPane.add(lblSenha);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsuario.setBounds(154, 152, 242, 41);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSenha.setBounds(154, 262, 242, 41);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel = new JLabel("Logar");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(216, 50, 73, 33);
		contentPane.add(lblNewLabel);
	}
	private void status() {
		try {
			con = dao.conectar();
			if(con == null) {
				//System.out.println("Erro de conexão");
				//lblStatus.setText("Não Conectado");
			} else {
				//System.out.println("Banco de dados conectado");
				//lblStatus.setText("Conectado");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
