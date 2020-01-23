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
import android.os.Message;
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
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.BuildSearchData;
import com.wholesale.wholesalefriends.main.data.CategoryLIstData;
import com.wholesale.wholesalefriends.main.data.CategoryListResponse;
import com.wholesale.wholesalefriends.main.data.ImageFile;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.main.join.JoinStep3Activity;
import com.wholesale.wholesalefriends.main.join.JoinStep4Activity;
import com.wholesale.wholesalefriends.module.API;
import com.wholesale.wholesalefriends.module.AppData;
import com.wholesale.wholesalefriends.module.SharedPreference;
import com.wholesale.wholesalefriends.module.util.LogUtil;
import com.yalantis.ucrop.UCrop;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;
import net.yazeed44.imagepicker.util.Util;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Button[] btnColors;

    private TagsEditText edtProductInfo2;
    private Button[]btnSizes;
    private TagsEditText edtProductInfo3;
    private Button[] btnMaterials;
    private Button[] btnThinicks;
    private Button[] btnClothInfo2s;
    private Button[] btnClothInfo3s;
    private Button[] btnClothInfo4s;

    private LinearLayout[] btnWashingInfos;
    private TextView[] tvWashingInfos;

    private EditText edtDescript;

    private ImageView[] ivRadios;
    private LinearLayout[] btnRadios;
    private LinearLayout btnClear;
    private LinearLayout[] btnAutoFills;
    private ImageView[] ivOpenTypeRadios;
    private LinearLayout[] btnOpenTypeRadios;
    private ImageView[] ivNationalRadios;
    private LinearLayout[] btnNationalRadios;

    private EditText edtOpenDay;
    private ImageView ivStoryCheck;
    private LinearLayout btnKakaoStory;
    private TextView tvInfo;
    private Button btnOk;

    private Button btnCategory;
    private String strValueCategory;
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
    private Bitmap[] bmList;

    private int nCode_value;

    private String strColorValue ="";
    private  String strSizeValue ="";
    private String strMaterialValue ="";
    private ArrayList<String> arrColorName = new ArrayList<>();
    private ArrayList<String> arrSizeName = new ArrayList<>();
    private ArrayList<String> arrMeterialName = new ArrayList<>();

    private int cloth_info_1;
    private int cloth_info_2;
    private int cloth_info_3;
    private int cloth_info_4;
    private int styleInfo;
    private  int nOpenOption =0;
    private int nNationalSelect =1;
    private int nKakaoOpen;

    private String store_id;

    private String strWashingInfo="";

    private static final int IMAGE_SAVE = 98;
    private static final int IMAGE_SAVE_FINISH = 97;
    private TextView tvPhotoRepresentative;
    private TextView tvSubPhotoRepresentative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_registration);


        Intent intent = getIntent();
        if(intent.hasExtra(Constant.CommonKey.store_id)){
            store_id = intent.getExtras().getString(Constant.CommonKey.store_id);
        }
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
        btnSizes = new Button[]{
                findViewById(R.id.btnColor01),
                findViewById(R.id.btnColor02),
                findViewById(R.id.btnColor03),
                findViewById(R.id.btnColor04),
                findViewById(R.id.btnColor05),
                findViewById(R.id.btnColor06),
                findViewById(R.id.btnColor07),
                findViewById(R.id.btnColor08),
                findViewById(R.id.btnColor09),
                findViewById(R.id.btnColor10),
                findViewById(R.id.btnColor11),
                findViewById(R.id.btnColor12),
                findViewById(R.id.btnColor13),
                findViewById(R.id.btnColor14),
                findViewById(R.id.btnColor15),
                findViewById(R.id.btnColor16),
                findViewById(R.id.btnColor17),
                findViewById(R.id.btnColor18),
                findViewById(R.id.btnColor19),
                findViewById(R.id.btnColor20),
                findViewById(R.id.btnColor21),
                findViewById(R.id.btnColor22),
                findViewById(R.id.btnColor23),
                findViewById(R.id.btnColor24),
        };

        edtProductInfo2 = findViewById(R.id.edtProductInfo2);

        btnSizes = new Button[]{
                findViewById(R.id.btnSize01),
                findViewById(R.id.btnSize02),
                findViewById(R.id.btnSize03),
                findViewById(R.id.btnSize04),
                findViewById(R.id.btnSize05),
                findViewById(R.id.btnSize06),
                findViewById(R.id.btnSize07),
                findViewById(R.id.btnSize08),
                findViewById(R.id.btnSize09),
                findViewById(R.id.btnSize10),
                findViewById(R.id.btnSize11),
                findViewById(R.id.btnSize12),
                findViewById(R.id.btnSize13),
        };

        edtProductInfo3 = findViewById(R.id.edtProductInfo3);
        btnMaterials = new Button[]{
                findViewById(R.id.btnMeterial01),
                findViewById(R.id.btnMeterial02),
                findViewById(R.id.btnMeterial03),
                findViewById(R.id.btnMeterial04),
                findViewById(R.id.btnMeterial05),
                findViewById(R.id.btnMeterial06),
                findViewById(R.id.btnMeterial07),
                findViewById(R.id.btnMeterial08),
                findViewById(R.id.btnMeterial09),
                findViewById(R.id.btnMeterial10),
                findViewById(R.id.btnMeterial11),
                findViewById(R.id.btnMeterial12),
        };

        btnThinicks = new Button[]{
                findViewById(R.id.btnThinick01),
                findViewById(R.id.btnThinick02),
                findViewById(R.id.btnThinick03),
        };
        btnClothInfo2s = new Button[]{
                findViewById(R.id.btnThinick04),
                findViewById(R.id.btnThinick05),
                findViewById(R.id.btnThinick06),
        };

        btnClothInfo3s = new Button[]{
                findViewById(R.id.btnThinick07),
                findViewById(R.id.btnThinick08),
                findViewById(R.id.btnThinick09),
                findViewById(R.id.btnThinick10),
        };
        btnClothInfo4s = new Button[]{
                findViewById(R.id.btnThinick11),
                findViewById(R.id.btnThinick12),
        };

        btnWashingInfos = new LinearLayout[]{
                findViewById(R.id.btnWashingInfo01),
                findViewById(R.id.btnWashingInfo02),
                findViewById(R.id.btnWashingInfo03),
                findViewById(R.id.btnWashingInfo04),
                findViewById(R.id.btnWashingInfo05),
                findViewById(R.id.btnWashingInfo06),
                findViewById(R.id.btnWashingInfo07),
                findViewById(R.id.btnWashingInfo08),

        };

        tvWashingInfos = new TextView[]{
                findViewById(R.id.tvWashingInfo01),
                findViewById(R.id.tvWashingInfo02),
                findViewById(R.id.tvWashingInfo03),
                findViewById(R.id.tvWashingInfo04),
                findViewById(R.id.tvWashingInfo05),
                findViewById(R.id.tvWashingInfo06),
                findViewById(R.id.tvWashingInfo07),
                findViewById(R.id.tvWashingInfo08),
        };

        edtDescript = findViewById(R.id.edtDescript);
        ivRadios = new ImageView[]{
                findViewById(R.id.ivRadio01),
                findViewById(R.id.ivRadio02),
                findViewById(R.id.ivRadio03),
                findViewById(R.id.ivRadio04),
                findViewById(R.id.ivRadio05),
                findViewById(R.id.ivRadio06),
                findViewById(R.id.ivRadio07),
                findViewById(R.id.ivRadio08),
                findViewById(R.id.ivRadio09),
                findViewById(R.id.ivRadio10),
                findViewById(R.id.ivRadio11),
                findViewById(R.id.ivRadio12),
                findViewById(R.id.ivRadio13),



        };
        btnRadios = new LinearLayout[]{
                findViewById(R.id.btnRadio01),
                findViewById(R.id.btnRadio02),
                findViewById(R.id.btnRadio03),
                findViewById(R.id.btnRadio04),
                findViewById(R.id.btnRadio05),
                findViewById(R.id.btnRadio06),
                findViewById(R.id.btnRadio07),
                findViewById(R.id.btnRadio08),
                findViewById(R.id.btnRadio09),
                findViewById(R.id.btnRadio10),
                findViewById(R.id.btnRadio11),
                findViewById(R.id.btnRadio12),
                findViewById(R.id.btnRadio13),

        };
        btnAutoFills= new LinearLayout[]{
                findViewById(R.id.btnAutoFill01),
                findViewById(R.id.btnAutoFill02),
                findViewById(R.id.btnAutoFill03),
        };

        ivOpenTypeRadios = new ImageView[]{
                findViewById(R.id.ivOpenTypeRadio01),
                findViewById(R.id.ivOpenTypeRadio02),
                findViewById(R.id.ivOpenTypeRadio03),

        };
        btnOpenTypeRadios = new LinearLayout[]{
                findViewById(R.id.btnOpenTypeRadio01),
                findViewById(R.id.btnOpenTypeRadio02),
                findViewById(R.id.btnOpenTypeRadio03),
        };

        ivNationalRadios = new ImageView[]{
                findViewById(R.id.ivNationalRadio01),
                findViewById(R.id.ivNationalRadio02),
                findViewById(R.id.ivNationalRadio03),

        };
        btnNationalRadios  = new LinearLayout[]{
                findViewById(R.id.btnNationalRadio01),
                findViewById(R.id.btnNationalRadio02),
                findViewById(R.id.btnNationalRadio03),
        };
        btnClear = findViewById(R.id.btnClear);


        edtOpenDay = findViewById(R.id.edtOpenDay);

        btnKakaoStory = findViewById(R.id.btnKakaoStory);
        ivStoryCheck = findViewById(R.id.ivStoryCheck);
        tvInfo = findViewById(R.id.tvInfo);
        btnOk = findViewById(R.id.btnOk);
        tvPhotoRepresentative = findViewById(R.id.tvPhotoRepresentative);
        tvSubPhotoRepresentative = findViewById(R.id.tvSubPhotoRepresentative);
        btnCategory = findViewById(R.id.btnCategory);
        tvTitle.setText("상품등록");
        init();


    }

    //
    private void initSelectColor(){
        for(int i=0; i<btnColors.length;i++){
            Button btn = btnColors[i];
            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_color_1_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_color_2_select);
                    break;
                case 2:
                    btn.setBackgroundResource(R.drawable.btn_color_3_select);
                    break;
                case 3:
                    btn.setBackgroundResource(R.drawable.btn_color_4_select);
                    break;
                case 4:
                    btn.setBackgroundResource(R.drawable.btn_color_5_select);
                    break;
                case 5:
                    btn.setBackgroundResource(R.drawable.btn_color_6_select);
                    break;
                case 6:
                    btn.setBackgroundResource(R.drawable.btn_color_7_select);
                    break;
                case 7:
                    btn.setBackgroundResource(R.drawable.btn_color_8_select);
                    break;
                case 8:
                    btn.setBackgroundResource(R.drawable.btn_color_9_select);
                    break;
                case 9:
                    btn.setBackgroundResource(R.drawable.btn_color_10_select);
                    break;
                case 10:
                    btn.setBackgroundResource(R.drawable.btn_color_11_select);
                    break;
                case 11:
                    btn.setBackgroundResource(R.drawable.btn_color_12_select);
                    break;
                case 12:
                    btn.setBackgroundResource(R.drawable.btn_color_13_select);
                    break;
                case 13:
                    btn.setBackgroundResource(R.drawable.btn_color_14_select);
                    break;
                case 14:
                    btn.setBackgroundResource(R.drawable.btn_color_15_select);
                    break;
                case 15:
                    btn.setBackgroundResource(R.drawable.btn_color_16_select);
                    break;
                case 16:
                    btn.setBackgroundResource(R.drawable.btn_color_17_select);
                    break;
                case 17:
                    btn.setBackgroundResource(R.drawable.btn_color_18_select);
                    break;
                case 18:
                    btn.setBackgroundResource(R.drawable.btn_color_19_select);
                    break;
                case 19:
                    btn.setBackgroundResource(R.drawable.btn_color_20_select);
                    break;
                case 20:
                    btn.setBackgroundResource(R.drawable.btn_color_21_select);
                    break;
                case 21:
                    btn.setBackgroundResource(R.drawable.btn_color_22_select);
                    break;
                case 22:
                    btn.setBackgroundResource(R.drawable.btn_color_23_select);
                    break;
                case 23:
                    btn.setBackgroundResource(R.drawable.btn_color_24_select);
                    break;
            }
        }
    }
    private void selectColor(View view,String color,int value)
    {
        boolean isDuplate =false;
        edtProductInfo1.setText(color);
        switch (value){
            case 1:
                view.setBackgroundResource(R.drawable.btn_color_1_on);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.btn_color_2_on);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.btn_color_3_on);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.btn_color_4_on);
                break;
            case 5:
                view.setBackgroundResource(R.drawable.btn_color_5_on);
                break;
            case 6:
                view.setBackgroundResource(R.drawable.btn_color_6_on);
                break;
            case 7:
                view.setBackgroundResource(R.drawable.btn_color_7_on);
                break;
            case 8:
                view.setBackgroundResource(R.drawable.btn_color_8_on);
                break;
            case 9:
                view.setBackgroundResource(R.drawable.btn_color_9_on);
                break;
            case 10:
                view.setBackgroundResource(R.drawable.btn_color_10_on);
                break;
            case 11:
                view.setBackgroundResource(R.drawable.btn_color_11_on);
                break;
            case 12:
                view.setBackgroundResource(R.drawable.btn_color_12_on);
                break;
            case 13:
                view.setBackgroundResource(R.drawable.btn_color_13_on);
                break;
            case 14:
                view.setBackgroundResource(R.drawable.btn_color_14_on);
                break;
            case 15:
                view.setBackgroundResource(R.drawable.btn_color_15_on);
                break;
            case 16:
                view.setBackgroundResource(R.drawable.btn_color_16_on);
                break;
            case 17:
                view.setBackgroundResource(R.drawable.btn_color_17_on);
                break;
            case 18:
                view.setBackgroundResource(R.drawable.btn_color_18_on);
                break;
            case 19:
                view.setBackgroundResource(R.drawable.btn_color_19_on);
                break;
            case 20:
                view.setBackgroundResource(R.drawable.btn_color_20_on);
                break;
            case 21:
                view.setBackgroundResource(R.drawable.btn_color_21_on);
                break;
            case 22:
                view.setBackgroundResource(R.drawable.btn_color_22_on);
                break;
            case 23:
                view.setBackgroundResource(R.drawable.btn_color_23_on);
                break;
            case 24:
                view.setBackgroundResource(R.drawable.btn_color_24_on);
                break;
        }


    }

    private void initSelectSize(){
        for(int i=0; i<btnSizes.length;i++){
            Button btn = btnSizes[i];

            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_size_01_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_size_02_select);
                    break;
                case 2:
                    btn.setBackgroundResource(R.drawable.btn_size_03_select);
                    break;
                case 3:
                    btn.setBackgroundResource(R.drawable.btn_size_04_select);
                    break;
                case 4:
                    btn.setBackgroundResource(R.drawable.btn_size_05_select);
                    break;
                case 5:
                    btn.setBackgroundResource(R.drawable.btn_size_06_select);
                    break;
                case 6:
                    btn.setBackgroundResource(R.drawable.btn_size_07_select);
                    break;
                case 7:
                    btn.setBackgroundResource(R.drawable.btn_size_08_select);
                    break;
                case 8:
                    btn.setBackgroundResource(R.drawable.btn_size_09_select);
                    break;
                case 9:
                    btn.setBackgroundResource(R.drawable.btn_size_10_select);
                    break;
                case 10:
                    btn.setBackgroundResource(R.drawable.btn_size_11_select);
                    break;
                case 11:
                    btn.setBackgroundResource(R.drawable.btn_size_12_select);
                    break;
                case 12:
                    btn.setBackgroundResource(R.drawable.btn_size_13_select);
                    break;
            }
        }
    }
    private void selectSize(View view,String color,int value)
    {
        edtProductInfo2.setText(color);
        switch (value){
            case 1:
                view.setBackgroundResource(R.drawable.btn_size1_on);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.btn_size2_on);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.btn_size3_on);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.btn_size4_on);
                break;
            case 5:
                view.setBackgroundResource(R.drawable.btn_size5_on);
                break;
            case 6:
                view.setBackgroundResource(R.drawable.btn_size6_on);
                break;
            case 7:
                view.setBackgroundResource(R.drawable.btn_size7_on);
                break;
            case 8:
                view.setBackgroundResource(R.drawable.btn_size8_on);
                break;
            case 9:
                view.setBackgroundResource(R.drawable.btn_size9_on);
                break;
            case 10:
                view.setBackgroundResource(R.drawable.btn_size10_on);
                break;
            case 11:
                view.setBackgroundResource(R.drawable.btn_size11_on);
                break;
            case 12:
                view.setBackgroundResource(R.drawable.btn_size12_on);
                break;
            case 13:
                view.setBackgroundResource(R.drawable.btn_size13_on);
                break;
        }


    }
    private void initSelectMaterial(){
        for(int i=0; i<btnMaterials.length;i++){
            Button btn = btnMaterials[i];

            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_meterial_01_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_meterial_02_select);
                    break;
                case 2:
                    btn.setBackgroundResource(R.drawable.btn_meterial_03_select);
                    break;
                case 3:
                    btn.setBackgroundResource(R.drawable.btn_meterial_04_select);
                    break;
                case 4:
                    btn.setBackgroundResource(R.drawable.btn_meterial_05_select);
                    break;
                case 5:
                    btn.setBackgroundResource(R.drawable.btn_meterial_06_select);
                    break;
                case 6:
                    btn.setBackgroundResource(R.drawable.btn_meterial_07_select);
                    break;
                case 7:
                    btn.setBackgroundResource(R.drawable.btn_meterial_08_select);
                    break;
                case 8:
                    btn.setBackgroundResource(R.drawable.btn_meterial_09_select);
                    break;
                case 9:
                    btn.setBackgroundResource(R.drawable.btn_meterial_10_select);
                    break;
                case 10:
                    btn.setBackgroundResource(R.drawable.btn_meterial_11_select);
                    break;
                case 11:
                    btn.setBackgroundResource(R.drawable.btn_meterial_12_select);
                    break;
            }
        }
    }
    private void selectMeterial(View view,String color,int value)
    {
        boolean isDuplate =false;
        edtProductInfo3.setText(color);

        switch (value){
            case 1:
                view.setBackgroundResource(R.drawable.btn_meterial1_on);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.btn_meterial2_on);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.btn_meterial3_on);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.btn_meterial4_on);
                break;
            case 5:
                view.setBackgroundResource(R.drawable.btn_meterial5_on);
                break;
            case 6:
                view.setBackgroundResource(R.drawable.btn_meterial6_on);
                break;
            case 7:
                view.setBackgroundResource(R.drawable.btn_meterial7_on);
                break;
            case 8:
                view.setBackgroundResource(R.drawable.btn_meterial8_on);
                break;
            case 9:
                view.setBackgroundResource(R.drawable.btn_meterial9_on);
                break;
            case 10:
                view.setBackgroundResource(R.drawable.btn_meterial10_on);
                break;
            case 11:
                view.setBackgroundResource(R.drawable.btn_meterial11_on);
                break;
            case 12:
                view.setBackgroundResource(R.drawable.btn_meterial12_on);
                break;
        }


    }

    private void selectClothInfo2(View view,int pos){
        for(int i=0; i<btnClothInfo2s.length;i++) {
            Button btn = btnClothInfo2s[i];
            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_thickness_04_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_thickness_05_select);
                    break;
                case 3:
                    btn.setBackgroundResource(R.drawable.btn_thickness_06_select);
                    break;
            }
        }
        switch (pos){
            case 0:
                view.setBackgroundResource(R.drawable.btn_thickness4_on);
                break;
            case 1:
                view.setBackgroundResource(R.drawable.btn_thickness5_on);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.btn_thickness6_on);
                break;
        }
        cloth_info_2 = (pos+1);

    }

    private void selectClothInfo3(View view,int pos){
        for(int i=0; i<btnClothInfo3s.length;i++) {
            Button btn = btnClothInfo3s[i];
            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_thickness_07_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_thickness_08_select);
                    break;
                case 3:
                    btn.setBackgroundResource(R.drawable.btn_thickness_09_select);
                    break;
                case 4:
                    btn.setBackgroundResource(R.drawable.btn_thickness_10_select);
                    break;
            }
        }
        switch (pos){
            case 0:
                view.setBackgroundResource(R.drawable.btn_thickness7_on);
                break;
            case 1:
                view.setBackgroundResource(R.drawable.btn_thickness8_on);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.btn_thickness9_on);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.btn_thickness10_on);
                break;
        }
        cloth_info_3 = (pos+1);

    }

    private void selectClothInfo4(View view,int pos){
        for(int i=0; i<btnClothInfo4s.length;i++) {
            Button btn = btnClothInfo4s[i];
            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_thickness_11_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_thickness_12_select);
                    break;
            }
        }
        switch (pos){
            case 0:
                view.setBackgroundResource(R.drawable.btn_thickness11_on);
                break;
            case 1:
                view.setBackgroundResource(R.drawable.btn_thickness12_on);
                break;
        }
        cloth_info_4 = (pos+1);

    }
    private void selectThickness(View view,int pos){
        for(int i=0; i<btnThinicks.length;i++) {
            Button btn = btnThinicks[i];
            switch (i){
                case 0:
                    btn.setBackgroundResource(R.drawable.btn_thickness_01_select);
                    break;
                case 1:
                    btn.setBackgroundResource(R.drawable.btn_thickness_02_select);
                    break;
                case 3:
                    btn.setBackgroundResource(R.drawable.btn_thickness_03_select);
                    break;
            }
        }
        switch (pos){
            case 0:
                view.setBackgroundResource(R.drawable.btn_thickness1_on);
                break;
            case 1:
                view.setBackgroundResource(R.drawable.btn_thickness2_on);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.btn_thickness3_on);
                break;
        }

        cloth_info_1 = (pos+1);
    }
    private void initSelectWashingInfo(){
        for(int i=0; i<tvWashingInfos.length;i++) {
            TextView btn = tvWashingInfos[i];
            btn.setTextColor(getResources().getColor(R.color.color_text_04));
        }
    }
    private void selectWashingInfo(TextView view,int pos){

        view.setTextColor(getResources().getColor(R.color.color_text_07));

        strWashingInfo =strWashingInfo+(pos+1)+",";
    }

    private void selectRadio(ImageView view,int pos){
        for(int i=0; i<ivRadios.length;i++) {
            ImageView btn = ivRadios[i];
            btn.setBackgroundResource(R.drawable.btn_radio);
        }
        view.setBackgroundResource(R.drawable.btn_radio_on);

        styleInfo = (pos+1);
    }

    private void selectAutoFillText(int pos){
        String strText = "";
        switch (pos){
            case 0:
                strText = "";
                break;
            case 1:
                strText = "";
                break;
            case 2:
                strText = "";
                break;
        }

        edtDescript.setText(strText);
    }

    private void selectOpenDay(ImageView view,int pos){
        for(int i=0; i<ivOpenTypeRadios.length;i++) {
            ImageView btn = ivOpenTypeRadios[i];
            btn.setBackgroundResource(R.drawable.btn_radio_on);
        }
        view.setBackgroundResource(R.drawable.btn_radio_on);

        nOpenOption = (pos+1);

    }
    private void selectNational(ImageView view,int pos){
        for(int i=0; i<ivNationalRadios.length;i++) {
            ImageView btn = ivNationalRadios[i];
            btn.setBackgroundResource(R.drawable.btn_radio_on);
        }
        view.setBackgroundResource(R.drawable.btn_radio_on);

        nNationalSelect = (pos+1);
    }

    private void init() {
        String[] colorNames = getResources().getStringArray(R.array.color_code_name);
        String[] sizeNames = getResources().getStringArray(R.array.size_code_name);
        String[] materialNames = getResources().getStringArray(R.array.material_code_name);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        btnKakaoStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nKakaoOpen ==0){
                    ivStoryCheck.setBackgroundResource(R.drawable.check_on);
                    nKakaoOpen = 1;
                }else{
                    ivStoryCheck.setBackgroundResource(R.drawable.check_default);
                    nKakaoOpen = 0;
                }
            }
        });

        for(int i=0; i<btnNationalRadios.length;i++){
            LinearLayout btn = btnNationalRadios[i];
            ImageView iv = ivNationalRadios[i];
            if(i==0) iv.setBackgroundResource(R.drawable.btn_radio_on);
            else iv.setBackgroundResource(R.drawable.btn_radio);
            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectNational(ivNationalRadios[finalI],finalI);
                }
            });
        }

        for(int i=0; i<btnOpenTypeRadios.length;i++){
            LinearLayout btn = btnOpenTypeRadios[i];
            ImageView iv = ivOpenTypeRadios[i];
            if(i==0) iv.setBackgroundResource(R.drawable.btn_radio_on);
            else iv.setBackgroundResource(R.drawable.btn_radio);
            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOpenDay(ivOpenTypeRadios[finalI],finalI);
                }
            });
        }

        for(int i=0; i<btnRadios.length;i++){
            LinearLayout btn = btnRadios[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectRadio(ivRadios[finalI],finalI);
                }
            });
        }
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDescript.setText("");
                edtDescript.setHint("상세설명을 입력해주세요.");
            }
        });

        for(int i=0; i<btnAutoFills.length;i++){
            LinearLayout btn = btnAutoFills[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectAutoFillText(finalI);
                }
            });
        }
        initSelectWashingInfo();
        for(int i=0; i<btnWashingInfos.length;i++){
            LinearLayout btn = btnWashingInfos[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectWashingInfo(tvWashingInfos[finalI],finalI);
                }
            });
        }

        for(int i=0; i<btnClothInfo2s.length;i++){
            Button btn = btnClothInfo2s[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectClothInfo2(btnClothInfo2s[finalI],finalI);
                }
            });
        }

        for(int i=0; i<btnClothInfo3s.length;i++){
            Button btn = btnClothInfo3s[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectClothInfo3(btnClothInfo3s[finalI],finalI);
                }
            });
        }

        for(int i=0; i<btnClothInfo4s.length;i++){
            Button btn = btnClothInfo4s[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectClothInfo4(btnClothInfo4s[finalI],finalI);
                }
            });
        }

        for(int i=0; i<btnThinicks.length;i++){
            Button btn = btnThinicks[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectThickness(btnThinicks[finalI],finalI);
                }
            });
        }
        initSelectMaterial();
