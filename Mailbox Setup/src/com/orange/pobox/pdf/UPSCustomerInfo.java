package com.orange.pobox.pdf;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UPSCustomerInfo {
	private String custName;
	private String date;
	private String address;
	private String tel;
	private String cell;
	private String fax;
	private String email;
	private List iteminfo;
	private String checkItem;
	private Date startDate1;
	private Date endDate1;
	private Date startDate2;
	private Date endDate2;
	private Date startDate3;
	private Date endDate3;
	private Date startDate4;
	private Date endDate4;
	private String upsEmailAddr;
	
	public UPSCustomerInfo(String custName, String date, String address,
			String tel, String cell, String fax, String email, List iteminfo,
			String checkItem, Date startDate1, Date endDate1, Date startDate2,
			Date endDate2, Date startDate3, Date endDate3, Date startDate4,
			Date endDate4, String upsEmailAddr) {
		super();
		this.custName = custName;
		this.date = date;
		this.address = address;
		this.tel = tel;
		this.cell = cell;
		this.fax = fax;
		this.email = email;
		this.iteminfo = iteminfo;
		this.checkItem = checkItem;
		this.startDate1 = startDate1;
		this.endDate1 = endDate1;
		this.startDate2 = startDate2;
		this.endDate2 = endDate2;
		this.startDate3 = startDate3;
		this.endDate3 = endDate3;
		this.startDate4 = startDate4;
		this.endDate4 = endDate4;
		this.upsEmailAddr = upsEmailAddr;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List getIteminfo() {
		return iteminfo;
	}
	public void setIteminfo(List iteminfo) {
		this.iteminfo = iteminfo;
	}
	public String getCheckItem() {
		return checkItem;
	}
	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}
	public Date getStartDate1() {
		return startDate1;
	}
	public String getStartDateStr1(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.startDate1);
		return str;
	}
	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}
	public String getEndDateStr1(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.endDate1);
		return str;
	}
	public Date getEndDate1() {
		return endDate1;
	}
	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}
	
	public Date getStartDate2() {
		return startDate2;
	}
	public String getStartDateStr2(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.startDate2);
		return str;
	}
	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}
	public String getEndDateStr2(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.endDate2);
		return str;
	}
	public Date getEndDate2() {
		return endDate2;
	}
	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}
	
	public Date getStartDate3() {
		return startDate3;
	}
	public String getStartDateStr3(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.startDate3);
		return str;
	}
	public void setStartDate3(Date startDate3) {
		this.startDate3 = startDate3;
	}
	public String getEndDateStr3(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str =formatter.format(this.endDate3);
		return str;
	}
	public Date getEndDate3() {
		return endDate3;
	}
	public void setEndDate3(Date endDate3) {
		this.endDate3 = endDate3;
	}
	
	public Date getStartDate4() {
		return startDate4;
	}
	public String getStartDateStr4(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.startDate4);
		return str;
	}
	public void setStartDate4(Date startDate4) {
		this.startDate4 = startDate4;
	}
	public String getEndDateStr4(){
		String str = "";
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		str = formatter.format(this.endDate4);
		return str;
	}
	public Date getEndDate4() {
		return endDate4;
	}
	public void setEndDate4(Date endDate4) {
		this.endDate4 = endDate4;
	}
	public String getUpsEmailAddr() {
		return upsEmailAddr;
	}
	public void setUpsEmailAddr(String upsEmailAddr) {
		this.upsEmailAddr = upsEmailAddr;
	}
	
	
}
