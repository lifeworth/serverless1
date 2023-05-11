package helloworld;


import org.joda.time.DateTime;

/**
 * @author zhiyuandu
 */
public class CommonResult<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;
    private Long timeStamp;

    public CommonResult() {
    }

    public CommonResult(Boolean success, Integer code, String message, T data, Long timeStamp) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timeStamp = timeStamp;
    }

    public static CommonResult<Object> SUCCESS() {
        return new CommonResult<>(true, 200, "操作成功", null, DateTime.now().getMillis());
    }

    public static <T> CommonResult<T> SUCCESS(T t) {
        return new CommonResult<>(true, 200, "操作成功", t, DateTime.now().getMillis());
    }

    public static CommonResult<Object> FAIL() {
        return new CommonResult<>(false, 500, "server端错误", null, DateTime.now().getMillis());
    }

    public static <T> CommonResult<T> FAIL(int code, String errorMessage) {
        return new CommonResult<T>(false, code, errorMessage, null, DateTime.now().getMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommonResult<?> result = (CommonResult<?>) o;

        if (!success.equals(result.success)) {
            return false;
        }
        if (!code.equals(result.code)) {
            return false;
        }
        if (!message.equals(result.message)) {
            return false;
        }
        if (!data.equals(result.data)) {
            return false;
        }
        return timeStamp.equals(result.timeStamp);
    }

    @Override
    public int hashCode() {
        int result = success.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + data.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public CommonResult<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public CommonResult<T> setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }


}
