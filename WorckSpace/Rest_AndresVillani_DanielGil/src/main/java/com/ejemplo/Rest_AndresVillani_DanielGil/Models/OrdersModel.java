package com.ejemplo.Rest_AndresVillani_DanielGil.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.Rest_AndresVillani_DanielGil.DBFactory.DBFactory;
import com.ejemplo.Rest_AndresVillani_DanielGil.Entities.Order;



public class OrdersModel {
    
    Connection conexion = null;

    public OrdersModel() throws SQLException {
	DataSource ds = DBFactory.getMySQLDataSource();
	conexion = ds.getConnection();
    }

    public Order read(Integer id) {
    Order ordenes = null;
	Statement sentencia = null;

	String sql = "SELECT `id`, "
			+ "`employee_id`, "
			+ "`customer_id`, "
			+ "`order_date`, "
			+ "`shipped_date`, "
			+ "`shipper_id`, "
			+ "`ship_name`, "
			+ "`ship_address`, "
			+ "`ship_city`, "
			+ "`ship_state_province`, "
			+ "`ship_zip_postal_code`, "
			+ "`ship_country_region`, "
			+ "`shipping_fee`, "
			+ "`taxes`, "
			+ "`payment_type`, "
			+ "`paid_date`, "
			+ "`notes`, "
			+ "`tax_rate`, "
			+ "`tax_status_id`, "
			+ "`status_id`"
			+ " FROM orders WHERE id=" + id;
	
	try {
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { 
	    	ordenes = new Order(
	    			rs.getInt("id"),
	    			rs.getInt("employee_id"),
	    			rs.getInt("customer_id"),
	    			rs.getDate("order_date"),
	    			rs.getDate("shipped_date"),
	    			rs.getInt("shipper_id"),
	    			rs.getString("ship_name"),
	    			rs.getString("ship_address"),
	    			rs.getString("ship_city"),
	    			rs.getString("ship_state_province"),
	    			rs.getString("ship_zip_postal_code"),
	    			rs.getString("ship_country_region"),
	    			rs.getBigDecimal("shipping_fee"),
	    			rs.getBigDecimal("taxes"),
	    			rs.getString("payment_type"),
	    			rs.getDate("paid_date"),
	    			rs.getString("notes"),
	    			rs.getDouble("tax_rate"),
	    			rs.getBoolean("tax_status_id"),
	    			rs.getBoolean("status_id"));
	    };
	    
	} catch (SQLException e) {
	    System.err.println("Error en read de Ordenes: " + e.getMessage());
	    return null;
	}

	return ordenes;
    }
    

