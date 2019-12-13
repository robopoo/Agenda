package alura.com.br.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import alura.com.br.R;
import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

import static alura.com.br.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class FormularioAlunoActivity extends AppCompatActivity {


    public static final String TITULO_APP_BAR = "Novo Aluno";
    public static final String TITULO_APP_BAR_NOVO_ALUNO = TITULO_APP_BAR;
    private static final String TITULO_APP_BAR_EDITA_ALUNO = "Edita Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno alunoSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        carregaAluno();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if(itemId == R.id.activity_formulario_aluno_menu_salvar){

        }
        finalizaFormulario();

        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        if(getIntent().hasExtra(CHAVE_ALUNO)) {

            setTitle(TITULO_APP_BAR_EDITA_ALUNO);

            alunoSelecionado = getIntent().getExtras().getParcelable(CHAVE_ALUNO);

            campoNome.setText(alunoSelecionado.getNome());
            campoTelefone.setText(alunoSelecionado.getTelefone());
            campoEmail.setText(alunoSelecionado.getEmail());
        }
        else {
            setTitle(TITULO_APP_BAR_NOVO_ALUNO);
            alunoSelecionado = new Aluno();
        }
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }


    private void preencheDadosAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        alunoSelecionado.setNome(nome);
        alunoSelecionado.setTelefone(telefone);
        alunoSelecionado.setEmail(email);
    }

    private void finalizaFormulario() {

        preencheDadosAluno();

        if(!alunoSelecionado.isNovo()) {
            alunoDAO.atualizar(alunoSelecionado);
        } else {
            alunoDAO.salvar(alunoSelecionado);
        }
        finish();
    }

}