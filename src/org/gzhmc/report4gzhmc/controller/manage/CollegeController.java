package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 学院管理
 * 
 * @author gcLiang
 * @date 2016年6月25日
 */
@Controller
@RequestMapping("/manage")
public class CollegeController extends BaseController {

	@Autowired
	CollegeMapper collegeMapper;

	/**
	 * 测试
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/test", "/test.html" })
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		
		ModelAndView modelAndView = new ModelAndView("manage/test");
		
		return modelAndView;
	}
	
	/**
	 * 测试2
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/test2", "/test2.html" })
	public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		
		ModelAndView modelAndView = new ModelAndView("manage/test2");
		
		return modelAndView;
	}
	
	// 显示所有学院信息
	@RequestMapping(value = { "/indexCollege", "/indexCollege.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<College> colleges = collegeMapper.getAll();
		return new ModelAndView("manage/indexCollege").addObject("colleges", colleges);
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addCollege", "/addCollege.html" })
	@Transactional
	public ModelAndView addCollege(HttpServletRequest request, HttpServletResponse response) {
		// 根据id查出对应的学院，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		College college = null;
		if (StringUtils.isNotEmpty(id)) {
			college = collegeMapper.getById(StringUtils.string2int(id));
		}
		ModelAndView mav = new ModelAndView("manage/addCollege");
		mav.addObject("college", college);
		return mav;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addCollege.action" })
	@Transactional
	public void addCollegeAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String collegeName = request.getParameter("collegeName");
		College college = new College();
		college.setcCollegeName(collegeName);
		ResultJson json = new ResultJson();
		int result;
		if (StringUtils.isNotEmpty(id)) {
			college.setcId(StringUtils.string2int(id));
			result = collegeMapper.updateSelective(college);
		} else {
			result = collegeMapper.add(college);
		}
		if (result == 1) {
			// 重定向转到管理学院的页面
			json.setSuccess(true);
			json.setMsg("操作成功!");
			} else {
			throw new WebException();
		}
		writeResultJson(response, json);
	}

	//判断本表关联其他表的数据是否存在，若存在则不允许删除
	private boolean qualify(int cCollegeId) {
		if(collegeMapper.getByGradeCollegeId(cCollegeId)==0&&collegeMapper.getByTeacherCollegeId(cCollegeId)==0)
	        return true;
		else
			return false;
	}
	
	// 根据id删除信息
	@RequestMapping(value = { "/delCollege.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delCollegeAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的学院
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		json.setSuccess(true);
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				if (qualify(StringUtils.string2int(idString[i]))) {
					result = collegeMapper.delete(StringUtils.string2int(idString[i]));
					if (result != 1) {
						json.setSuccess(false);
						json.setMsg("删除失败!");
						break;
					}
				}else{
					json.setSuccess(false);
					json.setMsg("删除的学院关联其他数据，不允许删除!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}
}
