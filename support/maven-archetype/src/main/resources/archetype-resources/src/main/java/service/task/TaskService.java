#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package ${package}.service.task;

import ${package}.dao.base.TaskMapper;
import ${package}.entity.base.Task;
import ${package}.entity.base.TaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
public class TaskService {

	@Autowired
	private TaskMapper taskMapper;

	public Task getTask(Long id) {
		return taskMapper.selectByPrimaryKey(id);
	}

	public void saveTask(Task entity) {
		taskMapper.insertSelective(entity);
	}

	public void deleteTask(Long id) {
		taskMapper.deleteByPrimaryKey(id);
	}

	public List<Task> getAllTask() {
		return taskMapper.selectByCondition(null);
	}

	public List<Task> getUserTask(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {

		TaskCondition taskCondition = new TaskCondition();

		if ("auto".equals(sortType)) {
			taskCondition.setOrderByClause("id desc");
		} else if ("title".equals(sortType)) {
			taskCondition.setOrderByClause("title asc");
		}
		taskCondition.createCriteria().andUserIdEqualTo(userId);

		return taskMapper.selectByCondition(taskCondition).subList((pageNumber-1)*pageSize,pageNumber*pageSize);
	}

	/**
	 * 创建分页请求.
	 */
//	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
//		Sort sort = null;
//		if ("auto".equals(sortType)) {
//			sort = new Sort(Direction.DESC, "id");
//		} else if ("title".equals(sortType)) {
//			sort = new Sort(Direction.ASC, "title");
//		}
//
//		return new PageRequest(pageNumber - 1, pagzSize, sort);
//	}

	/**
	 * 创建动态查询条件组合.
	 */
//	private Specification<Task> buildSpecification(Long userId, Map<String, Object> searchParams) {
//		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
//		Specification<Task> spec = DynamicSpecifications.bySearchFilter(filters.values(), Task.class);
//		return spec;
//	}
}
