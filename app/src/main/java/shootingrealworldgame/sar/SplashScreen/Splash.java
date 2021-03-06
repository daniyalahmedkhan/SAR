package shootingrealworldgame.sar.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import java.util.Timer;
import java.util.TimerTask;

import shootingrealworldgame.sar.Activites.MainActivity;
import shootingrealworldgame.sar.R;
import shootingrealworldgame.sar.Utilis.Typewriter;
import tyrantgit.explosionfield.ExplosionField;

public class Splash extends Activity implements OnProgressBarListener {

    private Timer timer;

    private NumberProgressBar number_progress_bar;
    MediaPlayer mPlayer;


    Typewriter textView, sar;


    private ExplosionField mExplosionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        setContentView(R.layout.activity_splash);
        mExplosionField = ExplosionField.attach2Window(this);


        textView = (Typewriter)findViewById(R.id.typewriter);
        sar = (Typewriter)findViewById(R.id.sar);



        mPlayer  = MediaPlayer.create(Splash.this, R.raw.sa);
        mPlayer.start();


        number_progress_bar = (NumberProgressBar)findViewById(R.id.number_progress_bar);
        number_progress_bar.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        number_progress_bar.incrementProgressBy(1);
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    public void onProgressChange(int current, int max) {



        if (current == 12){


            sar.setCharacterDelay(250);
            sar.animateText("SAR");


            textView.setCharacterDelay(350);
            textView.animateText("Real World Game");

        }

        if(current == max) {
           timer.cancel();

            final Handler handler = new Handler();



            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    ExplodView(sar);
                    ExplodView(textView);

                }
            }, 1000);



            handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mPlayer.stop();
                Intent intent = new Intent(Splash.this , MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
    }


    private void ExplodView(View view){


        mExplosionField.explode(view);

    }
}
