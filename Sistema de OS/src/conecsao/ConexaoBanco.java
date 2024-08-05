package conecsao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	

}
