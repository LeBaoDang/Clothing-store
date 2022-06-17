package com.poly.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.PreUpdate;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.AccountDAO;
import com.poly.dao.OrderDAO;
import com.poly.entity.Account;
import com.poly.service.MailerService;
import com.poly.service.ParamService;
import com.poly.service.SessionService;

@Controller
public class AccountController {
	@Autowired
	AccountDAO dao;
	@Autowired
	SessionService session;
	@Autowired
	ParamService paramService;
	@Autowired
	ServletContext app;
	@Autowired
	MailerService mailer;
	@Autowired
	OrderDAO daoo;
	Account account = new Account();

	@GetMapping("/account/login")
	public String login() {
		return "account/login";
	}

	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		Account account = dao.findById(username).orElse(null);
		try {
			Account user = dao.getOne(username);
			if (!user.getPassword().equals(password)) {
				model.addAttribute("message", "Invalid password");
			} else {
				String uri = session.get("security-uri");
				if (uri != null) {
					return "redirect:" + uri;
				} else {
					if (user.isAdmin()) {
						session.set("username", user.getUsername());
						model.addAttribute("message", "Login successfylly");
						return "redirect:/category/index";
					} else {
						session.set("username", user.getUsername());
						model.addAttribute("message", "Login successfylly");
						return "redirect:/home/index";
					}
				}
			}
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username");
		}
		return "account/login";
	}

	// xét quyền truy cập để hiển thị lại hệ thống menu
	@GetMapping("/logout/{username}")
	public String logout(Model model, @PathVariable("username") String username) {
		Account ac = dao.getOne(username);
		if (ac.isAdmin()) {
			session.set("username", ac.getUsername());
			model.addAttribute("message", "Login successfylly");
			return "redirect:/category/index";
		} else {
			session.set("username", ac.getUsername());
			model.addAttribute("message", "Login successfylly");
			return "redirect:/home/index";
		}
	}

	@RequestMapping("/accountuser/{username}")
	public String accountuser(Model model, @PathVariable("username") String username) {
		Account ac = dao.getOne(username);
		if (ac.isAdmin()) {
			ac.getUsername();
			ac.getPhoto();
			ac.getPassword();
			ac.getFullname();
			ac.getEmail();
			model.addAttribute("user", ac);
			return "/account/accountuser";
		} else {
			ac.getUsername();
			ac.getPhoto();
			ac.getPassword();
			ac.getFullname();
			ac.getEmail();
			model.addAttribute("user", ac);
			return "/account/accountuser";
		}
	}

	// quenmk
	@RequestMapping("quenmk")
	public String quenmk() {
		return "/account/quenmk";
	}

	@RequestMapping("quenmktv")
	public String quenmktv(Model model, @RequestParam("username") String username) {
		if (!dao.existsById(username)) {
			model.addAttribute("message", "Tài khoản này không tồn tại!");
		} else {
			Account ac = dao.getOne(username);
			String pass = ac.getPassword();
			String email = ac.getEmail();
			try {
				mailer.queue(email, "Lấy lại mật khẩu", pass);
				model.addAttribute("message", "Chúng tôi đã gửi mật khẩu vào email" + " " + email + " " + "của bạn");
				return "/account/quenmk";
			} catch (Exception e) {
				// TODO: handle exception
				return e.getMessage();
			}
		}
		return "/account/quenmk";
	}

	@RequestMapping("dangKi")
	public String Dangki() {
		return "/account/dangKi";
	}

	// quản lí profile
	@RequestMapping("/update/profile")
	public String UpdateProfile(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("passcu") String passcu,
			@RequestParam("xacnhanpas") String xacnhanpas, Account ac, BindingResult result,
			@RequestParam("photo") MultipartFile multipartFile) throws IOException {
		ac = dao.getOne(username);
		if (!ac.getPassword().equals(passcu)) {
			System.out.print("mật khẩu " + ac.getPassword());
			model.addAttribute("message", "mật khẩu không đúng!");
			return "/account/demo";
		} else if (!password.equalsIgnoreCase(xacnhanpas)) {
			model.addAttribute("message", "xác nhận mật khẩu không đúng!");
			return "/account/demo";
		} else {
			String filenameString = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String uploadDrString = "images/";
			ac.setPhoto(filenameString);
			ac.setPassword(password);
			dao.save(ac);
			paramService.save(multipartFile, uploadDrString);
			return "redirect:/accountuser/" + ac.getUsername();
		}
	}
}
