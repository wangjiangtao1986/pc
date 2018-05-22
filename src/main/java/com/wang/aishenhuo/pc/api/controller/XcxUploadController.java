package com.wang.aishenhuo.pc.api.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 *
 */
@RestController
public class XcxUploadController {

	@SuppressWarnings("deprecation")
	@RequestMapping("/upload")
	public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		int status = 0;
		
		String filePath = "";
		
		JSONObject j = new JSONObject();

		String fileName = "";
        if(file.isEmpty()) {
        	status = 0;
        } else {
            fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            filePath = "uploads" + File.separator + fileName;
            System.out.println(request.getRealPath("") + filePath);
            File dest = new File(request.getRealPath("") + filePath);
            if(!dest.getParentFile().exists()){//判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);//保存文件
                status = 1;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                status = 0;
            } catch (Exception e) {
                e.printStackTrace();
                status = 0;
            }
        }
		if(status>0) {
			j.put("status", 1);
			j.put("msg", "上传成功");
			j.put("data", "uploads/" + fileName);
		} else {
			j.put("status", 0);
			j.put("msg", "上传失败");
		}
		return j;
	}
}

//	public function index()
//	{
//		$upload = new \Think\Upload();// 实例化上传类
//	    $upload->maxSize   =     3145728 ;// 设置附件上传大小
//	    $upload->exts      =     array('jpg', 'gif', 'png', 'jpeg');// 设置附件上传类型
//	    $upload->rootPath  =     '/Uploads/'; // 设置附件上传根目录
//	    $upload->savePath  =     ''; // 设置附件上传（子）目录
//	    // 上传文件 
//	    $info   =   $upload->upload();
//	    if(!$info) {// 上传错误提示错误信息
//	    	$result['status'] = 0;
//	    	$result['msg'] = $upload->getError();
//	    }else{// 上传成功
//	        $result['status'] = 1;
//	        $result['msg'] = '上传成功';
//	        $result['data'] = 'https://xcx.codems.cn/Uploads/'.$info['file']['savepath'].$info['file']['savename'];
//	    }
//	    exit(json_encode($result));
//	}
