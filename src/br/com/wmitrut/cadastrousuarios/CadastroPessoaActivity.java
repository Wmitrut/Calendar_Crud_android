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
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastroPessoaActivity extends Activity {

	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_pessoa);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = (String) extras.get("id");

			try {
				DatabaseHelper db = DatabaseHelper.getInstance(this);
				Dao<Pessoa, Integer> dao = db.getDao(Pessoa.class);
				Pessoa pessoa = dao.queryForId(Integer.valueOf(id));

				EditText nome = (EditText) findViewById(R.id.nome);
				nome.setText(pessoa.getNome());
				RadioGroup sexo = (RadioGroup) findViewById(R.id.sexo);
				sexo.check("M".equals(pessoa.getSexo()) ? R.id.masculino : R.id.feminino);
				EditText telefone = (EditText) findViewById(R.id.telefone);
				telefone.setText(pessoa.getTelefone());
			} catch (Exception e) {
				Toast.makeText(this, "Erro editando pessoa",
						Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}			
		}
	}

	public void salvar(View v) {
		String nome = getTextoDaTela(R.id.nome);
		RadioGroup sexoR = (RadioGroup)findViewById(R.id.sexo);
		String sexo = (sexoR.getCheckedRadioButtonId() == R.id.masculino) ? "M" : "F";
		String telefone = getTextoDaTela(R.id.telefone);

		try {
			DatabaseHelper db = DatabaseHelper.getInstance(this);
			Dao<Pessoa, Integer> dao = db.getDao(Pessoa.class);
				Pessoa pessoa = null;
				if (id != null)
					pessoa = dao.queryForId(Integer.valueOf(id));
				if (pessoa == null) {
					pessoa = new Pessoa();
					pessoa.setNome(nome);
					pessoa.setSexo(sexo);
					pessoa.setTelefone(telefone);
					dao.create(pessoa);

					Toast.makeText(this, "Salvou!", Toast.LENGTH_SHORT).show();
				} else {
					pessoa.setNome(nome);
					pessoa.setSexo(sexo);
					pessoa.setTelefone(telefone);
					dao.update(pessoa);
					Toast.makeText(this, "Alterou!", Toast.LENGTH_SHORT).show();
				}
				finish();
		} catch (SQLException e) {
			Toast.makeText(this, "Erro salvando pessoa", Toast.LENGTH_SHORT)
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
