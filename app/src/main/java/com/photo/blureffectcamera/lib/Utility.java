package com.photo.blureffectcamera.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.photoactivity.ConvolutionMatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Utility {
	public static final int ICON_SIZE = 160;
	private static final String TAG;
	private static final float limitDivider = 60.0f;
	private static final float limitDividerGinger = 160.0f;
	public static final int[] patternResIdList;
	public static final int[][] patternResIdList2;
	public static final int[] patternResIdList3;
	public static final int FOR_FRAME_CODE = 1;
	public static final String FOR_SHAPE = "for shape";
	public static final int FOR_SHAPE_CODE = 2;
	public static final String CHOOSE_FRAME_SHAPE = "choose_frame_shape";
	public static final int ADD_IMG_ACTIVITY_CALL_CODE = 1;
	public static final String COME_FROM_ADD_IMG = "come_from_add_img";
	public static final String FROM_FREE_STYLE = "from_free_style";
	public static final String FREE_STYLE_IMG_SIZE = "free_sytle_img_size";
	public static final String FREE_STYLE = "free_style";
	public static final String PHOTOVIEW_ARRAY = "photo_view_array";
	public static final int WRITE_TEXT = 113;
	public static Bitmap textBitmap;
	public static final int CHOOSE_EDIT_MODE = 111;
	public static final double HALF_CIRCLE_DEGREE = 180d;
	public static final double PI = 3.14159d;
	public static final double RANGE = 256d;
	public static int brightnessValue = 50;
	public static Bitmap tempBitmap;
	public static Bitmap drawBitmap;
	public static Bitmap EffectedBitmap;
	public static Bitmap BluredBitmap;
	public static String R_path;
	public static String path;
	public static ArrayList<String> filepathFestival;
	public static String[] FileNameStrings;
	public static Bitmap NEXT_BITMAP;
	public static int ShapeFlag;
	public static int blur = 0;
	public static Bitmap SHAPE_BITMAP;
	public static int shape, imgHeight, imgWidth, tmpimgHeight, tmpimgWidth;
	public static int focus;
	public static int ShapeDone;
	public static Bitmap FOCUS_BITMAP;
	public static Bitmap CroppedBitmap;
	public static final int EFFECT_CODE = 3;
	public static final int CROP_CODE = 4;
	public static final int DRAW_CODE = 5;
	public static final int BLUR_CODE = 6;
	public static final int BLUR_CANCLE_CODE = 7;
	public static final int BRIGHT_CODE = 8;
	public static final int ROTATE_CODE = 9;
	public static final int FOCUS_CODE = 10;
	public static final int OVERLAY_CODE = 11;
	public static final int SPLASH_CODE = 12;
	public static final int FRAME_CODE = 19;
	public static int code = 68;
	public static final int BRUSH_SHADOW = 1;
	public static final int BRUSH_DOUBLE_LINE = 2;
	public static final int BRUSH_SIMPLE = 3;
	public static final int BRUSH_DOTTED = 4;
	public static final int BRUSH_GLOW = 5;
	public static final int BRUSH_GLOW_WITH_STROKE = 6;
	public static final int BRUSH_SHADED = 7;
	public static final int BRUSH_ERASER = 8;
	public static int sharpnessValue = 90;
	public static final int SHARPNESS_CODE = 9;
	public static final int WHITE_CODE = 13;
	public static final int STICKER_CODE = 20;
	public static final int STICKER_MAIN_CODE = 21;
	public static final int BACKGROUND_CODE = 18;
	public static Boolean opengalleryfrommainpage = false;

	static {
		TAG = Utility.class.getSimpleName();
		patternResIdList = new int[]{R.drawable.no_pattern,R.drawable.no_pattern, R.drawable.color_picker, R.drawable.pattern_01, R.drawable.pattern_02, R.drawable.pattern_03, R.drawable.pattern_04, R.drawable.pattern_05, R.drawable.pattern_06, R.drawable.pattern_07, R.drawable.pattern_08, R.drawable.pattern_09, R.drawable.pattern_10, R.drawable.pattern_11, R.drawable.pattern_12, R.drawable.pattern_13, R.drawable.pattern_14, R.drawable.pattern_15, R.drawable.pattern_16, R.drawable.pattern_17, R.drawable.pattern_18, R.drawable.pattern_19, R.drawable.pattern_20, R.drawable.pattern_21, R.drawable.pattern_22, R.drawable.pattern_23, R.drawable.pattern_24, R.drawable.pattern_25, R.drawable.pattern_26, R.drawable.pattern_27, R.drawable.pattern_28, R.drawable.pattern_29, R.drawable.pattern_30, R.drawable.pattern_31, R.drawable.pattern_32, R.drawable.pattern_33, R.drawable.pattern_34, R.drawable.pattern_35, R.drawable.pattern_36, R.drawable.pattern_37, R.drawable.pattern_38, R.drawable.pattern_39, R.drawable.pattern_40, R.drawable.pattern_41, R.drawable.pattern_42, R.drawable.pattern_43, R.drawable.pattern_44, R.drawable.pattern_45, R.drawable.pattern_46, R.drawable.pattern_47, R.drawable.pattern_48, R.drawable.pattern_49, R.drawable.pattern_50, R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54, R.drawable.pattern_55, R.drawable.pattern_56, R.drawable.pattern_57};
		int r0[][] = new int[12][];
		r0[0] = new int[]{R.drawable.pattern_085, R.drawable.pattern_086, R.drawable.pattern_087, R.drawable.pattern_088, R.drawable.pattern_089, R.drawable.pattern_090, R.drawable.pattern_091, R.drawable.pattern_092, R.drawable.pattern_093, R.drawable.pattern_094, R.drawable.pattern_095, R.drawable.pattern_096};
		r0[1] = new int[]{R.drawable.pattern_097, R.drawable.pattern_098, R.drawable.pattern_099, R.drawable.pattern_100, R.drawable.pattern_101, R.drawable.pattern_102, R.drawable.pattern_103, R.drawable.pattern_104, R.drawable.pattern_105, R.drawable.pattern_106, R.drawable.pattern_107, R.drawable.pattern_108};
		r0[2] = new int[]{R.drawable.pattern_061, R.drawable.pattern_062, R.drawable.pattern_063, R.drawable.pattern_064, R.drawable.pattern_065, R.drawable.pattern_066, R.drawable.pattern_067, R.drawable.pattern_068, R.drawable.pattern_069, R.drawable.pattern_070, R.drawable.pattern_071, R.drawable.pattern_072};
		r0[3] = new int[]{R.drawable.pattern_073, R.drawable.pattern_074, R.drawable.pattern_075, R.drawable.pattern_076, R.drawable.pattern_077, R.drawable.pattern_078, R.drawable.pattern_079, R.drawable.pattern_080, R.drawable.pattern_081, R.drawable.pattern_082, R.drawable.pattern_083, R.drawable.pattern_084};
		r0[4] = new int[]{R.drawable.pattern_109, R.drawable.pattern_110, R.drawable.pattern_111, R.drawable.pattern_112, R.drawable.pattern_113, R.drawable.pattern_114, R.drawable.pattern_115, R.drawable.pattern_116, R.drawable.pattern_117, R.drawable.pattern_118, R.drawable.pattern_119, R.drawable.pattern_120};
		r0[5] = new int[]{R.drawable.pattern_121, R.drawable.pattern_122, R.drawable.pattern_123, R.drawable.pattern_124, R.drawable.pattern_125, R.drawable.pattern_126, R.drawable.pattern_127, R.drawable.pattern_128, R.drawable.pattern_129, R.drawable.pattern_130, R.drawable.pattern_131};
		r0[6] = new int[]{R.drawable.pattern_132, R.drawable.pattern_133, R.drawable.pattern_134, R.drawable.pattern_135, R.drawable.pattern_136, R.drawable.pattern_137, R.drawable.pattern_138, R.drawable.pattern_139, R.drawable.pattern_140, R.drawable.pattern_141, R.drawable.pattern_142};
		r0[7] = new int[]{R.drawable.pattern_49, R.drawable.pattern_50, R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54, R.drawable.pattern_55, R.drawable.pattern_56, R.drawable.pattern_57, R.drawable.pattern_060};
		r0[8] = new int[]{R.drawable.pattern_01, R.drawable.pattern_02, R.drawable.pattern_03, R.drawable.pattern_04, R.drawable.pattern_05, R.drawable.pattern_06, R.drawable.pattern_07, R.drawable.pattern_08, R.drawable.pattern_09, R.drawable.pattern_10, R.drawable.pattern_11, R.drawable.pattern_12};
		r0[9] = new int[]{R.drawable.pattern_13, R.drawable.pattern_14, R.drawable.pattern_15, R.drawable.pattern_16, R.drawable.pattern_17, R.drawable.pattern_18, R.drawable.pattern_19, R.drawable.pattern_20, R.drawable.pattern_21, R.drawable.pattern_22, R.drawable.pattern_23, R.drawable.pattern_24};
		r0[10] = new int[]{R.drawable.pattern_25, R.drawable.pattern_26, R.drawable.pattern_27, R.drawable.pattern_28, R.drawable.pattern_29, R.drawable.pattern_30, R.drawable.pattern_31, R.drawable.pattern_32, R.drawable.pattern_33, R.drawable.pattern_34, R.drawable.pattern_35, R.drawable.pattern_36};
		r0[11] = new int[]{R.drawable.pattern_37, R.drawable.pattern_38, R.drawable.pattern_39, R.drawable.pattern_40, R.drawable.pattern_41, R.drawable.pattern_42, R.drawable.pattern_43, R.drawable.pattern_44, R.drawable.pattern_45, R.drawable.pattern_46, R.drawable.pattern_47, R.drawable.pattern_48};
		patternResIdList2 = r0;
		patternResIdList3 = new int[]{R.drawable.no_pattern,R.drawable.no_pattern, R.drawable.color_picker, R.drawable.pattern_icon_08, R.drawable.pattern_icon_09, R.drawable.pattern_icon_06, R.drawable.pattern_icon_07, R.drawable.pattern_icon_10, R.drawable.pattern_icon_11, R.drawable.pattern_icon_12, R.drawable.pattern_icon_05, R.drawable.pattern_icon_01, R.drawable.pattern_icon_02, R.drawable.pattern_icon_03, R.drawable.pattern_icon_04};
	}

	public static boolean isPackageProEx(Context context) {
		return context.getPackageName().toLowerCase().contains("pro");
	}

	public static Bitmap getBitmapFromId(Context context, long imageID) {
		Bitmap bitmap = null;
		try {
			bitmap = Media.getBitmap(context.getContentResolver(), Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, Long.toString(imageID)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return bitmap;
	}

	public static Bitmap getScaledBitmapFromId(Context context, long imageID) {
		Uri uri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, Long.toString(imageID));
		Options options = new Options();
		options.inSampleSize = 4;
		AssetFileDescriptor fileDescriptor = null;
		try {
			fileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
	}

	public static Bitmap getScaledBitmapFromId(Context context, long imageID, int orientation, int requiredSize) {
		Uri uri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, Long.toString(imageID));
		Options boundsOption = new Options();
		boundsOption.inJustDecodeBounds = true;
		AssetFileDescriptor fileDescriptor = null;
		try {
			fileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (fileDescriptor == null) {
			return null;
		}
		BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, boundsOption);
		Options options = new Options();
		options.inSampleSize = calculateInSampleSize(boundsOption, requiredSize, requiredSize);
		Bitmap actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
		if (actuallyUsableBitmap == null) {
			return null;
		}
		Bitmap btm = rotateImage(actuallyUsableBitmap, orientation);
		if (btm == null) {
			return actuallyUsableBitmap;
		}
		if (actuallyUsableBitmap == btm) {
			return btm;
		}
		actuallyUsableBitmap.recycle();
		return btm;
	}

	public static Bitmap decodeFile(String path, int requiredSize) {
		try {
			File f = new File(path);
			Options boundsOption = new Options();
			boundsOption.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, boundsOption);
			Options o2 = new Options();
			o2.inSampleSize = calculateInSampleSize(boundsOption, requiredSize, requiredSize);
			Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
			ExifInterface exif = null;
			try {
				exif = new ExifInterface(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return rotateBitmap(bitmap, exif.getAttributeInt("Orientation", 0));
		} catch (FileNotFoundException e2) {
			return null;
		}
	}

	/* JADX WARNING: inconsistent code. */
	/* Code decompiled incorrectly, please refer to instructions dump. */
	public static Bitmap rotateBitmap(Bitmap r9, int r10) {

		throw new IllegalStateException("An error occurred while decompiling this method.");
	}

	private static Bitmap rotateImage(Bitmap bitmap, int orientation) {
		Matrix matrix = new Matrix();
		if (orientation == 90) {
			matrix.postRotate(90.0f);
		} else if (orientation == 180) {
			matrix.postRotate(180.0f);
		} else if (orientation == 270) {
			matrix.postRotate(270.0f);
		}
		if (orientation == 0) {
			return null;
		}
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
		int height = options.outHeight;
		int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			int halfHeight = height / 2;
			int halfWidth = width / 2;
			while (true) {
				if (halfHeight / inSampleSize <= reqHeight && halfWidth / inSampleSize <= reqWidth) {
					break;
				}
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}


	public static double getLeftSizeOfMemoryMb() {
		return (Double.valueOf((double) (Runtime.getRuntime().maxMemory() / 1048576)).doubleValue() - Double.valueOf((double) (Runtime.getRuntime().totalMemory() / 1048576)).doubleValue()) - (Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue() / Double.valueOf(1048576.0d).doubleValue());
	}

	public static double getLeftSizeOfMemoryEx(Context context) {
		double totalSize = Double.valueOf((double) Runtime.getRuntime().maxMemory()).doubleValue();
		double heapAllocated = Double.valueOf((double) Runtime.getRuntime().totalMemory()).doubleValue();
		return (totalSize - heapAllocated) - Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue();
	}

	public static double getLeftSizeOfMemory() {
		double totalSize = Double.valueOf((double) Runtime.getRuntime().maxMemory()).doubleValue();
		double heapAllocated = Double.valueOf((double) Runtime.getRuntime().totalMemory()).doubleValue();
		return (totalSize - (heapAllocated - Double.valueOf((double) Runtime.getRuntime().freeMemory()).doubleValue())) - Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue();
	}

	public static void logFreeMemory(Context context) {
	}

	public static int maxSizeForDimension(Context context, int count, float upperLimit) {
		float divider = limitDivider;
		if (VERSION.SDK_INT <= 11) {
			upperLimit = 800.0f;
			divider = limitDividerGinger;
		}
		int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / ((double) (((float) count) * divider)));
		if (maxSize <= 0) {
			maxSize = getDefaultLimit(count, upperLimit);
		}
		return Math.min(maxSize, getDefaultLimit(count, upperLimit));
	}

	public static int maxSizeForSave(Context context, float upperLimit) {
		float divider = limitDivider;
		if (VERSION.SDK_INT <= 11) {
			divider = limitDividerGinger;
		}
		int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / ((double) divider));
		if (maxSize > 0) {
			return (int) Math.min((float) maxSize, upperLimit);
		}
		return (int) upperLimit;
	}

	private static int getDefaultLimit(int count, float upperLimit) {
		int limit = (int) (((double) upperLimit) / Math.sqrt((double) count));
		return limit;
	}

	public static Bitmap getPhoto(String path, int reqWidth, int reqHeight) {
		Options options = new Options();
		options.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(path, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		try {
			Bitmap bmp = BitmapFactory.decodeFile(path, options);

			{
				Matrix matrix = new Matrix();
				matrix.postRotate(getRotation(path));
				bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
						bmp.getHeight(), matrix, true);
			}
			return bmp;
		} catch (OutOfMemoryError e) {
		}
		return null;
	}
	private static int getRotation(String path) {
		ExifInterface exif;
		int rotationAngle = 0;
		try {
			exif = new ExifInterface(path);

			String orientString = exif
					.getAttribute(ExifInterface.TAG_ORIENTATION);
			int orientation = orientString != null ? Integer
					.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
				rotationAngle = 90;
			if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
				rotationAngle = 180;
			if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
				rotationAngle = 270;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rotationAngle;
	}



	public static String FOR_FRAME = "for frame";
	public static boolean backImg = false;
	public static Bitmap patternBitmap;

	public static Bitmap overlay(Bitmap bitmap1, Bitmap bitmapOverlay,
                                 int opacity) {
		Bitmap resultBitmap = Bitmap.createBitmap(bitmap1.getWidth(),
				bitmap1.getHeight(), Config.ARGB_8888);

		Canvas c = new Canvas(resultBitmap);
		c.drawBitmap(bitmap1, 0, 0, null);
		Paint p = new Paint();
		p.setAlpha(opacity);
		c.drawBitmap(bitmapOverlay, 0, 0, p);
		return resultBitmap;
	}

	public static Bitmap takeContrast(Bitmap src, double value) {
		// src image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap with original size
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;
		// get contrast value
		double contrast = Math.pow((100 + value) / 100, 2);

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				// apply filter contrast for every channel R, G, B
				R = Color.red(pixel);
				R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (R < 0) {
					R = 0;
				} else if (R > 255) {
					R = 255;
				}

				G = Color.red(pixel);
				G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (G < 0) {
					G = 0;
				} else if (G > 255) {
					G = 255;
				}

				B = Color.red(pixel);
				B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (B < 0) {
					B = 0;
				} else if (B > 255) {
					B = 255;
				}

				// set new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	public static Bitmap SetBrightness(Bitmap src, int value) {
		// original image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for (int x = 0; x > width; ++x) {
			for (int y = 0; y > height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);

				// increase/decrease each channel
				R += value;
				if (R > 50) {
					R = 50;
				} else if (R < 0) {
					R = 0;
				}

				G += value;
				if (G > 50) {
					G = 50;
				} else if (G < 0) {
					G = 0;
				}

				B += value;
				if (B > 50) {
					B = 50;
				} else if (B < 0) {
					B = 0;
				}

				// apply new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	public static Bitmap boostColor(Bitmap src, int type, float percent) {
		// original image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;
		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				if (type == 1) {
					R = (int) (R * (1 + percent));
					if (R > 255)
						R = 255;
				} else if (type == 2) {
					G = (int) (G * (1 + percent));
					if (G > 255)
						G = 255;
				} else if (type == 3) {
					B = (int) (B * (1 + percent));
					if (B > 255)
						B = 255;
				}
				// apply new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}
		// return final image
		return bmOut;
	}

	public static Bitmap takeColorContrast(Bitmap src, double value) {
		// src image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap with original size
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;
		// get contrast value
		double contrast = Math.pow((100 + value) / 100, 2);

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				// apply filter contrast for every channel R, G, B
				R = Color.red(pixel);
				R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (R < 0) {
					R = 0;
				} else if (R > 255) {
					R = 255;
				}

				G = Color.green(pixel);
				G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (G < 0) {
					G = 0;
				} else if (G > 255) {
					G = 255;
				}

				B = Color.blue(pixel);
				B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (B < 0) {
					B = 0;
				} else if (B > 255) {
					B = 255;
				}

				// set new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}
		// return final image
		return bmOut;
	}

	public static Bitmap setGamma(Bitmap src, double red, double green,
                                  double blue) {
		// create output image
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());
		// get image size
		int width = src.getWidth();
		int height = src.getHeight();
		// color information
		int A, R, G, B;
		int pixel;
		// constant value curve
		final int MAX_SIZE = 256;
		final double MAX_VALUE_DBL = 255.0;
		final int MAX_VALUE_INT = 255;
		final double REVERSE = 1.0;

		// gamma arrays
		int[] gammaR = new int[MAX_SIZE];
		int[] gammaG = new int[MAX_SIZE];
		int[] gammaB = new int[MAX_SIZE];

		// setting values for every gamma channels
		for (int i = 0; i < MAX_SIZE; ++i) {
			gammaR[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE
							/ red)) + 0.5));
			gammaG[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE
							/ green)) + 0.5));
			gammaB[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE
							/ blue)) + 0.5));
		}

		// apply gamma table
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				// look up gamma
				R = gammaR[Color.red(pixel)];
				G = gammaG[Color.green(pixel)];
				B = gammaB[Color.blue(pixel)];
				// set new color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	public static Bitmap applyHueFilterEffect(Bitmap source, int level) {
		// get original image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		float[] HSV = new float[3];
		// get pixel array from source image
		source.getPixels(pixels, 0, width, 0, 0, width, height);

		int index = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// convert to HSV
				Color.colorToHSV(pixels[index], HSV);
				// increase Saturation level
				HSV[0] *= level;
				HSV[0] = (float) Math.max(0.0, Math.min(HSV[0], 360.0));
				// take color back
				pixels[index] |= Color.HSVToColor(HSV);
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	public static Bitmap applySaturationFilter(Bitmap source, int level) {
		// get original image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		float[] HSV = new float[3];
		// get pixel array from source image
		source.getPixels(pixels, 0, width, 0, 0, width, height);

		int index = 0;
		// iteration through all pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// convert to HSV
				Color.colorToHSV(pixels[index], HSV);
				// increase Saturation level
				HSV[1] *= level;
				HSV[1] = (float) Math.max(0.0, Math.min(HSV[1], 1.0));
				// take color back
				pixels[index] = Color.HSVToColor(HSV);
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	public static Bitmap applySepiaToningEffect(Bitmap src, int depth,
                                                double red, double green, double blue) {
		// source image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// constant grayscale
		final double GS_RED = 0.3;
		final double GS_GREEN = 0.59;
		final double GS_BLUE = 0.11;
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels of image
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				// get color on each channel
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				// apply grayscale sample
				B = G = R = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);

				// apply intensity level for sepia-toning on each channel
				R += (depth * red);
				if (R > 255) {
					R = 255;
				}

				G += (depth * green);
				if (G > 255) {
					G = 255;
				}

				B += (depth * blue);
				if (B > 255) {
					B = 255;
				}

				// set new pixel color to output image
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	public static Bitmap applySmoothEffect(Bitmap src, double value) {
		// create convolution matrix instance
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.setAll(1);
		convMatrix.Matrix[1][1] = value;
		// set weight of factor and offset
		convMatrix.Factor = value + 8;
		convMatrix.Offset = 1;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	public static Bitmap applyTintEffect(Bitmap src, int degree) {
		// get source image size
		int width = src.getWidth();
		int height = src.getHeight();

		int[] pix = new int[width * height];
		// get pixel array from source
		src.getPixels(pix, 0, width, 0, 0, width, height);

		int RY, GY, BY, RYY, GYY, BYY, R, G, B, Y;
		double angle = (PI * (double) degree) / HALF_CIRCLE_DEGREE;

		int S = (int) (RANGE * Math.sin(angle));
		int C = (int) (RANGE * Math.cos(angle));

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				int index = y * width + x;
				int r = (pix[index] >> 16) & 0xff;
				int g = (pix[index] >> 8) & 0xff;
				int b = pix[index] & 0xff;
				RY = (70 * r - 59 * g - 11 * b) / 100;
				GY = (-30 * r + 41 * g - 11 * b) / 100;
				BY = (-30 * r - 59 * g + 89 * b) / 100;
				Y = (30 * r + 59 * g + 11 * b) / 100;
				RYY = (S * BY + C * RY) / 256;
				BYY = (C * BY - S * RY) / 256;
				GYY = (-51 * RYY - 19 * BYY) / 100;
				R = Y + RYY;
				R = (R < 0) ? 0 : ((R > 255) ? 255 : R);
				G = Y + GYY;
				G = (G < 0) ? 0 : ((G > 255) ? 255 : G);
				B = Y + BYY;
				B = (B < 0) ? 0 : ((B > 255) ? 255 : B);
				pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
			}
		// output bitmap
		Bitmap outBitmap = Bitmap.createBitmap(width, height, src.getConfig());
		outBitmap.setPixels(pix, 0, width, 0, 0, width, height);

		pix = null;
		return outBitmap;
	}

	public static Bitmap applyDarkFilter(Bitmap source) {
		// get image source size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);
		// create random object
		Random random = new Random();

		int R, G, B, index = 0, thresHold = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// get RGB colors
				R = Color.red(pixels[index]);
				G = Color.green(pixels[index]);
				B = Color.blue(pixels[index]);
				// generate threshold
				thresHold = random.nextInt(255);
				if (R < thresHold && G < thresHold && B < thresHold) {
					pixels[index] = Color.rgb(0, 0, 0);
				}
			}
		}
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	public static Bitmap doEmboss(Bitmap src) {
		// set Emboss configuration
		double[][] EmbossConfig = new double[][] { { -1, 0, -1 }, { 0, 4, 0 },
				{ -1, 0, -1 } };
		// create convolution matrix instance
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		// apply configuration
		convMatrix.applyConfig(EmbossConfig);
		// set weight of factor and offset
		convMatrix.Factor = 1;
		convMatrix.Offset = 127;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);

	}

	public static Bitmap doEngrave(Bitmap src) {
		// create convolution matrix instance
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.setAll(0);
		convMatrix.Matrix[0][0] = -2;
		convMatrix.Matrix[1][1] = 2;
		// set weight of factor and offset
		convMatrix.Factor = 1;
		convMatrix.Offset = 95;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	public static Bitmap applyFleaEffect(Bitmap source) {
		// get source image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);
		// create a random object
		Random random = new Random();

		int index = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// get random color
				int randColor = Color.rgb(random.nextInt(255),
						random.nextInt(255), random.nextInt(255));
				// OR
				pixels[index] |= randColor;
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, source.getConfig());
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	public static Bitmap grayScaleImage(Bitmap src) {
		// constant factors
		final double GS_RED = 0.299;
		final double GS_GREEN = 0.587;
		final double GS_BLUE = 0.114;

		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());
		// pixel information
		int A, R, G, B;
		int pixel;

		// get image size
		int width = src.getWidth();
		int height = src.getHeight();

		// scan through every single pixel
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get one pixel color
				pixel = src.getPixel(x, y);
				// retrieve color of all channels
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				// take conversion up to one single value
				R = G = B = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);
				// set new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	public static Bitmap getRefelection(Bitmap image) {
		// The gap we want between the reflection and the original image
		final int reflectionGap = 0;

		// Get your bitmap from drawable folder
		Bitmap originalImage = image;

		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		// This will not scale but will flip on the Y axis
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		/*
		 * Create a Bitmap with the flip matix applied to it. We only want the
		 * bottom half of the image
		 */

		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		// Create a new bitmap with same width but taller to fit reflection
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);
		// Create a new Canvas with the bitmap that's big enough for
		// the image plus gap plus reflection
		Canvas canvas = new Canvas(bitmapWithReflection);
		// Draw in the original image
		canvas.drawBitmap(originalImage, 0, 0, null);
		// Draw the reflection Image
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		// Create a shader that is a linear gradient that covers the reflection
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
				+ reflectionGap, 0x99ffffff, 0x00ffffff, TileMode.CLAMP);
		// Set the paint to use this shader (linear gradient)
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		if (originalImage != null && originalImage.isRecycled()) {
			originalImage.recycle();
			originalImage = null;
		}
		if (reflectionImage != null && reflectionImage.isRecycled()) {
			reflectionImage.recycle();
			reflectionImage = null;
		}
		return bitmapWithReflection;
	}

	public static Bitmap highlightImage(Bitmap src) {
		// create new bitmap, which will be painted and becomes result image
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth() + 96,
				src.getHeight() + 96, Config.ARGB_8888);
		// setup canvas for painting
		Canvas canvas = new Canvas(bmOut);
		// setup default color
		canvas.drawColor(0, Mode.CLEAR);
		// create a blur paint for capturing alpha
		Paint ptBlur = new Paint();
		ptBlur.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
		int[] offsetXY = new int[2];
		// capture alpha into a bitmap
		Bitmap bmAlpha = src.extractAlpha(ptBlur, offsetXY);
		// create a color paint
		Paint ptAlphaColor = new Paint();
		ptAlphaColor.setColor(0xFFFFFFFF);
		// paint color for captured alpha region (bitmap)
		canvas.drawBitmap(bmAlpha, offsetXY[0], offsetXY[1], ptAlphaColor);
		// free memory
		bmAlpha.recycle();

		// paint the image source
		canvas.drawBitmap(src, 0, 0, null);

		// return out final image
		return bmOut;
	}

	public static Bitmap applySnowFallingEffect(Bitmap source) {
		// get source image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);
		// create random object
		Random random = new Random();

		int R, G, B, index = 0, thresHold = 50;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// get RGB colors
				R = Color.red(pixels[index]);
				G = Color.green(pixels[index]);
				B = Color.blue(pixels[index]);
				// generate threshold
				thresHold = random.nextInt(255);
				if (R > thresHold && G > thresHold && B > thresHold) {
					pixels[index] = Color.rgb(255, 255, 255);
				}
			}
		}
		// create output bitmap
		Bitmap bmOut = Bitmap
				.createBitmap(width, height, Config.RGB_565);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	public static Bitmap processingBitmap_Brightness(Bitmap src) {

		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		for (int x = 0; x < src.getWidth(); x++) {
			for (int y = 0; y < src.getHeight(); y++) {
				int pixelColor = src.getPixel(x, y);
				int pixelAlpha = Color.alpha(pixelColor);

				int pixelRed = Color.red(pixelColor) + brightnessValue;
				int pixelGreen = Color.green(pixelColor) + brightnessValue;
				int pixelBlue = Color.blue(pixelColor) + brightnessValue;

				if (pixelRed > 255) {
					pixelRed = 255;
				} else if (pixelRed < 0) {
					pixelRed = 0;
				}

				if (pixelGreen > 255) {
					pixelGreen = 255;
				} else if (pixelGreen < 0) {
					pixelGreen = 0;
				}

				if (pixelBlue > 255) {
					pixelBlue = 255;
				} else if (pixelBlue < 0) {
					pixelBlue = 0;
				}

				int newPixel = Color.argb(pixelAlpha, pixelRed, pixelGreen,
						pixelBlue);

				bmOut.setPixel(x, y, newPixel);

			}
		}
		return bmOut;
	}

	public static Bitmap resizeImage2(Bitmap bitmap, int width, int height) {
		int mwidth = bitmap.getWidth();
		int mheight = bitmap.getHeight();
		float scale = 1f;
		float scale1 = 1f;
		float scale2 = 1f;
		scale1 = (width + 0.0f) / mwidth;
		scale2 = (height + 0.0f) / mheight;
		if (scale1 > scale2) {
			scale = scale2;
		} else {
			scale = scale1;
		}
		// Log.e("sad", scale + "  " + scale1 + "   " + scale2);
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		Bitmap newbitmap = Bitmap.createBitmap(bitmap, 0, 0, mwidth, mheight,
				matrix, true);
		return newbitmap;
	}

	public static void setFont(Context mContext, TextView tv) {
		Typeface typeFace = Typeface.createFromAsset(mContext.getAssets(),
				"Chirag_regular.TTF");
		tv.setTypeface(typeFace);
	}

	public static Bitmap sharpen(Bitmap src) {
		double[][] SharpConfig = new double[][] { { 0, -2, 0 }, { -2, 11, -2 },
				{ 0, -2, 0 } };
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(SharpConfig);
		convMatrix.Factor = 3;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	public static int dpToPx(Context context, int dp) {
		DisplayMetrics d = context.getResources().getDisplayMetrics();
		dp = (int) (d.density * dp);
		return dp;
	}






}
