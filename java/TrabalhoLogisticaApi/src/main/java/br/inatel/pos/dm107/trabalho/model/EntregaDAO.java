package br.inatel.pos.dm107.trabalho.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EntregaDAO {

	public void insert(Connection conn, Entrega entrega) throws SQLException {
		String sql = "insert into entrega (numero_pedido, id_cliente, nome_recebedor, cpf_recebedor, data_hora_entrega) "
				+ "values (?, ?, ?, ?, ?);";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, entrega.getNumeroPedido());
		pstm.setInt(2, entrega.getIdCliente());
		pstm.setString(3, entrega.getNomeRecebedor());
		pstm.setString(4, entrega.getCpfRecebedor());
		pstm.setTimestamp(5, entrega.getDataHoraEntrega());

		pstm.execute();
	}

	public Entrega listByPedido(Connection conn, int numPedido) throws SQLException {
		String sql = "select * from entrega where numero_pedido = ?";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, numPedido);
		ResultSet rs = pstm.executeQuery();

		Entrega entrega = new Entrega();
		while (rs.next()) {
			int id = rs.getInt("id");
			// int numeroPedido = rs.getInt("numero_pedido");
			int idCliente = rs.getInt("id_cliente");
			String nomeRecebedor = rs.getString("nome_recebedor");
			String cpfRecebedor = rs.getString("cpf_recebedor");
			Timestamp dataHoraEntrega = rs.getTimestamp("data_hora_entrega");
			entrega = new Entrega(id, numPedido, idCliente, nomeRecebedor, cpfRecebedor, dataHoraEntrega);
		}
		return entrega;
	}
}
