package org.gzhmc.report4gzhmc.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gzhmc.common.base.BaseController;
import org.gzhmc.common.util.DateUtils;
import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.service.ExperimentService;
import org.gzhmc.report4gzhmc.service.StudentService;
import org.gzhmc.report4gzhmc.service.TestService;
import org.gzhmc.report4gzhmc.service.TopicResponseLinkService;
import org.gzhmc.report4gzhmc.service.TopicResponseService;
import org.gzhmc.report4gzhmc.service.TopicThemeService;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Test;
import org.gzhmc.report4gzhmc.model.TopicResponse;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;
import org.gzhmc.report4gzhmc.model.TopicTheme;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 教师问题管理
 * 
 * @author stShen
 *
 */
@Controller
@RequestMapping("/teacher")
public class TopicTeaController extends BaseController {
	@Autowired
	TopicThemeService topicThemeService;
	@Autowired
	TopicResponseService topicResponseService;
	@Autowired
	TopicResponseLinkService topicResponseLinkService;
	@Autowired
	StudentService studentService;
	@Autowired
	TestService testService;
	@Autowired
	ExperimentService experimentService;
	// 表示问题通过审核
	private int PASS = 1;

	/**
	 * 跳转管理自己评论页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/myResponse", "/myResponse.html" })
	public ModelAndView myResponse(HttpServletRequest request) {
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicResponseLink> topicResponseLinks = topicResponseLinkService.getByUserId(userid);
		List<Test> tests = testService.getAll();
		return new ModelAndView("teacher/myResponse").addObject("topicResponseLinks", topicResponseLinks)
				.addObject("tests", tests);
	}

	/**
	 * 跳转所有学生问题页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/allQuestion", "/allQuestion.html" })
	public ModelAndView myTopic(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("teacher/allQuestion");
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicTheme> topicThemes = topicThemeService.getAll(0);
		List<Test> tests = testService.getAll();
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("topicThemes", topicThemes);
		return modelAndView;
	}

	/**
	 * 获得id获取对应问题内容
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getContent" })
	public void getContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(id);
		JSONObject jo = new JSONObject();
		jo.put("content", topicTheme.getcContent());
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(jo.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 获得id获取对应问题内容
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getResponseContent" })
	public void getResponseContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		TopicResponse topicResponse = topicResponseService.selectByPrimaryKey(Long.parseLong(id));
		// System.err.println(topicResponse.getcContent());
		JSONObject jo = new JSONObject();
		jo.put("content", topicResponse.getcContent());
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(jo.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 获得实验分类id获取对应问题
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getTeaTopicTheme" })
	public void getTeaTopicTheme(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<TopicTheme> topicThemes = topicThemeService.getByExamId(id);
		JSONArray json = new JSONArray();
		for (TopicTheme a : topicThemes) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getcTitle());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			if (a.getcTopicStatus() == 0) {
				jo.put("status", "等待审核");
			} else {
				jo.put("status", "通过");
			}
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 获得实验分类id和身份id获取对应回复
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getTeaResponse" })
	public void getTeaResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicResponseLink> topicResponseLinks = topicResponseLinkService.getByUserIdAndExamId(userid, id);
		// System.out.println(topicResponseLinks.size());
		JSONArray json = new JSONArray();
		for (TopicResponseLink a : topicResponseLinks) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getTopicTheme().getcTitle());
			jo.put("cTopicId", a.getcTopicId());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 根据id更改问题状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/passReport" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void passReport(HttpServletRequest request, HttpServletResponse response) {
		int id = StringUtils.string2int(request.getParameter("id"));
		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(id);
		ResultJson json = new ResultJson();
		topicTheme.setcTopicStatus(PASS);
		int result = topicThemeService.updateByPrimaryKey(topicTheme);
		if (result != 1) {
			json.setSuccess(false);
		}
		writeResultJson(response, json);
	}

	/**
	 * 根据id更改问题状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delComment" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delComment(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		ResultJson json = new ResultJson();
		TopicResponse topicResponse = topicResponseService.selectByPrimaryKey(Long.parseLong(id));
		int result = topicResponseService.deleteByPrimaryKey(Long.parseLong(id));
		if (result == 0) {
			json.setSuccess(false);
			json.setMsg("删除失败，请稍后再试！");
		} else {
			result = topicThemeService.subComment(topicResponse.getcTopicId());
		}
		writeResultJson(response, json);
	}

	/**
	 * 跳转问题回答页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("answerQus")
	public ModelAndView answerQus(HttpServletRequest request) {
		String qid = request.getParameter("id").trim();
		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
		ModelAndView modelAndView = new ModelAndView("teacher/answerQus");
		Experiment experiment = experimentService.getById(topicTheme.getcExperimentId());
		modelAndView.addObject("exam", experiment.getcExperimentName());
		modelAndView.addObject("topic", topicTheme);
		return modelAndView;
	}

	/**
	 * 跳转评论管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("commentManage")
	public ModelAndView commentManage(HttpServletRequest request) {
		List<Test> tests = testService.getAll();
		ModelAndView modelAndView = new ModelAndView("teacher/commentManage");
		modelAndView.addObject("tests", tests);
		return modelAndView;
	}

	/**
	 * 根据条件获取对应实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/getComment" })
	public void getComment(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {

		int experiment = Integer.parseInt(request.getParameter("experiment"));
		String datemin = request.getParameter("datemin");
		String datemax = request.getParameter("datemax");
		String userNmae = request.getParameter("num");
		List<TopicResponseLink> topicResponseLinks = topicResponseLinkService.getByUserIdAndExamIdAndDate(experiment,userNmae, datemin, datemax);
		JSONArray json = new JSONArray();
		for (TopicResponseLink a : topicResponseLinks) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cContent", a.getcContent());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			jo.put("cUserName", a.getUser().getcUserName());
			jo.put("cExperimentName", a.getExperiment().getcExperimentName());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 保存回复
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/answerQus.action" })
	public ModelAndView answerQusaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String qid = request.getParameter("qid");
		String content = request.getParameter("content");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		ModelAndView modelAndView = new ModelAndView("teacher/answerQus");
		PrintWriter out = response.getWriter();
		out.flush();
		int result = 0;
		result = topicThemeService.updateStatus(1, 1, StringUtils.string2int(qid));
		if (result != 0) {
			TopicResponse topicResponse = new TopicResponse();
			topicResponse.setcContent(content);
			topicResponse.setcCreateTime(new Date());
			topicResponse.setcLaunchId(StringUtils.string2int(userid));
			topicResponse.setcTopicId(Integer.parseInt(qid));
			// topicResponse.setcResponseStatus(1);
			result = topicResponseService.insert(topicResponse);
			TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
			Experiment experiment = experimentService.getById(topicTheme.getcExperimentId());
			modelAndView.addObject("exam", experiment.getcExperimentName());
			modelAndView.addObject("topic", topicTheme);
			if (result == 0) {
				modelAndView.addObject("answer", content);
				topicThemeService.updateStatus(0, 0, StringUtils.string2int(qid));
				out.write("<script>alert('发表失败，请稍后再试。');</script>");
			} else {
				List<TopicResponse> topicResponses = topicResponseService.getByTopicId(StringUtils.string2int(qid));
				modelAndView.addObject("exam", experiment.getcExperimentName());
				modelAndView.addObject("topic", topicTheme);
				modelAndView.addObject("topicResponses", topicResponses);
				out.write("<script>alert('发表成功。请返回原来页面并刷新。');</script>");
			}
		} else {
			out.write("<script>alert('发表失败，请稍后再试。');</script>");
		}
		return modelAndView;
	}
}
