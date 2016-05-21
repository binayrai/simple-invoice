package com.itexpertnepal.simpleinvoice.ui.controller.biller;

import com.itexpertnepal.simpleinvoice.api.BillerManager;
import com.itexpertnepal.simpleinvoice.domain.Biller;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */
@Component
@SpringRequestScoped
public class BillerAddController {

    private Biller biller;

    @Autowired
    private BillerManager billerManager;

    @Autowired
    private SpringSecurityUtility securityUtility;
    @Log
    private Logger logger;

    @PostConstruct
    public void init() {
        this.biller = new Biller();

    }

    @CatchException
    public String onSave() {
        biller.getAuditInfo().setCreatedBy(securityUtility.getPrinciple().getUsername());
        logger.debug("before service call");
        this.billerManager.addNew(biller);
        Messages.addInfo(null, "Billers  Succesfully" + "added");
        return "pretty:biller";

    }

    public Biller getBiller() {
        return biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }

}
