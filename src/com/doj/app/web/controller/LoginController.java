/**
 * 
 */
package com.doj.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dinesh.Rajput
 *
 */
@Controller
public class LoginController {
	
	@GetMapping("/")
	public String home(){
		return "home";
	}
	
	@GetMapping(value="/login")//as of spring 4.3
	public String login() {
		return "login" ;
	}
}
