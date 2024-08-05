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
					CadastroServicos frame = new CadastroServicos(isAdmin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroServicos(boolean isAdmin) {
        this.isAdmin = isAdmin;
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
        
        JLabel lblUsuario = new JLabel("Nome Usuario");
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
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(40, 245, 162, 24);
		panel.add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(40, 289, 544, 148);
		panel.add(textArea);
		
		JLabel lblCadastrarCliente = new JLabel("Cadastrar Cliente");
		lblCadastrarCliente.setForeground(SystemColor.text);
		lblCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarCliente.setBounds(680, 87, 159, 42);
		contentPane.add(lblCadastrarCliente);
		lblCadastrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroClientes CadastroClientes = new CadastroClientes(isAdmin);
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
				Principal Principal = new Principal(isAdmin);
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
		
		JLabel lblCadastrarServicos = new JLabel("Cadastrar Serviço");
		lblCadastrarServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Você já esta na tela de Cadastrar Serviços");

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
		lblCadastrarServicos.setForeground(SystemColor.text);
		lblCadastrarServicos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarServicos.setBounds(931, 87, 159, 42);
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
                	CadastrarFuncionarios CadastrarFuncionarios = new CadastrarFuncionarios(isAdmin);
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
		
	}
}
