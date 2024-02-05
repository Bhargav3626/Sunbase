package com.sunbase.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbase.customer.dao.CustomerDAO;
import com.sunbase.customer.model.Customers;
import com.sunbase.customer.util.DatabaseUtil;

public class CustomerDAOImpl implements CustomerDAO {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private static final String INSERT_QUERY = "INSERT INTO Customers (`uuid`,`first_name`, `last_name`, `street`, `address`, `city`, `state`, `email`, `phone`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String UPDATE_QUERY = "UPDATE Customers SET `first_name`=?, `last_name`=?, `street`=?, `address`=?, `city`=?, `state`=?, `email`=?, `phone`=? WHERE `uuid`=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Customers";
    private static final String DELETE_QUERY = "DELETE FROM Customers WHERE `uuid`=?";
    
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Customers WHERE uuid LIKE ?";
    private static final String SELECT_BY_FIRST_NAME_QUERY = "SELECT * FROM Customers WHERE first_name LIKE ?";
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM Customers WHERE email LIKE ?";
    private static final String SELECT_BY_PHONE_QUERY =  "SELECT * FROM Customers WHERE phone LIKE ?";
    private static final String SELECT_BY_CITY_QUERY = "SELECT * FROM Customers WHERE city LIKE ?";
    private static final String SELECT_BY_STATE_QUERY = "SELECT * FROM Customers WHERE state LIKE ?";

    public CustomerDAOImpl() {
        try {
            connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addCustomer(Customers customer) {
        try {
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getFirst_name());
            preparedStatement.setString(3, customer.getLast_name());
            preparedStatement.setString(4, customer.getStreet());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getCity());
            preparedStatement.setString(7, customer.getState());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getPhone());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                return rowsAffected;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    @Override
    public int updateCustomer(Customers customer) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, customer.getFirst_name());
            preparedStatement.setString(2, customer.getLast_name());
            preparedStatement.setString(3, customer.getStreet());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getState());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getPhone());
            preparedStatement.setString(9, customer.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    @Override
    public List<Customers> getAllCustomers() {
        List<Customers> customersList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customers customer = new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return customersList;
    }

    @Override
    public List<Customers> getCustomersByFirstName(String firstName) {
        List<Customers> customersList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_FIRST_NAME_QUERY);
            preparedStatement.setString(1, "%" + firstName +"%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customers customer = new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customersList;
    }

    @Override
    public List<Customers> getCustomersByEmail(String email) {
        List<Customers> customersList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);
            preparedStatement.setString(1, "%" + email + "%" );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customers customer = new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customersList;
    }

    @Override
    public List<Customers> getCustomersByPhone(String phone) {
        List<Customers> customersList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_PHONE_QUERY);
            preparedStatement.setString(1, "%" + phone + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customers customer = new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customersList;
    }

    @Override
    public List<Customers> getCustomersByCity(String city) {
        List<Customers> customersList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_CITY_QUERY);
            preparedStatement.setString(1, "%" + city + "%" );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customers customer = new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customersList;
    }

    @Override
    public List<Customers> getCustomersByState(String state) {
        List<Customers> customersList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_STATE_QUERY);
            preparedStatement.setString(1, "%" + state + "%" );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customers customer = new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                customersList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customersList;
    }

    @Override
    public Customers getCustomerById(String uuid) {
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setString(1, uuid);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Customers(
                        resultSet.getString("uuid"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    @Override
    public int deleteCustomer(String uuid) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, uuid);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
