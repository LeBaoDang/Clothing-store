package com.poly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.Item;
import com.poly.dao.AccountDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.ProductDAO;
import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.entity.Product;
import com.poly.service.SessionService;
import com.poly.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	@Autowired
	ShoppingCartService cart; // 1. tiêm Spring Bean đã viết ở bài trước
	@Autowired
	SessionService session;
	@Autowired
	AccountDAO accountDao;
	@Autowired
	OrderDAO orderDao;
	@Autowired
	ProductDAO productDao;


	// 2. xem giỏ hàng
	@RequestMapping("/cart/view")
	public String view(Model model) {
		model.addAttribute("Total", cart.getAmount());
		model.addAttribute("cart", cart);
		return "cart/index"; // 3. view lên trang giỏ hảng index
	}

	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		cart.add(id);
		return "redirect:/cart/view"; // hiển thị giỏ hàng
	}

	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return "redirect:/cart/view";
	}

	@RequestMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, @RequestParam("qty") Integer qty) {
		cart.update(id, qty);
		return "redirect:/cart/view";
	}

	@RequestMapping("/cart/clear")
	public String clear() {
		cart.clear();
		return "redirect:/cart/view";
	}


	// xem chi tiết
	@RequestMapping("/cart/chitiet/{id}")
	public String chitiet(@PathVariable("id") Integer id,Model model) {
		List<Product> PR = productDao.product(id);
		model.addAttribute("items",PR);
		return "/home/xemchitiet";
	}

	@RequestMapping("/cart/checkout")
	public String checkout() {
		// lấy username để login
		String username = session.get("username");
		if (username == null) {
			return "redirect:/account/login";
		}
		Account currentAcc = null;
		// nếu username khớp thì tt xử lí
		if (accountDao.findById(username).isPresent()) {
			currentAcc = accountDao.findById(username).get();
			Order ord = new Order();
			ord.setAccount(currentAcc);
			ord.setCreateDate(new Date());
			ord.setAddress("123");
			List<OrderDetail> details = new ArrayList<>();
			// duyệt tất cả sp có trong giỏ
			for (Item item : cart.getItems()) {
				OrderDetail od = new OrderDetail();
				od.setOrder(ord);
				productDao.findById(item.getId()).ifPresent(p -> {
					od.setProduct(p);
				});
				od.setPrice(item.getPrice());
				od.setQuantity(item.getQty());
				details.add(od);
			}
			ord.setOrderDetails(details);
			orderDao.save(ord);
			cart.clear();
		}

		return "redirect:/cart/view";
	}

}
