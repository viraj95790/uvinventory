package com.inventorymanagement.main.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.Dump.INode;

import com.inventorymanagement.common.utils.DateTimeUtils;
import com.inventorymanagement.main.common.constants.Constants;
import com.inventorymanagement.main.eu.bi.CustomerDAO;
import com.inventorymanagement.main.eu.bi.InvoiceDAO;
import com.inventorymanagement.main.eu.bi.PurchaseDAO;
import com.inventorymanagement.main.eu.bi.SaleDAO;
import com.inventorymanagement.main.eu.bi.SalesManDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.Connection_provider;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCCustomerDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCInvoiceDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCPurchaseDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSaleDAO;
import com.inventorymanagement.main.eu.blogic.jdbc.JDBCSalesManDAO;
import com.inventorymanagement.main.eu.entity.Customer;
import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;
import com.inventorymanagement.main.eu.entity.Sale;
import com.inventorymanagement.main.eu.entity.SalesMan;
import com.inventorymanagement.main.eu.entity.Timber;
import com.inventorymanagement.main.web.common.helper.LoginHelper;
import com.inventorymanagement.main.web.common.helper.LoginInfo;
import com.inventorymanagement.main.web.form.InvoiceForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InvoiceReportAction extends BaseAction implements Preparable,
		ModelDriven<InvoiceForm> {

	InvoiceForm invoiceForm = new InvoiceForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public String execute() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String fromDate = invoiceForm.getFromDate();
		String toDate = invoiceForm.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			invoiceForm.setFromDate(fromDate);
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			toDate = dateFormat.format(cal.getTime());
			invoiceForm.setToDate(toDate);
		}

		if (invoiceForm.getFromDate() != null) {
			fromDate = DateTimeUtils
					.changeDateFormat(invoiceForm.getFromDate());
			toDate = DateTimeUtils.changeDateFormat(invoiceForm.getToDate());
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			Connection connection = null;
			int totalQuantity = 0;
			double totalAmount = 0;
			try {
				connection = Connection_provider.getconnection();
				InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);

				/*
				 * if(loginInfo.getCode().equals(Constants.CODECONSTANT)){
				 * invoiceForm.setBillType(3); }
				 */

				ArrayList<Invoice> customerList = invoiceDAO.getCustomerList(
						fromDate, toDate, loginInfo.getUserId(),
						invoiceForm.getBillType(), loginInfo.getUserType());
				ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

				for (Invoice invoice : customerList) {

					String clientid = invoiceDAO.getclientid(invoice
							.getInvoiceNumber());
					CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
					Customer customer = invoiceDAO
							.getInvoiceCustomerDetails(Integer.toString(invoice
									.getInvoiceNumber()));

					invoice.setCustomerName(customer.getName());
					invoice.setMobileNumber(customer.getMob());

					ArrayList<Purchase> purchaseList = invoiceDAO
							.getSaleInvoiceList(invoice.getInvoiceNumber());
					
					Purchase p = purchaseList.get(purchaseList.size()-1);
					invoice.setTotalcgst(DateTimeUtils.changeFormat(p.getTotalcgstvalue()));
					invoice.setTotalsgst(DateTimeUtils.changeFormat(p.getTotalsgstvalue()));
					
					
					invoice.setPurchaseList(purchaseList);
					double gtotal = 0;
					double payAmount = 0;
					for (Purchase purchase : purchaseList) {
						// payAmount = payAmount+purchase.getTotal();
						totalQuantity = totalQuantity + purchase.getQuantity();
						totalAmount = totalAmount + purchase.getTotal();
						gtotal = gtotal
								+ (purchase.getMrp() * purchase.getQuantity());
					}
					invoice.setGtotal(Double.toString(gtotal));
					/*
					 * double per = (invoice.getVat()*payAmount)/100; payAmount
					 * = payAmount-per;
					 */
					payAmount = invoiceDAO.getSumOfSaleAmount(invoice
							.getInvoiceNumber());
					double balance = invoice.getSomoftotal() - payAmount;
					invoice.setBalance(balance);
					invoice.setSaleAmount(payAmount);
					invoice.setPayamount(payAmount);
					invoiceList.add(invoice);
				}

				invoiceForm.setInvoiceList(invoiceList);
				invoiceForm.setSelectedbillType(invoiceForm.getBillType());
				invoiceForm.setTotalQuantity(totalQuantity);
				invoiceForm.setTotalAmount(totalAmount);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}

		}

		return SUCCESS;
	}

	public String salesman() throws SQLException {
		if (!verifyLogin(request)) {
			return "login";
		}

		if (invoiceForm.getFromDate() != null) {
			String fromDate = DateTimeUtils.changeDateFormat(invoiceForm
					.getFromDate());
			String toDate = DateTimeUtils.changeDateFormat(invoiceForm
					.getToDate());
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);

				ArrayList<Invoice> customerList = invoiceDAO
						.getCustomerListBySalesMan(fromDate, toDate,
								loginInfo.getUserId(),
								invoiceForm.getEmployeeid());
				ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
				int totalQuantity = 0;
				double totalAmount = 0;
				for (Invoice invoice : customerList) {
					ArrayList<Purchase> purchaseList = invoiceDAO
							.getSaleInvoiceList(invoice.getInvoiceNumber());
					invoice.setPurchaseList(purchaseList);
					double payAmount = 0;

					for (Purchase purchase : purchaseList) {
						// payAmount = payAmount+purchase.getTotal();
						totalQuantity = totalQuantity + purchase.getQuantity();
						totalAmount = totalAmount + purchase.getTotal();
					}
					// double per = (invoice.getVat()*payAmount)/100;
					// payAmount = payAmount-per;
					payAmount = invoiceDAO.getSumOfSaleAmount(invoice
							.getInvoiceNumber());
					double balance = invoice.getSomoftotal() - payAmount;
					invoice.setBalance(balance);
					invoice.setSaleAmount(payAmount);
					invoice.setPayamount(payAmount);
					invoiceList.add(invoice);
				}

				SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
				SalesMan salesMan = salesManDAO.getSalesMan(invoiceForm
						.getEmployeeid());

				double commusion = (totalAmount * Double.parseDouble(salesMan
						.getCommission())) / 100;
				invoiceForm.setCommusion(Double.toString(commusion));

				invoiceForm.setInvoiceList(invoiceList);
				invoiceForm.setSelectedbillType(invoiceForm.getBillType());
				invoiceForm.setTotalQuantity(totalQuantity);
				invoiceForm.setTotalAmount(totalAmount);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}

		}

		return "salesmaninvoicereport";
	}

	public InvoiceForm getModel() {
		// TODO Auto-generated method stub
		return invoiceForm;
	}

	public void prepare() throws Exception {

		invoiceForm.setSelectedbillType(3);
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			// set salesman
			SalesManDAO salesManDAO = new JDBCSalesManDAO(connection);
			ArrayList<SalesMan> salesManList = salesManDAO
					.getSalesManList(loginInfo.getUserId());
			ArrayList<SalesMan> populatedSalesManList = new ArrayList<SalesMan>();

			for (SalesMan salesMan : salesManList) {
				populatedSalesManList
						.add(new SalesMan(salesMan.getId(), salesMan
								.getFirstName() + " " + salesMan.getLastName()));
			}

			invoiceForm.setPopulatedSalesManList(populatedSalesManList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public String account() {
		if (!verifyLogin(request)) {
			return "login";
		}

		String selectedid = request.getParameter("selectedid");

		if (selectedid == null) {
			selectedid = (String) session.getAttribute("saleaccountclientid");
		} else {
			session.setAttribute("saleaccountclientid", selectedid);
		}

		String fromDate = invoiceForm.getFromDate();
		String toDate = invoiceForm.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			invoiceForm.setFromDate(fromDate);
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			toDate = dateFormat.format(cal.getTime());
			invoiceForm.setToDate(toDate);
		}

		if (!fromDate.equals("")) {
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		}
		if (!toDate.equals("")) {
			String temp1[] = toDate.split("/");
			toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);

			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			Customer customer = customerDAO.editdata(selectedid);

			invoiceForm.setCustomerName(customer.getName() + " "
					+ customer.getSurname());
			invoiceForm.setMobileNumber(customer.getMob());

			ArrayList<Purchase> saleaccList = invoiceDAO.getSaleAccountList(
					fromDate, toDate, selectedid);
			invoiceForm.setSaleaccList(saleaccList);

			double d = 0, c = 0, b = 0;
			for (Purchase purchase : saleaccList) {

				d = d + Double.parseDouble(purchase.getDabit());
				c = c + Double.parseDouble(purchase.getCredit());
				b = b + purchase.getBalance();

			}

			invoiceForm.setDebitx(Double.toString(d));
			invoiceForm.setCreditx(Double.toString(c));
			invoiceForm.setBalance(Double.toString(b));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "saleaccount";
	}

	public String record() throws SQLException {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);

			String invoiceid = request.getParameter("invoiceid");

			ArrayList<Invoice> customerList = invoiceDAO
					.getSaleCustomerList(invoiceid);
			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

			for (Invoice invoice : customerList) {

				String clientid = invoiceDAO.getclientid(invoice
						.getInvoiceNumber());
				CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
				Customer customer = customerDAO.editdata(clientid);

				invoice.setCustomerName(customer.getName() + " "
						+ customer.getSurname());
				invoice.setMobileNumber(customer.getMob());

				ArrayList<Purchase> purchaseList = invoiceDAO
						.getSaleInvoiceList(invoice.getInvoiceNumber());
				invoice.setPurchaseList(purchaseList);
				double gtotal = 0;
				double payAmount = 0;
				for (Purchase purchase : purchaseList) {
					// payAmount = payAmount+purchase.getTotal();

					gtotal = gtotal
							+ (purchase.getMrp() * purchase.getQuantity());
				}
				invoice.setGtotal(Double.toString(gtotal));
				/*
				 * double per = (invoice.getVat()*payAmount)/100; payAmount =
				 * payAmount-per;
				 */
				payAmount = invoiceDAO.getSumOfSaleAmount(invoice
						.getInvoiceNumber());
				double balance = invoice.getSomoftotal() - payAmount;
				invoice.setBalance(balance);
				invoice.setSaleAmount(payAmount);
				invoice.setPayamount(payAmount);
				invoiceList.add(invoice);
			}

			invoiceForm.setInvoiceList(invoiceList);
			invoiceForm.setSelectedbillType(invoiceForm.getBillType());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "salerecord";
	}

	public String payment() {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			SaleDAO saleDAO = new JDBCSaleDAO(connection);

			int savesalepayment = saleDAO.saveSalePayment(
					Integer.parseInt(invoiceForm.getInvoiceid()),
					invoiceForm.getHowpaid(), invoiceForm.getPaymentrecieved(),
					invoiceForm.getPaymentnote());

			double balance = invoiceDAO.getpayrollbalance();

			double pbalance = Double.parseDouble(invoiceForm
					.getPaymentrecieved()) + balance;

			String customerid = invoiceDAO.getcustomerid(invoiceForm
					.getInvoiceid());

			int result = invoiceDAO.insertdata(pbalance, customerid,
					invoiceForm.getHowpaid(), invoiceForm.getPaymentrecieved());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "payment";
	}

	public String expense() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			PurchaseDAO purchaseDAO = new JDBCPurchaseDAO(connection);

			for (Timber t : invoiceForm.getPaylist()) {

				if (!t.getInvoice().equals("0")) {
					int savePurchasePayment = purchaseDAO.savePurchasePayment(
							Integer.parseInt(t.getInvoice()),
							invoiceForm.getHowpaid(), t.getDebitx(),
							invoiceForm.getPaymentnote());
				}

				double balance = invoiceDAO.getpayrollbalance();
				balance = balance - Double.parseDouble(t.getDebitx());

				String customerid = invoiceDAO.getcustomerid(invoiceForm
						.getInvoiceid());

				String date = DateTimeUtils.getCommencingDate(invoiceForm
						.getDate());
				date = date + "12:30:30";
				int result = invoiceDAO.savedata(t, balance, customerid,
						invoiceForm.getHowpaid(),
						invoiceForm.getPaymentrecieved(), date);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "expense";
	}

	public String display() {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);

			ArrayList<Timber> payrolist = invoiceDAO.getpayroll();
			invoiceForm.setPayrolList(payrolist);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "display";
	}

	public String payrolexpense() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);

			double balance = invoiceDAO.getpayrollbalance();
			invoiceForm.setBalance(Double.toString(balance));

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String fromDate = dateFormat.format(cal.getTime());
			invoiceForm.setDate(fromDate);

			ArrayList<Timber> vendorList = invoiceDAO.getvendorList();
			invoiceForm.setVendorList(vendorList);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "payrollex";
	}

	public String vnder() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			ArrayList<Timber> vendorList = invoiceDAO.getvendorList();

			String index = request.getParameter("index");

			StringBuffer str = new StringBuffer();
			str.append("<select onchange='setInvoiceAjax(this.value," + index
					+ ")' name='paylist[" + index + "].vendor' "
					+ "id='paylist[" + index
					+ "].vendor'><option value='0'>Select Vendor</option>");

			for (Timber timber : vendorList) {
				str.append("<option value='" + timber.getId() + "'>"
						+ timber.getVendor() + "</option>");
			}

			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String bal() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		String invid = request.getParameter("invid");
		try {
			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			ArrayList<Timber> vendorList = invoiceDAO.getvendorList();

			Timber timber = invoiceDAO.getVendorPurchaseDetails(invid);
			CustomerDAO customerDAO = new JDBCCustomerDAO(connection);
			Customer customer = customerDAO.editdata(timber.getVendorid());

			invoiceForm.setName(customer.getName());
			invoiceForm.setMobileNumber(customer.getMob());
			invoiceForm.setInvoiceid(invid);
			invoiceForm.setDate(timber.getDate());

			double debit = Double.parseDouble(timber.getPurchase());
			double discamt = (debit * Integer.parseInt(timber.getDiscount())) / 100;
			double r = debit - discamt;
			timber.setPurchase(Double.toString(r));

			double balance = Double.parseDouble(timber.getPurchase())
					- Double.parseDouble(timber.getPayamount());

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + balance + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String inv() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {

			String index = request.getParameter("index");
			String vnderid = request.getParameter("vnderid");

			connection = Connection_provider.getconnection();
			InvoiceDAO invoiceDAO = new JDBCInvoiceDAO(connection);
			ArrayList<Timber> vendorList = invoiceDAO.getInvoiceList(vnderid);

			StringBuffer str = new StringBuffer();
			str.append("<select onchange='setInvoiceNalance(this.value,"
					+ index + ")'  name='paylist[" + index + "].invoice' "
					+ "id='paylist[" + index
					+ "].invoice'><option value='0'>Select Invoice</option>");

			for (Timber timber : vendorList) {
				str.append("<option value='" + timber.getId() + "'>"
						+ timber.getId() + "</option>");
			}

			str.append("</select>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + str + "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
