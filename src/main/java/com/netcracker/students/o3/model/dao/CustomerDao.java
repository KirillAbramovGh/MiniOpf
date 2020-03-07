package com.netcracker.students.o3.model.dao;

import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends AbstractDao<Customer>
{

    private static final String tableName = "customers";

    @Override
    public List<Customer> getAll() throws SQLException
    {
        List<Customer> customers = new ArrayList<>();

        System.out.println("CustomerDao.getAll() ");
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            String sqlReq = "select * from " + getTableName();
            try (ResultSet resultSet = statement.executeQuery(sqlReq))
            {
                String sqlServiceReq = "select id from services where userid=?";
                ResultSet serviceSet;
                for (Customer customer; resultSet.next(); )
                {
                    customer = new CustomerImpl();
                    customer.setId(BigInteger.valueOf(resultSet.getInt("id")));
                    customer.setName(resultSet.getString("name"));
                    if(customer.getName() == null){
                        continue;
                    }
                    customer.setLogin(resultSet.getString("login"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setMoneyBalance(resultSet.getBigDecimal("moneybalance"));
                    customer.setAreaId(BigInteger.valueOf(resultSet.getLong("areaid")));

                    try(PreparedStatement serviceStatement = connection.prepareStatement(sqlServiceReq))
                    {
                        serviceStatement.setLong(1,customer.getId().longValue());
                        serviceSet = serviceStatement.executeQuery();
                        for(BigInteger serviceId;serviceSet.next();){
                            serviceId = serviceSet.getBigDecimal("id").toBigInteger();
                            customer.addConnectedServiceId(serviceId);
                        }
                    }


                    customers.add(customer);
                }
            }
        }


        return customers;
    }

    @Override
    public Customer getEntityById(final BigInteger id) throws SQLException
    {
        System.out.println("customer getEntityByID");
        Customer customer = null;
        String sqlReq = "select * from " + getTableName() + " where id=?";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1,id.longValue());
            try (ResultSet resultSet = statement.executeQuery())
            {


                if (resultSet.next())
                {
                    customer = new CustomerImpl();
                    customer.setId(resultSet.getBigDecimal("id").toBigInteger());
                    customer.setName(resultSet.getString("name"));

                    if(customer.getName() == null){
                        return null;
                    }
                    customer.setLogin(resultSet.getString("login"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setMoneyBalance(resultSet.getBigDecimal("moneybalance"));
                    customer.setAreaId(BigInteger.valueOf(resultSet.getLong("areaid")));
                    String sqlServiceReq = "select id from services where userid=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sqlServiceReq);
                    preparedStatement.setLong(1,id.longValue());
                    ResultSet serviceSet = preparedStatement.executeQuery();

                    for(BigInteger serviceId;serviceSet.next();){
                        serviceId = serviceSet.getBigDecimal("id").toBigInteger();
                        customer.addConnectedServiceId(serviceId);
                    }
                    preparedStatement.close();
                    serviceSet.close();
                }
            }
        }

        return customer;
    }

    @Override
    public void update(final Customer entity) throws SQLException
    {
        System.out.println("customer update");
        String sqlReq =
                "update " + getTableName() + " set name=?, login=?, password=?, moneybalance=?, areaid=? where id=?";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setString(1,entity.getName());
            statement.setString(2,entity.getLogin());
            statement.setString(3,entity.getPassword());
            statement.setBigDecimal(4,entity.getMoneyBalance());
            statement.setLong(5,entity.getAreaId().longValue());
            statement.setLong(6,entity.getId().longValue());
            statement.executeUpdate();
        }

    }

    @Override
    protected String getTableName()
    {
        return tableName;
    }

    @Override
    public void create(final Customer entity) throws SQLException
    {
        System.out.println("customer create");
        String sqlReq = "INSERT INTO " + getTableName() + " VALUES (?,?,?,?,?,?)";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setLong(1,entity.getId().longValue());
            statement.setString(2,entity.getName());
            statement.setString(3,entity.getLogin());
            statement.setString(4,entity.getPassword());
            statement.setBigDecimal(5,entity.getMoneyBalance());
            statement.setLong(6,entity.getAreaId().longValue());
            statement.executeUpdate();
        }
    }

    public Customer getCustomerByLogin(String login) throws SQLException
    {
        System.out.println("customer getByLogin");
        Customer customer = null;
        String sqlReq = "select * from " + getTableName() + " where login=?";
        try (Connection connection = getConnection();PreparedStatement statement = connection.prepareStatement(sqlReq))
        {
            statement.setString(1,login);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    customer = new CustomerImpl();
                    customer.setId(resultSet.getBigDecimal("id").toBigInteger());
                    customer.setName(resultSet.getString("name"));

                    if(customer.getId() == null){
                        return null;
                    }
                    customer.setLogin(resultSet.getString("login"));
                    customer.setPassword(resultSet.getString("password"));
                    customer.setMoneyBalance(resultSet.getBigDecimal("moneybalance"));
                    customer.setAreaId(BigInteger.valueOf(resultSet.getLong("areaid")));
                    String sqlServiceReq = "select id from services where userid=?";
                    PreparedStatement serviceStatement = connection.prepareStatement(sqlServiceReq);

                    serviceStatement.setLong(1,customer.getId().longValue());
                    ResultSet serviceSet = serviceStatement.executeQuery();

                    for(BigInteger serviceId;serviceSet.next();){
                        serviceId = serviceSet.getBigDecimal("id").toBigInteger();
                        customer.addConnectedServiceId(serviceId);
                    }
                    serviceSet.close();
                }
            }
        }

        return customer;
    }


}
