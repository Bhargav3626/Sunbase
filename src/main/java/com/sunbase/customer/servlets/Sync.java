package com.sunbase.customer.servlets;

import java.io.IOException;

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
		if (listArray != null) {
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
				cimpl.addCustomer(c);

			}
			req.setAttribute("message", "Customers Synced Successfully. ");
			req.getRequestDispatcher("HomeServlet").forward(req, resp);

		}
	}

}
