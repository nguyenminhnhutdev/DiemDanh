package com.example.myapplication.Data.remote;

public class    ApiUtils {
    public static final String BASE_URL = "https://diemdanhotpapi.azurewebsites.net";//Tien ich cua retrofit 2 giup tao utl co so

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
