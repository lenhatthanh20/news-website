package com.lenhatthanh.blog.application.usecase;

import com.lenhatthanh.blog.domain.article.Author;
import com.lenhatthanh.blog.domain.repository.AuthorRepositoryInterface;
import com.lenhatthanh.blog.infrastructure.restapi.requestmodel.CreateAuthorRequest;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CreateAuthor {
    public final String TOPIC = "authors";
    public final String EVENT = "created";

    private AuthorRepositoryInterface authorRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void execute(CreateAuthorRequest authorRequest) {
        Author author = new Author(
                UUID.randomUUID().toString(),
                authorRequest.getName(),
                authorRequest.getEmail()
        );

        authorRepository.save(author);
        this.syncToQuerySide(author);
    }

    private void syncToQuerySide(Author author) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(this.TOPIC, this.EVENT, author.toString());
        kafkaTemplate.send(producerRecord);
    }
}
