package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import org.gzhmc.common.exception.WebException;

import org.gzhmc.common.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gzhmc.common.base.BaseController;

import org.gzhmc.report4gzhmc.service.CollegeService;
import org.gzhmc.report4gzhmc.service.GradeExamService;
import org.gzhmc.report4gzhmc.service.GradeMajorCollegeService;
import org.gzhmc.report4gzhmc.service.GradeService;
import org.gzhmc.report4gzhmc.service.MajorService;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.Grade;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.Major;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 班级管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class GradeController extends BaseController {

	@Autowired
	GradeMajorCollegeService gradeMajorCollegeService;
	@Autowired
	GradeExamService gradeExamService;
	@Autowired
	MajorService majorService;
	@Autowired
	CollegeService collegeService;
	@Autowired
	GradeService gradeService;

	/**
	 * 显示所有班级信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "indexGrade", "indexGrade.html" })
	public ModelAndView index(HttpServletRequest request) {
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeService.getAll();
		return new ModelAndView("manage/indexGrade").addObject("gmclist", gradeMajorColleges);
	}

	/**
	 * 显示所有实验班级关联信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "indexGradeExam", "indexGradeExam.html" })
	public ModelAndView indexGradeExam(HttpServletRequest request) {
		List<GradeExam> gradeExams = gradeExamService.getAll();
		return new ModelAndView("manage/indexGradeExam").addObject("gradeExams", gradeExams);
	}

	/**
	 * 跳转到增加或修改信息的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/addGrade.html", "/addGrade" })
	@Transactional
	public ModelAndView addGrade(HttpServletRequest request) {
		String id = request.getParameter("id");
		GradeMajorCollege gradeMajorCollege = null;
		if (StringUtils.isNotEmpty(id)) {
			gradeMajorCollege = gradeMajorCollegeService.getById(StringUtils.string2int(id));
		}
		List<Major> majorList = majorService.getAll();
		List<College> colleges = collegeService.getAll();
		request.setAttribute("majorList", majorList);
		request.setAttribute("colleges", colleges);
		ModelAndView modelAndView = new ModelAndView("manage/addGrade");
		modelAndView.addObject("gradeMajorCollege", gradeMajorCollege);
		return modelAndView;
	}

	/**
	 * 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	 * 
	 * @param request
	 * @param response
	 * @throws WebException
	 */
	@RequestMapping(value = { "/addGrade.action" })
	@Transactional
	public void addGradeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String cYearClass = request.getParameter("cYearClass");
		String cClass = request.getParameter("cClass");
		String cMajor = request.getParameter("cMajor");
		String cCollege = request.getParameter("cCollege");

		Grade grade = new Grade();
		grade.setcClass(cClass);
		grade.setcCollegeId(StringUtils.string2int(cCollege));
		grade.setcMajorId(StringUtils.string2int(cMajor));
		grade.setcYearClass(cYearClass);
		ResultJson json = new ResultJson();
		int result;
		if (StringUtils.isNotEmpty(id)) {
			grade.setcId(StringUtils.string2int(id));
			result = gradeService.updateSelective(grade);
		} else {
			result = gradeService.add(grade);
		}
		if (result == 1) {
			// 重定向转到管理班级的页面
			json.setSuccess(true);
			json.setMsg("操作成功!请返回原来页面并刷新。");
		} else {
			throw new WebException();
		}
		writeResultJson(response, json);
	}

	/**
	 * 根据id删除班级信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delGrade.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delGradeAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的班级
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				if (qualify(StringUtils.string2int(idString[i]))) {
					result = gradeService.delete(StringUtils.string2int(idString[i]));
					if (result != 1) {
						json.setSuccess(false);
						json.setMsg("删除失败!");
						break;
					}
				} else {
					json.setSuccess(false);
					json.setMsg("删除的班级关联其他数据，不允许删除!");
					break;
				}
			}
		}

		writeResultJson(response, json);
	}

	/**
	 * 判断本表关联其他表的数据是否存在，若存在则不允许删除
	 * 
	 * @param cGradeId
	 * @return
	 */
	private boolean qualify(int cGradeId) {
		if (gradeService.getByStuGradeId(cGradeId) == 0 && gradeService.getByGraExamGradeId(cGradeId) == 0
				&& gradeService.getByteaGradeGradeId(cGradeId) == 0)
			return true;
		else
			return false;
	}

	/**
	 * 判断是否能够删除班级实验关联信息
	 * 
	 * @param id
	 * @return
	 */
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
	 * 根据id删除实验班级关联信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delExmGrade.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delExmGreadeAction(HttpServletRequest request, HttpServletResponse response) {
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
}
