package dev.sxfdxr.springmovies;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
//MongoRepository takes <T, unique id>
/*
This is the data access layer, we define the blueprint here (interface) and in service layer we'll define the methods to extract data
 * Interfaces are used for abstraction and loose coupling, will update here why we used repository as an interface.
 * By abstraction we only define the blue print and then we define the method else where in class which implements 
 * 
 * What did we do here ? We defined the blueprint and this is taken from MongoRepository inbuilt interface, we provided its required params ie object and objectId. This is later utilised in service layer
 */
public interface movieRepository extends MongoRepository<movies, ObjectId>{
    //Defining more methods here to get using imdbId
    Optional<movies> findByimdbId(String imdbId);
    Optional<movies> findByTitle(String title);
    Optional<movies> findByreleaseDate(String releaseDate);
}
