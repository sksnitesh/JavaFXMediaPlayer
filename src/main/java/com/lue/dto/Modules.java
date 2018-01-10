/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lue.dto;

import java.util.List;

/**
 *
 * @author lue
 */
public class Modules {
    private String ProductModuleId;
    private String fkProductPackageId;
    private String fkProductModuleId;
    private String ModuleTitle;
    private String ModuleImage;
    private String ModuleDesc;
    private String CreateDatetime;
    private String UpdateDatetime;
    private String TotalSub;
    private List<ModuleVideo> ModuleVideo;

    /**
     * @return the ProductModuleId
     */
    public String getProductModuleId() {
        return ProductModuleId;
    }

    /**
     * @param ProductModuleId the ProductModuleId to set
     */
    public void setProductModuleId(String ProductModuleId) {
        this.ProductModuleId = ProductModuleId;
    }

    /**
     * @return the fkProductPackageId
     */
    public String getFkProductPackageId() {
        return fkProductPackageId;
    }

    /**
     * @param fkProductPackageId the fkProductPackageId to set
     */
    public void setFkProductPackageId(String fkProductPackageId) {
        this.fkProductPackageId = fkProductPackageId;
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
     * @return the ModuleTitle
     */
    public String getModuleTitle() {
        return ModuleTitle;
    }

    /**
     * @param ModuleTitle the ModuleTitle to set
     */
    public void setModuleTitle(String ModuleTitle) {
        this.ModuleTitle = ModuleTitle;
    }

    /**
     * @return the ModuleImage
     */
    public String getModuleImage() {
        return ModuleImage;
    }

    /**
     * @param ModuleImage the ModuleImage to set
     */
    public void setModuleImage(String ModuleImage) {
        this.ModuleImage = ModuleImage;
    }

    /**
     * @return the ModuleDesc
     */
    public String getModuleDesc() {
        return ModuleDesc;
    }

    /**
     * @param ModuleDesc the ModuleDesc to set
     */
    public void setModuleDesc(String ModuleDesc) {
        this.ModuleDesc = ModuleDesc;
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

    /**
     * @return the TotalSub
     */
    public String getTotalSub() {
        return TotalSub;
    }

    /**
     * @param TotalSub the TotalSub to set
     */
    public void setTotalSub(String TotalSub) {
        this.TotalSub = TotalSub;
    }

    /**
     * @return the ModuleVideo
     */
    public List<ModuleVideo> getModuleVideo() {
        return ModuleVideo;
    }

    /**
     * @param ModuleVideo the ModuleVideo to set
     */
    public void setModuleVideo(List<ModuleVideo> ModuleVideo) {
        this.ModuleVideo = ModuleVideo;
    }
 
    
    }
