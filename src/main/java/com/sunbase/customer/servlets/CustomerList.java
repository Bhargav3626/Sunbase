package com.sunbase.customer.servlets;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerList {

	public JSONArray getCustomerListArray(String bearerToken) {

		// Specify the customer list endpoint URL
		// String bearerToken = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";
		String customerListUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

		try {
			// Create a URL object from the customer list endpoint URL
			URL url = new URL(customerListUrl);

			// Open a connection to the URL
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request method to GET
			connection.setRequestMethod("GET");

			// Set the authorization header with the bearer token
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

			// Get the response code from the connection
			int responseCode = connection.getResponseCode();

			// Check if the response code indicates success (200 OK)
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Read the response from the connection input stream
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				// Parse JSON response
				JSONArray jsonArray = new JSONArray(response.toString());
				return jsonArray;

			} else {
				// Print an error message if fetching the customer list fails
				System.out.println("Failed to fetch customer list. Response code: " + responseCode);
			}
		} catch (Exception e) {
			// Print any exceptions that occur during the authentication process
			e.printStackTrace();
		}
		return null;
	}

}