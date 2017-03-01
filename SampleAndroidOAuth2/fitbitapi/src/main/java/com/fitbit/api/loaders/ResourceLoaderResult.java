package com.fitbit.api.loaders;

/**
 * Created by jboggess on 10/17/16.
 */

public class ResourceLoaderResult<T> {
    private final T result;
    private final boolean successful;
    private final Exception exception;
    private final String errorMessage;
    private final ResultType resultType;

    public enum ResultType {
        SUCCESS, ERROR, EXCEPTION, LOGGED_OUT
    }

    private ResourceLoaderResult(T result, ResultType resultType, String errorMessage, Exception exception) {
        this.result = result;
        this.successful = resultType == ResultType.SUCCESS;
        this.errorMessage = errorMessage;
        this.exception = exception;
        this.resultType = resultType;
    }

    public static <G> ResourceLoaderResult<G> onSuccess(G result) {
        return new ResourceLoaderResult<G>(result, ResultType.SUCCESS, null, null);
    }

    public static <G> ResourceLoaderResult<G> onError(String errorMessage) {
        return new ResourceLoaderResult<G>(null, ResultType.ERROR, errorMessage, null);
    }

    public static <G> ResourceLoaderResult<G> onException(Exception exception) {
        String message = exception.getMessage();
        if (message == null) {
            message = exception.getCause().getMessage();
        }
        return new ResourceLoaderResult<G>(null, ResultType.EXCEPTION, message, exception);
    }

    public static <G> ResourceLoaderResult<G> onLoggedOut() {
        return new ResourceLoaderResult<G>(null, ResultType.LOGGED_OUT, null, null);
    }

    public T getResult() {
        return result;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Exception getException() {
        return exception;
    }

    public ResultType getResultType() {
        return resultType;
    }
}
