package com.resources.pagination.admin;

public class MessagePagination extends DefaultAdminPagination {

    private String messageTitle;
    private String messageBody;
    private String redirect = null;
    private int messageType = MESSAGE_TYPE_INFO;
    public static final String MESSAGE_VIEW = "/message_content";
    public static final int MESSAGE_TYPE_INFO = 0;
    public static final int MESSAGE_TYPE_WARNING = 1;
    public static final int MESSAGE_TYPE_ERROR = 2;
    public static final int MESSAGE_TYPE_SUCCESS = 3;

    public MessagePagination() {
    }

    public MessagePagination(int messageType, String messageTitle, String messageBody) {
        this.messageType = messageType;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    public int getMessageType() {
        return messageType;
    }

    public String getMESSAGE_VIEW() {
        return MESSAGE_VIEW;
    }

    public int getMESSAGE_TYPE_INFO() {
        return MESSAGE_TYPE_INFO;
    }

    public int getMESSAGE_TYPE_WARNING() {
        return MESSAGE_TYPE_WARNING;
    }

    public int getMESSAGE_TYPE_ERROR() {
        return MESSAGE_TYPE_ERROR;
    }

    public int getMESSAGE_TYPE_SUCCESS() {
        return MESSAGE_TYPE_SUCCESS;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
