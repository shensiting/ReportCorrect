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
import org.gzhmc.common.util.JsonUtils;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.common.util.UploadFileUtil;
import org.gzhmc.report4gzhmc.service.ExperimentService;
import org.gzhmc.report4gzhmc.service.ExperimentalTestService;
import org.gzhmc.report4gzhmc.service.GradeExamService;
import org.gzhmc.report4gzhmc.service.GradeMajorCollegeService;
import org.gzhmc.report4gzhmc.service.ReportService;
import org.gzhmc.report4gzhmc.service.ReportRelativeService;
import org.gzhmc.report4gzhmc.service.ScoreSheetService;
import org.gzhmc.report4gzhmc.service.StudentGradeService;
import org.gzhmc.report4gzhmc.service.StudentService;

import org.gzhmc.report4gzhmc.service.TeacherGradeLinkService;
import org.gzhmc.report4gzhmc.service.TeacherGradeService;
import org.gzhmc.report4gzhmc.service.TeacherService;
import org.gzhmc.report4gzhmc.service.TestService;
import org.gzhmc.report4gzhmc.model.ExcelMessage;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.ScoreSheet;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherGrade;
import org.gzhmc.report4gzhmc.model.Test;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 教师报告管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/teacher")
public class ReportTeaController extends BaseController {

	@Autowired
	TeacherService teacherService;
	@Autowired
	ReportService reportService;
	//@Autowired
	//TeacherExperimentalService teacherExperimentalService;
	@Autowired
	ReportRelativeService reportRelativeService;
	@Autowired
	StudentGradeService studentGradeService;
	@Autowired
	GradeMajorCollegeService gradeMajorCollegeService;
	@Autowired
	ExperimentalTestService experimentalTestService;
	@Autowired
	ExperimentService experimentService;
	@Autowired
	ScoreSheetService scoreSheetService;
	@Autowired
	TestService testService;
	@Autowired
	TeacherGradeLinkService teacherGradeLinkService;
	@Autowired
	TeacherGradeService teacherGradeService;
	@Autowired
	GradeExamService gradeExamService;
	@Autowired
	StudentService studentService;

	// ISCHECK=1表示report已经批改过或进入批改时间，ISSUMIT表示进入报告提交时间,CHECKED表示report已经被批改;NOPASS表示不及格
	private int ISCHECK = 1;
	private int ISSUMIT = 0;
	private int CHECKED = 1;
	private int NOPASS = 2;
	// ONLINEEDIT=0表示该实验使用在线编辑提交，WORDSUBMIT=1表示实验使用word文档上传
	private int ONLINEEDIT = 0;
	private int WORDSUBMIT = 1;

