package cn.itsource.hrm.client;


import cn.itsource.hrm.client.impl.RedisClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2019/12/27 19:25
 * @Version v1.0
 */

@FeignClient(value = "REDIS-SERVICE",path = "/redis",fallback = RedisClientFallback.class)
public interface RedisClient {

    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public void set(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(@RequestParam("key") String key);


}
