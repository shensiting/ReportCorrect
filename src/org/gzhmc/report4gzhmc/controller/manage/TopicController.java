package org.gzhmc.report4gzhmc.controller.manage;

import java.io.IOException;
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
import org.gzhmc.report4gzhmc.service.TopicResponseService;
import org.gzhmc.report4gzhmc.service.TopicThemeService;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.gzhmc.report4gzhmc.model.ResultJson;
import org.gzhmc.report4gzhmc.model.Student;
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

/*
 * 评论管理
 */
@Controller
@RequestMapping("/manage")
public class TopicController extends BaseController {
	@Autowired
	TopicThemeService topicThemeService;
	@Autowired
	TestService testService;
	@Autowired
	TopicResponseService topicResponseService;
	@Autowired
	ExperimentService experimentService;
	@Autowired
	StudentService studentService;

	/**
	 * 跳转问题显示页面(分情况跳转)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showTopic")
	public ModelAndView showMyTopic(HttpServletRequest request) {
		String qid = request.getParameter("id").trim();
		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
		ModelAndView modelAndView = new ModelAndView("manage/showTopic");
		Student student = studentService.getById(topicTheme.getcLaunchId());
		List<TopicResponse> topicResponses = topicResponseService.getByTopicId(StringUtils.string2int(qid));

		Experiment experiment = experimentService.getById(topicTheme.getcExperimentId());
		modelAndView.addObject("exam", experiment.getcExperimentName());
		modelAndView.addObject("stuName", student.getcStudentNumber());
		modelAndView.addObject("topic", topicTheme);
		modelAndView.addObject("topicResponses", topicResponses);

		return modelAndView;
	}

	/**
	 * 获得实验分类id获取对应实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = { "/getTopic" })
	public void getTopic(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		int experiment = Integer.parseInt(request.getParameter("experiment"));
		String datemin = request.getParameter("datemin");
		String datemax = request.getParameter("datemax");
		String userNmae = request.getParameter("num");
	
		List<TopicTheme> topicThemes = topicThemeService.getByUserIdAndExamIdAndDate(experiment, userNmae, datemin,datemax);
		JSONArray json = new JSONArray();
		for (TopicTheme a : topicThemes) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getcTitle());
			jo.put("cCommentNum", a.getcCommentNum());

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
	 * 显示所有评论列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "indexComment", "indexComment.html" })
	public ModelAndView indexComment(HttpServletRequest request) {
		List<TopicTheme> themes = topicThemeService.getManageAll();
		List<Test> tests = testService.getAll();
		// ModelAndView mdv=new ModelAndView();
		return new ModelAndView("manage/indexComment").addObject("TopicTheme", themes).addObject("tests", tests);
	}

	/**
	 * 显示所有评论列表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "getTopicComment", "getTopicComment.html" })
	public void getTopicComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<TopicTheme> topicThemes = topicThemeService.getByExamIdExceptStatus(id);
		JSONArray json = new JSONArray();
		for (TopicTheme a : topicThemes) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getcTitle());
			jo.put("cCommentNum", a.getcCommentNum());

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
	 * 删除帖子
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/delTheme.action" })
	@Transactional // 增删改操作一定要添加事务回滚
	public void delTheme(HttpServletRequest request, HttpServletResponse response) {
		// 根据id删除对应的帖子
		String ids = request.getParameter("ids");
		ResultJson json = new ResultJson();
		String[] idString = ids.split(",");
		int result = 0;
		json.setSuccess(true);
		if (idString != null && idString.length > 0) {
			for (int i = 0; i < idString.length; i++) {
				int k = StringUtils.string2int(idString[i]);
				List<TopicResponse> topicResponses = topicResponseService.getByTopicId(k);
				for (TopicResponse t : topicResponses) {
					result = topicResponseService.deleteByPrimaryKey(t.getcId());
					if (result != 0) {
						topicThemeService.subComment(k);
					} else {
						json.setSuccess(false);
						json.setMsg("删除失败!");
						return;
					}
				}
				result = topicThemeService.deleteByPrimaryKey(k);
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
