package org.gzhmc.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传实验报告
 * @author stShen
 * @date 2017年2月5日
 */
public class UploadFileUtil {

	/**
	 * 上传实验报告
	 * 
	 * @param request
	 * @param studentGrade 获取的学生班级信息
	 * @param experimentalTest 实验信息
	 * @param uploadFile 要上传的文档
	 * @param count1 report表中此学生次实验上传报告的记录
	 * @return
	 */
	public static String uploadReport(HttpServletRequest request, StudentGrade studentGrade, ExperimentalTest experimentalTest,
			MultipartFile uploadFile, int count1) {

		String name = uploadFile.getOriginalFilename()
				.substring(uploadFile.getOriginalFilename().lastIndexOf("."), uploadFile.getOriginalFilename().length())
				.trim();
		// 获取相对地址
		String path1 = request.getServletContext().getRealPath("gzhmc/report");
		// 获取存储位置
		String path2 = "/" + studentGrade.getCollege().getcCollegeName() + "/" + studentGrade.getGrade().getcYearClass()
				+ "/" + studentGrade.getMajor().getcMajorName() + "/" + studentGrade.getGrade().getcClass() + "/"
				+ experimentalTest.getcExperimentName() + "/";
		String uploadFilePath = path1 + path2 + "word/";
		String htmlPath = path1 + path2 + "html\\";
		// 替换路径中的\
		uploadFilePath = uploadFilePath.replace('\\', '/');
		htmlPath = htmlPath.replace('/', '\\');
		// 将实验命名为实验id+学号
		String filename = experimentalTest.getcId() + "_" + studentGrade.getcStudentNumber() + "_" + count1;

		try {
			InputStream is = uploadFile.getInputStream();
			if (null != studentGrade.getcPicturePath()) {
				// 如果服务器已经存在和上传文件同名的文件，则输出提示信息
				File tempFile = new File(uploadFilePath + studentGrade.getcPicturePath());
				if (tempFile.exists()) {
					tempFile.delete();
				}
			}
			File tempFile1 = new File(uploadFilePath);
			if (!tempFile1.exists()) {
				tempFile1.mkdirs();
			}
			File tempFile2 = new File(htmlPath);
			if (!tempFile2.exists()) {
				tempFile2.mkdirs();
			}
			// 开始保存文件到服务器
			if (!filename.equals("")) {
				FileOutputStream fos = new FileOutputStream(uploadFilePath + filename + name);
				byte[] buffer = new byte[8192]; // 每次读8K字节
				int count = 0;
				// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count); // 向服务端文件写入字节流
				}
				fos.close(); // 关闭FileOutputStream对象
				is.close(); // InputStream对象
				// 转换成html格式存储
				uploadFilePath = uploadFilePath.replace('/', '\\');
				htmlPath = htmlPath + filename + ".html";
				JacobUtil.wordToHtml(uploadFilePath + filename + name, htmlPath);
				// 将生成的html转码为utf-8
				JacobUtil.html2utf(htmlPath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path2;
	}

}
