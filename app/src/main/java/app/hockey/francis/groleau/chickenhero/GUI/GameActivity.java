package app.hockey.francis.groleau.chickenhero.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.Window;

import app.hockey.francis.groleau.chickenhero.Main.AppConstants;
import app.hockey.francis.groleau.chickenhero.Views.GameView;
import app.hockey.francis.groleau.chickenhero.R;

public class GameActivity extends AppCompatActivity {

    private GameView _gameView;
    private boolean _hasTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main_screen);

        //sets the activity view as GameView class
        SurfaceView view = new GameView(this, AppConstants.GetEngine());
        setContentView(view);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);
        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
            {
                _hasTouch = true;
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                if(_hasTouch) {
                    OnActionUp(event);
                    _hasTouch = false;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                //maybe something here?
                break;
            }
            default:break;
        }
        return false;
    }


    /*activates on touch up event*/
    private void OnActionUp(MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();

     
            AppConstants.GetEngine().Shoot();

    }


}
