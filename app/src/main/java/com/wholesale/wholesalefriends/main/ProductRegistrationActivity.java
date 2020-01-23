package com.wholesale.wholesalefriends.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.soundcloud.android.crop.Crop;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.data.ImageFile;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.yalantis.ucrop.UCrop;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;
import net.yazeed44.imagepicker.util.Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mabbas007.tagsedittext.TagsEditText;

public class ProductRegistrationActivity extends GroupActivity implements Picker.PickListener {


    private RelativeLayout btnBack;
    private TextView tvTitle;
    private ImageView ivPhoto;
    private LinearLayout llayoutForNoImg;
    private LinearLayout llayoutFOrNotImg;
    private RelativeLayout photoLayout;
    private ImageView ivSubPhoto01;
    private ImageView ivSubPhoto01Plus;
    private LinearLayout btnSubPhoto01;
    private ImageView ivSubPhoto02;
    private ImageView ivSubPhoto02Plus;
    private LinearLayout btnSubPhoto02;
    private ImageView ivSubPhoto03;
    private ImageView ivSubPhoto03Plus;
    private LinearLayout btnSubPhoto03;
    private ImageView ivSubPhoto04;
    private ImageView ivSubPhoto04Plus;
    private LinearLayout btnSubPhoto04;
    private ImageView ivSubPhoto05;
    private ImageView ivSubPhoto05Plus;
    private LinearLayout btnSubPhoto05;
    private EditText edtProductName;
    private EditText edtProductPrice;
    private Button btnInfo;
    private CheckBox cbCheck;
    private LinearLayout btnCheck;
    private TagsEditText edtProductInfo1;
    private Button btnColor01;
    private Button btnColor02;
    private Button btnColor03;
    private Button btnColor04;
    private Button btnColor05;
    private Button btnColor06;
    private Button btnColor07;
    private Button btnColor08;
    private Button btnColor09;
    private Button btnColor10;
    private Button btnColor11;
    private Button btnColor12;
    private Button btnColor13;
    private Button btnColor14;
    private Button btnColor15;
    private Button btnColor16;
    private Button btnColor17;
    private Button btnColor18;
    private Button btnColor19;
    private Button btnColor20;
    private Button btnColor21;
    private Button btnColor22;
    private Button btnColor23;
    private Button btnColor24;
    private EditText edtProductInfo2;
    private Button btnSize01;
    private Button btnSize02;
    private Button btnSize03;
    private Button btnSize04;
    private Button btnSize05;
    private Button btnSize06;
    private Button btnSize07;
    private Button btnSize08;
    private Button btnSize09;
    private Button btnSize10;
    private Button btnSize11;
    private Button btnSize12;
    private Button btnSize13;
    private EditText edtProductInfo3;
    private Button btnMeterial01;
    private Button btnMeterial02;
    private Button btnMeterial03;
    private Button btnMeterial04;
    private Button btnMeterial05;
    private Button btnMeterial06;
    private Button btnMeterial07;
    private Button btnMeterial08;
    private Button btnMeterial09;
    private Button btnMeterial10;
    private Button btnMeterial11;
    private Button btnMeterial12;
    private Button btnThinick01;
    private Button btnThinick02;
    private Button btnThinick03;
    private Button btnThinick04;
    private Button btnThinick05;
    private Button btnThinick06;
    private Button btnThinick07;
    private Button btnThinick08;
    private Button btnThinick09;
    private Button btnThinick10;
    private Button btnThinick11;
    private Button btnThinick12;
    private ImageView ivRadio01;
    private LinearLayout btnRadio01;
    private ImageView ivRadio02;
    private LinearLayout btnRadio02;
    private ImageView ivRadio03;
    private LinearLayout btnRadio03;
    private ImageView ivRadio04;
    private LinearLayout btnRadio04;
    private ImageView ivRadio05;
    private LinearLayout btnRadio05;
    private ImageView ivRadio06;
    private LinearLayout btnRadio06;
    private ImageView ivRadio07;
    private LinearLayout btnRadio07;
    private ImageView ivRadio08;
    private LinearLayout btnRadio08;
    private ImageView ivRadio09;
    private LinearLayout btnRadio09;
    private ImageView ivRadio10;
    private LinearLayout btnRadio10;
    private ImageView ivRadio11;
    private LinearLayout btnRadio11;
    private ImageView ivRadio12;
    private LinearLayout btnRadio12;
    private ImageView ivRadio13;
    private LinearLayout btnRadio13;
    private LinearLayout btnClear;
    private LinearLayout btnAutoFill01;
    private LinearLayout btnAutoFill02;
    private LinearLayout btnAutoFill03;
    private ImageView ivOpenTypeRadio01;
    private LinearLayout btnOpenTypeRadio01;
    private ImageView ivOpenTypeRadio02;
    private LinearLayout btnOpenTypeRadio02;
    private ImageView ivOpenTypeRadio03;
    private EditText edtOpenDay;
    private LinearLayout btnOpenTypeRadio03;
    private ImageView ivNationalRadio01;
    private LinearLayout btnNationalRadio01;
    private ImageView ivNationalRadio02;
    private LinearLayout btnNationalRadio02;
    private ImageView ivNationalRadio03;
    private LinearLayout btnNationalRadio03;
    private ImageView ivStoryCheck;
    private TextView tvInfo;
    private Button btnOk;

