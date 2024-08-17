package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conecsao.ConexaoBanco;


public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean isAdmin;
	private JComboBox<String> comboBox;
	private JTextField searchField;
	private JTextField tipoOsField;
	private JTextArea descricaoArea;
	private JComboBox<String> situacaoComboBox;
	private List<OrdemServico> ordensServico;
    private String nomeUsuario;

	
	
	private class OrdemServico {
		int id;
		String nomeCliente;
		String dataCriacao;
		String situacao;
		String tipoOs;
		String descricao;

		OrdemServico(int id, String nomeCliente, String dataCriacao, String situacao, String tipoOs, String descricao) {
			this.id = id;
			this.nomeCliente = nomeCliente;
			this.dataCriacao = dataCriacao;
			this.situacao = situacao;
			this.tipoOs = tipoOs;
			this.descricao = descricao;
		}

		@Override
		public String toString() {
			return "#" + id + " " + nomeCliente + " " + dataCriacao + " " + situacao;
		}
	}
	
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexaoBanco dao = new ConexaoBanco();
	                int userId = 1; // Exemplo de ID do usuário logado
	                boolean isAdmin = dao.isAdmin(userId);
                    String nomeUsuario = dao.getNomeUsuario(userId); 
					Principal frame = new Principal(isAdmin, nomeUsuario);
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
	
	public Principal(boolean isAdmin, String nomeUsuario) {
        this.isAdmin = isAdmin;
        this.nomeUsuario = nomeUsuario; 

        Design();
    }
	
	private void Design() {
		setResizable(false);
		setTitle("Sistema de Controle de OS");
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
		lblServicos.setBounds(452, 87, 159, 42);
		contentPane.add(lblServicos);
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Você já esta na tela de Serviços");

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
		lblCadastrarFuncionario.setBounds(145, 87, 200, 42);
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
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(145, 167, 945, 30);
		comboBox.addItem("#ID OS Nome do Cliente Data de Criação Situação");
		contentPane.add(comboBox);

		searchField = new JTextField();
		searchField.setBounds(366, 565, 379, 40);
		contentPane.add(searchField);

		tipoOsField = new JTextField();
		tipoOsField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tipoOsField.setBounds(145, 238, 379, 46);
		contentPane.add(tipoOsField);

		descricaoArea = new JTextArea();
		descricaoArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		descricaoArea.setBounds(145, 345, 379, 193);
		descricaoArea.setLineWrap(true); // Quebra automática de linha
		descricaoArea.setWrapStyleWord(true); // Quebra entre palavras
		contentPane.add(descricaoArea);

		situacaoComboBox = new JComboBox<>();
		situacaoComboBox.setBounds(576, 290, 379, 30);
		contentPane.add(situacaoComboBox);

		JButton btnAtualizarSituacao = new JButton("Atualizar Situação");
		btnAtualizarSituacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAtualizarSituacao.setBounds(576, 342, 379, 49);
		contentPane.add(btnAtualizarSituacao);

		JButton btnImprimirOs = new JButton("Imprimir OS");
		btnImprimirOs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnImprimirOs.setBounds(576, 414, 379, 49);
		contentPane.add(btnImprimirOs);

		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGerarRelatorio.setBounds(576, 489, 379, 49);
		contentPane.add(btnGerarRelatorio);
		
		JLabel lblNewLabel = new JLabel("Tipo de Serviço:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(145, 207, 130, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição do Serviço:");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(145, 311, 189, 28);
		contentPane.add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Situação do Serviço:");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(576, 254, 171, 28);
		contentPane.add(lblNewLabel_2);

		// Carregar ordens de serviço
		carregarOrdensServico();

		// Adicionar listeners
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() > 0) {
					exibirDetalhesOrdemServico(comboBox.getSelectedIndex() - 1);
				}
			}
		});

		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarOrdensServico();
			}
		});

		btnAtualizarSituacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarSituacaoOrdemServico(comboBox.getSelectedIndex() - 1);
			}
		});

		btnImprimirOs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});

		btnGerarRelatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gerarRelatorio();
			}
		});
	}

	private void carregarOrdensServico() {
		ordensServico = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linguagemcomercial", "root", "96692342b1");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT os.id_os, u.nome, os.data_criacao, s.descricao, os.tipo, os.descricao " +
								"FROM ordens_de_servico os " +
								"JOIN usuarios u ON os.id_cliente = u.id_user " +
								"JOIN situacoes_ordem_servico s ON os.id_situacao = s.id_situacao")) {

			while (rs.next()) {
				OrdemServico os = new OrdemServico(
						rs.getInt("id_os"),
						rs.getString("nome"),
						rs.getString("data_criacao"),
						rs.getString("descricao"),
						rs.getString("tipo"),
						rs.getString("os.descricao"));
				ordensServico.add(os);
				comboBox.addItem(os.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void filtrarOrdensServico() {
		String filtro = searchField.getText().toLowerCase();
		comboBox.removeAllItems();
		comboBox.addItem("#ID OS Nome do Cliente Data de Criação Situação");
		for (OrdemServico os : ordensServico) {
			if (os.toString().toLowerCase().contains(filtro)) {
				comboBox.addItem(os.toString());
			}
		}
	}

	private void exibirDetalhesOrdemServico(int index) {
		OrdemServico os = ordensServico.get(index);
		tipoOsField.setText(os.tipoOs);
		descricaoArea.setText(os.descricao);
		situacaoComboBox.removeAllItems();
		situacaoComboBox.addItem(os.situacao);
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linguagemcomercial", "root", "96692342b1");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT descricao FROM situacoes_ordem_servico")) {

			while (rs.next()) {
				String descricao = rs.getString("descricao");
				if (!descricao.equals(os.situacao)) {
					situacaoComboBox.addItem(descricao);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void atualizarSituacaoOrdemServico(int index) {
		OrdemServico os = ordensServico.get(index);
		String novaSituacao = (String) situacaoComboBox.getSelectedItem();

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linguagemcomercial", "root", "96692342b1");
				PreparedStatement ps = con.prepareStatement("UPDATE ordens_de_servico SET id_situacao = ? WHERE id_os = ?")) {
			ps.setInt(1, getSituacaoId(novaSituacao));
			ps.setInt(2, os.id);
			ps.executeUpdate();

			// Atualizar a situação localmente
			os.situacao = novaSituacao;
			JOptionPane.showMessageDialog(this, "Situação atualizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getSituacaoId(String descricao) throws SQLException {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linguagemcomercial", "root", "96692342b1");
				PreparedStatement ps = con.prepareStatement("SELECT id_situacao FROM situacoes_ordem_servico WHERE descricao = ?")) {
			ps.setString(1, descricao);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_situacao");
			} else {
				throw new SQLException("Situação não encontrada: " + descricao);
			}
		}
	}

	private void imprimirOS() {
        if (comboBox.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma Ordem de Serviço para imprimir.");
            return;
        }

        OrdemServico os = ordensServico.get(comboBox.getSelectedIndex() - 1);
        StringBuilder conteudoOS = new StringBuilder();
        conteudoOS.append("Ordem de Serviço\n\n");
        conteudoOS.append("ID: ").append(os.id).append("\n");
        conteudoOS.append("Cliente: ").append(os.nomeCliente).append("\n");
        conteudoOS.append("Data de Criação: ").append(os.dataCriacao).append("\n");
        conteudoOS.append("Situação: ").append(os.situacao).append("\n");
        conteudoOS.append("Tipo: ").append(os.tipoOs).append("\n");
        conteudoOS.append("Descrição: ").append(os.descricao).append("\n");

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                graphics.drawString(conteudoOS.toString(), 100, 100);
                return Printable.PAGE_EXISTS;
            }
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

	private void gerarRelatorio() {
		// Implementar a lógica de geração de relatório
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linguagemcomercial", "root", "96692342b1");
	             PreparedStatement ps = con.prepareStatement(
	                     "SELECT COUNT(*) AS total FROM ordens_de_servico WHERE MONTH(data_criacao) = ? AND YEAR(data_criacao) = ?")) {

	            LocalDate currentDate = LocalDate.now();
	            int currentMonth = currentDate.getMonthValue();
	            int currentYear = currentDate.getYear();

	            ps.setInt(1, currentMonth);
	            ps.setInt(2, currentYear);

	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                int totalOS = rs.getInt("total");
	                String relatorio = "Relatório de Ordens de Serviço\n\n";
	                relatorio += "Mês: " + DateTimeFormatter.ofPattern("MMMM").format(currentDate) + "\n";
	                relatorio += "Ano: " + currentYear + "\n";
	                relatorio += "Total de Ordens de Serviço: " + totalOS + "\n";
	                JOptionPane.showMessageDialog(this, relatorio);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
		
		
	
}
