package org.birthdayreminder.client;

public enum RestMethods {
    GETUPDATES("/getUpdates"),
    GETUPDATESOFFSET("/getUpdates?offset=%d&timeout=%d"),
    GETFILE("/getFile?file_id="),
    SENDMESSAGE("/sendMessage?chat_id=%d&text=%s");

    private final String path;

    RestMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getParamPathSendMessage(Long arg1, String arg2) {
        return String.format(path, arg1, arg2);
    }

    public String getParamUpdate(Integer arg1, Integer arg2) {
        return String.format(path, arg1, arg2);
    }

}




