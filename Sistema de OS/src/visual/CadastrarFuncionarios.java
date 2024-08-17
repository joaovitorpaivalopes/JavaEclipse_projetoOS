package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conecsao.ConexaoBanco;

public class CadastrarFuncionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean isAdmin;
	private JTextField txtFuncionario;
	private JTextField txtLogin;
	private JTextField txtSenha;
	private JTextField txtConSenha;
    private String nomeUsuario;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexaoBanco dao = new ConexaoBanco();
	                int userId = 1; // Exemplo de ID do usuário logado
	                boolean isAdmin = dao.isAdmin(userId);
	                String nomeUsuario = dao.getNomeUsuario(userId); 
					CadastrarFuncionarios frame = new CadastrarFuncionarios(isAdmin, nomeUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastrarFuncionarios(boolean isAdmin, String nomeUsuario) {
        this.isAdmin = isAdmin;
        this.nomeUsuario = nomeUsuario; 

		Design();
	}
	
	private void Design() {
		setResizable(false);
		setTitle("Cadastro de Funcionarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(35, 42, 46));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		
		JPanel panel = new JPanel();
        panel.setSize(621, 519); // Definindo o tamanho do painel
        panel.setBackground(new Color(49, 56, 62));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);
        panel.setLayout(null);

        contentPane.add(panel);
        panel.setLocation(462, 198);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 1920, 63);
        panel_1.setBackground(new Color(49, 56, 62));
        panel_1.setLayout(null);

        contentPane.add(panel_1);
        
        JLabel lblUsuario = new JLabel(nomeUsuario);
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsuario.setForeground(SystemColor.text);
        lblUsuario.setBounds(1135, 11, 217, 42);
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblUsuario);
        
        JLabel lblLogout = new JLabel("Logout");
        lblLogout.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		SistemaParaOs SistemaParaOs = new SistemaParaOs();
        		SistemaParaOs.setVisible(true);
				dispose(); 
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
        });
        lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblLogout.setForeground(SystemColor.text);
        lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogout.setBounds(1374, 11, 110, 42);
        panel_1.add(lblLogout);
        
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setForeground(SystemColor.text);
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrar.setBounds(195, 448, 246, 49);
        btnCadastrar.setBackground(new Color(107, 132, 142));
		panel.add(btnCadastrar);
		 btnCadastrar.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	        	}
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		 cadastrarFuncionario();
		 	}
	        });
		
		txtFuncionario = new JTextField();
		txtFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtFuncionario.setBounds(51, 35, 543, 41);
		panel.add(txtFuncionario);
		txtFuncionario.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome Funcionario:");
		lblNome.setBounds(51, 10, 185, 24);
		lblNome.setForeground(SystemColor.text);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNome);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLogin.setBounds(51, 156, 543, 41);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(51, 132, 90, 24);
		panel.add(lblNewLabel);
		
		txtSenha = new JTextField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSenha.setBounds(51, 276, 543, 41);
		panel.add(txtSenha);
		txtSenha.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);  centralizando o texto dentor de uma label
		lblNewLabel_1.setBounds(51, 251, 90, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmar Senha:");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(51, 356, 185, 24);
		panel.add(lblNewLabel_2);
		
		txtConSenha = new JTextField();
		txtConSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtConSenha.setBounds(51, 379, 543, 41);
		panel.add(txtConSenha);
		txtConSenha.setColumns(10);
		
		JLabel lblCadastrarCliente = new JLabel("Cadastrar Cliente");
		lblCadastrarCliente.setForeground(SystemColor.text);
		lblCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarCliente.setBounds(680, 87, 159, 42);
		contentPane.add(lblCadastrarCliente);
		lblCadastrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroClientes CadastroClientes = new CadastroClientes(isAdmin, nomeUsuario);
				CadastroClientes.setVisible(true);
				dispose(); 
			}
			@Override
        	public void mouseEntered(MouseEvent e) {
				lblCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});
		
		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setForeground(SystemColor.text);
		lblServicos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblServicos.setBounds(452, 87, 82, 42);
		contentPane.add(lblServicos);
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Principal Principal = new Principal(isAdmin, nomeUsuario);
				Principal.setVisible(true);
				dispose(); 
			}
			@Override
        	public void mouseEntered(MouseEvent e) {
				lblServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblServicos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});
		
		JLabel lblCadastrarServicos = new JLabel("Cadastrar Serviço");
		lblCadastrarServicos.setForeground(SystemColor.text);
		lblCadastrarServicos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarServicos.setBounds(931, 87, 159, 42);
		contentPane.add(lblCadastrarServicos);
		lblCadastrarServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroServicos CadastroServicos = new CadastroServicos(isAdmin, nomeUsuario);
				CadastroServicos.setVisible(true);
				dispose();
			}
			@Override
        	public void mouseEntered(MouseEvent e) {
				lblCadastrarServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblCadastrarServicos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});
		
		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar Funcionario");
		lblCadastrarFuncionario.setBounds(160, 87, 201, 42);
		contentPane.add(lblCadastrarFuncionario);
		lblCadastrarFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarFuncionario.setForeground(SystemColor.text);
		lblCadastrarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
        lblCadastrarFuncionario.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Você já esta na tela de cadastro de usuários");
             }
            @Override
             public void mouseEntered(MouseEvent e) {
               lblCadastrarFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	lblCadastrarFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
         });
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.setForeground(SystemColor.text);
		lblFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFuncionarios.setBounds(1193, 87, 159, 42);
		contentPane.add(lblFuncionarios);
		if (isAdmin) {
			lblFuncionarios.setVisible(true);
			lblFuncionarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Funcionarios Funcionarios = new Funcionarios(isAdmin, nomeUsuario);
					Funcionarios.setVisible(true);
					dispose();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
		 } else {
			 lblFuncionarios.setVisible(false);
	        }
	}
	 private void cadastrarFuncionario() {
		 String nomeFuncionario = txtFuncionario.getText();
		    String login = txtLogin.getText();
		    String senha = txtSenha.getText();
		    String confirmarSenha = txtConSenha.getText();

		    if (!senha.equals(confirmarSenha)) {
		        JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
		        return;
		    }

		    ConexaoBanco dao = new ConexaoBanco();
		    Connection con = null;

		    try {
		        con = dao.conectar();

		        // Verificar se o funcionário existe na tabela usuarios
		        String sql = "SELECT id_user FROM usuarios WHERE nome = ?";
		        try (PreparedStatement stmt = con.prepareStatement(sql)) {
		            stmt.setString(1, nomeFuncionario);
		            try (ResultSet rs = stmt.executeQuery()) {
		                if (rs.next()) {
		                    int idUser = rs.getInt("id_user");

		                    // Inserir as credenciais do funcionário na tabela credenciais_usuarios
		                    String insertSql = "INSERT INTO credenciais_usuarios (id_user, login, senha, ultimo_login) VALUES (?, ?, ?, NOW())";
		                    try (PreparedStatement insertStmt = con.prepareStatement(insertSql)) {
		                        insertStmt.setInt(1, idUser);
		                        insertStmt.setString(2, login);
		                        insertStmt.setString(3, senha);

		                        int rowsAffected = insertStmt.executeUpdate();

		                        if (rowsAffected > 0) {
		                            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
		                        } else {
		                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário!");
		                        }
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado na tabela de usuários!");
		                }
		            }
		        }

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
		    } finally {
		        try {
		            if (con != null) con.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
	    }
}
