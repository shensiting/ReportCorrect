package org.gzhmc.report4gzhmc.controller.user;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.CollegeService;
import org.gzhmc.report4gzhmc.service.ExperimentService;
import org.gzhmc.report4gzhmc.service.ExperimentalTestService;
import org.gzhmc.report4gzhmc.service.GradeExamService;
import org.gzhmc.report4gzhmc.service.GradeMajorCollegeService;
import org.gzhmc.report4gzhmc.service.ReportService;
import org.gzhmc.report4gzhmc.service.ReportRelativeService;
import org.gzhmc.report4gzhmc.service.ScoreSheetService;
import org.gzhmc.report4gzhmc.service.StudentGradeService;
import org.gzhmc.report4gzhmc.service.StudentService;
import org.gzhmc.report4gzhmc.service.TeacherCollegeService;
import org.gzhmc.report4gzhmc.service.TeacherGradeLinkService;
import org.gzhmc.report4gzhmc.service.TeacherGradeService;
import org.gzhmc.report4gzhmc.service.TeacherService;
import org.gzhmc.report4gzhmc.service.TestService;
import org.gzhmc.report4gzhmc.service.TopicThemeService;
import org.gzhmc.report4gzhmc.service.UserService;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.ScoreSheet;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherCollege;
import org.gzhmc.report4gzhmc.model.TeacherGrade;
import org.gzhmc.report4gzhmc.model.TeacherGradeLink;
import org.gzhmc.report4gzhmc.model.Test;
import org.gzhmc.report4gzhmc.model.User;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
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
	UserService userService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	ReportService reportService;
	@Autowired
	ReportRelativeService reportRelativeService;
	@Autowired
	StudentGradeService studentGradeService;
	@Autowired
	GradeMajorCollegeService gradeMajorCollegeService;
	//@Autowired
	//TeacherExperimentalService teacherExperimentalService;
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
	CollegeService collegeService;
	@Autowired
	TeacherGradeService teacherGradeService;
	@Autowired
	GradeExamService gradeExamService;
	@Autowired
	TopicThemeService topicThemeService;
	@Autowired
	StudentService studentService;
	@Autowired
	TeacherCollegeService teacherCollegeService;

	// ISCHECK=1表示report已经批改过或进入批改时间，ISSUMIT表示进入报告提交时间,CHECKED表示report已经被批改;NOPASS表示不及格
	private int ISCHECK = 1;
	private int ISSUMIT = 0;
	private int CHECKED = 1;
	private int NOPASS = 2;
	// ONLINEEDIT=0表示该实验使用在线编辑提交，WORDSUBMIT=1表示实验使用word文档上传
	private int ONLINEEDIT = 0;
	private int WORDSUBMIT = 1;

	/**
	 * 首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/index", "/index.html" })
	public ModelAndView test(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherService.getById(StringUtils.string2int(userid));
		return new ModelAndView("teacher/index").addObject("teachername", teacher.getcName());
	}

	/**
	 * 测试
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/teaShow", "/teaShow.html" })
	public ModelAndView teaShow(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		TeacherCollege teacher = teacherCollegeService.getById(StringUtils.string2int(userid));
		return new ModelAndView("teacher/teaShow").addObject("teacher", teacher);
	}

	/**
	 * 根据id删除教师班级关联信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delQuestion" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delQuestion(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		String id = request.getParameter("id");
		ResultJson json = new ResultJson();
		int result;
		result = topicThemeService.deleteByPrimaryKey(StringUtils.string2int(id));
		if (result != 1) {
			json.setSuccess(false);
			json.setMsg("删除失败!");
		}
		writeResultJson(response, json);
	}

	/**
	 * 班级关联实验信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/gradeExam", "/gradeExam.html" })
	public ModelAndView gradeExam(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		// 根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeService
				.getAllTeaGraByTeaIdAndStatu(StringUtils.string2int(userid));
		List<GradeExam> gradeExams = new ArrayList<GradeExam>();
		// System.out.println(teacherGrade.get(0).getcGradeId());
		// 根据班级Id找到对应关联的实验
		for (TeacherGrade t : teacherGrade) {
			List<GradeExam> gradeExams2 = gradeExamService.getByTeaGradeId(t.getcGradeId());
			gradeExams.addAll(gradeExams2);
		}
		// System.out.println(gradeExams.size());
		List<Test> tests = testService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = new ArrayList<GradeMajorCollege>();
		for (TeacherGrade t : teacherGrade) {
			GradeMajorCollege gradeMajorColleges2 = gradeMajorCollegeService.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/gradeExam");
		modelAndView.addObject("gradeExams", gradeExams);
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		return modelAndView;
	}

	/**
	 * 显示所有教师关联班级信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/teacherGrade", "/teacherGrade.html" })
	public ModelAndView indexTeacherGrade(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		List<TeacherGradeLink> teacherGradeLink = teacherGradeLinkService
				.getAllTeaGraByTeaId(StringUtils.string2int(userid));
		List<College> colleges = collegeService.getAll();
		ModelAndView modelAndView = new ModelAndView("/teacher/teacherGrade");
		modelAndView.addObject("teacherGrades", teacherGradeLink);
		modelAndView.addObject("colleges", colleges);
		return modelAndView;
	}

	/**
	 * 获得学院id获取对应班级
	 * 
	 * @param request
	 * @param response
	 * @param one
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/getTeaGraRelevance" })
	public void getTeaGraRelevance(HttpServletRequest request, HttpServletResponse response, Integer one)
			throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeService.getByCollegeId(id);
		// 将数据转换成json在原页面输出
		JSONArray json = new JSONArray();
		for (GradeMajorCollege a : gradeMajorColleges) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cYearClass", a.getcYearClass());
			jo.put("cMajorName", a.getMajor().getcMajorName());
			jo.put("cClass", a.getcClass());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 添加教师班级关联信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/addTeacherGrade" })
	public void addTeacherGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		int teaGraId = StringUtils.string2int(request.getParameter("cGradeId"));
		int i = teacherGradeService.getByTeaIdAndGradeId(userid, teaGraId);
		ResultJson json = new ResultJson();
		int j = teacherGradeService.getCountByGradeId(teaGraId);
		if (j == 0) {
			// 判断表中是否已经关联，若存在返回失败
			if (i == 0) {
				TeacherGrade teacherGrade = new TeacherGrade();
				teacherGrade.setcCreateTime(new Date());
				teacherGrade.setcGradeId(teaGraId);
				teacherGrade.setcStatus(0);
				teacherGrade.setcTeacherId(userid);
				int result = 0;
				result = teacherGradeService.add(teacherGrade);
				if (result == 0) {
					json.setSuccess(false);
					json.setMsg("关联失败，请稍后再试！");
				} else {
					json.setSuccess(true);
					json.setMsg("关联成功！");
				}
			} else {
				json.setSuccess(false);
				json.setMsg("关联已存在，请确认后再关联！");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("其他老师已设置关联，请确认后再关联！");
		}
		writeResultJson(response, json);
	}

	/**
	 * 添加班级实验关联
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/addGradeExam" })
	public void addGradeExam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cGradeId = StringUtils.string2int(request.getParameter("cGradeId"));
		int experiment = StringUtils.string2int(request.getParameter("experiment"));
		ResultJson json = new ResultJson();
		// 判断是否有其他教师关联此班级
		int i = gradeExamService.getByExamIdAndGradeId(experiment, cGradeId);
		if (i == 0) {
			GradeExam gradeExam = new GradeExam();
			gradeExam.setcCreateTime(new Date());
			gradeExam.setcGradeId(cGradeId);
			gradeExam.setcExperimentId(experiment);
			int result = 0;
			result = gradeExamService.add(gradeExam);
			if (result == 0) {
				json.setSuccess(false);
				json.setMsg("关联失败，请稍后再试！");

			} else {
				json.setSuccess(true);
				json.setMsg("关联成功！");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("不能重复关联，请确认后操作！");
		}

		writeResultJson(response, json);
	}

	/**
	 * 根据id删除教师班级关联信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delTeacherGrade" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delTeacherGrade(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = teacherGradeService.delete(StringUtils.string2int(idString[i]));
				if (result != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

	// 判断是否能够删除班级实验关联信息
	protected boolean qualifyDelete(int id) {
		GradeExam gradeExam = gradeExamService.getById(id);
		// 根据班级实验关联信息查询是否存在报告数据,若存在返回false，即不能删除数据
		int result = gradeExamService.getReportNumByExperimentIdandGradeId(gradeExam.getcGradeId(),
				gradeExam.getcExperimentId());
		if (result == 0)
			return true;
		else
			return false;
	}

	/**
	 * 根据id删除班级实验关联信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delGradeExam" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delGradeExam(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		String ids = request.getParameter("ids");
		int id;
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				id = StringUtils.string2int(idString[i]);
				if (qualifyDelete(id)) {
					result = gradeExamService.deleteByPrimaryKey(id);
					if (result != 1) {
						json.setSuccess(false);
						json.setMsg("删除失败!");
						break;
					}
				} else {
					json.setSuccess(false);
					json.setMsg("该数据与其他数据关联，删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

	/**
	 * 根据id更新班级实验关联信息批改状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/updateGradeExam" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void updateGradeExam(HttpServletRequest request, HttpServletResponse response) {
		int id = StringUtils.string2int(request.getParameter("id"));
		GradeExam gradeExam = gradeExamService.getById(id);
		ResultJson json = new ResultJson();
		if (gradeExam.getcStatus() == ISCHECK) {
			gradeExam.setcStatus(ISSUMIT);
		} else {
			gradeExam.setcStatus(ISCHECK);
		}
		int result = gradeExamService.updateSelective(gradeExam);
		if (result != 1) {
			json.setSuccess(false);
		}
		writeResultJson(response, json);
	}

	/**
	 * 根据id更新班级实验关联信息批改状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/updateGESubnitForm" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void updateGESubnitForm(HttpServletRequest request, HttpServletResponse response) {
		int id = StringUtils.string2int(request.getParameter("id"));
		GradeExam gradeExam = gradeExamService.getById(id);
		ResultJson json = new ResultJson();

		if (gradeExam.getcSubmitForm() == ONLINEEDIT) {
			gradeExam.setcSubmitForm(WORDSUBMIT);
		} else {
			gradeExam.setcSubmitForm(ONLINEEDIT);
		}
		int result = gradeExamService.updateSubmitForm(gradeExam);
		if (result != 1) {
			json.setSuccess(false);
		}
		writeResultJson(response, json);
	}

	/**
	 * 跳转修改密码页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/tpasswordChange")
	public ModelAndView iteacherPasswordChange(HttpServletRequest request) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherService.getById(StringUtils.string2int(userid));
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
		User user = userService.getById(StringUtils.string2int(userid));
		user.setcPassword(MD5.getMD5("crazy" + password));
		int result = userService.updateSelective(user);
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

	/**
	 * 删除在线编辑学生实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delReport.action")
	public void delReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int rid = Integer.parseInt(request.getParameter("id"));
		int result = reportService.delete(rid);
		ResultJson json = new ResultJson();
		if (result == 0) {
			json.setSuccess(false);
			json.setMsg("对不起，删除失败，请稍后重试");
		} else {
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeResultJson(response, json);
	}

	/**
	 * 删除word学生实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/delWord.action")
	public void delWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int rid = Integer.parseInt(request.getParameter("id"));
		int result = reportService.delete(rid);
		ResultJson json = new ResultJson();
		if (result == 0) {
			json.setSuccess(false);
			json.setMsg("对不起，删除失败，请稍后重试");
		} else {
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeResultJson(response, json);
	}

	/*
	 * 跳转教师批改页面
	 */
	@RequestMapping(value = { "reportCorrect", "reportCorrect.html" })
	public ModelAndView reportCorrect(HttpServletRequest request) {
		String rid = request.getParameter("rid");
		Report report = reportService.getById(StringUtils.string2int(rid));
		ModelAndView modelAndView = new ModelAndView("teacher/reportCorrect");
		modelAndView.addObject("report", report.getcContent());
		modelAndView.addObject("rid", rid);
		return modelAndView;
	}

	/*
	 * 跳转教师批改页面
	 */
	@RequestMapping(value = { "wordCorrect", "wordCorrect.html" })
	public ModelAndView wordCorrect(HttpServletRequest request) {
		String rid = request.getParameter("rid");
		ModelAndView modelAndView = new ModelAndView("teacher/wordCorrect");
		modelAndView.addObject("rid", rid);
		return modelAndView;
	}

	/**
	 * 教师report批改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "reportCorrect.action" })
	public void reportCorrectAction(HttpServletRequest request, HttpServletResponse response) {
		String rid = request.getParameter("rid");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		String process = request.getParameter("process");
		Double score = Double.parseDouble(request.getParameter("score"));
		String htmlForm = request.getParameter("htmlForm");
		Report report = reportService.getById(StringUtils.string2int(rid));
		ResultJson json = new ResultJson();
		int result = 0;
		// 存入成绩数据
		ScoreSheet scoreSheet;
		if (report.getcScoreId() != 0) {
			scoreSheet = scoreSheetService.getById(report.getcScoreId());
			scoreSheet.setcConclution(process);
			scoreSheet.setcCreateTime(new Date());
			scoreSheet.setcSum(score);
			result = scoreSheetService.update(scoreSheet);
		} else {
			scoreSheet = new ScoreSheet();
			scoreSheet.setcConclution(process);
			scoreSheet.setcCreateTime(new Date());
			scoreSheet.setcSum(score);
			result = scoreSheetService.add(scoreSheet);
		}

		if (result != 0) {
			report.setcScoreId(scoreSheet.getcId());
			report.setcPdfPath(htmlForm);
			report.setcTeacherId(StringUtils.string2int(userid));
			if (score < 60) {
				report.setcStatu(NOPASS);
			} else {
				report.setcStatu(CHECKED);
			}
			result = reportService.updateScore(report);
			if (result == 0) {
				scoreSheetService.delete(scoreSheet.getcId());
				json.setSuccess(false);
				json.setMsg("服务器出错，请稍后再试！");
			} else {
				json.setSuccess(true);
				json.setMsg("批改成功！");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("服务器出错，请稍后再试！");
		}
		writeResultJson(response, json);
	}

	/**
	 * 二维码扫描后跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request) {
		// String rid = request.getParameter("rid");
		// ReportRelative reportRelative =
		// reportRelativeService.getById(StringUtils.string2int(rid));
		// String theory =
		// getScore(reportRelative.getScoreSheet().getcTherory());
		// String labresult =
		// getScore(reportRelative.getScoreSheet().getcLabresult());
		// String conclusion =
		// getGrade(reportRelative.getScoreSheet().getcConclution());
		// String conclusion2 =
		// getScore(reportRelative.getScoreSheet().getcConclution());
		// String operation = getScore(
		// (reportRelative.getScoreSheet().getcReagen() +
		// reportRelative.getScoreSheet().getcInserument()
		// + reportRelative.getScoreSheet().getcExperiment()) / 3);
		// String reagen =
		// getScore(reportRelative.getScoreSheet().getcReagen());
		// String instrument =
		// getScore(reportRelative.getScoreSheet().getcInserument());
		// String experiment =
		// getScore(reportRelative.getScoreSheet().getcExperiment());
		ModelAndView modelAndView = new ModelAndView("teacher/show");
		// modelAndView.addObject("theory", theory);
		// modelAndView.addObject("labresult", labresult);
		// modelAndView.addObject("conclusion", conclusion);
		// modelAndView.addObject("conclusion2", conclusion2);
		// modelAndView.addObject("operation", operation);
		// modelAndView.addObject("reportRelative", reportRelative);
		// modelAndView.addObject("reagen", reagen);
		// modelAndView.addObject("instrument", instrument);
		// modelAndView.addObject("experiment", experiment);
		return modelAndView;
	}

}
