package com.xyz.roemahduren.util;

import javax.swing.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class DatabaseWorker<T> extends SwingWorker<T, Void> {
    private final Callable<T> callable;
    private final Consumer<T> onSuccess;
    private final Consumer<Throwable> onError;
    private final Runnable onFinally;

    public DatabaseWorker(Callable<T> callable, Consumer<T> onSuccess, Consumer<Throwable> onError, Runnable onFinally) {
        this.callable = callable;
        this.onSuccess = onSuccess;
        this.onError = onError;
        this.onFinally = onFinally;
    }

    @Override
    protected T doInBackground() throws Exception {
        return callable.call();
    }

    @Override
    protected void done() {
        try {
            T result = get();
            onSuccess.accept(result);
        } catch (RuntimeException | InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof RuntimeException) {
                RuntimeException exception = (RuntimeException) e.getCause();
                onError.accept(exception);
            } else {
                e.printStackTrace();
            }
        } finally {
            onFinally.run();
        }
    }
}
