package org.springcat.sample.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springcat.sample.entity.Task;
import org.springcat.sample.entity.TaskCondition;
import org.springside.modules.mybatis.annotation.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface TaskMapper {
    int countByExample(TaskCondition example);

    int deleteByExample(TaskCondition example);

    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExampleWithRowbounds(TaskCondition example, RowBounds rowBounds);

    List<Task> selectByExample(TaskCondition example);

    Task selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskCondition example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskCondition example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}