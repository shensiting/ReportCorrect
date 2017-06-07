package org.gzhmc.report4gzhmc.controller.manage;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.MD5;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆用户管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class UserController extends BaseController {

	@Autowired
	UserMapper userMapper;
	@RequestMapping("/index")
	public ModelAndView manageIndex(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("manage/index");
	}	

	/**
	 * 显示所有学生信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/indexUser", "indexUser.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userMapper.getAll();
		return new ModelAndView("manage/indexUser").addObject("users", users);
	}

	/**
	 *  跳转到增加或修改信息的页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/addUser.html", "/addUser" })
	@Transactional
	public ModelAndView addGrade(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		User user = null;
		if (StringUtils.isNotEmpty(id)) {
			user = userMapper.getById(StringUtils.string2int(id));
		}
		ModelAndView modelAndView = new ModelAndView("manage/addUser");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	/**
	 *  根据id是否存在判断是否为增加或修改信息，进行增加或修改
	 * @param request
	 * @param response
	 * @return
	 * @throws WebException
	 */
	@RequestMapping(value = { "/addUser.action" })
	@Transactional
	public void addCollegeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id").trim();
		String cUserName = request.getParameter("userNum");
		String cPassword = request.getParameter("password");
		String cRole = request.getParameter("role").trim();
		User user = new User();
		ResultJson json = new ResultJson();
		user.setcUserName(cUserName);	
		user.setcRole(StringUtils.string2int(cRole));
		int result=0;
		if (StringUtils.isNotEmpty(id)) {
			//密码默认在首位加“crazy”
			user.setcPassword(MD5.getMD5("crazy"+cPassword));
			user.setcId(StringUtils.string2int(id));
			result = userMapper.updateSelective(user);
		} 
		if (result == 1) {
			// 重定向转到管理登录用户的页面
			json.setSuccess(true);			
		} else {
			throw new WebException();
		}
		writeResultJson(response, json);
	}

	/**
	 *  根据id删除信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delUser.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delCollegeAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的用户
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = userMapper.delete(StringUtils.string2int(idString[i]));
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
