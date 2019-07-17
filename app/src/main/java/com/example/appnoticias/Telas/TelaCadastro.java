package com.example.appnoticias.Telas;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appnoticias.Componentes.Botao;
import com.example.appnoticias.Componentes.Input;


public class TelaCadastro extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TITULO DA PAGINA

        TextView titulo = new TextView(this);
        titulo.setPadding(0,80,0,0);
        titulo.setText("CADASTRO\n\n");
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
        setContentView(linearLayout);

//        MUDANDO TITULO DA TELA

        setTitle("Cadastro de Usuário");


//      INPUTS DA PAGINA

        Input nome = new Input(this,"Nome", InputType.TYPE_CLASS_TEXT, 500);
        layoutInputs.addView(nome);

        Input email = new Input(this,"Email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, 500);
        layoutInputs.addView(email);

        Input senha = new Input(this,"Senha", InputType.TYPE_TEXT_VARIATION_PASSWORD, 500);
        layoutInputs.addView(senha);



//        SETANDO BOTOES

        Botao botaoCadastro = new Botao(this, "Cadastrar");
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RAULT","FOI CADASTRADO");
            }
        });
        layoutBotoes.addView(botaoCadastro);

        Botao botaoCancelar = new Botao(this, "Cancelar");
        botaoCadastro.setStateListAnimator(null);
        botaoCadastro.setElevation(200);
        botaoCadastro.setTranslationZ(200);
        botaoCancelar.setOnClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RAULT","FOI CANCELADO");
                Intent mudarTelaCadastro = new Intent(getApplicationContext(), TelaLogin.class);
                startActivity(mudarTelaCadastro);
            }
        });
        layoutBotoes.addView(botaoCancelar);

//        CRIANDO GRADIENT

//        int[] colors = new int[2];
//        colors[0] = Color.rgb(255,0,0);
//        colors[1] = Color.rgb(255,255,255);
//
//
//        GradientDrawable gd = new GradientDrawable(
//                GradientDrawable.Orientation.BOTTOM_TOP, colors);
//        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
//        linearLayout.setBackground(gd);


//        SETANTO TODAS AS VIEWS TANTO DOS INPUTS QUANTO DOS BOTOES

        linearLayout.addView(titulo);
        linearLayout.addView(layoutInputs);
        linearLayout.addView(layoutBotoes);



    }
}
