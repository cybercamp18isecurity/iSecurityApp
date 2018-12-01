package com.telefonica.lucferbux.isecurityapp.networking

import com.telefonica.lucferbux.isecurityapp.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface RetrieveService {

/*    @Headers(
        "Content-Type: application/json"
        //"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjRhZjdlZi00ZDg1LTRlN2UtYjViNi0wNTA2NTNkNGI4ODUiLCJleHAiOjE1NDUyMTQ0OTgsImlhdCI6MTU0MjYyMjQ5OCwiaXNzIjoiYm90b24iLCJ1c2VyX25hbWUiOiJjaGVtYSIsInVzZXJfdXNlcm5hbWUiOiJjaGVtYSIsImFjY291bnRfYWRkciI6IjE4R3hTaHNMNkFTak45Y2Y0S1RhNHRwdXBnNjNGenNNenBMUFhpIiwidXNlcl9yb2xlIjoiYXBwIn0.cxEHrZFKcLSIieKrzHdxWk47XY_B9gJhmSGZvV7z11o"
    )*/
    @GET("/users")
    fun retreiveUsers(): Observable<UserInfoList>

/*    @Headers(
        "Content-Type: application/json"
        //"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjRhZjdlZi00ZDg1LTRlN2UtYjViNi0wNTA2NTNkNGI4ODUiLCJleHAiOjE1NDUyMTQ0OTgsImlhdCI6MTU0MjYyMjQ5OCwiaXNzIjoiYm90b24iLCJ1c2VyX25hbWUiOiJjaGVtYSIsInVzZXJfdXNlcm5hbWUiOiJjaGVtYSIsImFjY291bnRfYWRkciI6IjE4R3hTaHNMNkFTak45Y2Y0S1RhNHRwdXBnNjNGenNNenBMUFhpIiwidXNlcl9yb2xlIjoiYXBwIn0.cxEHrZFKcLSIieKrzHdxWk47XY_B9gJhmSGZvV7z11o"
    )*/
    @GET("/devices")
    fun retrieveDevice(): Observable<DeviceInfoList>

/*    @Headers(
        "Content-Type: application/json"
        //"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjRhZjdlZi00ZDg1LTRlN2UtYjViNi0wNTA2NTNkNGI4ODUiLCJleHAiOjE1NDUyMTQ0OTgsImlhdCI6MTU0MjYyMjQ5OCwiaXNzIjoiYm90b24iLCJ1c2VyX25hbWUiOiJjaGVtYSIsInVzZXJfdXNlcm5hbWUiOiJjaGVtYSIsImFjY291bnRfYWRkciI6IjE4R3hTaHNMNkFTak45Y2Y0S1RhNHRwdXBnNjNGenNNenBMUFhpIiwidXNlcl9yb2xlIjoiYXBwIn0.cxEHrZFKcLSIieKrzHdxWk47XY_B9gJhmSGZvV7z11o"
    )*/
    @GET("/domains")
    fun retrieveDomains(): Observable<DomainInfoList>

/*    @Headers(
        "Content-Type: application/json"
        //"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjRhZjdlZi00ZDg1LTRlN2UtYjViNi0wNTA2NTNkNGI4ODUiLCJleHAiOjE1NDUyMTQ0OTgsImlhdCI6MTU0MjYyMjQ5OCwiaXNzIjoiYm90b24iLCJ1c2VyX25hbWUiOiJjaGVtYSIsInVzZXJfdXNlcm5hbWUiOiJjaGVtYSIsImFjY291bnRfYWRkciI6IjE4R3hTaHNMNkFTak45Y2Y0S1RhNHRwdXBnNjNGenNNenBMUFhpIiwidXNlcl9yb2xlIjoiYXBwIn0.cxEHrZFKcLSIieKrzHdxWk47XY_B9gJhmSGZvV7z11o"
    )*/
    @GET("/alerts")
    fun retrieveAlerts(): Observable<AlertInfoList>

/*    @Headers(
        "Content-Type: application/json"
        //"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNjRhZjdlZi00ZDg1LTRlN2UtYjViNi0wNTA2NTNkNGI4ODUiLCJleHAiOjE1NDUyMTQ0OTgsImlhdCI6MTU0MjYyMjQ5OCwiaXNzIjoiYm90b24iLCJ1c2VyX25hbWUiOiJjaGVtYSIsInVzZXJfdXNlcm5hbWUiOiJjaGVtYSIsImFjY291bnRfYWRkciI6IjE4R3hTaHNMNkFTak45Y2Y0S1RhNHRwdXBnNjNGenNNenBMUFhpIiwidXNlcl9yb2xlIjoiYXBwIn0.cxEHrZFKcLSIieKrzHdxWk47XY_B9gJhmSGZvV7z11o"
    )*/
    @GET("/summary")
    fun retrieveSummary(): Observable<SummaryInfo>

}