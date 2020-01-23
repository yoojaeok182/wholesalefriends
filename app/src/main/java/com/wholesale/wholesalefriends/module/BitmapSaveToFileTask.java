package com.wholesale.wholesalefriends.module;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Handa-dev5 on 2018-01-10.
 */

public class BitmapSaveToFileTask {

    private Context mContext;
    private ArrayList<Bitmap> bitmapArrayList;
    private ArrayList<File> fileArrayList = new ArrayList<>();
    private int progressCount = 0;

    public interface TaskListener {
        void onFinish(ArrayList<File> results);
    }

    private TaskListener taskListener;

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public BitmapSaveToFileTask(Context context, ArrayList<Bitmap> bitmaps) {
        this.mContext = context;
        this.bitmapArrayList = bitmaps;
    }


    public void convert() {
        if (bitmapArrayList.get(progressCount) != null) {
            BitmapSaveTask task = new BitmapSaveTask();
            task.setProgressListener(new ProgressListener() {
                @Override public void onFinish(File results) {
                    fileArrayList.add(results);
                    progressCount++;
                    if (bitmapArrayList.size() > progressCount) {
                        convert();
                    } else {
                        taskListener.onFinish(fileArrayList);
                    }
                }
            });
            try {
                task.execute(bitmapArrayList.get(progressCount)).get();
            } catch (Exception e) {
                e.printStackTrace();
                progressCount++;
                if (bitmapArrayList.size() > progressCount) {
                    convert();
                } else {
                    taskListener.onFinish(fileArrayList);
                }
            }
        } else {
            progressCount++;
            if (bitmapArrayList.size() > progressCount) {
                convert();
            } else {
                taskListener.onFinish(fileArrayList);
            }
        }

    }


    public interface ProgressListener {
        void onFinish(File results);
    }

    public class BitmapSaveTask extends AsyncTask<Bitmap, String, File> {

        private ProgressListener progressListener;

        public void setProgressListener(ProgressListener progressListener) {
            this.progressListener = progressListener;
        }


        @Override protected File doInBackground(Bitmap... bitmaps) {
            return createTempFile(bitmaps[0]);
        }

        @Override
        protected void onPostExecute(File result) {
            progressListener.onFinish(result);
        }
    }


    private File createTempFile(Bitmap bitmap) {

        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
