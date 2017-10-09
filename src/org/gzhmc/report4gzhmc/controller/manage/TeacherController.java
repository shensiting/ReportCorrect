package org.gzhmc.report4gzhmc.controller.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.ReadStudentExcel;
import org.gzhmc.common.util.ReadTeadentExcel;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.CollegeService;
import org.gzhmc.report4gzhmc.service.TeacherCollegeService;
import org.gzhmc.report4gzhmc.service.TeacherGradeLinkService;
import org.gzhmc.report4gzhmc.service.TeacherGradeService;
import org.gzhmc.report4gzhmc.service.TeacherService;
import org.gzhmc.report4gzhmc.service.UserService;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.gzhmc.report4gzhmc.model.TeacherCollege;
import org.gzhmc.report4gzhmc.model.TeacherGrade;
import org.gzhmc.report4gzhmc.model.TeacherGradeLink;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author stShen
 */
@Controller
@RequestMapping("/manage")
public class TeacherController extends BaseController {

	@Autowired
	TeacherGradeLinkService teacherGradeLinkService;
	@Autowired
	TeacherCollegeService teacherCollegeService;
	@Autowired
	CollegeService collegeService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	UserService userService;

	@Autowired
	TeacherGradeService teacherGradeService;

	// VERIFY表示教师班级已经通过验证
	private int VERIFY = 1;
	// 判断教师
	private int TEACHER_ROLE = 2;
	/**
	 *  显示所有教师信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/indexTeacher", "/indexTeacher.html" })
	public ModelAndView index(HttpServletRequest request) {
		List<TeacherCollege> teacherColleges = teacherCollegeService.getAll();
		List<College> colleges = collegeService.getAll();
		ModelAndView modelAndView = new ModelAndView("/manage/indexTeacher");
		modelAndView.addObject("teacherColleges", teacherColleges);
		modelAndView.addObject("colleges", colleges);
		return modelAndView;
	}

	/**
	 * 跳转批量导入学生页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/batchImportTea", "/batchImportTea.html" })
	public ModelAndView batchImportTea(HttpServletRequest request) {			
		ModelAndView modelAndView = new ModelAndView("manage/batchImportTea");			
		return modelAndView;
	}
	
	/**
	 * 导入学生数据
	 * @param request
	 * @param response
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = { "/importTeaExcel"})
	public ModelAndView importTeaExcel(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile uploadFile) {			
		ReadTeadentExcel readTeadentExcel=new ReadTeadentExcel();
		List<Teacher> teachers;
		String name=uploadFile.getOriginalFilename();
		teachers=readTeadentExcel.getExcelInfo(name, uploadFile);		
		String message="";
		int result=0,i=0;
		User user;
		for (Teacher teacher : teachers) {			
			if (teacherService.getByTeacherId(teacher.getcTeacherId())==null) {
				result = teacherService.add(teacher);
				if (result != 0) {
					user = new User();
					user.setcCreateTime(new Date());
					user.setcId(teacher.getcUserId());
					user.setcPassword(MD5.getMD5("crazy123456"));
					user.setcRole(TEACHER_ROLE);
					user.setcUserName(teacher.getcTeacherId());
					result = userService.add(user);
					if (result == 0) {
						teacherService.delete(teacher.getcUserId());
						message = message + "工号为：" + teacher.getcTeacherId() + "教师信息导入失败！请检查信息是否合格！<br>";
					}else{
						i++;
					}
				} else {
					teacherService.delete(teacher.getcUserId());
					message = message + "工号为：" + teacher.getcTeacherId() + "教师信息导入失败！请检查信息是否合格！<br>";
				}
			} else {
				message = message + "工号为：" + teacher.getcTeacherId() + "教师信息添加失败！信息已存在，无需导入。<br>";
			}
		}				
		ModelAndView modelAndView = new ModelAndView("manage/batchImportTea");	
		modelAndView.addObject("msg", "导入成功："+i+"条；失败："+(teachers.size()-i)+"条<br>"+message);
		return modelAndView;
		
	}



	
	/**
	 *  显示所有教师关联班级信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/indexTeacherGrade", "/indexTeacherGrade.html" })
	public ModelAndView indexTeacherGrade(HttpServletRequest request) {
		List<TeacherGradeLink> teacherGradeLink = teacherGradeLinkService.getAllTeaGra();
		ModelAndView modelAndView = new ModelAndView("/manage/indexTeacherGrade");
		modelAndView.addObject("teacherGrades", teacherGradeLink);
		return modelAndView;
	}

	/**
	 *  跳转到增加或修改信息的页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/addTeacher", "/addTeacher.html" })
	@Transactional
	public ModelAndView addCollege(HttpServletRequest request) {
		// 根据id查出对应的考核，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		TeacherCollege teacherCollege = null;
		if (StringUtils.isNotEmpty(id)) {
			teacherCollege = teacherCollegeService.getById(StringUtils.string2int(id));
		}
		List<College> college = collegeService.getAll();
		request.setAttribute("college", college);
		ModelAndView mav = new ModelAndView("manage/addTeacher");
		mav.addObject("teacherCollege", teacherCollege);
		return mav;
	}

	/**
	 *  根据id是否存在判断是否为增加或修改信息，进行增加或修改
	 * @param request
	 * @param response
	 * @throws WebException
	 */
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
		int result=0;
		if (StringUtils.isNotEmpty(id)) {
			teacher.setcUserId(StringUtils.string2int(id));
			result = teacherService.updateSelective(teacher);
			if (result == 0) {				
				json.setSuccess(false);
				json.setMsg("修改失败！请重新操作！");
			} else {
				json.setSuccess(true);
				json.setMsg("修改成功！");
			}
		} else {
			if (teacherService.getByTeacherId(teacher.getcTeacherId()) == null) {
				result = teacherService.add(teacher);
				if (result == 1) {
					User user = new User();
					user.setcCreateTime(new Date());
					user.setcId(teacher.getcUserId());
					user.setcPassword(MD5.getMD5("crazy123456"));
					user.setcRole(2);
					user.setcUserName(teachername);
					result=userService.add(user);
					if (result == 0) {
						teacherService.delete(teacher.getcUserId());
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
	 *  根据id删除教师信息
	 * @param request
	 * @param response
	 */
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
				result = teacherService.delete(StringUtils.string2int(idString[i]));
				result2 = userService.delete(StringUtils.string2int(idString[i]));
				if (result != 1 && result2 != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

	/**
	 *  根据id删除教师班级关联信息
	 * @param request
	 * @param response
	 */
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

	/**
	 *  根据id验证信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/verify.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void verifyAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id验证教师资格
		String id = request.getParameter("id");
		ResultJson json = new ResultJson();

		int result;
		if (id != null && id != "") {

			TeacherGrade teacherGrade = teacherGradeService.getById(StringUtils.string2int(id));
			teacherGrade.setcStatus(VERIFY);
			result = teacherGradeService.updateByPrimaryKeySelective(teacherGrade);
			if (result != 1) {
				json.setSuccess(false);
			} else {
				json.setSuccess(true);
			}

		}
		writeResultJson(response, json);
	}
}
