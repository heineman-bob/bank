package Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.UserDAO;
import Entities.User;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
	  List<User> users = userDAO.findAll();
	  model.addAttribute("users", users);
	  return "index";
	}
}
