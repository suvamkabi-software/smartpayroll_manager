package gui;

import model.*;
import service.PayrollSystem;

import javax.swing.*;
import java.awt.*;

public class PayrollGUI extends JFrame {

    private JTextField nameField, idField, salaryField, hoursField, rateField;
    private JTextArea displayArea;

    private PayrollSystem payrollSystem;

    public PayrollGUI() {

        payrollSystem = new PayrollSystem();

        setTitle("Payroll Management System");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Name"));
        nameField = new JTextField(10);
        add(nameField);

        add(new JLabel("ID"));
        idField = new JTextField(10);
        add(idField);

        add(new JLabel("Monthly Salary (Full Time)"));
        salaryField = new JTextField(10);
        add(salaryField);

        add(new JLabel("Hours Worked (Part Time)"));
        hoursField = new JTextField(10);
        add(hoursField);

        add(new JLabel("Hourly Rate"));
        rateField = new JTextField(10);
        add(rateField);

        JButton addFullTime = new JButton("Add Full Time Employee");
        JButton addPartTime = new JButton("Add Part Time Employee");
        JButton removeEmployee = new JButton("Remove Employee");
        JButton showEmployees = new JButton("Show Employees");

        add(addFullTime);
        add(addPartTime);
        add(removeEmployee);
        add(showEmployees);

        displayArea = new JTextArea(10,40);
        add(new JScrollPane(displayArea));

        addFullTime.addActionListener(e -> addFullTimeEmployee());
        addPartTime.addActionListener(e -> addPartTimeEmployee());
        removeEmployee.addActionListener(e -> removeEmployee());
        showEmployees.addActionListener(e -> showEmployees());
    }

    private void addFullTimeEmployee() {

        String name = nameField.getText();
        int id = Integer.parseInt(idField.getText());
        double salary = Double.parseDouble(salaryField.getText());

        Employee emp = new FullTimeEmployee(name,id,salary);

        payrollSystem.addEmployee(emp);

        displayArea.append("Full Time Employee Added\n");
    }

    private void addPartTimeEmployee() {

        String name = nameField.getText();
        int id = Integer.parseInt(idField.getText());
        int hours = Integer.parseInt(hoursField.getText());
        double rate = Double.parseDouble(rateField.getText());

        Employee emp = new PartTimeEmployee(name,id,hours,rate);

        payrollSystem.addEmployee(emp);

        displayArea.append("Part Time Employee Added\n");
    }

    private void removeEmployee() {

        int id = Integer.parseInt(idField.getText());

        payrollSystem.removeEmployee(id);

        displayArea.append("Employee Removed\n");
    }

    private void showEmployees() {

        displayArea.setText("");

        for(Employee emp : payrollSystem.getEmployees()){
            displayArea.append(emp.toString()+"\n");
        }
    }
}
