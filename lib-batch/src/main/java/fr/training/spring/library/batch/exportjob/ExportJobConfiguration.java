package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.batch.dto.LibraryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;

@Configuration
public class ExportJobConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportJobConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "exportJob")
    public Job exportJob(CustomJobListener listener, final Step exportStep) {
        return jobBuilderFactory.get("export-job")
                .validator(new DefaultJobParametersValidator(new String[]{"output-file"}, new String[]{}))
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(exportStep)
                .end().build();
    }

    @Bean
    public Step exportStep(final FlatFileItemWriter<LibraryDto> exportWriter) {
        return stepBuilderFactory.get("export-step").<LibraryDto, LibraryDto>chunk(10)
                .reader(exportReader())
                .processor(exportProcessor())
                .writer(exportWriter)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<LibraryDto> exportReader() {
        JdbcCursorItemReader<LibraryDto> readerJdbc = new JdbcCursorItemReader<>();
        readerJdbc.setDataSource(dataSource);
        readerJdbc.setSql("select lib.id as libid, lib.type_lib, lib.number, lib.street, lib.city, lib.postal_code, lib.name, lib.surname, " +
                "b.id, b.title, b.author, b.pages_numbers, b.genre_litteraire, b.isbn " +
                "from library lib " +
                "JOIN library_books lb on lb.library_jpa_id = lib.id "+
                "JOIN book b on b.id = lb.books_id");

        //readerJdbc.setSql("select lib.id, lib.type_lib, lib.number, lib.street, lib.city, lib.postal_code, lib.name, lib.surname from library lib");
        readerJdbc.setRowMapper(new LibraryRowMapper());
        return readerJdbc;
    }

    @Bean
    public ItemProcessor<LibraryDto, LibraryDto> exportProcessor() {
        return new ItemProcessor<LibraryDto, LibraryDto>() {
            @Override
            public LibraryDto process(LibraryDto libraryDto) throws Exception {
                LOGGER.info("processing {}", libraryDto);
                return libraryDto;
            }
        };
    }

    @StepScope // Mandatory for using jobParameters
    @Bean
    public FlatFileItemWriter<LibraryDto> exportWriter(@Value("#{jobParameters['output-file']}") String outputFile) {
        final FlatFileItemWriter<LibraryDto> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(outputFile));
        final DelimitedLineAggregator<LibraryDto> lineAggregator = new DelimitedLineAggregator<>();
        final BeanWrapperFieldExtractor<LibraryDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "typeLib", "number", "street", "city", "postalCode", "name", "surname", "bookDtos" });

        lineAggregator.setFieldExtractor(fieldExtractor);
        lineAggregator.setDelimiter(";");

        writer.setLineAggregator(lineAggregator);
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("id,typelib,number,street,city,postalCode,name,surname, bookDtos");
            }
        });

        return writer;
    }
}
