package com.example.spring.web.thedemoserver.Service;
import com.example.spring.web.thedemoserver.model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
 private static List<Todo> todos = new ArrayList<Todo>();
 private static int todoCount = 3;
 static {
	 todos.add(new Todo(1, "Soumen Samanta", "Learn Java Spring", new Date(), false));
	 todos.add(new Todo(2, "Soumen Samanta", "Learn Micro", new Date(), false));
 }
 public List<Todo> retrieveTodos(String username){
	 List<Todo> filteredTodos = new ArrayList<Todo>();
	 for(Todo todo: todos) {
		 if(todo.getUser().equals(username)) {
			 filteredTodos.add(todo);
		 }
	 }
	 return filteredTodos;
 }
 public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
	 todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
 }
 public void deleteTodo(int id) {
	 Iterator<Todo> iterator = todos.iterator();
	 while(iterator.hasNext()) {
		 Todo todo = iterator.next();
		 if(todo.getId() == id) {
			 iterator.remove();
		 }
	 }
 }
}
