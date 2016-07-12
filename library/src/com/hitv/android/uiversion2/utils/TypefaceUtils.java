package com.hitv.android.uiversion2.utils;import android.content.Context;import android.graphics.Typeface;public class TypefaceUtils {    private static Typeface typefacefzltzhjt;    private static Typeface gothamBook;    private static Typeface gothamMedium;    private static TypefaceUtils mInstance;    private TypefaceUtils(Context context) {    	typefacefzltzhjt = Typeface.createFromAsset(context.getAssets(),                "fzltzhjt.ttf");    	gothamBook = Typeface.createFromAsset(context.getAssets(), "GothamRounded-Book.otf");    	gothamMedium = Typeface.createFromAsset(context.getAssets(), "GothamRounded-Medium.otf");    }    public static synchronized TypefaceUtils getInstance(Context context) {        if (mInstance == null) {            mInstance = new TypefaceUtils(context);        }        return mInstance;    }    public Typeface getTypefaceFzltzhjt() {        return typefacefzltzhjt;    }        public Typeface getTypefaceGothamBook(){    	return gothamBook;    }        public Typeface getTypefaceGothamMedium(){    	return gothamMedium;    }}