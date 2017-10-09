package org.gzhmc.report4gzhmc.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.gzhmc.report4gzhmc.model.Student;
import org.gzhmc.report4gzhmc.model.Test;
import org.gzhmc.report4gzhmc.model.TopicResponse;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;
import org.gzhmc.report4gzhmc.model.TopicTheme;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 学生问答管理
 * 
 * @author stShen
 * @date 2017年10月5日
 */
@Controller
@RequestMapping("/student")
public class TopicStuController extends BaseController {

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

	/**
	 * 根据实验id查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/getByExamId", "/getByExamId.html" })
	public ModelAndView getByExamId(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("experiment"));
		ModelAndView modelAndView = new ModelAndView("student/getTopicByExam");
		List<TopicTheme> topicThemes = topicThemeService.getPageAllByExamId(id, 0, 20);
		int count = topicThemeService.getCountAllByExamId(id);
		List<Test> tests = testService.getAll();
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("topicThemes", topicThemes);
		modelAndView.addObject("sum", count);
		modelAndView.addObject("eId", id);
		// 判断页码
		if (count <= 20)
			modelAndView.addObject("end", "1");
		else if (count % 20 != 0)
			modelAndView.addObject("end", count / 20 + 1);
		else
			modelAndView.addObject("end", count / 20);
		return modelAndView;
	}

	/**
	 * 跳转管理朋友评论页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/friendResponse", "/friendResponse.html" })
	public ModelAndView friendResponse(HttpServletRequest request) {
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicResponseLink> topicResponseLinks = topicResponseLinkService.getAllByUserId(userid);
		List<Test> tests = testService.getAll();
		return new ModelAndView("student/friendResponse").addObject("topicResponseLinks", topicResponseLinks)
				.addObject("tests", tests);
	}

	/**
	 * 获得实验分类id获取对应朋友回复
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getfriendResponse" })
	public void getfriendResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicResponseLink> topicResponseLinks = topicResponseLinkService.getAllByUserIdAndExamId(userid, id);
		// System.out.println(topicResponseLinks.size());
		JSONArray json = new JSONArray();
		for (TopicResponseLink a : topicResponseLinks) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getTopicTheme().getcTitle());
			jo.put("cTopicId", a.getcTopicId());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			if (a.getcResponseStatus() == 0) {
				jo.put("cResponseStatus", "未查看");
			} else {
				jo.put("cResponseStatus", "已查看");
			}

			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 跳转学生管理自己问题页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/myTopic", "/myTopic.html" })
	public ModelAndView myTopic(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("student/myTopic");
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicTheme> topicThemes = topicThemeService.getByLaunchId(userid);
		List<Test> tests = testService.getAll();
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("topicThemes", topicThemes);
		return modelAndView;
	}

	/**
	 * 跳转问题显示页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showTopic")
	public ModelAndView showTopic(HttpServletRequest request) {
		String qid = request.getParameter("id").trim();
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
		Student student = studentService.getById(topicTheme.getcLaunchId());
		List<TopicResponse> topicResponses = topicResponseService.getByTopicId(StringUtils.string2int(qid));
		// 判断查看topic的是不是本人，若是本人，则将未查看的消息设置为已查看
		if (topicTheme.getcLaunchId() == userid) {
			for (TopicResponse t : topicResponses) {
				topicResponseService.updateStatusByPrimaryKey(t.getcId());
			}
		}
		ModelAndView modelAndView = new ModelAndView("student/showTopic");
		Experiment experiment = experimentService.getById(topicTheme.getcExperimentId());
		modelAndView.addObject("exam", experiment.getcExperimentName());
		modelAndView.addObject("stuName", student.getcStudentNumber());
		modelAndView.addObject("topic", topicTheme);
		modelAndView.addObject("topicResponses", topicResponses);
		return modelAndView;
	}

	/**
	 * 跳转问题显示页面(分情况跳转)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showMyTopic")
	public ModelAndView showMyTopic(HttpServletRequest request) {
		String qid = request.getParameter("id").trim();
		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
		ModelAndView modelAndView;
		if (topicTheme.getcTopicStatus() == 0) {
			modelAndView = new ModelAndView("student/reportShow").addObject("report", topicTheme.getcContent());
		} else {
			modelAndView = new ModelAndView("redirect:/student/showTopic").addObject("id", qid);
		}

		return modelAndView;
	}

	/**
	 * 保存回复
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/replyQus.action" })
	public ModelAndView replyQusaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String qid = request.getParameter("qid");
		String content = request.getParameter("content");
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		ModelAndView modelAndView = new ModelAndView("student/showTopic");
		PrintWriter out = response.getWriter();
		out.flush();
		int result = 0;
		TopicTheme topicTheme1 = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
		// 对主帖子进行评论++
		result = topicThemeService.addComment(StringUtils.string2int(qid));
		if (result != 0) {
			TopicResponse topicResponse = new TopicResponse();
			topicResponse.setcContent(content);
			topicResponse.setcCreateTime(new Date());
			topicResponse.setcLaunchId(userid);
			topicResponse.setcTopicId(Integer.parseInt(qid));
			// 若帖子本来是自己发的，故不用消息提示
			if (topicTheme1.getcLaunchId() == userid)
				topicResponse.setcResponseStatus(1);

			result = topicResponseService.insert(topicResponse);

			if (result == 0) {
				modelAndView.addObject("answer", content);
				// 给前面添加的评论恢复原数
				topicThemeService.subComment(StringUtils.string2int(qid));
				out.write("<script>alert('发表失败，请稍后再试。');</script>");
			} else {

				out.write("<script>alert('发表成功。请返回原来页面并刷新。');</script>");
			}
		} else {
			out.write("<script>alert('发表失败，请稍后再试。');</script>");
		}

		TopicTheme topicTheme = topicThemeService.selectByPrimaryKey(StringUtils.string2int(qid));
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
	 * 根据实验id和页码分页查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getPageTopicByExam", "/getPageTopicByExam.html" })
	public void getPageTopicByExam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int eId = Integer.parseInt(request.getParameter("eId"));
		int id = Integer.parseInt(request.getParameter("id"));
		int i = 20 * (id - 1);
		List<TopicTheme> topicThemes = topicThemeService.getPageAllByExamId(eId, i, i + 20);
		JSONArray json = new JSONArray();
		for (TopicTheme a : topicThemes) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getcTitle());
			jo.put("cContent", a.getcContent());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			jo.put("cCommentNum", a.getcCommentNum());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 根据实验id和页码分页查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getCountMes", "/getCountMes.html" })
	public void getCountMes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		int num = topicThemeService.getCountMes(userid);
		JSONObject jo = new JSONObject();
		if (num == 0) {
			jo.put("num", "");
		} else {
			jo.put("num", num);
		}

		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(jo.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 根据内容查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/getByMes", "/getByMes.html" })
	public ModelAndView getByMes(HttpServletRequest request) {
		String mes = request.getParameter("mes").trim();
		ModelAndView modelAndView = new ModelAndView("student/getTopicByMes");
		List<TopicTheme> topicThemes = topicThemeService.getPageAllByMes(mes, 0, 20);
		int count = topicThemeService.getCountAllByMes(mes);
		List<Test> tests = testService.getAll();
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("topicThemes", topicThemes);
		modelAndView.addObject("sum", count);
		modelAndView.addObject("mes", mes);
		// 判断页码
		if (count <= 20)
			modelAndView.addObject("end", "1");
		else if (count % 20 != 0)
			modelAndView.addObject("end", count / 20 + 1);
		else
			modelAndView.addObject("end", count / 20);
		return modelAndView;
	}

	/**
	 * 跳转所有topic页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/allTopicTheme", "/allTopicTheme.html" })
	public ModelAndView allTopicTheme(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("student/allTopicTheme");
		List<TopicTheme> topicThemes = topicThemeService.getPageAll(0, 20);
		// for(TopicTheme t:topicThemes){
		/// System.out.println(t.getcId());
		// if(t.getcContent().length()>30){
		// t.setcContent(t.getcContent().replaceAll("</?[^>]+>", ""));
		// t.setcContent(t.getcContent().replaceAll("<a>\\s*|t|r|n</a>", ""));
		// if(t.getcContent().length()>30)
		// t.setcContent(t.getcContent().substring(0, 30));
		// }
		// }
		int count = topicThemeService.getCountAll();
		List<Test> tests = testService.getAll();
		modelAndView.addObject("tests", tests);
		modelAndView.addObject("topicThemes", topicThemes);
		modelAndView.addObject("sum", count);
		// 判断页码
		if (count <= 20)
			modelAndView.addObject("end", "1");
		else if (count % 20 != 0)
			modelAndView.addObject("end", count / 20 + 1);
		else
			modelAndView.addObject("end", count / 20);
		return modelAndView;
	}

	/**
	 * 获得实验分类id获取对应实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getTopicTheme" })
	public void getTopicTheme(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int userid = StringUtils.string2int(request.getSession().getAttribute("userid").toString().trim());
		List<TopicTheme> topicThemes = topicThemeService.getByLaunchIdAndExamId(userid, id);

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

			jo.put("cCommentNum", a.getcCommentNum());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 分页获得实验分类id获取对应实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getPageTopic" })
	public void getPageTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int i = 20 * (id - 1);
		List<TopicTheme> topicThemes = topicThemeService.getPageAll(i, i + 20);
		JSONArray json = new JSONArray();
		for (TopicTheme a : topicThemes) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getcTitle());
			jo.put("cContent", a.getcContent());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			jo.put("cCommentNum", a.getcCommentNum());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 分页获得实验title获取对应实验
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getPageTopicByMes" })
	public void getPageTopicByMes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mes = request.getParameter("mes").trim();
		int id = Integer.parseInt(request.getParameter("id"));
		int i = 20 * (id - 1);
		List<TopicTheme> topicThemes = topicThemeService.getPageAllByMes(mes, i, i + 20);
		JSONArray json = new JSONArray();
		for (TopicTheme a : topicThemes) {
			JSONObject jo = new JSONObject();
			jo.put("cId", a.getcId());
			jo.put("cTitle", a.getcTitle());
			jo.put("cContent", a.getcContent());
			jo.put("cCreateTime", DateUtils.dateToString(a.getcCreateTime()));
			jo.put("cCommentNum", a.getcCommentNum());
			json.put(jo);
		}
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 跳转提出问题页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/askQuestion", "/askQuestion.html" })
	public ModelAndView askQuestion(HttpServletRequest request) {
		List<Test> tests = testService.getAll();
		ModelAndView modelAndView = new ModelAndView("student/askQuestion");
		modelAndView.addObject("tests", tests);
		return modelAndView;
	}

	/**
	 * 判断验证码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("codeCheck")
	public void codeCheck(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code").trim();
		String codeSession = request.getSession().getAttribute("code").toString().trim();
		ResultJson json = new ResultJson();
		if (code.equals(codeSession)) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		writeResultJson(response, json);
	}

	/**
	 * 保存问题
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/askQuestion.action" })
	public ModelAndView askQuestionAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String experiment = request.getParameter("experiment");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userid = request.getSession().getAttribute("userid").toString().trim();
		ModelAndView modelAndView = new ModelAndView("student/askQuestion");
		PrintWriter out = response.getWriter();
		out.flush();
		TopicTheme topicTheme = new TopicTheme();
		topicTheme.setcContent(content);
		topicTheme.setcCreateTime(new Date());
		topicTheme.setcExperimentId(StringUtils.string2int(experiment));
		topicTheme.setcTitle(title);
		topicTheme.setcLaunchId(StringUtils.string2int(userid));
		int result = topicThemeService.insert(topicTheme);
		if (result == 0) {
			modelAndView.addObject("title", title);
			modelAndView.addObject("content", content);
			out.write("<script>alert('发表失败，请稍后再试。');</script>");
		} else {
			out.write("<script>alert('发表成功。');</script>");
		}
		List<Test> tests = testService.getAll();
		modelAndView.addObject("tests", tests);
		return modelAndView;
	}

}
