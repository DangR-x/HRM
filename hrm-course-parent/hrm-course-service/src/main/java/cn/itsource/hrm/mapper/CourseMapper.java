package cn.itsource.hrm.mapper;

import cn.itsource.hrm.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DangR-X
 * @since 2020-01-07
 */
@Component
public interface CourseMapper extends BaseMapper<Course> {

    void online(@Param("ids") List<Long> ids, @Param("time") long time);

}
