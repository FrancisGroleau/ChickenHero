package app.hockey.francis.groleau.chickenhero.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import app.hockey.francis.groleau.chickenhero.Main.AppConstants;

/**
 * Created by francis on 2016-02-13.
 */
public class Canon {

    private float _width;
    private float _height;
    private float _x;
    private float _y;
    private float _force;
    private Bitmap _bitmapCanon;
    private Bitmap _bitmapBase;
    private float _rotation;
    private float _rotationSpeed;
    private int _currentDirection;
    private boolean _isAutoFiring;

    private List<Projectile> _projectiles;

    private float _rateOfFire;
    private long _lastShotTime;   // the time of the last shot fired


    public Canon(float pX, float pY, float pForce, Bitmap pBitmapCanon, Bitmap pBitmapBase) {
        this._width = pBitmapCanon.getWidth();
        this._height = pBitmapCanon.getHeight();
        this._x = pX;
        this._y = pY;
        this._force = pForce;
        this._bitmapCanon = pBitmapCanon;
        this._bitmapBase = pBitmapBase;
        this._rotation = 0;
        this._rotationSpeed = 0.5f;
        this._currentDirection = 1;
        this._rateOfFire = 10;
        this._lastShotTime = 0;
        this._isAutoFiring = false;
        _projectiles = new ArrayList<Projectile>();
    }

    public void setRotationSpeed(float pRotationSpeed)
    {
        this._rotationSpeed = pRotationSpeed;
    }

    public void SetRateOfFire(float pRateOfFire)
    {
        this._rateOfFire = pRateOfFire;
    }

    public void SetAutoFire(boolean pAutoFire)
    {
        this._isAutoFiring = pAutoFire;
    }

    public void Draw(Canvas pCanvas)
    {
        DrawProjectiles(pCanvas);

        int pivotX = (int)_x;
        int pivotY = (int)(_y + (_height * 2));

        Bitmap canon = AppConstants.GetBitmapsBank().RotateBitmap(_bitmapCanon, _rotation, pivotX,pivotY);
        pCanvas.drawBitmap(canon,_x,_y + _height, null);
        pCanvas.drawBitmap(_bitmapBase, _x - ((_width / 2) - 10), _y + 70 + _bitmapBase.getHeight(), null);


    }

    private void DrawProjectiles(Canvas pCanvas)
    {

        List<Projectile> projectilesToDraw = new ArrayList<Projectile>(_projectiles);

        for(Projectile p : projectilesToDraw)
        {
            p.Draw(pCanvas);
        }
    }

    public void Update(long pTime)
    {
        if(_currentDirection == 1)
        {
            if(_rotation < 50)
                _rotation += _rotationSpeed;
            else
                _currentDirection = 0;
        }
        else
        {
            if(_rotation > -50)
                _rotation -= _rotationSpeed;
            else
                _currentDirection = 1;
        }

        List<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        for(Projectile p : _projectiles)
        {
            if(p.get_x() > AppConstants.SCREEN_WIDTH || p.get_x() < 0 ||
               p.get_y() > AppConstants.SCREEN_HEIGHT || p.get_y() < 0)
            {
                projectilesToRemove.add(p);
            }
        }
        RemoveProjectile(projectilesToRemove);

        for(Projectile p : _projectiles)
        {
            p.Update();
        }

        if(_isAutoFiring)
            Shoot(pTime);
    }

    public void Shoot(long pGameTime)
    {
        if (!_isAutoFiring || pGameTime > _lastShotTime + _rateOfFire) {

            if(_isAutoFiring)
                _lastShotTime = pGameTime;
            //okay we can shoot


            float d = (float)Math.toDegrees(Math.sin(Math.toRadians(_rotation)));
            float d2 = (float)Math.toDegrees(Math.cos(Math.toRadians(_rotation)));

            float _vx = (d * _force);
            float _vy = (d2 * _force * -1);

            float x = (_x + _width / 2);
            float y = (_y + (_height * 1.5f));

            Projectile p = new Projectile(AppConstants.GetBitmapsBank().get_cannonball(),x,y,_vx,_vy);
            _projectiles.add(p);

            //old decompile code from wallpaper
            //_canonballs.add(new Canonball(_canonball, _x - 10, _y, 29, 28, (int)(d * d1), (int)(d2 * d3) * -1));
            //d = _x;
            //d1 = Math.toDegrees(Math.sin(Math.toRadians(_angle)));
            //d2 = _force;
            //d3 = _y;
            //double d4 = Math.toDegrees(Math.cos(Math.toRadians(_angle)));
            //double d5 = _force;
            //_explosion = new CanonExplosion(_canonExplosion, (int)(d + d1 * d2), (int)(d3 - d4 * d5), 20, 4);



        }
    }

    public List<Projectile> get_projectiles()
    {
        return new ArrayList<Projectile>(_projectiles);
    }

    public void RemoveProjectile(List<Projectile> pProjectile)
    {
        for(Projectile p : pProjectile)
        {
            if(_projectiles.indexOf(p) > 0 && _projectiles.get(_projectiles.indexOf(p)) != null)
            {
                _projectiles.remove(_projectiles.get(_projectiles.indexOf(p)));
            }
        }
    }



}
