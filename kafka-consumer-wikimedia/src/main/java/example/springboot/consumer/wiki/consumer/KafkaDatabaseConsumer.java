package example.springboot.consumer.wiki.consumer;

import example.springboot.consumer.wiki.model.WikimediaData;
import example.springboot.consumer.wiki.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    public WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics = "wikimedia_recent_change", groupId = "myGroup")
    public void consume(String eventMessage) {
        log.info(String.format("Message received -> %s", eventMessage));
        WikimediaData wikiData = new WikimediaData();
        wikiData.setWikiEventData(eventMessage);
        wikimediaDataRepository.save(wikiData);
    }
}
