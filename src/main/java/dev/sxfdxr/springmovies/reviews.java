package dev.sxfdxr.springmovies;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Document(collection="reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class reviews {
    
    @Id
    private ObjectId id;
    private String body;
    private String reviewIdentifier;

    public reviews(String body){
        this.body=body;
        Date date = new Date();
        Timestamp timestamp= new Timestamp(date.getTime());
        this.reviewIdentifier=timestamp.toString();
    
    }

    
    

}
