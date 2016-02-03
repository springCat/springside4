package org.springcat.sample.web.api;

import ${package}.entity.User;
import ${package}.repository.mybatis.UserMybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/muser")
public class MyBatisUserController {

	@Autowired
	private UserMybatisDao userMybatisDao;

	@RequestMapping(method = RequestMethod.GET)
	public User list() {
		return userMybatisDao.get(1L);
	}

}
