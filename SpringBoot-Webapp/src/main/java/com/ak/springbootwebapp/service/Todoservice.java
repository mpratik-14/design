/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ak.springbootwebapp.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author studentadmin
 */

import com.ak.springbootwebapp.model.Todo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class Todoservice {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "saumik", "Learn Spring MVC", new Date(),false,"Spring","test@gmail.com",new Date()));
        todos.add(new Todo(2, "saumik", "Learn Maven", new Date(), false,"Maven","test@gmail.com",new Date()));
        todos.add(new Todo(3, "saumik", "Learn AWS", new Date(),false,"AWS","test@gmail.com",new Date()));
    }

    @Cacheable("todoscache")
    public List<Todo> retrieveTodos(String user) {
    	System.out.println("calling retrieveTodos");
    	
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
    @Cacheable("todocache")
    public Todo retrieveTodo(int id) {
    	System.out.println("called retrieveTodo");
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    public void updateTodo(Todo todo){
    		todos.remove(todo);
    		todos.add(todo);
    }

    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }
    
    public void addTodo(String name, String desc, Date targetDate,boolean isDone,String tname,String email,Date endDate) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone,tname,email,endDate));
        
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
    
    public void deleteAll() {
    	todos.clear();
    }
    
	@CacheEvict(value = "todoscache", allEntries = true)
	public void removeTodos() {
		System.out.println("removed from todoscache");
	}
	
	@CacheEvict(value = "todocache", allEntries = true)
	public void removeTodo() {
		System.out.println("removed from todocache");
	}
}
