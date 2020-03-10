package com.MetricsSuite.Models;

import java.io.Serializable;

public class FunctionPointData implements Serializable {

    long externalInputCount;
    long externalOutputCount;
    long externalInquiriesCount;
    long internalLogicalFileCount;
    long externalInterfaceFileCount;

    int inputFactor;
    int outputFactor;
    int inquiryFactor;
    int logicalFileFactor;
    int interfaceFileFactor;

    long inputTotal;
    long outputTotal;
    long inquiryTotal;
    long logicalFileTotal;
    long interfaceFileTotal;

    long totalCount;
    int vafTotal = 0;

    int[] vafValue = new int[14];

    long functionPointValue;

    String selectedLanguage;
    int languageCodeSize;
    long totalCodeSize;

    public FunctionPointData(String selectedLanguage, int codeSize){
        this.selectedLanguage = selectedLanguage;
        this.languageCodeSize = codeSize;
    }

    public long getExternalInputCount() {
        return externalInputCount;
    }

    public void setExternalInputCount(long externalInputCount) {
        this.externalInputCount = externalInputCount;
    }

    public long getExternalOutputCount() {
        return externalOutputCount;
    }

    public void setExternalOutputCount(long externalOutputCount) {
        this.externalOutputCount = externalOutputCount;
    }

    public long getExternalInquiriesCount() {
        return externalInquiriesCount;
    }

    public void setExternalInquiriesCount(long externalInquiriesCount) {
        this.externalInquiriesCount = externalInquiriesCount;
    }

    public long getInternalLogicalFileCount() {
        return internalLogicalFileCount;
    }

    public void setInternalLogicalFileCount(long internalLogicalFileCount) {
        this.internalLogicalFileCount = internalLogicalFileCount;
    }

    public long getExternalInterfaceFileCount() {
        return externalInterfaceFileCount;
    }

    public void setExternalInterfaceFileCount(long externalInterfaceFileCount) {
        this.externalInterfaceFileCount = externalInterfaceFileCount;
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

    public int getInquiryFactor() {
        return inquiryFactor;
    }

    public void setInquiryFactor(int inquiryFactor) {
        this.inquiryFactor = inquiryFactor;
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

    public long getInquiryTotal() {
        return inquiryTotal;
    }

    public void setInquiryTotal(long inquiryTotal) {
        this.inquiryTotal = inquiryTotal;
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

    public long getFunctionPointValue() {
        return functionPointValue;
    }

    public void setFunctionPointValue(long functionPointValue) {
        this.functionPointValue = functionPointValue;
    }

    public int[] getVafValue() {
        return vafValue;
    }

    public void setVafValue(int[] vafValue) {
        this.vafValue = vafValue;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public int getLanguageCodeSize() {
        return languageCodeSize;
    }

    public void setLanguageCodeSize(int languageCodeSize) {
        this.languageCodeSize = languageCodeSize;
    }

    public long getTotalCodeSize() {
        return totalCodeSize;
    }

    public void setTotalCodeSize(long totalCodeSize) {
        this.totalCodeSize = totalCodeSize;
    }
}
