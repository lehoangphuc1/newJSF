package com.demo.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "employee")
@RequestScoped

public class Employee {
	private int id;
	private String name;
	private String email;
	private String address;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Employee() {
	}

	private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public ArrayList<Employee> getGet_all_employee() throws Exception {
		ArrayList<Employee> list_of_employees = new ArrayList<Employee>();
		Connection connection = null;
		try {
			DB_connection obj_DB_connection = new DB_connection();
			connection = obj_DB_connection.get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from employee");
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setAddress(rs.getString("address"));
				employee.setPhone(rs.getString("phone"));
				list_of_employees.add(employee);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return list_of_employees;
	}

	public void add_Employee() {
		try {
			Connection connection = null;
			DB_connection obj_DB_connection = new DB_connection();
			connection = obj_DB_connection.get_connection();
			PreparedStatement ps = connection.prepareStatement("insert into employee(name,email,address,phone) values('"
					+ name + "','" + email + "','" + address + "','" + phone + "')");
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public String update_employee() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		try {
			DB_connection obj_DB_connection = new DB_connection();
			Connection connection = obj_DB_connection.get_connection();
			PreparedStatement ps = connection
					.prepareStatement("update employee set name=?,email=?,address=?,phone=? where id=?");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, address);
			ps.setString(4, phone);
			ps.setString(5, String.valueOf(id));
			System.out.println(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "/index.xhtml?faces-redirect=true";
	}

	public String delete_Employee() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String field_id = params.get("action");
		try {
			DB_connection obj_DB_connection = new DB_connection();
			Connection connection = obj_DB_connection.get_connection();
			PreparedStatement ps = connection.prepareStatement("delete from employee where id=?");
			ps.setString(1, field_id);
			System.out.println(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "/index.xhtml?faces-redirect=true";
	}

}
