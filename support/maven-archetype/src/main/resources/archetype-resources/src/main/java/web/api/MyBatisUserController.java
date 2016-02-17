#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package ${package}.web.api;

import org.springcat.sample.dao.UserMapper;
import org.springcat.sample.entity.User;
import org.springcat.sample.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springcat.sample.entity.UserExt;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/muser")
public class MyBatisUserController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MybatisService mybatisService;

	@RequestMapping(method = RequestMethod.GET)
	public User list() {
		return userMapper.selectByPrimaryKey(1L);
	}


	@RequestMapping(value = "tx", method = RequestMethod.GET)
	public User tx()  {
		User user = mybatisService.testTx();
		return user;
	}

	@RequestMapping(method = RequestMethod.POST)
	public UserExt show(@RequestBody UserExt user){
		return user;
	}

}
