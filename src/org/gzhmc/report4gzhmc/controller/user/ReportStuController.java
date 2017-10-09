package org.gzhmc.report4gzhmc.controller.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.DateUtils;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.common.util.UploadFileUtil;
import org.gzhmc.report4gzhmc.service.ExperimentService;
import org.gzhmc.report4gzhmc.service.ExperimentalTestService;
import org.gzhmc.report4gzhmc.service.GradeExamService;
import org.gzhmc.report4gzhmc.service.ReportService;
import org.gzhmc.report4gzhmc.service.ReportRelativeService;

import org.gzhmc.report4gzhmc.service.StudentGradeService;
import org.gzhmc.report4gzhmc.service.StudentService;
import org.gzhmc.report4gzhmc.service.TestService;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 学生报告管理
 * 
 * @author stShen
 * @date 2017年2月5日
 */
@Controller
@RequestMapping("/student")
public class ReportStuController extends BaseController {

	@Autowired
	ReportService reportService;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentGradeService studentGradeService;
	@Autowired
	ReportRelativeService reportRelativeMappper;
	@Autowired
	ExperimentalTestService experimentalTestService;
	@Autowired
	TestService testService;
	@Autowired
	ExperimentService experimentService;
	@Autowired
	GradeExamService gradeExamService;

	// 表示批改为1，未批改为0
	public static int CHECKED = 1;
	public static int NOCHECK = 0;
	// 表示报告不及格，允许修改
	public static int RECHECK = 3;
	// 表示不及格，不允许修改
	public static int FAIL = 4;
	// ISCHECK=1表示report已经批改过或进入批改时间，ISSUMIT表示进入报告提交时间
	private int ISCHECK = 1;
	private int ISSUMIT = 0;
	// ONLINEEDIT=0表示该实验使用在线编辑提交，WORDSUBMIT=1表示实验使用word文档上传
	private int ONLINEEDIT = 0;
	private int WORDSUBMIT = 1;

