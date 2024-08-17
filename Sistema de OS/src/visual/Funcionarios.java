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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conecsao.ConexaoBanco;

public class Funcionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
	private boolean isAdmin;
    private String nomeUsuario;
    private ConexaoBanco dao;
    private DefaultTableModel model;

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
					Funcionarios frame = new Funcionarios(isAdmin, nomeUsuario);
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
	public Funcionarios(boolean isAdmin, String nomeUsuario) {
		this.isAdmin = isAdmin;
        this.nomeUsuario = nomeUsuario; 
        this.dao = new ConexaoBanco(); // Inicializando `dao` no construtor

		
        Design();
        carregarFuncionarios(); // Carrega os funcionários na tabela

	}
	
	private void Design() {
		setResizable(false);
		setTitle("Funcionarios");
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
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 150, 1173, 600);
        contentPane.add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"#ID Func", "Nome do Func", "Tipo Func", "Último Login"}
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        // Botão para deletar funcionário
        JButton btnDeletar = new JButton("Deletar Funcionário");
        btnDeletar.setBounds(639, 774, 200, 40);
        contentPane.add(btnDeletar);
        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarFuncionarioSelecionado();
            }
        });
       
		
		
		JLabel lblCadastrarCliente = new JLabel("Cadastrar Cliente");
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
		lblCadastrarCliente.setForeground(SystemColor.text);
		lblCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastrarCliente.setBounds(680, 87, 159, 42);
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
		lblFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
               JOptionPane.showMessageDialog(null, "Você já esta na tela de Funcionarios Cadastrados");

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
		
    }
	  private void carregarFuncionarios() {
	        try {
	            Connection con = dao.conectar();
	            String sql = "SELECT * FROM funcionarios";
	            PreparedStatement pst = con.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                Vector<String> row = new Vector<>();
	                row.add(String.valueOf(rs.getInt("ID_Funcionario")));
	                row.add(rs.getString("Nome_Funcionario"));
	                row.add(rs.getString("Tipo_funcionario"));
	                row.add(rs.getTimestamp("Ultimo_Login").toString());
	                model.addRow(row);
	            }
	            rs.close();
	            pst.close();
	            con.close();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e.getMessage());
	        }
	    }

	    private void deletarFuncionarioSelecionado() {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(null, "Selecione um funcionário para deletar.");
	            return;
	        }

	        int idFuncionario = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

	        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o funcionário selecionado?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            try {
	                Connection con = dao.conectar();
	                String sql = "CALL Deletar_Funcionario(?)";
	                PreparedStatement pst = con.prepareStatement(sql);
	                pst.setInt(1, idFuncionario);
	                pst.executeUpdate();
	                pst.close();
	                con.close();

	                model.removeRow(selectedRow);

	                JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso.");
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "Erro ao deletar funcionário: " + e.getMessage());
	            }
	        }
	    }

}
