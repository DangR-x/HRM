package cn.itsource.hrm.repository;

import cn.itsource.hrm.doucument.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/7 15:35
 * @Version v1.0
 */
@Repository
public interface CourseRepository extends ElasticsearchRepository<CourseDocument,Long> {
}
