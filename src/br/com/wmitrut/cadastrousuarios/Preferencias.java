package br.com.wmitrut.cadastrousuarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferencias {
	private static final String NOME_USUARIO = "nome_usuario";
	private static final String CADASTRO_USUARIO = "cadastro_usuario";
	private static Preferencias instance;
	private SharedPreferences preferences;
	private Preferencias(Context c) {
		preferences = c.getSharedPreferences(CADASTRO_USUARIO, 0);
	}
	public static Preferencias getInstance(Context c) {
		if (instance == null)
			instance = new Preferencias(c.getApplicationContext());
		return instance;
	}
	public void setNomeUsuario(String nome) {
		Editor edit = preferences.edit();
		edit.putString(NOME_USUARIO, nome);
		edit.commit();
	}
	public String getNomeUsuario() {
		return preferences.getString(NOME_USUARIO, null);
	}
}
