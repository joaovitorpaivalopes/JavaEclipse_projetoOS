package conecsao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBanco {
	
	private Connection con;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/linguagemcomercial";
	private String user = "root";
	private String password = "96692342b1";
	
	public Connection conectar() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public boolean cadastrarCliente(String nome, String endereco, String telefone) {
        String sql = "INSERT INTO usuarios (nome, endereco, telefone) VALUES (?, ?, ?)";

        try (Connection con = this.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, telefone);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public boolean isAdmin(int userId) {
        boolean isAdmin = false;
        String sql = "SELECT * FROM administradores WHERE  id_user = ?";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    isAdmin = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdmin;
    }
	
	public String getNomeUsuario(int userId) {
        String nomeUsuario = null;
        String sql = "SELECT nome FROM usuarios WHERE id_user = ?";

        try (Connection conec = conectar(); PreparedStatement stmt = conec.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();            
            if (rs.next()) {
                nomeUsuario = rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomeUsuario;

    }
	
	

}
