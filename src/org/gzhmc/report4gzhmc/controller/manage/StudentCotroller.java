package org.gzhmc.report4gzhmc.controller.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.ReadStudentExcel;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.GradeMajorCollegeService;
import org.gzhmc.report4gzhmc.service.StudentGradeService;
import org.gzhmc.report4gzhmc.service.StudentService;
import org.gzhmc.report4gzhmc.service.UserService;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.StudentGrade;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 学生信息管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class StudentCotroller extends BaseController {

	@Autowired
	StudentGradeService studentGradeService;
	@Autowired
	StudentService studentService;
	@Autowired
	GradeMajorCollegeService gradeMajorCollegeService;
	@Autowired
	UserService userService;
	// 判断学生
	private int STUDENT_ROLE = 3;
	// 判断管理员权限
	private int MANAGE_ROLE = 1;

	/**
	 *  显示所有学生信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/indexStudent", "/indexStudent.html" })
	public ModelAndView index(HttpServletRequest request) {
		List<StudentGrade> studentGrades = studentGradeService.getAll();
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeService.getAll();
		ModelAndView modelAndView = new ModelAndView("manage/indexStudent");
		modelAndView.addObject("studentGrades", studentGrades);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		return modelAndView;
	}

	/**
	 * 跳转批量导入学生页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/batchImportStu", "/batchImportStu.html" })
	public ModelAndView batchImportStu(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("manage/batchImportStu");
		return modelAndView;
	}

	/**
	 * 导入学生数据
	 * 
	 * @param request
	 * @param response
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = { "/importExcel" })
	public ModelAndView importExcel(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile) {
		ReadStudentExcel readStudentExcel = new ReadStudentExcel();
		List<Student> students;
		String name = uploadFile.getOriginalFilename();
		students = readStudentExcel.getExcelInfo(name, uploadFile);
		String message = "";
		int result = 0, i = 0;
		User user;
		for (Student student : students) {
			if (studentService.getByStudentNumber(student.getcStudentNumber()) == 0) {
				result = studentService.add(student);
				if (result != 0) {
					user = new User();
					user.setcCreateTime(new Date());
					user.setcId(student.getcUserId());
					user.setcPassword(MD5.getMD5("crazy123456"));
					user.setcRole(STUDENT_ROLE);
					user.setcUserName(student.getcStudentNumber());
					result = userService.add(user);
					if (result == 0) {
						studentService.delete(student.getcUserId());
						message = message + "学号为：" + student.getcStudentNumber() + "同学信息导入失败！请检查信息是否合格！<br>";
					} else {
						i++;
					}
				} else {
					studentService.delete(student.getcUserId());
					message = message + "学号为：" + student.getcStudentNumber() + "同学信息导入失败！请检查信息是否合格！<br>";
				}
			} else {
				message = message + "学号为：" + student.getcStudentNumber() + "同学信息添加失败！信息已存在，无需导入。<br>";
			}
		}
		ModelAndView modelAndView = new ModelAndView("manage/batchImportStu");
		modelAndView.addObject("msg", "导入成功：" + i + "条；失败：" + (students.size() - i) + "条<br>" + message);
		return modelAndView;

	}

	/**
	 *  根据id是否存在判断是否为增加或修改信息，进行增加或修改
	 * @param request
	 * @param response
	 * @throws WebException
	 */
	@RequestMapping(value = { "/addStudent.action" })
	@Transactional
	public void addCollegeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String Studentnum = request.getParameter("Studentnum");
		String Studentname = request.getParameter("Studentname");
		String cYearClass = request.getParameter("cGradeId").trim();
		Student student = new Student();
		student.setcGradeId(StringUtils.string2int(cYearClass));
		student.setcName(Studentname);
		student.setcStudentNumber(Studentnum);
		ResultJson json = new ResultJson();
		int result = 0;
		if (StringUtils.isNotEmpty(id)) {
			student.setcUserId(StringUtils.string2int(id));
			result = studentService.updateSelective(student);
			if (result == 0) {
				json.setSuccess(false);
				json.setMsg("修改失败！请重新操作！");
			} else {
				json.setSuccess(true);
				json.setMsg("修改成功！");
			}
		} else {
			if (studentService.getByStudentNumber(student.getcStudentNumber()) == 0) {
				result = studentService.add(student);
				if (result == 1) {
					User user = new User();
					user.setcCreateTime(new Date());
					user.setcId(student.getcUserId());
					user.setcPassword(MD5.getMD5("crazy123456"));
					user.setcRole(STUDENT_ROLE);
					user.setcUserName(Studentnum);
					result = userService.add(user);
					if (result == 0) {
						studentService.delete(student.getcUserId());
						json.setSuccess(false);
						json.setMsg("添加失败！请重新操作！");
					} else {
						json.setSuccess(true);
						json.setMsg("添加成功！");
					}
				} else {
					json.setSuccess(false);
					json.setMsg("添加失败！请重新操作！");
				}

			} else {
				json.setSuccess(false);
				json.setMsg("信息已存在！添加失败！");
			}
		}

		writeResultJson(response, json);
	}

	/**
	 *  根据id删除信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delStudent.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delMajorAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学生
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result1, result2;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result1 = studentService.delete(StringUtils.string2int(idString[i]));
				result2 = userService.delete(StringUtils.string2int(idString[i]));
				if (result1 != 1 && result2 != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

}