	/**
	 * 跳转report成绩管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("scoreManage")
	public ModelAndView scoreManage(HttpServletRequest request) {
		String userId = request.getSession().getAttribute("userid").toString().trim();
		List<ReportRelative> relatives = reportRelativeService.getScoreByTeacherId(StringUtils.string2int(userId),
				ONLINEEDIT);
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService
				.getAllTeaGraByTeaIdAndStatu(StringUtils.string2int(userId));
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/scoreManage");
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelativesbyStuid", relatives);
		return modelAndView;
	}

	/**
	 * 作业统计action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("exportExamAccount")
	public void exportExamAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int gradeId = StringUtils.string2int(request.getParameter("cGradeId").trim());
		int experimentId = StringUtils.string2int(request.getParameter("experiment").trim());
		List<Student> students = studentService.getNoSubmitStuByGradeIdAndExamId(experimentId, gradeId);
		int sum = studentService.getCountAll(gradeId);
		JSONArray json = new JSONArray();
		JSONObject jso = new JSONObject();
		jso.put("sum", sum);
		jso.put("submitStu", sum - students.size());
		jso.put("noSubmit", students.size());
		json.put(jso);
		for (Student a : students) {
			JSONObject jo = new JSONObject();
			jo.put("stuNum", a.getcStudentNumber());
			jo.put("name", a.getcName());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 判断实验班级是否关联
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("checkRelated")
	public void checkRelated(HttpServletRequest request, HttpServletResponse response) {
		int gradeId = StringUtils.string2int(request.getParameter("cGradeId").trim());
		int experimentId = StringUtils.string2int(request.getParameter("experiment").trim());
		ResultJson json = new ResultJson();
		// 判断是否有其他教师关联此班级
		int i = gradeExamService.getByExamIdAndGradeId(experimentId, gradeId);
		if (i == 0) {
			json.setSuccess(false);
		} else {
			json.setSuccess(true);

		}
		writeResultJson(response, json);
	}

	/**
	 * 跳转作业统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("examAccount")
	public ModelAndView examAccount(HttpServletRequest request) {
		String userId = request.getSession().getAttribute("userid").toString().trim();
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService
				.getAllTeaGraByTeaIdAndStatu(StringUtils.string2int(userId));
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/examAccount");
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		return modelAndView;
	}

	/**
	 * 跳转word成绩管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("scoreWordManage")
	public ModelAndView scoreWordManage(HttpServletRequest request) {
		String userId = request.getSession().getAttribute("userid").toString().trim();
		List<ReportRelative> relatives = reportRelativeService.getScoreByTeacherId(StringUtils.string2int(userId),
				WORDSUBMIT);
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService
				.getAllTeaGraByTeaIdAndStatu(StringUtils.string2int(userId));
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/scoreWordManage");
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelativesbyStuid", relatives);
		return modelAndView;
	}

	// 查看文档代码
	@RequestMapping("/scoreWordShow")
	public ModelAndView scoreWordShow(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String rid = request.getParameter("id");
		Report report = reportService.getById(StringUtils.string2int(rid));
		ModelAndView modelAndView = new ModelAndView("student/wordShow");
		String classpath = "http://localhost:8080/ueditor/gzhmu/score";
		// 修改word路径获取html路径
		String htmlPath = report.getcPdfPath().replaceFirst("word", "html");
		htmlPath = htmlPath.substring(0, htmlPath.lastIndexOf(".")) + ".html";
		modelAndView.addObject("wordpath", classpath + htmlPath);
		return modelAndView;

	}

	/**
	 * 更改不及格实验提交状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("allowSubmit")
	public void allowSubmit(HttpServletRequest request, HttpServletResponse response) {
		int id = StringUtils.string2int(request.getParameter("id"));
		int statu = StringUtils.string2int(request.getParameter("msg"));
		Report report = reportService.getById(id);
		report.setcStatu(statu);
		ResultJson json = new ResultJson();
		int result = reportService.update(report);
		if (result == 0) {
			json.setSuccess(false);
			json.setMsg("修改失败！请稍后再试！");
		} else {
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeResultJson(response, json);
	}

	/**
	 * 返回json数据
	 * 
	 * @param json
	 * @param response
	 */
	protected void response_write(List json, HttpServletResponse response) {
		try {

			response.setCharacterEncoding("UTF-8"); // 设置编码格式
			// response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JsonUtils.toJson(json));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// logger.error(e.getLocalizedMessage(),e);
		}
	}

