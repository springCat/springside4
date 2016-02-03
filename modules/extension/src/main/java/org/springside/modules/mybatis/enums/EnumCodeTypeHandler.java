package org.springside.modules.mybatis.enums;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举类型转换
 *
 * @author zhifeng
 * @param <E>
 */
public abstract class EnumCodeTypeHandler<E extends EnumCode> extends BaseTypeHandler<EnumCode> {

    private final Class<E> type;

    public EnumCodeTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnumCode parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setInt(i, parameter.code());
        } else {
            ps.setObject(i, parameter.code(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public EnumCode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer s = rs.getInt(columnName);
        return  EnumCodes.valueOf(type, s);
    }

    @Override
    public EnumCode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer s = rs.getInt(columnIndex);
        return EnumCodes.valueOf(type, s);
    }

    @Override
    public EnumCode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer s = cs.getInt(columnIndex);
        return EnumCodes.valueOf(type, s);
    }
}
