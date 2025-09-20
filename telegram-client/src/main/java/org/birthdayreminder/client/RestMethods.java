package org.birthdayreminder.client;

public enum RestMethods {
    GETUPDATES("/getUpdates"),
    GETUPDATESOFFSET("/getUpdates?offset=%d&timeout=%d"),
    GETFILE("/getFile?file_id="),
    SENDMESSAGE("/sendMessage?chat_id=%d&parse_mode=%s&text=%s");

    private final String path;

    RestMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getParamPathSendMessage(Long arg1, String arg2, String arg3) {
        return String.format(path, arg1, arg2, arg3);
    }

    public String getParamUpdate(Integer arg1, Integer arg2) {
        return String.format(path, arg1, arg2);
    }

}




