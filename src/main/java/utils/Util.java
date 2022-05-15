package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Response;

import static java.util.stream.Collectors.joining;

public class Util {
	private static final Gson GSON = new GsonBuilder().create();

	
	public static String readInputStream(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
	}
	
	public static void setResponse(HttpServletResponse resp, String result, String errorCode, String description) throws IOException {
		Response response = new Response();
		response.setResult(result);
		response.setErrorCode(errorCode);
		response.setDescription(description);
		String json = GSON.toJson(response);
		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}

}
