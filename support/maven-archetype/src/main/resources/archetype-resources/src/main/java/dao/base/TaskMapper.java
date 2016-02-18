#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.dao.base;

import org.apache.ibatis.annotations.Param;
import ${package}.entity.base.Task;
import ${package}.entity.base.TaskCondition;
import org.springside.modules.mybatis.annotation.MyBatisRepository;
import java.util.List;

@MyBatisRepository
public interface TaskMapper {
    int countByCondition(TaskCondition condition);

    int deleteByCondition(TaskCondition condition);

    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByCondition(TaskCondition condition);

    Task selectByPrimaryKey(Long id);

    int updateByConditionSelective(@Param("record") Task record, @Param("condition") TaskCondition condition);

    int updateByCondition(@Param("record") Task record, @Param("condition") TaskCondition condition);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}