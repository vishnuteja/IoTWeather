package in.tvt.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.tvt.api.constants.URI;
import in.tvt.api.entity.Usr;
import in.tvt.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@Controller + @ResponseBody

@RestController
@RequestMapping(value=URI.USERS)
@Api(tags="Users")
public class UserController {

	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value="Find All Users", notes="Returns all the users in the app")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public List<Usr> findAll(){
		
		return service.findAll();
	
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	@ApiOperation(value="Find One By Id", notes="Returns the user info by id if it exists in the app")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="Not Found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public Usr findOne(@PathVariable("id") String id){
		
		return service.findOne(id);
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value="Create User", notes="Creates user with details")
	@ResponseBody
	@ApiResponses(value= {
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=400, message="Bad Request"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	
	public Usr create(@RequestBody Usr usr){
		
		System.out.println(usr);
		return service.create(usr);
	
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	@ApiOperation(value="Update User", notes="Update user details if email exists")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="Not Found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})

	public Usr update(@PathVariable("id") String id, @RequestBody Usr usr){
		
		return service.update(id, usr);
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	@ApiOperation(value="Delete User", notes="Delete user details when Id is specified")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="success"),
			@ApiResponse(code=404, message="Not Found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	
	public void delete(@PathVariable("id") String id){
		
		service.delete(id);
	
	}
}
