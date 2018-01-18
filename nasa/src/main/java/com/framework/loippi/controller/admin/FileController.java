package com.framework.loippi.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.qiniu.FileServiceImpl4Qiniu;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.loippi.service.FileService;
import com.framework.loippi.support.FileInfo;
import com.framework.loippi.support.FileInfo.FileType;
import com.framework.loippi.support.FileInfo.OrderType;
import com.framework.loippi.support.Message;
import com.framework.loippi.utils.SettingUtils;
import com.framework.loippi.utils.doc.JsonUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * Controller - 文件处理
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Controller("adminFileController")
@RequestMapping("/admin/file")
public class FileController extends GenericController {

	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	  /**
     * 上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void upload(FileType fileType, MultipartFile file, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!fileService.isValid(fileType, file)) {
            data.put("message", Message.warn("admin.upload.invalid"));
        } else {
            UploadManager uploadManager = new UploadManager();
            Auth auth = Auth.create(FileServiceImpl4Qiniu.QINIU_AK, FileServiceImpl4Qiniu.QINIU_SK);
            String filename = UUID.randomUUID().toString();
            try {
                CommonsMultipartFile cf = (CommonsMultipartFile) file;
                DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                uploadManager.put(fi.getStoreLocation(), filename, auth.uploadToken(FileServiceImpl4Qiniu.QINIU_TOKEN));
            } catch (QiniuException e) {
                e.printStackTrace();
            }
            String url = FileServiceImpl4Qiniu.QINIU_PREFIX.concat(filename);
            if (StringUtil.isEmpty(url)) {
                data.put("message", Message.warn("admin.upload.error"));
            } else {
                data.put("message", SUCCESS_MESSAGE);
                data.put("url", url);
            }
        }
        try {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 浏览
	 */
	@RequestMapping(value = "/browser", method = RequestMethod.GET)
	public @ResponseBody List<FileInfo> browser(String path, FileType fileType, OrderType orderType) {
		return fileService.browser(path, fileType, orderType);
	}

}