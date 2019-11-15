package com.jeecg.demo.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: JformOrderMain子表
 * @author onlineGenerator
 * @date 2017-09-17 11:49:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jform_order_customer", schema = "")
@SuppressWarnings("serial")
public class JformOrderCustomerEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**客户名*/
    @Excel(name="客户名",width=15)
	private String name;
	/**单价*/
    @Excel(name="单价",width=15)
	private Double money;
	/**性别*/
    @Excel(name="性别",width=15,dicCode="sex")
	private String sex;
	/**电话*/
    @Excel(name="电话",width=15)
	private String telphone;
	/**身份证扫描件*/
    @Excel(name="身份证扫描件",width=15)
	private String sfPic;
	/**外键*/
	private String fkId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
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
	 *@return: java.lang.String  客户名
	 */
	
	@Column(name ="NAME",nullable=true,length=100)
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单价
	 */
	
	@Column(name ="MONEY",nullable=true,scale=2,length=10)
	public Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单价
	 */
	public void setMoney(Double money){
		this.money = money;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	
	@Column(name ="SEX",nullable=true,length=4)
	public String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(String sex){
		this.sex = sex;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	
	@Column(name ="TELPHONE",nullable=true,length=32)
	public String getTelphone(){
		return this.telphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证扫描件
	 */
	
	@Column(name ="SF_PIC",nullable=true,length=500)
	public String getSfPic(){
		return this.sfPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证扫描件
	 */
	public void setSfPic(String sfPic){
		this.sfPic = sfPic;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	
	@Column(name ="FK_ID",nullable=false,length=36)
	public String getFkId(){
		return this.fkId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setFkId(String fkId){
		this.fkId = fkId;
	}
	
}
