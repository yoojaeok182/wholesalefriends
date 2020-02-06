package com.wholesale.wholesalefriends.main.join;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.soundcloud.android.crop.Crop;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.ImageEditActivity;
import com.wholesale.wholesalefriends.main.LoginActivity;
import com.wholesale.wholesalefriends.main.wholesale_market.Main2Activity;
import com.wholesale.wholesalefriends.main.retail_market.MainActivity;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ImageFile;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.view.CircleImageView;
import com.yalantis.ucrop.UCrop;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;
import net.yazeed44.imagepicker.util.Util;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JoinStep4Activity extends GroupActivity implements Picker.PickListener{

    private static final int CAMERA = 1;
    private static final int GALLERY = 2;
    private static final int IMAGECROP = 99;
    Uri mImageCaptureUri = null;
    private Boolean isCamera = false;
    private static File tempFile;
    public static final int PICK_FROM_ALBUM2 = 122;
    public static final int PICK_FROM_CAMERA2 = 222;
    private String mCurrentPhotoPath;
    private Uri contentUri;
    private ArrayList<ImageFile> profileList;

    private static final int IMAGE_SAVE = 98;
    private static final int IMAGE_SAVE_FINISH = 97;


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private CircleImageView ivPhoto;

    private ImageView ivPhotoCamera;
    private EditText edtStoreName;
    private TextView tvErrorMsg01;
    private EditText edtCeoeName;
    private TextView tvErrorMsg02;
    private EditText edtId;
    private TextView tvErrorMsg03;
    private EditText edtPwd;
    private TextView tvErrorMsg04;
    private EditText edtPwd2;
    private TextView tvErrorMsg05;
    private EditText edtStoreRegistNumber;
    private TextView tvErrorMsg06;
    private Button btnOk;
    private int level = 0;
    private int store_type = -1;
    private int store_onoﬀ = -1;
    private String store_addr;
    private String strStore;
    private String strFloor;
    private String strRoomNo;
    private int nWholesaleMode = -1;
    private String strSiteName;
    private String strSiteUrl;


    private int store_id;

    private static JoinStep4Activity instance;
    private EditText edtPhoneNumber;
    private TextView tvErrorMsg07;
    private ImageView ivTerms01Check;
    private TextView tvTerms01;
    private LinearLayout btnTerms01;
    private ImageView ivTerms02Check;
    private TextView tvTerms02;
    private LinearLayout btnTerms02;
    private TextView tvErrorMsg08;

    private boolean isChecked01, isChecked02;
    private Bitmap store_photo;

    private RelativeLayout btnPhoto;
    public static JoinStep4Activity getInstance() {
        return instance;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_step_04);
        instance = this;
        Intent intent = getIntent();
        if (intent.hasExtra("store_id")) {
            store_id = intent.getExtras().getInt("store_id");
        }

        if (intent.hasExtra("level")) {
            level = intent.getExtras().getInt("level");
        }

        if (intent.hasExtra("store_onoﬀ")) {
            store_onoﬀ = intent.getExtras().getInt("store_onoﬀ");
        }
        if (intent.hasExtra("store_type")) {
            store_type = intent.getExtras().getInt("store_type");
        }

        if (intent.hasExtra("store")) {
            strStore = intent.getExtras().getString("store");
        }

        if (intent.hasExtra("floor")) {
            strFloor = intent.getExtras().getString("floor");
        }
        if (intent.hasExtra("roomNo")) {
            strRoomNo = intent.getExtras().getString("roomNo");
        }
        if (intent.hasExtra("store_addr")) {
            store_addr = intent.getExtras().getString("store_addr");
        }
        if (intent.hasExtra("site_name")) {
            strSiteName = intent.getExtras().getString("site_name");
        }
        if (intent.hasExtra("site_url")) {
            strSiteUrl = intent.getExtras().getString("site_url");
        }

        if (intent.hasExtra("wholesale")) {
            nWholesaleMode = intent.getExtras().getInt("wholesale");
        }

        btnPhoto = findViewById(R.id.btnPhoto);
        ivTerms01Check = findViewById(R.id.ivTerms01Check);
        tvTerms01 = findViewById(R.id.tvTerms01);
        btnTerms01 = findViewById(R.id.btnTerms01);
        ivTerms02Check = findViewById(R.id.ivTerms02Check);
        tvTerms02 = findViewById(R.id.tvTerms02);
        btnTerms02 = findViewById(R.id.btnTerms02);
        tvErrorMsg08 = findViewById(R.id.tvErrorMsg08);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        tvErrorMsg07 = findViewById(R.id.tvErrorMsg07);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivPhoto = findViewById(R.id.ivPhoto);
        ivPhotoCamera = findViewById(R.id.ivPhotoCamera);
        edtStoreName = findViewById(R.id.edtStoreName);
        tvErrorMsg01 = findViewById(R.id.tvErrorMsg01);
        edtCeoeName = findViewById(R.id.edtCeoeName);
        tvErrorMsg02 = findViewById(R.id.tvErrorMsg02);
        edtId = findViewById(R.id.edtId);
        tvErrorMsg03 = findViewById(R.id.tvErrorMsg03);
        edtPwd = findViewById(R.id.edtPwd);
        tvErrorMsg04 = findViewById(R.id.tvErrorMsg04);
        edtPwd2 = findViewById(R.id.edtPwd2);
        tvErrorMsg05 = findViewById(R.id.tvErrorMsg05);
        edtStoreRegistNumber = findViewById(R.id.edtStoreRegistNumber);
        tvErrorMsg06 = findViewById(R.id.tvErrorMsg06);
        btnOk = findViewById(R.id.btnOk);


        profileList = new ArrayList<>();


        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhotoDialog();
            }
        });


        ivTerms01Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivTerms01Check.setBackgroundResource(R.drawable.check_on);
                isChecked01 = true;
            }
        });
        ivTerms02Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivTerms02Check.setBackgroundResource(R.drawable.check_on);
                isChecked02 = true;
            }
        });

        btnTerms01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivTerms01Check.performClick();
            }
        });
        btnTerms02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivTerms02Check.performClick();
            }
        });
        tvTerms01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        tvTerms02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPhoto.performClick();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (store_type == 1) {
            tvTitle.setText("도매 회원가입");
        } else {
            tvTitle.setText("소매 회원가입");
        }

        edtId.setFilters(new InputFilter[] {com.wholesale.wholesalefriends.module.util.Util.filter1});



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput()) {
                    ImageFile imageFile = null;
                    if(profileList != null && profileList.size() > 0) {
                        imageFile = profileList.get(0);
                    }

                    final CommonAlertDialog dg = new CommonAlertDialog(JoinStep4Activity.this, true, true);
                    dg.setTitle("회원가입");
                    dg.setMessage("입력하신 내용으로 회원가입을\n진행합니다.");
                    dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (dg.isOk()) {
                                String ceoName = edtCeoeName.getText().toString().trim();
                                String strId = edtId.getText().toString().trim();
                                String strPwd = edtPwd.getText().toString().trim();
                                String strStoreName = edtStoreName.getText().toString().trim();
                                String strStoreRegistNumber = edtStoreRegistNumber.getText().toString().trim();
                                String strPhoneNumber = edtPhoneNumber.getText().toString().trim();

                                if (store_type == 1) {
                                    //도매
                                    API.join(JoinStep4Activity.this, store_type,  level,  ceoName,  strId,  strPwd,
                                            strPhoneNumber,  strStoreName,  strStoreRegistNumber,  store_id+"",
                                             store_addr,  store_photo,  "",
                                            "", "",  "",  resultHandler, errHandler );
                                } else {
                                    //소매
                                    API.join(JoinStep4Activity.this, store_type,  level,  ceoName,  strId,  strPwd,
                                            strPhoneNumber,  strStoreName,  strStoreRegistNumber,  store_id+"",
                                            store_addr,  store_photo,  store_onoﬀ+"",
                                            strSiteName, strSiteUrl,  "",  resultHandler, errHandler );
                                }
                            }
                        }
                    });
                    dg.show();
                }
            }
        });

        edtStoreRegistNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });

        edtCeoeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtStoreName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtPwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });

        edtPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkRegistBtn();
            }
        });
    }

    private void selectPhotoDialog(){
        ArrayList<String> arrRoomName = new ArrayList<>();
        arrRoomName.add(getString(R.string.camera));
        arrRoomName.add(getString(R.string.album));
        String[] items = arrRoomName.toArray(new String[arrRoomName.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(JoinStep4Activity.this);
        builder.setCancelable(false);
        builder.setTitle("상가 선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              if(which ==0){
                  // 저장소(파일) 접근 권한 확인
                  if ((ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                          || (ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                          || (ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

                      ActivityCompat.requestPermissions(JoinStep4Activity.this,
                              new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA);
                  } else {
                      takePhoto();
                  }
              }else{
                  if ((ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                      ActivityCompat.requestPermissions(JoinStep4Activity.this,
                              new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY);
                  } else {
                      pickImages();
                  }
              }
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //the user clicked on Cancel
            }
        });
        builder.show();
    }

    private boolean checkInput() {
        String ceoName = edtCeoeName.getText().toString().trim();
        String strId = edtId.getText().toString().trim();
        String strPwd = edtPwd.getText().toString().trim();
        String strPwd2 = edtPwd2.getText().toString().trim();
        String strStoreName = edtStoreName.getText().toString().trim();
        String strStoreRegistNumber = edtStoreRegistNumber.getText().toString().trim();
        String strPhoneNumber = edtPhoneNumber.getText().toString().trim();
        tvErrorMsg01.setVisibility(View.GONE);
        tvErrorMsg02.setVisibility(View.GONE);
        tvErrorMsg03.setVisibility(View.GONE);
        tvErrorMsg04.setVisibility(View.GONE);
        tvErrorMsg05.setVisibility(View.GONE);
        tvErrorMsg06.setVisibility(View.GONE);
        tvErrorMsg07.setVisibility(View.GONE);
        tvErrorMsg08.setVisibility(View.GONE);
        if ((strStoreName != null && strStoreName.length() == 0)) {
            tvErrorMsg01.setVisibility(View.VISIBLE);
            return false;
        }

        if ((ceoName != null && ceoName.length() == 0)) {
            tvErrorMsg02.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strId != null && strId.length() == 0)||(strId.length()<4)) {
            tvErrorMsg03.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strPwd != null && strPwd.length() == 0)||strPwd.length()<6) {
            tvErrorMsg04.setVisibility(View.VISIBLE);
            return false;
        }

        if ((strPwd2 != null && strPwd2.length() == 0)||strPwd.length()<6) {
            tvErrorMsg05.setVisibility(View.VISIBLE);
            return false;
        }


        if ((strStoreRegistNumber != null && strStoreRegistNumber.length() == 0)) {
            tvErrorMsg06.setVisibility(View.VISIBLE);
            return false;
        }
        if ((strPhoneNumber != null && strPhoneNumber.length() == 0)) {
            tvErrorMsg07.setVisibility(View.VISIBLE);
            return false;
        }


        if (!isChecked01 ||!isChecked02 ) {
            tvErrorMsg08.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private void checkRegistBtn() {
        String ceoName = edtCeoeName.getText().toString();
        String strId = edtId.getText().toString();
        String strPwd = edtPwd.getText().toString();
        String strPwd2 = edtPwd2.getText().toString();
        String strStoreName = edtStoreName.getText().toString();
        String strStoreRegistNumber = edtStoreRegistNumber.getText().toString();
        String strPhoneNumber = edtPhoneNumber.getText().toString();
        if ((ceoName != null && ceoName.length() == 0) || (strId != null && strId.length() == 0) || (strPwd != null && strPwd.length() == 0) ||
                (strPwd2 != null && strPwd2.length() == 0) || (strStoreName != null && strStoreName.length() == 0) || (strStoreRegistNumber != null && strStoreRegistNumber.length() == 0) ||
                (strPhoneNumber != null && strPhoneNumber.length() == 0)) {
            btnOk.setBackgroundResource(R.drawable.btn_02);
            btnOk.setEnabled(false);
        } else {
            btnOk.setEnabled(true);
            btnOk.setBackgroundResource(R.drawable.btn_02_on);
        }

    }

    private Handler resultHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
                    SharedPreference.putSharedPreference(JoinStep4Activity.this, Constant.CommonKey.user_no,jsonObject.getInt("user_id"));
                    SharedPreference.putSharedPreference(JoinStep4Activity.this, Constant.CommonKey.user_id,edtId.getText().toString());
                    SharedPreference.putSharedPreference(JoinStep4Activity.this, Constant.CommonKey.user_pwd,edtPwd.getText().toString());
                    SharedPreference.putSharedPreference(JoinStep4Activity.this, Constant.CommonKey.store_type,store_type);
                    SharedPreference.putSharedPreference(JoinStep4Activity.this, Constant.CommonKey.level,level);

                    if(!jsonObject.isNull("store_id")){
                        int  store_id = jsonObject.getInt("store_id");
                        SharedPreference.putSharedPreference(JoinStep4Activity.this, Constant.CommonKey.store_id,store_id);
                    }
                    Intent intent = null;
                    if(store_type ==2){
                        intent = new Intent(JoinStep4Activity.this, MainActivity.class);
                    }else{
                        intent = new Intent(JoinStep4Activity.this, Main2Activity.class);
                    }
                    startActivity(intent);

                    if(JoinStep1Activity.getInstance()!=null)JoinStep1Activity.getInstance().finish();
                    if(JoinStep2Activity.getInstance()!=null)JoinStep2Activity.getInstance().finish();
                    if(JoinStep3Activity.getInstance()!=null)JoinStep3Activity.getInstance().finish();
                    if(JoinStep4Activity.getInstance()!=null)JoinStep4Activity.getInstance().finish();
                    if(JoinStepAddrInputActivity.getInstance()!=null)JoinStepAddrInputActivity.getInstance().finish();
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };
    private Handler errHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(!jsonObject.getBoolean("result")){

                    if(jsonObject.getString("error")!=null && jsonObject.getString("error").length()>0){
                        final CommonAlertDialog dg = new CommonAlertDialog(JoinStep4Activity.this,false,true);
                        dg.setTitle("계정 정보 확인");
                        dg.setMessage(jsonObject.getString("error"));
                        dg.show();

                    }
                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    };

    private void pickImages() {

        new Picker.Builder(this, JoinStep4Activity.this, R.style.AppTheme_NoActionBar)
                .setPickMode(Picker.PickMode.SINGLE_IMAGE)
                .build()
                .startActivity();
    }
    private void takePhoto() {
        isCamera = true;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            /**
             *  안드로이드 OS 누가 버전 이후부터는 file:// URI 의 노출을 금지로 FileUriExposedException 발생
             *  Uri 를 FileProvider 도 감싸 주어야 합니다.
             *
             *  참고 자료 http://programmar.tistory.com/4 , http://programmar.tistory.com/5
             */
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                Uri photoUri = FileProvider.getUriForFile(this,
                        getPackageName()+".provider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PICK_FROM_CAMERA2);

            } else {

                Uri photoUri = Uri.fromFile(tempFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PICK_FROM_CAMERA2);

            }
        }
    }


    /**
     * 권한획득 여부를 받아와서 기능을 동작할것인지, 권환 획득을 위한 안내를 할것인지 결정
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA: {
                if (grantResults.length > 0) {
                    //권한을 얻었다면
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        takePhoto();
                    } else {
                        // 사용자가 권한설정창 다시 보지 않기를 눌렀다면 알람다이얼로그를 띄워줌
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(JoinStep4Activity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || !ActivityCompat.shouldShowRequestPermissionRationale(JoinStep4Activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                || !ActivityCompat.shouldShowRequestPermissionRationale(JoinStep4Activity.this, android.Manifest.permission.CAMERA)) {

                            if (ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                    && (ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                                final CommonAlertDialog d = new CommonAlertDialog(JoinStep4Activity.this,false,true);
                                d.setTitle(  getString(R.string.dialog_alert));
                                d.setMessage(getString(R.string.dialog_request_camera_storage_permission));
                                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        if (d.isOk() == true) {
                                            final Intent i = new Intent();
                                            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            i.addCategory(Intent.CATEGORY_DEFAULT);
                                            i.setData(Uri.parse("package:" + getPackageName()));
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                            startActivity(i);
                                        }
                                    }
                                });
                                d.show();
                            } else if (ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                final CommonAlertDialog d = new CommonAlertDialog(JoinStep4Activity.this,false,true);
                                d.setTitle(  getString(R.string.dialog_alert));
                                d.setMessage(getString(R.string.dialog_request_storage_permission));
                                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        if (d.isOk() == true) {
                                            final Intent i = new Intent();
                                            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            i.addCategory(Intent.CATEGORY_DEFAULT);
                                            i.setData(Uri.parse("package:" + getPackageName()));
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                            startActivity(i);
                                        }
                                    }
                                });
                                d.show();
                            } else if (ContextCompat.checkSelfPermission(JoinStep4Activity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                final CommonAlertDialog d = new CommonAlertDialog(JoinStep4Activity.this,false,true);
                                d.setTitle(  getString(R.string.dialog_alert));
                                d.setMessage(getString(R.string.dialog_request_camera_permission));
                                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        if (d.isOk() == true) {
                                            final Intent i = new Intent();
                                            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            i.addCategory(Intent.CATEGORY_DEFAULT);
                                            i.setData(Uri.parse("package:" + getPackageName()));
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                            startActivity(i);
                                        }
                                    }
                                });
                                d.show();
                            }
                        }
                    }
                }
                return;
            }
            case GALLERY: {
                if (grantResults.length > 0) {
                    //권한을 얻었다면
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        pickImages();
                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(JoinStep4Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            final CommonAlertDialog d = new CommonAlertDialog(JoinStep4Activity.this,false,true);
                            d.setTitle(  getString(R.string.dialog_alert));
                            d.setMessage(getString(R.string.dialog_request_storage_permission));
                            d.setCancelable(false);
                            d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    if (d.isOk() == true) {
                                        final Intent i = new Intent();
                                        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        i.addCategory(Intent.CATEGORY_DEFAULT);
                                        i.setData(Uri.parse("package:" + getPackageName()));
                                        startActivity(i);
                                    }
                                }
                            });
                            d.show();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGECROP:
                    profileList.clear();
                    ArrayList<ImageFile> imageFiles = (ArrayList<ImageFile>) data.getSerializableExtra("image");
                    if (imageFiles != null && imageFiles.size() > 0) {
                        for (int i = 0; i < imageFiles.size(); i++) {
                            profileList.add(imageFiles.get(i));
                            Glide.with(JoinStep4Activity.this).asBitmap().load(profileList.get(0).getResizePath()).listener(new RequestListener<Bitmap>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                    store_photo = resource;
                                    return false;
                                }
                            }).into(ivPhoto);
                        }

                    }
                    break;

                case CAMERA:
                {
                    final Intent datas = data;
                    final ProgressDialog progressDialog = new ProgressDialog(JoinStep4Activity.this, R.style.WholesaleeProgress);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    if (!progressDialog.isShowing()) {
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.dialog_thread_progress);
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String path = null;
                                if (datas == null || datas.getData() == null) {
                                    path = Util.getPath(JoinStep4Activity.this, mImageCaptureUri);
                                } else {
                                    path = Util.getPath(JoinStep4Activity.this, datas.getData());
                                }
                                if (path == null) {
                                    Toast.makeText(JoinStep4Activity.this, getString(R.string.toast_broken_imagefile), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                ArrayList<ImageFile> imageFileArrayList = new ArrayList<>();
                                ImageFile imageFile = new ImageFile();
                                imageFile.setIsBridgeShoot(1);
                                imageFile.setOriginalPath(path);
                                imageFileArrayList.add(imageFile);

                                progressDialog.dismiss();
                                Intent intent = new Intent(JoinStep4Activity.this, ImageEditActivity.class);
                                intent.putExtra("image", imageFileArrayList);
                                intent.putExtra("profile", true);
                                startActivityForResult(intent, IMAGECROP);
                            } catch (Exception e) {
                                Toast.makeText(JoinStep4Activity.this, getString(R.string.toast_broken_camera), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }, 1000);
                }
                break;
                case PICK_FROM_CAMERA2:
                {

                    Uri photoUri = Uri.fromFile(tempFile);
                    mImageCaptureUri = photoUri;
                    final Intent datas = data;
                    final ProgressDialog progressDialog = new ProgressDialog(JoinStep4Activity.this, R.style.WholesaleeProgress);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    if (!progressDialog.isShowing()) {
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.dialog_thread_progress);
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String path = null;
                                if (datas == null || datas.getData() == null) {
                                    path = Util.getPath(JoinStep4Activity.this, mImageCaptureUri);
                                } else {
                                    path = Util.getPath(JoinStep4Activity.this, datas.getData());
                                }
                                if (path == null) {
                                    Toast.makeText(JoinStep4Activity.this, getString(R.string.toast_broken_imagefile), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                ArrayList<ImageFile> imageFileArrayList = new ArrayList<>();
                                ImageFile imageFile = new ImageFile();
                                imageFile.setIsBridgeShoot(1);
                                imageFile.setOriginalPath(path);
                                imageFileArrayList.add(imageFile);

                                progressDialog.dismiss();
                                Intent intent = new Intent(JoinStep4Activity.this, ImageEditActivity.class);
                                intent.putExtra("image", imageFileArrayList);
                                intent.putExtra("profile", true);
                                startActivityForResult(intent, IMAGECROP);
                            } catch (Exception e) {
                                Toast.makeText(JoinStep4Activity.this, getString(R.string.toast_broken_camera), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }, 1000);
                }
                break;

            }
        }
    }
    /**
     *  폴더 및 파일 만들기
     */
    private File createImageFile() throws IOException {

        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String url = "IMG_" + String.valueOf(System.currentTimeMillis());
        // 이미지가 저장될 파일 이름 ( blackJin )
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera", url);
        if (!storageDir.exists()) storageDir.mkdirs();

        // 빈 파일 생성
        File image = File.createTempFile(url, ".jpg", storageDir);

        return image;
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        String destinationFileName = "IMG_" + String.valueOf(System.currentTimeMillis());
        UCrop uCrop = UCrop.of(source, Uri.fromFile(new File(getCacheDir(), destinationFileName)));
        uCrop = advancedConfig(uCrop);
        uCrop.withAspectRatio(500, 600 )
                .start(this);


    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();

        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(100);
        options.setHideBottomControls(true);
        options.setFreeStyleCropEnabled(false);


        return uCrop.withOptions(options);
    }


    private void handleCropResult(@NonNull Intent result) {
        try{
            if(result!=null && UCrop.getOutput(result)!=null){
                contentUri = UCrop.getOutput(result);
                if (contentUri != null) {
                    mCurrentPhotoPath = contentUri.getPath();
//                    uiUpdateHandler.sendEmptyMessage(IMAGE_SAVE);
                } else {
                    Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }


    }

    @Override
    public void onPickedSuccessfully(ArrayList<ImageEntry> images, boolean isEdit) {
        ArrayList<ImageFile> imageFileArrayList = new ArrayList<>();
        if (images != null && images.size() > 0) {
            ImageFile imageFile = new ImageFile();
            if (images.get(0).path.contains("gif") || images.get(0).path.contains("GIF")) {
                Toast.makeText(this, getString(R.string.toast_not_use_gif), Toast.LENGTH_SHORT).show();
                return;
            }
            imageFile.setOriginalPath(images.get(0).path);
            imageFileArrayList.add(imageFile);

            Intent intent = new Intent(JoinStep4Activity.this, ImageEditActivity.class);
            intent.putExtra("image", imageFileArrayList);
            intent.putExtra("profile", true);
            startActivityForResult(intent, IMAGECROP);
        }


    }

    @Override
    public void onCancel(boolean isCamera) {

    }
}
