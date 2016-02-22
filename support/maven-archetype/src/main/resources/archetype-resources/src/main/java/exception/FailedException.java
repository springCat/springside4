package ${package}.exception;

/**
 * Created by springcat on 16/2/19.
 */
public class FailedException extends RuntimeException {

    private static final long serialVersionUID = 3583566093089790852L;

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FailedException() {
        super();
    }

    public FailedException(int code, String msg) {
       this.code = code;
        this.msg = msg;
    }
}
