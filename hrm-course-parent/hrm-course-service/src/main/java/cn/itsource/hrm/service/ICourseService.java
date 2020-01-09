package cn.itsource.hrm.service;

import cn.itsource.hrm.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DangR-X
 * @since 2020-01-07
 */
public interface ICourseService extends IService<Course> {

    void uponline(List<Long> ids);

}
