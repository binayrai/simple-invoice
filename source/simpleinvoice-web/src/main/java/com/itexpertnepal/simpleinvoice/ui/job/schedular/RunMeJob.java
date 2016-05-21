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
public class RunMeJob extends QuartzJobBean {

    private RunMeTask runMeTask;

    public void setRunMeTask(RunMeTask runMeTask) {
        this.runMeTask = runMeTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        try {
            runMeTask.notifyMe();
        } catch (DocumentException ex) {
            Logger.getLogger(RunMeJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunMeJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
