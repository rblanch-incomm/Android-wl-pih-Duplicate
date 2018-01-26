package com.incomm.payithere.tasks;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

/**
 * Created by rblanch on 11/15/17.
 */

public class BarcodeGeneratorTask extends AsyncTask<String, Void, Bitmap> {

    private int width;
    private int height;
    private final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.RGB_565;
    private OnTaskCompleted listener;

    public BarcodeGeneratorTask(int width, int height, OnTaskCompleted listener) {
        this.width = 3500;
        this.height = height > 0 ? height : 1;
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Code128Writer code128Writer = new Code128Writer();
        final int WHITE = 0xFFFFFFFF;
        final int BLACK = 0xFF000000;
        Log.e("VARS", width + " " + height + " " + BITMAP_CONFIG);
        Bitmap bitmap = Bitmap.createBitmap(width, height, BITMAP_CONFIG);
        try {
            BitMatrix bitMatrix = code128Writer.encode(strings[0], BarcodeFormat.CODE_128, width, height);
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
                }
            }
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (listener != null && !isCancelled()) {
            listener.onTaskCompleted(bitmap);
        }
    }
}
