package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeHelper {

    public static String makeEmployeeTree() {

        // TODO, change this to use a single query operation to get all employees
        Employee employee = Employee.find(1); // root employee
        // and use this data structure to maintain reference information needed to build the tree structure

        Map<Long, List<Employee>> employeeMap = new HashMap<>();
        List<Employee> all = employee.all();

        //gets employees from one list and puts them into a hash map
        for(Employee employee1 : all){
            long reportsTo = employee1.getReportsTo();
            List employees = employeeMap.get(reportsTo);

            if(employees == null){ //check null
                employees = new ArrayList<>();
                employeeMap.put(employee1.getReportsTo(), employees);
            }
        employees.add(employee1);
        }

        return "<ul>" + makeTree(employee, employeeMap) + "</ul>";
    }

    // TODO - currently this method just usese the employee.getReports() function, which
    //  issues a query.  Change that to use the employeeMap variable instead
    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a><ul>";
        List<Employee> reports = employeeMap.get(employee.getEmployeeId());

        if(reports != null){ //check null
            for (Employee report : reports) {

                list += makeTree(report, employeeMap);
                //recursively call maketree
            }
        }
        return list + "</ul></li>";
    }
}
