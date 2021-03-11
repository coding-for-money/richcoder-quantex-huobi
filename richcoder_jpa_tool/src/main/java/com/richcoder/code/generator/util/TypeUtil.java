package com.richcoder.code.generator.util;

import java.sql.Types;

public class TypeUtil {

    public static String getType(int sqlType) {
        String className = String.class.getName();

        switch (sqlType) {

            case Types.NUMERIC:
            case Types.DECIMAL:
                className = java.math.BigDecimal.class.getName();
                break;

            case Types.BIT:
                className = Boolean.class.getName();
                break;

            case Types.TINYINT:
                className = Byte.class.getName();
                break;

            case Types.SMALLINT:
                className = Short.class.getName();
                break;

            case Types.INTEGER:
                className = Integer.class.getName();
                break;

            case Types.BIGINT:
                className = Long.class.getName();
                break;

            case Types.REAL:
                className = Float.class.getName();
                break;

            case Types.FLOAT:
            case Types.DOUBLE:
                className = Double.class.getName();
                break;

            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                className = "byte[]";
                break;

            case Types.DATE:
                className = java.util.Date.class.getName();
                break;

            case Types.TIME:
                className = java.util.Date.class.getName();
                break;

            case Types.TIMESTAMP:
                className = java.util.Date.class.getName();
                break;

            case Types.BLOB:
                className = java.sql.Blob.class.getName();
                break;

            case Types.CLOB:
                className = java.sql.Clob.class.getName();
                break;
        }

        return className;
    }


}
