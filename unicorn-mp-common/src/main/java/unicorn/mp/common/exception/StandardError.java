package unicorn.mp.common.exception;

public interface StandardError {
    String getType();

    String getCode();

    String getDescription();

    String getToast();
}
