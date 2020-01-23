package com.wholesale.wholesalefriends.module;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.dialog.AlertDialog;
import com.wholesale.wholesalefriends.main.dialog.LoadingDialog;

import org.json.JSONObject;

import java.io.File;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Created by Handa-dev5 on 2018-01-04.
 */

public class RetrofitModel {

    private Context mContext;
    private Retrofit mRetrofit;
    private OkHttpClient client;
    private LoadingDialog loadingDialog;
    private Handler _resultHandler = null;
    private Handler _errorHandler = null;


    private String method;
    private String upload_file;
    private String api;
    private String _newURL = null;
    private String _newAPI = null;
    private boolean _isLoading = true;
    private boolean _isSSL = false;
    public void setResultHandler(Handler handler){
        _resultHandler = handler;
    }
    public void setErrorHandler(Handler handler){
        _errorHandler = handler;
    }


    private ArrayList<String> _key = new ArrayList<String>();
    private ArrayList<String> _values = new ArrayList<String>();
    private ArrayList<Bitmap> _bitmapDatas = new ArrayList<Bitmap>();
    private ArrayList<File> _fileDatas = new ArrayList<File>();


    //일반 text 파라매터 추가
    public void addParam(String param, String value){
        _key.add(param);
        _values.add(value);
    }

    public void addParam(String parm,Bitmap bm){
        upload_file = parm;
        _bitmapDatas.add(bm);
    }
    //비트맵 파라매터 추가
    public void addBitmap(Bitmap bm){
        _bitmapDatas.add(bm);
    }
    public void setBitmap(Bitmap bm, int idx){
        for(int i=0; i<=idx; i++){
            if(i >= _bitmapDatas.size()){
                _bitmapDatas.add(null);
            }
        }
        _bitmapDatas.set(idx, bm);
    }

    //파일 파라매터 추가
    public void addFile(File file) {
        _fileDatas.add(file);
    }
    public void setFile(File file, int idx) {
        for(int i=0; i<=idx; i++){
            if(i >= _fileDatas.size()){
                _fileDatas.add(null);
            }
        }
        _fileDatas.set(idx, file);
    }

    //로딩 설정
    public void setLoading(boolean b){
        _isLoading = b;
    }

    //URL 커스텀 설정
    public void setURL(String s){
        _newURL = s;
    }

    //API 커스텀 설정
    public void setAPI(String s){
        _newAPI = s;
    }


    public RetrofitModel(Context context){

        mContext = context;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(_isSSL) {
            client = getSSLOkHttpClient();
        } else {
            client = getOkHttpClient();
        }

        if(context instanceof GroupActivity){
            ((GroupActivity)context).setOnDestroyListener(new GroupActivity.OnDestroyListener() {
                @Override
                public void onDestroy() {
                    dismissDialog();
                }
            });
        }
    }


    //업로드 형식 설정 멀티파트에 포스트 방식
    private interface UploadInterface{
        @Multipart
        @POST
        Call<String> upload(
                @PartMap Map<String, RequestBody> params,
                @Part List<MultipartBody.Part> fileParams,
                @Url String apiUrl
        );
    }

    //API 호출 하기
    public void callBackHttp(String _method) {

        if(_isLoading) {
            loadingDialog = new LoadingDialog(mContext);
            loadingDialog.setCancelable(false);
            loadingDialog.show();
        }

        method = _method;

        //RSA 보안키 등록


        //프로토콜 설정
        String protocal = "http://";
        if (_isSSL) {
            protocal = "https://";
        }

        //URl 주소 설정
        String urlStr;
        urlStr = protocal + Constant.DOMAIN.url + "/";
        if(_newURL != null) {
            urlStr = protocal + _newURL;
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(urlStr)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        upload();
    }

   //API에 맞추어 메소드 작성
    public void upload(){

        final HashMap<String, RequestBody> params = new HashMap<>();
        for(int i = 0; i < _key.size(); i++) {
            RequestBody value = RequestBody.create(MediaType.parse("text/plain"), _values.get(i));
            params.put(_key.get(i), value);
        }
        ArrayList<MultipartBody.Part> fileParams = new ArrayList<>();
        if(_fileDatas.size() > 0) {
            for(int i = 0; i < _fileDatas.size(); i++) {
                if(_fileDatas.get(i) != null) {
                    RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), _fileDatas.get(i));
                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(upload_file+"[" + i + "]", _fileDatas.get(i).getName(), mFile);
                    fileParams.add(fileToUpload);
                }
            }
        }

