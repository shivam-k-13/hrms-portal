package service.exception;

public class LLMQuotaExceededException extends RuntimeException {

    private final int retryAfterSeconds;

    public LLMQuotaExceededException(String message, int retryAfterSeconds) {
        super(message);

        this.retryAfterSeconds = retryAfterSeconds;
    }

    public int getRetryAfterSeconds() {
        return retryAfterSeconds;
    }

}