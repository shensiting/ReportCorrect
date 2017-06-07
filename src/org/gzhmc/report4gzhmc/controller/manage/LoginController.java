package org.gzhmc.report4gzhmc.controller.manage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.CaptchaUtil;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.mapper.MajorMapper;
import org.gzhmc.report4gzhmc.mapper.StudentMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.gzhmc.report4gzhmc.model.Major;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.Teacher;
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
public class LoginController extends BaseController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	MajorMapper majorMapper;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	GradeMajorCollegeMapper gradeMajorCollegeMapper;

	// 判断学生
	private int STUDENT_ROLE = 3;
	// 判断教师
	private int TEACHER_ROLE = 2;
	// 判断管理员权限
	private int MANAGE_ROLE = 1;
	// 判断教师账号是否已经被验证
	private int TEACHER_VERIFY = 1;

	/**
	 * 跳转注册页面，把数据库检索出来的信息呈现在页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("userid", " ");
		session.setMaxInactiveInterval(618000);
		List<College> colleges = collegeMapper.getAll();
		List<Major> majors = majorMapper.getAll();
		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("/manage/register");
		modelAndView.addObject("colleges", colleges);
		modelAndView.addObject("majors", majors);
		modelAndView.addObject("gradeMajorColleges", gradeMajorColleges);
		return modelAndView;
	}

	/** 
     * 验证码验证 
     *  
     * @param session 
     * @param code 
     */  
    private void checkCode(HttpSession session, String code) {  
        String codeSession = (String) session.getAttribute("code");  
        if (!StringUtils.isNotEmpty(codeSession)) {  
           System.out.println("没有生成验证码信息");  
            
        }  
        if (StringUtils.isNotEmpty(code)) {  
        	System.out.println("未填写验证码信息");  
           
        }  
        if (codeSession.equalsIgnoreCase(code)) {  
            // 验证码通过  
        } else {  
        	System.out.println("验证码错误");  
            
        }  
    }  
	
    @RequestMapping("/check")  
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // 通知浏览器不要缓存  
        response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "-1");  
        CaptchaUtil util = CaptchaUtil.Instance();  
        // 将验证码输入到session中，用来验证  
        String code = util.getString();  
        request.getSession().setAttribute("code", code);  
        // 输出打web页面  
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());  
    }  
    
	/**
	 * 登陆验证信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/check.action" })
	public void logins(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String codeSession = (String) session.getAttribute("code"); 
		session.setAttribute("userid", " ");
		session.setMaxInactiveInterval(618000);
		String code=request.getParameter("code").trim();
		String number = request.getParameter("number").trim();
		String password = request.getParameter("password").trim();
		//System.out.println(password);
		ResultJson json = new ResultJson();
		User user = userMapper.getByUserName(number);
		//判断验证码是否正确
		if (code.equals(codeSession)) {
			if (null != user) {
				if (user.getcPassword().equals(password)) {
					json.setSuccess(true);
					if (user.getcRole() == MANAGE_ROLE) {
						session.setAttribute("userid", user.getcId());
						json.setMsg("manage/index");
					} else if (user.getcRole() == TEACHER_ROLE) {
						// 判断老师账号是否通过验证
						Teacher teacher = teacherMapper.getByTeacherId(number);
						if (teacher != null) {
							// if (teacher.getcVerify() == TEACHER_VERIFY) {
							session.setAttribute("userid", user.getcId());
							json.setMsg("teacher/test");
							// } else {
							// json.setSuccess(false);
							// json.setMsg("对不起，您的账号还未通过验证，请等验证完毕后再登录。\n验证时间一般为一个工作日，谢谢合作。");
							// }
						}
					} else {
						session.setAttribute("userid", user.getcId());
						json.setMsg("student/index");
					}
				} else {
					json.setSuccess(false);
					json.setMsg("账号或密码错误，请确认后输入。");
				}
			} else {
				json.setSuccess(false);
				json.setMsg("账号不存在，请先注册。");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("验证码输入错误！");
		}
		writeResultJson(response, json);
	}

	/**
	 * 学生注册具体实现方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/Studentregister.action")
	@Transactional
	public void studentRegisterAction(HttpServletRequest request, HttpServletResponse response) {
		String ScId = request.getParameter("cId").trim();
		String ScPassword = request.getParameter("cPassword");
		String ScName = request.getParameter("cName");
		String ScYearClass = request.getParameter("cYearClass").trim();
		// 判断是否数据库同时把两个数据存入
		int result;
		ResultJson json = new ResultJson();
		User user = userMapper.getByUserName(ScId);
		if (null != user) {
			json.setSuccess(false);
			json.setMsg("账号已存在，请前往登录。");
		} else {
			// 存入用户信息
			User user2 = new User();
			user2.setcCreateTime(new Date());
			user2.setcPassword(ScPassword);
			user2.setcRole(STUDENT_ROLE);
			user2.setcUserName(ScId);
			result = userMapper.add(user2);
			if (result != 0) {
				// 存入学生信息
				int cuserid = userMapper.getMaxcId();
				Student student = new Student();
				student.setcUserId(cuserid);
				student.setcGradeId(StringUtils.string2int(ScYearClass));
				student.setcName(ScName);
				student.setcStudentNumber(ScId);
				result = studentMapper.add(student);
				if (result != 0) {
					json.setSuccess(true);
				} else {
					json.setSuccess(false);
					json.setMsg("服务器异常，请稍候再试！");
				}
			} else {
				json.setSuccess(false);
				json.setMsg("服务器异常，请稍候再试！");
			}
		}
		writeResultJson(response, json);
	}

	/**
	 * 老师注册具体实现方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/Teacherregister.action")
	@Transactional
	public void TeacherRegisterAction(HttpServletRequest request, HttpServletResponse response) {
		String TcId = request.getParameter("cId").trim();
		String TcPassword = request.getParameter("cPassword").trim();
		String TcName = request.getParameter("cName").trim();
		String TcCollege = request.getParameter("cCollege").trim();
		//System.out.println(TcCollege + " " + TcName);
		// 判断是否数据库同时把两个数据存入
		int result;
		ResultJson json = new ResultJson();
		User user = userMapper.getByUserName(TcId);
		if (null != user) {
			//System.out.println(user.getcUserName() + " null!=user");
			json.setSuccess(false);
			json.setMsg("账号已存在，请前往登录。");
		} else {
			// 存入用户信息
			User user2 = new User();
			user2.setcCreateTime(new Date());
			user2.setcPassword(TcPassword);
			user2.setcRole(TEACHER_ROLE);
			user2.setcUserName(TcId);
			result = userMapper.add(user2);
			if (result != 0) {
				// 存入教师信息
				int cuserid = userMapper.getMaxcId();
				Teacher teacher = new Teacher();
				teacher.setcCollegeId(StringUtils.string2int(TcCollege));
				teacher.setcName(TcName);
				teacher.setcTeacherId(TcId);
				teacher.setcUserId(cuserid);
				result = teacherMapper.add(teacher);
				if (result != 0) {
					json.setSuccess(true);
				} else {
					json.setSuccess(false);
					json.setMsg("服务器异常，请稍候再试！");
				}
			} else {
				json.setSuccess(false);
				json.setMsg("服务器异常，请稍候再试！");
			}
		}
		writeResultJson(response, json);
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exit")
	public ModelAndView exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("userid", null);
		return new ModelAndView("redirect:/login.jsp");
	}

}
