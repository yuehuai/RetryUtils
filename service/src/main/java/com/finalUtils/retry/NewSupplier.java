package com.finalUtils.retry;

@FunctionalInterface
public interface NewSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
     void get() throws Exception;
}