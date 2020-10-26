package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.AlunoDao;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

import java.util.List;

public class BuscaAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {

    private AlunoDao dao;
    private ListaAlunosAdapter adapter;

    public BuscaAlunoTask(AlunoDao dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}

