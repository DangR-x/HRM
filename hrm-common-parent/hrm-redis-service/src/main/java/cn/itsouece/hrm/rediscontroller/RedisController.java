package cn.itsouece.hrm.rediscontroller;

import cn.itsouece.hrm.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2019/12/27 19:06
 * @Version v1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;


    /**
     *
     * @param key
     * @param value
     */
    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public void set(String key,String value){
        redisUtils.set(key,value );
    }

    /**
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(String key){
        String value = redisUtils.get(key);
        return value;
    }


}