	/**
	 * 获得实验分类id获取对应实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getRelevance" })
	public void getRelevance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Experiment> experiments = experimentService.getByClassify(id);
		response_write(experiments, response);
	}

	/**
	 * 根据实验id和班级获取对应实验报告
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getReportList" })
	public ModelAndView getReportList(HttpServletRequest request) throws IOException {
		int eid = Integer.parseInt(request.getParameter("experiment"));
		int gradeId = Integer.parseInt(request.getParameter("cGradeId"));
		List<ReportRelative> relatives = reportRelativeService.getExitScoreByExperimentIdandGradeId(eid, gradeId,
				ONLINEEDIT);
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService.getAllTeaGraByTeaIdAndStatu(userid);
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/reportManage");

		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelatives", relatives);
		return modelAndView;

	}

	/**
	 * 根据实验id和班级获取对应实验报告
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getWordList" })
	public ModelAndView getWordList(HttpServletRequest request) throws IOException {
		int eid = Integer.parseInt(request.getParameter("experiment"));
		int gradeId = Integer.parseInt(request.getParameter("cGradeId"));
		List<ReportRelative> relatives = reportRelativeService.getExitScoreByExperimentIdandGradeId(eid, gradeId,
				WORDSUBMIT);
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService.getAllTeaGraByTeaIdAndStatu(userid);
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/wordManage");

		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelatives", relatives);
		return modelAndView;

	}

	/**
	 * 旧版实验报告显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 
	@RequestMapping("/teacherIndex")
	public ModelAndView indexTeacher(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherService.getById(StringUtils.string2int(userid));
		List<ExperimentalTest> experimentalTests = experimentalTestService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeService.getAll();
		ModelAndView modelAndView = new ModelAndView("teacher/teacherIndex");
		modelAndView.addObject("teachername", teacher.getcName());
		int count = teacherExperimentalService.getCountByTeacherId(teacher.getcUserId());
		if (count > 0) {
			List<TeacherExperimental> teacherExperimentals = teacherExperimentalService
					.getByTeacherId(teacher.getcUserId());
			int[] haves = new int[teacherExperimentals.size()];
			for (int i = 0; i < teacherExperimentals.size(); i++) {
				haves[i] = teacherExperimentals.get(i).getcExperimentalTestId();
			}
			List<ReportRelative> reportRelatives = reportRelativeService.getExitScoreByExperimentId(haves);
			modelAndView.addObject("reportRelatives", reportRelatives);
		}
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("experimentalTests", experimentalTests);
		return modelAndView;
	}
*/
	/**
	 * 新版在线编辑实验报告显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/reportManage")
	public ModelAndView reportManage(HttpServletRequest request) {
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService.getAllTeaGraByTeaIdAndStatu(userid);
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		List<ReportRelative> reportRelatives = new ArrayList<ReportRelative>();
		for (TeacherGrade t : teacherGrade) {
			List<ReportRelative> relatives = reportRelativeService.getByGradeId(t.getcGradeId(), ONLINEEDIT);
			reportRelatives.addAll(relatives);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/reportManage");
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelatives", reportRelatives);
		return modelAndView;
	}

	/**
	 * 新版word实验报告显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/wordManage")
	public ModelAndView wordManage(HttpServletRequest request) {
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService.getAllTeaGraByTeaIdAndStatu(userid);
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		List<ReportRelative> reportRelatives = new ArrayList<ReportRelative>();
		for (TeacherGrade t : teacherGrade) {
			List<ReportRelative> relatives = reportRelativeService.getByGradeId(t.getcGradeId(), WORDSUBMIT);
			reportRelatives.addAll(relatives);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/wordManage");
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelatives", reportRelatives);
		return modelAndView;
	}

	/**
	 * 教师word批改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = { "wordCorrect.action" })
	public ModelAndView wordCorrectAction(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile,
			@RequestParam(value = "process", required = false) String process,
			@RequestParam(value = "rid", required = false) String rid,
			@RequestParam(value = "score", required = false) String score) throws IOException {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Report report = reportService.getById(StringUtils.string2int(rid));
		PrintWriter out = response.getWriter();
		out.flush();
		int maxSize = 10 * 1024 * 1024;

		if (uploadFile.getSize() > maxSize) {
			ModelAndView modelAndView1 = new ModelAndView("teacher/wordCorrect");
			out.write(
					"<script>alert('上传文件超过10mb，请重新上传。');document.getElementById('loading').style.display='none';</script>");
			modelAndView1.addObject("rid", rid);
			modelAndView1.addObject("process", process);
			return modelAndView1;
		}

		int result = 0;
		// 存入成绩数据
		ScoreSheet scoreSheet;
		if (report.getcScoreId() != 0) {
			scoreSheet = scoreSheetService.getById(report.getcScoreId());
			scoreSheet.setcConclution(process);
			scoreSheet.setcCreateTime(new Date());
			scoreSheet.setcSum(StringUtils.string2double(score));
			result = scoreSheetService.update(scoreSheet);
		} else {
			scoreSheet = new ScoreSheet();
			scoreSheet.setcConclution(process);
			scoreSheet.setcCreateTime(new Date());
			scoreSheet.setcSum(StringUtils.string2double(score));
			result = scoreSheetService.add(scoreSheet);
		}
		if (result != 0) {
			ExperimentalTest experimentalTest = experimentalTestService.getById(report.getcExperimentTextId());
			StudentGrade studentGrade = studentGradeService.getById(report.getcStudentId());
			// 获取路径和上传文档
			String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
			classpath = classpath.replaceAll("ReportCorrect/WEB-INF/classes/", "") + "ueditor/gzhmu/score";
			String path2 = UploadFileUtil.uploadReport(request, studentGrade, experimentalTest, uploadFile, 1,
					classpath);

			report.setcScoreId(scoreSheet.getcId());
			report.setcPdfPath(path2);
			report.setcTeacherId(StringUtils.string2int(userid));
			if (StringUtils.string2double(score) < 60) {
				report.setcStatu(NOPASS);
			} else {
				report.setcStatu(CHECKED);
			}
			result = reportService.updateScore(report);
			if (result == 0) {
				scoreSheetService.delete(scoreSheet.getcId());
				out.write(
						"<script>alert('服务器出错，请稍后再试！');document.getElementById('loading').style.display='none';</script>");
			} else {
				out.write("<script>alert('批改成功。');document.getElementById('loading').style.display='none';</script>");
			}
		} else {
			out.write(
					"<script>alert('服务器出错，请稍后再试！');document.getElementById('loading').style.display='none';</script>");
		}
		List<TeacherGrade> teacherGrade = teacherGradeService
				.getAllTeaGraByTeaIdAndStatu(StringUtils.string2int(userid));
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		List<ReportRelative> reportRelatives = new ArrayList<ReportRelative>();
		for (TeacherGrade t : teacherGrade) {
			List<ReportRelative> relatives = reportRelativeService.getByGradeId(t.getcGradeId(), WORDSUBMIT);
			reportRelatives.addAll(relatives);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/wordManage");
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelatives", reportRelatives);
		return modelAndView;

	}

	/**
	 * 教师批改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @RequestMapping(value = { "reportCorrect2.action" })
	 * @Transactional public ModelAndView
	 *                reportCorrect2Action(HttpServletRequest request,
	 *                HttpServletResponse response) throws Exception { String
	 *                rid = request.getParameter("rid"); String userid =
	 *                request.getSession().getAttribute("userid").toString().trim();
	 *                Teacher teacher =
	 *                teacherService.getById(StringUtils.string2int(userid));
	 *                Report report =
	 *                reportService.getById(StringUtils.string2int(rid));
	 *                ExperimentalTest experimentalTest =
	 *                experimentalTestService.getById(report.getcExperimentTextId());
	 *                response.setContentType("text/html; charset=utf8");
	 *                PrintWriter out = response.getWriter(); String theory =
	 *                request.getParameter("theory"); String reagen =
	 *                request.getParameter("reagen"); String instrument =
	 *                request.getParameter("instrument"); String experiment =
	 *                request.getParameter("experiment"); String labresult =
	 *                request.getParameter("labresult"); String conclusion =
	 *                request.getParameter("conclusion"); String score =
	 *                request.getParameter("score"); String comment =
	 *                request.getParameter("comment"); // 存入成绩数据 ScoreSheet
	 *                scoreSheet = new ScoreSheet();
	 *                scoreSheet.setcComment(comment); //
	 *                scoreSheet.setcConclution(Double.parseDouble(conclusion));
	 *                scoreSheet.setcCreateTime(new Date());
	 *                scoreSheet.setcExperiment(Double.parseDouble(experiment));
	 *                scoreSheet.setcInserument(Double.parseDouble(instrument));
	 *                scoreSheet.setcLabresult(Double.parseDouble(labresult));
	 *                scoreSheet.setcReagen(Double.parseDouble(reagen));
	 *                scoreSheet.setcTherory(Double.parseDouble(theory));
	 *                scoreSheet.setcSum(Double.parseDouble(score));
	 *                out.flush(); // System.out.println("开始生成证书"); //
	 *                判断是否及格，及格则生成证书 if (report.getcStatu() == 1) {
	 *                out.write("<script>alert('已由另外一名老师批改完成，请返回另选一份。');</script>");
	 *                } else if (StringUtils.string2double(score) - 60 < 0) {
	 * 
	 *                int result = scoreSheetService.add(scoreSheet); if (result
	 *                != 0) { report.setcTeacherId(teacher.getcUserId());
	 *                report.setcStatu(ISCHECK);
	 *                report.setcScoreId(scoreSheet.getcId()); result =
	 *                reportService.update(report); if (result != 0) { //
	 *                System.out.println("不及格批改成功"); Resit resit = new Resit();
	 *                resit.setcExperiment(report.getcExperimentTextId());
	 *                resit.setcStudentId(report.getcStudentId()); result =
	 *                resitService.insert(resit); if (result != 0) {
	 *                out.write("<script>alert('批改成功！成绩为不及格，已加入补考名单。');</script>");
	 *                } else {
	 *                out.write("<script>alert('批改成功！加入补考名单失败，请联系管理员。');</script>");
	 *                } } else {
	 *                out.write("<script>alert('服务器异常，请稍后再试！');</script>"); } }
	 *                else { out.write("服务器异常，请稍后再试！"); } } else { StudentGrade
	 *                studentGrade =
	 *                studentGradeService.getById(report.getcStudentId()); //
	 *                获取相对地址 String path1 =
	 *                request.getServletContext().getRealPath("gzhmc/report");
	 *                // 获取存储位置 String path2 = "/" +
	 *                studentGrade.getCollege().getcCollegeName() + "/" +
	 *                studentGrade.getGrade().getcYearClass() + "/" +
	 *                studentGrade.getMajor().getcMajorName() + "/" +
	 *                studentGrade.getGrade().getcClass() + "/" +
	 *                experimentalTest.getcExperimentName() + "/"; // String
	 *                qrPath = path1 + path2 + "qrcode/"; String pdfPath = path1
	 *                + path2 + "pdf\\"; // 替换路径中的\ // qrPath =
	 *                qrPath.replace('\\', '/'); pdfPath = pdfPath.replace('/',
	 *                '\\'); // 将实验命名为实验报告id+学号 String filename =
	 *                report.getcId() + "_" + studentGrade.getcStudentNumber();
	 *                // String //
	 *                path=request.getServletContext().getRealPath("jsp/teacher");
	 * 
	 *                int result = scoreSheetService.add(scoreSheet); if (result
	 *                != 0) { // 存入实验部分字段
	 *                report.setcTeacherId(teacher.getcUserId());
	 *                report.setcStatu(ISCHECK);
	 *                report.setcScoreId(scoreSheet.getcId()); //
	 *                report.setcQRcode(path2 + "qrcode/" + filename + ".jpg");
	 *                report.setcPdfPath(pdfPath + filename + ".pdf"); result =
	 *                reportService.update(report); if (result != 0) { //
	 *                若文件不存在则创建 // File tempFile1 = new File(qrPath); // if
	 *                (!tempFile1.exists()) { // tempFile1.mkdirs(); // } File
	 *                tempFile2 = new File(pdfPath); if (!tempFile2.exists()) {
	 *                tempFile2.mkdirs(); } // // 生成二维码 //
	 *                QRCodeUtil.createQRCode("/Report4gzhmc/teacher/show?rid="
	 *                // + report.getcId(), 200, // qrPath + filename + ".jpg");
	 *                // 生成pdf ReportRelative reportRelative =
	 *                reportRelativeService.getById(report.getcId());
	 *                SetpdfCertificate.setCertificate(reportRelative, request);
	 * 
	 *                out.write("<script>alert('批改成功！');</script>"); } else {
	 *                out.write("<script>alert('服务器异常，请稍后再试！');</script>"); } }
	 *                else { out.write("服务器异常，请稍后再试！"); }
	 * 
	 *                } List<TeacherExperimental> teacherExperimentals =
	 *                teacherExperimentalService.getByTeacherId(teacher.getcUserId());
	 *                int[] haves = new int[teacherExperimentals.size()]; for
	 *                (int i = 0; i < teacherExperimentals.size(); i++) {
	 *                haves[i] =
	 *                teacherExperimentals.get(i).getcExperimentalTestId(); }
	 *                List<ExperimentalTest> experimentalTests =
	 *                experimentalTestService.getAll(); List<GradeMajorCollege>
	 *                gradeMajorColleges = gradeMajorCollegeService.getAll();
	 *                List<ReportRelative> reportRelatives =
	 *                reportRelativeService.getExitScoreByExperimentId(haves);
	 *                ModelAndView modelAndView = new
	 *                ModelAndView("teacher/teacherIndex");
	 *                modelAndView.addObject("teachername", teacher.getcName());
	 *                modelAndView.addObject("experimentalTests",
	 *                experimentalTests);
	 *                modelAndView.addObject("reportRelatives",
	 *                reportRelatives);
	 *                modelAndView.addObject("gradeMajorColleges",
	 *                gradeMajorColleges); return modelAndView; }
	 */

