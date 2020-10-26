package com.example.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Telefone;

public class BuscaTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO dao;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaTelefoneDoAlunoTask(TelefoneDAO dao, int alunoId, PrimeiroTelefoneEncontradoListener listener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
