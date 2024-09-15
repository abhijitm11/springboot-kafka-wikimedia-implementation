package example.springboot.consumer.wiki.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wikimedia_changes")
public class WikimediaData {
    @Id
    private String id;
    private String wikiEventData;
}
