package com.ejemplo.Rest_AndresVillani_DanielGil.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.Rest_AndresVillani_DanielGil.DBFactory.DBFactory;
import com.ejemplo.Rest_AndresVillani_DanielGil.Entities.OrderDetail;



public class OrderDetailsModel {
    
    Connection conexion = null;

    public OrderDetailsModel() throws SQLException {
	DataSource ds = DBFactory.getMySQLDataSource();
	conexion = ds.getConnection();
    }

    public OrderDetail read(Integer id) {
    OrderDetail ordersDetails = null;
	Statement sentencia = null;

	String sql = "SELECT `id`, `order_id`, `product_id`, `status_id`, "
		+ "`purchase_order_id`, `inventory_id`, `quantity` , `unit_price`,"
		+ "`discount`, `date_allocated` " + "FROM order_details "
		+ "WHERE id = " + id;

	try {
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { 
	    ordersDetails = new OrderDetail(
			rs.getInt("id"),
			rs.getInt("order_id"),
			rs.getInt("product_id"),
			rs.getInt("status_id"),
			rs.getInt("purchase_order_id"),
			rs.getInt("inventory_id"),
			rs.getBigDecimal("quantity"),
			rs.getBigDecimal("unit_price"),
			rs.getDouble("discount"),
			rs.getDate("date_allocated"));
	    };
	    
	} catch (SQLException e) {
	    System.err.println("Error en read de los detalles de las Ordenes: " + e.getMessage());
	    return null;
	}

	return ordersDetails;
    }

    /**
     * 
     * @param Detalles de las Ordenes
     * @return Devuelve el id del registro recien insertado
     */
    public Integer insert(OrderDetail ordersDetails) throws  SQLException {
	Integer id = null;
	PreparedStatement ps = null;
	String sql = "INSERT INTO order_details ( "
		+ "`order_id`, `product_id`, `status_id`, "
		+ "`purchase_order_id`, `inventory_id`, `quantity` , `unit_price`,"
		+ "`discount`, `date_allocated` ) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	try {
	    ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, ordersDetails.getOrder_id());
		ps.setInt(2, ordersDetails.getProduct_id());
		ps.setInt(3, ordersDetails.getStatus_id());
		ps.setInt(4, ordersDetails.getPurchase_order_id());
		ps.setInt(5, ordersDetails.getInventory_id());
		ps.setBigDecimal(6, ordersDetails.getQuantity());
		ps.setBigDecimal(7, ordersDetails.getUnit_price());
		ps.setDouble(8, ordersDetails.getDiscount());
		ps.setDate(9, ordersDetails.getDate_allocated());
	    
	    if (ps.executeUpdate() > 0) {
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    id = rs.getInt(1);
		}
	    }

	} catch (SQLException e) {
	    System.err.println("Error al insertar los detalles de las Ordenes: " + e.getMessage());
	    throw e;
	}

	return id;
    }

    public Boolean delete(Integer idOrderDetails) throws SQLException {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "DELETE FROM order_details where id = ?";
	try {
	    ps = conexion.prepareStatement(sql);

	    ps.setInt(1, idOrderDetails);

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al borrar los detalles de las Ordenes: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public Boolean update(OrderDetail ordersDetails) throws SQLException  {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "UPDATE order_details set "
		+ "order_id = ?, "
		+ "product_id = ?, "
		+ "status_id = ?, "
		+ "purchase_order_id = ?, "
		+ "inventory_id = ?, "
		+ "quantity  = ?, "
		+ "unit_price = ?, "
		+ "discount = ?, "
		+ "date_allocated = ?, "
		+ "where id = ?";
	
	try {
	    ps = conexion.prepareStatement(sql);
	    ps.setInt(1, ordersDetails.getOrder_id());
		ps.setInt(2, ordersDetails.getProduct_id());
		ps.setInt(3, ordersDetails.getStatus_id());
		ps.setInt(4, ordersDetails.getPurchase_order_id());
		ps.setInt(5, ordersDetails.getInventory_id());
		ps.setBigDecimal(6, ordersDetails.getQuantity());
		ps.setBigDecimal(7, ordersDetails.getUnit_price());
		ps.setDouble(8, ordersDetails.getDiscount());
		ps.setDate(9, ordersDetails.getDate_allocated());

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al actualizar los detalles de las Ordenes: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public ArrayList<OrderDetail> lista(String filtro, Integer limite, Integer offset)

    {
	ArrayList<OrderDetail> ordersDetails = new ArrayList<OrderDetail>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "
		+ "`order_id`, "
		+ "`product_id`, "
		+ "`status_id`, "
		+ "`purchase_order_id`, "
		+ "`inventory_id`, "
		+ "`quantity` , "
		+ "`unit_price`,"
		+ "`discount`, "
		+ "`date_allocated` " 
		+ "FROM order_details ";

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
	    ordersDetails.add(new OrderDetail(
	    		rs.getInt("id"),
				rs.getInt("order_id"),
				rs.getInt("product_id"),
				rs.getInt("status_id"),
				rs.getInt("purchase_order_id"),
				rs.getInt("inventory_id"),
				rs.getBigDecimal("quantity"),
				rs.getBigDecimal("unit_price"),
				rs.getDouble("discount"),
				rs.getDate("date_allocated")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de los detalles de las Ordenes: " + e.getMessage());
	    return null;
	}

	return ordersDetails;
    }
    
    public ArrayList<OrderDetail> listaOrders(Integer order_id, Integer limite, Integer offset)

    {
	ArrayList<OrderDetail> ordersDetails = new ArrayList<OrderDetail>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "
		+ "`order_id`, "
		+ "`product_id`, "
		+ "`status_id`, "
		+ "`purchase_order_id`, "
		+ "`inventory_id`, "
		+ "`quantity` , "
		+ "`unit_price`,"
		+ "`discount`, "
		+ "`date_allocated` " 
		+ "FROM order_details ";
	try {
	    if (order_id != null)
		sql += " WHERE order_id =" + order_id;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { 
	    ordersDetails.add(new OrderDetail(
	    		rs.getInt("id"),
				rs.getInt("order_id"),
				rs.getInt("product_id"),
				rs.getInt("status_id"),
				rs.getInt("purchase_order_id"),
				rs.getInt("inventory_id"),
				rs.getBigDecimal("quantity"),
				rs.getBigDecimal("unit_price"),
				rs.getDouble("discount"),
				rs.getDate("date_allocated")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de los detalles de las Ordenes: " + e.getMessage());
	    return null;
	}

	return ordersDetails;
    }

}
