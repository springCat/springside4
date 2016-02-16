package org.springcat.sample.web.api;

import org.apache.commons.lang3.RandomUtils;
import org.springcat.sample.dao.UserMapper;
import org.springcat.sample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/muser")
public class MyBatisUserController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(method = RequestMethod.GET)
	public User list() {
		return userMapper.selectByPrimaryKey(1L);
	}

	@Transactional
	@RequestMapping(value = "tx", method = RequestMethod.GET)
	public User tx() throws Exception {
		User user = userMapper.selectByPrimaryKey(1L);
		user.setId(null);
		user.setLoginName(RandomUtils.nextInt(1,100)+"");
		userMapper.insertSelective(user);
		throw new Exception("1111");
//		return user;
	}

}
