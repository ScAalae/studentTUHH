package com.spring.app.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.app.dao.StudentDao;
import com.spring.app.pojo.Student;



@Controller
@RequestMapping(value="/home")
public class CrudController {

    private static Logger logger = LogManager.getLogger(CrudController.class);

	@Autowired
	private StudentDao studentDao;
	
	
	@RequestMapping(value = "/read")
	public ModelAndView readStudent(ModelAndView model) throws IOException {
	
		List<Student> listStudent = studentDao.read();
		model.addObject("listStudent", listStudent);
		model.setViewName("read");
		
		
		logger.debug("-------------Liste Angezeigt----------");
		
		
		return model;
	}
	
	
	//////////////////////////////CREATE/////////////////////////////////////////////////////
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createStudent(ModelAndView mv) {
	
		mv.setViewName("create");
		
		return mv;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createStudent(@RequestParam("name") String name, @RequestParam("email") String email,
		@RequestParam("course") String course) {
		
		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setCourse(course);
		
		studentDao.create(student);
		logger.debug("-------------Student hinzugefügt----------");
	
		
		return "redirect:/home/read/";
	}
	
	////////////////////////UPDATE///////////////////////////////////////////////////////////////
	@RequestMapping(value = "/update/{studentId}", method = RequestMethod.GET)
	public ModelAndView findStudentById(ModelAndView model, @PathVariable("studentId") int studentId)
	throws IOException {
	
		List<Student> listStudent = studentDao.findStudentById(studentId);
		model.addObject("listStudent", listStudent);
		model.setViewName("update");
		
		return model;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateStudent(@RequestParam("id") int id, @RequestParam("name") String name,
	@RequestParam("email") String email, @RequestParam("course") String course, ModelAndView mv) {
	
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setEmail(email);
		student.setCourse(course);
		
		studentDao.update(student);
		logger.debug("-------------Update gemacht----------");
		return "redirect:/home/read/";
	}
	
	
	///////////////////////////////////DELETE///////////////////////////////////////////////////////
	@RequestMapping(value = "/delete/{studentId}")
	public String deleteStudentById(ModelAndView mv, @PathVariable("studentId") int studentId)
	throws IOException {
	
		studentDao.delete(studentId);
		logger.debug("-------------Student gelöscht----------");
		return "redirect:/home/read/"; 
	}
}
