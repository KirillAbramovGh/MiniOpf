package com.netcracker.students.o3.model.model;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public interface Model
{
    void setOrders(final Map<BigInteger, Order> orders);

    void setTemplates(final Map<BigInteger, Template> templates);

    void setServices(final Map<BigInteger, Service> services);

    void setCustomers(final Map<BigInteger, Customer> customers);

    void setEmployers(final Map<BigInteger, Employee> employers);

    void setAreas(final Map<BigInteger, Area> areas);



    BigInteger getLastId();

    void setLastId(final BigInteger lastId);

    BigInteger getNextId();



    BigInteger createCustomer(String name, String login, String password, BigInteger areaId);

    BigInteger createEmployee(String name, String login, String password);

    BigInteger createOrder(BigInteger templateId, BigInteger serviceId,
            OrderStatus status, OrderAction action);

    BigInteger createTemplate(String name, BigDecimal cost, String description);

    BigInteger createService(BigInteger userId, BigInteger templateId, ServiceStatus status);

    BigInteger createArea(String name, String description);



    Map<BigInteger, Order> getOrders();

    Map<BigInteger, Template> getTemplates();

    Map<BigInteger, Service> getServices();

    Map<BigInteger, Customer> getCustomers();

    Map<BigInteger, Employee> getEmployees();

    Map<BigInteger, Area> getAreas();



    Order getOrderById(BigInteger id);

    Template getTemplateById(BigInteger id);

    Service getServiceById(BigInteger id);

    Customer getCustomerById(BigInteger id);

    Employee getEmployeeById(BigInteger id);

    Area getAreaById(BigInteger id);



    void addOrder(Order order);

    void addService(Service service);

    void addTemplate(Template template);

    void addCustomer(Customer customer);

    void addEmployee(Employee employee);

    void addArea(Area area);



    void deleteOrderById(BigInteger id);

    void deleteTemplateById(BigInteger id);

    void deleteServiceById(BigInteger id);

    void deleteCustomerById(BigInteger id);

    void deleteEmployeeById(BigInteger id);

    void deleteAreaById(BigInteger id);



    void setOrder(Order order);

    void setTemplate(Template template);

    void setService(Service service);

    void setCustomer(Customer customer);

    void setEmployee(Employee employee);

    void setArea(Area area);

}
