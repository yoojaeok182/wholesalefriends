package com.wholesale.wholesalefriends.module.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.wholesale.wholesalefriends.main.base.MyApplication;
import com.wholesale.wholesalefriends.module.StorePackageInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import androidx.core.app.ActivityCompat;

public class Util {

    public static final int LANGUE_ENG = 0;
    public static final int LANGUE_KOR = 1;
    public static final int LANGUE_MIX = 2;

    public static boolean isPurchaceOk = false;


    public static InputFilter filter1= new InputFilter() {

        public CharSequence filter(CharSequence source, int start, int end,

                                   Spanned dest, int dstart, int dend) {



            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");

            if (!ps.matcher(source).matches()) {

                return "";

            }

            return null;

        }

    };




    public static String getSplitRegDate(String regDate){
        String origin = regDate;
        if(origin!=null && origin.length()>0 && origin.indexOf(" ")>-1){
            String[] str = origin.split(" ");
            origin = str[0];
        }
        return origin;
    }
    public static String getFormattedPrice(Integer price){
        String formatteredStringPrice =price +"";
        try{
            DecimalFormat myFormatter = new DecimalFormat("###,###");
             formatteredStringPrice = myFormatter.format(price);
        }catch (Throwable e){}
        return formatteredStringPrice+"원";
    }
    /**
            * 파일 Uri 를 절대경로로 변환함
     */
    public static String getPath(final Context context, final Uri uri) {

        try {
            //check here to KITKAT or new version
            final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }
                }
                // DownloadsProvider
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static void updateDisplay(TextView tvAlarm,int pHour, int pMinute) {
        String _timeStr = "";
        tvAlarm.setText(_timeStr+" "+
                new StringBuilder()
                        .append(Util.getMinutePad(pHour)).append(":")
                        .append(Util.getMinutePad(pMinute)));
    }

    public static void updateDisplay(int pHour, int pMinute, TextView tvAlarm) {
        String _timeStr = "";
        tvAlarm.setText(_timeStr+" "+
                new StringBuilder()
                        .append(Util.getMinutePad(pHour)).append(":")
                        .append(Util.getMinutePad(pMinute)));
    }

    public static String updateDisplay(int hour, int minute) {
        String ampm ="", hourStr, minueStr;
	/*	if (hour >= 12)
			ampm = "PM";
		else
			ampm = "AM";*/

        if (hour < 10)
            hourStr = String.format("%02d", hour);
        else
            hourStr = String.valueOf(hour);

        if (minute < 10)
            minueStr = String.format("%02d", minute);
        else
            minueStr = String.valueOf(minute);

        return ampm + " " + hourStr + ":" + minueStr;
    }



    public static String[] getRandomIndex(){
        String[] str = new String[3];

        // Integer 정수 객체를 담는 HashSet
// HashSet은 중복을 거부하는 Set 입니다.
// 중복이 들어오면 이는 무시되어 같은 수는 하나만 담기게 됩니다.
        HashSet<Integer> luckyNumbers = new HashSet<>();

// 이 HashSet에 수가 6개 채워질 때 까지 반복합니다.
// 중복 처리가 자동으로 이루어지므로 삽입만 하면 됩니다.
// 하지만 중복된 수는 삽입이 거부되므로 반복 횟수가 늘어날 수 있습니다.
        while(luckyNumbers.size() < 3){
            // 1. 임의의 수 삽입
            // Math.random()이 제공하는 0.0~0.999...의 수에 45를 곱해
            // 0.0~44.xx의 수를 만들고 이를 정수로 바꾼다.
            // 여기에 1을 더해서 1~45가 되도록 한다.
            luckyNumbers.add((int)(Math.random() * 6) + 1);
        }

// 2. 출력하기
        System.out.println("인덱스는 다음과 같습니다.");
        boolean isFirstNum = true; // 맨 처음에 기재되는 번호인지를 파악하는 플래그
        int index = 0;
// luckyNumbers 각 원소를 읽기전용으로 하나씩 꺼내기
        for(int luckyNum : luckyNumbers){
            if(isFirstNum){
                isFirstNum = false; // 맨 처음에는 ", " 문자열을 추가하지 않고 플래그를 내립니다.
            } else {
                // 맨 처음 원소가 아니므로 앞에 ", "문자열 추가
                // 1, 2, 3, ..., 6 꼴로 나오게 함
                System.out.print(", ");
                LogUtil.d(" ");
            }

            System.out.print(luckyNum);
            str[index] ="" + luckyNum;
            index++;
        }


        return str;
    }

