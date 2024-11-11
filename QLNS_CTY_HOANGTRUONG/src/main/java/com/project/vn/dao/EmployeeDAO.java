package com.project.vn.dao;

import com.project.vn.dto.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO extends AbstractDAO<Employee>{

    private static EmployeeDAO instance;

    public static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

    private EmployeeDAO() {
    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = """
                                UPDATE employee
                                SET isDeleted = 1
                                WHERE id = ?;
                             """;
                ps = cnt.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                cnt.close();
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public Boolean checkIsExistPhone(String phone,int id) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = """
                                SELECT COUNT(1) FROM employee WHERE phone = AND id<> ?
                             """;
                ps = cnt.prepareStatement(sql);
                ps.setString(1, phone);
                ps.setInt(2,id);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                cnt.close();
            }
            return true;
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    @Override
    public Boolean update(Employee emp) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = """
                                UPDATE employee
                                SET fullName = ?,
                                    address = ?,
                                    phone = ?,
                                    gender = ?,
                                    position = ?,
                                    department = ?
                                WHERE id = ?;
                             """;
                ps = cnt.prepareStatement(sql);
                ps.setString(1, emp.getFullName());
                ps.setString(2, emp.getAddress());
                ps.setString(3, emp.getPhone());
                ps.setString(4, emp.getGender());
                ps.setString(5, emp.getPosition());
                ps.setString(6, emp.getDepartment());
                ps.setInt(7, emp.getId());
                ps.executeUpdate();
                cnt.close();
            }
            return true;
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    @Override
    public Boolean add(Employee emp) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "INSERT INTO employee(fullName, address, phone, gender, position, department) VALUES (?,?,?,?,?,?);";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, emp.getFullName());
                ps.setString(2, emp.getAddress());
                ps.setString(3, emp.getPhone());
                ps.setString(4, emp.getGender());
                ps.setString(5, emp.getPosition());
                ps.setString(6, emp.getDepartment());
                ps.executeUpdate();
                cnt.close();
            }
            return true;
        } catch (Exception e) {

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public List<Employee> getAllEmployeNotHaveContract() throws Exception {
        String sql = """
                     SELECT e.*
                     FROM employee e
                     LEFT JOIN qlnsht.contract c on e.id = c.employeeId
                     WHERE c.id IS NULL;
                     """;
        List<Employee> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection cnt = DBConnect.getConnection();
        if (cnt != null) {
            ps = cnt.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                Employee item = new Employee();
                item.setId(rs.getInt("id"));
                item.setFullName(rs.getString("fullName"));
                item.setAddress(rs.getString("address"));
                item.setPhone(rs.getString("phone"));
                item.setGender(rs.getString("gender"));
                item.setPosition(rs.getString("position"));
                item.setIsDeleted(rs.getBoolean("isDeleted"));
                item.setDepartment(rs.getString("department"));
                list.add(item);
            }
            cnt.close();
        }
        if (ps != null) {
            ps.close();
        }
        return list;
    }

    @Override
    public List<Employee> getAll() throws Exception {
        List<Employee> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection cnt = DBConnect.getConnection();
        if (cnt != null) {
            String sql = "SELECT * FROM employee WHERE isDeleted = 0;";
            ps = cnt.prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()) {
                Employee item = new Employee();
                item.setId(rs.getInt("id"));
                item.setFullName(rs.getString("fullName"));
                item.setAddress(rs.getString("address"));
                item.setPhone(rs.getString("phone"));
                item.setGender(rs.getString("gender"));
                item.setPosition(rs.getString("position"));
                item.setIsDeleted(rs.getBoolean("isDeleted"));
                item.setDepartment(rs.getString("department"));
                list.add(item);
            }
            cnt.close();
        }
        if (ps != null) {
            ps.close();
        }
        return list;
    }

    public List<Employee> getAll(String textSearch) throws Exception {
        List<Employee> list = new ArrayList<>();
        PreparedStatement ps = null;
        Connection cnt = DBConnect.getConnection();
        if (cnt != null) {
            String sql = "SELECT * FROM employee WHERE isDeleted = 0 AND fullName LIKE ?";
            ps = cnt.prepareStatement(sql);
            ps.setString(1, "%" + textSearch + "%");
            var rs = ps.executeQuery();
            while (rs.next()) {
                Employee item = new Employee();
                item.setId(rs.getInt("id"));
                item.setFullName(rs.getString("fullName"));
                item.setAddress(rs.getString("address"));
                item.setPhone(rs.getString("phone"));
                item.setGender(rs.getString("gender"));
                item.setPosition(rs.getString("position"));
                item.setIsDeleted(rs.getBoolean("isDeleted"));
                item.setDepartment(rs.getString("department"));
                list.add(item);
            }
            cnt.close();
        }
        if (ps != null) {
            ps.close();
        }
        return list;
    }
}
