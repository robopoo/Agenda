package alura.com.br.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


public class Aluno implements Parcelable {

    private int id = 0;
    private String nome;
    private String telefone;
    private String email;




    public Aluno() {

    }

    public Aluno(String nome, String telefone, String email) {

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;

    }



    public Aluno(Parcel parcel){
        nome = parcel.readString();
        telefone = parcel.readString();
        email = parcel.readString();
        id = parcel.readInt();

    }


    public static final Parcelable.Creator<Aluno>
            CREATOR = new Parcelable.Creator<Aluno>() {

        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(telefone);
        dest.writeString(email);
        dest.writeInt(id);
    }

    public boolean isNovo() {

        if( this.id == 0 ){
            return true;
        }
        else{
            return false;
        }
    }
}