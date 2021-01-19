package fr.training.spring.library.batch.exportjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class CustomJobListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomJobListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);

        String msg = new StringJoiner(" : ", jobExecution.getJobInstance().toString()+"[", "]" )
                .add("status ="+jobExecution.getStatus().toString())
                .toString();
        LOGGER.info("custom Job Listener: job has finished: "+msg);

    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
        if(jobExecution.getStatus()== BatchStatus.STARTED){
            LOGGER.info("custom Job Listener: job is started: "+jobExecution.getJobInstance().toString());
        }
    }
}
