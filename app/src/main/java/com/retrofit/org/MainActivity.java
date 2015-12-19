package com.retrofit.org;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.retrofit.org.network.NetworkController;
import com.retrofit.org.network.api.GitHubService;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GitHubService service = NetworkController.get(GitHubService.class);
        Call<ResponseBody> call = service.listByUser("taoruizuo","HelloWorld");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                String user= null;
                try {
                    user = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(MainActivity.class.getName(), "" + user);
                ((TextView) findViewById(R.id.txt_content)).setText(user);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
