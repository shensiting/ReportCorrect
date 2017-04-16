package org.gzhmc.report4gzhmc.controller.manage;


import java.util.List;

import org.gzhmc.common.exception.WebException;

import org.gzhmc.common.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gzhmc.common.base.BaseController;

import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.mapper.GradeMapper;
import org.gzhmc.report4gzhmc.mapper.MajorMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.Grade;
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
	GradeMajorCollegeMapper gradeMajorCollegeMapper;
	@Autowired
	MajorMapper majorMapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	GradeMapper gradeMapper;

	// 显示所有班级信息
	@RequestMapping(value = { "indexGrade", "indexGrade.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
		return new ModelAndView("manage/indexGrade").addObject("gmclist", gradeMajorColleges);
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addGrade.html", "/addGrade" })
	@Transactional
	public ModelAndView addGrade(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		GradeMajorCollege gradeMajorCollege = null;
		if (StringUtils.isNotEmpty(id)) {
			gradeMajorCollege = gradeMajorCollegeMapper.getById(StringUtils.string2int(id));
		}
		List<Major> majorList = majorMapper.getAll();
		List<College> colleges = collegeMapper.getAll();
		request.setAttribute("majorList", majorList);
		request.setAttribute("colleges", colleges);
		ModelAndView modelAndView = new ModelAndView("manage/addGrade");
		modelAndView.addObject("gradeMajorCollege", gradeMajorCollege);
		return modelAndView;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addGrade.action" })
	@Transactional
	public ModelAndView addCollegeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
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

		int result;
		if (StringUtils.isNotEmpty(id)) {
			grade.setcId(StringUtils.string2int(id));
			result = gradeMapper.updateSelective(grade);
		} else {
			result = gradeMapper.add(grade);
		}
		if (result == 1) {
			// 重定向转到管理班级的页面
			return new ModelAndView("redirect:/manage/indexGrade.html");
		} else {
			throw new WebException();
		}
	}

	// 根据id删除信息
	@RequestMapping(value = { "/delGrade.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delMajorAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的班级
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = gradeMapper.delete(StringUtils.string2int(idString[i]));
				if (result != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}

		writeResultJson(response, json);
	}

}
