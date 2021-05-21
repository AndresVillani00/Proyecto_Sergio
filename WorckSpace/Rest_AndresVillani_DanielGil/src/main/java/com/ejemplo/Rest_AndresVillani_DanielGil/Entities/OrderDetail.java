package com.ejemplo.Rest_AndresVillani_DanielGil.Entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id", 
    		     "order_id", 
    		     "product_id", 
    		     "status_id", 
                     "purchase_order_id", 
                     "inventory_id", 
                     "quantity", 
                     "unit_price", 
                     "discount", 
                     "date_allocated"})

public class OrderDetail {
	private Integer id;
	private Integer order_id;
	private Integer product_id;
	private Integer status_id;
	private Integer purchase_order_id;
	private Integer inventory_id;
	private BigDecimal quantity;
	private BigDecimal unit_price;
	private double discount;
	private Date date_allocated;
	
	public OrderDetail() {

	}

	public OrderDetail(Integer id, Integer order_id, Integer product_id, Integer status_id, Integer purchase_order_id,
			Integer inventory_id, BigDecimal quantity, BigDecimal unit_price, double discount, Date date_allocated) {

		this.id = id; 
		this.order_id = order_id; 
		this.product_id = product_id; 
		this.status_id = status_id; 
		this.purchase_order_id = purchase_order_id; 
		this.inventory_id = inventory_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.discount = discount;
		this.date_allocated = date_allocated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public Integer getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(Integer purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public Integer getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getDate_allocated() {
		return date_allocated;
	}

	public void setDate_allocated(Date date_allocated) {
		this.date_allocated = date_allocated;
	}

	@Override
	public String toString() {
		return "OrderDetail [quantity=" + quantity + ", unit_price=" + unit_price + ", discount=" + discount + ", "
				+ (date_allocated != null ? "date_allocated=" + date_allocated : "") + "]";
	}

	

}



