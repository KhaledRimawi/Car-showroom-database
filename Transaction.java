package application;

import java.time.LocalDate;

public class Transaction{
	
    public Integer Transaction_Id, Customer_Id, Car_Id, emp_id;
    public LocalDate Date;
    public String Pay_Type;
    public Double Discount, Paid_Amount,price, TotalPrice, Remaining;
   
	public Transaction(int Transaction_Id, int Customer_Id,int Car_Id , 
			int emp_id,LocalDate date, String pay_Type, double price, double discount,
			double paid_Amount, double totalPrice, double remaining) {
		this.Transaction_Id = Transaction_Id;
		this.Customer_Id= Customer_Id;
		this.Car_Id = Car_Id;
		this.emp_id = emp_id;
		this.Date = date;
		Pay_Type = pay_Type;
		this.price = price;
		Discount = discount;
		Paid_Amount = paid_Amount;
		this.TotalPrice = totalPrice;
		this.Remaining = remaining;
	}
	public Transaction(int Transaction_Id, int Customer_Id,int Car_Id , 
			int emp_id,LocalDate date, String pay_Type, double price, double discount,
			double paid_Amount) {
		this.Transaction_Id = Transaction_Id;
		this.Customer_Id= Customer_Id;
		this.Car_Id = Car_Id;
		this.emp_id = emp_id;
		this.Date = date;
		Pay_Type = pay_Type;
		this.price = price;
		Discount = discount;
		Paid_Amount = paid_Amount;
		
	}
	
	public Transaction(int Transaction_Id, double totalPrice, double remaining) {
		this.Transaction_Id = Transaction_Id;
		this.TotalPrice = totalPrice;
		this.Remaining = remaining;
	}
	

	public Integer getTransaction_Id() {
		return Transaction_Id;
	}

	public void setTransaction_Id(Integer transaction_Id) {
		Transaction_Id = transaction_Id;
	}

	public Integer getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(Integer customer_Id) {
		Customer_Id = customer_Id;
	}

	public Integer getCar_Id() {
		return Car_Id;
	}

	public void setCar_Id(Integer car_Id) {
		Car_Id = car_Id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public String getPay_Type() {
		return Pay_Type;
	}

	public void setPay_Type(String pay_Type) {
		Pay_Type = pay_Type;
	}

	public Double getDiscount() {
		return Discount;
	}

	public void setDiscount(Double discount) {
		Discount = discount;
	}

	public Double getPaid_Amount() {
		return Paid_Amount;
	}

	public void setPaid_Amount(Double paid_Amount) {
		Paid_Amount = paid_Amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		TotalPrice = totalPrice;
	}

	public Double getRemaining() {
		return Remaining;
	}

	public void setRemaining(Double remaining) {
		Remaining = remaining;
	}
	
    	
	
}

