package org.gzhmc.report4gzhmc.controller.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.DateUtils;
import org.gzhmc.common.util.JacobUtil;
import org.gzhmc.common.util.JsonUtils;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.common.util.UploadFileUtil;
import org.gzhmc.report4gzhmc.mapper.ExperimentMapper;
import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.mapper.GradeExamMapper;
import org.gzhmc.report4gzhmc.mapper.ReportMapper;
import org.gzhmc.report4gzhmc.mapper.ReportRelativeMapper;
import org.gzhmc.report4gzhmc.mapper.ResitMapper;
import org.gzhmc.report4gzhmc.mapper.StudentGradeMapper;
import org.gzhmc.report4gzhmc.mapper.StudentMapper;
import org.gzhmc.report4gzhmc.mapper.TestMapper;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.gzhmc.report4gzhmc.model.Resit;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.Test;
import org.gzhmc.report4gzhmc.model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import atg.taglib.json.util.JSONArray;

/**
 * 学生界面管理
 * @author stShen
 * @date 2017年2月5日
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ReportMapper reportMapper;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	ResitMapper resitMapper;
	@Autowired
	StudentGradeMapper studentGradeMapper;
	@Autowired
	ReportRelativeMapper reportRelativeMappper;
	@Autowired
	ExperimentalTestMapper experimentalTestMapper;
	@Autowired
	TestMapper testMapper;
	@Autowired
	ExperimentMapper experimentMapper;
	@Autowired
	GradeExamMapper gradeExamMapper;
	
	
	// 表示批改为1，未批改为0
	public static int CHECKED = 1;
	public static int NOCHECK = 0;
	
	/**
	 * 报告编辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportEdit", "/reportEdit.html" })
	public ModelAndView reportEdit(HttpServletRequest request, HttpServletResponse response) {
		int reportId=StringUtils.string2int(request.getParameter("id"));
		Report report=reportMapper.getById(reportId);	
		ModelAndView modelAndView = new ModelAndView("student/reportEdit");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentMapper.getById(Integer.parseInt(userid));
		List<GradeExam> gradeExams=gradeExamMapper.getByGradeId(student.getcGradeId());
		//List<Test> tests=testMapper.getAll();
	    modelAndView.addObject("tests",gradeExams);
		modelAndView.addObject("report", report);
		return modelAndView;
	}
	/**
	 * 报告查看
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportShow", "/reportShow.html" })
	public ModelAndView reportShow(HttpServletRequest request, HttpServletResponse response) {
		int reportId=StringUtils.string2int(request.getParameter("id"));
		Report report=reportMapper.getById(reportId);
		
		ModelAndView modelAndView = new ModelAndView("student/reportShow");
		modelAndView.addObject("report", report.getcPath());
		return modelAndView;
	}
	/**
	 * 学生首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/index", "/index.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentMapper.getById(StringUtils.string2int(userid));
		
		ModelAndView modelAndView = new ModelAndView("student/index");
		modelAndView.addObject("studentName", student.getcName());
		return modelAndView;
	}
	
	
	/**
	 * 报告提交页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/uploadReport", "/uploadReport.html" })
	public ModelAndView uploadReport(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView("student/uploadReport");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentMapper.getById(Integer.parseInt(userid));
		List<GradeExam> gradeExams=gradeExamMapper.getByGradeId(student.getcGradeId());
		//List<Test> tests=testMapper.getAll();
	    modelAndView.addObject("tests",gradeExams);
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
	public ModelAndView informationAction(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = request.getSession().getAttribute("userid").toString().trim();
		StudentGrade student = studentGradeMapper.getById(StringUtils.string2int(userid));
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
	 */
	@RequestMapping(value = { "/studentIndex", "/studentIndex.html" })
	public ModelAndView indexStudent(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentMapper.getById(StringUtils.string2int(userid));
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("student/studentIndex");
		modelAndView.addObject("studentName", student.getcName());
		modelAndView.addObject("studentNum", student.getcStudentNumber());
		modelAndView.addObject("experimentalTests", experimentalTests);
		if (null != request.getParameter("process")) {
			modelAndView.addObject("process", request.getParameter("process"));
		}
		return modelAndView;
	}
	/**
	 * 上传实验报告action
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/uploadExperiment", "/uploadExperiment.action" })
	public ModelAndView uploadExperiment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String experiment = request.getParameter("content");
		String process = request.getParameter("process");
		int eId = Integer.parseInt(request.getParameter("test"));
		int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString().trim());
		Student student = studentMapper.getById(userid);
		PrintWriter out = response.getWriter();
		Report reportMes = new Report();
		reportMes.setcExperimentTextId(eId);
		reportMes.setcStudentId(userid);		
		ModelAndView modelAndView = new ModelAndView("student/uploadReport");
		int count1 = reportMapper.getCountByexperimrntAndstudent(reportMes);
		List<Report> reports = reportMapper.getByexperimrntAndstudentId(reportMes);
		modelAndView.addObject("process", process);
		int result;
		out.flush();
		if (count1 != 0 && !isAllowUpload(reports)) {			
			out.write("<script>alert('此实验已经通过考核，请选择正确的实验提交。')</script>");
		}
		// 判断文件大小是否合格
		//else if (experiment.length() > 10000) {
		//	out.write("<script>alert('上传文件超过最大限制，请重新上传。')</script>");
		//}
		
		// 判断实验过程是否是在字数限制范围内
		else if (process.length() > 50||process.length()<=10) {
			out.write("<script>alert('报告过程字数不合格，请修改后上传')</script>");
		} else if (student.getcPicturePath().isEmpty()) {

			out.write("<script>alert('在上传文档之前请先上传照片，以便生成证书。')</script>");
		} else {
			// 判断表格中是否存在数据
			if (count1 != 0) {
				// 判断是补考还是第一次考试
				Report report2 = getReport(reports);
				// 表中存在插入的数据则更新数据
				if (report2 != null) {
					report2.setcCreateTime(new Date());
					report2.setcProcess(process);
					// 存储上传的文档数据
					report2.setcPath(experiment);
					result = reportMapper.update(report2);
				} else {
					// 若为第一次补考，则新建数据，在补考表中更新数据
					Report report = new Report();
					report.setcCreateTime(new Date());
					report.setcPath(experiment);
					report.setcProcess(process);
					report.setcExperimentTextId(eId);
					report.setcStatu(NOCHECK);
					report.setcStudentId(userid);
					report.setcReportNum(DateUtils.getreportnum());
					// System.out.println(report.getcReportNum());
					result = reportMapper.add(report);
					Resit resit = new Resit();
					resit.setcExperiment(eId);
					resit.setcStudentId(userid);
					resit.setcReportId(report.getcId());
					result = resitMapper.updateByStudentIdAndExperimentId(resit);
				}
			} else {
				// 第一次考试
				Report report = new Report();
				report.setcCreateTime(new Date());
				report.setcPath(experiment);
				report.setcProcess(process);
				report.setcExperimentTextId(eId);
				report.setcStatu(NOCHECK);
				report.setcStudentId(userid);
				report.setcReportNum(DateUtils.getreportnum());
				// System.out.println(report.getcReportNum());
				result = reportMapper.add(report);
			}
			if (result != 0) {
				out.write("<script>alert('上传成功。')</script>");
			} else {
				out.write("<script>alert('服务器异常，请稍后再试。')</script>");
			}
		}
		List<GradeExam> gradeExams=gradeExamMapper.getByGradeId(student.getcGradeId());
		
	    modelAndView.addObject("tests",gradeExams);
		return modelAndView;

	}

	// 返回补考的数据
	private Report getReport(List<Report> reports) {
		for (Report r : reports) {
			if (r.getcStatu() == NOCHECK) {
				return r;
			}
		}
		return null;
	}

	// 判断学生是否已经考核通过
	public boolean isAllowUpload(List<Report> reports) {
		for (Report r : reports) {			
			if (null!=r.getcPdfPath()) {				
				return false;
			}
		}
		return true;
	}
	
	
	

	/**
	 * 上传文档和实验过程
	 * 
	 * @param request
	 * @param response
	 * @param uploadFile
	 * @param process
	 * @param experiment
	 * @return
	 * @throws IOException
	 
	@RequestMapping(value = { "/uploadreport.action" })
	public ModelAndView uploadReportAction(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile,
			@RequestParam(value = "process", required = false) String process,
			@RequestParam(value = "experiment", required = false) String experiment) throws IOException {

		response.setContentType("text/html;charset=utf-8");
		int result = 0;
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		// 根据学生选择的实验id找到对应实验
		ExperimentalTest experimentalTest = experimentalTestMapper.getById(StringUtils.string2int(experiment));
		int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString().trim());
		StudentGrade studentGrade = studentGradeMapper.getById(userid);
		PrintWriter out = response.getWriter();

		// 获取所有userid和experiment的report
		Report reportMes = new Report();
		reportMes.setcExperimentTextId(experimentalTest.getcId());
		reportMes.setcStudentId(studentGrade.getcUserId());
		int count1 = reportMapper.getCountByexperimrntAndstudent(reportMes);

		List<Report> reports = reportMapper.getByexperimrntAndstudentId(reportMes);

		// 设置最大上传容量
		int maxSize = 20 * 1024 * 1024;
		String name = uploadFile.getOriginalFilename()
				.substring(uploadFile.getOriginalFilename().lastIndexOf("."), uploadFile.getOriginalFilename().length())
				.trim();
		out.flush();
		ModelAndView modelAndView = new ModelAndView("student/studentIndex");
		// System.out.println(studentGrade.getcPicturePath());
		if (count1 != 0 && !isAllowUpload(reports)) {
			modelAndView.addObject("process", process);
			out.write("<script>alert('此实验已经通过考核，请选择正确的实验提交。')</script>");
		}
		// 判断文件大小是否合格
		else if (uploadFile.getSize() > maxSize) {
			modelAndView.addObject("process", process);
			out.write("<script>alert('上传文件超过20mb，请重新上传。')</script>");
		}
		// 判断文件是否是文档格式
		else if (!".doc".equals(name) && !".docx".equals(name)) {
			modelAndView.addObject("process", process);
			out.write("<script>alert('文件格式错误，请重新上传。文件格式为doc或docx')</script>");
		}
		// 判断实验过程是否是在字数限制范围内
		else if (process.length() > 50) {
			modelAndView.addObject("process", process);
			out.write("<script>alert('字数超过一百，请缩减后上传')</script>");
		} else if (studentGrade.getcPicturePath().isEmpty()) {
			modelAndView.addObject("process", process);
			out.write("<script>alert('在上传文档之前请先上传照片，以便生成证书。')</script>");
		} else {
			// 将实验命名为实验id+学号
			String filename = experimentalTest.getcId() + "_" + studentGrade.getcStudentNumber() + "_" + count1;
			//上传文档
			String path2 = UploadFileUtil.uploadReport(request, studentGrade, experimentalTest, uploadFile, count1);
			// 重新定义路径存入数据库
			String htmlPath = path2 + "html/" + filename + ".html";

			// 判断表格中是否存在数据
			if (count1 != 0) {
				// 判断是补考还是第一次考试
				Report report2 = getReport(reports);
				//表中存在插入的数据则更新数据
				if (report2 != null) {
					report2.setcCreateTime(new Date());
					report2.setcProcess(process);
					report2.setcPath(htmlPath);
					result = reportMapper.update(report2);
				} else {
					//若为第一次补考，则新建数据，在补考表中更新数据
					Report report = new Report();
					report.setcCreateTime(new Date());
					report.setcPath(htmlPath);
					report.setcProcess(process);
					report.setcExperimentTextId(StringUtils.string2int(experiment));
					report.setcStatus(NOCHECK);
					report.setcStudentId(userid);
					report.setcReportNum(DateUtils.getreportnum());
					// System.out.println(report.getcReportNum());
					result = reportMapper.add(report);
					Resit resit = new Resit();
					resit.setcExperiment(experimentalTest.getcId());
					resit.setcStudentId(studentGrade.getcUserId());
					resit.setcReportId(report.getcId());
					result = resitMapper.updateByStudentIdAndExperimentId(resit);
				}
			} else {
				//第一次考试
				Report report = new Report();
				report.setcCreateTime(new Date());
				report.setcPath(htmlPath);
				report.setcProcess(process);
				report.setcExperimentTextId(StringUtils.string2int(experiment));
				report.setcStatus(NOCHECK);
				report.setcStudentId(userid);
				report.setcReportNum(DateUtils.getreportnum());
				// System.out.println(report.getcReportNum());
				result = reportMapper.add(report);
			}
			if (result != 0) {
				out.write("<script>alert('报告上传成功。')</script>");
			} else {
				out.write("<script>alert('服务器异常，请稍后再试。')</script>");
			}
		}

		modelAndView.addObject("experimentalTests", experimentalTests);
		modelAndView.addObject("studentName", studentGrade.getcName());
		return modelAndView;
	}*/
	/**
	 * 管理所有报告
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportManage", "reportManage.html" })
	public ModelAndView reportManage(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentMapper.getById(StringUtils.string2int(userid));
		List<ReportRelative> reportRelatives = reportRelativeMappper
				.getReportLinkExperimentByStuId(student.getcUserId());
		// System.out.println(reportRelatives.size());
		ModelAndView modelAndView = new ModelAndView("student/reportManage");		
		modelAndView.addObject("reportRelatives", reportRelatives);
		return modelAndView;
	}

	//旧版查看文档代码
//	@RequestMapping("/reportShow")
//	public ModelAndView reportShow(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String rid = request.getParameter("rid");
//		Report report = reportMapper.getById(StringUtils.string2int(rid));
//		ModelAndView modelAndView = new ModelAndView("student/reportShow");
//		modelAndView.addObject("wordpath", report.getcPath());
//		return modelAndView;
//
//	}

	/**
	 * 跳转密码修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/passwordChange", "passwordChange.html" })
	public ModelAndView passwordChange(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		

		return new ModelAndView("student/passwordChange");
	}

	@RequestMapping("scoreInquery")
	public ModelAndView scoreInquery(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getSession().getAttribute("userid").toString().trim();
		List<ReportRelative> relatives = reportRelativeMappper.getByStudentId(StringUtils.string2int(userId));
		return new ModelAndView("student/scoreInquery").addObject("reportRelativesbyStuid", relatives);
	}

	/**
	 * 跳转身份信息晚上页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/uploadPicture", "uploadPicture.html" })
	public ModelAndView uploadPicture(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		StudentGrade student = studentGradeMapper.getById(StringUtils.string2int(userid));
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
		String password = request.getParameter("password");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		User user = userMapper.getById(StringUtils.string2int(userid));
		user.setcPassword(MD5.getMD5("crazy" + password));
		int result = userMapper.updateSelective(user);
		ResultJson json = new ResultJson();
		if (result != RESULT_ONE) {
			json.setSuccess(false);
			json.setMsg("对不起，密码修改失败。");
		} else {
			json.setSuccess(true);
			json.setMsg("密码修改成功。");
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
		StudentGrade studentGrade = studentGradeMapper.getById(StringUtils.string2int(userid));
		PrintWriter out = response.getWriter();
		int maxSize = 5 * 1024 * 1024;
		String name = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."),
				uploadFile.getOriginalFilename().length());
		out.flush();
		//if (!StringUtils.isIdcard(idcard)) {
		//	out.write("<script>alert('身份证号格式错误，请重新上传。')</script>");
		//} else 
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
					Student student = studentMapper.getById(StringUtils.string2int(userid));
					student.setcPicturePath(path2 + filename);
					//加密后存储
					//idcard=MD5.convertMD5(idcard);
					//student.setcIDNumber(idcard);
					int result = studentMapper.updateSelective(student);
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
		studentGrade = studentGradeMapper.getById(StringUtils.string2int(userid));
		return new ModelAndView("student/uploadPicture").addObject("student", studentGrade);
	}

}
