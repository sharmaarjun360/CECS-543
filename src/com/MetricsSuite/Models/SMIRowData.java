package com.MetricsSuite.Models;

import java.io.Serializable;

public class SMIRowData implements Serializable {

    private double smi;
    private int moduleAdded;
    private int moduleChanged;
    private int moduleDeleted;
    private int totalModules;

    public SMIRowData(double smi, int moduleAdded, int moduleChanged, int moduleDeleted, int totalModules){
        this.smi = smi;
        this.moduleAdded = moduleAdded;
        this.moduleChanged = moduleChanged;
        this.moduleDeleted = moduleDeleted;
        this.totalModules = totalModules;
    }

    public double getSmi() {
        return smi;
    }

    public void setSmi(double smi) {
        this.smi = smi;
    }

    public int getModuleAdded() {
        return moduleAdded;
    }

    public void setModuleAdded(int moduleAdded) {
        this.moduleAdded = moduleAdded;
    }

    public int getModuleChanged() {
        return moduleChanged;
    }

    public void setModuleChanged(int moduleChanged) {
        this.moduleChanged = moduleChanged;
    }

    public int getModuleDeleted() {
        return moduleDeleted;
    }

    public void setModuleDeleted(int moduleDeleted) {
        this.moduleDeleted = moduleDeleted;
    }

    public int getTotalModules() {
        return totalModules;
    }

    public void setTotalModules(int totalModules) {
        this.totalModules = totalModules;
    }

    public String toString(){
        return "SMI: "+this.smi+" Module Added: "+this.moduleAdded+" Module Changed: "+this.moduleChanged
                +" Module Deleted: "+this.moduleDeleted+" Total Modules: "+this.totalModules;
    }
}
