package com.itexpertnepal.simpleinvoice.ui.job.schedular;

/**
 *
 * @author binay
 */
public class ReoccuranceListener  {

//    // Initiate a Schedule Factory
//    private static final SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//    // Retrieve a scheduler from schedule factory (Lazy init)
//    private Scheduler scheduler = null;
//    private static final Logger LOG = Logger.getLogger(ReoccuranceListener.class.getName());
//
//    @Override
//    public void contextDestroyed(ServletContextEvent arg0) {
//        try {
//            if (scheduler != null && scheduler.isStarted()) {
//                scheduler.shutdown();
//            }
//        } catch (SchedulerException e) {
//            LOG.info(e.getMessage());
//        }
//    }
//
//    @Override
//    public void contextInitialized(ServletContextEvent arg0) {
//
//        LOG.info("----- Initializing quartz -----");
//        try {
//            scheduler = schedulerFactory.getScheduler();
//
//            // Initiate JobDetail with job name, job group, and executable job class
//            JobDetail jobDetail = JobBuilder.newJob(ReoccuranceJob.class)
//                    .withIdentity("db_refresher", "refresher")
//                    .build();
//
//            Trigger simpleTrigger = newTrigger()
//                    .withIdentity("trigger2", "group1")
//                    .withSchedule(cronSchedule("0/50 * * * * ?"))
//                    .build();
//
//            Trigger trigger = newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .startAt(DateBuilder.nextGivenMinuteDate(new Date(), 0))
//                    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
//                            .withIntervalInMonths(1)).build();
////            // Initiate SimpleTrigger with its name and group name.
////            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
////                    .withIdentity(TriggerKey.triggerKey("myTrigger", "myTriggerGroup"))
////                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
////                    .startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND))
////                    .build();
//
//            scheduler.scheduleJob(jobDetail, simpleTrigger);
//
//            // start the scheduler
//            scheduler.start();
//
//        } catch (SchedulerException e) {
//            LOG.info(e.getMessage());
//        } catch (Exception e) {
//            LOG.info(e.getMessage());
//        }
//    }
}
