package org.gzhmc.report4gzhmc.controller.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.exception.WebException;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.ExperimentMapper;
import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.mapper.TestMapper;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;

import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Test;
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
	@Autowired
	TestMapper testMapper;
	@Autowired
	ExperimentMapper experimentMapper;

	// 显示所有实验信息
	@RequestMapping(value = { "/indexExperiment", "/indexExperiment.html" })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		List<ExperimentalTest> experimentalTests = experimentalTestMapper.getAll();
		List<Test> tests=testMapper.getAll();
		ModelAndView modelAndView = new ModelAndView("manage/indexExperiment");
		modelAndView.addObject("experimentalTests", experimentalTests);
		modelAndView.addObject("tests", tests);
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
	public void addexperimentAction(HttpServletRequest request, HttpServletResponse response) throws WebException, IOException {
		// 获取表单中的值，如果id值为空，则表示新增，否则表示根据此id来修改
		String id = request.getParameter("id");
		System.out.println(id);
		String name = request.getParameter("experimentName");
		String Englishname = request.getParameter("testEngName");
		String TestTime = request.getParameter("testTime");
		String classify = request.getParameter("experiment");
		Experiment experiment=new Experiment();
		experiment.setcExperimentEnglishName(Englishname);
		experiment.setcExperimentName(name);
		experiment.setcExperimentTime(TestTime);
		experiment.setcClassify(Integer.parseInt(classify));
		ResultJson json = new ResultJson();
		int result;
		if (StringUtils.isNotEmpty(id)) {
			experiment.setcId(StringUtils.string2int(id));
			result = experimentMapper.update(experiment);
		} else {
			result = experimentMapper.add(experiment);
		}
		if (result == 1) {
			// 重定向转到管理实验的页面
			json.setSuccess(true);
			json.setMsg("操作成功!");
			
		} else {
			throw new WebException();
		}
		writeResultJson(response, json);
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
				result = experimentMapper.delete(StringUtils.string2int(idString[i]));
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
		//experimentalTest.setcStatu(StringUtils.string2int(statu));
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