	/**
	 * 跳转实验报告查看页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/teacherreportshow")
	public ModelAndView reportShow(HttpServletRequest request) throws IOException {
		String rid = request.getParameter("rid");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherService.getById(StringUtils.string2int(userid));
		Report report = reportService.getById(StringUtils.string2int(rid));
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
	public ModelAndView downloadPdf(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherService.getById(StringUtils.string2int(userid));
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeService.getAll();
		List<ReportRelative> reportRelativesbystatus = reportRelativeService.getAllBystatu();
		// List<ReportRelative> reportRelatives =
		// reportRelativeService.getExperimentIdandGradeId();
		List<ExperimentalTest> experimentalTests = experimentalTestService.getAll();
		ModelAndView modelAndView = new ModelAndView("teacher/downloadpdf");
		modelAndView.addObject("experimentalTests", experimentalTests);
		// modelAndView.addObject("reportRelatives", reportRelatives);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("teachername", teacher.getcName());
		modelAndView.addObject("reportRelativesbystatus", reportRelativesbystatus);
		return modelAndView;
	}

	/**
	 * 按班按实验查找pdf成绩
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryPdf")
	public ModelAndView queryPdfAction(HttpServletRequest request) {
		// 获取课程表格数据
		int gradeid = StringUtils.string2int(request.getParameter("gradeidq"));
		// 获取实验表格数据
		int experimentid = StringUtils.string2int(request.getParameter("experimentidq"));
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherService.getById(StringUtils.string2int(userid));

		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeService.getAll();
		List<ReportRelative> relatives = reportRelativeService.getScoreByExperimentIdandGradeId(experimentid, gradeid);
		List<ExperimentalTest> experimentalTests = experimentalTestService.getAll();
		ModelAndView modelAndView = new ModelAndView("teacher/downloadpdf");
		modelAndView.addObject("experimentalTests", experimentalTests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("teachername", teacher.getcName());
		modelAndView.addObject("reportRelativesbystatus", relatives);
		return modelAndView;
	}

	/**
	 * pdf下载表格导出
	 * 
	 * @param request
	 * @param response
	 * @param action
	 *            不同按钮的值
	 * @throws Exception
	 */
	@RequestMapping("/downloadPdfExce.action")
	public void downloadPdfAction(HttpServletRequest request, HttpServletResponse response, @RequestParam String action)
			throws Exception {
		// Teacher teacher =
		// teacherService.getById(StringUtils.string2int(userid));
		// 获取课程表格数据
		int gradeid = StringUtils.string2int(request.getParameter("gradeid"));
		// 获取实验表格数据
		int experimentid = StringUtils.string2int(request.getParameter("experimentid"));

		if (action.equals("下载证书")) {
			response.setContentType("text/html; charset=utf8");
			GradeMajorCollege gradeMajorCollege = gradeMajorCollegeService.getById(gradeid);
			ExperimentalTest experimentalTest = experimentalTestService.getById(experimentid);
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
				out.write("<script>alert('还未有数据，请确认后选择。');window.location.href='../teacher/downloadpdf';</script>");
			} else {
				files.add(file);
				DownLoadUtil.downLoadFiles(files, request, response);
			}
			out.close();
		} else if (action.equals("导出表格")) {
			List<ReportRelative> relatives = reportRelativeService.getScoreByExperimentIdandGradeId(experimentid,
					gradeid);
			List<ExcelMessage> excelMessages = new ArrayList<ExcelMessage>();
			ExcelMessage excelMessage;
			GradeMajorCollege gradeMajorCollege;
			// System.out.println(relatives.size());
			if (relatives.size() > 0) {
				// 得到学院年级班级
				gradeMajorCollege = gradeMajorCollegeService.getById(gradeid);
				for (int i = 0; i < relatives.size(); i++) {
					excelMessage = new ExcelMessage();
					excelMessage.setcReportNum(relatives.get(i).getcReportNum());
					excelMessage.setcExperimentName(relatives.get(i).getExperimental().getcExperimentName());
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
				out.write("<script>alert('还未有数据，请重新选择。');window.location.href='../teacher/downloadpdf';</script>");

			}
		}

	}

