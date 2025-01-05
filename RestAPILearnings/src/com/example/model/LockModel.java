package com.example.model;

public class LockModel {
    public LockModel(boolean isLocked, int methodAllowedToExecute) {
        this.isLocked = isLocked;
        this.methodAllowedToExecute = methodAllowedToExecute;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getMethodAllowedToExecute() {
        return methodAllowedToExecute;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setMethodAllowedToExecute(int methodAllowedToExecute) {
        this.methodAllowedToExecute = methodAllowedToExecute;
    }

    @Override
    public String toString() {
        return "LockModel{" +
                "isLocked=" + isLocked +
                ", methodAllowedToExecute=" + methodAllowedToExecute +
                '}';
    }

    private boolean isLocked;
    private int methodAllowedToExecute;
}
