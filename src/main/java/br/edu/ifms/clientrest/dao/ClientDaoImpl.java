package br.edu.ifms.clientrest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.clientrest.modelo.Client;

public class ClientDaoImpl implements ClientDao {
	
	private Connection connection;
	
	public ClientDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long adicionar(Client client) throws SQLException {		
		Statement stmt = null;
		long id = 0;
		try {
			String sql = "insert into client (firstName, lastName, email, phone)"+
					" values (\'"+client.getFirstName()+"\',\'"+client.getLastName()+"\',\'"+ client.getEmail() +"\',\'"+ client.getPhone() +"\') RETURNING ID";
			stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getLong(1);
	        }			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		return id;
	}

	@Override
	public List<Client> listar() throws SQLException {
		ArrayList<Client> list = new ArrayList<Client>();
		Statement stmt = null;
		try {
			String sql = "select * from client";
			stmt = this.connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getLong("id"));
				client.setFirstName(rs.getString("firstName"));
				client.setLastName(rs.getString("lastName"));
				client.setEmail(rs.getString("email"));
				client.setPhone(rs.getString("phone"));
				list.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		return list;
	}

}
