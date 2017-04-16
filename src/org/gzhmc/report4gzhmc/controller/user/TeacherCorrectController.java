package org.gzhmc.report4gzhmc.controller.user;

import java.io.File;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.DownLoadUtil;
import org.gzhmc.common.util.ExportExcelUtil;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.SetpdfCertificate;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.mapper.ReportMapper;
import org.gzhmc.report4gzhmc.mapper.ReportRelativeMapper;
import org.gzhmc.report4gzhmc.mapper.ResitMapper;
import org.gzhmc.report4gzhmc.mapper.ScoreSheetMapper;
import org.gzhmc.report4gzhmc.mapper.StudentGradeMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherExperimentalMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.ExcelMessage;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.gzhmc.report4gzhmc.model.Resit;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.ScoreSheet;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherExperimental;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 教师界面管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherCorrectController extends BaseController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	ReportMapper reportMapper;
	@Autowired
	ResitMapper resitMapper;
	@Autowired
	ReportRelativeMapper reportRelativeMapper;
	@Autowired
	StudentGradeMapper studentGradeMapper;
	@Autowired
	GradeMajorCollegeMapper gradeMajorCollegeMapper;
	@Autowired
	TeacherExperimentalMapper teacherExperimentalMapper;
	@Autowired
	ExperimentalTestMapper experimentalTestMapper;
	@Autowired
	ScoreSheetMapper scoreSheetMapper;
	// ISCHECK=1表示report已经批改过
	private int ISCHECK = 1;

	/**
	 * 实验报告显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/teacherIndex")
	public ModelAndView indexTeacher(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();

		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();

		ModelAndView modelAndView = new ModelAndView("teacher/teacherIndex");
		modelAndView.addObject("teachername", teacher.getcName());
		int count = teacherExperimentalMapper.getCountByTeacherId(teacher.getcUserId());
		if (count > 0) {
			List<TeacherExperimental> teacherExperimentals = teacherExperimentalMapper
					.getByTeacherId(teacher.getcUserId());
			int[] haves = new int[teacherExperimentals.size()];
			for (int i = 0; i < teacherExperimentals.size(); i++) {
				haves[i] = teacherExperimentals.get(i).getcExperimentalTestId();
			}
			List<ReportRelative> reportRelatives = reportRelativeMapper.getExitScoreByExperimentId(haves);

			modelAndView.addObject("reportRelatives", reportRelatives);
		}
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("experimentalTests", experimentalTests);
		return modelAndView;
	}

	/**
	 * 跳转修改密码页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tpasswordChange")
	public ModelAndView iteacherPasswordChange(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		return new ModelAndView("teacher/tpasswordChange").addObject("teachername", teacher.getcName());
	}

	/**
	 * 教师密码修改
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/tpasswordChange.action")
	public void identityPerfectionStudentAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String password = request.getParameter("password");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		User user = userMapper.getById(StringUtils.string2int(userid));
		user.setcPassword(MD5.getMD5("crazy"+password));
		int result = userMapper.updateSelective(user);
		ResultJson json = new ResultJson();
		if (result != RESULT_ONE) {
			json.setSuccess(false);
			json.setMsg("对不起，密码修改失败。");
		} else {
			json.setSuccess(true);
			json.setMsg("修改成功，请重新登陆。");
		}
		writeResultJson(response, json);
	}

	/*
	 * 跳转教师批改页面
	 */
	@RequestMapping(value = { "reportcorrect", "reportcorrect.html" })
	public ModelAndView reportCorrect(HttpServletRequest request, HttpServletResponse response) {
		String rid = request.getParameter("rid");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		Report report = reportMapper.getById(StringUtils.string2int(rid));
		if (report.getcStatus() == 1) {
			// HttpSession session = request.getSession();
			// out.write("<script>alert('实验报告已经批改，请重新选择一份。');</script>");
			ModelAndView modelAndView = new ModelAndView("teacher/cmnerror");
			modelAndView.addObject("message", "实验报告已经批改，请重新选择一份。");
			modelAndView.addObject("showpath", "teacher/teacherIndex");
			return modelAndView;
		} else {
			String path = report.getcPath();
			ModelAndView modelAndView = new ModelAndView("teacher/reportcorrect");
			modelAndView.addObject("teachername", teacher.getcName());
			modelAndView.addObject("path", path);
			modelAndView.addObject("rid", rid);
			return modelAndView;
		}
	}

	/**
	 * 教师批改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "reportcorrect.action" })
	@Transactional
	public ModelAndView reportCorrectAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rid = request.getParameter("rid");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		Report report = reportMapper.getById(StringUtils.string2int(rid));
		ExperimentalTest experimentalTest = experimentalTestMapper.getById(report.getcExperimentTextId());
		response.setContentType("text/html; charset=utf8");
		PrintWriter out = response.getWriter();
		String theory = request.getParameter("theory");
		String reagen = request.getParameter("reagen");
		String instrument = request.getParameter("instrument");
		String experiment = request.getParameter("experiment");
		String labresult = request.getParameter("labresult");
		String conclusion = request.getParameter("conclusion");
		String score = request.getParameter("score");
		String comment = request.getParameter("comment");
		// 存入成绩数据
		ScoreSheet scoreSheet = new ScoreSheet();
		scoreSheet.setcComment(comment);
		scoreSheet.setcConclution(Double.parseDouble(conclusion));
		scoreSheet.setcCreateTime(new Date());
		scoreSheet.setcExperiment(Double.parseDouble(experiment));
		scoreSheet.setcInserument(Double.parseDouble(instrument));
		scoreSheet.setcLabresult(Double.parseDouble(labresult));
		scoreSheet.setcReagen(Double.parseDouble(reagen));
		scoreSheet.setcTherory(Double.parseDouble(theory));
		scoreSheet.setcSum(Double.parseDouble(score));
		out.flush();
		//System.out.println("开始生成证书");
		// 判断是否及格，及格则生成证书
		if (report.getcStatus() == 1) {
			out.write("<script>alert('已由另外一名老师批改完成，请返回另选一份。');</script>");
		} else if(StringUtils.string2double(score) - 60 < 0) {
			
			int result = scoreSheetMapper.add(scoreSheet);
			if (result != 0) {
				report.setcTeacherId(teacher.getcUserId());
				report.setcStatus(ISCHECK);
				report.setcScoreId(scoreSheet.getcId());
				result = reportMapper.update(report);
				if (result != 0) {
					//System.out.println("不及格批改成功");
					Resit resit=new Resit();
					resit.setcExperiment(report.getcExperimentTextId());
					resit.setcStudentId(report.getcStudentId());
					result=resitMapper.insert(resit);
					if(result!=0){
					out.write("<script>alert('批改成功！成绩为不及格，已加入补考名单。');</script>");}
					else{
						out.write("<script>alert('批改成功！加入补考名单失败，请联系管理员。');</script>");}
				} else {
					out.write("<script>alert('服务器异常，请稍后再试！');</script>");
				}
			} else {
				out.write("服务器异常，请稍后再试！");
			}
		} else {
			StudentGrade studentGrade = studentGradeMapper.getById(report.getcStudentId());
			// 获取相对地址
			String path1 = request.getServletContext().getRealPath("gzhmc/report");
			// 获取存储位置
			String path2 = "/" + studentGrade.getCollege().getcCollegeName() + "/"
					+ studentGrade.getGrade().getcYearClass() + "/" + studentGrade.getMajor().getcMajorName() + "/"
					+ studentGrade.getGrade().getcClass() + "/" + experimentalTest.getcExperimentName() + "/";
			// String qrPath = path1 + path2 + "qrcode/";
			String pdfPath = path1 + path2 + "pdf\\";
			// 替换路径中的\
			// qrPath = qrPath.replace('\\', '/');
			pdfPath = pdfPath.replace('/', '\\');
			// 将实验命名为实验报告id+学号
			String filename = report.getcId() + "_" + studentGrade.getcStudentNumber();			
			// String
			// path=request.getServletContext().getRealPath("jsp/teacher");
						
			int result = scoreSheetMapper.add(scoreSheet);
			if (result != 0) {
				// 存入实验部分字段
				report.setcTeacherId(teacher.getcUserId());
				report.setcStatus(ISCHECK);
				report.setcScoreId(scoreSheet.getcId());
				// report.setcQRcode(path2 + "qrcode/" + filename + ".jpg");
				report.setcPdfPath(pdfPath + filename + ".pdf");
				result = reportMapper.update(report);
				if (result != 0) {
					// 若文件不存在则创建
					// File tempFile1 = new File(qrPath);
					// if (!tempFile1.exists()) {
					// tempFile1.mkdirs();
					// }
					File tempFile2 = new File(pdfPath);
					if (!tempFile2.exists()) {
						tempFile2.mkdirs();
					}
					// // 生成二维码
					// QRCodeUtil.createQRCode("/Report4gzhmc/teacher/show?rid="
					// + report.getcId(), 200,
					// qrPath + filename + ".jpg");
					// 生成pdf
					ReportRelative reportRelative = reportRelativeMapper.getById(report.getcId());
					SetpdfCertificate.setCertificate(reportRelative, request);

					out.write("<script>alert('批改成功！');</script>");
				} else {
					out.write("<script>alert('服务器异常，请稍后再试！');</script>");
				}
			} else {
				out.write("服务器异常，请稍后再试！");
			}

		}
		List<TeacherExperimental> teacherExperimentals = teacherExperimentalMapper.getByTeacherId(teacher.getcUserId());
		int[] haves = new int[teacherExperimentals.size()];
		for (int i = 0; i < teacherExperimentals.size(); i++) {
			haves[i] = teacherExperimentals.get(i).getcExperimentalTestId();
		}
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
		List<ReportRelative> reportRelatives = reportRelativeMapper.getExitScoreByExperimentId(haves);
		ModelAndView modelAndView = new ModelAndView("teacher/teacherIndex");
		modelAndView.addObject("teachername", teacher.getcName());
		modelAndView.addObject("experimentalTests", experimentalTests);
		modelAndView.addObject("reportRelatives", reportRelatives);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		return modelAndView;
	}

	/**
	 * 跳转实验报告查看页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/teacherreportshow")
	public ModelAndView reportShow(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rid = request.getParameter("rid");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		Report report = reportMapper.getById(StringUtils.string2int(rid));
		ModelAndView modelAndView = new ModelAndView("teacher/teacherreportshow");
		modelAndView.addObject("wordpath", report.getcPath());
		modelAndView.addObject("teachername", teacher.getcName());
		return modelAndView;

	}

	/**
	 * 跳转下载pdf页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downloadpdf")
	public ModelAndView downloadPdf(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
		List<ReportRelative> reportRelativesbystatus = reportRelativeMapper.getAllBystatu();
		//List<ReportRelative> reportRelatives = reportRelativeMapper.getExperimentIdandGradeId();
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("teacher/downloadpdf");
		modelAndView.addObject("experimentalTests", experimentalTests);
		//modelAndView.addObject("reportRelatives", reportRelatives);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("teachername", teacher.getcName());
		modelAndView.addObject("reportRelativesbystatus", reportRelativesbystatus);
		return modelAndView;
	}
	
	/**
	 * 按班按实验查找pdf成绩
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryPdf")
	public ModelAndView queryPdfAction(HttpServletRequest request, HttpServletResponse response) {
		// 获取课程表格数据
		int gradeid = StringUtils.string2int(request.getParameter("gradeidq"));
		// 获取实验表格数据
		int experimentid = StringUtils.string2int(request.getParameter("experimentidq"));

		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		
		List<GradeMajorCollege> gradeMajorColleges =gradeMajorCollegeMapper.getAll();
		List<ReportRelative> relatives = reportRelativeMapper.getScoreByExperimentIdandGradeId(experimentid, gradeid);
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("teacher/downloadpdf");
		modelAndView.addObject("experimentalTests", experimentalTests);

		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("teachername", teacher.getcName());
		modelAndView.addObject("reportRelativesbystatus", relatives);
		return modelAndView;
	}

	/**
	 *  pdf下载表格导出
	 * @param request
	 * @param response
	 * @param action 不同按钮的值
	 * @throws Exception
	 */
	@RequestMapping("/downloadPdfExce.action")
	public void downloadPdfAction(HttpServletRequest request, HttpServletResponse response, @RequestParam String action)
			throws Exception {
		// Teacher teacher =
		// teacherMapper.getById(StringUtils.string2int(userid));
		// 获取课程表格数据
		int gradeid = StringUtils.string2int(request.getParameter("gradeid"));
		// 获取实验表格数据
		int experimentid = StringUtils.string2int(request.getParameter("experimentid"));

		if (action.equals("下载证书")) {
			response.setContentType("text/html; charset=utf8");
			GradeMajorCollege gradeMajorCollege = gradeMajorCollegeMapper.getById(gradeid);
			ExperimentalTest experimentalTest = experimentalTestMapper.getById(experimentid);
			PrintWriter out = response.getWriter();
			List<File> files = new ArrayList<File>();
			// 获取相对地址
			String path1 = request.getServletContext().getRealPath("gzhmc/report");
			// 获取存储位置
			String path2 = "/" + gradeMajorCollege.getCollege().getcCollegeName() + "/"
					+ gradeMajorCollege.getcYearClass() + "/" + gradeMajorCollege.getMajor().getcMajorName() + "/"
					+ gradeMajorCollege.getcClass() + "/" + experimentalTest.getcExperimentName() + "/";
			String pdfPath = path1 + path2 + "pdf";

			File file = new File(pdfPath);
			// out.flush();加上这句会出错，因为提前返回数据后面就无法再返回了
			if (!file.exists()) {
				out.write(
						"<script>alert('还未有数据，请确认后选择。');window.location.href='../teacher/downloadpdf';</script>");
			} else {
				files.add(file);
				DownLoadUtil.downLoadFiles(files, request, response);
			}
			out.close();
		} else if (action.equals("导出表格")) {
			List<ReportRelative> relatives = reportRelativeMapper.getScoreByExperimentIdandGradeId(experimentid,
					gradeid);
			List<ExcelMessage> excelMessages = new ArrayList<ExcelMessage>();
			ExcelMessage excelMessage;
			GradeMajorCollege gradeMajorCollege;
			// System.out.println(relatives.size());
			if (relatives.size() > 0) {
				// 得到学院年级班级
				gradeMajorCollege = gradeMajorCollegeMapper.getById(gradeid);
				for (int i = 0; i < relatives.size(); i++) {
					excelMessage = new ExcelMessage();
					excelMessage.setcReportNum(relatives.get(i).getcReportNum());
					excelMessage.setcExperimentName(relatives.get(i).getExperimentalTest().getcExperimentName());
					excelMessage.setcName(relatives.get(i).getStudent().getcName());
					excelMessage.setcStudentNumber(relatives.get(i).getStudent().getcStudentNumber());
					excelMessage.setcSum(relatives.get(i).getScoreSheet().getcSum());
					// 获得学院年级班级
					excelMessage.setcCollegeName(gradeMajorCollege.getCollege().getcCollegeName());
					excelMessage.setcMajorName(gradeMajorCollege.getMajor().getcMajorName());
					excelMessage.setcYearClass(gradeMajorCollege.getcYearClass());
					excelMessage.setcClass(gradeMajorCollege.getcClass());
					excelMessages.add(i, excelMessage);
				}
				String[] excelheader = { "学院#cCollegeName", "年级#cYearClass", "专业#cMajorName", "班级#cClass", "姓名#cName",
						"实验#cExperimentName", "证书编号#cReportNum", "成绩#cSum" };
				ExportExcelUtil.export(response, "成绩单", excelheader, excelMessages);
				// System.out.println(relatives.size());
			} else {
				PrintWriter out = response.getWriter();
				out.write(
						"<script>alert('还未有数据，请重新选择。');window.location.href='../teacher/downloadpdf';</script>");

			}
		}

	}

	/**
	 * 实验报告表格查看
	 */
	@RequestMapping("/queryreport")
	public ModelAndView queryReportAction(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf8");
		int i;
		int gradeid = Integer.parseInt(request.getParameter("gradeidq"));
		int experimentid = Integer.parseInt(request.getParameter("experimentidq"));
		// System.out.println("gradeid" + gradeid + "experimentid" +
		// experimentid);
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();

		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();

		ModelAndView modelAndView = new ModelAndView("teacher/teacherIndex");
		modelAndView.addObject("teachername", teacher.getcName());
		int count = teacherExperimentalMapper.getCountByTeacherId(teacher.getcUserId());
		List<ReportRelative> reportRelatives;
		if (count > 0) {
			List<TeacherExperimental> teacherExperimentals = teacherExperimentalMapper
					.getByTeacherId(teacher.getcUserId());
			int TSize = teacherExperimentals.size();
			for (i = 0; i < TSize; i++) {
				if (experimentid == teacherExperimentals.get(i).getcExperimentalTestId()) {
					break;
				}
			}
			if (i == TSize) {
				reportRelatives = null;
			} else {
				reportRelatives = reportRelativeMapper.getExitScoreByExperimentIdandGradeId(experimentid, gradeid);
			}
			modelAndView.addObject("reportRelatives", reportRelatives);
		}
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("experimentalTests", experimentalTests);
		return modelAndView;
	}

	/**
	 * 实验报告下载
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadreport.action")
	public void downloadReportAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf8");
		String gradeid = request.getParameter("gradeid");
		String experimentid = request.getParameter("experimentid");
		GradeMajorCollege gradeMajorCollege = gradeMajorCollegeMapper.getById(StringUtils.string2int(gradeid));
		ExperimentalTest experimentalTest = experimentalTestMapper.getById(StringUtils.string2int(experimentid));
		List<File> files = new ArrayList<File>();

		// 获取相对地址
		String path1 = request.getServletContext().getRealPath("gzhmc/report");
		// 获取存储位置
		String path2 = "/" + gradeMajorCollege.getCollege().getcCollegeName() + "/" + gradeMajorCollege.getcYearClass()
				+ "/" + gradeMajorCollege.getMajor().getcMajorName() + "/" + gradeMajorCollege.getcClass() + "/"
				+ experimentalTest.getcExperimentName() + "/";
		String pdfPath = path1 + path2 + "word";
		PrintWriter out = response.getWriter();
		File file = new File(pdfPath);
		if (!file.exists()) {
			out.write(
					"<script>alert('对不起，实验报告不存在');window.location.href='../teacher/teacherIndex';</script>");
		} else {
			files.add(file);
			DownLoadUtil.downLoadFiles(files, request, response);
		}
		out.close();

	}

	public String getScore(double sum) {
		String sumchar;
		if (sum >= 0 && sum < 1) {
			sumchar = "不合格";
		} else if (sum >= 1 && sum < 2) {
			sumchar = "合格";
		} else if (sum >= 2 && sum < 3) {
			sumchar = "良好";
		} else {
			sumchar = "优秀";
		}
		return sumchar;
	}

	public String getGrade(double sum) {
		String sumchar;
		if (sum >= 0 && sum < 1) {
			sumchar = "D";
		} else if (sum >= 1 && sum < 2) {
			sumchar = "C";
		} else if (sum >= 2 && sum < 3) {
			sumchar = "B";
		} else {
			sumchar = "A";
		}
		return sumchar;
	}

	/**
	 * 二维码扫描后跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
		String rid = request.getParameter("rid");
		ReportRelative reportRelative = reportRelativeMapper.getById(StringUtils.string2int(rid));
		String theory = getScore(reportRelative.getScoreSheet().getcTherory());
		String labresult = getScore(reportRelative.getScoreSheet().getcLabresult());
		String conclusion = getGrade(reportRelative.getScoreSheet().getcConclution());
		String conclusion2 = getScore(reportRelative.getScoreSheet().getcConclution());
		String operation = getScore(
				(reportRelative.getScoreSheet().getcReagen() + reportRelative.getScoreSheet().getcInserument()
						+ reportRelative.getScoreSheet().getcExperiment()) / 3);
		String reagen = getScore(reportRelative.getScoreSheet().getcReagen());
		String instrument = getScore(reportRelative.getScoreSheet().getcInserument());
		String experiment = getScore(reportRelative.getScoreSheet().getcExperiment());
		ModelAndView modelAndView = new ModelAndView("teacher/show");
		modelAndView.addObject("theory", theory);
		modelAndView.addObject("labresult", labresult);
		modelAndView.addObject("conclusion", conclusion);
		modelAndView.addObject("conclusion2", conclusion2);
		modelAndView.addObject("operation", operation);
		modelAndView.addObject("reportRelative", reportRelative);
		modelAndView.addObject("reagen", reagen);
		modelAndView.addObject("instrument", instrument);
		modelAndView.addObject("experiment", experiment);
		return modelAndView;
	}

	/**
	 * 根据前台传来的checkBoxId进行表格导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/excelout.action")
	public void excelOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		String[] idString = ids.split(",");
		int[] haves = new int[idString.length];
		for (int i = 0; i < idString.length; i++)
			haves[i] = StringUtils.string2int(idString[i]);
		List<ReportRelative> relatives = reportRelativeMapper.getByManyId(haves);
		List<ExcelMessage> excelMessages = new ArrayList<ExcelMessage>();
		ExcelMessage excelMessage;
		GradeMajorCollege gradeMajorCollege;
		//System.out.println(relatives.size());
		for (int i = 0; i < relatives.size(); i++) {
			excelMessage = new ExcelMessage();
			excelMessage.setcReportNum(relatives.get(i).getcReportNum());
			excelMessage.setcExperimentName(relatives.get(i).getExperimentalTest().getcExperimentName());
			excelMessage.setcName(relatives.get(i).getStudent().getcName());
			excelMessage.setcStudentNumber(relatives.get(i).getStudent().getcStudentNumber());
			excelMessage.setcSum(relatives.get(i).getScoreSheet().getcSum());
			gradeMajorCollege = gradeMajorCollegeMapper.getById(relatives.get(i).getStudent().getcGradeId());
			excelMessage.setcCollegeName(gradeMajorCollege.getCollege().getcCollegeName());
			excelMessage.setcMajorName(gradeMajorCollege.getMajor().getcMajorName());
			excelMessage.setcYearClass(gradeMajorCollege.getcYearClass());
			excelMessage.setcClass(gradeMajorCollege.getcClass());
			excelMessages.add(i, excelMessage);
		}
		String[] excelheader = { "学院#cCollegeName", "年级#cYearClass", "专业#cMajorName", "班级#cClass", "姓名#cName",
				"实验#cExperimentName", "证书编号#cReportNum", "成绩#cSum" };
		ExportExcelUtil.export(response, "成绩单", excelheader, excelMessages);
		//System.out.println(relatives.size());
	}
}
