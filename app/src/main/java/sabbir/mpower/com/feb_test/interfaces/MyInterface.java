package sabbir.mpower.com.feb_test.interfaces;

import sabbir.mpower.com.feb_test.model.UserData;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Sabbir on 09,February,2017
 * mPower Social
 * Dhaka
 */
public interface MyInterface {
    @GET("/users/himelhimu")
     void getUser(Callback<UserData>userDataCallback);
}