    public static void setColorFilter(ImageView imageView , int color, PorterDuff.Mode mode){
        imageView.setColorFilter(color, mode);
    }
    public static InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[ㄱ-ㅣ가-힣\u318D\u119E\u11A2\u2022\u2025\u00B7\uFE55]*$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };


    public static void setTextViewColorPartial(TextView view, String fulltext, String subtext) {
        view.setText("");
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
        str.setSpan(bss, i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(str);

    }

    public static void setTextViewColorPartial(TextView view, String fulltext, String subtext, int color, boolean isLine) {
        view.setText("");
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(isLine)str.setSpan(new UnderlineSpan(), i, i + subtext.length(), 0);
        view.setText(str);

    }
    public static void setTextViewColorPartial(TextSwitcher view, String fulltext, String subtext, int color) {
        view.setText("");
        SpannableStringBuilder ssb = new SpannableStringBuilder(fulltext);
        int i = fulltext.indexOf(subtext);
        ssb.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(ssb);

    }

    public static int convertTimeToMin(int hour, int minute) {
        int second = 0;
        second += (hour * 60);
        second += (minute);
        return second;
    }


    public static void writeToFile(String data) {
        final File file = new File(getTempDir(), "config.txt");

        // Save your stream, don't forget to flush() it before closing it.

        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readToFile() {
        //Get the text file
        File file = new File(getTempDir(), "config.txt");

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException e) {
            return null;
            //You'll need to add proper error handling here
        }
        return text.toString();
    }

    public static final String getTempDir() {
        String folderPath = MyApplication.getApplication().getFilesDir().getAbsolutePath();
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        return folderPath;
    }



    //El 인터벌 조정 체크 되어 있는지

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                MyApplication.get_instance().getResources().getDisplayMetrics());
    }

    public static void resultGraph(String point, ImageView ivProgress, View nonRate) {
        float fStudiedRate = (float) Integer.valueOf(point) / (float) 100;
        float fNonStudiedRate = 1 - fStudiedRate;
        LinearLayout.LayoutParams studiedParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, fNonStudiedRate);
        ivProgress.setLayoutParams(studiedParam);

        LinearLayout.LayoutParams nonStudiedParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, fStudiedRate);
        nonRate.setLayoutParams(nonStudiedParam);
    }

    public static int getTodayTimeUnseTimeType() {
        int nResult = -1;
        Calendar calendar = Calendar.getInstance();
        int nHour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if ((nHour >= 0 && minute >= 0) && (nHour < 7)) {
            nResult = 0;
        } else if ((nHour >= 7 && minute >= 0) && (nHour < 12)) {
            nResult = 1;
        } else if ((nHour >= 12 && minute >= 0) && (nHour < 18)) {
            nResult = 2;
        } else if ((nHour >= 18 && minute >= 0) && (nHour < 24)) {
            nResult = 3;
        }

        return nResult;
    }


    public static int getRandom(int max) {
        return new Random().nextInt((max));
    }


    //포춘쿠키에서 다시 뽑기 버튼을 보일지 안보일지 체크
    public static boolean isViewVisible() {
        boolean isResult = true;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (hour < 9) {        //시간이 9시보다 작으면서
            if ((hour >= 8 && minute >= 55)) {        // 8시 55분보다 크거나 같을때
                isResult = false;
            }
        }
        return isResult;
    }

    public static String twoNum(int t) {
        if (t < 10) {
            return "0" + t;
        } else {
            return "" + t;
        }
    }

    public static String viewRealTimeMeetTime(int timeSec) {
        int h = timeSec / 60 / 60;
        int m = timeSec / 60 % 60;
        int s = timeSec % 60 % 60;

        int day = h / 24;
        int hh = h % 24;
        String timeStr = null;
        if (m > 0) {
            timeStr = twoNum(m) + " : " + twoNum(s);
        } else {
            timeStr = twoNum(m) + " : " + twoNum(s);
        }


        return timeStr;
    }


    /**
     * 랜덤 int 구하기
     *
     * @param min 최소값(이상)
     * @param max 최대값(이하)
     * @return
     */
    public static int random(int type, int min, int max) {
        Random random = new Random();
        int index = 0;
        if (type == 0) //시간
        {
            index = random.nextInt((max - min) + 1) + min;
        } else {    //분
            index = random.nextInt(59);
        }

        return index;
    }

    public static String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);

        return getTime;
    }

    public static long getCurrentTimer(boolean isAdd) {
        Calendar cal = Calendar.getInstance();
        if (isAdd) {
            cal.add(Calendar.MINUTE, 1);
        }
        LogUtil.d(cal.get(Calendar.YEAR) + "," + (cal.get(Calendar.MONTH) + 1) + "," + cal.get(Calendar.DAY_OF_MONTH) + "," + cal.get(Calendar.HOUR_OF_DAY) + "," + cal.get(Calendar.MINUTE));
        return cal.getTimeInMillis();
    }

    public static int getCurrentCalMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        return month;
    }



    public static String getTime() {
        long now = System.currentTimeMillis();
        Date date1 = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = sdf.format(date1);

        return getTime;
    }

    public static void getKeyHash(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(
                    ctx.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
//				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * 현재 디버그모드여부를 리턴
     *
     * @param context
     * @return
     */
    public static boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }


    public static boolean isCheckTimerAMPM() {
        boolean isResult = false;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 18) {
            isResult = true;
        } else {
            isResult = false;
        }
        return isResult;
    }

    public static boolean isScreenOn(Context context) {
        try {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return pm.isInteractive();
            } else {
                return pm.isScreenOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static int getStatusBarHeight(Context ctx) {

        int statusHeight = 0;
        int screenSizeType = (ctx.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK);

        if (screenSizeType != Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");

            if (resourceId > 0) {
                statusHeight = ctx.getResources().getDimensionPixelSize(resourceId);
            }
        }

        return statusHeight;
    }


    public static int updatePad2(String t, int c) {
        int nReturn = 0;
        if (t.equals("PM")) {
            if (c == 12) c = 0;
            nReturn = 12 + c;
        } else {
            nReturn = c;
        }
        return nReturn;
    }

    public static int updatePad(String t, int c) {
        int nReturn = 0;
        if (t.equals("PM")) {
            if (c == 12) c = 0;
            nReturn = 12 + c;
        } else {
            nReturn = c;
        }
        return nReturn;
    }

    public static void sqliteExport(Context context) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//handasoft.mobile.divination_pro/databases//charm_database.db";
                String backupDBPath = "charm_database1.sqlite";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
                if (backupDB.exists()) {
                    Toast.makeText(context, "DB Export Complete!!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        }
    }

    public static String pad(int c) {
        if (c >= 13) {
            if (c - 12 >= 10) {
                return String.valueOf(c - 12);
            } else {
                return "0" + String.valueOf(c - 12);
            }
        } else {
            if (c >= 10) {
                return String.valueOf(c);
            } else {
                return "0" + String.valueOf(c);
            }
        }


    }

    /** Add padding to numbers less than ten */
    public static String getMinutePad(int c) {
        if (c >= 10) {
            return String.valueOf(c);
        } else {
            return "0" + String.valueOf(c);
        }


    }

    public static String version(Context context) {
        String strResult = "";
        try {
            String _pack = context.getPackageName();

            if (_pack == null) {
                _pack = StorePackageInfo.STORE_PACKAGE_NAME;
            }
            strResult = context.getPackageManager().getPackageInfo(_pack, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return strResult;
    }

    public static boolean isServiceRunningCheck(Context context, String serviceName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public static void DeleteDir(String path) {
        File file = new File(path);
        File[] childFileList = file.listFiles();
        for (File childFile : childFileList) {
            if (childFile.isDirectory()) {
                DeleteDir(childFile.getAbsolutePath());     //하위 디렉토리 루프
            } else {
                childFile.delete();    //하위 파일삭제
            }
        }
        file.delete();    //root 삭제
    }

    public static File SaveBitmapToFileCache(Bitmap bitmap, String strFilePath, String filename) {
        String storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        String strPath = storage + "/" + strFilePath + "/";
        File file = new File(strPath);

        if (!file.exists())
            file.mkdirs();

        File fileCacheItem = new File(strPath + filename);
        OutputStream out = null;


        try {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static Uri getUriFromPath(Context context) {
        String fileName = "file:///android_asset/unse_jumsin.jpg";
        Uri fileUri = Uri.parse(fileName);
        String filePath = fileUri.getPath();
        Cursor c = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, "_data = '" + filePath + "'", null, null);
        c.moveToNext();
        int id = c.getInt(c.getColumnIndex("_id"));
        Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

        return uri;
    }

    public static File rawToFile(Context context, InputStream finp, String filename) {
        FileOutputStream fout = null;
        File tmpFile = null;
        byte[] buf = null;
        int cntRead = 0;

        tmpFile = new File(context.getFilesDir() + "/tempbanner");
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }

        buf = new byte[1024 + 1];
        try {
            fout = new FileOutputStream(new File(tmpFile + "/" + filename));
            while (0 < (cntRead = finp.read(buf))) {
                fout.write(buf, 0, cntRead);
            }
        } catch (FileNotFoundException e1) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (Exception e) {
                fout = null;
            }
            try {
                if (finp != null) {
                    finp.close();
                }
            } catch (Exception e) {
                finp = null;
            }
        }
        return new File(tmpFile + "/" + filename);
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenWidth(Activity context) {

        Display display = context.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        }
        return display.getWidth();
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenHeight(Activity context) {

        Display display = context.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        }
        return display.getHeight();
    }

    /**
     * 픽셀을 DP 로 변환하는 메소드.
     * @param px 픽셀
     * @return 픽셀에서 dp 로 변환된 값.
     */
    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }


    public static int convertDpToPixel(double dip, Context context) {

        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) dip, context.getResources().getDisplayMetrics());
        return value;

    }

    public static boolean getOnstore(Context context, String _package) {
        try {
            PackageManager pm = context.getPackageManager();
            List<ApplicationInfo> appList = pm.getInstalledApplications(0);
            ApplicationInfo app = null;
            int nSize = appList.size();
            for (int i = 0; i < nSize; i++) {
                app = appList.get(i);
                if (app.packageName.indexOf(_package) != -1) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }


    public static boolean isNetworkStat(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo lte_4g = manager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
        boolean blte_4g = false;
        if (lte_4g != null)
            blte_4g = lte_4g.isConnected();
        if (mobile != null) {
            if (mobile.isConnected() || wifi.isConnected() || blte_4g)
                return true;
        } else {
            if (wifi.isConnected() || blte_4g)
                return true;
        }

        return false;
    }

    /**
     * EXIF정보를 회전각도로 변환하는 메서드
     *
     * @param exifOrientation EXIF 회전각

     * @return 실제 각도

     */

    public static int exifOrientationToDegrees(int exifOrientation) {

        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }





    public static String replaceNum(String num) {
        int n = Integer.valueOf(num);
        String price = "";
        try {
            NumberFormat nf = NumberFormat.getInstance();
            price = String.valueOf(nf.format(n));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return price;

    }

    public static String getForegroundActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> infoList = manager.getRunningTasks(1);
        String className = null;
        if (infoList.size() > 0) {
            ActivityManager.RunningTaskInfo info = infoList.get(0);
            className = info.topActivity.getClassName();
        }
        return className;
    }

    public static boolean runForground(Context context) {
        //		boolean isRun = false;
        String strPackage = "";
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> proceses = am.getRunningAppProcesses();

        //프로세서 전체를 반복
        for (ActivityManager.RunningAppProcessInfo process : proceses) {
            if (process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                strPackage = process.processName; //package이름과 동일함.
//				Log.d("TEST", strPackage);

                if (strPackage.equals("handasoft.mobile.lovetory")) {
                    return true;
                }
            }
        }

        return false;

    }

    public static String getCurrentFormattedDate(String format) {
        Date dt = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(dt);
    }

    public static String getPad(int n) {
        String str = "";
        if (n < 10) {
            str = "0" + n;
        } else {
            str = "" + n;
        }
        return str;
    }

    public static int getConvertString(String s) {
        int n = 0;
        if (s.indexOf("0") > 0 && s.lastIndexOf("0") < 1) {
            String str = s.substring(1, s.length());
            n = Integer.valueOf(str);
        } else {
            n = Integer.valueOf(s);
        }

        return n;
    }

    public static int getFindIndex(String[] arr, String s) {
        int n = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].toString().trim().contains(s.toString().trim())) {
                n = i;

                return n;
            }
        }
        return n;
    }


    public static int getOsVersion() {

        int version = Build.VERSION.SDK_INT;


        switch (version) {

            case 2:

                Log.d("VERSION", "? 1.1");

                break;

            case 3:

                Log.d("VERSION", "CUPCAKE 1.5");

                break;

            case 4:

                Log.d("VERSION", "DONUT 1.6");

                break;

            case 5:

                Log.d("VERSION", "ECLAIR 2.0");

                break;

            case 6:

                Log.d("VERSION", "ECLAIR 2.0.1");

                break;

            case 7:

                Log.d("VERSION", "ECLAIR 2.1");

                break;

            case 8:

                Log.d("VERSION", "FROYO 2.2");

                break;

            case 9:

                Log.d("VERSION", "GINGERBREAD 2.3");

                break;

            case 10:

                Log.d("VERSION", "GINGERBREAD 2.3.3-2.3.4");

                break;

            case 11:

                Log.d("VERSION", "HONEYCOMB 3.0");

                break;

            case 12:

                Log.d("VERSION", "HONEYCOMB 3.1");

                break;

            case 13:

                Log.d("VERSION", "HONEYCOMB 3.2");

                break;

            case 14:

                Log.d("VERSION", "ICECREAMCAKE 4.0");

                break;

        }

        return version;
    }


    public static String getMyPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if ( ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        String num = tm.getLine1Number();

        if(num!= null){
            num = num.replace("+82", "0");
        }

        return num;
    }
    public static boolean installKakao(Context context){
        PackageInfo pi;
        String packageName = "com.kakao.talk";
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(packageName,  PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean installApp(Context context){
        PackageInfo pi;



        String packageName = context.getPackageName();
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(packageName,  PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;

        }


    }

    /**
     * 한글인지 체크
     * @param context
     * @param str
     * @return
     */
    public static boolean isHangul(Context context,String str){
        boolean isHangle = false;

        isHangle = Pattern.matches("^[가-힝]*$", str);

        return isHangle;
    }
    public InputFilter filterAlphaNum = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    /** 한글만 받기 **/
    public InputFilter filterKor = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[ㄱ-ㅎ가-흐]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    public static boolean isEngNum(Context context, String str){
        boolean isTrue = false;

        isTrue = Pattern.matches("^[a-zA-Z0-9]+$", str);

        return isTrue;
    }
    /**
     * 한글, 영문인지 체크
     * @param context
     * @param str
     * @return
     */
    public static boolean isHanEng(Context context,String str){
        boolean isHangle = false;

        isHangle = Pattern.matches("^[a-zA-Z가-힝]*$", str);

        return isHangle;
    }

    public static boolean isNumber(Context context, String str){
        boolean isHangle = false;

        isHangle = Pattern.matches("^[0-9]*$", str);

        return isHangle;
    }



}
