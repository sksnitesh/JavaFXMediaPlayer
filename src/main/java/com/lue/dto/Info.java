package com.lue.dto;

import java.util.List;

/**
 *
 * @author lue
 */
public class Info {

    private String LicenceUserId;
    private String fkOrgLicenceId;
    private String Firstname;
    private String LastName;
    private String Email;
    private String LicenceCode;
    private String AddDatetime;

    private String CategoryId;
    private String fkCategoryId;
    private String CategoryTitle;
    private String CategoryDesc;
    private String CategoryPrice;
    private String CategoryType;
    private String AddCategoryDateTime;
    private String UpdateCategoryDateTime;
    private List<Packages> Packages;

    /**
     * @return the LicenceUserId
     */
    public String getLicenceUserId() {
        return LicenceUserId;
    }

    /**
     * @param LicenceUserId the LicenceUserId to set
     */
    public void setLicenceUserId(String LicenceUserId) {
        this.LicenceUserId = LicenceUserId;
    }

    /**
     * @return the fkOrgLicenceId
     */
    public String getFkOrgLicenceId() {
        return fkOrgLicenceId;
    }

    /**
     * @param fkOrgLicenceId the fkOrgLicenceId to set
     */
    public void setFkOrgLicenceId(String fkOrgLicenceId) {
        this.fkOrgLicenceId = fkOrgLicenceId;
    }

    /**
     * @return the Firstname
     */
    public String getFirstname() {
        return Firstname;
    }

    /**
     * @param Firstname the Firstname to set
     */
    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the LicenceCode
     */
    public String getLicenceCode() {
        return LicenceCode;
    }

    /**
     * @param LicenceCode the LicenceCode to set
     */
    public void setLicenceCode(String LicenceCode) {
        this.LicenceCode = LicenceCode;
    }

    /**
     * @return the AddDatetime
     */
    public String getAddDatetime() {
        return AddDatetime;
    }

    /**
     * @param AddDatetime the AddDatetime to set
     */
    public void setAddDatetime(String AddDatetime) {
        this.AddDatetime = AddDatetime;
    }

    /**
     * @return the CategoryId
     */
    public String getCategoryId() {
        return CategoryId;
    }

    /**
     * @param CategoryId the CategoryId to set
     */
    public void setCategoryId(String CategoryId) {
        this.CategoryId = CategoryId;
    }

    /**
     * @return the fkCategoryId
     */
    public String getFkCategoryId() {
        return fkCategoryId;
    }

    /**
     * @param fkCategoryId the fkCategoryId to set
     */
    public void setFkCategoryId(String fkCategoryId) {
        this.fkCategoryId = fkCategoryId;
    }

    /**
     * @return the CategoryTitle
     */
    public String getCategoryTitle() {
        return CategoryTitle;
    }

    /**
     * @param CategoryTitle the CategoryTitle to set
     */
    public void setCategoryTitle(String CategoryTitle) {
        this.CategoryTitle = CategoryTitle;
    }

    /**
     * @return the CategoryDesc
     */
    public String getCategoryDesc() {
        return CategoryDesc;
    }

    /**
     * @param CategoryDesc the CategoryDesc to set
     */
    public void setCategoryDesc(String CategoryDesc) {
        this.CategoryDesc = CategoryDesc;
    }

    /**
     * @return the CategoryPrice
     */
    public String getCategoryPrice() {
        return CategoryPrice;
    }

    /**
     * @param CategoryPrice the CategoryPrice to set
     */
    public void setCategoryPrice(String CategoryPrice) {
        this.CategoryPrice = CategoryPrice;
    }

    /**
     * @return the CategoryType
     */
    public String getCategoryType() {
        return CategoryType;
    }

    /**
     * @param CategoryType the CategoryType to set
     */
    public void setCategoryType(String CategoryType) {
        this.CategoryType = CategoryType;
    }

    /**
     * @return the AddCategoryDateTime
     */
    public String getAddCategoryDateTime() {
        return AddCategoryDateTime;
    }

    /**
     * @param AddCategoryDateTime the AddCategoryDateTime to set
     */
    public void setAddCategoryDateTime(String AddCategoryDateTime) {
        this.AddCategoryDateTime = AddCategoryDateTime;
    }

    /**
     * @return the UpdateCategoryDateTime
     */
    public String getUpdateCategoryDateTime() {
        return UpdateCategoryDateTime;
    }

    /**
     * @param UpdateCategoryDateTime the UpdateCategoryDateTime to set
     */
    public void setUpdateCategoryDateTime(String UpdateCategoryDateTime) {
        this.UpdateCategoryDateTime = UpdateCategoryDateTime;
    }

    /**
     * @return the Packages
     */
    public List<Packages> getPackages() {
        return Packages;
    }

    /**
     * @param Packages the Packages to set
     */
    public void setPackages(List<Packages> Packages) {
        this.Packages = Packages;
    }
    
    
}
