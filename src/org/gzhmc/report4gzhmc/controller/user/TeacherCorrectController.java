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
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.SetpdfCertificate;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.mapper.ExperimentMapper;
import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.mapper.GradeExamMapper;
import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.mapper.ReportMapper;
import org.gzhmc.report4gzhmc.mapper.ReportRelativeMapper;
import org.gzhmc.report4gzhmc.mapper.ResitMapper;
import org.gzhmc.report4gzhmc.mapper.ScoreSheetMapper;
import org.gzhmc.report4gzhmc.mapper.StudentGradeMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherExperimentalMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherGradeLinkMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherGradeMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.mapper.TestMapper;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.ExcelMessage;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.Report;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.gzhmc.report4gzhmc.model.Resit;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.ScoreSheet;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherExperimental;
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
	ExperimentMapper experimentMapper;
	@Autowired
	ScoreSheetMapper scoreSheetMapper;
	@Autowired
	TestMapper testMapper;
	@Autowired
	TeacherGradeLinkMapper teacherGradeLinkMapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	TeacherGradeMapper teacherGradeMapper;
	@Autowired
	GradeExamMapper gradeExamMapper;

	// ISCHECK=1表示report已经批改过或进入批改时间，ISSUMIT表示进入报告提交时间
	private int ISCHECK = 1;
	private int ISSUMIT=0;


	/**
	 * 测试
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/test", "/test.html" })
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		List<Test> tests = testMapper.getAll();
		return new ModelAndView("teacher/test").addObject("teachername", teacher.getcName());
	}

	/**
	 * 测试
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/test2", "/test2.html" })
	public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) {
		List<Test> tests = testMapper.getAll();
		return new ModelAndView("teacher/test2").addObject("tests", tests);
	}
	
	/**
	 * 班级关联实验信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/gradeExam", "/gradeExam.html" })
	public ModelAndView gradeExam(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		//根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeMapper.getAllTeaGraByTeaIdAndStatu(StringUtils.string2int(userid));
		List<GradeExam> gradeExams=new ArrayList<GradeExam>();
		//System.out.println(teacherGrade.get(0).getcGradeId());
	    //根据班级Id找到对应关联的实验
		for(TeacherGrade t:teacherGrade){
			List<GradeExam> gradeExams2=gradeExamMapper.getByTeaGradeId(t.getcGradeId());
			gradeExams.addAll(gradeExams2);
		}
		//System.out.println(gradeExams.size());
		List<Test> tests = testMapper.getAll();
		List<GradeMajorCollege> gradeMajorColleges=new ArrayList<GradeMajorCollege>();
		for(TeacherGrade t:teacherGrade){
			GradeMajorCollege gradeMajorColleges2=gradeMajorCollegeMapper.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);			
		}		
		ModelAndView modelAndView=new ModelAndView("teacher/gradeExam");
		modelAndView.addObject("gradeExams", gradeExams);
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		return modelAndView;
	}

	// 返回json数据
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

	// 显示所有教师关联班级信息
	@RequestMapping(value = { "/teacherGrade", "/teacherGrade.html" })
	public ModelAndView indexTeacherGrade(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		List<TeacherGradeLink> teacherGradeLink = teacherGradeLinkMapper
				.getAllTeaGraByTeaId(StringUtils.string2int(userid));
		List<College> colleges = collegeMapper.getAll();
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
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getByCollegeId(id);
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
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/addTeacherGrade" })
	public void addTeacherGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getSession().getAttribute("userid").toString().trim();
		String teaGraId = request.getParameter("cGradeId");
		TeacherGrade teacherGrade = new TeacherGrade();
		teacherGrade.setcCreateTime(new Date());
		teacherGrade.setcGradeId(StringUtils.string2int(teaGraId));
		teacherGrade.setcStatus(0);
		teacherGrade.setcTeacherId(StringUtils.string2int(userid));
		int result = 0;
		result = teacherGradeMapper.add(teacherGrade);
		ResultJson json = new ResultJson();
		if (result == 0) {
			json.setSuccess(false);

		} else {
			json.setSuccess(true);
		}
		writeResultJson(response, json);
	}
	/**
	 * 添加班级实验关联
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/addGradeExam" })
	public void addGradeExam(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		String cGradeId = request.getParameter("cGradeId");
		String experiment=request.getParameter("experiment");
	    GradeExam gradeExam=new GradeExam();
	    gradeExam.setcCreateTime(new Date());
	    gradeExam.setcGradeId(StringUtils.string2int(cGradeId));
	    gradeExam.setcExperimentId(StringUtils.string2int(experiment));
		int result = 0;
		result = gradeExamMapper.add(gradeExam);
		ResultJson json = new ResultJson();
		if (result == 0) {
			json.setSuccess(false);

		} else {
			json.setSuccess(true);
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
				result = teacherGradeMapper.delete(StringUtils.string2int(idString[i]));
				if (result != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}
	
	//判断是否能够删除班级实验关联信息
	protected boolean qualifyDelete(int id) {
	    GradeExam gradeExam=gradeExamMapper.getById(id);
	    //根据班级实验关联信息查询是否存在报告数据,若存在返回false，即不能删除数据
		int result=gradeExamMapper.getReportNumByExperimentIdandGradeId(gradeExam.getcGradeId(),gradeExam.getcExperimentId());
		if(result==0)
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
					result = gradeExamMapper.deleteByPrimaryKey(id);
					if (result != 1) {
						json.setSuccess(false);
						json.setMsg("删除失败!");
						break;
					}
				}else{
					json.setSuccess(false);
					json.setMsg("该数据与其他数据关联，删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

	/**
	 * 根据id更新班级实验关联信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/updateGradeExam" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void updateGradeExam(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		int id = StringUtils.string2int(request.getParameter("id"));
		GradeExam gradeExam = gradeExamMapper.getById(id);
		ResultJson json = new ResultJson();

		if (gradeExam.getcStatus() == ISCHECK) {
			gradeExam.setcStatus(ISSUMIT);
		} else {
			gradeExam.setcStatus(ISCHECK);
		}
		int result = gradeExamMapper.updateSelective(gradeExam);
		if (result != 1) {
			json.setSuccess(false);
		}
		writeResultJson(response, json);
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
		List<Experiment> experiments = experimentMapper.getByClassify(id);
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
	public ModelAndView getReportList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int eid = Integer.parseInt(request.getParameter("experiment"));
		int gradeId=Integer.parseInt(request.getParameter("cGradeId"));
		List<ReportRelative> relatives=reportRelativeMapper.getExitScoreByExperimentIdandGradeId(eid, gradeId);
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());				
		//根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeMapper.getAllTeaGraByTeaIdAndStatu(userid);		
		List<Test> tests = testMapper.getAll();
		List<GradeMajorCollege> gradeMajorColleges=new ArrayList<GradeMajorCollege>();
		for(TeacherGrade t:teacherGrade){
			GradeMajorCollege gradeMajorColleges2=gradeMajorCollegeMapper.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);			
		}
        ModelAndView modelAndView = new ModelAndView("teacher/reportManage");
		
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
	 * 新版实验报告显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/reportManage")
	public ModelAndView reportManage(HttpServletRequest request, HttpServletResponse response) {		
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());				
		//根据教师id找到教师所关联的班级
		List<TeacherGrade> teacherGrade = teacherGradeMapper.getAllTeaGraByTeaIdAndStatu(userid);		
		List<Test> tests = testMapper.getAll();
		List<GradeMajorCollege> gradeMajorColleges=new ArrayList<GradeMajorCollege>();
		for(TeacherGrade t:teacherGrade){
			GradeMajorCollege gradeMajorColleges2=gradeMajorCollegeMapper.getById(t.getcGradeId());
			gradeMajorColleges.add(gradeMajorColleges2);			
		}
		List<ReportRelative> reportRelatives=new ArrayList<ReportRelative>();
		for(TeacherGrade t:teacherGrade){
			List<ReportRelative> relatives=reportRelativeMapper.getByGradeId(t.getcGradeId());
			reportRelatives.addAll(relatives);
		}
		ModelAndView modelAndView = new ModelAndView("teacher/reportManage");
		
//		int count = teacherExperimentalMapper.getCountByTeacherId(userid);
//		if (count > 0) {
//			List<TeacherExperimental> teacherExperimentals = teacherExperimentalMapper
//					.getByTeacherId(userid);
//			int[] haves = new int[teacherExperimentals.size()];
//			for (int i = 0; i < teacherExperimentals.size(); i++) {
//				haves[i] = teacherExperimentals.get(i).getcExperimentalTestId();
//			}
//			List<ReportRelative> reportRelatives = reportRelativeMapper.getExitScoreByExperimentId(haves);
//			modelAndView.addObject("reportRelatives", reportRelatives);
//		}
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		modelAndView.addObject("reportRelatives", reportRelatives);
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
		user.setcPassword(MD5.getMD5("crazy" + password));
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
	@RequestMapping(value = { "reportCorrect", "reportCorrect.html" })
	public ModelAndView reportCorrect(HttpServletRequest request, HttpServletResponse response) {
	
//		int reportId=StringUtils.string2int(request.getParameter("id"));
//		Report report=reportMapper.getById(reportId);	
//		ModelAndView modelAndView = new ModelAndView("student/reportEdit");
//		String userid = request.getSession().getAttribute("userid").toString().trim();
//		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
//		List<GradeExam> gradeExams=gradeExamMapper.getByGradeId(student.getcGradeId());
//	    modelAndView.addObject("tests",gradeExams);
//		modelAndView.addObject("report", report);
		
		
		String rid = request.getParameter("rid");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));
		Report report = reportMapper.getById(StringUtils.string2int(rid));
		if (report.getcStatu() == 1) {
			// HttpSession session = request.getSession();
			// out.write("<script>alert('实验报告已经批改，请重新选择一份。');</script>");
			ModelAndView modelAndView = new ModelAndView("teacher/cmnerror");
			modelAndView.addObject("message", "实验报告已经批改，请重新选择一份。");
			modelAndView.addObject("showpath", "teacher/teacherIndex");
			return modelAndView;
		} else {
			String path = report.getcPath();
			ModelAndView modelAndView = new ModelAndView("teacher/reportCorrect");
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
	@RequestMapping(value = { "reportCorrect.action" })
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
		// System.out.println("开始生成证书");
		// 判断是否及格，及格则生成证书
		if (report.getcStatu() == 1) {
			out.write("<script>alert('已由另外一名老师批改完成，请返回另选一份。');</script>");
		} else if (StringUtils.string2double(score) - 60 < 0) {

			int result = scoreSheetMapper.add(scoreSheet);
			if (result != 0) {
				report.setcTeacherId(teacher.getcUserId());
				report.setcStatu(ISCHECK);
				report.setcScoreId(scoreSheet.getcId());
				result = reportMapper.update(report);
				if (result != 0) {
					// System.out.println("不及格批改成功");
					Resit resit = new Resit();
					resit.setcExperiment(report.getcExperimentTextId());
					resit.setcStudentId(report.getcStudentId());
					result = resitMapper.insert(resit);
					if (result != 0) {
						out.write("<script>alert('批改成功！成绩为不及格，已加入补考名单。');</script>");
					} else {
						out.write("<script>alert('批改成功！加入补考名单失败，请联系管理员。');</script>");
					}
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
				report.setcStatu(ISCHECK);
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
		// List<ReportRelative> reportRelatives =
		// reportRelativeMapper.getExperimentIdandGradeId();
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
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
	public ModelAndView queryPdfAction(HttpServletRequest request, HttpServletResponse response) {
		// 获取课程表格数据
		int gradeid = StringUtils.string2int(request.getParameter("gradeidq"));
		// 获取实验表格数据
		int experimentid = StringUtils.string2int(request.getParameter("experimentidq"));

		String userid = request.getSession().getAttribute("userid").toString().trim();
		Teacher teacher = teacherMapper.getById(StringUtils.string2int(userid));

		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
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
				out.write("<script>alert('还未有数据，请确认后选择。');window.location.href='../teacher/downloadpdf';</script>");
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
		// System.out.println(relatives.size());
		for (int i = 0; i < relatives.size(); i++) {
			excelMessage = new ExcelMessage();
			excelMessage.setcReportNum(relatives.get(i).getcReportNum());
			excelMessage.setcExperimentName(relatives.get(i).getExperimental().getcExperimentName());
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
		// System.out.println(relatives.size());
	}
}
