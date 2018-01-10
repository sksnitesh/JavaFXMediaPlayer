package com.lue.dto;

import java.util.List;

/**
 *
 * @author lue
 */
public class ModuleVideo {
   
    private String ProductVideoId;
    private String fkProductModuleId;
    private String VideoTitle;
    private String VideoUrl;
    private String VideoImage;
    private String CreateDatetime;
    private String UpdateDatetime;
    private List<ImgDesc> ImgDesc;

    
    
    /**
     * @return the ImgDesc
     */
    public List<ImgDesc> getImgDesc() {
        return ImgDesc;
    }

    /**
     * @param ImgDesc the ImgDesc to set
     */
    public void setImgDesc(List<ImgDesc> ImgDesc) {
        this.ImgDesc = ImgDesc;
    }

    /**
     * @return the ProductVideoId
     */
    public String getProductVideoId() {
        return ProductVideoId;
    }

    /**
     * @param ProductVideoId the ProductVideoId to set
     */
    public void setProductVideoId(String ProductVideoId) {
        this.ProductVideoId = ProductVideoId;
    }

    /**
     * @return the fkProductModuleId
     */
    public String getFkProductModuleId() {
        return fkProductModuleId;
    }

    /**
     * @param fkProductModuleId the fkProductModuleId to set
     */
    public void setFkProductModuleId(String fkProductModuleId) {
        this.fkProductModuleId = fkProductModuleId;
    }

    /**
     * @return the VideoTitle
     */
    public String getVideoTitle() {
        return VideoTitle;
    }

    /**
     * @param VideoTitle the VideoTitle to set
     */
    public void setVideoTitle(String VideoTitle) {
        this.VideoTitle = VideoTitle;
    }

    /**
     * @return the VideoUrl
     */
    public String getVideoUrl() {
        return VideoUrl;
    }

    /**
     * @param VideoUrl the VideoUrl to set
     */
    public void setVideoUrl(String VideoUrl) {
        this.VideoUrl = VideoUrl;
    }

    /**
     * @return the VideoImage
     */
    public String getVideoImage() {
        return VideoImage;
    }

    /**
     * @param VideoImage the VideoImage to set
     */
    public void setVideoImage(String VideoImage) {
        this.VideoImage = VideoImage;
    }

    /**
     * @return the CreateDatetime
     */
    public String getCreateDatetime() {
        return CreateDatetime;
    }

    /**
     * @param CreateDatetime the CreateDatetime to set
     */
    public void setCreateDatetime(String CreateDatetime) {
        this.CreateDatetime = CreateDatetime;
    }

    /**
     * @return the UpdateDatetime
     */
    public String getUpdateDatetime() {
        return UpdateDatetime;
    }

    /**
     * @param UpdateDatetime the UpdateDatetime to set
     */
    public void setUpdateDatetime(String UpdateDatetime) {
        this.UpdateDatetime = UpdateDatetime;
    }
    
    
}
