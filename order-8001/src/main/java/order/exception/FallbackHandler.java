package order.exception;

public class FallbackHandler {
    public static String fallback(Throwable throwable) {
        return "Exception occurred, please try again later";
    }
}
