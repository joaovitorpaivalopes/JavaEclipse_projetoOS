package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conecsao.ConexaoBanco;


public class CadastroClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private boolean isAdmin;
	private JTextField textField_1;
	private JTextField textField_2;
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
					CadastroClientes frame = new CadastroClientes(isAdmin, nomeUsuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroClientes(boolean isAdmin, String nomeUsuario) {
        this.isAdmin = isAdmin;
        this.nomeUsuario = nomeUsuario; 

        Design();
    }
	
	
	private void Design() {
		setResizable(false);
		setTitle("Cadastro de Clientes");
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
        panel.setSize(621, 471); // Definindo o tamanho do painel
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
        btnCadastrar.setBounds(195, 390, 246, 49);
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
				String nome = textField.getText();
		        String endereco = textField_1.getText();
		        String telefone = textField_2.getText();

		        if (nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
		        } else {
		            ConexaoBanco dao = new ConexaoBanco();
		            boolean sucesso = dao.cadastrarCliente(nome, endereco, telefone);

		            if (sucesso) {
		                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
		                textField.setText("");
		                textField_1.setText("");
		                textField_2.setText("");
		            } else {
		                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente.");
		            }
		        }
			}
        });
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(51, 35, 543, 41);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(51, 10, 73, 24);
		lblNome.setForeground(SystemColor.text);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNome);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(51, 156, 543, 41);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Endereço:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(51, 132, 90, 24);
		panel.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setBounds(51, 276, 543, 41);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefone");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(51, 251, 90, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblCadastrarCliente = new JLabel("*Cadastrar Cliente");
		lblCadastrarCliente.addMouseListener(new MouseAdapter() {
			
			@Override
        	public void mouseEntered(MouseEvent e) {
				lblCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblCadastrarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
		});
		lblCadastrarCliente.setForeground(SystemColor.text);
		lblCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarCliente.setBounds(680, 87, 177, 42);
		contentPane.add(lblCadastrarCliente);
		
		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setForeground(SystemColor.text);
		lblServicos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblServicos.setBounds(452, 87, 159, 42);
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
}
