package cn.itsource.hrm.clienk.impl;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.doucument.CourseDocument;
import cn.itsource.hrm.clienk.CourseClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/7 15:50
 * @Version v1.0
 */
@Component
public class CourseEsFallback implements CourseClient {
    @Override
    public AjaxResult createIndexes(List<CourseDocument> courses) {
        return null;
    }

    @Override
    public AjaxResult deleteIndexes(List<Long> ids) {
        return null;
    }
}
