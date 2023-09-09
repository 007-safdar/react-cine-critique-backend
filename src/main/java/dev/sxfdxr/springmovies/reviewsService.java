package dev.sxfdxr.springmovies;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;



@Service
public class reviewsService {
    @Autowired
    private reviewsRepository ReviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public reviews createReview(String reviewBody, String imdbId){
        //Business logic here is divided by 2 Get the reviewbody alone from the response body and insert in movie repository
        //There are 2 ways to talk to db one is through Repsotory another is through mongotemplate
        //Create review object and pass the response body as review body

        reviews Reviews= ReviewRepository.insert(new reviews(reviewBody));
        //here in api's usually we return the inserted object in the db for testing, here we're basically just returning the review
        // Here we're using custom constructor to only return review body. This constructor is defined in reviews class
    

        mongoTemplate.update(movies.class)
                    .matching(Criteria.where("imdbId").is(imdbId))
                    .apply(new Update().push("reviewIds").value(Reviews))
                    .first();
                    ///The above is one way else we can create a query add Criteria, create update we wanna make in object and enclose all this inside.update(query,tobeinserted,classname)
        return Reviews;
    }
//Delete review by id and delete all

    public String deleteMovieReviews(ObjectId id) {
        ReviewRepository.deleteById(id);
            return "Deleted Successfully";
    }

    

    public String deleteAllMovieReviews(){
        ReviewRepository.deleteAll();
        return "All movies Deleted Successfully";
    }


    //Write code to delete reviews from movies collection as well
    /*
     * Business Requirement here is to create an API which takes 
     * imdbId as path variable and then deletes the review in reviews collection as 
     * well as movies collection
     Steps: Divide into 2
     First: Delete review collection
     Second: Delete revewIds array 
     Before this add a common identifier between these 2 so we can fetch it
     Initially fetch from path variable the imdbId, go to the approprirate movie then inside review id delete the appropriate one
     Write a delete api for all the review ids as well for clean up
     //19 jul plan 
     Create the delete api for removing reviewIds fully and partially via imdbid in movies class--> This is done
     For tom create Update api to complete CRUD, will go to react post that
     //
     */

    public movies deleteSingleMovieReview(String imdbId, String reviewBody){
        Query query= new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));
        Update update = new Update();
        update.pull("reviewIds",new Query(Criteria.where("body").is(reviewBody)));
        FindAndModifyOptions options= new FindAndModifyOptions().returnNew(true);
    return mongoTemplate.findAndModify(query, update,options, movies.class);
    //Use options in findandmodify to return new object or previous (by default)

    }

public movies updateMovieReviews(String imdbId,String newReviewBody,String oldReviewBody){
        Query query= new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId) );
         Update update = new Update();
         update.push("reviewIds",newReviewBody);
         FindAndModifyOptions options=new FindAndModifyOptions().returnNew(true);
        return mongoTemplate.findAndModify(query,update,options,movies.class);

    }


}
//Questions to see tom. Why do we need a constructor just for body and whats insert doing here

