package br.com.wmitrut.cadastrousuarios;

import java.util.List;

import br.com.wmitrut.cadastrousuarios.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PessoaAdapter<T extends Pessoa> extends BaseAdapter {
	private Context context;
	private List<T> labelList;
	public PessoaAdapter(Context context, List<T> labelList) {
		this.context = context;
		this.labelList = labelList;
	}
	@Override
	public int getCount() {
		return labelList.size();
	}
	@Override
	public Pessoa getItem(int posicao) {
		return labelList.get(posicao);
	}
	@Override
	public long getItemId(int posicao) {
		return posicao;
	}
	@Override
	public View getView(int posicao, View view, ViewGroup parent) {

		final LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View viewItem = inflater.inflate(R.layout.item_pessoa, null);
		
		final Pessoa pessoa = labelList.get(posicao);
		viewItem.setTag(pessoa.getId().toString());
		
		TextView tv = (TextView) viewItem.findViewById(R.id.nome_pessoa);
		tv.setText(pessoa.toString());
		
		Button b = (Button)viewItem.findViewById(R.id.editar);
		b.setTag(pessoa.getId().toString());
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), CadastroPessoaActivity.class);
				i.putExtra("id", pessoa.getId().toString());
				v.getContext().startActivity(i);
			}
		});
		
		Button bx = (Button)viewItem.findViewById(R.id.excluir);
		bx.setTag(pessoa.getId());
		
		return viewItem;
	}
	/* 
	 * 
		final LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View viewItem = inflater
				.inflate(R.layout.default_list_item, null);

		final Object item = labelList.get(posicao);

		viewItem.setTag(item);

		TextView descricao = (TextView) viewItem
				.findViewById(R.id_list.descricao);
		descricao.setText(item.toString());
	 */

}
