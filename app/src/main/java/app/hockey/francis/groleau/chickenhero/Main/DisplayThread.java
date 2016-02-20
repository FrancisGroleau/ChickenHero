package app.hockey.francis.groleau.chickenhero.Main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * Created by francis on 2016-02-13.
 */
public class DisplayThread extends Thread {

    SurfaceHolder _surfaceHolder;
    Paint _backgroundPaint;

    //Delay amout between screen refreshes
    private final long DELAY = 4;
    private boolean _isOnRun;

    public DisplayThread(SurfaceHolder pSurfaceHolder, Context pContext){
        _surfaceHolder = pSurfaceHolder;

        _backgroundPaint = new Paint();
        _backgroundPaint.setARGB(255, 0, 0, 0);
        _isOnRun = true;
    }

    /**
     * This is the core shizzle
     * From here will be called all the method that are associated with the display in GameEngine object
     * */
    @Override
    public void run()
    {
        //Looping until the boolean is false
        while (_isOnRun)
        {


            //locking the canvas
            Canvas canvas = _surfaceHolder.lockCanvas(null);



            if (canvas != null)
            {
                //Updates the game objects buisiness logic
                AppConstants.GetEngine().Update(canvas);
                //Clears the screen with black paint and draws object on the canvas
                synchronized (_surfaceHolder)
                {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), _backgroundPaint);
                    AppConstants.GetEngine().Draw(canvas);
                }

                //unlocking the Canvas
                _surfaceHolder.unlockCanvasAndPost(canvas);
            }

            //delay time
            try
            {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException ex)
            {
                //TODO: Log
            }
        }
    }

    /**
     * @return whether the thread is running
     * */
    public boolean IsRunning()
    {
        return _isOnRun;
    }
    /**
     * Sets the thread state, false = stoped, true = running
     **/
    public void SetIsRunning(boolean state)
    {
        _isOnRun = state;
    }

}
