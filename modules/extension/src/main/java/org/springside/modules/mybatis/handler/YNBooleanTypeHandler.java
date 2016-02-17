package org.springside.modules.mybatis.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现数据库中的Y/N值与Boolean之间相互转换
 *
 */
@MappedTypes(Boolean.class)
@MappedJdbcTypes({JdbcType.CHAR, JdbcType.NULL})
public class YNBooleanTypeHandler extends BaseTypeHandler<Boolean> {

    private static final String FALSE = "N";
    private static final String TRUE = "Y";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter ? TRUE : FALSE);
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs.getString(columnName));
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs.getString(columnIndex));
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getNullableResult(cs.getString(columnIndex));
    }

    private Boolean getNullableResult(String s) {
        return StringUtils.isBlank(s) ? null : TRUE.equalsIgnoreCase(s.trim());
    }
}
