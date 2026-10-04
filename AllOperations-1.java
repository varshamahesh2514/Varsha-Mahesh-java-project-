package EmployeeManagementSystem;

import java.sql.*;

public class AllOperations {
    public static final Connection c = DBConnection.getConnection();

    // 1. CREATE: Add Employee Record
    public static void addEmployee(int empId, String name, String department, double salary, String designation, String contact) {
        String query = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, empId);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setDouble(4, salary);
            ps.setString(5, designation);
            ps.setString(6, contact);
            
            ps.executeUpdate();
            System.out.println("Employee added successfully: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. READ: View All Employees
    public static void viewEmployees() {
        String query = "SELECT * FROM Employee";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            System.out.println("\n--- EMPLOYEE RECORDS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("emp_id") +
                                   " | Name: " + rs.getString("name") +
                                   " | Dept: " + rs.getString("department") +
                                   " | Salary: ₹" + rs.getDouble("salary") +
                                   " | Designation: " + rs.getString("designation") +
                                   " | Contact: " + rs.getString("contact_number"));
            }
            System.out.println("------------------------\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. UPDATE: Update Employee Salary and Designation
    public static void updateEmployee(int empId, double newSalary, String newDesignation) {
        String query = "UPDATE Employee SET salary = ?, designation = ? WHERE emp_id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setDouble(1, newSalary);
            ps.setString(2, newDesignation);
            ps.setInt(3, empId);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee ID " + empId + " updated successfully.");
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. DELETE: Remove Employee Record
    public static void deleteEmployee(int empId) {
        String query = "DELETE FROM Employee WHERE emp_id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, empId);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee ID " + empId + " deleted successfully.");
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to demonstrate CRUD operations
    public static void main(String[] args) {
        // Create (Add initial records)
        addEmployee(101, "Aarav Sharma", "Engineering", 75000.00, "Software Engineer", "9876543210");
        addEmployee(102, "Priya Patel", "Human Resources", 60000.00, "HR Manager", "9812345678");
        addEmployee(103, "Rohan Verma", "Finance", 68000.00, "Financial Analyst", "9765432109");

        // Read (View records)
        viewEmployees();

        // Update (Modify salary and designation)
        updateEmployee(101, 85000.00, "Senior Software Engineer");

        // Delete (Remove a record)
        deleteEmployee(103);

        // View final list
        viewEmployees();
    }
}
