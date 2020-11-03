package com.example.demo.util;
import java.io.Serializable;
import java.util.Set;

public class Pager implements Serializable {
    private int pageSize = 20;
    private long pageNum = 1L;
    private int sortFlag = 0;
    private int flag = 0;
    private String maxRow;
    private String minRow;
    private String fieldCompare;
    private String[] sortFields;
    private boolean isCount;
    private boolean isSum;
    private boolean isNexHasData;
    private boolean isUpHasData;
    private String source;
    private String ip;
    private String msg;
    private String extString;
    private boolean extBoolean;
    private int extInt;
    private String queryData;
    private String dataField;
    private String collectionTable;
    private Set<String> queryFields;
    private boolean returnJson;

    public Pager() {
    }

    public boolean isReturnJson() {
        return this.returnJson;
    }

    public void setReturnJson(boolean returnJson) {
        this.returnJson = returnJson;
    }

    public String getCollectionTable() {
        return this.collectionTable;
    }

    public Set<String> getQueryFields() {
        return this.queryFields;
    }

    public void setQueryFields(Set<String> queryFields) {
        this.queryFields = queryFields;
    }

    public void setCollectionTable(String collectionTable) {
        this.collectionTable = collectionTable;
    }

    public String getDataField() {
        return this.dataField;
    }

    public void setDataField(String dataField) {
        this.dataField = dataField;
    }

    public String getQueryData() {
        return this.queryData;
    }

    public void setQueryData(String queryData) {
        this.queryData = queryData;
    }

    public boolean isNexHasData() {
        return this.isNexHasData;
    }

    public void setNexHasData(boolean nexHasData) {
        this.isNexHasData = nexHasData;
    }

    public boolean isUpHasData() {
        return this.isUpHasData;
    }

    public void setUpHasData(boolean upHasData) {
        this.isUpHasData = upHasData;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String[] getSortFields() {
        return this.sortFields;
    }

    public void setSortFields(String[] sortFields) {
        this.sortFields = sortFields;
    }

    public boolean isCount() {
        return this.isCount;
    }

    public void setCount(boolean count) {
        this.isCount = count;
    }

    public boolean isSum() {
        return this.isSum;
    }

    public void setSum(boolean sum) {
        this.isSum = sum;
    }

    public String getExtString() {
        return this.extString;
    }

    public void setExtString(String extString) {
        this.extString = extString;
    }

    public boolean isExtBoolean() {
        return this.extBoolean;
    }

    public void setExtBoolean(boolean extBoolean) {
        this.extBoolean = extBoolean;
    }

    public int getExtInt() {
        return this.extInt;
    }

    public void setExtInt(int extInt) {
        this.extInt = extInt;
    }

    public String getFieldCompare() {
        return this.fieldCompare;
    }

    public void setFieldCompare(String fieldCompare) {
        this.fieldCompare = fieldCompare;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public int getSortFlag() {
        return this.sortFlag;
    }

    public void setSortFlag(int sortFlag) {
        this.sortFlag = sortFlag;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMaxRow() {
        return this.maxRow;
    }

    public void setMaxRow(String maxRow) {
        this.maxRow = maxRow;
    }

    public String getMinRow() {
        return this.minRow;
    }

    public void setMinRow(String minRow) {
        this.minRow = minRow;
    }
}
