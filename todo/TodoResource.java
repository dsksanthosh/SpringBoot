package restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
public class TodoResource {

	private TodoService todoservice;
	
	
	public TodoResource(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	@GetMapping("/basicAuth")
	public String basicAuthCheck() {	
		return "success";	
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		
		return todoservice.findByUsername(username);
		
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username,@PathVariable int id) {
		
		return todoservice.findById(id);
		
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Object> deleteTodo(@PathVariable String username,@PathVariable int id) {
		
		 todoservice.deleteById(id);
		 return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username,
											 @PathVariable int id,
											 @RequestBody Todo todo) {
		
		 todoservice.updateTodo(todo);
		 return todo;
		
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo addTodo(@PathVariable String username,@RequestBody Todo todo) {
		
		 Todo createdTodo=todoservice.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
		 return createdTodo;
		
	}
	
	
}
