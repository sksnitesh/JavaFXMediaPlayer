package com.lue.dto;

/**
 *
 * @author lue
 */
public class ImgDesc {
    private String VideodescId;
    private String fkProductVideoId;
    private String VideoImage;
    private String VideoDescriptions;

    /**
     * @return the VideodescId
     */
    public String getVideodescId() {
        return VideodescId;
    }

    /**
     * @param VideodescId the VideodescId to set
     */
    public void setVideodescId(String VideodescId) {
        this.VideodescId = VideodescId;
    }

    /**
     * @return the fkProductVideoId
     */
    public String getFkProductVideoId() {
        return fkProductVideoId;
    }

    /**
     * @param fkProductVideoId the fkProductVideoId to set
     */
    public void setFkProductVideoId(String fkProductVideoId) {
        this.fkProductVideoId = fkProductVideoId;
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
     * @return the VideoDescriptions
     */
    public String getVideoDescriptions() {
        return VideoDescriptions;
    }

    /**
     * @param VideoDescriptions the VideoDescriptions to set
     */
    public void setVideoDescriptions(String VideoDescriptions) {
        this.VideoDescriptions = VideoDescriptions;
    }
   
    
}
