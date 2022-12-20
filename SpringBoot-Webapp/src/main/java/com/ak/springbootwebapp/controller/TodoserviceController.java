/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ak.springbootwebapp.controller;
import com.ak.springbootwebapp.model.Todo;

import com.ak.springbootwebapp.service.Todoservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
@Slf4j
/**
 *
 * @author studentadmin
 */
public class TodoserviceController {
    @Autowired
	Todoservice service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = (String) model.get("name");
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}
	
	@RequestMapping(value = "/list-todos-new", method = RequestMethod.GET)
	public String showTodosNew(ModelMap model) {
		//log.debug("listing it");
		String name = (String) model.get("name");
		model.put("todos", service.retrieveTodos(name));
		return "list-todos-new";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String) model.get("name"),
				"Default Desc", new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value = "/add-todo-new", method = RequestMethod.GET)
	public String showAddTodoPageNew(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String) model.get("name"),
				"Default Desc", new Date(), false,"Default Name","Default Email",new Date()));
		return "todo-new";
	}
	

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		//log.info("Deleting todo id "+id);
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/delete-todo-new", method = RequestMethod.GET)
	public String deleteTodoNew(@RequestParam int id) {
		System.out.println("TODO being deleted for :"+id);
		//log.info("Deleting todo id "+id);
		if(id==0) {
			service.deleteAll();
		}else {
			service.deleteTodo(id);
		}
		service.removeTodos();
		return "redirect:/list-todos-new";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo-new", method = RequestMethod.GET)
	public String showUpdateTodoPageNew(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);		
		return "todo-new";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo,
			BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUser((String) model.get("name"));

		service.updateTodo(todo);

		return "redirect:/list-todos";
	}
	@RequestMapping(value = "/update-todo-new", method = RequestMethod.POST)
	public String updateTodoNew(ModelMap model, @Valid Todo todo,
			BindingResult result) {

		if (result.hasErrors()) {
			return "todo-new";
		}

		todo.setUser((String) model.get("name"));

		service.updateTodo(todo);
		service.removeTodos();

		return "redirect:/list-todos-new";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(),false);
		//service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(),false,todo.getName(),todo.getEmail(),todo.getEndDate());
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/add-todo-new", method = RequestMethod.POST)
	public String addTodoNew(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo-new";
		}

		//service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(),false);
		service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(),false,todo.getName(),todo.getEmail(),todo.getEndDate());
		service.removeTodos();
		return "redirect:/list-todos-new";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap model) {
		String name = (String) model.get("name");
		return "test";
	}

}
