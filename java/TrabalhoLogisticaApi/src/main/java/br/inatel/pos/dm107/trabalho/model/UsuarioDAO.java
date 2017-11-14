package br.inatel.pos.dm107.trabalho.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	public Usuario usuarioByUsername(Connection conn, String username) throws SQLException {

		Usuario usuario = null;

		String sql = "SELECT * FROM usuario WHERE username = ?;";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);

		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setUsername(rs.getString("username"));
			usuario.setPassword(rs.getString("password"));
		}
		return usuario;
	}

	
}
