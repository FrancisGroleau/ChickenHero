package app.hockey.francis.groleau.chickenhero.Main;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.hockey.francis.groleau.chickenhero.Entities.Canon;
import app.hockey.francis.groleau.chickenhero.Entities.Chicken;
import app.hockey.francis.groleau.chickenhero.Entities.Projectile;
import app.hockey.francis.groleau.chickenhero.Main.AppConstants;

/**
 * Created by francis on 2016-02-13.
 */
//https://github.com/Pavel-Durov/CodeProject-Android-Basic-Game-Loop/blob/master/src/net/simplydone/objects/DisplayThread.java
public class GameEngine {

    static final Object _sync = new Object();
    private List<Chicken>_chickens;
    private Canon _canon;
    private long _xp = 0;
    private long _nuggetCount = 0;
    private long _thighCount = 0;
    private long _wholeCount = 0;


    public GameEngine()
    {
        InitAssets();
    }

    private void InitAssets()
    {
        InitChickens();
        InitCanon();
    }

    private void InitChickens()
    {
        _chickens = new ArrayList<Chicken>();
        Random r = new Random();
        for (int i = 0; i < 10; i++)
        {
            int startX = -1 *(1 +  (int)(Math.random() * 600));
            int startY = 1;
            int vx = 2 + (int)(Math.random() * 3);
            int vy = 1;
            Chicken c = new Chicken(AppConstants.GetBitmapsBank().get_chickenSprite(),8,vx,vy,startX,startY);
            _chickens.add(c);
        }
    }

    private void InitCanon()
    {
        Bitmap canonBitmap = AppConstants.GetBitmapsBank().get_canon();
        Bitmap baseBitmap = AppConstants.GetBitmapsBank().get_base();
        int x = (AppConstants.SCREEN_WIDTH / 2) - canonBitmap.getWidth() / 2;
        int y = AppConstants.SCREEN_HEIGHT - (canonBitmap.getHeight() + 300);


        _canon = new Canon(x,y,0.3f,canonBitmap,baseBitmap);
    }


    public void Update(Canvas pCanvas)
    {
        long time = System.currentTimeMillis();

        UpdateChickens(pCanvas);
        _canon.Update(time);
        CheckForDeadChickens(time);
    }


    private void UpdateChickens(Canvas pCanvas)
    {
        for (Chicken c : _chickens)
        {
            c.Update(pCanvas);
        }
    }

    private void CheckForDeadChickens(long pGameTime)
    {
        List<Projectile> projectilesToRemove = new ArrayList<Projectile>();

        for(Projectile p : _canon.get_projectiles()) {
            for (Chicken c : _chickens) {
                if(((p.get_x() > c.get_x()) && (p.get_x() < (c.get_x() + c.get_width()))) &&
                    (p.get_y() > c.get_y()) && (p.get_y() < (c.get_y() + c.get_height())))
                {
                    c.Kill(pGameTime);
                    projectilesToRemove.add(p);
                }
            }
        }

        _canon.RemoveProjectile(projectilesToRemove);
    }

    public void Draw(Canvas pCanvas)
    {

        long time = System.currentTimeMillis();
        DrawChicken(pCanvas, time);

        //since the canon can shoot at any moment we must protect it from
        //being modified while we paint it
        synchronized (_sync) {
            DrawCanon(pCanvas);
        }
    }

    private void DrawChicken(Canvas pCanvas, long pTime)
    {
        for (Chicken c : _chickens)
        {
            c.UpdateAnimation(pTime);
            c.Draw(pCanvas, System.currentTimeMillis());
        }
    }

    private void DrawCanon(Canvas pCanvas)
    {
            _canon.Draw(pCanvas);
    }


    public void Shoot()
    {
        long time = System.currentTimeMillis();
        _canon.Shoot(time);
    }

}
