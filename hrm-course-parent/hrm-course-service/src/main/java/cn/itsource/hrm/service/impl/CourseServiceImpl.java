package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.client.SystemDictionaryitemClient;
import cn.itsource.hrm.domain.*;
import cn.itsource.hrm.clienk.CourseClient;
import cn.itsource.hrm.doucument.CourseDocument;
import cn.itsource.hrm.mapper.*;
import cn.itsource.hrm.service.ICourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DangR-X
 * @since 2020-01-07
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private SystemDictionaryitemClient systemDictionaryitemClient;

    @Autowired
    private CourseDetailMapper courseDetailMapper;

    @Autowired
    private CourseResourceMapper courseResourceMapper;

    @Autowired
    private CourseMarketMapper courseMarketMapper;


    @Override
    @Transactional
    public void uponline(List<Long> ids) {
        List<Course> courses = new ArrayList<>();
        //根据id查询出数据信息
        for (Long id : ids) {
            Course course = courseMapper.selectById(id);
            courses.add(course);
        }
        //将course转为coursedocument
        List<CourseDocument> courseDocuments = coursesToDocu(courses);
        //根据id修改数据的发布状态信息
        courseMapper.online(ids,System.currentTimeMillis() );
        //调用es创建document
        courseClient.createIndexes(courseDocuments);
    }

    public List<CourseDocument> coursesToDocu(List<Course> list){
        List<CourseDocument> courseDocument = new ArrayList<>();
        for (Course course : list) {
            CourseDocument document = courseToDocu(course);
            courseDocument.add(document);
        }
        return courseDocument;
    }

    public CourseDocument courseToDocu(Course c){
        CourseDocument doucment = new CourseDocument();
        //属性的设置
        BeanUtils.copyProperties(c, doucment);
        Long courseTypeId = c.getCourseTypeId();
        CourseType courseType = courseTypeMapper.selectById(courseTypeId);
        doucment.setCourseTypeName(courseType.getName());
        doucment.setGradeId(c.getGrade());
        Systemdictionaryitem systemdictionaryitem = systemDictionaryitemClient.get(c.getGrade());
        doucment.setGradeName(systemdictionaryitem==null?null:systemdictionaryitem.getName());
        CourseDetail courseDetail = courseDetailMapper.selectOne(
                new QueryWrapper<CourseDetail>()
                        .eq("course_id", c.getId())
        );
        doucment.setIntro(courseDetail==null?null:courseDetail.getIntro());
        CourseResource courseResource = courseResourceMapper.selectOne(
                new QueryWrapper<CourseResource>().eq("course_id", c.getId())
        );
        doucment.setResources(courseResource==null?null:courseResource.getResources());

        CourseMarket courseMarket = courseMarketMapper.selectOne(
                new QueryWrapper<CourseMarket>()
                        .eq("course_id", c.getId())
        );

        BeanUtils.copyProperties(courseMarket==null?new CourseMarket():courseMarket, doucment,"id");

        //看关键字查询什么字段
        //机构名称  课程类型名称  课程名称
        String all = c.getTenantName()+" "+(courseType==null?"":courseType.getName())+" "+c.getName();
        doucment.setAll(all);

        return doucment;
    }
}