	/**
	 * 实验报告表格查看
	 * 
	 * @RequestMapping("/queryreport") public ModelAndView
	 * queryReportAction(HttpServletRequest request, HttpServletResponse
	 * response) { response.setContentType("text/html; charset=utf8"); int i;
	 * int gradeid = Integer.parseInt(request.getParameter("gradeidq")); int
	 * experimentid = Integer.parseInt(request.getParameter("experimentidq"));
	 * // System.out.println("gradeid" + gradeid + "experimentid" + //
	 * experimentid); String userid =
	 * request.getSession().getAttribute("userid").toString().trim(); Teacher
	 * teacher = teacherService.getById(StringUtils.string2int(userid));
	 * List<ExperimentalTest> experimentalTests =
	 * experimentalTestService.getAll();
	 * 
	 * List<GradeMajorCollege> gradeMajorColleges =
	 * gradeMajorCollegeService.getAll();
	 * 
	 * ModelAndView modelAndView = new ModelAndView("teacher/teacherIndex");
	 * modelAndView.addObject("teachername", teacher.getcName()); int count =
	 * teacherExperimentalService.getCountByTeacherId(teacher.getcUserId());
	 * List<ReportRelative> reportRelatives; if (count > 0) {
	 * List<TeacherExperimental> teacherExperimentals =
	 * teacherExperimentalService .getByTeacherId(teacher.getcUserId()); int
	 * TSize = teacherExperimentals.size(); for (i = 0; i < TSize; i++) { if
	 * (experimentid == teacherExperimentals.get(i).getcExperimentalTestId()) {
	 * break; } } if (i == TSize) { reportRelatives = null; } else {
	 * reportRelatives =
	 * reportRelativeService.getExitScoreByExperimentIdandGradeId(experimentid,
	 * gradeid); } modelAndView.addObject("reportRelatives", reportRelatives); }
	 * modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
	 * modelAndView.addObject("experimentalTests", experimentalTests); return
	 * modelAndView; }
	 */
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
		GradeMajorCollege gradeMajorCollege = gradeMajorCollegeService.getById(StringUtils.string2int(gradeid));
		ExperimentalTest experimentalTest = experimentalTestService.getById(StringUtils.string2int(experimentid));
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
			out.write("<script>alert('对不起，实验报告不存在');window.location.href='../teacher/teacherIndex';</script>");
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
		List<ReportRelative> relatives = reportRelativeService.getByManyId(haves);
		List<ExcelMessage> excelMessages = new ArrayList<ExcelMessage>();
		ExcelMessage excelMessage;
		GradeMajorCollege gradeMajorCollege;
		// System.out.println(relatives.size());
		for (int i = 0; i < relatives.size(); i++) {
			excelMessage = new ExcelMessage();
			// excelMessage.setcReportNum(relatives.get(i).getcReportNum());
			excelMessage.setcExperimentName(relatives.get(i).getExperimental().getcExperimentName());
			excelMessage.setcName(relatives.get(i).getStudent().getcName());
			excelMessage.setcStudentNumber(relatives.get(i).getStudent().getcStudentNumber());
			excelMessage.setcSum(relatives.get(i).getScoreSheet().getcSum());
			gradeMajorCollege = gradeMajorCollegeService.getById(relatives.get(i).getStudent().getcGradeId());
			excelMessage.setcCollegeName(gradeMajorCollege.getCollege().getcCollegeName());
			excelMessage.setcMajorName(gradeMajorCollege.getMajor().getcMajorName());
			excelMessage.setcYearClass(gradeMajorCollege.getcYearClass());
			excelMessage.setcClass(gradeMajorCollege.getcClass());
			excelMessages.add(i, excelMessage);
		}
		String[] excelheader = { "学院#cCollegeName", "年级#cYearClass", "专业#cMajorName", "班级#cClass", "姓名#cName",
				"实验#cExperimentName", "成绩#cSum" };
		ExportExcelUtil.export(response, "成绩单", excelheader, excelMessages);
		// System.out.println(relatives.size());
	}

	/**
	 * 判断是否可以导出数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("checkExport")
	public void checkExport(HttpServletRequest request, HttpServletResponse response) {
		int gradeid = StringUtils.string2int(request.getParameter("cGradeId").trim());
		int experimentid = StringUtils.string2int(request.getParameter("experiment").trim());
		List<ReportRelative> relatives = reportRelativeService.getByExamIdandGradeId(experimentid, gradeid);
		ResultJson json = new ResultJson();
		if (null == relatives || relatives.size() == 0) {
			json.setSuccess(false);
		} else {
			json.setSuccess(true);
		}
		writeResultJson(response, json);
	}

	/**
	 * 根据前台传来的checkBoxId进行表格导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/exportExcelByGrade")
	public void exportExcelByGrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int gradeid = StringUtils.string2int(request.getParameter("cGradeId").trim());
		int experimentid = StringUtils.string2int(request.getParameter("experiment").trim());
		List<ReportRelative> relatives = reportRelativeService.getByExamIdandGradeId(experimentid, gradeid);
		if (null == relatives || relatives.size() == 0) {
			PrintWriter out = response.getWriter();
			out.write("<script>alert('还未有数据，请确认后选择。');</script>");
			out.flush();
			return;
		}
		List<ExcelMessage> excelMessages = new ArrayList<ExcelMessage>();
		ExcelMessage excelMessage;
		GradeMajorCollege gradeMajorCollege = gradeMajorCollegeService.getById(gradeid);
		// System.out.println(relatives.size());
		for (int i = 0; i < relatives.size(); i++) {
			excelMessage = new ExcelMessage();
			// excelMessage.setcReportNum(relatives.get(i).getcReportNum());
			excelMessage.setcExperimentName(relatives.get(i).getExperimental().getcExperimentName());
			excelMessage.setcName(relatives.get(i).getStudent().getcName());
			excelMessage.setcStudentNumber(relatives.get(i).getStudent().getcStudentNumber());
			excelMessage.setcSum(relatives.get(i).getScoreSheet().getcSum());
			excelMessage.setcCollegeName(gradeMajorCollege.getCollege().getcCollegeName());
			excelMessage.setcMajorName(gradeMajorCollege.getMajor().getcMajorName());
			excelMessage.setcYearClass(gradeMajorCollege.getcYearClass());
			excelMessage.setcClass(gradeMajorCollege.getcClass());
			excelMessages.add(i, excelMessage);
		}
		String[] excelheader = { "学院#cCollegeName", "年级#cYearClass", "专业#cMajorName", "班级#cClass", "姓名#cName",
				"实验#cExperimentName", "成绩#cSum" };
		ExportExcelUtil.export(response, "成绩单", excelheader, excelMessages);
		// System.out.println(relatives.size());
	}
}
