package br.com.wmitrut.cadastrousuarios;

import java.sql.SQLException;
import java.util.List;

import br.com.wmitrut.cadastrousuarios.R;
import com.j256.ormlite.dao.Dao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListaPessoasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_pessoas);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			DatabaseHelper db = DatabaseHelper.getInstance(this);
			Dao<Pessoa, Long> dao = db.getDao(Pessoa.class);
			
			List<Pessoa> pessoas = dao.queryForAll();
			PessoaAdapter<Pessoa> adapter =
					new PessoaAdapter<Pessoa>(this, pessoas);
			ListView lv = (ListView)findViewById(R.id.lista_pessoas);
			lv.setAdapter(adapter);
		} catch (Exception e) {
			Toast.makeText(this, "Erro recuperando pessoas", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

	public void deletar(View v) {
		try {
			DatabaseHelper db = DatabaseHelper.getInstance(this);
			Dao<Pessoa, Integer> dao = db.getDao(Pessoa.class);
			int id = (Integer) v.getTag();
			Pessoa pessoa = dao.queryForId(id);
			dao.delete(pessoa);
			onResume();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
