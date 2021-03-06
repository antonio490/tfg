package com.example.antonio.puzzle;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by antonio on 8/7/16.
 */
public class Level extends AppCompatActivity implements View.OnClickListener {

    String TAG = "class level";
    TextView txt, txtfallos;
    ImageView imagen, imagen2, imagen3, imagen4, flash_stars, emoticono;
    Button next_level, salir;
    int valor = 0;
    public static float tiempo = 0;
    private static String username = "";
    private static String password = "";
    private static int puzzle = 0, N1 = 0, N2 = 0, N3 = 0;
    private static boolean flag = false;


    Mostrar_nivel mostrar = new Mostrar_nivel();


    private static float MaxVeloX = 0, MaxVeloY = 0;
    private static int colorFondo = 0;
    //private static float time = 0;

    public static List<Float> veloX = new ArrayList<Float>();
    public static List<Float> veloY = new ArrayList<Float>();
    public static List<Float> time = new ArrayList<Float>();


    public void setTime(float t){
        time.add(t);
    }

    public List<Float> getTime(){
        return time;
    }

    public void setTiempo(long t){
        tiempo = (float) t/1000;
    }

    public float getTiempo(){
        return tiempo;
    }

    //Registramos el color de select_background;

    public void setFondo(int var) { colorFondo = var;}

    public int getFondo() { return colorFondo;}

    public void setUsername(String name){
        username = name;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String pass){
        password = pass;
    }

    public String getPassword(){
        return password;
    }

    public void setPuzzle(int n) { puzzle = n; }

    public int getPuzzle() { return puzzle; }

    public void setJerkData(int n1, int n2, int n3) {

        N1 = n1;
        N2 = n2;
        N3 = n3;
    }

    public int getN1(){
        return N1;
    }

    public int getN2(){
        return N2;
    }

    public int getN3(){
        return N3;
    }

    public void setFlagSave(boolean value){
        flag = value;
        Log.w(TAG, "flag= " + flag);
    }

    public boolean getFlag(){
        return flag;
    }

    public String getDate(){
        Calendar date = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(date.getTime());
        return formattedDate;
    }

    public void borraData(){
        tiempo = 0;
        time.clear();
        veloX.clear();
        veloY.clear();
        N1 = 0;
        N2 = 0;
        N3 = 0;
        flag = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel);

        ////////////////////////////////////////////////////////////////////////////////////

        float maxVeloX, maxVeloY, timeMedio;
        Mostrar_nivel fallos = new Mostrar_nivel();
        int var = fallos.get_fallos();

        maxVeloX = calcMediaVelX();
        maxVeloY = calcMediaVelY();
        timeMedio = mediaTiempos();




        //calcular la media de la velocidad y , de la velocidad x y de las diferencias de tiempo;


        if(getFlag()) {

            LoginRequest loginRequest = new LoginRequest(getUsername(), getPassword(), responseListener);
            RequestQueue queue = Volley.newRequestQueue(Level.this);
            queue.add(loginRequest);

            RegisterData registerData = new RegisterData(getUsername(), getDate(), getPuzzle(), maxVeloX, maxVeloY, var, timeMedio, getTiempo(), getN1(), getN2(), getN3(), responseListener);
            queue = Volley.newRequestQueue(Level.this);
            queue.add(registerData);

        }

            borraData();




        //////////////////////////////////////////////////////////////////////////