	/**
	 * 报告编辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportEdit", "/reportEdit.html" })
	public ModelAndView reportEdit(HttpServletRequest request) {
		int reportId = StringUtils.string2int(request.getParameter("id"));
		Report report = reportService.getById(reportId);
		ModelAndView modelAndView = new ModelAndView("student/reportEdit");
		Experiment experiment = experimentService.getById(report.getcExperimentTextId());
		modelAndView.addObject("experimentName", experiment.getcExperimentName());
		modelAndView.addObject("test", report.getcExperimentTextId());
		modelAndView.addObject("report", report);
		return modelAndView;
	}

	/**
	 * 文档编辑上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportWordEdit", "/reportWordEdit.html" })
	public ModelAndView reportWordEdit(HttpServletRequest request) {
		int reportId = StringUtils.string2int(request.getParameter("id"));
		Report report = reportService.getById(reportId);
		ModelAndView modelAndView = new ModelAndView("student/wordEdit");
		Experiment experiment = experimentService.getById(report.getcExperimentTextId());
		modelAndView.addObject("experimentName", experiment.getcExperimentName());
		modelAndView.addObject("test", report.getcExperimentTextId());
		modelAndView.addObject("process", report.getcProcess());
		return modelAndView;
	}

	/**
	 * 报告下载
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/downLoadWord", "/downLoadWord.html" })
	public ResponseEntity<byte[]> downLoadWord(HttpServletRequest request) throws IOException {
		int reportId = StringUtils.string2int(request.getParameter("id"));
		Report report = reportService.getById(reportId);
		String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
		classpath = classpath.replaceAll("ReportCorrect/WEB-INF/classes/", "") + "ueditor/gzhmu/report";
		String wordPath = classpath + report.getcPath();
		String name = report.getcPath().substring(report.getcPath().lastIndexOf("/") + 1, report.getcPath().length());
		File file = new File(wordPath);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", name);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * 报告下载
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/downLoadWordScore", "/downLoadWordScore.html" })
	public ResponseEntity<byte[]> downLoadWordScore(HttpServletRequest request) throws IOException {
		int reportId = StringUtils.string2int(request.getParameter("id"));
		Report report = reportService.getById(reportId);
		String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
		classpath = classpath.replaceAll("ReportCorrect/WEB-INF/classes/", "") + "ueditor/gzhmu/score";
		String wordPath = classpath + report.getcPdfPath();
		String name = report.getcPdfPath().substring(report.getcPdfPath().lastIndexOf("/") + 1,
				report.getcPdfPath().length());
		File file = new File(wordPath);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", name);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * 报告查看
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportShow", "/reportShow.html" })
	public ModelAndView reportShow(HttpServletRequest request) {
		int reportId = StringUtils.string2int(request.getParameter("id"));
		Report report = reportService.getById(reportId);
		ModelAndView modelAndView = new ModelAndView("student/reportShow");
		modelAndView.addObject("report", report.getcContent());
		return modelAndView;
	}

	/**
	 * 上传实验报告action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/uploadExperiment", "/uploadExperiment.action" })
	public ModelAndView uploadExperiment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String experiment = request.getParameter("content");
		String process = request.getParameter("process");
		String test = request.getParameter("test");
		int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString().trim());
		Student student = studentService.getById(userid);
		PrintWriter out = response.getWriter();
		ModelAndView modelAndView = new ModelAndView("student/uploadReport");
		int eId = Integer.parseInt(test);
		Report reportMes = new Report();
		reportMes.setcExperimentTextId(eId);
		reportMes.setcStudentId(userid);
		int count1 = reportService.getCountByexperimrntAndstudent(reportMes);
		List<Report> reports = reportService.getByexperimrntAndstudentId(reportMes);
		modelAndView.addObject("process", process);
		int result = 0;
		out.flush();
		// 判断表格中是否存在数据
		if (count1 != 0) {
			// 判断是补考还是第一次考试
			Report report2 = getReport(reports);
			// 表中存在插入的数据则更新数据
			if (report2 != null) {
				report2.setcCreateTime(new Date());
				report2.setcProcess(process);
				// 存储上传的文档数据
				report2.setcContent(experiment);
				result = reportService.update(report2);
			}
			// else {
			// // 若为第一次补考，则新建数据，在补考表中更新数据
			// Report report = new Report();
			// report.setcCreateTime(new Date());
			// report.setcContent(experiment);
			// report.setcProcess(process);
			// report.setcExperimentTextId(eId);
			// report.setcStatu(NOCHECK);
			// report.setcStudentId(userid);
			// report.setcReportNum(DateUtils.getreportnum());
			// // System.out.println(report.getcReportNum());
			// result = reportService.add(report);
			// Resit resit = new Resit();
			// resit.setcExperiment(eId);
			// resit.setcStudentId(userid);
			// resit.setcReportId(report.getcId());
			// result = resitService.updateByStudentIdAndExperimentId(resit);
			// }
		} else {
			// 第一次考试
			Report report = new Report();
			report.setcCreateTime(new Date());
			report.setcContent(experiment);
			report.setcProcess(process);
			report.setcExperimentTextId(eId);
			report.setcStatu(NOCHECK);
			report.setcStudentId(userid);
			report.setcReportNum(DateUtils.getreportnum());
			// System.out.println(report.getcReportNum());
			result = reportService.add(report);
		}
		if (result != 0) {
			out.write("<script>alert('上传成功。');document.getElementById('loading').style.display='none';</script>");
		} else {
			out.write(
					"<script>alert('服务器异常，请稍后再试。');document.getElementById('loading').style.display='none';</script>");
		}
		List<GradeExam> gradeExams = gradeExamService.getByGradeId(student.getcGradeId(), ONLINEEDIT);
		modelAndView.addObject("tests", gradeExams);
		return modelAndView;

	}

	/**
	 * 修改实验报告action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/updateExperiment", "/updateExperiment.action" })
	public ModelAndView updateExperiment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String experiment = request.getParameter("content");
		String process = request.getParameter("process");
		int rid = StringUtils.string2int(request.getParameter("rId").trim());
		Report report = reportService.getById(rid);
		int result = 0;
		PrintWriter out = response.getWriter();
		out.flush();
		// 判断表格中是否存在数据
		if (report.getcStatu() != NOCHECK && report.getcStatu() != RECHECK) {
			out.write("<script>alert('不允许再次提交实验。');</script>");
		} else {
			// 表中存在插入的数据则更新数据
			if (report != null) {
				report.setcCreateTime(new Date());
				report.setcProcess(process);
				report.setcStatu(NOCHECK);
				// 存储上传的文档数据
				report.setcContent(experiment);
				result = reportService.update(report);
				if (result != 0) {
					out.write("<script>alert('编辑成功。');</script>");
				} else {
					out.write("<script>alert('编辑失败，请重新再试。');</script>");
				}
			}
		}

		ModelAndView modelAndView = new ModelAndView("student/reportEdit");
		Experiment et = experimentService.getById(report.getcExperimentTextId());
		modelAndView.addObject("experimentName", et.getcExperimentName());
		modelAndView.addObject("report", report);
		return modelAndView;
	}

	/**
	 * 修改实验报告action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/updateWord.action" })
	public ModelAndView updateWordAction(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile,
			@RequestParam(value = "process", required = false) String process,
			@RequestParam(value = "test", required = false) String experiment) throws IOException {
		int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString().trim());
		Report reportMes = new Report();
		reportMes.setcExperimentTextId(Integer.parseInt(experiment));
		reportMes.setcStudentId(userid);
		Report report2 = null;
		PrintWriter out = response.getWriter();
		out.flush();
		int count1 = reportService.getCountByexperimrntAndstudent(reportMes);
		List<Report> reports = reportService.getByexperimrntAndstudentId(reportMes);
		int result = 0;

		// 判断表格中是否存在数据
		if (count1 != 0) {
			// 判断是补考还是第一次考试
			report2 = getReport(reports);
			// 表中存在插入的数据则更新数据
			if (report2 != null) {
				ExperimentalTest experimentalTest = experimentalTestService.getById(StringUtils.string2int(experiment));
				StudentGrade studentGrade = studentGradeService.getById(userid);
				// 获取路径和上传文档
				String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
				classpath = classpath.replaceAll("ReportCorrect/WEB-INF/classes/", "") + "ueditor/gzhmu/report";
				String path2 = UploadFileUtil.uploadReport(request, studentGrade, experimentalTest, uploadFile, count1,
						classpath);
				report2.setcCreateTime(new Date());
				report2.setcProcess(process);
				report2.setcPath(path2);
				// 存储上传的文档数据
				result = reportService.update(report2);
			}
		}
		if (result != 0) {
			out.write("<script>alert('实验上传成功。')</script>");
		} else {
			out.write("<script>alert('实验上传失败，请稍后重新上传。')</script>");
		}
		ModelAndView modelAndView = new ModelAndView("student/wordEdit");
		Experiment experiment1 = experimentService.getById(Integer.parseInt(experiment));
		modelAndView.addObject("experimentName", experiment1.getcExperimentName());
		modelAndView.addObject("test", Integer.parseInt(experiment));
		modelAndView.addObject("process", process);
		return modelAndView;
	}

	// 返回补考的数据
	private Report getReport(List<Report> reports) {
		for (Report r : reports) {
			// 判断是否允许批改
			if (r.getcStatu() == NOCHECK || r.getcStatu() == RECHECK) {
				return r;
			}
		}
		return null;
	}

	// 判断学生是否已经考核通过
	public boolean isAllowUpload(List<Report> reports) {
		for (Report r : reports) {
			if (r.getcStatu() != NOCHECK && r.getcStatu() != RECHECK) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 跳转report成绩查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("scoreInquery")
	public ModelAndView scoreInquery(HttpServletRequest request) {
		String userId = request.getSession().getAttribute("userid").toString().trim();
		List<ReportRelative> relatives = reportRelativeMappper.getScoreByStudentId(StringUtils.string2int(userId),
				ONLINEEDIT);
		return new ModelAndView("student/scoreInquery").addObject("reportRelativesbyStuid", relatives);
	}

	/**
	 * 跳转word成绩查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("scoreWordInquery")
	public ModelAndView scoreWordInquery(HttpServletRequest request) {
		String userId = request.getSession().getAttribute("userid").toString().trim();
		List<ReportRelative> relatives = reportRelativeMappper.getScoreByStudentId(StringUtils.string2int(userId),
				WORDSUBMIT);
		return new ModelAndView("student/scoreWordInquery").addObject("reportRelativesbyStuid", relatives);
	}

	@RequestMapping("scoreShow")
	public ModelAndView scoreShow(HttpServletRequest request) {
		String rid = request.getParameter("rid");
		ReportRelative relatives = reportRelativeMappper.getPostilById(StringUtils.string2int(rid));
		return new ModelAndView("student/scoreShow").addObject("postil", relatives.getcPdfPath());
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
	 */
	@RequestMapping(value = { "/uploadWord.action" })
	public ModelAndView uploadReportAction(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile,
			@RequestParam(value = "process", required = false) String process,
			@RequestParam(value = "experiment", required = false) String experiment) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		int result = 0;
		int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString().trim());
		ModelAndView modelAndView = new ModelAndView("student/uploadWord");
		PrintWriter out = response.getWriter();
		// 根据学生选择的实验id找到对应实验
		ExperimentalTest experimentalTest = experimentalTestService.getById(StringUtils.string2int(experiment));
		StudentGrade studentGrade = studentGradeService.getById(userid);
		// 获取所有userid和experiment的report
		Report reportMes = new Report();
		reportMes.setcExperimentTextId(experimentalTest.getcId());
		reportMes.setcStudentId(studentGrade.getcUserId());
		int count1 = reportService.getCountByexperimrntAndstudent(reportMes);
		List<Report> reports = reportService.getByexperimrntAndstudentId(reportMes);
		// 设置最大上传容量
		int maxSize = 10 * 1024 * 1024;
		out.flush();
		// 判断文件大小是否合格
		if (uploadFile.getSize() > maxSize) {
			modelAndView.addObject("process", process);
			out.write("<script>alert('上传文件超过10mb，请重新上传。');document.getElementById('loading').style.display='none';</script>");
		} else {
			// 获取路径和上传文档
			String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
			classpath = classpath.replaceAll("ReportCorrect/WEB-INF/classes/", "") + "ueditor/gzhmu/report";
			String path2 = UploadFileUtil.uploadReport(request, studentGrade, experimentalTest, uploadFile, count1,
					classpath);
			// 判断表格中是否存在数据
			if (count1 != 0) {
				// 判断是补考还是第一次考试
				Report report2 = getReport(reports);
				// 表中存在插入的数据则更新数据
				if (report2 != null) {
					report2.setcCreateTime(new Date());
					report2.setcProcess(process);
					report2.setcPath(path2);
					result = reportService.update(report2);
				}
				// else {
				// //若为第一次补考，则新建数据，在补考表中更新数据
				// Report report = new Report();
				// report.setcCreateTime(new Date());
				// report.setcPath(path2);
				// report.setcProcess(process);
				// report.setcExperimentTextId(StringUtils.string2int(experiment));
				// report.setcStatu(NOCHECK);
				// report.setcStudentId(userid);
				// report.setcReportNum(DateUtils.getreportnum());
				// // System.out.println(report.getcReportNum());
				// result = reportService.add(report);
				// Resit resit = new Resit();
				// resit.setcExperiment(experimentalTest.getcId());
				// resit.setcStudentId(studentGrade.getcUserId());
				// resit.setcReportId(report.getcId());
				// result = resitService.updateByStudentIdAndExperimentId(resit);
				// }
			} else {
				// 第一次考试
				Report report = new Report();
				report.setcCreateTime(new Date());
				report.setcPath(path2);
				report.setcProcess(process);
				report.setcExperimentTextId(StringUtils.string2int(experiment));
				report.setcStatu(NOCHECK);
				report.setcStudentId(userid);
				report.setcReportNum(DateUtils.getreportnum());
				// System.out.println(report.getcReportNum());
				result = reportService.add(report);
			}
			if (result != 0) {
				out.write("<script>alert('报告上传成功。')</script>");
			} else {
				out.write("<script>alert('服务器异常，请稍后再试。')</script>");
			}
		}
		Student student = studentService.getById(userid);
		List<GradeExam> gradeExams = gradeExamService.getByGradeId(student.getcGradeId(), WORDSUBMIT);

		modelAndView.addObject("experiment", gradeExams);
		return modelAndView;
	}

	/**
	 * 管理所有报告
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/reportManage", "reportManage.html" })
	public ModelAndView reportManage(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentService.getById(StringUtils.string2int(userid));
		List<ReportRelative> reportOnlineRelatives = reportRelativeMappper
				.getReportLinkExperimentByStuIdAndSubmitForm(student.getcUserId(), ONLINEEDIT);
		List<ReportRelative> reportWordRelatives = reportRelativeMappper
				.getReportLinkExperimentByStuIdAndSubmitForm(student.getcUserId(), WORDSUBMIT);
		// System.out.println(reportRelatives.size());
		ModelAndView modelAndView = new ModelAndView("student/reportManage");
		modelAndView.addObject("reportOnlineRelatives", reportOnlineRelatives);
		modelAndView.addObject("reportWordRelatives", reportWordRelatives);
		return modelAndView;
	}

	/**
	 * 查看文档代码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/reportWordShow")
	public ModelAndView reportWordShow(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String rid = request.getParameter("id");
		Report report = reportService.getById(StringUtils.string2int(rid));
		ModelAndView modelAndView = new ModelAndView("student/wordShow");
		String classpath = "http://10.178.50.64:8080/ueditor/gzhmu/report";
		// 修改word路径获取html路径
		String htmlPath = report.getcPath().replaceFirst("word", "html");
		htmlPath = htmlPath.substring(0, htmlPath.lastIndexOf(".")) + ".html";
		modelAndView.addObject("wordpath", classpath + htmlPath);
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
	public ModelAndView uploadReport(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("student/uploadReport");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentService.getById(Integer.parseInt(userid));
		// 选出设置为在线编辑提交的实验
		List<GradeExam> gradeExams = gradeExamService.getByGradeId(student.getcGradeId(), ONLINEEDIT);
		// List<Test> tests=testService.getAll();
		modelAndView.addObject("tests", gradeExams);
		return modelAndView;
	}

	/**
	 * 报告上传页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/uploadWord", "/uploadWord.html" })
	public ModelAndView uploadWord(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("student/uploadWord");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Student student = studentService.getById(Integer.parseInt(userid));
		// 选出设置为文档上传的实验
		List<GradeExam> gradeExams = gradeExamService.getByGradeId(student.getcGradeId(), WORDSUBMIT);
		// List<Test> tests=testService.getAll();
		modelAndView.addObject("experiment", gradeExams);
		return modelAndView;
	}

	/**
	 * 检查是否可以提交在线编辑数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/checkHtml.action" })
	public void checkHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int userid = Integer.parseInt(request.getSession().getAttribute("userid").toString().trim());
		Student student = studentService.getById(userid);
		int eId = Integer.parseInt(request.getParameter("id"));
		Report reportMes = new Report();
		reportMes.setcExperimentTextId(eId);
		reportMes.setcStudentId(userid);
		int count1 = reportService.getCountByexperimrntAndstudent(reportMes);
		List<Report> reports = reportService.getByexperimrntAndstudentId(reportMes);
		GradeExam gradeExam = gradeExamService.getByStuIdAndExamId(userid, eId);

		JSONObject jo = new JSONObject();
		if (count1 != 0 && gradeExam.getcStatus() == ISCHECK) {
			jo.put("mes", "此实验进入批改时间，不允许继续编辑。");
		} else if (count1 != 0 && !isAllowUpload(reports)) {
			jo.put("mes", "此实验已经通过考核，请选择正确的实验提交。");
		} else if (null == student.getcPicturePath() || student.getcPicturePath().equals("")) {
			System.out.println(student.getcPicturePath().equals(""));
			jo.put("mes", "在上传文档之前请先上传照片，以便生成证书。");
		} else {
			jo.put("mes", "");
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(jo.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
