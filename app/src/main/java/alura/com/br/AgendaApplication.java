package alura.com.br;

import android.app.Application;

import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;


public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        criaListaDeTeste();
    }

    private void criaListaDeTeste() {
        AlunoDAO alunoDAO = new AlunoDAO();

        alunoDAO.salvar(new Aluno("Mychel", "07367368106", "mychel.platini2@gmail.com"));
        alunoDAO.salvar(new Aluno("Platini", "999252702", "mychel.platini@yahoo.com"));
        alunoDAO.salvar(new Aluno("Katlin", "3729657451", "katlinstaubsantos@gmail.com"));
    }
}
