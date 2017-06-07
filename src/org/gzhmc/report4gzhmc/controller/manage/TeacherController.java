package org.gzhmc.report4gzhmc.controller.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherCollegeMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherGradeLinkMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherGradeMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherCollege;
import org.gzhmc.report4gzhmc.model.TeacherGrade;
import org.gzhmc.report4gzhmc.model.TeacherGradeLink;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author stShen
 */
@Controller
@RequestMapping("/manage")
public class TeacherController extends BaseController {

	@Autowired
	TeacherGradeLinkMapper teacherGradeLinkMapper;
	@Autowired
	TeacherCollegeMapper teacherCollegeMapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	UserMapper userMapper;

	@Autowired
	TeacherGradeMapper teacherGradeMapper;

	// VERIFY表示教师班级已经通过验证
	private int VERIFY = 1;

	// 显示所有教师信息
	@RequestMapping(value = { "/indexTeacher", "/indexTeacher.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<TeacherCollege> teacherColleges = teacherCollegeMapper.getAll();
		List<College> colleges = collegeMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("/manage/indexTeacher");
		modelAndView.addObject("teacherColleges", teacherColleges);
		modelAndView.addObject("colleges", colleges);
		return modelAndView;
	}

	// 显示所有教师关联班级信息
	@RequestMapping(value = { "/indexTeacherGrade", "/indexTeacherGrade.html" })
	public ModelAndView indexTeacherGrade(HttpServletRequest request, HttpServletResponse response) {
		List<TeacherGradeLink> teacherGradeLink = teacherGradeLinkMapper.getAllTeaGra();
		ModelAndView modelAndView = new ModelAndView("/manage/indexTeacherGrade");
		modelAndView.addObject("teacherGrades", teacherGradeLink);
		return modelAndView;
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addTeacher", "/addTeacher.html" })
	@Transactional
	public ModelAndView addCollege(HttpServletRequest request, HttpServletResponse response) {
		// 根据id查出对应的考核，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		TeacherCollege teacherCollege = null;
		if (StringUtils.isNotEmpty(id)) {
			teacherCollege = teacherCollegeMapper.getById(StringUtils.string2int(id));
		}
		List<College> college = collegeMapper.getAll();
		request.setAttribute("college", college);
		ModelAndView mav = new ModelAndView("manage/addTeacher");
		mav.addObject("teacherCollege", teacherCollege);
		return mav;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addTeacher.action" })
	@Transactional
	public void addCollegeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		ResultJson json = new ResultJson();
		String id = request.getParameter("id");
		String teachernum = request.getParameter("teachernum");
		String teachername = request.getParameter("teachername");
		String ccollege = request.getParameter("ccollege");
		Teacher teacher = new Teacher();
		teacher.setcCollegeId(StringUtils.string2int(ccollege));
		teacher.setcName(teachername);
		teacher.setcTeacherId(teachernum);
		int result;
		if (StringUtils.isNotEmpty(id)) {
			teacher.setcUserId(StringUtils.string2int(id));
			result = teacherMapper.updateSelective(teacher);
		} else {

			result = teacherMapper.add(teacher);
			User user = new User();
			user.setcCreateTime(new Date());
			user.setcId(teacher.getcUserId());
			user.setcPassword(MD5.getMD5("crazy123456"));
			user.setcRole(2);
			user.setcUserName(teachername);
			userMapper.add(user);
		}
		if (result == 1) {
			// 重定向转到管理学院的页面
			json.setSuccess(true);
			json.setMsg("操作成功!");

		} else {
			throw new WebException();
		}
	}

	//
	// 根据id删除教师信息
	@RequestMapping(value = { "/delTeacher.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delTeacherAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result, result2;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = teacherMapper.delete(StringUtils.string2int(idString[i]));
				result2 = userMapper.delete(StringUtils.string2int(idString[i]));
				if (result != 1 && result2 != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

	// 根据id删除教师班级关联信息
	@RequestMapping(value = { "/delTeacherGrade.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delTeacherGradeAction(HttpServletRequest request, HttpServletResponse response) {
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

	// 根据id验证信息
	@RequestMapping(value = { "/verify.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void verifyAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id验证教师资格
		String id = request.getParameter("id");
		ResultJson json = new ResultJson();

		int result;
		if (id != null && id != "") {

			TeacherGrade teacherGrade = teacherGradeMapper.getById(StringUtils.string2int(id));
			teacherGrade.setcStatus(VERIFY);
			result = teacherGradeMapper.updateByPrimaryKeySelective(teacherGrade);
			if (result != 1) {
				json.setSuccess(false);
			} else {
				json.setSuccess(true);
			}

		}
		writeResultJson(response, json);
	}
}
