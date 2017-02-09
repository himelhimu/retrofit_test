package sabbir.mpower.com.feb_test.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sabbir.mpower.com.feb_test.StackOverFlowQuestions;

/**
 * Created by Sabbir on 09,February,2017
 * mPower Social
 * Dhaka
 */
public interface StackAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverFlowQuestions> getAllQuestions(@Query("tagged") String tags);
}
