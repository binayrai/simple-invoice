package com.itexpertnepal.simpleinvoice.ui.job.schedular;

import com.itexpertnepal.simpleinvoice.api.CustomerManager;
import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.domain.Customer;
import com.itexpertnepal.simpleinvoice.domain.CustomerAccount;
import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.domain.InvoiceDetails;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.pdf.facade.PageHeadGenerator;
import com.itexpertnepal.simpleinvoice.ui.pdf.facade.PdfGenerateUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author binay
 */
public class RunMeTask {

    @Autowired
    private InvoiceManager invoiceManager;
    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private ProductManager productManager;
    @Log
    private Logger logger;

    @CatchException
    public void notifyMe() throws DocumentException, IOException {
        logger.debug("=================================================== \n");
        logger.debug("Inside schedular run monthly");
        logger.debug("=================================================== \n");
        this.invoiceManager.reoccuranceTrigger(CustomerService.FrequencyType.Monthly);

        logger.debug("*************************************************** \n");
        logger.debug("Inside schedular run monthly for Pdf generation");
        logger.debug("*************************************************** \n");
        this.invoicePdfGeneration();

    }

    private static PdfWriter writer;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String userHome = System.getProperty("user.home");

    public void invoicePdfGeneration() throws DocumentException, IOException {

        Date date = new Date();

        List<Invoice> listInvoice = this.invoiceManager.findAllByMonthWise();
        if (!listInvoice.isEmpty()) {

            final String filePath = "" + userHome + "/invoice/" + CustomerService.FrequencyType.Monthly.toString() + "_Invoice_" + format.format(date) + ".pdf";

            Document document = new Document(PageSize.A4);
            writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            writer.setPageEvent(new PageHeadGenerator());

            for (Invoice invoice : listInvoice) {
                Customer customer = this.customerManager.findByCode(invoice.getCustomerCode());
                if (customer != null) {

                    CustomerAccount customerAccount = this.customerManager.findByCustomerCode(customer.getCode());
                    logger.debug("inside pdf generation.");
                    logger.debug("Pdf page head generated");
                    logger.debug("date withoutformat :" + invoice.getAuditInfo().getCreatedOn());
                    logger.debug("date format :" + format.format(invoice.getAuditInfo().getCreatedOn()));
                    double total = 0.0;
                    PdfGenerateUtility.PlaceChunck("Account No:", 380, 710, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck("Invoice No:", 380, 698, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck("Statement Date:", 380, 686, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck(customer.getAccountNumber(), 450, 710, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);
                    PdfGenerateUtility.PlaceChunck(String.valueOf(invoice.getInvoiceNumber()), 450, 698, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);
                    PdfGenerateUtility.PlaceChunck(formatter.format(invoice.getDate()), 450, 686, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);

                    PdfGenerateUtility.PlaceChunck(customer.getName(), 35, 710, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck(customer.getStreetAddress(), 35, 698, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);
                    PdfGenerateUtility.PlaceChunck(customerAddress(customer), 35, 686, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);

                    PdfGenerateUtility.PlaceChunck("DATE", 35, 660, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck("DESCRIPTION", 100, 660, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck("QTY", 295, 660, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck("AMOUNT", 500, 660, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    int yCoordinate = 610;
                    int invoiceSize = invoice.getInvoiceDetailses().size();
                    for (InvoiceDetails details : invoice.getInvoiceDetailses()) {

                        Product product = this.productManager.findByCode(details.getProductCode());

                        PdfGenerateUtility.PlaceChunck(formatter.format(invoice.getDate()), 35, yCoordinate, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);
                        PdfGenerateUtility.PlaceChunck(product.getName(), 100, yCoordinate, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);
                        PdfGenerateUtility.PlaceChunck(String.valueOf(details.getQuantity()), 295, yCoordinate, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);
                        PdfGenerateUtility.PlaceChunck("$ " + String.valueOf(details.getUnitPrice() * details.getQuantity()), 500, yCoordinate, PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer);

                        total += details.getUnitPrice() * details.getQuantity();
                        yCoordinate -= 20;

                    }
                    total += customerAccount.getBalanceForwared();

                    PdfGenerateUtility.PlaceChunck("BALANCE FORWARD", 315, 640, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);
                    PdfGenerateUtility.PlaceChunck("$ " + String.valueOf(roundDouble(customerAccount.getBalanceForwared())), 500, 640, PdfGenerateUtility.BoldHead.TIMES_BOLD, writer);

                    PdfGenerateUtility.PlaceChunck("TOTAL AMOUNT DUE", 300, 575 - (invoiceSize * 10), PdfGenerateUtility.BoldHead.TIMES_BOLD, writer, invoiceSize);

                    PdfGenerateUtility.PlaceChunck("$ "+String.valueOf(roundDouble(total)), 500, 575 - (invoiceSize * 10), PdfGenerateUtility.BoldHead.TIMES_ROMAN, writer, invoiceSize);

                }

                document.newPage();
                //download and save pdf file            
                //DownloadFileServlet.downloadPDF(invoice.getInvoiceNumber());
                logger.debug("Pdf generation successful For monthly");
            }
            document.close();
        }
    }

    private static double roundDouble(double roundValue) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(roundValue));

    }

    private String customerAddress(Customer customer) {
        StringBuilder builder = new StringBuilder();
        builder.append(customer.getCity());
        builder.append(",");
        builder.append(customer.getState() == null ? "" : customer.getState());
        builder.append("   ");
        builder.append(customer.getZipCode() == null ? "" : customer.getZipCode());
        return builder.toString();

    }

}
