package EmployeeManagementSystem;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) throws Exception {
        Connection c = DBConnection.getConnection();

        String createEmployeeTable = """
            CREATE TABLE IF NOT EXISTS Employee (
                emp_id INT PRIMARY KEY,
                name VARCHAR(100),
                department VARCHAR(100),
                salary DOUBLE,
                designation VARCHAR(100),
                contact_number VARCHAR(15)
            );
            """;

        Statement s = c.createStatement();
        s.executeUpdate(createEmployeeTable);

        System.out.println("Employee Table Created Successfully");
    }
}
