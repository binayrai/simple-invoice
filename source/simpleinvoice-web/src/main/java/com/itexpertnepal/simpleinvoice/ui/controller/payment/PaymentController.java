package com.itexpertnepal.simpleinvoice.ui.controller.payment;

import com.itexpertnepal.simpleinvoice.api.PaymentManager;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import java.util.List;
import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class PaymentController {

    private UploadedFile file;
    @Log
    Logger logger;
    @Autowired
    private PaymentManager paymentManager;
    @Autowired
    private SpringSecurityUtility securityUtility;

    private List<Object[]> listPayments;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @CatchException
    public void upload() {
        logger.debug("Inside upoad..");
        if (file != null) {
            this.paymentManager.bulkPaymentProcess(new String(file.getContents()), securityUtility.getPrinciple().getUsername());
            Messages.addInfo(null, "Sucessfully " + file.getFileName() + " is uploaded. ");
        }

    }

    public List<Object[]> getListPayments() {
        if (listPayments == null) {
            this.listPayments = this.paymentManager.finAll();
        }
        return listPayments;
    }

    public void setListPayments(List<Object[]> listPayments) {
        this.listPayments = listPayments;
    }

}
