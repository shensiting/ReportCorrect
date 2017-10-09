package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.TestService;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 实验分类管理
 * 
 * @author stshen
 *
 *         2017年9月22日
 */
@Controller
@RequestMapping("/manage")
public class TestController extends BaseController {
	@Autowired
	TestService testService;

	/**
	 * 显示所有实验分类信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/indexTest", "/indexTest.html" })
	public ModelAndView index(HttpServletRequest request) {
		List<Test> tests = testService.getAll();
		return new ModelAndView("manage/indexTest").addObject("tests", tests);
	}

	/**
	 *  跳转到增加或修改信息的页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/addtest", "/addtest.html" })
	@Transactional
	public ModelAndView addtest(HttpServletRequest request) {
		// 根据id查出对应的实验分类，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		Test test = null;
		if (StringUtils.isNotEmpty(id)) {
			test = testService.getById(StringUtils.string2int(id));
		}
		ModelAndView mav = new ModelAndView("manage/addTest");
		mav.addObject("test", test);
		return mav;
	}

	/**
	 *  根据id是否存在判断是否为增加或修改信息，进行增加或修改
	 * @param request
	 * @param response
	 * @throws WebException
	 */
	@RequestMapping(value = { "/addTest.action" })
	@Transactional
	public void addtestAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String testName = request.getParameter("testName");
		Test test = new Test();
		test.setcTestName(testName);
		ResultJson json = new ResultJson();
		int result;
		if (StringUtils.isNotEmpty(id)) {
			test.setcId(StringUtils.string2int(id));
			result = testService.updateSelective(test);
		} else {
			result = testService.add(test);
		}
		if (result == 1) {
			// 重定向转到管理实验分类的页面
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			throw new WebException();
		}
		writeResultJson(response, json);
	}

	/**
	 *  判断本表关联其他表的数据是否存在，若存在则不允许删除
	 * @param cTestId
	 * @return
	 */
	private boolean qualify(int cTestId) {
		if (testService.getByExamTestId(cTestId) == 0)
			return true;
		else
			return false;
	}

	/**
	 *  根据id删除信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delTest.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delTestAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的实验分类
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				if (qualify(StringUtils.string2int(idString[i]))) {
					result = testService.delete(StringUtils.string2int(idString[i]));
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
