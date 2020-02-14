package fr.epsi.applimspr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.epsi.applimspr.model.Utilisateur;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import fr.epsi.applimspr.SynchronousCallAdapterFactory;
public interface JsonPlaceHolderApi {

//    @GET("posts")
//    Call<List> getPosts(
//            @Query("userId") Integer[] userId,
//            @Query("_sort") String sort,
//            @Query("_order") String order
//    );
//
//    @GET("posts")
//    Call<List> getPosts(@QueryMap Map<String, String> parameters);
//
//    @GET("posts/{id}/comments")
//    Call<List> getComments(@Path("id") int postId);
//
//    @GET
//    Call<List> getComments(@Url String url);

    @POST("login")
    Call<Utilisateur> connexion(@QueryMap Map<String, String> parameters);


//    @FormUrlEncoded
//    @POST("login")
//    Call<Utilisateur> connexion(
//            @Field("login") String login,
//            @Field("password") String password,
//            @Field("body") String body
//    );
//    @FormUrlEncoded
//    @POST("posts")
//    Call<List> createPost(
//            @Field("userId") int userId,
//            @Field("title") String title,
//            @Field("body") String text
//    );

//    @FormUrlEncoded
//    @POST("posts")
//    Call<List> createPost(@FieldMap Map<String, String> fields);
}
