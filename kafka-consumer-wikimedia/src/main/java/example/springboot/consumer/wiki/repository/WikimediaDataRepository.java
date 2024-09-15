package example.springboot.consumer.wiki.repository;

import example.springboot.consumer.wiki.model.WikimediaData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WikimediaDataRepository extends MongoRepository<WikimediaData, Long> {
}
