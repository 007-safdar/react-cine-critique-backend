package dev.sxfdxr.springmovies;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/reviews")

public class reviewsController {

  @Autowired
  private reviewsService reviewsService;

  @PostMapping
  public ResponseEntity<reviews> putSingleReview(@RequestBody Map<String,String>payload){
    return new ResponseEntity<reviews>(reviewsService.createReview(payload.get("reviewBody"),payload.get("imdbId")),HttpStatus.CREATED);
  }
//Delete single review by object id
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSingleMovieReview(@PathVariable ObjectId id){
    return new ResponseEntity<String>(reviewsService.deleteMovieReviews(id),HttpStatus.OK);
  }
//Delete all review 
  @DeleteMapping
  public ResponseEntity<String> deleteAllReviews(){
    return new ResponseEntity<String>(reviewsService.deleteAllMovieReviews(),HttpStatus.OK);

  }
//Delete review in movie collection
@DeleteMapping("/deleteReview")
public ResponseEntity<movies> deleteSingleReview (@RequestBody Map<String,String>payload)
{
    return new ResponseEntity<>(reviewsService.deleteSingleMovieReview(payload.get("imdbId"),payload.get("reviewBody")),HttpStatus.OK);
}

@PutMapping("/update")
public ResponseEntity<movies> updateReview(@RequestBody Map<String,String>payload){
    return new ResponseEntity<movies>(reviewsService.updateMovieReviews(payload.get("imdbId"),payload.get("newReviewBody"),payload.get("oldreview")),HttpStatus.OK);
}
}

    

