package br.com.wmitrut.cadastrousuarios;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "compromissos")
public class Compromissos {
	@DatabaseField(generatedId = true)
	private Integer id;
	@DatabaseField(canBeNull = false)
	private String nome;
	@DatabaseField(canBeNull = false, unique = true)
	private String sexo;
	@DatabaseField(canBeNull = false)
	private String telefone;

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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void atualizar(Compromissos pessoa) {
		setNome(pessoa.getNome());
		setSexo(pessoa.getSexo());
		setTelefone(pessoa.getTelefone());
	}

	@Override
	public String toString() {
		return nome + " (" + telefone + ")";
	}
}
