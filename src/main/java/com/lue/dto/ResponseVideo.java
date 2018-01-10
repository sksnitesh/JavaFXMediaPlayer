package com.lue.dto;

import java.util.List;

/**
 *
 * @author lue
 */
public class ResponseVideo {

    private String Success;
    private String Message;
    private List<Info> Info;

    /**
     * @return the Success
     */
    public String getSuccess() {
        return Success;
    }

    /**
     * @param Success the Success to set
     */
    public void setSuccess(String Success) {
        this.Success = Success;
    }

    /**
     * @return the Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param Message the Message to set
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

    /**
     * @return the Info
     */
    public List<Info> getInfo() {
        return Info;
    }

    /**
     * @param Info the Info to set
     */
    public void setInfo(List<Info> Info) {
        this.Info = Info;
    }

}
