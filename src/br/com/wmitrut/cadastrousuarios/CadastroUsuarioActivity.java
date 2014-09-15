package br.com.wmitrut.cadastrousuarios;

import java.sql.SQLException;

import br.com.wmitrut.cadastrousuarios.R;

import com.j256.ormlite.dao.Dao;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuarioActivity extends Activity {

	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = (String) extras.get("id");

			try {
				DatabaseHelper db = DatabaseHelper.getInstance(this);
				Dao<Usuario, Integer> dao = db.getDao(Usuario.class);
				Usuario usuario = dao.queryForId(Integer.valueOf(id));

				EditText nome = (EditText) findViewById(R.id.nome);
				nome.setText(usuario.getNome());
				EditText senha = (EditText) findViewById(R.id.senha);
				senha.setText(usuario.getPassword());
				EditText username = (EditText) findViewById(R.id.username);
				username.setText(usuario.getUsername());
			} catch (Exception e) {
				Toast.makeText(this, "Erro editando usuario",
						Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		} else {
			String nomeU = Preferencias.getInstance(this).getNomeUsuario();
			EditText nome = (EditText) findViewById(R.id.nome);
			nome.setText(nomeU);
		}
	}

	public void salvar(View v) {
		String nome = getTextoDaTela(R.id.nome);
		Preferencias.getInstance(this).setNomeUsuario(nome);

		String username = getTextoDaTela(R.id.username);
		String senha = getTextoDaTela(R.id.senha);
		String confirmacao = getTextoDaTela(R.id.confirmacao);

		try {
			DatabaseHelper db = DatabaseHelper.getInstance(this);
			Dao<Usuario, Integer> dao = db.getDao(Usuario.class);
			if (senha.equals(confirmacao)) {
				Usuario usuario = null;
				if (id != null)
					usuario = dao.queryForId(Integer.valueOf(id));
				if (usuario == null) {
					usuario = new Usuario();
					usuario.setNome(nome);
					usuario.setUsername(username);
					usuario.setPassword(senha);
					dao.create(usuario);

					Toast.makeText(this, "Salvou!", Toast.LENGTH_SHORT).show();
				} else {
					usuario.setNome(nome);
					usuario.setUsername(username);
					usuario.setPassword(senha);
					dao.update(usuario);
					Toast.makeText(this, "Alterou!", Toast.LENGTH_SHORT).show();
				}
				finish();
			} else {
				Toast.makeText(this, "Confirmação não confere com a senha!",
						Toast.LENGTH_SHORT).show();
			}
		} catch (SQLException e) {
			Toast.makeText(this, "Erro salvando usuario", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
		}
	}

	public void cancelar(View v) {
		finish();
	}

	// Alt + Shift + L = Extrair Variável Local
	// Alt + Shift + M = Extrair Método
	// Alt + Shift + R = Renomear
	private String getTextoDaTela(int id) {
		return ((EditText) findViewById(id)).getText().toString();
	}

}
