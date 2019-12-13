package alura.com.br.dao;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorIds = 1;

    public void salvar(Aluno aluno) {

        aluno.setId(contadorIds);
        alunos.add(aluno);
        contadorIds++;
    }


    public void atualizar(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);

        if(alunoEncontrado != null){
            int posicao = alunos.indexOf(alunoEncontrado);

            alunos.set(posicao, aluno);
        }


    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {

        for (Aluno a : alunos) {
            if(a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> getAlunos(){

        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoSelecionado) {

        Aluno alunoParaRemover = buscaAlunoPeloId(alunoSelecionado);

        if(alunoParaRemover != null){
            alunos.remove(alunoParaRemover);
        }
    }
}