    /**
     * 
     * @param ordenes
     * @return Devuelve el id del registro recien insertado
     */
    public Integer insert(Order ordenes) throws  SQLException {
	Integer id = null;
	PreparedStatement ps = null;
	String sql = "INSERT INTO orders ("+ "`employee_id`, "
			+ "`customer_id`, "+ "`order_date`, "+ "`shipped_date`, "
			+ "`shipper_id`, "+ "`ship_name`, "+ "`ship_address`, "
			+ "`ship_city`, "+ "`ship_state_province`, "+ "`ship_zip_postal_code`, "
			+ "`ship_country_region`, "+ "`shipping_fee`, "+ "`taxes`, "
			+ "`payment_type`, "+ "`paid_date`, "+ "`notes`, "
			+ "`tax_rate`, "+ "`tax_status_id`, "+ "`status_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	try {
	    ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setInt(1, ordenes.getEmployee_id());
		ps.setInt(2, ordenes.getCustomer_id());
		ps.setDate(3, ordenes.getOrder_date());
		ps.setDate(4, ordenes.getShipped_date());
		ps.setInt(5, ordenes.getShipper_id());
		ps.setString(6, ordenes.getShip_name());
		ps.setDate(7, ordenes.getShipped_date());
		ps.setString(8, ordenes.getShip_city());
		ps.setString(9, ordenes.getShip_state_province());
		ps.setString(10, ordenes.getShip_zip_postal_code());
		ps.setString(11, ordenes.getShip_country_region());
		ps.setBigDecimal(12, ordenes.getShipping_fee());
		ps.setBigDecimal(13, ordenes.getTaxes());
		ps.setString(14, ordenes.getPayment_type());
		ps.setDate(15, ordenes.getPaid_date());
		ps.setString(16, ordenes.getNotes());
		ps.setDouble(17, ordenes.getTax_rate());
		ps.setBoolean(18, ordenes.getTax_status_id());
		ps.setBoolean(19, ordenes.getStatus_id());
		
	    if (ps.executeUpdate() > 0) {
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    id = rs.getInt(1);
		}
	    }

	} catch (SQLException e) {
	    System.err.println("Error al insertar Order: " + e.getMessage());
	    throw e;
	}

	return id;
    }

    public Boolean delete(Integer idOrden) throws SQLException {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "DELETE FROM orders where customer_id = ?";
	try {
	    ps = conexion.prepareStatement(sql);

	    ps.setInt(1, idOrden);

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al borrar una Orden: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public Boolean update(Order ordenes) throws SQLException  {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "UPDATE orders set "
			+"employee_id = ?, "
			+"customer_id = ?, "
			+"order_date = ?, "
			+"shipped_date = ?, "
			+"shipper_id = ?, "
			+"ship_name = ?, "
			+"ship_address = ?, "
			+"ship_city = ?, "
			+"ship_state_province = ?, "
			+"ship_zip_postal_code = ?, "
			+"ship_country_region = ?, "
			+"shipping_fee = ?, "
			+"taxes = ?, "
			+"payment_type = ?, "
			+"paid_date = ?, "
			+"notes = ?, "
			+"tax_rate = ?, "
			+"tax_status_id = ?, "
			+"status_id = ? " 
			+"WHERE id=?";
	
	try {
	    ps = conexion.prepareStatement(sql);
	    ps.setInt(1, ordenes.getEmployee_id());
		ps.setInt(2, ordenes.getCustomer_id());
		ps.setDate(3, ordenes.getOrder_date());
		ps.setDate(4, ordenes.getShipped_date());
		ps.setInt(5, ordenes.getShipper_id());
		ps.setString(6, ordenes.getShip_name());
		ps.setDate(7, ordenes.getShipped_date());
		ps.setString(8, ordenes.getShip_city());
		ps.setString(9, ordenes.getShip_state_province());
		ps.setString(10, ordenes.getShip_zip_postal_code());
		ps.setString(11, ordenes.getShip_country_region());
		ps.setBigDecimal(12, ordenes.getShipping_fee());
		ps.setBigDecimal(13, ordenes.getTaxes());
		ps.setString(14, ordenes.getPayment_type());
		ps.setDate(15, ordenes.getPaid_date());
		ps.setString(16, ordenes.getNotes());
		ps.setDouble(17, ordenes.getTax_rate());
		ps.setBoolean(18, ordenes.getTax_status_id());
		ps.setBoolean(19, ordenes.getStatus_id());
		ps.setInt(20, ordenes.getId());
	  

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al actualizar Cliente: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public ArrayList<Order> lista(String filtro, Integer limite, Integer offset)

    {
	ArrayList<Order> ordenes = new ArrayList<Order>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "+ "`employee_id`, "+ "`customer_id`, "
			+ "`order_date`, "+ "`shipped_date`, "+ "`shipper_id`, "+ "`ship_name`, "
			+ "`ship_address`, "+ "`ship_city`, "+ "`ship_state_province`, "+ "`ship_zip_postal_code`, "
			+ "`ship_country_region`, "+ "`shipping_fee`, "+ "`taxes`, "
			+ "`payment_type`, "+ "`paid_date`, "+ "`notes`, "+ "`tax_rate`, "
			+ "`tax_status_id`, "+ "`status_id`"+ " FROM orders ";


	try {
	    if (filtro != null)
		sql += " WHERE " + filtro;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		ordenes.add(new Order(
				rs.getInt("id"),
				rs.getInt("employee_id"),
				rs.getInt("customer_id"),
				rs.getDate("order_date"),
				rs.getDate("shipped_date"),
				rs.getInt("shipper_id"),
				rs.getString("ship_name"),
				rs.getString("ship_address"),
				rs.getString("ship_city"),
				rs.getString("ship_state_province"),
				rs.getString("ship_zip_postal_code"),
				rs.getString("ship_country_region"),
				rs.getBigDecimal("shipping_fee"),
				rs.getBigDecimal("taxes"),
				rs.getString("payment_type"),
				rs.getDate("paid_date"),
				rs.getString("notes"),
				rs.getDouble("tax_rate"),
				rs.getBoolean("tax_status_id"),
				rs.getBoolean("status_id")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de las Ordenes: " + e.getMessage());
	    return null;
	}

	return ordenes;
    }
    
    public ArrayList<Order> listaCustomer(Integer customer_id, Integer limite, Integer offset)

    {
	ArrayList<Order> ordenes = new ArrayList<Order>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "+ "`employee_id`, "+ "`customer_id`, "
			+ "`order_date`, "+ "`shipped_date`, "+ "`shipper_id`, "+ "`ship_name`, "
			+ "`ship_address`, "+ "`ship_city`, "+ "`ship_state_province`, "+ "`ship_zip_postal_code`, "
			+ "`ship_country_region`, "+ "`shipping_fee`, "+ "`taxes`, "
			+ "`payment_type`, "+ "`paid_date`, "+ "`notes`, "+ "`tax_rate`, "
			+ "`tax_status_id`, "+ "`status_id`"+ " FROM orders ";


	try {
	    if (customer_id != null)
		sql += " WHERE customer_id =" + customer_id;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		ordenes.add(new Order(
				rs.getInt("id"),
				rs.getInt("employee_id"),
				rs.getInt("customer_id"),
				rs.getDate("order_date"),
				rs.getDate("shipped_date"),
				rs.getInt("shipper_id"),
				rs.getString("ship_name"),
				rs.getString("ship_address"),
				rs.getString("ship_city"),
				rs.getString("ship_state_province"),
				rs.getString("ship_zip_postal_code"),
				rs.getString("ship_country_region"),
				rs.getBigDecimal("shipping_fee"),
				rs.getBigDecimal("taxes"),
				rs.getString("payment_type"),
				rs.getDate("paid_date"),
				rs.getString("notes"),
				rs.getDouble("tax_rate"),
				rs.getBoolean("tax_status_id"),
				rs.getBoolean("status_id")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de las Ordenes: " + e.getMessage());
	    return null;
	}

	return ordenes;
    }

}
