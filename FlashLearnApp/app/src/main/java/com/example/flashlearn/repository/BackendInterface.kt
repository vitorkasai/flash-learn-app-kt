package com.example.flashlearn.repository

interface BackendInterface {

    @GET("board")
    suspend fun getBoards() : List<Board>

    @POST
    suspend fun addBoard(@Body board: Board): Board

    @GET("board/{iid}")
    suspend fun getBoard(@Path("iid") iid: Long): Board

    @DELETE("board/{iid}")
    suspend fun deleteBoard(@Path("iid") iid: Long): GeneralResponse

    @POST("board/{iid}/messages")
    suspend fun postMessage(@Path("iid") iid: Long, @Body message: Message): GeneralResponse

    // only for testing
    @GET("reset")
    suspend fun reset(): GeneralResponse

    @GET("board/{iid}/messages")
    suspend fun getMessages(@Path("iid") iid: Long): List<Message>

}