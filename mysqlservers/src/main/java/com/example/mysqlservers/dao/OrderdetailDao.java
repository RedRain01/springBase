/**
 * All rights reserved by YSCompany Inc.
 */
package com.example.mysqlservers.dao;



import com.example.mysqlservers.model.Orderdetail;

/**
 * Mapper.<p>
 * @author WillYang
 * @Date 2020-01-15 09:58:21
 * @since 1.0
 */

public interface OrderdetailDao {

	static String SEQUENCE = "SEQ_orderdetail_ID";
	
	/**
	 * 创建对象
	 * @param orderdetail
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	public int create(Orderdetail orderdetail);
	
	
	/**
	 * 根据主键查询对象
	 * @param orderid
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	public Orderdetail queryByPk(String orderid);


	/**
	 * 根据主键查询对象
	 * @param
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	public  java.util.List<Orderdetail>   all();
	
	/**
	 * 根据主键修改对象
	 * @param orderdetail
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	public int updateByPk(Orderdetail orderdetail);
	
	/**
	 * 根据主键删除对象
	 * @param orderid
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	public int deleteByPk(String orderid);

	
	/**
	 * 根据对象查询数据
	 * @param orderdetail
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	public java.util.List<Orderdetail> queryByOrderdetail(Orderdetail orderdetail);
	
	/**
	 * 根据条件分页查询数据
	 * @param paramMap
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	@SuppressWarnings({"rawtypes" })
	public java.util.List<Orderdetail> queryByPage(java.util.Map paramMap);

	/**
	 * 根据条件查询数据总量
	 * @param paramMap
	 * @return
	 * @author WillYang
	 * @since 2020-01-15 09:58:21
	 */
	@SuppressWarnings({"rawtypes" })
	public long countByCondtion(java.util.Map paramMap);
}
