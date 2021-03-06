package com.example.antonio.puzzle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by antonio on 11/6/16.
 */
public class Select_Background extends AppCompatActivity implements View.OnClickListener{

    Button blanco, verde, azul, rosa, madera, back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_background);


        blanco = (Button) findViewById(R.id.button_blanco);
        rosa = (Button) findViewById(R.id.button_rosa);
        verde = (Button) findViewById(R.id.button_verde);
        azul = (Button) findViewById(R.id.button_azul);
        madera = (Button) findViewById(R.id.button_madera);
        back = (Button) findViewById(R.id.button_back);


        blanco.setOnClickListener(this);
        rosa.setOnClickListener(this);
        verde.setOnClickListener(this);
        azul.setOnClickListener(this);
        madera.setOnClickListener(this);
        back.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        final MediaPlayer click = MediaPlayer.create(this, R.raw.click);
        Level registro = new Level();

        switch (v.getId()) {
            case R.id.button_rosa:
                registro.setFondo(1);
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_verde:
                registro.setFondo(2);
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_azul:
                registro.setFondo(3);
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_blanco:
                registro.setFondo(4);
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_madera:
                registro.setFondo(5);
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_back:
                click.start();
                startActivity(new Intent(this, MainActivity.class));
                break;

            default:
                break;


        }
    }
}
