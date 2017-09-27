package edu.tecii.android.practica6radiobutnoti;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.*;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    RadioGroup gr;
    RadioButton ide;

    TextView res;

    Button btn;
    int numer=0;

    NotificationManager manager; //encargado de las prioridades, el que toma estas prioridades
    //Builder crea el objeto
    NotificationCompat.Builder noti1;

    Timer time;
    TimerTask tarea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gr = (RadioGroup)findViewById(R.id.radioGroup);

        res = (TextView)findViewById(R.id.textView2);

        btn = (Button)findViewById(R.id.button);
        //notificacion
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ide = (RadioButton) gr.findViewById(checkedId);
                //Codigo para asignar los segundos por cada radiobutton escogido
                switch (checkedId){

                        case R.id.radioButton5:{
                        numer =5000;
                        res.setText("Segundos: "+ numer/1000);

                        break;
                        }
                        case R.id.radioButton15:{
                        numer =15000;
                         res.setText("Segundos: "+ numer/1000);
                        break;
                        }
                        case R.id.radioButton30:{
                        numer=30000;
                         res.setText("Segundos: "+ numer/1000);
                        break;
                        }

                        case R.id.radioButton60:{
                        numer=60000;
                         res.setText("Segundos: "+ numer/1000);
                        break;
                        }


                }
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                time = new Timer();
                //Codigo para realizar el timer y la tarea, en el metodo run se pondra las actividades y estas se ejecutaran
                //hasta que se cumpla el tiempo establecido
                tarea = new TimerTask() {
                    @Override
                    public void run() {
                        showNoti();
                    }
                };
                time.schedule(tarea,numer);
                //if(time.equals(0)) {
                  //  time.cancel();
                    //time=null;
               // }

                }
        });



        }

    //Codigo para desplegar una notificacion con titulo, mensaje y una imagen
    public void showNoti(){
        noti1 = new NotificationCompat.Builder(getApplicationContext());
        noti1.setContentTitle("Notificacion del tiempo en segundos: " + (numer/1000));
        noti1.setContentText("Mensaje de notificacion");
        noti1.setSmallIcon(R.mipmap.ic_launcher);
        noti1.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        noti1.setTicker("texto 1");
        Intent mismaActivity = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent inte = PendingIntent.getActivity(getApplicationContext(), 1, mismaActivity, PendingIntent.FLAG_ONE_SHOT);
        noti1.setContentIntent(inte);
        manager.notify(numer+1, noti1.build());
    }
    }






