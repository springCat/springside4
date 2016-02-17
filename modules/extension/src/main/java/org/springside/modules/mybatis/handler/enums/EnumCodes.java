package org.springside.modules.mybatis.handler.enums;

/**
 * 枚举类工具集
 *
 * @author zhifeng
 */
public final class EnumCodes {
    
    private EnumCodes(){
        
    }

    /**
     * 根据枚举类编码获取对应的枚举类
     *
     * @param <E>
     * @param enumClass
     * @param code
     * @return
     */
    public static <E extends EnumCode> E valueOf(Class<E> enumClass, Integer code) {
        for (E enumeration : enumClass.getEnumConstants()) {
            if (enumeration.code()==code) {
                return enumeration;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("non-existing code [");
        sb.append(code);
        sb.append("] in enum type [");
        sb.append(enumClass.getName());
        sb.append("]");
        throw new IllegalArgumentException(sb.toString());
    }

}
