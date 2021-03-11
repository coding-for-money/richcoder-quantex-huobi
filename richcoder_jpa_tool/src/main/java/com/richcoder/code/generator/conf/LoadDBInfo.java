package com.richcoder.code.generator.conf;


import com.richcoder.code.generator.util.DBUtil;
import com.richcoder.code.generator.util.StringUtil;
import com.richcoder.code.generator.util.TypeUtil;
import com.richcoder.code.generator.vo.ColumnInfo;
import com.richcoder.code.generator.vo.TableInfo;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoadDBInfo {

    private static final Connection conn;
    private static DatabaseMetaData dbMeta;

    static {
        conn = DBUtil.getConnection();
        try {
            dbMeta = conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<TableInfo> queryTableInfos(Set<String> skipTables, Set<String> initTables,
                                                  Set<String> skipTablePrefix) {
        ResultSet rs = null;
        List<TableInfo> list = new ArrayList<>();
        try {
            rs = dbMeta.getTables(conn.getCatalog(), "%", "%", new String[]{"TABLE", "VIEW"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");

                /**
                 * 如果初始化表不为空，并且不包含此表，跳过
                 */
                if (initTables != null && !initTables.contains(tableName)) {
                    continue;
                }

                /**
                 * 如果跳过表不为空，并且包含此表，跳过
                 */
                if (skipTables != null && skipTables.contains(tableName)) {
                    continue;
                }

                String comment = rs.getString("REMARKS");
                String primaryKey = queryPrimaryKey(tableName);
                List<ColumnInfo> columnInfos = queryColumnInfo(tableName);

                Set<String> columnTypes = new HashSet<>();
                for (ColumnInfo columnInfo : columnInfos) {
                    columnTypes.add(columnInfo.getAttrType());
                }
                String name = tableName.toLowerCase();
                if (skipTablePrefix != null) {
                    for (String string : skipTablePrefix) {

                        if (StringUtils.isBlank(string)) {
                            continue;
                        }

                        String skipTableString = string.toLowerCase();
                        if (name.startsWith(skipTableString)) {
                            name = name.replaceFirst(skipTableString, "");
                        }
                    }
                }
                String className = StringUtil.toCamelCase(name);
                className = StringUtil.firstCharToUpperCase(className);

                TableInfo tableMeta = new TableInfo(tableName, comment, className, primaryKey, columnInfos,
                        columnTypes);
                list.add(tableMeta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(rs, null, conn);
        }
        return list;
    }

    private static List<ColumnInfo> queryColumnInfo(String tableName) throws SQLException {
        ResultSet colRet = dbMeta.getColumns(conn.getCatalog(), "%", tableName, "%");

        List<ColumnInfo> list = new ArrayList<ColumnInfo>();
        try {
            while (colRet.next()) {
                String colName = colRet.getString("COLUMN_NAME");
                int len = colRet.getInt("CHAR_OCTET_LENGTH");
                String colClassName = colRet.getString("TYPE_NAME");
                String columnComment = colRet.getString("REMARKS");
                int sqlType = colRet.getInt("DATA_TYPE");
                //				TODO 将类型转化 移到工具类
                String attrType = TypeUtil.getType(sqlType);
                if (StringUtils.isBlank(attrType)) {
                    if (sqlType == Types.BLOB) {
                        attrType = "byte[]";
                    } else if (sqlType == Types.CLOB || sqlType == Types.NCLOB) {
                        attrType = "java.lang.String";
                    } else {
                        attrType = "java.lang.String";
                    }
                }

                String attrName = colName;
                if (colName.contains("_")) {
                    attrName = StringUtil.toCamelCase(colName.toLowerCase());
                }

                ColumnInfo columnInfo = new ColumnInfo(colName, colClassName, attrName, attrType,
                        columnComment);
                list.add(columnInfo);
            }
        } finally {
            colRet.close();
        }
        return list;
    }

    private static String queryPrimaryKey(String tableName) throws SQLException {
        String primaryKey = null;
        ResultSet rs = null;
        try {
            rs = dbMeta.getPrimaryKeys(conn.getCatalog(), "%", tableName);
            int num = 0;
            while (rs.next()) {
                primaryKey = rs.getString("COLUMN_NAME");
                num++;
            }
            if (num != 1) {
                System.err.println("表 " + tableName + " 中有" + num + "个主键。");
            }
        } finally {
            rs.close();
        }
        return primaryKey;
    }

    public static void main(String[] args) {
        List<TableInfo> list = LoadDBInfo.queryTableInfos(null, null, null);
        for (TableInfo tableInfo : list) {
            System.out.println(tableInfo);
            for (ColumnInfo columnInfo : tableInfo.getColumnInfos()) {
                System.out.println("\t" + columnInfo);
            }
        }
    }
}
