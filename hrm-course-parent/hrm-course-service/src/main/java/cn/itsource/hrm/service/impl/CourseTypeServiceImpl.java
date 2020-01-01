package cn.itsource.hrm.service.impl;

import cn.itsource.basic.util.PageList;
import cn.itsource.hrm.client.RedisClient;
import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.mapper.CourseTypeMapper;
import cn.itsource.hrm.query.CourseTypeQuery;
import cn.itsource.hrm.service.ICourseTypeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.DoubleRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author DangR-X
 * @since 2019-12-26
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private RedisClient redisClient;

    private final String COURSE_TYPE="hrm:courseType:dataTree";

    @Override
    public List<CourseType> getTreeDate() {
       /* List<CourseType> parent = getParentBypid(0L);*/
       /* List<CourseType> parent = getFirstLenvenMap();*/
        String courseTypesStr = redisClient.get(COURSE_TYPE);
        List<CourseType> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(courseTypesStr)){
            list = JSONObject.parseArray(courseTypesStr, CourseType.class);
        }else {
            list = getFirstLenvenMap();
            String jsonString = JSONObject.toJSONString(list);
            redisClient.set(COURSE_TYPE, jsonString);
        }


        return list;
    }
    //根据数据库中的parentid可以快速确定第一个父级，表中设计的parentid=0的都是第一父级
    //用递归的方法
    public List<CourseType> getParentBypid(Long pid){

        List<CourseType> children = courseTypeMapper.selectList(new QueryWrapper<CourseType>().eq("pid", pid));

        //根据parentid查询出来的是空的话就说明
        if(children==null||children.size()==0){
            return null;
        }
        for (CourseType child : children) {
            List<CourseType> childs = getParentBypid(child.getId());
            child.setChildren(childs);
        }

        return children;
    }
    //循环嵌套的方法
    public List<CourseType> getFirstlenven(){

        List<CourseType> firstlenven = new ArrayList<>();

        //先查询所有的数据
        List<CourseType> courseTypes = courseTypeMapper.selectList(null);

        for (CourseType courseType : courseTypes) {
            if(courseType.getPid()==0L){
                firstlenven.add(courseType);
            }else {
                for (CourseType course : courseTypes) {
                    if(course.getId().longValue()==courseType.getPid().longValue()){
                        course.getChildren().add(courseType);
                    }
                }
            }
        }
        return firstlenven;
    }

    //map循环
    public List<CourseType> getFirstLenvenMap(){

        List<CourseType> firstlenven = new ArrayList<>();
        List<CourseType> courseTypes = courseTypeMapper.selectList(null);

        Map<Long, CourseType> map = new HashMap<>();

        for (CourseType courseType : courseTypes) {
            map.put(courseType.getId(), courseType);
        }

        for (CourseType courseType : courseTypes) {
            //如果该courseType的父级id为o那么这个就是父级元素
            if(courseType.getPid().longValue()==0L){
                firstlenven.add(courseType);

            }else {
                //否则依据父级id查询出父级对象，然后放入父级对象的children中
                CourseType parent = map.get(courseType.getPid());
                if(parent!=null){
                    parent.getChildren().add(courseType);
                }
            }

        }
        return firstlenven;
    }

    /**
     * 增删改的同步操作
     * 有变动之后就从数据库中查出来然后转为json字符串保存进redis中去
     */
    public void snycource(){
        List<CourseType> parent = getFirstLenvenMap();
        String jsonString = JSONObject.toJSONString(parent);
        redisClient.set(COURSE_TYPE, jsonString);
    }

}
