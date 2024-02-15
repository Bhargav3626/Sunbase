package com.sunbase.customer.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sunbase.customer.dao.impl.CustomerDAOImpl;
import com.sunbase.customer.model.Customers;

@WebServlet("/Sync")
public class Sync extends HttpServlet {
	
	CustomerList clist;
	CustomerDAOImpl cimpl;
	
	@Override
	public void init() throws ServletException {
		 clist = new CustomerList();
		 cimpl = new CustomerDAOImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String token = (String) session.getAttribute("adminUsername");
		
		 JSONArray listArray = clist.getCustomerListArray(token);
		 System.out.println(listArray);
		if (listArray != null && !listArray.isEmpty()) {
			System.out.println("inside");
			for (int i = 0; i < listArray.length(); i++) {
				JSONObject jsonObject = listArray.getJSONObject(i);
				
				String uuid = jsonObject.getString("uuid");
				String first_name = jsonObject.getString("first_name");
				String last_name = jsonObject.getString("last_name");
				String street = jsonObject.getString("street");
				String address = jsonObject.getString("address");
				String city = jsonObject.getString("city");
				String state = jsonObject.getString("state");
				String email = jsonObject.getString("email");
				String phone = jsonObject.getString("phone");

				Customers c = new Customers(uuid, first_name, last_name, street, address, city, state, email, phone);
				Customers existingCustomer = cimpl.getCustomerById(uuid);
				if(existingCustomer!=null)
				{
					int rowsAffected = cimpl.updateCustomer(c);
//					if (rowsAffected > 0)
//		            {
//		            	// Call getAllCustomers to update the customer list
//		                List<Customers> updatedCustomersList = cimpl.getAllCustomers();
//		                // Set the updated customer list in the request
//		                req.setAttribute("searchResults", updatedCustomersList);
//		                // Customer updated successfully, redirect to home.jsp with success message
//		                req.getRequestDispatcher("home.jsp?successMessage=Customer updated successfully").forward(req, resp);
//		            }
				}
				else
				{
					cimpl.addCustomer(c);
					//req.getRequestDispatcher("HomeServlet").forward(req, resp);
				}
			}
		}
		
			req.getRequestDispatcher("HomeServlet").forward(req, resp);
		
	}

}
