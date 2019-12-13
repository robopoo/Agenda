package alura.com.br.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;



import static alura.com.br.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {


    public static final String TITULO_APP_BAR = "Lista de Alunos";

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListView listaDeAlunos;
    private ListaAlunosAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);

        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = menuInfo.position;


        int itemId = item.getItemId();

        if(itemId == R.id.activity_lista_alunos_menu_remover )  {

            new AlertDialog.Builder(this)
                    .setTitle("Removendo Aluno")
                    .setMessage("Tem certeza que deseja remover aluno?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Aluno alunoSelecionado = adapter.getItem(position);
                            alunoDAO.remove(alunoSelecionado);
                            adapter.remove(alunoSelecionado);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        }


        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }


    public void atualizaAlunos(){

        adapter.atualizaListaAluno(alunoDAO.getAlunos());
    }

    public void configuraLista(){

        listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        //loading the listView...

        adapter = new ListaAlunosAdapter(this);

        listaDeAlunos.setAdapter(adapter);

        configuraListenerDeClickPorItemDaLista();//click para editar o aluno...

        configuraFabNovoAluno();

        registerForContextMenu(listaDeAlunos);

    }


    private void configuraListenerDeClickPorItemDaLista() {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);

                Intent vaiParaFormularioAluno = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);

                vaiParaFormularioAluno.putExtra(CHAVE_ALUNO, alunoSelecionado);
                startActivity(vaiParaFormularioAluno);
            }
        });
    }

    private void configuraFabNovoAluno() {

        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);

        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
            }
        });
    }
}