package org.springcat.sample.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springcat.sample.entity.User;
import org.springcat.sample.entity.UserCondition;
import org.springside.modules.mybatis.annotation.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface UserMapper {
    int countByExample(UserCondition example);

    int deleteByExample(UserCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithRowbounds(UserCondition example, RowBounds rowBounds);

    List<User> selectByExample(UserCondition example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserCondition example);

    int updateByExample(@Param("record") User record, @Param("example") UserCondition example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}