package com.example.moremanga.moremanga.services;

import android.os.AsyncTask;
import android.util.ArrayMap;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class NetworkService implements INetworkService {
    private String url;

    private Map<String, String> query;
    private Object body;
    private Map<String, String> headres;

    public NetworkService() {
        headres = new ArrayMap<String, String>();
    }

    public NetworkService(String _url) {
        this();
        url = _url;
    }

    public NetworkService GetQuery(String _url) {
        NetworkService newService = new NetworkService();
        newService.url = _url;
        return newService;
    }

    public NetworkService WithQuery(Map<String, String> _query) {
        query = _query;
        return this;
    }

    public NetworkService WithBody(Object _body) {
        body = _body;
        return this;
    }

    public NetworkService WithHeaders(Map<String, String> _headres) {
        headres = _headres;
        return this;
    }

    public void GET(String _url) {
        new NetworkService(_url).GET();
    }
    public void PUT(String _url) {
        new NetworkService(_url).PUT();
    }
    public void POST(String _url) {
        new NetworkService(_url).POST();
    }
    public void PATCH(String _url) {
        new NetworkService(_url).PATCH();
    }
    public void DELETE(String _url) {
        new NetworkService(_url).DELETE();
    }

    public void GET() {
        new NetworkRunner("GET", query, null, headres).execute(url);
    }
    public void PUT() {
        new NetworkRunner("PUT", query, body, headres).execute(url);
    }
    public void POST() {
        new NetworkRunner("POST", query, body, headres).execute(url);
    }
    public void PATCH() {
        new NetworkRunner("PATCH", query, body, headres).execute(url);
    }
    public void DELETE() {
        new NetworkRunner("DELETE", query, null, headres).execute(url);
    }

    private class  NetworkRunner extends AsyncTask<String, Integer, String>  {
        public String ServerResponse;
        public String ResponseStatus;

        private String methodType;
        private Map<String, String> query;
        private Object body;
        private Map<String, String> headers;

        public NetworkRunner(String _methodType, Map<String, String>  _query, Object _body, Map<String, String> _header) {
            methodType = _methodType;
            query = _query;
            body = _body;
            headers = _header;
        }

        protected String doInBackground(String... urls) {
            URL url;
            String content = "";
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0] + "?" + getQuery());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(methodType);

                addBodyIfItsSet(urlConnection);
                addHeaders(urlConnection);

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK){
                    ServerResponse = readStream(urlConnection.getInputStream());
                    ResponseStatus = "success";
                }
                else {
                    ServerResponse = readStream(urlConnection.getInputStream());
                    ResponseStatus = "error";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(String result) {

        }

        private String getQuery() throws UnsupportedEncodingException {
            String queryParts = "";

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String line = String.format("key=%s", URLEncoder.encode(entry.getKey(), entry.getValue()));
                if (queryParts != "") {
                    queryParts += "&";
                }
                queryParts += line;
            }

            return queryParts;
        }

        private void addBodyIfItsSet(HttpURLConnection urlConnection) throws IOException {
            if (body != null) {
                Gson gson = new Gson();
                String jsonBody = gson.toJson(body);

                urlConnection.setRequestProperty("Content-Type","application/json");

                OutputStream op = urlConnection.getOutputStream();
                byte[] outputInBytes = jsonBody.getBytes("UTF-8");
                op.write(outputInBytes);
            }
        }

        private void addHeaders(HttpURLConnection urlConnection) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }
    }
}
