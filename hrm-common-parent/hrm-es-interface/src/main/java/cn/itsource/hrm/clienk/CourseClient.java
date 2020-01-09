package cn.itsource.hrm.clienk;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.clienk.impl.CourseEsFallback;
import cn.itsource.hrm.doucument.CourseDocument;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/7 15:47
 * @Version v1.0
 */
@FeignClient(value = "ES-SERVICE",path = "/es",fallback = CourseEsFallback.class)
public interface CourseClient {

    @PostMapping("/create")
    public AjaxResult createIndexes(List<CourseDocument> courses);

    @PostMapping("/delete")
    public AjaxResult deleteIndexes(@RequestBody List<Long> ids);
}
