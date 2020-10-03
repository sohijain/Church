package com.church.com;

import com.church.com.model.BasicResponse;
import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;


public interface NetworkInterface {
    /*http://www.awesomemedia.in/church/webservice/signup?name=test%20name&mobile=789456123&email=test@gmail.com&password=123465&lat=22.5651&lon=789&register_id=ab55svhsdhgh*/
/*    @GET(WebService.SIGNUP)
    Observable<BasicResponse> createUser(@Field("name") String name, @Field("mobile") String mobile, @Field("email") String email, @Field("password") String password, @Field("lat") String lat, @Field("lon") String lon, @Field("register_id") String register_id);
 */
    @GET(WebService.SIGNUP)
    Observable<BasicResponse> createUser(@QueryMap Map<String, String> option);

    @GET(WebService.LOGIN)
    Observable<BasicResponse> login(@QueryMap Map<String, String> option);
    @GET(WebService.CHANGE_PASSWORD)
    Observable<BasicResponse> changePassword(@QueryMap Map<String, String> option);
    @GET(WebService.SOCIAL_LOGIN)
    Observable<BasicResponse> socialLogin(@QueryMap Map<String, String> option);
    @GET(WebService.FORGOT_PASSWORD)
    Observable<BasicResponse> forgotPassword(@QueryMap Map<String, String> option);
    @GET(WebService.GET_PROFILE)
    Observable<BasicResponse> getProfile(@QueryMap Map<String, String> option);
    @GET(WebService.UPDATE_PROFILE)
    Observable<BasicResponse> updateProfile(@QueryMap Map<String, String> option);
    @GET(WebService.GET_CATEGORY)
    Observable<BasicResponse> getCategory(@QueryMap Map<String, String> option);
    @GET(WebService.GET_INFO)
    Observable<BasicResponse> getInfo(@QueryMap Map<String, String> option);
    @GET(WebService.BANNER)
    Observable<BasicResponse> benner(@QueryMap Map<String, String> option);
/*
    //   @FormUrlEncoded
    @POST(WebService.ADD_CHAT)
    Call<ResponseBody> addChat(@Body RequestBody file);

    @Multipart
    @POST(WebService.ADD_CHAT)
    Call<ResponseBody> addChat(@PartMap() Map<String,
            RequestBody> partMap, @Part MultipartBody.Part filePart);

    @POST(WebService.ADD_CHAT)
    Observable<AddChatResponse> addChatOnOurServer(@Body RequestBody file);

    @POST(WebService.ADD_USER)
    Observable<BasicResponse> addUser(@Body RequestBody file);


    @GET(WebService.BLOCK_USER)
    Observable<BasicResponse> addBlockUnblockUser(@QueryMap Map<String, String> option);


    @GET(WebService.GET_BLOCK_USER)
    Observable<BlockUserListResponse> getBlockUser(@QueryMap Map<String, String> option);

    @POST(WebService.UPDATE_USER)
    Observable<BasicResponse> updateUser(@Body RequestBody file);

    @GET(WebService.GET_USER_PROFILE)
    Observable<ProfileResponse> getUserProfile(@QueryMap Map<String, String> option);

    @FormUrlEncoded
    @POST(WebService.SAVE_FIREBASE_TOKEN)
    Observable<BasicResponse> saveToken(@Field("username") String username, @Field("token") String token);

    @GET(WebService.GET_CHAT)
    Observable<ChatListResponse> getChatList(
            @QueryMap Map<String, String> option);

    @GET(WebService.GET_USER_STATUS)
    Observable<UserStatusResponse> getUserStatus(@QueryMap Map<String, String> option);

    @GET(WebService.GET_USER_CHAT_LIST)
    Observable<UserChatListResponse> getUserChatList(@QueryMap Map<String, String> option);

    @GET(WebService.GET_GROUP_USER_LIST)
    Observable<GroupUserListResponse> getGroupUserList(@QueryMap Map<String, String> option);

    @GET(WebService.DELETE_ONE_TO_ONE_CHAT)
    Observable<BasicResponse> deleteOntToOneChat(@QueryMap Map<String, String> option);

    @GET(WebService.GET_USER_CONTACT_LIST)
    Observable<ContactListResponse> getUserContactList(@QueryMap Map<String, String> option);

    // @FormUrlEncoded
    @POST(WebService.ADD_USER_ON_GROUP)
    Observable<BasicResponse> addMembersOnGroup(@Body JsonObject locationPost);

    @POST(WebService.UPDATE_USER_ON_GROUP)
    Observable<BasicResponse> updateUserGroup(@Body JsonObject jsonObject);

    @POST(WebService.UPDATE_USER_ROLE_ON_GROUP)
    Observable<BasicResponse> updateUserRoleOnGroup(@Body JsonObject jsonObject);

    @POST(WebService.UPDATE_GROUP_NAME)
    Observable<BasicResponse> updateGroupName(@Body JsonObject jsonObject);

    @POST(WebService.ADD_GROUP_ICON)
    Observable<UpdateGroupIconResponse> addGroupIcon(@Body RequestBody file);

    @POST(WebService.PUSHER_EVENT_TRIGGER)
    Observable<BasicResponse> pusherEventTrigger(@Body JsonObject jsonObject);*/
}

