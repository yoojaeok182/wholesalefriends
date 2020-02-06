package com.wholesale.wholesalefriends.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.soundcloud.android.crop.Crop;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.GroupActivity;
import com.wholesale.wholesalefriends.main.common.Constant;
import com.wholesale.wholesalefriends.main.data.ImageBitmap;
import com.wholesale.wholesalefriends.main.data.ImageFile;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;
import com.wholesale.wholesalefriends.module.util.BitmapUtil;
import com.wholesale.wholesalefriends.module.util.LogUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Handa-dev5 on 2016-05-03.
 */
public class ImageEditActivity extends GroupActivity {


    ArrayList<ImageBitmap> arrImage;
    ArrayList<ImageFile> imageFiles;
    TextView tvCount;
    ImageView ivImg0;
    ImageView ivImg1;
    ImageView ivImg2;
    ImageView ivImg3;
    ImageView ivImg4;

    ImageView[] ivCheck;
    TextView[] tvImageNumber;
    private int imagePosition = 0;
    private boolean isProfile = false;
    private boolean isNodata = false;;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);
        isBeforeLogin = true;

        ImageView btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
               finish();
            }
        });
        tvCount = (TextView) findViewById(R.id.tv_count);

        final Intent intent = getIntent();
        isProfile = intent.getBooleanExtra("profile", false);
        imageFiles = (ArrayList<ImageFile>) intent.getSerializableExtra("image");
        arrImage = new ArrayList<>();



        boolean isOverSize = false;
        isNodata = false;
        ArrayList<ImageBitmap> temp = new ArrayList<>();
        for (int i = 0; i <imageFiles.size(); i++) {
            if(!imageFiles.get(i).isOverSize()) {
                ImageBitmap imageBitmap = new ImageBitmap();
                imageBitmap.setOriginalPath(imageFiles.get(i).getOriginalPath());
                imageBitmap.setCheck(imageFiles.get(i).isCheck());
                imageBitmap.setBitmap(BitmapUtil.compressImage(imageFiles.get(i).getOriginalPath(), 1, false));
                temp.add(imageBitmap);
            } else {
                imageFiles.remove(i);
                isOverSize = true;
            }
        }

        if(temp.size() > 0) {
            for (int i = temp.size() - 1; i >= 0; i--) {
                arrImage.add(temp.get(i));
                if(isProfile){
                    arrImage.get(i).setCheck(true);
                }
            }
        } else {
            isNodata = true;
        }

        if(!isNodata) {
            tvCount.setText(0+"");
            ivCheck = new ImageView[arrImage.size()];
            tvImageNumber = new TextView[arrImage.size()];

            TextView tvAdd = (TextView) findViewById(R.id.tv_ok);
            tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(getCheckCount().equals("0") ){
                        return;
                    }

                    for (int i = arrImage.size() - 1; i >= 0; i--) {
                        if (arrImage.get(i).isCheck()) {
                            String resizePath = "";
                            resizePath = BitmapUtil.saveImage(arrImage.get(i).getBitmap());
                            arrImage.get(i).setResizePath(resizePath);
                            imageFiles.get(i).setResizePath(resizePath);
                            // imageFiles.get(i).setBitmap(arrImage.get(i).getBitmap());
                        } else {
                            imageFiles.remove(i);
                        }
                    }
                    Intent intent1 = new Intent();
                    intent1.putExtra("image", imageFiles);
                    setResult(RESULT_OK, intent1);
                    finish();
                }
            });

            ViewPager mPager;
            ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getLayoutInflater());
            mPager = (ViewPager) findViewById(R.id.viewpager);
            mPager.setAdapter(imagePagerAdapter);
            mPager.setCurrentItem(0);
            mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override public void onPageSelected(int position) {
                    imagePosition = position;
                    if (arrImage.get(position).isCheck()) {
                        tvImageNumber[position].setVisibility(View.VISIBLE);
                        tvImageNumber[position].setText(getCheckNumber(position));
                    } else {
                        tvImageNumber[position].setVisibility(View.GONE);
                    }
                }

                @Override public void onPageScrollStateChanged(int state) {

                }
            });
        }

        if(isNodata) {
            if(isOverSize) {
                CommonAlertDialog dialog = new CommonAlertDialog(ImageEditActivity.this,false,true);
                dialog.setCancelable(false);
                dialog.setTitle(  getString(R.string.dialog_alert));
                dialog.setMessage( getString(R.string.dialog_image_size_alert));
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) {
                            finish();
                    }
                });
                dialog.show();
            } else {
                CommonAlertDialog dialog = new CommonAlertDialog(ImageEditActivity.this,false,true);
                dialog.setCancelable(false);
                dialog.setTitle(  getString(R.string.dialog_alert));
                dialog.setMessage( getString(R.string.dialog_image_size_no));

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            }
        } else {
            if(isOverSize) {
                CommonAlertDialog dialog = new CommonAlertDialog(ImageEditActivity.this,false,true);
                dialog.setCancelable(false);
                dialog.setTitle(  getString(R.string.dialog_alert));
                dialog.setMessage( getString(R.string.dialog_image_size_alert));
                dialog.show();
            }
        }
    }

    @Override protected void onResume() {
        super.onResume();
    }

    @Override protected void onDestroy() {
        try {
            arrImage = null;
            ivImg0.setImageDrawable(null);
            ivImg1.setImageDrawable(null);
            ivImg2.setImageDrawable(null);
            ivImg3.setImageDrawable(null);
            ivImg4.setImageDrawable(null);
        } catch (Exception e) {

        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, data);
        }
    }

    private void beginCrop(String filepath, boolean isRect) {
        Uri source = Uri.fromFile(new File(filepath));
        String folderPath = Constant.PICTURE_DIR2;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File((folderPath + "/wholesalefriend" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

        Uri destination = Uri.fromFile(file);

        if(isRect) {
            Crop.of(source, destination).withAspect(500,600).start(this);
        } else {
            Crop.of(source, destination).start(this);
        }
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            arrImage.get(imagePosition).setCroped(true);
            arrImage.get(imagePosition).setCropPath(Crop.getOutput(result).getPath());
            arrImage.get(imagePosition).setBitmap(BitmapUtil.compressImage(Crop.getOutput(result).getPath(), 1, true));
            Uri source = Uri.fromFile(new File(Crop.getOutput(result).getPath()));
            try {
                switch (imagePosition) {
                    case 0:
                        Glide.with(this).asBitmap().load(Crop.getOutput(result).getPath()).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                LogUtil.e("e : " + e.getMessage());
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(imagePosition).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg0);
                        break;
                    case 1:
                        Glide.with(this).asBitmap().load(Crop.getOutput(result).getPath()).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(imagePosition).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg1);
                        break;
                    case 2:
                        Glide.with(this).asBitmap().load(Crop.getOutput(result).getPath()).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(imagePosition).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg2);
                        break;
                    case 3:
                        Glide.with(this).asBitmap().load(Crop.getOutput(result).getPath()).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(imagePosition).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg3);
                        break;
                    case 4:
                        Glide.with(this).asBitmap().load(Crop.getOutput(result).getPath()).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(imagePosition).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg4);
                        break;
                }
            } catch (OutOfMemoryError e) {
                Log.d("OOM:", "" + e.getMessage());
                e.printStackTrace();
            }
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            if(isProfile) {
                finish();
            }
        }
    }

    private String getCheckCount() {
        int count = 0;
        for(int i = 0; i < arrImage.size(); i++) {
            if(arrImage.get(i).isCheck()) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    private String getCheckNumber(int position) {
        int count = 1;
        for(int i = 0; i < position; i++) {
            if(arrImage.get(i).isCheck()) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    public class ImagePagerAdapter extends PagerAdapter {


        LayoutInflater inflater;

        public ImagePagerAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public int getCount() {
            return arrImage.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = null;
            view = inflater.inflate(R.layout.layout_image_edit, null);


            ivCheck[position] = (ImageView) view.findViewById(R.id.iv_check);
            tvImageNumber[position] = (TextView) view.findViewById(R.id.tv_number);
            RelativeLayout imageContainer = (RelativeLayout) view.findViewById(R.id.check_container);
            imageContainer.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (arrImage.get(position).isCheck()) {
                        arrImage.get(position).setCheck(false);
                        ivCheck[position].setBackgroundResource(R.drawable.top_bg_num);
                        tvImageNumber[position].setVisibility(View.GONE);
                        tvCount.setText(getCheckCount());
                    } else {
                        arrImage.get(position).setCheck(true);
                        ivCheck[position].setBackgroundResource(R.drawable.top_bg_num);
                        tvImageNumber[position].setVisibility(View.VISIBLE);
                        tvImageNumber[position].setText(getCheckNumber(position));
                        tvCount.setText(getCheckCount());
                    }
                }
            });

            if (arrImage.get(position).isCheck()) {
                ivCheck[position].setBackgroundResource(R.drawable.top_bg_num);
                tvImageNumber[position].setVisibility(View.VISIBLE);
                tvImageNumber[position].setText(getCheckNumber(position));
            } else {
                ivCheck[position].setBackgroundResource(R.drawable.top_bg_num);
                tvImageNumber[position].setVisibility(View.GONE);
            }

            //이미지뷰 position으로 로딩
            try {

                switch (position) {
                    case 0:
                        ivImg0 = (ImageView) view.findViewById(R.id.iv_img);
//                        Glide.with(ImageEditActivity.this).asBitmap().load(arrImage.get(position).getOriginalPath()).into(ivImg0);
                        Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getOriginalPath())).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(position).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg0);
                        break;
                    case 1:
                        ivImg1 = (ImageView) view.findViewById(R.id.iv_img);
//                        Glide.with(ImageEditActivity.this).asBitmap().load(arrImage.get(position).getOriginalPath()).into(ivImg1);
                        Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getOriginalPath())).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(position).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg1);
                        break;
                    case 2:
                        ivImg2 = (ImageView) view.findViewById(R.id.iv_img);
