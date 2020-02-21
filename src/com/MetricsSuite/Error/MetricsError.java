package com.MetricsSuite.Error;

public class MetricsError extends Exception{
    public enum ERROR_CODE {

        ERROR_NULL_PROJECT(1011, "Project is null."),
        ERROR_PROJECT_ALREADY_EXISTS(1010, "This Project already exists."),
        ERROR_READING_PROJECT(1011, "Could not read the file.");

        private final int code;
        private final String description;

        private ERROR_CODE(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code + ": " + description;
        }
    }

    public MetricsError(ERROR_CODE errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorCode.toString(), cause, enableSuppression, writableStackTrace);
    }

    public MetricsError(ERROR_CODE errorCode) {
        super(errorCode.toString());
    }
}
