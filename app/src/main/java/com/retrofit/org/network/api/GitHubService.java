package com.retrofit.org.network.api;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by hp on 2015/12/19.
 */
public interface GitHubService {
    @GET("repos/{user}/{project}")
        Call<ResponseBody> listByUser(@Path("user")String user,@Path("project")String project);
        }