//                        Glide.with(ImageEditActivity.this).asBitmap().load(arrImage.get(position).getOriginalPath()).into(ivImg2);
                        Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getOriginalPath())).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(position).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg2);
                        break;
                    case 3:
                        ivImg3 = (ImageView) view.findViewById(R.id.iv_img);
//                        Glide.with(ImageEditActivity.this).asBitmap().load(arrImage.get(position).getOriginalPath()).into(ivImg2);
                        Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getOriginalPath())).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(position).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg3);
                        break;

                    case 4:
                        ivImg4 = (ImageView) view.findViewById(R.id.iv_img);
//                        Glide.with(ImageEditActivity.this).asBitmap().load(arrImage.get(position).getOriginalPath()).into(ivImg2);
                        Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getOriginalPath())).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                arrImage.get(position).setBitmap(resource);
                                return false;
                            }
                        }).into(ivImg4);
                        break;
                }
            } catch (OutOfMemoryError e) {
                Log.d("OOM:", "" + e.getMessage());
                e.printStackTrace();
            }

            RelativeLayout rotate = (RelativeLayout) view.findViewById(R.id.rotate);
            rotate.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (!arrImage.get(position).isGif()) {
                        arrImage.get(position).setBitmap(BitmapUtil.rotateImage(arrImage.get(position).getBitmap(), 90));
                        try {
                            switch (position) {
                                case 0:
                                    Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getCropPath())).listener(new RequestListener<Bitmap>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                            arrImage.get(position).setBitmap(resource);
                                            return false;
                                        }
                                    }).into(ivImg0);
                                    break;
                                case 1:
                                    Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getCropPath())).listener(new RequestListener<Bitmap>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                            arrImage.get(position).setBitmap(resource);
                                            return false;
                                        }
                                    }).into(ivImg1);
                                    break;
                                case 2:
                                    Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getCropPath())).listener(new RequestListener<Bitmap>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                            arrImage.get(position).setBitmap(resource);
                                            return false;
                                        }
                                    }).into(ivImg2);
                                    break;
                                case 3:
                                    Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getCropPath())).listener(new RequestListener<Bitmap>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                            arrImage.get(position).setBitmap(resource);
                                            return false;
                                        }
                                    }).into(ivImg3);
                                    break;

                                case 4:
                                    Glide.with(ImageEditActivity.this).asBitmap().load(new File(arrImage.get(position).getCropPath())).listener(new RequestListener<Bitmap>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                            arrImage.get(position).setBitmap(resource);
                                            return false;
                                        }
                                    }).into(ivImg4);
                                    break;
                            }
                        } catch (OutOfMemoryError e) {
                            Log.d("OOM:", "" + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            });

            RelativeLayout crop = (RelativeLayout) view.findViewById(R.id.crop);
            crop.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(!arrImage.get(position).isGif()) {
                        if(isProfile) {
                            beginCrop(arrImage.get(position).getOriginalPath(), true);
                        } else {
                            beginCrop(arrImage.get(position).getOriginalPath(), false);
                        }
                    }
                }
            });


            if(isProfile) {
                beginCrop(arrImage.get(0).getOriginalPath(), true);
            }

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View v, Object obj) {
            // TODO Auto-generated method stub
            return v == obj;
        }
    }
}