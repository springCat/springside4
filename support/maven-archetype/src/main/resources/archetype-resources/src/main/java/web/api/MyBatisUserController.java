#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package ${package}.web.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${package}.dao.base.UserMapper;
import ${package}.entity.base.User;
import ${package}.rest.RestException;
import ${package}.entity.UserExt;
import ${package}.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

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
	public UserExt show(@RequestBody @Valid UserExt user){
		return user;
	}

	@RequestMapping(value = "para",method = RequestMethod.POST)
	public String para(@RequestParam int name){
		return "ok";
	}

	@RequestMapping(value = "exception",method = RequestMethod.POST)
	public String exception(){
		throw new RestException(HttpStatus.BAD_GATEWAY,"11");

	}

	@RequestMapping(value = "/page",method = RequestMethod.POST)
	public PageInfo page(){

		PageHelper.startPage(1, 10);
		PageHelper.orderBy("login_name desc");
		List<User> list = userMapper.selectByCondition(null);

		PageInfo page = new PageInfo(list);

		return page;
	}
}
