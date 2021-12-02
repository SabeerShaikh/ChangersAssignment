package com.changersassignment.domain.repository;

import androidx.lifecycle.LiveData;

public class RepositoryResponse<T> extends LiveData {

    public final T repositoryResponse;
    /**
     * always check this status when the object is received
     **/
    public final boolean success;
    /**
     * set with the reason for the failure
     **/
    public final String failureMessage;

    /**
     * error code from api layer
     */
    public final int code;

    private RepositoryResponse(T repositoryResponse, boolean status, String failureMessage, int code) {
        this.repositoryResponse = repositoryResponse;
        this.success = status;
        this.failureMessage = failureMessage;
        this.code = code;
    }

    /**
     * Called in successful completion of use case
     *
     * @param repositoryResponse
     */
    public RepositoryResponse(T repositoryResponse) {
        this(repositoryResponse, true, "", 0);
    }

    /**
     * Called in a failure situation
     *
     * @param message
     */
    public RepositoryResponse(String message, int code) {
        this(null, false, message, code);
    }

    /**
     * Called in a failure situation
     *
     * @param message
     */
    public RepositoryResponse(String message) {
        this(null, false, message, -1);
    }
}