    private Button btnCategory;
    private int[] nSelectPhotoIndex = new int[5];
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
    private TextView tvPhotoRepresentative;
    private TextView tvSubPhotoRepresentative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        ivPhoto = findViewById(R.id.ivPhoto);
        llayoutForNoImg = findViewById(R.id.llayoutForNoImg);
        llayoutFOrNotImg = findViewById(R.id.llayoutFOrNotImg);
        photoLayout = findViewById(R.id.photoLayout);
        ivSubPhoto01 = findViewById(R.id.ivSubPhoto01);
        ivSubPhoto01Plus = findViewById(R.id.ivSubPhoto01Plus);
        btnSubPhoto01 = findViewById(R.id.btnSubPhoto01);
        ivSubPhoto02 = findViewById(R.id.ivSubPhoto02);
        ivSubPhoto02Plus = findViewById(R.id.ivSubPhoto02Plus);
        btnSubPhoto02 = findViewById(R.id.btnSubPhoto02);
        ivSubPhoto03 = findViewById(R.id.ivSubPhoto03);
        ivSubPhoto03Plus = findViewById(R.id.ivSubPhoto03Plus);
        btnSubPhoto03 = findViewById(R.id.btnSubPhoto03);
        ivSubPhoto04 = findViewById(R.id.ivSubPhoto04);
        ivSubPhoto04Plus = findViewById(R.id.ivSubPhoto04Plus);
        btnSubPhoto04 = findViewById(R.id.btnSubPhoto04);
        ivSubPhoto05 = findViewById(R.id.ivSubPhoto05);
        ivSubPhoto05Plus = findViewById(R.id.ivSubPhoto05Plus);
        btnSubPhoto05 = findViewById(R.id.btnSubPhoto05);
        edtProductName = findViewById(R.id.edtProductName);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        btnInfo = findViewById(R.id.btnInfo);
        cbCheck = findViewById(R.id.cbCheck);
        btnCheck = findViewById(R.id.btnCheck);
        edtProductInfo1 = findViewById(R.id.edtProductInfo1);
        btnColor01 = findViewById(R.id.btnColor01);
        btnColor02 = findViewById(R.id.btnColor02);
        btnColor03 = findViewById(R.id.btnColor03);
        btnColor04 = findViewById(R.id.btnColor04);
        btnColor05 = findViewById(R.id.btnColor05);
        btnColor06 = findViewById(R.id.btnColor06);
        btnColor07 = findViewById(R.id.btnColor07);
        btnColor08 = findViewById(R.id.btnColor08);
        btnColor09 = findViewById(R.id.btnColor09);
        btnColor10 = findViewById(R.id.btnColor10);
        btnColor11 = findViewById(R.id.btnColor11);
        btnColor12 = findViewById(R.id.btnColor12);
        btnColor13 = findViewById(R.id.btnColor13);
        btnColor14 = findViewById(R.id.btnColor14);
        btnColor15 = findViewById(R.id.btnColor15);
        btnColor16 = findViewById(R.id.btnColor16);
        btnColor17 = findViewById(R.id.btnColor17);
        btnColor18 = findViewById(R.id.btnColor18);
        btnColor19 = findViewById(R.id.btnColor19);
        btnColor20 = findViewById(R.id.btnColor20);
        btnColor21 = findViewById(R.id.btnColor21);
        btnColor22 = findViewById(R.id.btnColor22);
        btnColor23 = findViewById(R.id.btnColor23);
        btnColor24 = findViewById(R.id.btnColor24);
        edtProductInfo2 = findViewById(R.id.edtProductInfo2);
        btnSize01 = findViewById(R.id.btnSize01);
        btnSize02 = findViewById(R.id.btnSize02);
        btnSize03 = findViewById(R.id.btnSize03);
        btnSize04 = findViewById(R.id.btnSize04);
        btnSize05 = findViewById(R.id.btnSize05);
        btnSize06 = findViewById(R.id.btnSize06);
        btnSize07 = findViewById(R.id.btnSize07);
        btnSize08 = findViewById(R.id.btnSize08);
        btnSize09 = findViewById(R.id.btnSize09);
        btnSize10 = findViewById(R.id.btnSize10);
        btnSize11 = findViewById(R.id.btnSize11);
        btnSize12 = findViewById(R.id.btnSize12);
        btnSize13 = findViewById(R.id.btnSize13);
        edtProductInfo3 = findViewById(R.id.edtProductInfo3);
        btnMeterial01 = findViewById(R.id.btnMeterial01);
        btnMeterial02 = findViewById(R.id.btnMeterial02);
        btnMeterial03 = findViewById(R.id.btnMeterial03);
        btnMeterial04 = findViewById(R.id.btnMeterial04);
        btnMeterial05 = findViewById(R.id.btnMeterial05);
        btnMeterial06 = findViewById(R.id.btnMeterial06);
        btnMeterial07 = findViewById(R.id.btnMeterial07);
        btnMeterial08 = findViewById(R.id.btnMeterial08);
        btnMeterial09 = findViewById(R.id.btnMeterial09);
        btnMeterial10 = findViewById(R.id.btnMeterial10);
        btnMeterial11 = findViewById(R.id.btnMeterial11);
        btnMeterial12 = findViewById(R.id.btnMeterial12);
        btnThinick01 = findViewById(R.id.btnThinick01);
        btnThinick02 = findViewById(R.id.btnThinick02);
        btnThinick03 = findViewById(R.id.btnThinick03);
        btnThinick04 = findViewById(R.id.btnThinick04);
        btnThinick05 = findViewById(R.id.btnThinick05);
        btnThinick06 = findViewById(R.id.btnThinick06);
        btnThinick07 = findViewById(R.id.btnThinick07);
        btnThinick08 = findViewById(R.id.btnThinick08);
        btnThinick09 = findViewById(R.id.btnThinick09);
        btnThinick10 = findViewById(R.id.btnThinick10);
        btnThinick11 = findViewById(R.id.btnThinick11);
        btnThinick12 = findViewById(R.id.btnThinick12);
        ivRadio01 = findViewById(R.id.ivRadio01);
        btnRadio01 = findViewById(R.id.btnRadio01);
        ivRadio02 = findViewById(R.id.ivRadio02);
        btnRadio02 = findViewById(R.id.btnRadio02);
        ivRadio03 = findViewById(R.id.ivRadio03);
        btnRadio03 = findViewById(R.id.btnRadio03);
        ivRadio04 = findViewById(R.id.ivRadio04);
        btnRadio04 = findViewById(R.id.btnRadio04);
        ivRadio05 = findViewById(R.id.ivRadio05);
        btnRadio05 = findViewById(R.id.btnRadio05);
        ivRadio06 = findViewById(R.id.ivRadio06);
        btnRadio06 = findViewById(R.id.btnRadio06);
        ivRadio07 = findViewById(R.id.ivRadio07);
        btnRadio07 = findViewById(R.id.btnRadio07);
        ivRadio08 = findViewById(R.id.ivRadio08);
        btnRadio08 = findViewById(R.id.btnRadio08);
        ivRadio09 = findViewById(R.id.ivRadio09);
        btnRadio09 = findViewById(R.id.btnRadio09);
        ivRadio10 = findViewById(R.id.ivRadio10);
        btnRadio10 = findViewById(R.id.btnRadio10);
        ivRadio11 = findViewById(R.id.ivRadio11);
        btnRadio11 = findViewById(R.id.btnRadio11);
        ivRadio12 = findViewById(R.id.ivRadio12);
        btnRadio12 = findViewById(R.id.btnRadio12);
        ivRadio13 = findViewById(R.id.ivRadio13);
        btnRadio13 = findViewById(R.id.btnRadio13);
        btnClear = findViewById(R.id.btnClear);
        btnAutoFill01 = findViewById(R.id.btnAutoFill01);
        btnAutoFill02 = findViewById(R.id.btnAutoFill02);
        btnAutoFill03 = findViewById(R.id.btnAutoFill03);
        ivOpenTypeRadio01 = findViewById(R.id.ivOpenTypeRadio01);
        btnOpenTypeRadio01 = findViewById(R.id.btnOpenTypeRadio01);
        ivOpenTypeRadio02 = findViewById(R.id.ivOpenTypeRadio02);
        btnOpenTypeRadio02 = findViewById(R.id.btnOpenTypeRadio02);
        ivOpenTypeRadio03 = findViewById(R.id.ivOpenTypeRadio03);
        edtOpenDay = findViewById(R.id.edtOpenDay);
        btnOpenTypeRadio03 = findViewById(R.id.btnOpenTypeRadio03);
        ivNationalRadio01 = findViewById(R.id.ivNationalRadio01);
        btnNationalRadio01 = findViewById(R.id.btnNationalRadio01);
        ivNationalRadio02 = findViewById(R.id.ivNationalRadio02);
        btnNationalRadio02 = findViewById(R.id.btnNationalRadio02);
        ivNationalRadio03 = findViewById(R.id.ivNationalRadio03);
        btnNationalRadio03 = findViewById(R.id.btnNationalRadio03);
        ivStoryCheck = findViewById(R.id.ivStoryCheck);
        tvInfo = findViewById(R.id.tvInfo);
        btnOk = findViewById(R.id.btnOk);
        tvPhotoRepresentative = findViewById(R.id.tvPhotoRepresentative);
        tvSubPhotoRepresentative = findViewById(R.id.tvSubPhotoRepresentative);
        btnCategory = findViewById(R.id.btnCategory);
        tvTitle.setText("상품등록");
        init();


    }

    private void selectColor(String color,int value)
    {
        edtProductInfo1.setText(color);
    }

    private void init() {

        btnColor01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor01.getText().toString(),1);
          }
        });
        btnColor02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor02.getText().toString(),2);

            }
        });
        btnColor03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor03.getText().toString(),3);
            }
        });
        btnColor04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor04.getText().toString(),4);
            }
        });
        btnColor05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor05.getText().toString(),5);
            }
        });
        btnColor06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor06.getText().toString(),6);
            }
        });
        btnColor07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor07.getText().toString(),7);
            }
        });
        btnColor08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor08.getText().toString(),8);
            }
        });
        btnColor09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor09.getText().toString(),9);
            }
        });
        btnColor10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor10.getText().toString(),10);
            }
        });
        btnColor11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor11.getText().toString(),11);
            }
        });
        btnColor12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor12.getText().toString(),12);
            }
        });
        btnColor13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor13.getText().toString(),13);
            }
        });
        btnColor14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor14.getText().toString(),14);
            }
        });
        btnColor15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor15.getText().toString(),15);
            }
        });
        btnColor16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor16.getText().toString(),16);
            }
        });
        btnColor17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor17.getText().toString(),17);
            }
        });
        btnColor18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor18.getText().toString(),18);
            }
        });
        btnColor19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor19.getText().toString(),19);
            }
        });
        btnColor20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor20.getText().toString(),20);
            }
        });
        btnColor21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor21.getText().toString(),21);
            }
        });
        btnColor22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor22.getText().toString(),22);
            }
        });
        btnColor23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor23.getText().toString(),23);
            }
        });
        btnColor24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectColor(btnColor24.getText().toString(),24);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbCheck.isChecked()){
                    cbCheck.setChecked(false);
                }else{
                    cbCheck.setChecked(true);
                }
            }
        });
        edtProductInfo1.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> tags) {

            }

            @Override
            public void onEditingFinished() {

            }
        });
        edtProductInfo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbCheck.setChecked(b);
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductRegistrationActivity.this,CategorySelectActivity.class);
                startActivityForResult(intent,4000);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        llayoutForNoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPhoto.performClick();
            }
        });
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nSelectPhotoIndex[0] = 0;
                selectPhotoDialog();
            }
        });

        btnSubPhoto01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nSelectPhotoIndex[0] = 0;
                selectPhotoDialog();
            }
        });
        btnSubPhoto02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nSelectPhotoIndex[1] = 1;
                selectPhotoDialog();
            }
        });

        btnSubPhoto03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nSelectPhotoIndex[2] = 2;
                selectPhotoDialog();
            }
        });
        btnSubPhoto04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nSelectPhotoIndex[3] = 3;
                selectPhotoDialog();
            }
        });

        btnSubPhoto05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nSelectPhotoIndex[4] = 4;
                selectPhotoDialog();
            }
        });
    }

    private void selectPhotoDialog() {
        ArrayList<String> arrRoomName = new ArrayList<>();
        arrRoomName.add(getString(R.string.camera));
        arrRoomName.add(getString(R.string.album));
        String[] items = arrRoomName.toArray(new String[arrRoomName.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(ProductRegistrationActivity.this);
        builder.setCancelable(false);
        builder.setTitle("상가 선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // 저장소(파일) 접근 권한 확인
                    if ((ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                            || (ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                            || (ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

                        ActivityCompat.requestPermissions(ProductRegistrationActivity.this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA);
                    } else {
                        takePhoto();
                    }
                } else {
                    if ((ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(ProductRegistrationActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY);
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

    private void pickImages() {

        new Picker.Builder(this, ProductRegistrationActivity.this, R.style.AppTheme_NoActionBar)
                .setPickMode(Picker.PickMode.MULTIPLE_IMAGES)
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                Uri photoUri = FileProvider.getUriForFile(this,
                        getPackageName() + ".provider", tempFile);
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
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(ProductRegistrationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || !ActivityCompat.shouldShowRequestPermissionRationale(ProductRegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                || !ActivityCompat.shouldShowRequestPermissionRationale(ProductRegistrationActivity.this, Manifest.permission.CAMERA)) {

                            if (ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                    && (ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                                final CommonAlertDialog d = new CommonAlertDialog(ProductRegistrationActivity.this, false, true);
                                d.setTitle(getString(R.string.dialog_alert));
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
                            } else if (ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                final CommonAlertDialog d = new CommonAlertDialog(ProductRegistrationActivity.this, false, true);
                                d.setTitle(getString(R.string.dialog_alert));
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
                            } else if (ContextCompat.checkSelfPermission(ProductRegistrationActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                final CommonAlertDialog d = new CommonAlertDialog(ProductRegistrationActivity.this, false, true);
                                d.setTitle(getString(R.string.dialog_alert));
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
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(ProductRegistrationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            final CommonAlertDialog d = new CommonAlertDialog(ProductRegistrationActivity.this, false, true);
                            d.setTitle(getString(R.string.dialog_alert));
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
                        }

                        switch (profileList.size()) {
                            case 0:
                                tvPhotoRepresentative.setVisibility(View.GONE);
                                tvSubPhotoRepresentative.setVisibility(View.GONE);
                                llayoutForNoImg.setVisibility(View.VISIBLE);
                                ivSubPhoto01Plus.setVisibility(View.VISIBLE);
                                ivSubPhoto02Plus.setVisibility(View.VISIBLE);
                                ivSubPhoto03Plus.setVisibility(View.VISIBLE);
                                ivSubPhoto04Plus.setVisibility(View.VISIBLE);
                                ivSubPhoto05Plus.setVisibility(View.VISIBLE);

                                break;
                            case 1:
                                tvPhotoRepresentative.setVisibility(View.GONE);
                                tvSubPhotoRepresentative.setVisibility(View.GONE);
                                llayoutForNoImg.setVisibility(View.GONE);
                                ivSubPhoto01Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivPhoto);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivSubPhoto01);
                                break;
                            case 2:
                                tvPhotoRepresentative.setVisibility(View.GONE);
                                tvSubPhotoRepresentative.setVisibility(View.GONE);
                                llayoutForNoImg.setVisibility(View.GONE);
                                ivSubPhoto01Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivPhoto);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivSubPhoto01);

                                ivSubPhoto02Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(1).getResizePath()).into(ivSubPhoto02);
                                break;
                            case 3:
                                tvPhotoRepresentative.setVisibility(View.GONE);
                                tvSubPhotoRepresentative.setVisibility(View.GONE);
                                llayoutForNoImg.setVisibility(View.GONE);
                                ivSubPhoto01Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivPhoto);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivSubPhoto01);

                                ivSubPhoto02Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(1).getResizePath()).into(ivSubPhoto02);
                                ivSubPhoto03Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(2).getResizePath()).into(ivSubPhoto03);
                                break;
                            case 4:
                                tvPhotoRepresentative.setVisibility(View.GONE);
                                tvSubPhotoRepresentative.setVisibility(View.GONE);
                                llayoutForNoImg.setVisibility(View.GONE);
                                ivSubPhoto01Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivPhoto);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivSubPhoto01);

                                ivSubPhoto02Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(1).getResizePath()).into(ivSubPhoto02);
                                ivSubPhoto03Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(2).getResizePath()).into(ivSubPhoto03);
                                ivSubPhoto04Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(3).getResizePath()).into(ivSubPhoto04);
                                break;
                            case 5:
                                tvPhotoRepresentative.setVisibility(View.GONE);
                                tvSubPhotoRepresentative.setVisibility(View.GONE);
                                llayoutForNoImg.setVisibility(View.GONE);
                                ivSubPhoto01Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivPhoto);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(0).getResizePath()).into(ivSubPhoto01);

                                ivSubPhoto02Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(1).getResizePath()).into(ivSubPhoto02);
                                ivSubPhoto03Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(2).getResizePath()).into(ivSubPhoto03);
                                ivSubPhoto04Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(3).getResizePath()).into(ivSubPhoto04);
                                ivSubPhoto05Plus.setVisibility(View.GONE);
                                Glide.with(ProductRegistrationActivity.this).load(profileList.get(4).getResizePath()).into(ivSubPhoto05);
                                break;
                        }
                    }
                    break;

                case CAMERA: {
                    final Intent datas = data;
                    final ProgressDialog progressDialog = new ProgressDialog(ProductRegistrationActivity.this, R.style.WholesaleeProgress);
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
                                    path = Util.getPath(ProductRegistrationActivity.this, mImageCaptureUri);
                                } else {
                                    path = Util.getPath(ProductRegistrationActivity.this, datas.getData());
                                }
                                if (path == null) {
                                    Toast.makeText(ProductRegistrationActivity.this, getString(R.string.toast_broken_imagefile), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                ArrayList<ImageFile> imageFileArrayList = new ArrayList<>();
                                ImageFile imageFile = new ImageFile();
                                imageFile.setIsBridgeShoot(1);
                                imageFile.setOriginalPath(path);
                                imageFileArrayList.add(imageFile);

                                progressDialog.dismiss();
                                Intent intent = new Intent(ProductRegistrationActivity.this, ImageEditActivity.class);
                                intent.putExtra("image", imageFileArrayList);
                                intent.putExtra("profile", true);
                                startActivityForResult(intent, IMAGECROP);
                            } catch (Exception e) {
                                Toast.makeText(ProductRegistrationActivity.this, getString(R.string.toast_broken_camera), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }, 1000);
                }
                break;
                case PICK_FROM_CAMERA2: {

                    Uri photoUri = Uri.fromFile(tempFile);
                    mImageCaptureUri = photoUri;
                    final Intent datas = data;
                    final ProgressDialog progressDialog = new ProgressDialog(ProductRegistrationActivity.this, R.style.WholesaleeProgress);
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
                                    path = Util.getPath(ProductRegistrationActivity.this, mImageCaptureUri);
                                } else {
                                    path = Util.getPath(ProductRegistrationActivity.this, datas.getData());
                                }
                                if (path == null) {
                                    Toast.makeText(ProductRegistrationActivity.this, getString(R.string.toast_broken_imagefile), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                ArrayList<ImageFile> imageFileArrayList = new ArrayList<>();
                                ImageFile imageFile = new ImageFile();
                                imageFile.setIsBridgeShoot(1);
                                imageFile.setOriginalPath(path);
                                imageFileArrayList.add(imageFile);

                                progressDialog.dismiss();
                                Intent intent = new Intent(ProductRegistrationActivity.this, ImageEditActivity.class);
                                intent.putExtra("image", imageFileArrayList);
                                intent.putExtra("profile", true);
                                startActivityForResult(intent, IMAGECROP);
                            } catch (Exception e) {
                                Toast.makeText(ProductRegistrationActivity.this, getString(R.string.toast_broken_camera), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }, 1000);
                }
                break;
                case 4000:
                    break;

            }
        }
    }

    /**
     * 폴더 및 파일 만들기
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
        uCrop.withAspectRatio(500, 600)
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
        try {
            if (result != null && UCrop.getOutput(result) != null) {
                contentUri = UCrop.getOutput(result);
                if (contentUri != null) {
                    mCurrentPhotoPath = contentUri.getPath();
//                    uiUpdateHandler.sendEmptyMessage(IMAGE_SAVE);
                } else {
                    Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Throwable e) {
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

            Intent intent = new Intent(ProductRegistrationActivity.this, ImageEditActivity.class);
            intent.putExtra("image", imageFileArrayList);
            intent.putExtra("profile", true);
            startActivityForResult(intent, IMAGECROP);
        }


    }

    @Override
    public void onCancel(boolean isCamera) {

    }
}
