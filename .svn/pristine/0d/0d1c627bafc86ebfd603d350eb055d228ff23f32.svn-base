package com.itexpertnepal.simpleinvoice.ui.job.schedular;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author binay
 */
public class RunMeJobQuaterly extends QuartzJobBean {

    private RunMeTaskQuaterly runMeTaskQuaterly;

    public void setRunMeTaskQuaterly(RunMeTaskQuaterly runMeTaskQuaterly) {
        this.runMeTaskQuaterly = runMeTaskQuaterly;
    }

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        try {
            runMeTaskQuaterly.notifyMe();
        } catch (DocumentException ex) {
            Logger.getLogger(RunMeJobQuaterly.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunMeJobQuaterly.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
