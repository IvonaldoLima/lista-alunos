package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.model.Telefone;

public abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final TaskFinalizadaListner listner;

    protected BaseAlunoComTelefoneTask(TaskFinalizadaListner listner) {
        this.listner = listner;
    }

    protected void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listner.quandoFinalizada();
    }

    public interface TaskFinalizadaListner{
        void quandoFinalizada();
    };
}
