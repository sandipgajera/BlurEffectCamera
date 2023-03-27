package com.photo.blureffectcamera.canvas;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

@SuppressWarnings("unchecked")
public class FontCache {
private static final Hashtable<String, Typeface> cache;
    
    static {
        cache = new Hashtable<String, Typeface>();
    }
    
    public static Typeface get(final Context context, final String s) {
        final Hashtable<String, Typeface> cache = FontCache.cache;
        // monitorenter(cache)
        Label_0026: {
            if (s == null) {
                break Label_0026;
            }
            try {
                if (s.length() == 0 || s.compareTo("") == 0) {
                    return null;
                }
                if (!FontCache.cache.containsKey(s)) {
                    FontCache.cache.put(s, Typeface.createFromAsset(context.getAssets(), s));
                }
                return FontCache.cache.get(s);
            }
            finally {
            }
            // monitorexit(cache)
        }
		return null;
    }
}
