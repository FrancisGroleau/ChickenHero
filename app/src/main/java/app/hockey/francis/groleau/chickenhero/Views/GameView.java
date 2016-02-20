package app.hockey.francis.groleau.chickenhero.Views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import app.hockey.francis.groleau.chickenhero.Main.GameEngine;
import app.hockey.francis.groleau.chickenhero.Main.AppConstants;
import app.hockey.francis.groleau.chickenhero.Main.DisplayThread;

/**
 * Created by francis on 2016-02-13.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder _surfaceHolder;
    Context _context;
    private DisplayThread _displayThread;

    public GameView(Context pContext, GameEngine pEngine)
    {
        super(pContext);

        _context = pContext;
        InitView();
    }

    private void InitView()
    {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        _displayThread = new DisplayThread(holder, _context);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Starts the display thread
        if(!_displayThread.IsRunning())
        {
            _displayThread = new DisplayThread(getHolder(), _context);
            _displayThread.start();
        }
        else
        {
            _displayThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        _displayThread.SetIsRunning(false);
        AppConstants.StopThread(_displayThread);
    }
}
