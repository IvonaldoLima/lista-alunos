package com.example.agenda.asynctask;

import com.example.agenda.database.dao.AlunoDao;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDao alunoDao;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;


    public SalvaAlunoTask(AlunoDao alunoDao, Aluno aluno, Telefone telefoneFixo,
                          Telefone telefoneCelular, TelefoneDAO telefoneDAO, TaskFinalizadaListner listener) {
        super(listener);
        this.alunoDao = alunoDao;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDao.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
        return null;
    }

}
