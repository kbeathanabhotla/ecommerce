package com.crossover.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossover.ecommerce.domain.Customer;
import com.crossover.ecommerce.domain.CustomerOrder;
import com.crossover.ecommerce.domain.Product;
import com.crossover.ecommerce.service.Cart;
import com.crossover.ecommerce.service.CategoryService;
import com.crossover.ecommerce.service.CustomerService;
import com.crossover.ecommerce.service.OrderService;
import com.crossover.ecommerce.service.OrderedProductService;
import com.crossover.ecommerce.service.ProductService;
import com.crossover.ecommerce.util.LogUtil;
import com.crossover.ecommerce.util.RegionHashMap;

@Controller
public class FrontStoreController {

	@Autowired
	private Cart cart;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderedProductService orderedProductService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap mm) {
		mm.put("categoryList", categoryService.getAll());
		return "front_store/home";
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(@RequestParam("id") Byte id, ModelMap mm) {
		mm.put("productList", productService.getByCategoryId(id));
		mm.put("categoryList", categoryService.getAll());
		mm.put("id", id);
		return "front_store/category";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(ModelMap mm) {
		Map<Product, Integer> itemMap = cart.getItems();
		double subTotal = cart.calculateSubTotal();
		Integer numOfItems = cart.sumQuantity();
		mm.put("itemMap", itemMap);
		mm.put("numOfItems", numOfItems);
		mm.put("subTotal", subTotal);
		return "front_store/cart";
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public @ResponseBody String addToCart(@RequestParam("id") Integer id) {
		Product product = productService.getById(id);
		cart.addItem(product);
		return cart.sumQuantity().toString();
	}

	@RequestMapping(value = "/clearCart", method = RequestMethod.GET)
	public String clearCart() {
		cart.clear();
		return "redirect:/cart";
	}

	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public String updateCart(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {
		cart.updateItem(id, quantity);
		return "redirect:/cart";
	}

	/**
	 * Checkout
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(Customer customer, ModelMap mm) {
		double subTotal = cart.calculateSubTotal();
		if (subTotal == 0) {
			mm.put("subTotal", subTotal);
		} else {
			double total = cart.calculateTotal(subTotal);
			RegionHashMap regionHashMap = new RegionHashMap();
			Map<String, String> regions = regionHashMap.getCityRegion();
			mm.put("subTotal", subTotal);
			mm.put("total", total);
			mm.put("regions", regions);
		}
		return "front_store/checkout";
	}

	@RequestMapping(value = "/purchaseGuest", method = RequestMethod.POST)
	public String purchaseGuest(@ModelAttribute("customer") Customer customer, ModelMap mm) {
		// 1. Save customer (customer)
		Integer customerId = customerService.save(customer);

		// 2. Save order (customer_order)
		double subTotal = cart.calculateSubTotal();
		double total = cart.calculateTotal(subTotal);
		CustomerOrder order = orderService.save(customerId, total);

		// 3. Save order details (ordered_product)
		Map<Product, Integer> itemMap = new HashMap<Product, Integer>(cart.getItems());
		orderedProductService.save(order, itemMap);

		mm.put("subTotal", subTotal);
		mm.put("total", total);
		mm.put("order", order);
		mm.put("customer", customer);
		mm.put("itemMap", itemMap);

		cart.clear();
		return "front_store/confirmation";
	}

	@RequestMapping(value = "/purchaseMember", method = RequestMethod.POST)
	public String purchaseMember(@RequestParam("email") String email, ModelMap mm) {
		// 1. Get id
		Customer customer = customerService.getByEmail(email);
		Integer customerId = customer.getId();

		// 2. Save order (customer_order)
		double subTotal = cart.calculateSubTotal();
		double total = cart.calculateTotal(subTotal);
		CustomerOrder order = orderService.save(customerId, total);

		// 3. Save order details (ordered_product)
		Map<Product, Integer> itemMap = new HashMap<Product, Integer>(cart.getItems());
		orderedProductService.save(order, itemMap);

		mm.put("subTotal", subTotal);
		mm.put("total", total);
		mm.put("order", order);
		mm.put("customer", customer);
		mm.put("itemMap", itemMap);

		cart.clear();
		return "front_store/confirmation";
	}

	/**
	 * AJAX service
	 */
	@RequestMapping(value = "/validateMember", method = RequestMethod.POST)
	@ResponseBody
	public String validateMember(@RequestParam("email") String email) {
		Customer customer = customerService.getByEmail(email);
		if (customer != null) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "/getCartSize", method = RequestMethod.GET)
	@ResponseBody
	public String getCartSize() {
		return cart.sumQuantity().toString();
	}

}
