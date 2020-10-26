package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.agenda.asynctask.BuscaAlunoTask;
import com.example.agenda.asynctask.RemoveAlunoTask;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.AlunoDao;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunoView {

    private ListaAlunosAdapter adapter;
    private final Context context;
    private AlunoDao dao;
    private String nomeBancoDeDaDados = "agenda.db";

    public ListaAlunoView(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getRoomAlunoDao();
    }

    public void confirmaRemocao(@NonNull MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }


    public void atualizaAlunos() {
        new BuscaAlunoTask(dao, adapter).execute();
    }


    private void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();
    }


    public void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(context);
        listaDeAlunos.setAdapter(adapter);
    }
}
