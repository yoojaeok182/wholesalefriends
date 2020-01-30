package com.wholesale.wholesalefriends.module.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wholesale.wholesalefriends.main.base.MyApplication;

import static android.content.Context.WINDOW_SERVICE;

public class ImageUtil {

    public static void requestImageView(Context ctx, int clumWidth, View iv, int item_margin, int marginCnt){
        try{

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) ctx.getApplicationContext()
                    .getSystemService(WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);


            double gap = 0;
            double imgWidth = 0;
            double imgHeight =0;


            int maxW = iv.getWidth();
            int maxH = iv.getHeight();


            Log.e("Image","maxW1:"+maxW+" maxH1:"+maxH);
            ViewGroup.LayoutParams params = iv.getLayoutParams();
            if(maxW == 0 || maxH == 0){
                iv.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                maxW = iv.getMeasuredWidth();
                maxH = iv.getMeasuredHeight();
                Log.e("trace",metrics.widthPixels+"mesure maxW:"+maxW+" maxH:"+maxH);
            }


            int spanCnt = ((metrics.widthPixels)/clumWidth);
            int margin =(item_margin*(spanCnt-1))+marginCnt;
            gap= ((double)(((metrics.widthPixels)-(margin))/spanCnt)/ (double)maxW);

            gap= (double)(((metrics.widthPixels)-Util.convertDpToPixel(60, (ctx!=null?ctx: MyApplication.get_instance().getApplicationContext())))/2)/ (double)maxW;


            imgWidth = maxW*(gap);
            imgHeight = maxH*(gap);

            params.width =(int) imgWidth;
            params.height = (int)imgHeight;

            if(maxW>params.width){
                params.width = maxW;

            }else{

            }
            if(maxH>params.height){
                params.height = maxH;
            }

//            ivPhoto.setLayoutParams(params1);
            iv.setLayoutParams(params);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    public static void requestImageView(Context ctx, int clumWidth, View iv,ImageView ivPhoto, int item_margin, int marginCnt){
        try{

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) ctx.getApplicationContext()
                    .getSystemService(WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);


            double gap = 0;
            double imgWidth = 0;
            double imgHeight =0;


            int maxW = iv.getWidth();
            int maxH = iv.getHeight();

            int imgmaxW = ivPhoto.getWidth();
            int imgmaxH = ivPhoto.getHeight();

            Log.e("Image","maxW1:"+maxW+" maxH1:"+maxH);
            ViewGroup.LayoutParams params = iv.getLayoutParams();
            ViewGroup.LayoutParams params1 = ivPhoto.getLayoutParams();
            if(maxW == 0 || maxH == 0){
                iv.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                maxW = iv.getMeasuredWidth();
                maxH = iv.getMeasuredHeight();
                Log.e("trace",metrics.widthPixels+"mesure maxW:"+maxW+" maxH:"+maxH);
            }

            if(imgmaxW == 0 || imgmaxH == 0){
                ivPhoto.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                imgmaxW = ivPhoto.getMeasuredWidth();
                imgmaxH = ivPhoto.getMeasuredHeight();
                Log.e("trace",metrics.widthPixels+"mesure maxW:"+maxW+" maxH:"+maxH);
            }

            int spanCnt = ((metrics.widthPixels)/clumWidth);
            int margin =(item_margin*(spanCnt-1))+marginCnt;
            gap= ((double)(((metrics.widthPixels)-(margin))/spanCnt)/ (double)maxW);




            imgWidth = maxW*(gap);
            imgHeight = maxH*(gap);

            params.width =(int) imgWidth;
            params.height = (int)imgHeight;

            params1.width = (int)(imgmaxW*(gap));
            params1.height = (int)(imgmaxH*gap);

            if(maxW>params.width){
                params.width = maxW;

            }else{

            }
            if(maxH>params.height){
                params.height = maxH;
            }

//            ivPhoto.setLayoutParams(params1);
            iv.setLayoutParams(params);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    public static void requestImageView(Context ctx, int clumWidth, ImageView iv, int item_margin, int marginCnt){
        try{

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) ctx.getApplicationContext()
                    .getSystemService(WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);


            double gap = 0;
            double imgWidth = 0;
            double imgHeight =0;


            int maxW = iv.getWidth();
            int maxH = iv.getHeight();
            Log.e("Image","maxW1:"+maxW+" maxH1:"+maxH);
            ViewGroup.LayoutParams params = iv.getLayoutParams();

            if(maxW == 0 || maxH == 0){
                iv.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                maxW = iv.getMeasuredWidth();
                maxH = iv.getMeasuredHeight();
                Log.e("trace",metrics.widthPixels+"mesure maxW:"+maxW+" maxH:"+maxH);
            }
            int spanCnt = (metrics.widthPixels)/clumWidth;
            int margin =(item_margin*(spanCnt-1))+marginCnt;
            gap= ((double)(((metrics.widthPixels)-margin)/spanCnt)/ (double)maxW);




            imgWidth = maxW*(gap);
            imgHeight = maxH*(gap);

            params.width =(int) imgWidth;
            params.height = (int)imgHeight;

            if(maxW>params.width){
                params.width = maxW;

            }else{

            }
            if(maxH>params.height){
                params.height = maxH;
            }

            iv.setLayoutParams(params);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }
}
