package example.springboot.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import example.springboot.wikimedia.WikimediaChangesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger log = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        // send messages to a Kafka topic using a streaming source.

        // Define the Kafka topic to which messages will be sent.
        String topic = "wikimedia_recent_change";

        // Create an instance of WikimediaChangesHandler, which is a custom implementation of EventHandler. This handler is responsible for processing events (messages) received from the streaming source and sending them to the specified Kafka topic.
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        // Create a builder for EventSource, which is used to configure and build an instance of EventSource that connects to the streaming URL and uses the provided EventHandler to process incoming events.
        EventSource eventSource = new EventSource.Builder(eventHandler, URI.create(url)).build();

        // Starts the EventSource instance. This action initiates the connection to the streaming URL and begins receiving events from the Wikimedia stream. The EventSource will run in its own thread, continuously processing incoming events.
        eventSource.start();       // starting the thread
        TimeUnit.MINUTES.sleep(10);

    }


}
