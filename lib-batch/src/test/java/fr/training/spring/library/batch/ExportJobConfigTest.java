package fr.training.spring.library.batch;

import fr.training.spring.library.batch.exportjob.ExportJobConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {SpringBatchApp.class, ExportJobConfiguration.class})
public class ExportJobConfigTest {
    private static final Logger LOG = LoggerFactory.getLogger(ExportJobConfigTest.class);

    /** directory for temporary test files */
    private static final String TMP_DIR = "./target/tmp";

    /**
     * exportJob of type Job
     */
    @Autowired
    @Qualifier("exportJob")
    private Job exportJob;

    /**
     * jobLauncher of type JobLauncher
     */
    @Autowired
    private JobLauncher jobLauncher;

    private File fileTmp;
    /**
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        // setup test data
        LOG.debug("Delete tmp directory");
        FileSystemUtils.deleteRecursively(new File(TMP_DIR));
    }

    @Test
    public void testJob() throws Exception {
        //Given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String filename =new StringBuilder().append(TMP_DIR).append("/libraryTest").append(LocalDateTime.now().format(formatter)).append(".txt").toString();
        final File targetFile = new File(filename);
        final JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("output-file", targetFile.getAbsolutePath());
        // when
        final JobExecution jobExecution = getJobLauncherTestUtils(exportJob)
                .launchJob(jobParametersBuilder.toJobParameters());

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        assertThat(targetFile.exists()).isTrue();
        System.out.println("file created :"+targetFile.getAbsolutePath());
    }

    private JobLauncherTestUtils getJobLauncherTestUtils(final Job job){
        final JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJob(job);
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        return jobLauncherTestUtils;
    }
}
