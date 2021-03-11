package com.richcoder.code.generator.vo;


import com.richcoder.code.generator.util.StringUtil;

import java.util.List;
import java.util.Set;

public class TableInfo {

    private final String classPackageName;
    private String tableName;
    private String tableComment;
    private String className;
    private String shortStartClassName;
    private String primaryKey;

    private List<ColumnInfo> columnInfos;

    /**
     * 增加头文件导入时使用
     */
    private Set<String> columnTypes;

    public TableInfo(String tableName, String tableComment, String className,
                     String primaryKey, List<ColumnInfo> columnInfos, Set<String> columnTypes) {
        super();
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.className = className;
        this.primaryKey = primaryKey;
        this.columnInfos = columnInfos;
        this.columnTypes = columnTypes;
        this.shortStartClassName = StringUtil.firstCharToLowerCase(className);
        this.classPackageName = StringUtil.toPackageName(this.shortStartClassName);
    }

    public String getShortStartClassName() {
        return shortStartClassName;
    }

    public void setShortStartClassName(String shortStartClassName) {
        this.shortStartClassName = shortStartClassName;
    }

    public Set<String> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(Set<String> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnInfo> getColumnInfos() {
        return columnInfos;
    }

    public void setColumnInfos(List<ColumnInfo> columnInfos) {
        this.columnInfos = columnInfos;
    }

    public String getClassPackageName() {
        return classPackageName;
    }

    @Override
    public String toString() {
        return "TableInfo [tableName=" + tableName + ", tableComment="
                + tableComment + ", className=" + className + ", primaryKey="
                + primaryKey + "]";
    }

}
