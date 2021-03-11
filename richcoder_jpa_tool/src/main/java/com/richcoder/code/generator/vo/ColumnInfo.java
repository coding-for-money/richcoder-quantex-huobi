package com.richcoder.code.generator.vo;

public class ColumnInfo {

    /**
     * 表中字段名
     */
    private String field;
    /**
     * 表中类型
     */
    private String type;
    /**
     * 实体中属性名
     */
    private String attrName;
    /**
     * 实体中类型（带包名）
     */
    private String attrType;
    /**
     * 实体中类型（不带包名）
     */
    private String attrShortType;
    /**
     * 备注
     */
    private String comment;
    /**
     * 是否主键
     */
    private boolean primary;

    public ColumnInfo() {
    }

    public ColumnInfo(String field, String type, String attrName,
                      String attrType, String comment) {
        super();
        this.field = field;
        this.type = type;
        this.attrName = attrName;
        this.attrType = attrType;
        this.attrShortType = attrType.substring(attrType.lastIndexOf(".") + 1);
        this.comment = comment;
    }

    public String getAttrShortType() {
        return attrShortType;
    }

    public void setAttrShortType(String attrShortType) {
        this.attrShortType = attrShortType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public String toString() {
        return "ColumnInfo [field=" + field + ", type=" + type + ", attrName="
                + attrName + ", attrType=" + attrType + ", comment=" + comment
                + "]";
    }

}
