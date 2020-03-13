package com.MetricsSuite.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SMIData implements Serializable {

    private List<SMIRowData> rowList;

    public SMIData(){
        this.rowList = new ArrayList<>();
    }

    public List<SMIRowData> getRowList() {
        return rowList;
    }

    public void setRowList(List<SMIRowData> rowList) {
        this.rowList = rowList;
    }
}
