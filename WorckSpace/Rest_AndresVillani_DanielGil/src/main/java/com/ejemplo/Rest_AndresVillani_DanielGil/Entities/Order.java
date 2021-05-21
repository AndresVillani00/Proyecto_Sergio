package com.ejemplo.Rest_AndresVillani_DanielGil.Entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id", 
    		     "employee_id", 
    		     "customer_id", 
    		     "shipper_id", 
                     "tax_status_id", 
                     "status_id", 
                     "order_date", 
                     "shipped_date", 
                     "paid_date", 
                     "ship_name", 
                     "ship_address", 
                     "ship_city", 
                     "ship_state_province", 
                     "ship_zip_postal_code", 
                     "ship_country_region", 
                     "payment_type", 
                     "notes", 
                     "shipping_fee", 
                     "taxes", 
                     "tax_rate"})

public class Order {
	private Integer id;
	private Integer employee_id;
	private Integer customer_id;
	private Integer shipper_id;
	private Boolean tax_status_id;
	private Boolean status_id;
	private Date order_date;
	private Date shipped_date;
	private Date paid_date;
	private String ship_name;
	private String ship_address;
	private String ship_city;
	private String ship_state_province;
	private String ship_zip_postal_code;
	private String ship_country_region;
	private String payment_type;
	private String notes;
	private BigDecimal shipping_fee;
	private BigDecimal taxes;
	private double tax_rate;

	
	public Order() {

	}

	public Order(Integer id, Integer employee_id, Integer customer_id, Integer shipper_id, Boolean tax_status_id, Boolean status_id,
			Date order_date, Date shipped_date, Date paid_date, String ship_name, String ship_address, String ship_city, String ship_state_province,
			String ship_zip_postal_code, String ship_country_region, String payment_type, String notes, BigDecimal shipping_fee, BigDecimal taxes, double tax_rate) {

		this.id = id; 
		this.employee_id = employee_id; 
		this.customer_id = customer_id; 
		this.shipper_id = shipper_id; 
		this.tax_status_id = tax_status_id; 
		this.status_id = status_id;
		this.order_date = order_date; 
		this.shipped_date = shipped_date; 
		this.paid_date = paid_date;
		this.ship_name = ship_name; 
		this.ship_address = ship_address; 
		this.ship_city = ship_city;
		this.ship_state_province = ship_state_province;
		this.ship_zip_postal_code = ship_zip_postal_code; 
		this.ship_country_region = ship_country_region; 
		this.payment_type = payment_type;
		this.notes = notes;
		this.shipping_fee = shipping_fee;
		this.taxes = taxes;
		this.tax_rate = tax_rate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getShipper_id() {
		return shipper_id;
	}

	public void setShipper_id(Integer shipper_id) {
		this.shipper_id = shipper_id;
	}

	public Boolean getTax_status_id() {
		return tax_status_id;
	}

	public void setTax_status_id(Boolean tax_status_id) {
		this.tax_status_id = tax_status_id;
	}

	public Boolean getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Boolean status_id) {
		this.status_id = status_id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Date getShipped_date() {
		return shipped_date;
	}

	public void setShipped_date(Date shipped_date) {
		this.shipped_date = shipped_date;
	}

	public Date getPaid_date() {
		return paid_date;
	}

	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}

	public String getShip_name() {
		return ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}

	public String getShip_address() {
		return ship_address;
	}

	public void setShip_address(String ship_address) {
		this.ship_address = ship_address;
	}

	public String getShip_city() {
		return ship_city;
	}

	public void setShip_city(String ship_city) {
		this.ship_city = ship_city;
	}

	public String getShip_state_province() {
		return ship_state_province;
	}

	public void setShip_state_province(String ship_state_province) {
		this.ship_state_province = ship_state_province;
	}

	public String getShip_zip_postal_code() {
		return ship_zip_postal_code;
	}

	public void setShip_zip_postal_code(String ship_zip_postal_code) {
		this.ship_zip_postal_code = ship_zip_postal_code;
	}

	public String getShip_country_region() {
		return ship_country_region;
	}

	public void setShip_country_region(String ship_country_region) {
		this.ship_country_region = ship_country_region;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getShipping_fee() {
		return shipping_fee;
	}

	public void setShipping_fee(BigDecimal shipping_fee) {
		this.shipping_fee = shipping_fee;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	@Override
	public String toString() {
		return "Order [" + (order_date != null ? "order_date=" + order_date + ", " : "")
				+ (shipped_date != null ? "shipped_date=" + shipped_date + ", " : "")
				+ (paid_date != null ? "paid_date=" + paid_date + ", " : "")
				+ (ship_name != null ? "ship_name=" + ship_name + ", " : "")
				+ (ship_address != null ? "ship_address=" + ship_address + ", " : "")
				+ (ship_city != null ? "ship_city=" + ship_city + ", " : "")
				+ (ship_state_province != null ? "ship_state_province=" + ship_state_province + ", " : "")
				+ (ship_zip_postal_code != null ? "ship_zip_postal_code=" + ship_zip_postal_code + ", " : "")
				+ (ship_country_region != null ? "ship_country_region=" + ship_country_region + ", " : "")
				+ (payment_type != null ? "payment_type=" + payment_type + ", " : "")
				+ (notes != null ? "notes=" + notes + ", " : "") + "shipping_fee=" + shipping_fee + ", taxes=" + taxes
				+ ", tax_rate=" + tax_rate + "]";
	}
	
	
	

}



