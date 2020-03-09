package com.example.linkspc.appnhac.Service;

public class APIService {
    private  static  String base_url="https://linhyol2019.000webhostapp.com/server/";
    public static  Dataservice getseservice()
    {
        return  APIRetofitClient.getClient(base_url).create(Dataservice.class);
    }
}
