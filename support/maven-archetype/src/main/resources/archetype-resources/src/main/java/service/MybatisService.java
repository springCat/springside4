#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import org.apache.commons.lang3.RandomUtils;
import ${package}.dao.base.UserMapper;
import ${package}.entity.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by springcat on 16/2/16.
 */
@Transactional
@Component
public class MybatisService {

    @Autowired
    private UserMapper userMapper;

    public User testTx(){
        User user = userMapper.selectByPrimaryKey(1L);
        user.setId(null);
        user.setLoginName(RandomUtils.nextInt(1,100)+"");
        userMapper.insertSelective(user);
//        throw new ServiceException("1111");
        return user;

    }
}
