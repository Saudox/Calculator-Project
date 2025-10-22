package com.calc.calc;

public class ResultWrap {
    private Object value;
    private String status;

    public ResultWrap() {}

    public ResultWrap(Object value, String status) {
        this.value = value;
        this.status = status;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
