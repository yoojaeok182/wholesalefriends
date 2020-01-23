package com.wholesale.wholesalefriends.main.data;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ImageBitmap implements Serializable {

    private Bitmap bitmap;
    private String originalPath;
    private String cropPath;
    private String resizePath;
    private Integer fileSeq;
    private Integer isBridgeShoot = 0;
    private boolean isCheck = false;
    private boolean isCroped = false;
    private boolean isGif = false;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getCropPath() {
        return cropPath;
    }

    public void setCropPath(String cropPath) {
        this.cropPath = cropPath;
    }

    public String getResizePath() {
        return resizePath;
    }

    public void setResizePath(String resizePath) {
        this.resizePath = resizePath;
    }

    public Integer getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(Integer fileSeq) {
        this.fileSeq = fileSeq;
    }

    public Integer getIsBridgeShoot() {
        return isBridgeShoot;
    }

    public void setIsBridgeShoot(Integer isBridgeShoot) {
        this.isBridgeShoot = isBridgeShoot;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCroped() {
        return isCroped;
    }

    public void setCroped(boolean croped) {
        isCroped = croped;
    }

    public boolean isGif() {
        return isGif;
    }

    public void setGif(boolean gif) {
        isGif = gif;
    }
}
