package org.springcat.sample.exception;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by springcat on 16/2/19.
 */
@RestController
@RequestMapping(value = "/errors")
public class ErrorRestController {

    @RequestMapping(value = "500",method = RequestMethod.GET)
    public Map err500(){
        Map m = Maps.newHashMap();
        m.put(500,"出错了");
        return m;
    }
}
