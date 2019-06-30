package br.com.exemplo.Jobs;

import br.com.exemplo.Annotations.Intervalo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Iterator;
import java.util.Set;

public class JobListener implements ServletContextListener {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public void contextInitialized(ServletContextEvent event) {

        this.log.info("########## Iniciando o Crontab ##########");
        try {

            Reflections reflections = new Reflections("br.com.exemplo.Jobs");
            Set<Class<? extends Job>> classes = reflections.getSubTypesOf(Job.class);

            Iterator<Class<? extends Job>> iterator = classes.iterator();
            while (iterator.hasNext()) {
                Class<? extends Job> j = iterator.next();

                JobDetail job = JobBuilder.newJob(j).withIdentity(j.getName(), "Site").build();
                Intervalo intervalo = j.getAnnotation(Intervalo.class);

                if (intervalo == null) {
                    return;
                }
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(j.getName(), "Site")
                        .withSchedule(CronScheduleBuilder.cronSchedule(intervalo.value()))
                        .build();
                Scheduler scheduler =
                        new StdSchedulerFactory().getScheduler();
                scheduler.scheduleJob(job, trigger);
                scheduler.start();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