        api = Constant.DOMAIN.api;
        if(_newAPI != null) {
            api = _newAPI;
        }

        //비트맵이 있다면 비트맵을 순차적으로 파일화 시킨후 콜 진행
        if(_bitmapDatas.size() > 0) {
            BitmapSaveToFileTask bitmapSaveToFileTask = new BitmapSaveToFileTask(mContext, _bitmapDatas);
            bitmapSaveToFileTask.setTaskListener(new BitmapSaveToFileTask.TaskListener() {
                @Override public void onFinish(ArrayList<File> results) {
                    ArrayList<MultipartBody.Part> fileParams = new ArrayList<>();
                    if(results.size() > 0) {
                        for(int i = 0; i < results.size(); i++) {
                            if(results.get(i) != null) {
                                RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), results.get(i));
                                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("upload_file[" + i + "]", results.get(i).getName(), mFile);
                                fileParams.add(fileToUpload);
                            }
                        }
                    }
                    call(params, fileParams, api);
                }
            });
            bitmapSaveToFileTask.convert();
        } else {
            call(params, fileParams, api);
        }
    }


    //Retrofit Call
    private void call(HashMap<String, RequestBody> params, ArrayList<MultipartBody.Part> fileParams ,String api) {
        UploadInterface uploadInterface = mRetrofit.create(UploadInterface.class);
        Call<String> call = uploadInterface.upload(
                params,
                fileParams,
                api);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call,
                                   Response<String> response) {
                //콘텍스트가 엑티비티 이면서 살아있을때만 다이얼로그 캔슬
                dismissDialog();

                try {
                    JSONObject json = new JSONObject(response.body());
                    final Message msg = new Message();
                    msg.obj = json;
                    if (json.getBoolean("result")) {
                        //성공
                        if (_resultHandler != null) {
                            _resultHandler.sendMessage(msg);
                        }
                    } else {
                        //실패
                        if (_errorHandler != null) {
                            _errorHandler.sendMessage(msg);
                        }else if(_isLoading){
                            _errorHandler = new Handler(Looper.getMainLooper()){
                                public void handleMessage(Message msg){
                                    JSONObject json = (JSONObject)msg.obj;
                                    try{
                                        if(!json.isNull("errmsg") && !json.getString("errmsg").equals("")){
                                            final AlertDialog alert = new AlertDialog(mContext, json.getString("errmsg"), false);
                                            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                @Override
                                                public void onDismiss(DialogInterface dialog) {
                                                    if(alert.isOk()){

                                                    }
                                                }
                                            });
                                            alert.show();

                                        }
                                    }catch(Exception e){}
                                }
                            };
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Handa", "HttpResult Passing Error");
                    if(_errorHandler != null) {
                        _errorHandler.sendEmptyMessage(0);
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(loadingDialog != null && loadingDialog.isShowing()) {
                    dismissDialog();
                }
                Log.e("Handa", t.getMessage());
                if(_errorHandler != null) {
                    _errorHandler.sendEmptyMessage(0);
                }
            }
        });
    }

    /**
     * 이런 이유 때문에 OkHttpClient 를 직접 생성하여 사용 하고 있습니다.

     처음에는 OkHttpClient 를 모든 API 호출시 새로 생성하도록 하였습니다. 헌데 TestCode 가 200회가 넘어가면 File IO 를 너무 많이 사용했다는 오류가 계속적으로 발생하였습니다.

     이 오류가 단순히 File IO 가 많아서 라는 메세지 때문에 처음에는 Database 에 대한 오류인 줄 알고 Memory Cache 작업과 테스트코드 개선작업을 하였으나 정상 동작이 되지 않았습니다. (테스트 코드 1회에 평균 2번의 API 통신과 2회의 DB 처리를 합니다.)

     그 와중에 기존의 테스트 코드는 정상 동작하는 것을 보고 Retrofit2 작업을 진행한 branch 만의 문제임을 깨달았습니다.

     현재는 OkhttpClient.Builder 를 통해 생성한 1개의 OkhttpClient 만을 재사용하도록 변경하였습니다.
     * @return
     */

    //HttpClient 생성
    public OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15 , TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(interceptor).build();
        return client;
    }

    //SSL HttpClient 생성
    public OkHttpClient getSSLOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient();

            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .connectTimeout(15 , TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //다이얼로그 취소
    private void dismissDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            if(mContext != null && !((Activity)mContext).isFinishing()) {
                loadingDialog.dismiss();
            }
        }
    }
}
