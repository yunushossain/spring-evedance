package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.ProductModel;
import com.example.repository.ProductRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/")
	public ModelAndView index() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProductModel> productList = (List<ProductModel>) productRepository.findAll();
		map.put("productList", productList);
		return new ModelAndView("index", "data", map);
	}
	
	
	
	@GetMapping("/product_create")
	public ModelAndView create() {
		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("create_product", "data", map);
	}
	
	@PostMapping("/product_save")
	public ModelAndView save(@ModelAttribute ProductModel productModel, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			productModel = productRepository.save(productModel);
			map.put("product", productModel);
			map.put("status", "Success");
			map.put("message", "Data saved successfully");
		} catch (Exception e) {
			map.put("status", "Failed");
			map.put("message", e.getLocalizedMessage());
		}
		return new ModelAndView("create_product", "data", map);
	}
	
	
	@GetMapping("/product_edit/{id}")
	public ModelAndView Edit(@PathVariable(value = "id") long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProductModel product =  productRepository.findById(id).get();
		map.put("product", product);
		return new ModelAndView("edit_product", "data", map);
	}
	
	
	
	@PostMapping("/product_update")
	public ModelAndView update(@ModelAttribute ProductModel productModel, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			productModel = productRepository.save(productModel);
			map.put("product", productModel);
			map.put("status", "Success");
			map.put("message", "Data updated successfully");
		} catch (Exception e) {
			map.put("status", "Failed");
			map.put("message", e.getLocalizedMessage());
		}
		return new ModelAndView("edit_product", "data", map);
	}

	@GetMapping("/product_delete/{id}")
	public ModelAndView delete(@PathVariable(value = "id") long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ProductModel product =  productRepository.findById(id).get();
			productRepository.delete(product);
			map.put("message", "Delete successful");
			
		} catch (Exception e) {
			
		}
		return new ModelAndView("redirect:/");
	}
}
