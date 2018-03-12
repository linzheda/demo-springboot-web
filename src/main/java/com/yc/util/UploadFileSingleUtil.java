package com.yc.util;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class UploadFileSingleUtil {

	
	public static Map<String, UploadFile> uploadFile(HttpServletRequest request, CommonsMultipartFile files, String picRootName ) {
		Map<String, UploadFile> map = new HashMap<String, UploadFile>();
		if (files != null &&! files.isEmpty() ) {
			//  request.getSession().getServletContext().getRealPath("/")) ->c:\tomcat\apache-tomcat-7.0-47\wabapps\douban
			File webappsfile=new File( request.getSession().getServletContext().getRealPath("/")).getParentFile();
			//图片保存的服务器位置->c:\tomcat\apache-tomcat-7.0-47\wabapps\\uploadpdfs
			File picFile=new File(  webappsfile, picRootName);
			//构建图片服务器的url地址 -> http://localhost:8080/uploadpdfs
			String picBasePath =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+picRootName ;
			
				try {
					String originalFilename = files.getOriginalFilename();
				
					// 生成新文件名,与时间相关
					String newfilename = getUniqueFileName()+ originalFilename.substring(originalFilename.lastIndexOf("."));
					String saveDir=picFile.getAbsolutePath()+getNowDateStr();
					String newFilePath=saveDir+newfilename;
					String newFileUrl= picBasePath+getNowDateStr()+newfilename;
					
					File saveDirFile=new File( saveDir);
					
					if (!saveDirFile.exists()) {
						saveDirFile.mkdirs();
					}

					File imageFile = new File(newFilePath);

					UploadFile uploadFile = new UploadFile();
					uploadFile.contentType = files.getContentType();
					uploadFile.size = files.getSize();
					uploadFile.originalFileName = originalFilename;
					uploadFile.newFileName = newfilename;
					uploadFile.newFilePath=newFilePath;
					uploadFile.newFileUrl=newFileUrl;

					map.put("uploadFile", uploadFile);

					files.transferTo(imageFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
		return map;
	}

	
	private static String getNowDateStr() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		return File.separator+year + File.separator + month + File.separator + day+File.separator;
		//return "/" +year+"/"+month+"/"+day+"/";
	}

	/**
	 *  生成唯一的文件名
	 * @return
	 */
	private static String getUniqueFileName() {
		String str = UUID.randomUUID().toString();
		return str.replace("-", "");
	}

	//内部类
	public static class UploadFile {
		String originalFileName;
		String newFileName;
		String newFilePath;
		String newFileUrl;
		long size;
		String contentType;

		public String getNewFilePath() {
			return newFilePath;
		}

		public void setNewFilePath(String newFilePath) {
			this.newFilePath = newFilePath;
		}

		public String getNewFileUrl() {
			return newFileUrl;
		}

		public void setNewFileUrl(String newFileUrl) {
			this.newFileUrl = newFileUrl;
		}

		public String getOriginalFileName() {
			return originalFileName;
		}

		public void setOriginalFileName(String originalFileName) {
			this.originalFileName = originalFileName;
		}

		public String getNewFileName() {
			return newFileName;
		}

		public void setNewFileName(String newFileName) {
			this.newFileName = newFileName;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

	}
}
