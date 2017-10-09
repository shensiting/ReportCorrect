package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.MajorService;
import org.gzhmc.report4gzhmc.model.Major;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 专业信息管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class MajorController extends BaseController {
	@Autowired
	MajorService majorService;

	/**
	 *  显示所有专业信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/indexMajor", "/indexMajor.html" })
	public ModelAndView index(HttpServletRequest request) {
		List<Major> majorList = majorService.getAll();
		return new ModelAndView("manage/indexMajor").addObject("majorList", majorList);
	}

	/**
	 *  跳转到增加或修改信息的页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/addMajor", "/addMajor.html" })
	@Transactional
	public ModelAndView addMajor(HttpServletRequest request) {
		// 根据id查出对应的专业，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		Major major = null;
		if (StringUtils.isNotEmpty(id)) {
			major = majorService.getById(StringUtils.string2int(id));
		}
		ModelAndView mav = new ModelAndView("manage/addMajor");
		mav.addObject("major", major);
		return mav;
	}

	/**
	 *  根据id是否存在判断是否为增加或修改信息，进行增加或修改
	 * @param request
	 * @param response
	 * @throws WebException
	 */
	@RequestMapping(value = { "/addMajor.action" })
	@Transactional
	public void addMajorAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String Majorname = request.getParameter("Majorname");
		ResultJson json = new ResultJson();
		Major major = new Major();
		major.setcMajorName(Majorname);
		int result;
		if (StringUtils.isNotEmpty(id)) {
			major.setcId(StringUtils.string2int(id));
			result = majorService.update(major);
		} else {
			result = majorService.add(major);
		}
		if (result == 1) {
			// 重定向转到管理专业的页面
			json.setSuccess(true);
			json.setMsg("操作成功!");

		} else {
			throw new WebException();
		}
		writeResultJson(response, json);
	}

	/**
	 *  判断本表关联其他表的数据是否存在，若存在则不允许删除
	 * @param cMajorId
	 * @return
	 */
	private boolean qualify(int cMajorId) {
		if (majorService.getByGradeMajorId(cMajorId) == 0)
			return true;
		else
			return false;
	}

	/**
	 *  根据id删除信息
	 * @param request
	 * @param response
	 */
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
				if (qualify(StringUtils.string2int(idString[i]))) {
					result = majorService.delete(StringUtils.string2int(idString[i]));
					if (result != 1) {
						json.setSuccess(false);
						json.setMsg("删除失败!");
						break;
					}
				} else {
					json.setSuccess(false);
					json.setMsg("删除的专业关联其他数据，不允许删除!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

}
