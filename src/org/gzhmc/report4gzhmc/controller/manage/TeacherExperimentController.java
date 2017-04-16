package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherExperimentalMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherlinkExperimentMapper;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherExperimental;
import org.gzhmc.report4gzhmc.model.TeacherlinkExperiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 考核信息管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class TeacherExperimentController extends BaseController {

	@Autowired
	ExperimentalTestMapper experimentalTestMapper;
	@Autowired
	TeacherlinkExperimentMapper teacherlinkExperimentMapper;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	TeacherExperimentalMapper teacherExperimentalMapper;

	// 显示所有信息
	@RequestMapping("/indexTeacherExperiment")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<TeacherlinkExperiment> teacherlinkExperiments = teacherlinkExperimentMapper.getAll();
		return new ModelAndView("manage/indexTeacherExperiment").addObject("teacherlinkExperiments",
				teacherlinkExperiments);
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addTeacherExperiment.html", "/addTeacherExperiment" })
	@Transactional
	public ModelAndView addTeacherExperiment(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		TeacherlinkExperiment teacherlinkExperiment = null;
		if (StringUtils.isNotEmpty(id)) {
			teacherlinkExperiment = teacherlinkExperimentMapper.getById(StringUtils.string2int(id));
		}
		List<Teacher> teachers = teacherMapper.getAll();
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		request.setAttribute("teachers", teachers);
		request.setAttribute("experimentalTests", experimentalTests);
		ModelAndView modelAndView = new ModelAndView("manage/addTeacherExperiment");
		modelAndView.addObject("teacherlinkExperiment", teacherlinkExperiment);
		return modelAndView;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addTeacherExperiment.action" })
	@Transactional
	public ModelAndView addTeacherExperimentAction(HttpServletRequest request, HttpServletResponse response)
			throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String cteacher = request.getParameter("cteacher");
		String cexperiment = request.getParameter("cexperiment");
		TeacherExperimental teacherExperimental = new TeacherExperimental();
		teacherExperimental.setcExperimentalTestId(StringUtils.string2int(cexperiment));
		teacherExperimental.setcTeacherId(StringUtils.string2int(cteacher));
		int result;
		if (StringUtils.isNotEmpty(id)) {
			teacherExperimental.setcId(StringUtils.string2int(id));
			result = teacherExperimentalMapper.updateSelective(teacherExperimental);
		} else {
			result = teacherExperimentalMapper.add(teacherExperimental);
		}
		if (result == 1) {
			// 重定向转到管理学院的页面
			return new ModelAndView("redirect:/manage/indexTeacherExperiment.html");
		} else {
			throw new WebException();
		}
	}

	// 根据id删除信息
	@RequestMapping(value = { "/delTeacherExperiment.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delTeacherExperimentAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = teacherExperimentalMapper.delete(StringUtils.string2int(idString[i]));
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
