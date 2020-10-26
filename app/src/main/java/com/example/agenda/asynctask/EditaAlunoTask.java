package com.example.agenda.asynctask;

import com.example.agenda.database.dao.AlunoDao;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;
import com.example.agenda.model.TipoTelefone;

import java.util.List;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDao alunoDao;
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final List<Telefone> telefonesDoAluno;


    public EditaAlunoTask(AlunoDao alunoDao, TelefoneDAO telefoneDAO, Aluno aluno,
                          Telefone telefoneFixo, Telefone telefoneCelular, List<Telefone> telefonesDoAluno, TaskFinalizadaListner listener) {
        super(listener);
        this.alunoDao = alunoDao;
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefonesDoAluno = telefonesDoAluno;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        alunoDao.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualizaLista(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipoTelefone() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }


}
