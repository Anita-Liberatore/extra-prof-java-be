package utils;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import response.Response;

public class Util {
	private static final Gson GSON = new GsonBuilder().create();

	
	public static String readInputStream(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
	}
	
	public static void setResponse(HttpServletResponse resp, String result, String errorCode, String description) throws IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4000");
		Response response = new Response();
		response.setResult(result);
		response.setErrorCode(errorCode);
		response.setDescription(description);
		String json = GSON.toJson(response);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}
	
	public static boolean checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return req.getSession() != null ? true : false;
	}
	
	

}
