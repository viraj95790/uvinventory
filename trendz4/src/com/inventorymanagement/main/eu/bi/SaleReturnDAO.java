package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Invoice;
import com.inventorymanagement.main.eu.entity.Purchase;

public interface SaleReturnDAO {

	int saveCompany(Purchase purchase1, String userId);

	int saveSize(Purchase purchase, String string, String string2);

	int savePurchaseInvoice(Purchase purchase, String userId, int companyId);

	ArrayList<Invoice> getCompanyList(String userId);

	ArrayList<Purchase> getPurchaseInvoiceList();

	Purchase getSaleReturnDetails(String salereturnID);

	int deleteSaleReturn(String salereturnID);

	int countSaler_invoiceData(int companyID);

	int deleteSaler_company(int companyID);

}
