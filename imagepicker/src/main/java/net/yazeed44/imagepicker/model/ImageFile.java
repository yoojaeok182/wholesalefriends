package net.yazeed44.imagepicker.model;

import java.io.Serializable;

/**
 * Created by Handa-dev5 on 2016-05-03.
 */
public class ImageFile implements Serializable{
    private String originalPath;
    private String cropPath;
    private String resizePath;
    private Integer fileSeq;
    private Integer isBridgeShoot = 0;
    private boolean isCheck = false;
    private boolean isCroped = false;
    private boolean isGif = false;
    private boolean isOverSize = false;

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getResizePath() {
        return resizePath;
    }

    public void setResizePath(String resizePath) {
        this.resizePath = resizePath;
    }

    public String getCropPath() {
        return cropPath;
    }

    public void setCropPath(String cropPath) {
        this.cropPath = cropPath;
    }

    public Integer getIsBridgeShoot() {
        return isBridgeShoot;
    }

    public void setIsBridgeShoot(Integer isBridgeShoot) {
        this.isBridgeShoot = isBridgeShoot;
    }

    public Integer getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(Integer fileSeq) {
        this.fileSeq = fileSeq;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean isCroped() {
        return isCroped;
    }

    public void setIsCroped(boolean isCroped) {
        this.isCroped = isCroped;
    }

    public boolean isGif() {
        return isGif;
    }

    public void setIsGif(boolean isGif) {
        this.isGif = isGif;
    }

    public boolean isOverSize() {
        return isOverSize;
    }

    public void setIsOverSize(boolean isOverSize) {
        this.isOverSize = isOverSize;
    }
}
