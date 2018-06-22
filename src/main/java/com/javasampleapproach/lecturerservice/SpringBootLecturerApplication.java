package com.javasampleapproach.lecturerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootLecturerApplication {

	@Autowired
	BeanPropertyInfo beanPropertyInfo;

	@Autowired
	DBUtil dbUtil;

	@RequestMapping(value = "/")
	public String welcome() {

		return "Welcome To Lecturer Service!" + beanPropertyInfo.toString();
	}
	@RequestMapping(value = "/{name}")
	public String getname( @PathVariable("name") String name) {

		EmployeeDAO e=dbUtil.getEmployee(name);
		if (null !=e ){
			return "employee id ="+e.getId();
		}
		return "old response " + beanPropertyInfo.toString();
	}

	@RequestMapping(value = "/add/{name}")
	public String addname( @PathVariable("name") String name) {

		int ii=dbUtil.addEmployee(new EmployeeDAO(0,name));
		return "added and new id="+ii;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLecturerApplication.class, args);
	}
}