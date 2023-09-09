package dev.sxfdxr.springmovies;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
 * What did we do here ? Here we defined the connection between the repository layer and defined the methods here
 * This is later connected to controller layer
 */
//We write database access methods here
@Service
public class movieService {
        //Java its required to instantiate, this annotation does it for us
    @Autowired 
    private movieRepository MovieRepository;
    
    public List<movies> allMovies (){
        return MovieRepository.findAll();
        //findAll is defined in mongoRepository class
    }
    //Making it optional if the object id doesnt exist and the value fetched is null
    public Optional<movies> singleMovie(String imdbId){
        return MovieRepository.findByimdbId(imdbId);
    }
    
    public Optional<movies> singleMovieByTitle(String title){
        return MovieRepository.findByTitle(title);
    }

    public Optional<movies> moviesByReleaseDate(String releaseDate){
        return MovieRepository.findByreleaseDate(releaseDate);
    }
}
