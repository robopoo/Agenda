package alura.com.br.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.R;
import alura.com.br.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {


    private final List<Aluno> alunos = new ArrayList<>();
    private Context context;


    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
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

        Aluno alunoDevolido = alunos.get(position);

        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolido.getNome());

        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolido.getTelefone());


        return viewCriada;
    }

    private void clear() {
        alunos.clear();
    }

    private void addAll(List<Aluno> alunos) {

        this.alunos.addAll(alunos);
    }

    public void atualizaListaAluno(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
    }



    public void remove(Aluno alunoSelecionado) {
        alunos.remove(alunoSelecionado);
        notifyDataSetChanged();


    }
}
