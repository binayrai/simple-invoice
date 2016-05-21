package com.itexpertnepal.simpleinvoice.ui.job.schedular;

import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author binay
 */
public class ReoccuranceJob extends QuartzJobBean {

    @Autowired
    private InvoiceManager invoiceManager;

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        List<Invoice> findAll = this.invoiceManager.findAll();
    }

}
