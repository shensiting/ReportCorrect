package org.gzhmc.report4gzhmc.controller.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.mapper.StudentGradeMapper;
import org.gzhmc.report4gzhmc.mapper.StudentMapper;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 学生信息管理
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class StudentCotroller extends BaseController {

	@Autowired
	StudentGradeMapper studentGradeMapper;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	GradeMajorCollegeMapper gradeMajorCollegeMapper;
	@Autowired
	UserMapper userMapper;

	// 显示所有学生信息
	@RequestMapping(value = { "/indexStudent", "/indexStudent.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<StudentGrade> studentGrades = studentGradeMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("manage/indexStudent");
		modelAndView.addObject("studentGrades", studentGrades);
		return modelAndView;
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addStudent.html", "/addStudent" })
	@Transactional
	public ModelAndView addGrade(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		StudentGrade studentGrade = null;
		if (StringUtils.isNotEmpty(id)) {
			studentGrade = studentGradeMapper.getById(StringUtils.string2int(id));
		}
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
		request.setAttribute("gradeMajorColleges", gradeMajorColleges);
		ModelAndView modelAndView = new ModelAndView("manage/addStudent");
		modelAndView.addObject("studentGrade", studentGrade);
		return modelAndView;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addStudent.action" })
	@Transactional
	public ModelAndView addCollegeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String Studentnum = request.getParameter("Studentnum");
		String Studentname = request.getParameter("Studentname");
		String IDnum = request.getParameter("IDnum");
		String cYearClass = request.getParameter("cYearClass").trim();
		Student student = new Student();
		student.setcGradeId(StringUtils.string2int(cYearClass));
		student.setcIDNumber(MD5.convertMD5(IDnum));
		student.setcName(Studentname);
		student.setcStudentNumber(Studentnum);
		int result;
		if (StringUtils.isNotEmpty(id)) {
			student.setcUserId(StringUtils.string2int(id));
			result = studentMapper.updateSelective(student);
		} else {
			result = studentMapper.add(student);
			User user=new User();
			user.setcCreateTime(new Date());
			user.setcId(student.getcUserId());
			user.setcPassword(MD5.getMD5("crazy123456"));
			user.setcRole(2);
			user.setcUserName(Studentname);
			userMapper.add(user);			
		}
		if (result == 1) {
			// 重定向转到管理学生的页面
			return new ModelAndView("redirect:/manage/indexStudent.html");
		} else {
			throw new WebException();
		}
	}

	// 根据id删除信息
	@RequestMapping(value = { "/delStudent.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delMajorAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学生
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = studentMapper.delete(StringUtils.string2int(idString[i]));
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
