package com.nenamja.volunteer.data.remote;


import com.nenamja.volunteer.data.Memo;
import com.nenamja.volunteer.net.NenamjaResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
* interface 기반의 Request
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:19 PM
**/
public interface NenamjaServiceApi {

    @GET("/v1.0/memos") //메모에서 전체리스트 조회 처리
    Observable<NenamjaResponse<NenamjaListResponse>> getMemoList();

    @GET("/v1.0/memos/{memoId}") //메모 상세조회 처리
    Observable<NenamjaResponse<Memo>> getMemoDetail(@Path("memoId") String memoId);

    @POST("/v1.0/memos/delete") //메모 삭제
    Observable<NenamjaResponse> removeMemo(@Body String[] memoIdList);

    @POST("/v1.0/memos/delete-all") //메모 전체 삭제
    Observable<NenamjaResponse> removeAllMemo();

    @DELETE("/v1.0/memos/recent-searches")// 메모 최근검색어 전체 삭제
    Observable<NenamjaResponse<Boolean>> deleteRecentData();

    @DELETE("/v1.0/memos/recent-searches/{keyword}")// 메모 최근검색어 선택 삭제
    Observable<NenamjaResponse<Boolean>> deleteRecentData(@Path("keyword") String keyword);

    @GET("/v1.0/memos/search/{keyword}")// 메모 검색어 조회 처리
    Observable<NenamjaResponse<NenamjaListResponse>> searchMemoList(@Path("keyword") String keyword);
}