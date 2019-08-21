package com.example.appnoticias.Telas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appnoticias.Componentes.Botao;
import com.example.appnoticias.Componentes.Input;
import com.example.appnoticias.Database.UsuarioDao;
import com.example.appnoticias.Model.Usuario;
import com.example.appnoticias.R;
import com.example.appnoticias.rss.GetRss;

public class TelaEditar extends SideBar {
    private Input edtNome;
    private Input edtEmail;
    private Input edtSenha;
    private String nome;
    private String email;
    private String senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        TITULO DA PAGINA

        TextView titulo = new TextView(this);
        titulo.setPadding(0,80,0,0);
        titulo.setText("DADOS\n\n");
        titulo.setTextSize(25f);
        titulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        //        LAYOUT DOS BOTOES

        LinearLayout layoutBotoes= new LinearLayout(this);
        layoutBotoes.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layoutBotoes.setOrientation(LinearLayout.VERTICAL);
        layoutBotoes.setPadding(9,9,9,9);
        layoutBotoes.setGravity(Gravity.CENTER_HORIZONTAL);

//        LAYOUT DOS INPUTS
        LinearLayout layoutInputs= new LinearLayout(this);
        layoutInputs.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layoutInputs.setOrientation(LinearLayout.VERTICAL);
        layoutInputs.setPadding(9,5,9,9);
        layoutInputs.setGravity(Gravity.CENTER_HORIZONTAL);

//        LAYOUT DOS LAYOUTS

        LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(9,9,9,9);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        setDynamicContent(linearLayout);

//        MUDANDO TITULO DA PAGINA

        setTitle("Editar Informações");

//      INPUTS DA PAGINA

        edtNome = new Input(this,"Nome", InputType.TYPE_CLASS_TEXT, 500);
        layoutInputs.addView(edtNome);

        edtEmail = new Input(this,"Email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, 500);
        layoutInputs.addView(edtEmail);

        edtSenha = new Input(this,"Senha atual", InputType.TYPE_TEXT_VARIATION_PASSWORD, 500);
        layoutInputs.addView(edtSenha);





//        SETANDO BOTOES

        Botao botaoCadastro = new Botao(this, "Editar", Color.rgb(255,69,0));
        botaoCadastro.setColorTextButton();
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                UsuarioDao usuarioDao = new UsuarioDao(TelaEditar.this);
                System.out.println(usuarioDao.listar());
                SharedPreferences sharedPreferences = getSharedPreferences("authenticatedUser", MODE_PRIVATE);
                int idUsuario = sharedPreferences.getInt("codigo",usuario.getCodigo());
                usuario.setCodigo(idUsuario);
                usuario.setNome(edtNome.getValue());
                usuario.setEmail(edtEmail.getValue());
                usuario.setSenha(edtSenha.getValue());

                if(validaCampos()==true){

                    usuarioDao.alterar(usuario);
                    Toast.makeText(TelaEditar.this,"Usuário Editado com sucesso!",Toast.LENGTH_SHORT).show();
                    System.out.println(usuarioDao.listar());
                    Log.d("RAULT","FOI EDITADO");
                    finish();
                    Intent mudarTelaCadastro = new Intent(getApplicationContext(), TelaLogin.class);
                    startActivity(mudarTelaCadastro);

                    }else {
                        Toast.makeText(TelaEditar.this,"",Toast.LENGTH_SHORT).show();

                        }

                }

            });
        layoutBotoes.addView(botaoCadastro);


        Botao botaoCancelar = new Botao(this, "Cancelar", Color.rgb(255,69,0));
        botaoCancelar.setColorTextButton();
        botaoCancelar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RAULT","FOI CANCELADO");
                Intent mudarTelaCadastro = new Intent(getApplicationContext(), GetRss.class);
                startActivity(mudarTelaCadastro);
            }
        });
        layoutBotoes.addView(botaoCancelar);



//        SETANTO TODAS AS VIEWS TANTO DOS INPUTS QUANTO DOS BOTOES

        linearLayout.addView(titulo);
        linearLayout.addView(layoutInputs);
        linearLayout.addView(layoutBotoes);
    }
    public boolean validaCampos(){
        boolean res = false;
        nome = edtNome.getValue();
        email = edtEmail.getValue();
        senha = edtSenha.getValue();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aviso");
        builder.setMessage("Há campos inválidos ou em branco!");
        builder.setNeutralButton("OK", null);

        if (res = isCampoVazio(nome)){
            edtNome.requestFocus();
            builder.show();
            return false;
        }else if (res = isCampoVazio(email)){
            edtEmail.requestFocus();
            builder.show();
            return false;
        }else if (res = !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.requestFocus();
            builder.show();
            return false;
        } else if (res = isCampoVazio(senha)||(senha.length()<4)){
            edtSenha.requestFocus();
            builder.show();
            return false;
        }

        else return true;
    }


    public boolean isCampoVazio(String valor) {

        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }

}
