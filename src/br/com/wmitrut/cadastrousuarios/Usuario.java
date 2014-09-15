package br.com.wmitrut.cadastrousuarios;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuarios")
public class Usuario {
	@DatabaseField(generatedId = true)
	private Integer id;
	@DatabaseField(canBeNull = false)
	private String nome;
	@DatabaseField(canBeNull = false, unique = true)
	private String username;
	@DatabaseField(canBeNull = false)
	private String password;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void atualizar(Usuario usuario) {
		setNome(usuario.getNome());
		setUsername(usuario.getUsername());
		setPassword(usuario.getPassword());
	}

	@Override
	public String toString() {
		return nome + " (" + username + ")";
	}
}
