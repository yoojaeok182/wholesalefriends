package com.wholesale.wholesalefriends.module.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import com.wholesale.wholesalefriends.main.common.Constant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {

    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 24f;
    private static final int MAX_IMAGESIZE = 1000;


    public static String saveImage(Bitmap bitmap) {

        FileOutputStream stream = null;
        try {
            String folderPath = Constant.PICTURE_DIR2;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File((folderPath + "/wholesalestore" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            return file.getAbsolutePath();

        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap compressImage(String fileImage, int scale, boolean isCroped) {

        Bitmap bitmap = null;
        try {
            BitmapFactory.Options optionsForGettingDimensions = new BitmapFactory.Options();
            optionsForGettingDimensions.inJustDecodeBounds = true;
            BufferedInputStream boundsOnlyStream = new BufferedInputStream(new FileInputStream(fileImage));
            bitmap = BitmapFactory.decodeStream(boundsOnlyStream, null, optionsForGettingDimensions);
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (boundsOnlyStream != null) {
                boundsOnlyStream.close();
            }
            int w, l;
            w = optionsForGettingDimensions.outWidth;
            l = optionsForGettingDimensions.outHeight;

            ExifInterface exif = new ExifInterface(fileImage);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            int rotate = 0;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = -90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            int what = w > l ? w : l;

            BitmapFactory.Options options = new BitmapFactory.Options();
            int nScale = ((what / 1000) + 1);
            options.inSampleSize = nScale;
            options.inJustDecodeBounds = false;

            // TODO: Sometime the decode File Returns null for some images
            // For such cases, thumbnails can't be created.
            // Thumbnails will link to the original file
            BufferedInputStream scaledInputStream = new BufferedInputStream(new FileInputStream(fileImage));
            bitmap = BitmapFactory.decodeStream(scaledInputStream, null, options);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = width;
            int newHeight = height;
            float rate = 0.0f;

            if (width > height) {
                if (MAX_IMAGESIZE < width) {
                    rate = MAX_IMAGESIZE / (float) width;
                    newHeight = (int) (height * rate);
                    newWidth = MAX_IMAGESIZE;
                }
            } else {
                if (MAX_IMAGESIZE < height) {
                    rate = MAX_IMAGESIZE / (float) height;
                    newWidth = (int) (width * rate);
                    newHeight = MAX_IMAGESIZE;
                }
            }
            scaledInputStream.close();
            if (rotate != 0) {
                Matrix matrix = new Matrix();
                matrix.setRotate(rotate);
                if(isCroped) {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newHeight, newWidth, false);
                    bitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, false);
                } else {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, false);
                }
            } else {
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), null, false);
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap compressCropImage(String fileImage, int scale) {

        Bitmap bitmap = null;
        try {
            BitmapFactory.Options optionsForGettingDimensions = new BitmapFactory.Options();
            optionsForGettingDimensions.inJustDecodeBounds = true;
            BufferedInputStream boundsOnlyStream = new BufferedInputStream(new FileInputStream(fileImage));
            bitmap = BitmapFactory.decodeStream(boundsOnlyStream, null, optionsForGettingDimensions);
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (boundsOnlyStream != null) {
                boundsOnlyStream.close();
            }
            int w, l;
            w = optionsForGettingDimensions.outWidth;
            l = optionsForGettingDimensions.outHeight;

            ExifInterface exif = new ExifInterface(fileImage);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            int rotate = 0;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = -90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            int what = w > l ? w : l;

            BitmapFactory.Options options = new BitmapFactory.Options();
            if (what > 3000) {
                options.inSampleSize = scale * 6;
            } else if (what > 2000 && what <= 3000) {
                options.inSampleSize = scale * 5;
            } else if (what > 1500 && what <= 2000) {
                options.inSampleSize = scale * 4;
            } else if (what > 1000 && what <= 1500) {
                options.inSampleSize = scale * 3;
            } else if (what > 400 && what <= 1000) {
                options.inSampleSize = scale * 2;
            } else {
                options.inSampleSize = scale;
            }

            options.inJustDecodeBounds = false;

            // TODO: Sometime the decode File Returns null for some images
            // For such cases, thumbnails can't be created.
            // Thumbnails will link to the original file
            BufferedInputStream scaledInputStream = new BufferedInputStream(new FileInputStream(fileImage));
            bitmap = BitmapFactory.decodeStream(scaledInputStream, null, options);
            scaledInputStream.close();
            if (rotate != 0) {
                Matrix matrix = new Matrix();
                matrix.setRotate(rotate);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                        bitmap.getHeight(), matrix, false);
            }

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap rotateImage(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    /**
     * Bitmap 이미지를 가운데를 기준으로 w, h 크기 만큼 crop한다.
     *
     * @param src 원본
     * @param w   넓이
     * @param h   높이
     * @return
     */
    public static Bitmap cropCenterBitmap(Bitmap src, int w, int h) {
        if (src == null) {
            return null;
        }

        int width = src.getWidth();
        int height = src.getHeight();

        if (width < w && height < h) {
            return src;
        }

        int x = 0;
        int y = 0;

        if (width > w) {
            x = width - w / 2;
        }

        if (height > h) {
            y = height - h / 2;
        }

        int cw = w; // crop width
        int ch = h; // crop height

        if (w > width) {
            cw = width;
        }

        if (h > height) {
            ch = height;
        }

        return Bitmap.createBitmap(src, x, y, cw, ch);
    }

}
