package es.tipolisto.breeds.services;

import java.util.List;

import es.tipolisto.breeds.model.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IPostsApiService {
    @GET("posts")
    Call<List<Posts>> getPosts();
}
