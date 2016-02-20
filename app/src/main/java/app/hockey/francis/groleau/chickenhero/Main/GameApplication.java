package app.hockey.francis.groleau.chickenhero.Main;

import android.app.Application;

/**
 * Created by francis on 2016-02-13.
 */
public class GameApplication extends Application {

    public GameApplication(){ }

    @Override
    public void onCreate()
    {
        super.onCreate();
        //Initialization of the AppConstants class
        AppConstants.Initialization(this.getApplicationContext());

    }
}
