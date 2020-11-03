/**
 * All rights reserved by YSCompany Inc.
 */
package com.example.mysqlservers.model;


/**
 * <p>
 * @author WillYang
 * @Date 2020-01-15 09:58:21
 * @since 1.0
 */
public class Orderdetail {
	
	/** serialVersionUID */
    private static final long serialVersionUID = 1L;
	
	/** . */
	private String orderid ;
	
	/** . */
	private String commodityName ;
	
	/** . */
	private String price ;
	
	/** . */
	private java.util.Date orderTime ;
	
	/** . */
	private String customer ;
	
	

    /** set . */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	/** get . */
	public String getOrderid() {
		return this.orderid;
	}
	

    /** set . */
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	/** get . */
	public String getCommodityName() {
		return this.commodityName;
	}
	

    /** set . */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/** get . */
	public String getPrice() {
		return this.price;
	}
	

    /** set . */
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}
	
	/** get . */
	public java.util.Date getOrderTime() {
		return this.orderTime;
	}
	

    /** set . */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	/** get . */
	public String getCustomer() {
		return this.customer;
	}
	
}