//******************************************************************************************************
        for(int i=0; i<btnMaterials.length;i++){
            Button btn = btnMaterials[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectMeterial(btnMaterials[finalI],materialNames[finalI],(finalI+1));
                }
            });
        }

        edtProductInfo3.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> tags) {
                LogUtil.d(Arrays.toString(tags.toArray()));
                arrMeterialName= (ArrayList<String>) tags;
                initSelectMaterial();
                if(arrMeterialName!=null && arrMeterialName.size()>0){
                    for(int i=0; i<arrMeterialName.size();i++){
                        for(int j=0;j<materialNames.length;j++){
                            if(arrMeterialName.get(i).equals(materialNames[j])){
                                selectSize(btnMaterials[j],materialNames[j],(j+1));

                            }
                        }
                    }
                }

            }

            @Override
            public void onEditingFinished() {

            }
        });
        //********************************************************************************************
        initSelectSize();
        for(int i=0; i<btnSizes.length;i++){
            Button btn = btnSizes[i];

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectSize(btnSizes[finalI],sizeNames[finalI],(finalI+1));
                }
            });
        }

        edtProductInfo2.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> tags) {
                LogUtil.d(Arrays.toString(tags.toArray()));
               arrSizeName= (ArrayList<String>) tags;
                initSelectSize();
                if(arrSizeName!=null && arrSizeName.size()>0){
                    for(int i=0; i<arrSizeName.size();i++){
                        for(int j=0;j<sizeNames.length;j++){
                            if(arrSizeName.get(i).equals(sizeNames[j])){
                                selectSize(btnSizes[j],sizeNames[j],(j+1));

                            }
                        }
                    }
                }
            }

            @Override
            public void onEditingFinished() {

            }
        });
        //********************************************************************************************
        initSelectColor();

        for(int i=0; i<btnColors.length;i++){
            Button btn = btnColors[i];
            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectColor(btnColors[finalI],colorNames[finalI],(finalI+1));
                }
            });
        }

        edtProductInfo1.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> tags) {
                LogUtil.d(Arrays.toString(tags.toArray()));
                arrColorName= (ArrayList<String>) tags;
                initSelectColor();
                if(arrColorName!=null && arrColorName.size()>0){
                    for(int i=0; i<arrColorName.size();i++){
                        for(int j=0;j<colorNames.length;j++){
                            if(arrColorName.get(i).equals(colorNames[j])){
                                selectColor(btnColors[j],colorNames[j],(j+1));

                            }
                        }
                    }
                }
            }

            @Override
            public void onEditingFinished() {

            }
        });
        //********************************************************************************
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
                selectCategory();
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

    private void checkSelectColor(){
        String[] colorNames = getResources().getStringArray(R.array.color_code_name);
        String[] sizeNames = getResources().getStringArray(R.array.size_code_name);
        String[] materialNames = getResources().getStringArray(R.array.material_code_name);

        String comoma = ",";
        if(arrColorName!=null && arrColorName.size()>0){
            for(int i=0; i<arrColorName.size();i++){
                for(int j=0;j<colorNames.length;j++){
                    if(arrColorName.get(i).equals(colorNames[j])){
                            strColorValue = strColorValue +(j+1)+comoma;
                    }
                }
            }
        }

        strColorValue = strColorValue.substring(0, strColorValue.length()-1);

    }

    private void checkSelectSize(){
        String[] sizeNames = getResources().getStringArray(R.array.size_code_name);

        String comoma = ",";
        if(arrSizeName!=null && arrSizeName.size()>0){
            for(int i=0; i<arrSizeName.size();i++){
                for(int j=0;j<sizeNames.length;j++){
                    if(arrSizeName.get(i).equals(sizeNames[j])){
                            strSizeValue = strSizeValue +(j+1)+comoma;
                    }
                }
            }
        }

        strSizeValue = strSizeValue.substring(0, strSizeValue.length()-1);

    }

    private void checkWashingInfo(){

        strWashingInfo = strWashingInfo.substring(0, strWashingInfo.length()-1);
    }
    private void checkSelectMaterial(){
        String[] materialNames = getResources().getStringArray(R.array.material_code_name);

        String comoma = ",";
        if(arrMeterialName!=null && arrMeterialName.size()>0){
            for(int i=0; i<arrMeterialName.size();i++){
                for(int j=0;j<materialNames.length;j++){
                    if(arrMeterialName.get(i).equals(materialNames[j])){
                            strMaterialValue = strMaterialValue +(j+1)+comoma;
                    }
                }
            }
        }

        strMaterialValue = strMaterialValue.substring(0, strMaterialValue.length()-1);

    }
    private void saveData(){

        if(profileList ==null || (profileList!=null && profileList.size() ==0)){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("이미지가 포함되어있지 않습니다.\n이미지 등록 후 저장해주세요.");
            dg.show();
            return;
        }

        if(edtProductName.getText().toString().length() ==0){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("상품명을 입력해주세요.");
            dg.show();
            return;
        }

        String strPrice = edtProductPrice.getText().toString();
        int price=0;
       try{
           price= (strPrice!=null&& strPrice.length()>0)?Integer.valueOf(strPrice):0;
       }catch (Throwable e){

       }

        if(price <=100){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("단가를 100원 이하로 입력하면 등록할 수 없어요.");
            dg.show();
            return;
        }

        if(strValueCategory==null ||(strValueCategory!=null&& strValueCategory.length()==0)){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("카테고리를 선택해주세요.");
            dg.show();
            return;
        }
        checkSelectColor();
        if(strColorValue.length() ==0){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("색상을 선택해주세요.");
            dg.show();
            return;
        }
        checkSelectSize();
        if(strSizeValue.length() ==0){

            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("사이즈를 선택해주세요.");
            dg.show();
            return;
        }

        checkWashingInfo();
        if(strWashingInfo.length() ==0){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("세탁정보를 선택해주세요.");
            dg.show();
            return;
        }

        if(edtDescript.getText().toString().length() ==0){
            final CommonAlertDialog dg = new CommonAlertDialog(this,false,false);
            dg.setMessage("상세설명을 입력해주세요.");
            dg.show();
            return;
        }

        API.productAdd(this,edtProductName.getText().toString(), price+"",strValueCategory,strMaterialValue,
                cloth_info_1+"",cloth_info_2+"",cloth_info_3+"",cloth_info_4+"",
                strWashingInfo,styleInfo+"",strColorValue, strSizeValue,
                edtDescript.getText().toString(),nNationalSelect+"", SharedPreference.getIntSharedPreference(this,Constant.CommonKey.store_id)+""
                ,nOpenOption+"",edtOpenDay.getText().toString().length()>0?edtOpenDay.getText().toString():"",
                nKakaoOpen+"",cbCheck.isChecked()?(1+""):(0+""),bmList, resultHandler, errHandler);
    }

    private void selectCategory(){
        CategoryListResponse response = AppData.getInstance().getCategoryListResponse();
        if(response!=null &&response.getList().size()>0){

            ArrayList<CategoryLIstData> arrayList = new ArrayList<>();
            arrayList.addAll(response.getList());

            String[] items = new String[arrayList.size()];
            for(int i=0; i<arrayList.size();i++){
                items[i]= arrayList.get(i).getName();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(ProductRegistrationActivity.this);
            builder.setCancelable(false);
            builder.setTitle("카테고리 선택");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // the user clicked on options[which]
                    btnCategory.setText(items[which]);
                    strValueCategory  = response.getList().get(which).getCode();
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


    }

    private Handler resultHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try{
                JSONObject jsonObject = (JSONObject)msg.obj;

                if(jsonObject.getBoolean("result")){
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

                if(jsonObject.getBoolean("result")){

                    if(jsonObject.getString("error")!=null && jsonObject.getString("error").length()>0){
                        final CommonAlertDialog dg = new CommonAlertDialog(ProductRegistrationActivity.this,false,true);
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
                            bmList[i] = imageFiles.get(i).getBitmap();
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