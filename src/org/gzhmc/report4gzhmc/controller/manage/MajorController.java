package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.MajorMapper;
import org.gzhmc.report4gzhmc.model.Major;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 专业信息管理
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class MajorController extends BaseController {
	@Autowired
	MajorMapper majorMapper;

	// 显示所有专业信息
	@RequestMapping(value = { "/indexMajor", "/indexMajor.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<Major> majorList = majorMapper.getAll();

		return new ModelAndView("manage/indexMajor").addObject("majorList", majorList);
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addMajor", "/addMajor.html" })
	@Transactional
	public ModelAndView addMajor(HttpServletRequest request, HttpServletResponse response) {
		// 根据id查出对应的专业，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		Major major = null;
		if (StringUtils.isNotEmpty(id)) {
			major = majorMapper.getById(StringUtils.string2int(id));
		}
		ModelAndView mav = new ModelAndView("manage/addMajor");
		mav.addObject("major", major);
		return mav;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addMajor.action" })
	@Transactional
	public ModelAndView addMajorAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String Majorname = request.getParameter("Majorname");
		Major major = new Major();
		major.setcMajorName(Majorname);
		int result;
		if (StringUtils.isNotEmpty(id)) {
			major.setcId(StringUtils.string2int(id));
			result = majorMapper.update(major);
		} else {
			result = majorMapper.add(major);
		}
		if (result == 1) {
			// 重定向转到管理专业的页面
			return new ModelAndView("redirect:/manage/indexMajor");
		} else {
			throw new WebException();
		}
	}

	// 根据id删除信息
	@RequestMapping(value = { "/delMajor.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delMajorAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的专业
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = majorMapper.delete(StringUtils.string2int(idString[i]));
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