        final MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);

        next_level = (Button) findViewById(R.id.button_next);
        salir = (Button) findViewById(R.id.button_exit);
        txt = (TextView) findViewById(R.id.texto1);
        txtfallos = (TextView) findViewById(R.id.text_fallos);
        imagen = (ImageView) findViewById(R.id.imageView4);
        imagen2 = (ImageView) findViewById(R.id.imageStar1);
        imagen3 = (ImageView) findViewById(R.id.imageStar2);
        imagen4 = (ImageView)findViewById(R.id.imageStar3);
        flash_stars = (ImageView)findViewById(R.id.imageStars);


        next_level.setOnClickListener(this);
        salir.setOnClickListener(this);

        int a = getPuzzle();

        int y = mostrar.get_nivel();
        int fail = mostrar.get_fallos();

        String err = Integer.toString(fail);



        switch(a){
            case 1:
                correct.start();
                txt.setText("Nivel 1");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen2);
                move(flash_stars);
                valor = 1;
                break;

            case 2:
                correct.start();
                txt.setText("Nivel 1");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen3);
                move(flash_stars);
                //slide(imagen);
                valor = 2;
                break;

            case 3:
                correct.start();
                txt.setText("Nivel 1");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                imagen4.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen4);
                move(flash_stars);
                valor = 3;
                break;

            case 4:
                correct.start();
                txt.setText("Nivel 2");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen2);
                move(flash_stars);
                valor = 4;
                break;

            case 5:
                correct.start();
                txt.setText("Nivel 2");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen3);
                move(flash_stars);
                //slide(imagen);
                valor = 5;
                break;

            case 6:
                correct.start();
                txt.setText("Nivel 2");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                imagen4.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen4);
                move(flash_stars);
                valor = 6;
                break;

            case 7:
                correct.start();
                txt.setText("Nivel 3");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen4);
                move(flash_stars);
                valor = 7;
                break;

            case 8:
                correct.start();
                txt.setText("Nivel 3");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen3);
                move(flash_stars);
                //slide(imagen);
                valor = 8;
                break;

            case 9:
                correct.start();
                txt.setText("Nivel 3");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                imagen4.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                zoom(imagen4);
                move(flash_stars);
                valor = 9;
                break;

            case 10:
                correct.start();
                txt.setText("FINAL");
                txtfallos.setText("Número de fallos: " + err);
                imagen2.setImageResource(R.drawable.yellow_star);
                imagen3.setImageResource(R.drawable.yellow_star);
                imagen4.setImageResource(R.drawable.yellow_star);
                flash_stars.setImageResource(R.drawable.stars);
                //zoom(imagen4);
                move(flash_stars);
                valor = 10;
                break;

            default:
                txt.setText("Nivel --");
                break;

        }


    }

    public void zoom(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        view.startAnimation(animation);
    }

    public void move(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        view.startAnimation(animation);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_next:
                if(valor == 1)
                    setContentView(new Screen_2(getApplicationContext(), Level.this));
                else if (valor == 2)
                    setContentView(new Screen_3(getApplicationContext(), Level.this));
                else if (valor == 3)
                    setContentView(new Screen_4(getApplicationContext(), Level.this));
                else if (valor == 4)
                    setContentView(new Screen_5(getApplicationContext(), Level.this));
                else if (valor == 5)
                    setContentView(new Screen_6(getApplicationContext(), Level.this));
                else if (valor == 6)
                    setContentView(new Screen_ima(getApplicationContext(), Level.this));
                else if (valor == 7)
                    setContentView(new Screen_ima2(getApplicationContext(), Level.this));
                else if (valor == 8)
                    setContentView(new Screen_ima3(getApplicationContext(), Level.this));
                else if (valor == 9)
                    setContentView(new Screen_ima4(getApplicationContext(), Level.this));
                else
                    startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.button_exit:
                showMessage();

            default:
                break;
        }
    }

    Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {
                    //click.start();// Ir a LoginActivity

                }
                else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void showMessage(){

        AlertDialog.Builder myMessage = new AlertDialog.Builder(this);

        myMessage.setTitle("¿Quieres terminar de jugar?")

                .setPositiveButton("Si", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        setContentView(R.layout.activity_main);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                })

                .setIcon(R.drawable.sad)
                .create()
                .show();
    }

    public void setVelx(float x){
        veloX.add(x);

    }

    public List<Float> getVelx(){
        return veloX;
    }

    public void setVely(float y){
        veloY.add(y);
    }

    public List<Float> getVely(){
        return veloY;
    }

    public float calcMediaVelX(){
        float media = 0;
        for(int i=0; i< getVelx().size(); i++)
            media += getVelx().get(i);

        media = media / getVelx().size();
        Log.w(TAG, "Media velocidad x = " + media);
        return media;
    }

    public float calcMediaVelY(){
        float media = 0;
        for(int i=0; i< getVely().size(); i++)
            media += getVely().get(i);

        media = media / getVely().size();
        Log.w(TAG, "Media velocidad y = " + media);
        return media;
    }

    public float mediaTiempos(){
        float media = 0;

        for(int i=0; i<getTime().size(); i++)
            media += getTime().get(i);

        Log.w(TAG, "Media tiempos = " + media);
        media = media / getTime().size();
        return media;
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }


}
