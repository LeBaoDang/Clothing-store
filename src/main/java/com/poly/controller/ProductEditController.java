package com.poly.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.AccountDAO;
import com.poly.dao.ProductDAO;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.ParamService;

@RequestMapping("product")
@Controller
public class ProductEditController {
	
	@Autowired // Tiêm Product vào
	ProductDAO dao;
	@Autowired
	ParamService paramService;
	
// làm việc với bảng Categories
	@GetMapping("index")
	public String showForm(Model model) {
		Product item = new Product();
		model.addAttribute("item", item);
		List<Product> items = dao.findAll();
		model.addAttribute("items", items);
		return "product/index";
	}

	@PostMapping("SaveOrUpdate")
	public String saveorupdate(@ModelAttribute("item") Product ac, BindingResult result, Model model,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String filenameString = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDrString = "images/";
		ac.setImage(filenameString);
		dao.save(ac);
		paramService.save(multipartFile, uploadDrString);
		model.addAttribute("item", new Product());
		return "redirect:/product/index";
	}
//EDIT
// hàm 4
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product item = dao.findById(id).get();
		model.addAttribute("item", item);
		List<Product> items = dao.findAll();
		model.addAttribute("items", items);
		return "product/index";
	}

	// hàm 5
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		dao.deleteById(id);
		return "redirect:/product/index";
	}
	
	@RequestMapping("/product/update")
	public String update(Product item) {
		dao.save(item);
		return "redirect:/product/index/" + item.getId();
	}

}
