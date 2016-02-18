#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.dao.base;

import org.apache.ibatis.annotations.Param;
import ${package}.entity.base.User;
import ${package}.entity.base.UserCondition;
import org.springside.modules.mybatis.annotation.MyBatisRepository;
import java.util.List;

@MyBatisRepository
public interface UserMapper {
    int countByCondition(UserCondition condition);

    int deleteByCondition(UserCondition condition);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByCondition(UserCondition condition);

    User selectByPrimaryKey(Long id);

    int updateByConditionSelective(@Param("record") User record, @Param("condition") UserCondition condition);

    int updateByCondition(@Param("record") User record, @Param("condition") UserCondition condition);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}