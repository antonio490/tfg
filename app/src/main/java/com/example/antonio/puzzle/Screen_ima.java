package com.example.antonio.puzzle;

/**
 * Created by antonio on 7/30/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;




public class Screen_ima extends View {

    private static final String TAG = "Screen_4";
    private Button botonIni;
    public Canvas canvas;
    // Activity de la clase Play
    private Activity newActivity = null;
    public Splash_Screen finish;
    /**
     * Main bitmap
     */
    private Bitmap ping_1 = null, ping_sp = null, ping_2 = null, ping2_sp = null, ping_3 = null, ping3_sp = null, ping_4 = null, ping4_sp = null, ping_5 = null, ping5_sp = null, ping_6 = null, ping6_sp = null;
    private Bitmap home_Bitmap = null, next_Bitmap = null, save_Bitmap= null, speak_Bitmap = null;


    private BitArea x1, x2, x3, x4, x5, x6;
    private int valor = 1;
    private int N1 = 0, N2 = 0, N3 = 0;
    private boolean flag_save = false, flag_pintar = false;
    long endTime= 0, initialTime = 0, totalTime = 0;
    private long tiempo1 = 0, tiempo2 = 0;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0, check5 = 0, check6 = 0;
    int fail = 0, color;
    VelocityTracker tracker = null;
    static float MaxVelocity_x = 0;
    static float MaxVelocity_y = 0;
    Mostrar_nivel mostrar = new Mostrar_nivel();
    Level registros = new Level();


    AudioRecord speak = new AudioRecord();


    public Screen_ima(Context context, Activity activity) {
        super(context);
        newActivity = activity;

        init(context);

    }

    public Screen_ima(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public Screen_ima(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }


    // Guardamos atributos de la clase circulo.
    public static class BitArea {
        int leftX;
        int leftY;
        int num;

        BitArea(int centerX, int centerY, int num) {
            this.leftX = centerX;
            this.leftY = centerY;
            this.num = num;
        }
    }

    // Guardamos atributos de la clase cuadrado.
    public static class SquareArea {
        int Swidth;
        int Sheight;
        int leftY;
        int leftX;

        SquareArea(int Swidth, int Sheight, int leftX, int leftY) {
            this.Swidth = Swidth;
            this.Sheight = Sheight;
            this.leftX = leftX;
            this.leftY = leftY;

        }
    }


    private Paint mCirclePaint;
    private Paint Circle_stroke;
    private Paint mSquarePaint;
    private Paint Square_stroke;
    private Paint mRectPaint;
    private Paint Rect_stroke;
    private Paint mFondoPaint;

    private static final int SHAPES_LIMIT = 4;

    private HashSet<BitArea> mBit = new HashSet<BitArea>(SHAPES_LIMIT);
    private SparseArray<BitArea> mBitPointer = new SparseArray<BitArea>(SHAPES_LIMIT);

    private HashSet<SquareArea> mSquare = new HashSet<SquareArea>(SHAPES_LIMIT);
    private SparseArray<SquareArea> mSquarePointer = new SparseArray<SquareArea>(SHAPES_LIMIT);


    private void init(final Context context) {

        next_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.check);
        home_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.home);
        speak_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.speaker);
        save_Bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.register);

        ping_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_1);
        ping_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_sp);
        ping_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_2);
        ping2_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping2_sp);
        ping_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_3);
        ping3_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping3_sp);
        ping_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_4);
        ping4_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping4_sp);
        ping_5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_5);
        ping5_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping5_sp);
        ping_6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping_6);
        ping6_sp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ping6_sp);


        mSquarePaint = new Paint();
        mSquarePaint.setColor(Color.GREEN);
        mSquarePaint.setStyle(Paint.Style.FILL);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRectPaint = new Paint();
        mRectPaint.setColor(Color.RED);
        mRectPaint.setStyle(Paint.Style.FILL);


        mFondoPaint = new Paint();
        mFondoPaint.setColor(Color.WHITE);
        mFondoPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(final Canvas canv) {


        color = registros.getFondo();

        switch(color){
            case 1: setBackgroundColor(0xfffabfd0); break; //rosa

            case 2: setBackgroundColor(0xffbce29a); break;  //verde

            case 3: setBackgroundColor(0xffbfd3fa); break;  //azul

            case 4: setBackgroundColor(0xffffffff); break;  //blanco

            case 5: setBackgroundResource(R.drawable.madera_1); break;

            default: setBackgroundResource(R.drawable.madera_1); break;
        }

        Square_stroke = new Paint();
        Square_stroke.setStyle(Paint.Style.STROKE);
        Square_stroke.setStrokeWidth(5);
        Square_stroke.setColor(Color.RED);


        //imagen boton de checkeo
        if(flag_pintar == false)
            canv.drawBitmap(save_Bitmap, 1060, 20, null);

        canv.drawBitmap(next_Bitmap, 1160, 20, null);
        canv.drawBitmap(speak_Bitmap, 120, 20, null);
        canv.drawBitmap(home_Bitmap, 20, 20, null);

        //canv.drawBitmap(next_Bitmap, 1140, 600, null);
        //canv.drawBitmap(home_Bitmap, 70, 600, null);

        //silueta de las imagenes
        canv.drawBitmap(ping_sp, 100, 200, null);
        canv.drawBitmap(ping2_sp, 300, 200, null);
        canv.drawBitmap(ping3_sp, 500, 200, null);
        canv.drawBitmap(ping4_sp, 100, 500, null);
        canv.drawBitmap(ping5_sp, 300, 500, null);
        canv.drawBitmap(ping6_sp, 500, 500, null);



        // Posición inicial de figuras dinámicas

        x1 = obtainTouchedBit(700, 500);
        x2 = obtainTouchedBit(750, 450);
        x3 = obtainTouchedBit(1050, 500);
        x4 = obtainTouchedBit(900, 450);
        x5 = obtainTouchedBit(850, 200);
        x6 = obtainTouchedBit(1000, 200);




        for (BitArea imagen : mBit) {
            if (imagen.num == 1)
                canv.drawBitmap(ping_1, imagen.leftX, imagen.leftY, null);
            else if (imagen.num == 2)
                canv.drawBitmap(ping_2, imagen.leftX, imagen.leftY, null);
            else if (imagen.num == 3)
                canv.drawBitmap(ping_3, imagen.leftX, imagen.leftY, null);
            else if (imagen.num == 4)
                canv.drawBitmap(ping_4, imagen.leftX, imagen.leftY, null);
            else if (imagen.num == 5)
                canv.drawBitmap(ping_5, imagen.leftX, imagen.leftY, null);
            else
                canv.drawBitmap(ping_6, imagen.leftX, imagen.leftY, null);


        }

    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        boolean handled = false;

        try {

            //CircleArea touchedCircle;
            BitArea touchedBit;

            int xTouch;
            int yTouch;
            int pointerId;
            int actionIndex = event.getActionIndex();


            // get touch event coordinates and make transparent circle from it
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:

                    //Tiempo total por puzzle
                    if (flag_save) {
                        if (tiempo1 == 0) {
                            tiempo1 = System.currentTimeMillis();
                        }
                        Log.w(TAG, "tiempo parcial= " + totalTime);
                    }


                    //tiempo entre pulsaciones del usuario.
                    if (initialTime == 0) {
                        initialTime = System.currentTimeMillis();
                    } else {
                        endTime = System.currentTimeMillis();
                        long diff = endTime - initialTime;
                        //registro.setTime(diff);
                        if (flag_save)
                            registros.setTime(diff); //Clase Level

                        initialTime = endTime;
                        Log.i("Screen_1", "Time between clicks: " + diff);
                    }

                    ////////////////////////////////////////

                    if (tracker == null) {
                        tracker = VelocityTracker.obtain();
                    } else {
                        tracker.clear();
                    }

                    tracker.addMovement(event); //track ACTION
                    MaxVelocity_y = 0;
                    MaxVelocity_x = 0;


                    xTouch = (int) event.getX(0);
                    yTouch = (int) event.getY(0);


                    // check if we've touched inside some Circle
                    touchedBit = obtainTouchedBit(xTouch, yTouch);
                    touchedBit.leftX = xTouch;
                    touchedBit.leftY = yTouch;

                    mBitPointer.put(event.getPointerId(0), touchedBit);


                    invalidate();
                    handled = true;
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    //handled = true;
                    break;


                case MotionEvent.ACTION_MOVE:

                    tracker.addMovement(event); //track ACTION
                    tracker.computeCurrentVelocity(1000);
                    float x_vel = tracker.getXVelocity();
                    float y_vel = tracker.getYVelocity();

                    if (x_vel > 0.05f) {
                        MaxVelocity_x = x_vel;
                        if (flag_save)
                            registros.setVelx(MaxVelocity_x); //Clase Level
                        Log.w(TAG, "velocidad x = " + MaxVelocity_x);
                    }

                    if (y_vel > 0.05f) {
                        MaxVelocity_y = y_vel;
                        if (flag_save)
                            registros.setVely(MaxVelocity_y);   //Clase Level;
                        Log.w(TAG, "velocidad y = " + MaxVelocity_y);

                    }

                    Log.w(TAG, "Move");

                    jerk_function(x_vel, y_vel);
                    pointerId = event.getPointerId(actionIndex);

                    xTouch = (int) event.getX(actionIndex);
                    yTouch = (int) event.getY(actionIndex);

                    touchedBit = mBitPointer.get(pointerId);


                    if (null != touchedBit) {
                        touchedBit.leftX = xTouch;
                        touchedBit.leftY = yTouch;

                    }

                    if (touchedBit.num == 1) {
                        //comprobamos que el circulo pulsado se situa en la posicion correcta.
                        if ((touchedBit.leftX > 90) && (touchedBit.leftX < 110) && (touchedBit.leftY > 190) && (touchedBit.leftY < 210)) {
                            Log.w(TAG, "circulo 1");
                            check1 = 1;
                            touchedBit.leftX = 100;
                            touchedBit.leftY = 200;

                        }
                    }

                    if (touchedBit.num == 2) {

                        if ((touchedBit.leftX > 290) && (touchedBit.leftX < 310) && (touchedBit.leftY > 190) && (touchedBit.leftY < 210)) {
                            Log.w(TAG, "circulo 2");
                            check2 = 1;
                            touchedBit.leftX = 300;
                            touchedBit.leftY = 200;

                        }
                    }

                    if (touchedBit.num == 3) {

                        if ((touchedBit.leftX > 490) && (touchedBit.leftX < 510) && (touchedBit.leftY > 190) && (touchedBit.leftY < 210)) {
                            Log.w(TAG, "circulo 3");
                            check3 = 1;
                            touchedBit.leftX = 500;
                            touchedBit.leftY = 200;

                        }
                    }

                    if (touchedBit.num == 4) {

                        if ((touchedBit.leftX > 90) && (touchedBit.leftX < 110) && (touchedBit.leftY > 490) && (touchedBit.leftY < 510)) {
                            Log.w(TAG, "circulo 4");
                            check4 = 1;
                            touchedBit.leftX = 100;
                            touchedBit.leftY = 500;

                        }
                    }

                    if (touchedBit.num == 5) {

                        if ((touchedBit.leftX > 290) && (touchedBit.leftX < 310) && (touchedBit.leftY > 490) && (touchedBit.leftY < 510)) {
                            Log.w(TAG, "circulo 5");
                            check5 = 1;
                            touchedBit.leftX = 300;
                            touchedBit.leftY = 500;

                        }
                    }

                    if (touchedBit.num == 6) {

                        if ((touchedBit.leftX > 490) && (touchedBit.leftX < 510) && (touchedBit.leftY > 490) && (touchedBit.leftY < 510)) {
                            Log.w(TAG, "circulo 6");
                            check6 = 1;
                            touchedBit.leftX = 500;
                            touchedBit.leftY = 500;

                        }
                    }


                    //}
                    invalidate();
                    handled = true;
                    break;

                case MotionEvent.ACTION_UP:
                    xTouch = (int) event.getX(0);
                    yTouch = (int) event.getY(0);

                    if ((xTouch > 1140) && (xTouch < 1220) && (yTouch > 1) && (yTouch < 60)) {
                        Log.w(TAG, "PULSADO NEXT");
                        String num1 = Integer.toString(check1);
                        String num2 = Integer.toString(check2);
                        String num3 = Integer.toString(check3);
                        String num4 = Integer.toString(check4);
                        String num5 = Integer.toString(check5);
                        String num6 = Integer.toString(check6);
                        Log.w(num1, "valor check1");
                        Log.w(num2, "valor check2");
                        Log.w(num3, "valor check3");
                        Log.w(num4, "valor check4");
                        Log.w(num5, "valor check5");
                        Log.w(num6, "valor check6");


                        if ((check1 == 1) && (check2 == 1) && (check3 == 1) && (check4 == 1) && (check5 == 1) && (check6 == 1)) {

                            Log.w(TAG, "funcionando");
                            tiempo2 = System.currentTimeMillis();
                            tiempo2 = tiempo2 - tiempo1;
                            registros.setTiempo(tiempo2);

                            mostrar.set_fallos(fail);
                            mostrar.set_nivel(3);
                            registros.setPuzzle(7);
                            registros.setJerkData(N1, N2, N3);


                            Intent intent = new Intent(getContext(), Level.class);
                            newActivity.startActivity(intent);

                        } else {
                            fail = fail + 1;  //aumentamos la variable fail en caso de no acertar puzzle.
                            String fallo = Integer.toString(fail);
                            Log.w(fallo, "numero de fallos");
                            check1 = 0;
                            check2 = 0;
                            check3 = 0;
                            check4 = 0;
                            check5 = 0;
                            check6 = 0;
                            x1 = obtainTouchedBit(700, 100);
                            x2 = obtainTouchedBit(750, 450);
                            x3 = obtainTouchedBit(1050, 300);
                            x4 = obtainTouchedBit(900, 450);
                            x5 = obtainTouchedBit(850, 100);
                            x6 = obtainTouchedBit(1000, 100);
                            invalidate();
                        }

                    } else if ((xTouch > 1) && (xTouch < 80) && (yTouch > 1) && (yTouch < 80)) {
                        Log.w(TAG, "PULSADO PAUSE");


                        newActivity.setContentView(R.layout.activity_main);
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        newActivity.startActivity(intent);


                    } else if ((xTouch > 110) && (xTouch < 180) && (yTouch > 1) && (yTouch < 80)) {
                        Log.w(TAG, "Audio Record");

                        speak.startPlaying();

                    } else if ((xTouch > 1040) && (xTouch < 1120) && (yTouch > 1) && (yTouch < 80)) {
                        Log.w(TAG, "Guardar variables");
                        registros.setFlagSave(true);
                        flag_pintar = true;
                        flag_save = true;

                    }

                    invalidate();
                    handled = true;
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    //handled = true;
                    break;


                case MotionEvent.ACTION_CANCEL:
                    handled = true;
                    break;

                default:
                    // do nothing
                    break;
            }
        }catch(NullPointerException e){e.printStackTrace();}

        return super.onTouchEvent(event) || handled;
    }


    /**
     * Search and creates new (if needed) circle based on touch area
     *
     * @param xTouch int x of touch
     * @param yTouch int y of touch
     * @return obtained {@link BitArea}
     */
    private BitArea obtainTouchedBit(final int xTouch, final int yTouch) {
       BitArea touchedBit = getTouchedBit(xTouch, yTouch);


        if (null == touchedBit) {

            switch (valor) {

                case 1:
                    touchedBit = new BitArea(xTouch, yTouch, 1);
                    valor = 2;
                    break;

                case 2:
                    touchedBit = new BitArea(xTouch, yTouch, 2);
                    valor = 3;
                    break;

                case 3:
                    touchedBit = new BitArea(xTouch, yTouch, 3);
                    valor = 4;
                    break;

                case 4:
                    touchedBit = new BitArea(xTouch, yTouch, 4);
                    valor = 5;
                    break;

                case 5:
                    touchedBit = new BitArea(xTouch, yTouch, 5);
                    valor = 6;
                    break;

                case 6:
                    touchedBit = new BitArea(xTouch, yTouch, 6);
                    valor = 1;
                    break;


            }

            if (mBit.size() < 6) {
                Log.w(TAG, "Added imagen " + touchedBit);
                mBit.add(touchedBit);
            }
        }

        return touchedBit;
    }

    /**
     * Determines touched circle
     *
     * @param xTouch int x touch coordinate
     * @param yTouch int y touch coordinate
     * @return {@link BitArea} touched circle or null if no circle has been touched
     */
    private BitArea getTouchedBit(final int xTouch, final int yTouch) {
        BitArea touched = null;

        for (BitArea imagen : mBit) {
            if ((imagen.leftX  < xTouch) && ((imagen.leftX + 250) > xTouch) && (imagen.leftY < yTouch) && ((imagen.leftY + 300) > yTouch))  {
                touched = imagen;
                break;
            }
        }

        return touched;
    }

    private SquareArea obtainTouchedSquare(final int xTouch, final int yTouch) {
        SquareArea touchedSquare = getTouchedSquare(xTouch, yTouch);

        if (null == touchedSquare) {

            switch (valor) {

                case 1:
                    touchedSquare = new SquareArea(150, 150, xTouch, yTouch);
                    valor = 2;
                    break;

                case 2:
                    touchedSquare = new SquareArea(200, 200, xTouch, yTouch);
                    valor = 3;
                    break;

                case 3:
                    touchedSquare = new SquareArea(250, 250, xTouch, yTouch);
                    valor = 4;
                    break;

                case 4:
                    touchedSquare = new SquareArea(300, 300, xTouch, yTouch);
                    valor = 1;
                    break;


            }


            if (mSquare.size() < 4) {
                Log.w(TAG, "Added square " + touchedSquare);
                mSquare.add(touchedSquare);
            }
        }

        return touchedSquare;
    }

    private SquareArea getTouchedSquare(final int xTouch, final int yTouch) {
        SquareArea touched = null;

        for (SquareArea square : mSquare) {
            if ((((square.leftX + square.Swidth) > xTouch) && ((square.leftX) < xTouch)) && (((square.leftY + square.Sheight) > yTouch) && ((square.leftY) < yTouch))) {
                Log.w(TAG, "cuadrado tocado");

                touched = square;
                break;
            }
        }

        return touched;
    }


    private void jerk_function(float x_vel, float y_vel){

        if(Math.abs(x_vel) < 150.0f && Math.abs(y_vel) < 150.0f) {

            N1 += 1;
        }

        if(Math.abs(x_vel) > 160.0f && Math.abs(x_vel) < 400.0f && Math.abs(y_vel) > 160.0f && Math.abs(y_vel) < 400.0f) {

            N2 += 1;
        }

        if(Math.abs(x_vel) > 410.0f && Math.abs(x_vel) < 1400.0f && Math.abs(y_vel) > 410.0f && Math.abs(y_vel) < 1400.0f) {

            N3 += 1;
        }

    }
}