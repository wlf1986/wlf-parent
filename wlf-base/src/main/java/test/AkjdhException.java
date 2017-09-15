package test;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月24日 19:13
 */
public class AkjdhException extends Exception {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public AkjdhException(String message, int code) {
        super(message);
        this.code = code;
    }
}
