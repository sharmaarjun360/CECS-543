package com.MetricsSuite.Models;

import java.io.Serializable;

public class FunctionPointData implements Serializable {

    long inputCount;
    long outputCount;
    long inquryCount;
    long logicalFileCount;
    long interfaceFileCount;

    int inputFactor;
    int outputFactor;
    int inquryFactor;
    int logicalFileFactor;
    int interfaceFileFactor;

    long inputTotal;
    long outputTotal;
    long inquryTotal;
    long logicalFileTotal;
    long interfaceFileTotal;

    long totalCount;
    int vafTotal = 0;

    int[] vafValue = new int[14];

    double functionPointValue;

    public long getInputCount() {
        return inputCount;
    }

    public void setInputCount(long inputCount) {
        this.inputCount = inputCount;
    }

    public long getOutputCount() {
        return outputCount;
    }

    public void setOutputCount(long outputCount) {
        this.outputCount = outputCount;
    }

    public long getInquryCount() {
        return inquryCount;
    }

    public void setInquryCount(long inquryCount) {
        this.inquryCount = inquryCount;
    }

    public long getLogicalFileCount() {
        return logicalFileCount;
    }

    public void setLogicalFileCount(long logicalFileCount) {
        this.logicalFileCount = logicalFileCount;
    }

    public long getInterfaceFileCount() {
        return interfaceFileCount;
    }

    public void setInterfaceFileCount(long interfaceFileCount) {
        this.interfaceFileCount = interfaceFileCount;
    }

    public int getInputFactor() {
        return inputFactor;
    }

    public void setInputFactor(int inputFactor) {
        this.inputFactor = inputFactor;
    }

    public int getOutputFactor() {
        return outputFactor;
    }

    public void setOutputFactor(int outputFactor) {
        this.outputFactor = outputFactor;
    }

    public int getInquryFactor() {
        return inquryFactor;
    }

    public void setInquryFactor(int inquryFactor) {
        this.inquryFactor = inquryFactor;
    }

    public int getLogicalFileFactor() {
        return logicalFileFactor;
    }

    public void setLogicalFileFactor(int logicalFileFactor) {
        this.logicalFileFactor = logicalFileFactor;
    }

    public int getInterfaceFileFactor() {
        return interfaceFileFactor;
    }

    public void setInterfaceFileFactor(int interfaceFileFactor) {
        this.interfaceFileFactor = interfaceFileFactor;
    }

    public long getInputTotal() {
        return inputTotal;
    }

    public void setInputTotal(long inputTotal) {
        this.inputTotal = inputTotal;
    }

    public long getOutputTotal() {
        return outputTotal;
    }

    public void setOutputTotal(long outputTotal) {
        this.outputTotal = outputTotal;
    }

    public long getInquryTotal() {
        return inquryTotal;
    }

    public void setInquryTotal(long inquryTotal) {
        this.inquryTotal = inquryTotal;
    }

    public long getLogicalFileTotal() {
        return logicalFileTotal;
    }

    public void setLogicalFileTotal(long logicalFileTotal) {
        this.logicalFileTotal = logicalFileTotal;
    }

    public long getInterfaceFileTotal() {
        return interfaceFileTotal;
    }

    public void setInterfaceFileTotal(long interfaceFileTotal) {
        this.interfaceFileTotal = interfaceFileTotal;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getVafTotal() {
        return vafTotal;
    }

    public void setVafTotal(int vafTotal) {
        this.vafTotal = vafTotal;
    }

    public double getFunctionPointValue() {
        return functionPointValue;
    }

    public void setFunctionPointValue(double functionPointValue) {
        this.functionPointValue = functionPointValue;
    }

    public int[] getVafValue() {
        return vafValue;
    }

    public void setVafValue(int[] vafValue) {
        this.vafValue = vafValue;
    }
}
