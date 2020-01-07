package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.doucument.CourseDocument;
import cn.itsource.hrm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/7 15:38
 * @Version v1.0
 */
@RestController
@RequestMapping("/es")
public class CourseEscontroller {


    @Autowired
    private CourseRepository repository;

    @PostMapping("/create")
    public AjaxResult createIndexes(List<CourseDocument> courses){
        try {
            repository.saveAll(courses);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("创建失败！");
        }
    }

    @PostMapping("/delete")
    public AjaxResult deleteIndexes(@RequestBody List<Long> ids){
            try {
                for (Long id : ids) {
                        repository.deleteById(id);
                }
                return AjaxResult.me();
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.me().setSuccess(false).setMessage("删除失败！");
            }
    }




}
