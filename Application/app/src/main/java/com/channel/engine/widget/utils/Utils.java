package com.channel.engine.widget.utils;

import android.support.graphics.drawable.VectorDrawableCompat;
import android.annotation.TargetApi;
import android.os.Build;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.channel.application.R;
import com.channel.engine.graphics.drawable.Icon;
import com.channel.engine.graphics.drawable.IconType;

public class Utils {

	private static Map<String, Typeface> cachedFontMap = new HashMap<String, Typeface>();

	public static int pxToSp(final Context context, final float px) {
		return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
	}

	public static int spToPx(final Context context, final float sp) {
		return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
	}

	public static Typeface findFont(Context context, String fontPath, String defaultFontPath){

		if (fontPath == null){
			return Typeface.DEFAULT;
		}

		String fontName = new File(fontPath).getName();
		String defaultFontName = "";
		if (!TextUtils.isEmpty(defaultFontPath)){
			defaultFontName = new File(defaultFontPath).getName();
		}

		if (cachedFontMap.containsKey(fontName)){
			return cachedFontMap.get(fontName);
		}else{
			try{
				AssetManager assets = context.getResources().getAssets();

				if (Arrays.asList(assets.list("")).contains(fontPath)){
					Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
					cachedFontMap.put(fontName, typeface);
					return typeface;
				}else if (Arrays.asList(assets.list("fonts")).contains(fontName)){
					Typeface typeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s",fontName));
					cachedFontMap.put(fontName, typeface);
					return typeface;
				}else if (Arrays.asList(assets.list("iconfonts")).contains(fontName)){
					Typeface typeface = Typeface.createFromAsset(context.getAssets(), String.format("iconfonts/%s",fontName));
					cachedFontMap.put(fontName, typeface);
					return typeface;
				}else if (!TextUtils.isEmpty(defaultFontPath) && Arrays.asList(assets.list("")).contains(defaultFontPath)){
					Typeface typeface = Typeface.createFromAsset(context.getAssets(), defaultFontPath);
					cachedFontMap.put(defaultFontName, typeface);
					return typeface;
				} else {
					throw new Exception("Font not Found");
				}

			}catch (Exception e){
				Log.e(Utils.class.getSimpleName(), String.format("Unable to find %s font. Using Typeface.DEFAULT instead.", fontName));
				cachedFontMap.put(fontName, Typeface.DEFAULT);
				return Typeface.DEFAULT;
			}
		}
	}
    
    public static double mapValueFromRangeToRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return toLow + ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));
    }

    public static double clamp(double value, double low, double high) {
        return Math.min(Math.max(value, low), high);
    }

    public static List<Icon> getIcons() {
        List<Icon> icons = new ArrayList<>();
        icons.add(new Icon(R.drawable.heart_on, R.drawable.heart_off, IconType.Heart));
        icons.add(new Icon(R.drawable.star_on, R.drawable.star_off, IconType.Star));
        icons.add(new Icon(R.drawable.thumb_on, R.drawable.thumb_off, IconType.Thumb));

        return icons;
    }

    public static Drawable resizeDrawable(Context context, Drawable drawable, int width, int height) {
        Bitmap bitmap = getBitmap(drawable, width, height);
        return new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, width, height, true));
    }

    public static Bitmap getBitmap(Drawable drawable, int width, int height) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawableCompat) {
            return getBitmap((VectorDrawableCompat) drawable, width, height);
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable, width, height);
        } else {
            throw new IllegalArgumentException("Unsupported drawable type");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap getBitmap(VectorDrawableCompat vectorDrawable, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
