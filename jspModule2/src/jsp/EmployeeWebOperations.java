package jsp;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.*;
import com.netcracker.students.o3.controller.sorters.*;
import com.netcracker.students.o3.controller.sorters.SortType.*;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class EmployeeWebOperations {
    private BigInteger employeeId;
    private Controller controller;
    private HtmlTableBuilder tableBuilder;
    private SearcherService searcherService;
    private SearcherTemplates searcherTemplates;
    private SearcherCustomer searcherCustomer;
    private SearcherOrders searcherOrders;
    private SearcherArea searcherArea;
    private SearcherEmployee searcherEmployee;


    public EmployeeWebOperations() {
        controller = ControllerImpl.getInstance();
        tableBuilder = HtmlTableBuilder.getInstance();
        searcherService = SearcherService.getInstance();
        searcherArea = SearcherArea.getInstance();
        searcherOrders = SearcherOrders.getInstance();
        searcherTemplates = SearcherTemplates.getInstance();
        searcherCustomer = SearcherCustomer.getInstance();
        searcherEmployee = SearcherEmployee.getInstance();
    }

    public void start(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public String getFIO() {
        return controller.getEmployee(employeeId).getName();
    }

    public String getLogin() {
        return controller.getEmployee(employeeId).getLogin();
    }

    public String getPassword() {
        return controller.getEmployee(employeeId).getPassword();
    }

    public void changeNameAndPassword(String name, String password) {
        if (!getFIO().equals(name)) {
            changeName(name);
        }
        if (!getPassword().equals(password)) {
            changePassword(password);
        }
    }

    private void changeName(String name) {
        if (isNotNullOrEmpty(name)) {
            controller.getEmployee(employeeId).setName(name);
        }
    }

    private void changePassword(String password) {
        if (isNotNullOrEmpty(password)) {
            controller.getEmployee(employeeId).setPassword(password);
        }
    }

    public String showEmployeeOrders(String search, String field, OrderSortType sortOrders) {
        List<Order> orders;
        if(isNotNullOrEmpty(search)){
            orders = searcherOrders.search(search,field,getEmployeeOrders());
        }else {
            orders = getEmployeeOrders();
        }

        if(sortOrders!=null) {
            OrderSorter.getInstance().sort(orders, sortOrders);
        }

        return tableBuilder.createOrdersTable(orders);
    }

    public String showAllOrders(String search,String field,OrderSortType sortOrders) {
        List<Order> orders;
        if(isNotNullOrEmpty(search)){
            orders = searcherOrders.search(search,field,controller.getOrders());
        }else {
            orders = controller.getOrders();
        }

        if(sortOrders!=null){
            OrderSorter.getInstance().sort(orders,sortOrders);
        }

        return tableBuilder.createOrdersTable(orders);
    }

    public String showAllServices(String search, String field, ServiceSortType sortService) {
        List<Service> services;
        if(isNotNullOrEmpty(search)){
            services = searcherService.search(search,field,controller.getServices());
        }else {
            services = controller.getServices();
        }

        if(sortService!=null){
            ServiceSorter.getInstance().sort(services,sortService);
        }
        return tableBuilder.createEmployeeServicesTable(services);
    }

    public String showAllTemplates(String search, String field, TemplateSortType sortTemplates) {
        List<Template> templates;
        if(isNotNullOrEmpty(search)){
            templates = searcherTemplates.search(search,field,controller.getTemplates());
        }else {
            templates = controller.getTemplates();
        }

        if(sortTemplates!=null){
            TemplateSorter.getInstance().sort(templates,sortTemplates);
        }
        return tableBuilder.createEmployeeTemplatesTable(templates);
    }

    public String showAllCustomers(String search, String field, CustomerSortType sortCustomers) {
        List<Customer> customers ;
        if(isNotNullOrEmpty(search)){
            customers = searcherCustomer.search(search,field,controller.getCustomers());
        }else {
            customers = controller.getCustomers();
        }

        if(sortCustomers!=null){
            CustomerSorter.getInstance().sort(customers,sortCustomers);
        }
        return tableBuilder.createCustomersTable(customers);
    }

    public String showAllEmployees(String search, String field, EmployeeSortType sortEmployees) {
        List<Employee> employees  ;
        if(isNotNullOrEmpty(search)){
            employees = searcherEmployee.search(search,field,controller.getEmployers());
        }else {
            employees = controller.getEmployers();
        }

        if(sortEmployees!=null){
            EmployeeSorter.getInstance().sort(employees,sortEmployees);
        }
        return tableBuilder.createEmployeesTable(employees);
    }

    public String showAllAreas(String search,String field, AreaSortType sortAreas) {
        List<Area> areas ;
        if(isNotNullOrEmpty(search)){
            areas = searcherArea.search(search,field,controller.getAreas());
        }else {
            areas = controller.getAreas();
        }

        if(sortAreas!=null){
            AreaSorter.getInstance().sort(areas,sortAreas);
        }
        return tableBuilder.createAreasTable(areas);
    }


    public void startOrder(BigInteger orderId){
         controller.startOrder(orderId,employeeId);
    }

    private List<Order> getEmployeeOrders() {
        return controller.getOrdersByEmployeeId(employeeId);
    }

    private boolean isNotNullOrEmpty(String value) {
        return value != null && !value.replaceAll(" ", "").isEmpty();
    }

    public void resumeOrder(BigInteger orderId) {
        controller.resumeOrder(orderId);
    }

    public void completeOrder(BigInteger orderId) {
        controller.completeOrder(orderId);
    }

    public void deleteTemplate(BigInteger templateId) {
        controller.deleteTemplate(templateId);
    }

    public void deleteCustomer(BigInteger customerId) {
        controller.deleteCustomer(customerId);
    }

    public void deleteEmployee(BigInteger employeeId) {
        controller.deleteEmployee(employeeId);
    }

    public void deleteArea(BigInteger areaId) {
        controller.deleteArea(areaId);
    }
}
