package org.gzhmc.report4gzhmc.controller.user;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.StudentGradeService;
import org.gzhmc.report4gzhmc.service.StudentService;
import org.gzhmc.report4gzhmc.service.TopicThemeService;
import org.gzhmc.report4gzhmc.service.UserService;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 学生界面管理
 * 
 * @author stShen
 * @date 2017年2月5日
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentGradeService studentGradeService;
	@Autowired
	TopicThemeService topicThemeService;

	/**
	 * 学生首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/index", "/index.html" })
	public ModelAndView index(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentService.getById(StringUtils.string2int(userid));
		int num = topicThemeService.getCountMes(StringUtils.string2int(userid));
		ModelAndView modelAndView = new ModelAndView("student/index");
		modelAndView.addObject("studentName", student.getcName());
		if (num != 0)
			modelAndView.addObject("num", num);
		return modelAndView;
	}

	/**
	 * 跳转个人信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/information", "/information.html" })
	public ModelAndView informationAction(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		StudentGrade student = studentGradeService.getById(StringUtils.string2int(userid));
		ModelAndView modelAndView = new ModelAndView("student/information");
		modelAndView.addObject("student", student);
		return modelAndView;
	}

	/**
	 * 跳转学生首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @RequestMapping(value = { "/studentIndex", "/studentIndex.html" }) public
	 *                       ModelAndView indexStudent(HttpServletRequest
	 *                       request, HttpServletResponse response) { String
	 *                       userid =
	 *                       request.getSession().getAttribute("userid").toString().trim();
	 *                       Student student =
	 *                       studentService.getById(StringUtils.string2int(userid));
	 *                       List<ExperimentalTest> experimentalTests =
	 *                       experimentalTestService.getAll(); ModelAndView
	 *                       modelAndView = new
	 *                       ModelAndView("student/studentIndex");
	 *                       modelAndView.addObject("studentName",
	 *                       student.getcName());
	 *                       modelAndView.addObject("studentNum",
	 *                       student.getcStudentNumber());
	 *                       modelAndView.addObject("experimentalTests",
	 *                       experimentalTests); if (null !=
	 *                       request.getParameter("process")) {
	 *                       modelAndView.addObject("process",
	 *                       request.getParameter("process")); } return
	 *                       modelAndView; }
	 */

	/**
	 * 跳转密码修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/passwordChange", "passwordChange.html" })
	public ModelAndView passwordChange(HttpServletRequest request) {
		return new ModelAndView("student/passwordChange");
	}

	/**
	 * 跳转身份信息晚上页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/uploadPicture", "uploadPicture.html" })
	public ModelAndView uploadPicture(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		StudentGrade student = studentGradeService.getById(StringUtils.string2int(userid));
		ModelAndView modelAndView = new ModelAndView("student/uploadPicture");
		modelAndView.addObject("student", student);
		return modelAndView;
	}

	/**
	 * 学生密码修改
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/passwordChange.action")
	public void identityPerfectionStudentAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String oldPassword = request.getParameter("oldPassword");
		String password = request.getParameter("password");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		ResultJson json = new ResultJson();
		User user = userService.getById(StringUtils.string2int(userid));
		if (!MD5.getMD5("crazy" + oldPassword).equals(user.getcPassword())) {
			json.setSuccess(false);
			json.setMsg("对不起，原密码输入错误，密码修改失败。");
		} else {
			user.setcPassword(MD5.getMD5("crazy" + password));
			int result = userService.updateSelective(user);
			if (result != RESULT_ONE) {
				json.setSuccess(false);
				json.setMsg("对不起，密码修改失败。");
			} else {
				json.setSuccess(true);
				json.setMsg("密码修改成功。");
			}
		}
		writeResultJson(response, json);
	}

	/**
	 * 照片上传
	 * 
	 * @param request
	 * @param response
	 * @param uploadFile
	 *            上传是照片
	 * @param idcard
	 *            上传的身份证号
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/uploadPicture.action" })
	public ModelAndView uploadPhoto(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		StudentGrade studentGrade = studentGradeService.getById(StringUtils.string2int(userid));
		PrintWriter out = response.getWriter();
		int maxSize = 5 * 1024 * 1024;
		String name = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."),
				uploadFile.getOriginalFilename().length());
		out.flush();
		// if (!StringUtils.isIdcard(idcard)) {
		// out.write("<script>alert('身份证号格式错误，请重新上传。')</script>");
		// } else
		if (uploadFile.getSize() > maxSize) {
			out.write("<script>alert('上传文件超过5mb，请重新上传。')</script>");

		} else if (!".jpg".equals(name) && !".jpeg".equals(name) && !".JPG".equals(name) && !".JEPG".equals(name)) {
			out.write("<script>alert('文件格式错误，请重新上传。')</script>");
		} else {
			// 获取相对地址
			String path1 = request.getServletContext().getRealPath("gzhmc/picture");
			// 获取存储位置
			String path2 = "/" + studentGrade.getCollege().getcCollegeName() + "/"
					+ studentGrade.getGrade().getcYearClass() + "/" + studentGrade.getMajor().getcMajorName() + "/"
					+ studentGrade.getGrade().getcClass() + "/";
			String uploadFilePath = path1 + path2;
			// 替换路径中的\
			uploadFilePath = uploadFilePath.replace('\\', '/');
			try {
				// 将图片命名为学号
				String filename = studentGrade.getcStudentNumber() + name;
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

				// 开始保存文件到服务器
				if (!filename.equals("")) {
					FileOutputStream fos = new FileOutputStream(uploadFilePath + filename);
					byte[] buffer = new byte[8192]; // 每次读8K字节
					int count = 0;
					// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
					while ((count = is.read(buffer)) > 0) {
						fos.write(buffer, 0, count); // 向服务端文件写入字节流
					}
					fos.close(); // 关闭FileOutputStream对象
					is.close(); // InputStream对象
					Student student = studentService.getById(StringUtils.string2int(userid));
					student.setcPicturePath(path2 + filename);
					// 加密后存储
					// idcard=MD5.convertMD5(idcard);
					// student.setcIDNumber(idcard);
					int result = studentService.updateSelective(student);
					if (result != 0) {

						out.write("<script>alert('文件上传成功。');</script>");
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		studentGrade = studentGradeService.getById(StringUtils.string2int(userid));
		return new ModelAndView("student/uploadPicture").addObject("student", studentGrade);
	}

}
