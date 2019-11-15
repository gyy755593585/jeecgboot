
package com.jeecg.demo.page;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import com.jeecg.demo.entity.JformOrderCustomerEntity;
import com.jeecg.demo.entity.JformOrderTicketEntity;

/**   
 * @Title: Entity
 * @Description: 订单主信息
 * @author onlineGenerator
 * @date 2017-09-17 11:49:08
 * @version V1.0   
 *
 */
public class JformOrderMainPage implements java.io.Serializable {
	/**主键*/
	private String id;
	/**订单号*/
    @Excel(name="订单号")
	private String orderCode;
	/**订单日期*/
    @Excel(name="订单日期",format = "yyyy-MM-dd")
	private java.util.Date orderDate;
	/**订单金额*/
    @Excel(name="订单金额")
	private Double orderMoney;
	/**备注*/
    @Excel(name="备注")
	private String content;
	/**订单扫描件*/
    @Excel(name="订单扫描件")
	private String ctype;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单号
	 */
	public String getOrderCode(){
		return this.orderCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderCode(String orderCode){
		this.orderCode = orderCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  订单日期
	 */
	public java.util.Date getOrderDate(){
		return this.orderDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  订单日期
	 */
	public void setOrderDate(java.util.Date orderDate){
		this.orderDate = orderDate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  订单金额
	 */
	public Double getOrderMoney(){
		return this.orderMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  订单金额
	 */
	public void setOrderMoney(Double orderMoney){
		this.orderMoney = orderMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	public String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单扫描件
	 */
	public String getCtype(){
		return this.ctype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单扫描件
	 */
	public void setCtype(String ctype){
		this.ctype = ctype;
	}

	/**保存-JformOrderMain子表*/

    @ExcelCollection(name="订单客户信息")

	private List<JformOrderCustomerEntity> jformOrderCustomerList = new ArrayList<JformOrderCustomerEntity>();
		public List<JformOrderCustomerEntity> getJformOrderCustomerList() {
		return jformOrderCustomerList;
		}
		public void setJformOrderCustomerList(List<JformOrderCustomerEntity> jformOrderCustomerList) {
		this.jformOrderCustomerList = jformOrderCustomerList;
		}
	/**保存-JformOrderMain子表*/

    @ExcelCollection(name="订单机票信息")

	private List<JformOrderTicketEntity> jformOrderTicketList = new ArrayList<JformOrderTicketEntity>();
		public List<JformOrderTicketEntity> getJformOrderTicketList() {
		return jformOrderTicketList;
		}
		public void setJformOrderTicketList(List<JformOrderTicketEntity> jformOrderTicketList) {
		this.jformOrderTicketList = jformOrderTicketList;
		}
}
