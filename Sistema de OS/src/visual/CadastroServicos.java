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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conecsao.ConexaoBanco;

public class CadastroServicos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean isAdmin;
	private JTextField textField;
	private JTextField textField_1;
    private String nomeUsuario;
    private JTextArea txtAreaDeDescricaoOS;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexaoBanco dao = new ConexaoBanco();
	                int userId = 1; 
	                boolean isAdmin = dao.isAdmin(userId);
                    String nomeUsuario = dao.getNomeUsuario(userId); 
					CadastroServicos frame = new CadastroServicos(isAdmin, nomeUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroServicos(boolean isAdmin, String nomeUsuario) {
        this.isAdmin = isAdmin;
        this.nomeUsuario = nomeUsuario; 

        Design();
        

    }
	
	
	private void Design() {
		setResizable(false);
		setTitle("Cadastro de Serviços");
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
        panel.setSize(621, 612); // Definindo o tamanho do painel
        panel.setBackground(new Color(49, 56, 62));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);
        panel.setLayout(null);

        contentPane.add(panel);
        panel.setLocation(462, 168);
        
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
        lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblLogout.setForeground(SystemColor.text);
        lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogout.setBounds(1319, 11, 217, 42);
        panel_1.add(lblLogout);
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
        
        
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setForeground(SystemColor.text);
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrar.setBounds(195, 514, 246, 49);
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
		 		cadastrarOrdemServico();
		 	}
	        });
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(40, 65, 544, 41);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome do Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(40, 28, 162, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo do Serviço");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(40, 132, 162, 24);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(40, 176, 544, 41);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descrição do Serviço:");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(40, 245, 192, 24);
		panel.add(lblNewLabel_2);
		
		txtAreaDeDescricaoOS = new JTextArea();
		txtAreaDeDescricaoOS.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtAreaDeDescricaoOS.setBounds(40, 289, 544, 148);
		txtAreaDeDescricaoOS.setLineWrap(true); // Quebra automática de linha
	    txtAreaDeDescricaoOS.setWrapStyleWord(true); // Quebra entre palavras

	    JScrollPane scrollPane = new JScrollPane(txtAreaDeDescricaoOS);
	    scrollPane.setBounds(40, 289, 544, 148);
	    panel.add(scrollPane);		
		
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
		lblServicos.setForeground(SystemColor.text);
		lblServicos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblServicos.setBounds(452, 87, 159, 42);
		contentPane.add(lblServicos);
		
		JLabel lblCadastrarServicos = new JLabel("*Cadastrar Serviço");
		lblCadastrarServicos.addMouseListener(new MouseAdapter() {
			
			@Override
        	public void mouseEntered(MouseEvent e) {
				lblCadastrarServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblCadastrarServicos.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});
		lblCadastrarServicos.setForeground(SystemColor.text);
		lblCadastrarServicos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarServicos.setBounds(931, 87, 177, 42);
		contentPane.add(lblCadastrarServicos);
		
		JLabel lblCadastrarFuncionario = new JLabel("Cadastrar Funcionario");
		lblCadastrarFuncionario.setBounds(155, 87, 202, 42);
		contentPane.add(lblCadastrarFuncionario);
		lblCadastrarFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarFuncionario.setForeground(SystemColor.text);
		lblCadastrarFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		if (isAdmin) {
            lblCadastrarFuncionario.setVisible(true);
            lblCadastrarFuncionario.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	CadastrarFuncionarios CadastrarFuncionarios = new CadastrarFuncionarios(isAdmin, nomeUsuario);
                	CadastrarFuncionarios.setVisible(true);
    				dispose();   				
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
        } else {
            lblCadastrarFuncionario.setVisible(false);
        }
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
	
	private void cadastrarOrdemServico() {
        String nomeCliente = textField.getText();
        String tipoServico = textField_1.getText();
        String descricaoServico = txtAreaDeDescricaoOS.getText();
        int idSituacaoInicial = 1; // Aguardando aprovação

        if (nomeCliente.isEmpty() || tipoServico.isEmpty() || descricaoServico.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        ConexaoBanco dao = new ConexaoBanco();
        Connection con = null;

        try {
            con = dao.conectar();

            // Buscar o ID do cliente baseado no nome
            String sqlCliente = "SELECT id_user FROM usuarios WHERE nome = ?";
            try (PreparedStatement stmtCliente = con.prepareStatement(sqlCliente)) {
                stmtCliente.setString(1, nomeCliente);
                try (ResultSet rsCliente = stmtCliente.executeQuery()) {
                    if (rsCliente.next()) {
                        int idCliente = rsCliente.getInt("id_user");

                        // Inserir a nova Ordem de Serviço
                        String sqlInserirOS = "INSERT INTO ordens_de_servico (id_cliente, tipo, descricao, id_situacao) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement stmtInserirOS = con.prepareStatement(sqlInserirOS)) {
                            stmtInserirOS.setInt(1, idCliente);
                            stmtInserirOS.setString(2, tipoServico);
                            stmtInserirOS.setString(3, descricaoServico);
                            stmtInserirOS.setInt(4, idSituacaoInicial);

                            int rowsAffected = stmtInserirOS.executeUpdate();

                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar Ordem de Serviço.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.");
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
