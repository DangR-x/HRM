package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static cn.itsource.hrm.util.FastDfsApiOpr.upload;

/**
 * @Description TODO
 * @Author dangran
 * @Date 2020/1/4 16:28
 * @Version v1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @PostMapping("/upload")
    public AjaxResult uploadfile(MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        int indexOf = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(indexOf + 1);

        try {
            String file_id = upload(file.getBytes(), extName);
            return AjaxResult.me().setResultObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败！");
        }
    }

    @DeleteMapping("/delete")
    public AjaxResult delete(String file_id){
        String fileid = file_id.substring(1);
        int indexOf = fileid.indexOf("/");
        String groupName=fileid.substring(0,indexOf);
        String fileName = fileid.substring(indexOf+1);

        try {
            FastDfsApiOpr.delete(groupName, fileName);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败！");
        }
    }


}
