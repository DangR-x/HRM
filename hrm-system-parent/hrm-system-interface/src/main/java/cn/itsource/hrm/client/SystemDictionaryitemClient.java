package cn.itsource.hrm.client;

import cn.itsource.hrm.client.impl.SystemDictionaryitemImpl;
import cn.itsource.hrm.domain.Systemdictionaryitem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/8 21:35
 * @Version v1.0
 */
@FeignClient(value = "SYSTEM-SERVICE",path = "/systemdictionaryitem",fallback = SystemDictionaryitemImpl.class)
public interface SystemDictionaryitemClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    Systemdictionaryitem get(@PathVariable("id")Long id);

}
