/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.DanhMucMonAn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TheKiet
 */
public class Helper {

    private static final String USER_AGENT = "Mozilla/5.0";

    public String pushData(String url, String dataJson, String action) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(action);
            con.setRequestProperty("User-Agent", USER_AGENT);
             con.setRequestProperty("Content-Type", "application/json");
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(dataJson.getBytes());

            os.flush();
            os.close();
            int responseCode = con.getResponseCode();
           if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

               return response.toString();
            } else {
                return "error";
            }
        } catch (Exception ex) {
            return "error";
        }
    }

    public String getData(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();
            } else {
                return "error";
            }

        } catch (Exception e) {
            return "error";
        }
    }

    public List<ChiNhanh> parseChiNhanh(String objectJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //Set pretty printing of json
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            TypeReference<List<ChiNhanh>> mapType = new TypeReference<List<ChiNhanh>>() {
            };
            List<ChiNhanh> jsonToBranchList = objectMapper.readValue(objectJson, mapType);

            return jsonToBranchList;
        } catch (IOException ex) {
            return null;
        }
    }

    public List<DanhMucMonAn> parseDanhMucMonAn(String objectJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //Set pretty printing of json
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            TypeReference<List<DanhMucMonAn>> mapType = new TypeReference<List<DanhMucMonAn>>() {
            };
            List<DanhMucMonAn> jsonToDanhMucMonAnList = objectMapper.readValue(objectJson, mapType);

            return jsonToDanhMucMonAnList;
        } catch (IOException ex) {
            return null;
        }
    }

    public String parseClassToJson(Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String arrayToJson = objectMapper.writeValueAsString(data);
            return arrayToJson;

        } catch (JsonProcessingException ex) {
            return "";
        }
    }
}
