package mvp.Json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

	private static ObjectMapper objMapper = defaultObjMapper();
	
	private static ObjectMapper defaultObjMapper() 
	{
		return new ObjectMapper();
	}
	
	public static JsonNode parse(String src) throws IOException 
	{
		return objMapper.readTree(src);
	}
	
	public static <C> C fromJson(JsonNode node, Class<C> className) throws JsonProcessingException {
		return objMapper.treeToValue(node, className);
	}
	
	public static JsonNode toJson(Object obj) {
		return objMapper.valueToTree(obj);
	}
	
	public static String stringify(JsonNode json) {
		ObjectWriter objWriter = objMapper.writer();
		objWriter = objWriter.with(SerializationFeature.INDENT_OUTPUT);
		try {
			return objWriter.writeValueAsString(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
