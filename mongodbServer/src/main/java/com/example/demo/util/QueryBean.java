package com.example.demo.util;

import java.io.Serializable;
import java.util.Arrays;

public class QueryBean implements Serializable {
    private String key;
    private String op;
    private String val;
    private String[] keys;
    private String[] vals;
    private transient Object startValue;
    private transient Object endValue;
    private boolean flag;
    private String type;

    public QueryBean(String key, Object startValue, Object endValue, boolean flag) {
        this.key = key;
        this.startValue = startValue;
        this.endValue = endValue;
        this.flag = flag;
    }

    public QueryBean(String key, Object startValue, Object endValue, String type) {
        this.key = key;
        this.startValue = startValue;
        this.endValue = endValue;
        this.type = type;
    }

    public QueryBean(String key, String type, Object startValue) {
        this.key = key;
        this.type = type;
        this.startValue = startValue;
    }

    public QueryBean(String key, String op, String val) {
        this.key = key;
        this.op = op;
        this.val = val;
    }

    public QueryBean(String key, String op, String[] vals) {
        this.key = key;
        this.op = op;
        this.vals = vals;
    }

    public QueryBean(String[] keys, String op, String[] vals) {
        this.op = op;
        this.keys = keys;
        this.vals = vals;
    }

    public QueryBean() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOp() {
        return this.op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getVal() {
        return this.val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String[] getKeys() {
        return this.keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String[] getVals() {
        return this.vals;
    }

    public void setVals(String[] vals) {
        this.vals = vals;
    }

    public Object getStartValue() {
        return this.startValue;
    }

    public void setStartValue(Object startValue) {
        this.startValue = startValue;
    }

    public Object getEndValue() {
        return this.endValue;
    }

    public void setEndValue(Object endValue) {
        this.endValue = endValue;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "{'key':'" + this.key + '\'' + ", 'op':'" + this.op + '\'' + ", 'val':'" + this.val + '\'' + ", 'startValue':'" + this.startValue + '\'' + ", 'endValue':'" + this.endValue + '\'' + ", 'type':'" + this.type + '\'' + ", 'flag':'" + this.flag + '\'' + ", 'keys':" + Arrays.toString(addQuotes(this.keys)) + ", 'vals':" + Arrays.toString(addQuotes(this.vals)) + "}";
    }

    public static String[] addQuotes(String[] array) {
        if (array != null && array.length > 0) {
            for(int i = 0; i < array.length; ++i) {
                array[i] = "'" + array[i] + "'";
            }
        }

        return array;
    }
}
