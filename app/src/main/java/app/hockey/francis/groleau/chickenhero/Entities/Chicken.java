package app.hockey.francis.groleau.chickenhero.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by francis on 2016-02-13.
 */
public class Chicken {

    private int _width;
    private int _height;
    private Bitmap _sprite;
    private int _velocityX;
    private int _velocityY;
    private int _x;
    private int _y;

    private Rect _sourceRect;    // the rectangle to be drawn from the animation bitmap
    private int _frameNumber;        // number of frames in animation
    private int _currentFrame;   // the current frame
    private long _frameTicker;   // the time of the last frame update
    private int _framePeriod;    // milliseconds between each frame (1000/fps)

    private double _amplitude;
    private double _frequency;

    public Chicken(Bitmap pSprite, int pFrameCount, int pVelocityX, int pVelocityY, int pX, int pY)
    {
        _sprite = pSprite;
        _width = _sprite.getWidth() / pFrameCount;
        _height = _sprite.getHeight();
        _velocityX = pVelocityX;
        _velocityY = pVelocityY;
        _x = pX;
        _y = pY;

        _currentFrame = 0;
        _frameNumber = pFrameCount;
        _sourceRect = new Rect(0, 0, _width, _height);
        _framePeriod = 1000 / 10;
        _frameTicker = 0l;


       _amplitude =  1 + (Math.random() * 200);

        double lower = 0.00025f;
        double upper = 0.00032f;
       _frequency = Math.random() * (upper - lower) + lower;


    }

    public int get_width() {
        return _width;
    }

    public void set_width(int _width) {
        this._width = _width;
    }

    public int get_height() {
        return _height;
    }

    public void set_height(int _height) {
        this._height = _height;
    }

    public Bitmap get_sprite() {
        return _sprite;
    }

    public void set_sprite(Bitmap _sprite) {
        this._sprite = _sprite;
    }

    public double get_velocityX() {
        return _velocityX;
    }

    public void set_velocityX(int _velocityX) {
        this._velocityX = _velocityX;
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_velocityY() {
        return _velocityY;
    }

    public void set_velocityY(int _velocityY) {
        this._velocityY = _velocityY;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }


    //chicken follow a sine wave pattern
    //http://stackoverflow.com/questions/8342887/how-do-you-move-an-object-in-a-wavy-pattern
    public void Update(Canvas pCanvas)
    {
        _x += _velocityX;


        //minHeigth + (amplitude * Math.sin(frequenxy * x))
        _y = 300 + (int)(_amplitude * Math.sin(Math.toDegrees(_frequency * _x)));


        if(_x > pCanvas.getWidth())
            _x = -_width + (-1 * (200 + (int)(Math.random() * 500)));

    }

    public void UpdateAnimation(long pGameTime) {
        if (pGameTime > _frameTicker + _framePeriod) {
            _frameTicker = pGameTime;
            // increment the frame
            _currentFrame++;
            if (_currentFrame >= _frameNumber) {
                _currentFrame = 0;
            }
        }
        // define the rectangle to cut out sprite
        _sourceRect.left = _currentFrame * _width;
        _sourceRect.right = _sourceRect.left + _width;
    }



    public void Draw(Canvas pCanvas)
    {
        Rect destRect = new Rect(_x, _y, _x + _width, _y + _height);
        pCanvas.drawBitmap(_sprite, _sourceRect, destRect, null);
    }


    public void Kill()
    {
        _x = -_width;
    }


}
