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

public class ListaUsuariosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_usuarios);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			DatabaseHelper db = DatabaseHelper.getInstance(this);
			Dao<Usuario, Long> dao = db.getDao(Usuario.class);
			
			List<Usuario> usuarios = dao.queryForAll();
			UsuarioAdapter<Usuario> adapter =
					new UsuarioAdapter<Usuario>(this, usuarios);
			ListView lv = (ListView)findViewById(R.id.lista_usuarios);
			lv.setAdapter(adapter);
		} catch (Exception e) {
			Toast.makeText(this, "Erro recuperando usuarios", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_usuarios, menu);
		return true;
	}

	public void deletar(View v) {
		try {
			DatabaseHelper db = DatabaseHelper.getInstance(this);
			Dao<Usuario, Integer> dao = db.getDao(Usuario.class);
			int id = (Integer) v.getTag();
			Usuario usuario = dao.queryForId(id);
			dao.delete(usuario);
			onResume();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
