#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package ${package}.service.account;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ${package}.dao.base.TaskMapper;
import ${package}.dao.base.UserMapper;
import ${package}.entity.base.TaskCondition;
import ${package}.entity.base.User;
import ${package}.entity.base.UserCondition;
import ${package}.entity.UserExt;
import ${package}.service.ServiceException;
import ${package}.service.account.ShiroDbRealm.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import java.util.List;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@DependsOn(value = "userMapper")
@Component
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TaskMapper taskMapper;

	private Clock clock = Clock.DEFAULT;

	public List<User> getAllUser() {
		return (List<User>) userMapper.selectByCondition(null);

	}

	public User getUser(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public User findUserByLoginName(String loginName) {
		UserCondition userCondition = new UserCondition();
		userCondition.createCriteria().andLoginNameEqualTo(loginName);

		return userMapper.selectByCondition(userCondition).get(0);
	}

	public void registerUser(UserExt user) {
		entryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(clock.getCurrentDate());

		userMapper.insertSelective(user);
	}

	public void updateUser(UserExt user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userMapper.insertSelective(user);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userMapper.deleteByPrimaryKey(id);
		TaskCondition taskCondition = new TaskCondition();
		taskCondition.createCriteria().andUserIdEqualTo(id);
		taskMapper.deleteByCondition(taskCondition);

	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(UserExt user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

}
