package gabrieldev.backend.infra;

public class ErrorDefaultMsg {
    private String message;
    private String code;

    public ErrorDefaultMsg(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
