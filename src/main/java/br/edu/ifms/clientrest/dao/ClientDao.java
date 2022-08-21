package br.edu.ifms.clientrest.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifms.clientrest.modelo.Client;

public interface ClientDao {
	
	public long adicionar(Client client) throws SQLException;
	public List<Client> listar() throws SQLException;

}
