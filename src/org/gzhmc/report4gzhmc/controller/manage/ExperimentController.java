package org.gzhmc.report4gzhmc.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;


import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;

import org.gzhmc.report4gzhmc.model.ExperimentalTest;

import org.gzhmc.report4gzhmc.model.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 课程管理
 * 
 *  @author stShen
 *
 */
@Controller
@RequestMapping("/manage")
public class ExperimentController extends BaseController {

	@Autowired
	ExperimentalTestMapper experimentalTestMapper;
	

	// 显示所有实验信息
	@RequestMapping(value = { "/indexExperiment", "/indexExperiment.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("manage/indexExperiment");
		modelAndView.addObject("experimentalTests", experimentalTests);
		return modelAndView;
	}

	// 跳转到增加或修改信息的页面
	@RequestMapping(value = { "/addExperiment", "/addExperiment.html" })
	@Transactional
	public ModelAndView addexperiment(HttpServletRequest request, HttpServletResponse response) {
		// 根据id查出对应的实验，然后通过addobject方法传到jsp页面上，通过el语言来获取
		String id = request.getParameter("id");
		ExperimentalTest experimentalTest = null;
		if (StringUtils.isNotEmpty(id)) {
			experimentalTest = experimentalTestMapper.getById(StringUtils.string2int(id));
		}
		ModelAndView mav = new ModelAndView("manage/addExperiment");
		mav.addObject("experimentalTest", experimentalTest);
		return mav;
	}

	// 根据id是否存在判断是否为增加或修改信息，进行增加或修改
	@RequestMapping(value = { "/addExperiment.action" })
	@Transactional
	public ModelAndView addexperimentAction(HttpServletRequest request, HttpServletResponse response) throws WebException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String Englishname = request.getParameter("Englishname");
		String TestTime = request.getParameter("TestTime");
		ExperimentalTest experimentalTest=new ExperimentalTest();
		experimentalTest.setcExperimentEnglishName(Englishname);
		experimentalTest.setcExperimentName(name);
		experimentalTest.setcExperimentTime(TestTime);
		int result;
		if (StringUtils.isNotEmpty(id)) {
			experimentalTest.setcId(StringUtils.string2int(id));
			result = experimentalTestMapper.update(experimentalTest);
		} else {
			result = experimentalTestMapper.add(experimentalTest);
		}
		if (result == 1) {
			// 重定向转到管理课程的页面
			return new ModelAndView("redirect:/manage/indexExperiment.html");
		} else {
			throw new WebException();
		}
	}

	// 根据id删除信息
	@RequestMapping(value = { "/delExperiment.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delexperimentAction(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的课程
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result;
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				result = experimentalTestMapper.delete(StringUtils.string2int(idString[i]));
				if (result != 1) {
					json.setSuccess(false);
					json.setMsg("删除失败!");
					break;
				}
			}
		}
		writeResultJson(response, json);
	}

	/*
	 * 修改状态
	 */
	@RequestMapping("statu.action")
	public void statuaction(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String statu=request.getParameter("statu");
		ExperimentalTest experimentalTest=experimentalTestMapper.getById(StringUtils.string2int(id));
		experimentalTest.setcStatu(StringUtils.string2int(statu));
		int result=experimentalTestMapper.update(experimentalTest);
		ResultJson json = new ResultJson();
		if(result!=0){
			json.setSuccess(true);
		}
		else{
			json.setSuccess(false);
			json.setMsg("对不起，服务器异常，请稍后再试。");
		}
		writeResultJson(response, json);
	}
	
}
