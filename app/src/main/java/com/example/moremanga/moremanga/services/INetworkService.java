package com.example.moremanga.moremanga.services;

import java.util.Map;

public interface INetworkService {
    INetworkService GetQuery(String _url);
    NetworkService WithQuery(Map<String, String> _query);
    NetworkService WithBody(Object _body);
    NetworkService WithHeaders(Map<String, String> _headres);
    void GET(String _url);
    void PUT(String _url);
    void POST(String _url);
    void PATCH(String _url);
    void DELETE(String _url);
    void GET();
    void PUT();
    void POST();
    void PATCH();
    void DELETE();
}
