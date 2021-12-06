package com.example.spring.web.thedemoserver.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.spring.web.thedemoserver.Service.TodoService;
import com.example.spring.web.thedemoserver.model.Todo;

@Controller
public class TodoController {
	@Autowired
	TodoService service;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	@RequestMapping(value ="/todo", method = RequestMethod.GET)
	public String getTodoPage(ModelMap model) {
		String userId = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(userId));
		model.addAttribute("todo", new Todo());
		return "Todo";
	}
	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,@Valid Todo todo, BindingResult result ) throws ParseException {
		String userId = getLoggedInUserName(model);
//		Date currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(todo.getTargetDate().toString());
		if(result.hasErrors()) {
			model.put("todos", service.retrieveTodos(userId));
			return "Todo";
		}
		
		service.addTodo(userId, todo.getDesc(), todo.getTargetDate(), todo.isDone());
		model.put("todos", service.retrieveTodos(userId));
		return "redirect:/todo";
	}
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	@RequestMapping(value = "/delete-todo", method=RequestMethod.GET)
	public String DeleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/todo";
	}
}
