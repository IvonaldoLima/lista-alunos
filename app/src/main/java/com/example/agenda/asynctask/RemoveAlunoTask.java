package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.AlunoDao;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask{

    private final AlunoDao dao;
    private final ListaAlunosAdapter adapter;
    private final Aluno aluno;


    public RemoveAlunoTask(AlunoDao dao, ListaAlunosAdapter adapter, Aluno aluno) {
        this.dao = dao;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        dao.remove(aluno);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        adapter.remove(aluno);
    }
}
