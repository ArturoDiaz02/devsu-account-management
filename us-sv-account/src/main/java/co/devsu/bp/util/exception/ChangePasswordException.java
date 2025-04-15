package co.devsu.bp.util.exception;

public class ChangePasswordException extends GeneralEntityException {

    public ChangePasswordException(String msg) {
        super(msg);
    }

    @Override
    public String getCode() {
        return "";
    }
}
