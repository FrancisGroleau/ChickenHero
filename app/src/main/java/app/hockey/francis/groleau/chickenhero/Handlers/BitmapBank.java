package app.hockey.francis.groleau.chickenhero.Handlers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import app.hockey.francis.groleau.chickenhero.R;

/**
 * Created by francis on 2016-02-13.
 */
public class BitmapBank {

    Bitmap _pok;
    Bitmap _base;
    Bitmap _canon;
    Bitmap _cannonball;
    Bitmap _chickenSprite;
    Bitmap _explosionSprite;
    Bitmap _nuggets;
    Bitmap _thigh;
    Bitmap _whole;
    Bitmap _cloud1;
    Bitmap _cloud2;
    Bitmap _cloud3;

    /**
     * Loads bitmaps from the resources
     * @param res
     * 		resources reference
     * */
    public BitmapBank(Resources res)
    {
        _pok = BitmapFactory.decodeResource(res, R.drawable.pok);
        _base = BitmapFactory.decodeResource(res, R.drawable.base);
        _canon = BitmapFactory.decodeResource(res, R.drawable.canon);
        _cannonball = BitmapFactory.decodeResource(res, R.drawable.canonball);
        _chickenSprite = BitmapFactory.decodeResource(res, R.drawable.chicken_sprite_transparent);
        _explosionSprite = BitmapFactory.decodeResource(res, R.drawable.explosion_sprite);
        _nuggets = BitmapFactory.decodeResource(res, R.drawable.nuggets);
        _thigh = BitmapFactory.decodeResource(res, R.drawable.thigh1);
        _whole = BitmapFactory.decodeResource(res, R.drawable.whole);
        _cloud1 = BitmapFactory.decodeResource(res, R.drawable.cloud1);
        _cloud2 = BitmapFactory.decodeResource(res, R.drawable.cloud2);
        _cloud3 = BitmapFactory.decodeResource(res, R.drawable.cloud3);
    }

    public Bitmap get_chickenSprite() {
        return _chickenSprite;
    }

    public Bitmap get_pok() {
        return _pok;
    }

    public Bitmap get_base() {
        return _base;
    }

    public Bitmap get_canon() {
        return _canon;
    }

    public Bitmap get_cannonball() {
        return _cannonball;
    }

    public Bitmap get_explosionSprite() {
        return _explosionSprite;
    }

    public Bitmap get_nuggets() {
        return _nuggets;
    }

    public Bitmap get_thigh() {
        return _thigh;
    }

    public Bitmap get_whole() {
        return _whole;
    }

    public Bitmap get_cloud1() {
        return _cloud1;
    }

    public Bitmap get_cloud2() {
        return _cloud2;
    }

    public Bitmap get_cloud3() {
        return _cloud3;
    }

    /**
     * Rotates given bitmap according to passed angle, using Metrix object
     * @param source
     * 			Bitmap that needed to be rotated
     * @param angle
     * 			Rotation angle
     *
     * @return rotated bitmap
     * */
    public static Bitmap RotateBitmap(Bitmap source, float angle, int pPivotX, int pPivotY)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle, pPivotX, pPivotY);

        return Bitmap.createBitmap
                (
                        source,
                        0, 0,
                        source.getWidth(),
                        source.getHeight(),
                        matrix,
                        true
                );
    }

}
