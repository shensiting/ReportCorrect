package org.gzhmc.report4gzhmc.controller.manage;

import org.gzhmc.report4gzhmc.mapper.StudentGradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author stShen
 */
@Controller
@RequestMapping("/student")
public class StudentManageController {

	@Autowired
	StudentGradeMapper studentGradeMapper;
	
	@RequestMapping("/studentManage")
	public ModelAndView FirstManage() {
		return new ModelAndView("/user/studentManage");
	}
}
