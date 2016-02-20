package app.hockey.francis.groleau.chickenhero.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by francis on 2016-02-13.
 */
public class Projectile {

    private Bitmap _bitmap;
    private float _x;
    private float _y;
    private float _vx;
    private float _vy;
    private float _width;
    private float _height;

    public float get_height() {
        return _height;
    }

    public void set_height(float _height) {
        this._height = _height;
    }

    public Bitmap get_bitmap() {
        return _bitmap;
    }

    public void set_bitmap(Bitmap _bitmap) {
        this._bitmap = _bitmap;
    }

    public float get_y() {
        return _y;
    }

    public void set_y(float _y) {
        this._y = _y;
    }

    public float get_x() {
        return _x;
    }

    public void set_x(float _x) {
        this._x = _x;
    }

    public float get_vx() {
        return _vx;
    }

    public void set_vx(float _vx) {
        this._vx = _vx;
    }

    public float get_vy() {
        return _vy;
    }

    public void set_vy(float _vy) {
        this._vy = _vy;
    }

    public float get_width() {
        return _width;
    }

    public void set_width(float _width) {
        this._width = _width;
    }




    public Projectile(Bitmap pBitmap, float pX,  float pY, float pVx, float pVy) {

        Bitmap temp = Bitmap.createScaledBitmap(pBitmap, 29, 28, false);

        this._bitmap = temp;
        this._x = pX;
        this._y = pY;
        this._vx = pVx;
        this._vy = pVy;

        _width = 29;
        _height = 28;
    }


    public void Update()
    {
        _x = _x + _vx;
        _y = _y + _vy;

    }

    public void Draw(Canvas pCanvas)
    {
        pCanvas.drawBitmap(_bitmap, _x, _y, null);
    }
}
