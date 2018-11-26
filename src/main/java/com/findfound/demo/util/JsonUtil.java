package com.findfound.demo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Utility class for JSON to object conversion and vice versa.
 *
 * Note : please review the ObjectMapper configuration before using this utility for your module.
 *
 * @author Raghu M
 *
 */
public class JsonUtil {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	private static final ObjectWriter OBJECT_WRITER_DEFAULT = OBJECT_MAPPER.writer();

	static {
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		OBJECT_MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

	}

	public static final <T> T fromJson(final byte[] s, final Class<T> type) throws Exception {
		final ObjectReader reader = OBJECT_MAPPER.readerFor(type);
		return reader.readValue(s);
	}

	public static final <T> T fromJson(final String s, final Class<T> type) throws Exception {
		final ObjectReader reader = OBJECT_MAPPER.readerFor(type);
		return reader.readValue(s);
	}

	public static final String toJsonString(final Object o) throws Exception {
		return OBJECT_WRITER_DEFAULT.writeValueAsString(o);
	}

	public static final String subJson(final String json, final String identifier) throws Exception {
		final JsonNode node = OBJECT_MAPPER.readValue(json, JsonNode.class);
		return node.findValues(identifier).get(0).asText();
	}

	public static final <T> T convertValue(final Object obj, final Class<T> type) {
		return OBJECT_MAPPER.convertValue(obj, type);
	}

	public static final <T> List<T> parseJsonArray(final String json, final Class<T> type) throws IOException,
			ClassNotFoundException {
		Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + type.getName() + ";");
		T[] objects = OBJECT_MAPPER.readValue(json, arrayClass);
		return Arrays.asList(objects);
	}

	// TODO: Need to optimize this logic.
	public static final String toJsonArray(final List<String> l) {
		final StringBuilder result = new StringBuilder("[");

		final Iterator<String> it = l.iterator();
		int index = 0;
		while (it.hasNext()) {
			if ((index > 0) && it.hasNext()) {
				result.append(",");
			}
			result.append(it.next());
			++index;
		}
		result.append("]");

		return result.toString();
	}

	// get uuid
	public static final String getUUID(final String str) {

		byte[] bytes = null;
		try {
			bytes = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UUID uuid = UUID.nameUUIDFromBytes(bytes);
		return uuid.toString();
	}
	
	
}
