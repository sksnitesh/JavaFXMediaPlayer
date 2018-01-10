package com.lue.dto;

import java.util.List;

/**
 *
 * @author lue
 */
public class Packages {
    private String ProductPackageId;
    private String fkProductCategoryId;
    private String PackageName;
    private String PackageDesc;
    private String PackageImage;
    private String PackagePrice;
    private String PackageRenewal;
    private String CreateDatetime;
    private String UpdateDatetime;
    private String ExpiryDate;
    private String IsBuyed;
    private List<Modules> Modules;

    /**
     * @return the ProductPackageId
     */
    public String getProductPackageId() {
        return ProductPackageId;
    }

    /**
     * @param ProductPackageId the ProductPackageId to set
     */
    public void setProductPackageId(String ProductPackageId) {
        this.ProductPackageId = ProductPackageId;
    }

    /**
     * @return the fkProductCategoryId
     */
    public String getFkProductCategoryId() {
        return fkProductCategoryId;
    }

    /**
     * @param fkProductCategoryId the fkProductCategoryId to set
     */
    public void setFkProductCategoryId(String fkProductCategoryId) {
        this.fkProductCategoryId = fkProductCategoryId;
    }

    /**
     * @return the PackageName
     */
    public String getPackageName() {
        return PackageName;
    }

    /**
     * @param PackageName the PackageName to set
     */
    public void setPackageName(String PackageName) {
        this.PackageName = PackageName;
    }

    /**
     * @return the PackageDesc
     */
    public String getPackageDesc() {
        return PackageDesc;
    }

    /**
     * @param PackageDesc the PackageDesc to set
     */
    public void setPackageDesc(String PackageDesc) {
        this.PackageDesc = PackageDesc;
    }

    /**
     * @return the PackageImage
     */
    public String getPackageImage() {
        return PackageImage;
    }

    /**
     * @param PackageImage the PackageImage to set
     */
    public void setPackageImage(String PackageImage) {
        this.PackageImage = PackageImage;
    }

    /**
     * @return the PackagePrice
     */
    public String getPackagePrice() {
        return PackagePrice;
    }

    /**
     * @param PackagePrice the PackagePrice to set
     */
    public void setPackagePrice(String PackagePrice) {
        this.PackagePrice = PackagePrice;
    }

    /**
     * @return the PackageRenewal
     */
    public String getPackageRenewal() {
        return PackageRenewal;
    }

    /**
     * @param PackageRenewal the PackageRenewal to set
     */
    public void setPackageRenewal(String PackageRenewal) {
        this.PackageRenewal = PackageRenewal;
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
     * @return the ExpiryDate
     */
    public String getExpiryDate() {
        return ExpiryDate;
    }

    /**
     * @param ExpiryDate the ExpiryDate to set
     */
    public void setExpiryDate(String ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    /**
     * @return the IsBuyed
     */
    public String getIsBuyed() {
        return IsBuyed;
    }

    /**
     * @param IsBuyed the IsBuyed to set
     */
    public void setIsBuyed(String IsBuyed) {
        this.IsBuyed = IsBuyed;
    }

    /**
     * @return the Modules
     */
    public List<Modules> getModules() {
        return Modules;
    }

    /**
     * @param Modules the Modules to set
     */
    public void setModules(List<Modules> Modules) {
        this.Modules = Modules;
    }
  
    
 }
