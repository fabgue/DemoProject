/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.au.springjtableintegration.web.controller;

import com.au.springjtableintegration.business.service.ExpenseService;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseBean;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseOptionsBean;
import com.au.springjtableintegration.web.dto.datatable.response.JsonJTableExpenseOptionsResponse;
import com.au.springjtableintegration.web.dto.datatable.response.JsonJTableExpenseResponse;
import com.au.springjtableintegration.web.dto.datatable.response.JsonJtableExpenseListResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The controller managing all the functionalities of the data table
 * 
 * @author Sanath Kodikara
 */
@Controller
public class DataTableController {
	@Autowired
	private ExpenseService expenseService;

	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "datatable";
	}

	/* Table data load - This loads the data for the table */
	@RequestMapping(value = "/datatable/getAllExpenses", method = RequestMethod.POST)
	@ResponseBody
	public JsonJtableExpenseListResponse getAllExpenses(
			@RequestParam int jtStartIndex, @RequestParam int jtPageSize) {
		JsonJtableExpenseListResponse jstr;
		List<JsonJTableExpenseBean> expList;
		try {
			int expenseCount = expenseService.getExpenseCount();
			expList = expenseService.listExpenses(jtStartIndex, jtPageSize);
			jstr = new JsonJtableExpenseListResponse("OK", expList,
					expenseCount);
		} catch (Exception e) {
			jstr = new JsonJtableExpenseListResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	/* Cascaded drop down part one - loads the categories */
	@RequestMapping(value = "/datatable/categories", method = RequestMethod.POST)
	public @ResponseBody
	JsonJTableExpenseOptionsResponse getCategories() {
		JsonJTableExpenseOptionsResponse jstr;
		List<JsonJTableExpenseOptionsBean> categories;
		try {
			categories = expenseService.listExpenseCategories();
			jstr = new JsonJTableExpenseOptionsResponse("OK", categories);
		} catch (Exception e) {
			jstr = new JsonJTableExpenseOptionsResponse("ERROR", e.getMessage());
		}
		return jstr;
	}

	/* Cascaded drop down part two - loads the sub-categories */
	@RequestMapping(value = "/datatable/subcategories", method = RequestMethod.POST)
	public @ResponseBody
	JsonJTableExpenseOptionsResponse getSubCategoryByCategory(
			@RequestParam(value = "categoryId", required = true) String categoryId) {
		JsonJTableExpenseOptionsResponse jstr;
		List<JsonJTableExpenseOptionsBean> subcategories;
		try {
			if (categoryId.equals("0")) {
				subcategories = expenseService.listExpenseSubCategories();
			} else {
				subcategories = expenseService
						.getSubCategoriesForCategory(categoryId);
			}
			jstr = new JsonJTableExpenseOptionsResponse("OK", subcategories);
		} catch (Exception e) {
			jstr = new JsonJTableExpenseOptionsResponse("ERROR", e.getMessage());
		}
		return jstr;
	}

	/* CRUD operation - Add the expense */
	@RequestMapping(value = "/datatable/addExpense", method = RequestMethod.POST)
	@ResponseBody
	public JsonJTableExpenseResponse insertGroup(
			@ModelAttribute JsonJTableExpenseBean expenseBean,
			BindingResult result) {
		JsonJTableExpenseResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new JsonJTableExpenseResponse("ERROR",
					"Form invalid");
		}
		try {
			expenseService.addExpense(expenseBean);
			jsonJtableResponse = new JsonJTableExpenseResponse("OK",
					expenseBean);
		} catch (Exception e) {
			jsonJtableResponse = new JsonJTableExpenseResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}

	/* CRUD operation - Update the expense */
	@RequestMapping(value = "/datatable/updateExpense", method = RequestMethod.POST)
	@ResponseBody
	public JsonJTableExpenseResponse updateRole(
			@ModelAttribute JsonJTableExpenseBean expenseBean,
			BindingResult result) {
		JsonJTableExpenseResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new JsonJTableExpenseResponse("ERROR",
					"Form invalid");
		}
		try {
			expenseService.updateExpense(expenseBean);
			jsonJtableResponse = new JsonJTableExpenseResponse("OK",
					expenseBean);
		} catch (Exception e) {
			jsonJtableResponse = new JsonJTableExpenseResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}

	/* CRUD operation - Delete the expense */
	@RequestMapping(value = "/datatable/deleteExpense", method = RequestMethod.POST)
	@ResponseBody
	public JsonJTableExpenseResponse delete(@RequestParam String ExpenseId) {
		JsonJTableExpenseResponse jsonJtableResponse;
		try {
			expenseService.removeExpense(new Integer(ExpenseId));
			jsonJtableResponse = new JsonJTableExpenseResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new JsonJTableExpenseResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}
}
