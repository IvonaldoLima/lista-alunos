package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.asynctask.BuscaTelefoneDoAlunoTask;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.TipoTelefone;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
         dao = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);

        Aluno alunoDevolvido = alunos.get(position);
        vincula(viewCriada, alunoDevolvido);

        return viewCriada;
    }

    private void vincula(View viewCriada, Aluno alunoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNomeCompleto());

        TextView dataCadastro = viewCriada.findViewById(R.id.item_aluno_data_cadastro);
        dataCadastro.setText(alunoDevolvido.getDataDeCadastroFormatada());

        TextView telefoneFixo = viewCriada.findViewById(R.id.item_aluno_telefone);
        new BuscaTelefoneDoAlunoTask(dao, alunoDevolvido.getId(), TipoTelefone.FIXO, telefoneEncontrado ->{
            telefoneFixo.setText(telefoneEncontrado.getNumero());
        }).execute();

        TextView telefoneCelulaar = viewCriada.findViewById(R.id.item_aluno_celular);
        new BuscaTelefoneDoAlunoTask(dao, alunoDevolvido.getId(), TipoTelefone.CELULAR, telefoneEncontrado ->{
            telefoneCelulaar.setText(telefoneEncontrado.getNumero());
        }).execute();
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
