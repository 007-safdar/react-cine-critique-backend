package dev.sxfdxr.springmovies;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movies")
/*
 *What did we do here ? Defined the end point and called the method in the service layer which connects with the db via repository layer
 */
public class springmoviesController {
    //Create reference of service layer
    @Autowired 
    private movieService MovieService;

    @GetMapping
    public ResponseEntity<List<movies>> getAllmovies(){
        return new ResponseEntity<List<movies>>(MovieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<movies>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity <Optional<movies>>(MovieService.singleMovie(imdbId),HttpStatus.OK);
    }
//Error faced here was ambiguous handler methods mapped, as path were same for both either we can change path or add query param
    @GetMapping("/title/{title}")
    public ResponseEntity<Optional<movies>> getSingleMoviebyTitle(@PathVariable String title){
        return new ResponseEntity<Optional<movies>>(MovieService.singleMovieByTitle(title), HttpStatus.OK);
    }
    
     @GetMapping("/title")
    public ResponseEntity<Optional<movies>> getSingleMoviebyTitleParam(@RequestParam(value="title") String title){
        return new ResponseEntity<Optional<movies>>(MovieService.singleMovieByTitle(title), HttpStatus.OK);
    }
     

    @GetMapping("/releaseDate/{releaseDate}")
        public ResponseEntity<Optional<movies>> getMoviesByReleaseDate(@PathVariable String releaseDate){
            return new ResponseEntity<Optional<movies>>(MovieService.moviesByReleaseDate(releaseDate), HttpStatus.OK);
        }
    
}
