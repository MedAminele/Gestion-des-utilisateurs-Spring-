package org.dsi.Controller;

import java.util.List;

import javax.persistence.Index;

import org.dsi.dao.Repo.UserRepository;
import org.dsi.entities.User;
import org.dsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService service;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	// w
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	// show login page
//    @PostMapping("/doLogin")
//    public String showLoginPage (){
//       
//        return "login";
//        
//    }
	// process login page
//    @PostMapping("/process_login")
//    public String processLogin(User user) {
//    	
//    	
//        return "login_success";
//    }
	// deleting user
	@GetMapping("/delete")
	public String delete(Long id, String keyword) {
		userRepo.deleteById(id);
		return "redirect:/users";
	}

	// edit user
	@GetMapping("/edit")
	public String edit(Model model, Long id) {
		User user = userRepo.findById(id).get();
		if (user == null)
			throw new RuntimeException("introuvable");
		model.addAttribute("user", user);

		return "edit";

	}

	// page edit user
	@PostMapping("/save")
	public String save(Model model, User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		// if (bindingResult.hasErrors()) return "index";
//    	System.out.println(user);
//    	System.out.println(model);
		User res = userRepo.saveAndFlush(user);
		System.out.println(res);
		List<User> list = service.getAllUsers();
		model.addAttribute("listUsers", list);
		return "users";

	}

	// Keyword
//    @GetMapping("/Keyword")
//    public String patients(Model model,
//			   	 @RequestParam(name = "page" ,defaultValue = "0") int page,
//				    @RequestParam(name = "size" ,defaultValue = "5") int size,
//                    @RequestParam(name = "keyword" ,defaultValue = "") String keyword){
//        Page<User> pageUser=userRepo.findByNomContains(keyword,PageRequest.of(page, size));
//        model.addAttribute("listUsers", pageUser.getContent());
//        model.addAttribute("currentPage",page);
//        model.addAttribute("keyword",keyword);
//        
//        return "users";
//    }
	// Keyword

	@GetMapping("/Keyword")
	public String home(Model model, String keyword) {

		System.out.println(keyword);
		if (keyword != null) {
			List<User> list = service.getByKeyword(keyword);
			System.out.println(list.size());
			model.addAttribute("listUsers", list);
		} else {
			List<User> list = service.getAllUsers();
			model.addAttribute("listUsers", list);
		}
		return "users";
	}

}
