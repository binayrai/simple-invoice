package com.itexpertnepal.simpleinvoice.ui.upload;

import com.itexpertnepal.simpleinvoice.api.UploadManager;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
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
public class UploadController {

    private UploadedFile file;
    @Log
    Logger logger;

    @Autowired
    private SpringSecurityUtility securityUtility;

    @Autowired
    private UploadManager uploadManager;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @CatchException
    public void uploadCustomer() {
        logger.debug("Inside upoad..");
        if (file != null) {
            this.uploadManager.bulkCustomerUpload(new String(file.getContents()), securityUtility.getPrinciple().getUsername());
            Messages.addInfo(null, "Sucessfully " + file.getFileName() + " is uploaded. ");
        }
    }

    @CatchException
    public void uploadProduct() {
        logger.debug("Inside upoad..");
        if (file != null) {
            this.uploadManager.bulkProductUpload(new String(file.getContents()), securityUtility.getPrinciple().getUsername());
            Messages.addInfo(null, "Sucessfully " + file.getFileName() + " is uploaded. ");
        }
    }

}
