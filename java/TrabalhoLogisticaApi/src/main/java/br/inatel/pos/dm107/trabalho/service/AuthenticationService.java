package br.inatel.pos.dm107.trabalho.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.StringTokenizer;

import br.inatel.pos.dm107.trabalho.model.ConnectionFactory;
import br.inatel.pos.dm107.trabalho.model.Usuario;
import br.inatel.pos.dm107.trabalho.model.UsuarioDAO;

public class AuthenticationService {

	public boolean authenticate(String authCredentials) {
		if (null == authCredentials)
			return false;

		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		

		try {
			
			Connection conn = new ConnectionFactory().getConnection();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.usuarioByUsername(conn, username);
			
			if (usuario != null && usuario.getPassword().equals(password)) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}