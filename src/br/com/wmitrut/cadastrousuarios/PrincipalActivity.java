package br.com.wmitrut.cadastrousuarios;

import br.com.wmitrut.cadastrousuarios.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	public void novo(View v) {
		Intent intent = new Intent(this, CadastroUsuarioActivity.class);
		startActivity(intent);
	}
	
	public void listar(View v) {
		Intent intent = new Intent(this, ListaUsuariosActivity.class);
		startActivity(intent);
	}

	public void novoPessoa(View v) {
		Intent intent = new Intent(this, CadastroPessoaActivity.class);
		startActivity(intent);
	}
	
	public void listarPessoa(View v) {
		Intent intent = new Intent(this, ListaPessoasActivity.class);
		startActivity(intent);
	}
}
