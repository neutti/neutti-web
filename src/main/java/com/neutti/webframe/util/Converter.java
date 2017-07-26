package com.neutti.webframe.util;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

/**
 * <p>
 * Utility class to perform conversions.
 * </p>
 * <p>
 * <a href="ConvertUtil.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author Matt Raible
 *         <a href="mailto:matt@raibledesigns.com">&lt;matt@raibledesigns.com&
 *         gt;</a>
 * @author Sergey Zubtsovskiy
 *         <a href="mailto:sergey.zubtsovskiy@blandware.com">&lt;sergey.
 *         zubtsovskiy@blandware.com&gt;</a>
 * @version $Revision: 1.19 $ $Date: 2006/08/03 10:07:33 $
 */
public final class Converter {
	// ~ Static fields/initializers
	// =============================================

	// ~ Methods
	// ================================================================

	/**
	 * Converts a ResourceBundle to a Map object.
	 *
	 * @param rb
	 *            a given resource bundle
	 * @return Map a populated map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map convertBundleToMap(ResourceBundle rb) {
		Map map = new HashMap();

		for (Enumeration keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			map.put(key, rb.getString(key));
		}

		return map;
	}

	/**
	 * Converts a ResourceBundle to a Properties object.
	 *
	 * @param rb
	 *            a given resource bundle
	 * @return Properties a populated properties object
	 */
	public static Properties convertBundleToProperties(ResourceBundle rb) {
		Properties props = new Properties();

		for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			props.put(key, rb.getString(key));
		}

		return props;
	}

	/**
	 * Converts list to string concatenating list elements with the delimiter
	 *
	 * @param list
	 *            List to convert to string
	 * @param delimiter
	 *            Delimiter to put between elements
	 * @return List members, delimited by specifed delimiter.
	 */
	@SuppressWarnings("rawtypes")
	public static String convertListToString(List list, String delimiter) {
		return convertListToString(list, delimiter, null);
	}

	/**
	 * Converts list to string concatenating list elements with the delimiter.
	 * Each element is additionally enclosed in <code>encloser</code> chars.
	 *
	 * @param list
	 *            List to convert to string
	 * @param delimiter
	 *            Delimiter to put between elements
	 * @param encloser
	 *            Character to enclose each element
	 * @return List members, delimited by specifed delimiter. Each element
	 *         enclosed with specified character
	 */
	@SuppressWarnings("rawtypes")
	public static String convertListToString(List list, String delimiter, char encloser) {
		return convertListToString(list, delimiter, new Character(encloser));
	}

	/**
	 * Converts list to string concatenating list elements with the delimiter.
	 * Each element is additionally enclosed in <code>encloser</code> chars.
	 *
	 * @param list
	 *            List to convert to string
	 * @param delimiter
	 *            Delimiter to put between elements
	 * @param encloser
	 *            Character to enclose each element
	 * @return List members, delimited by specifed delimiter. Each element
	 *         enclosed with specified character
	 */
	@SuppressWarnings("rawtypes")
	private static String convertListToString(List list, String delimiter, Character encloser) {

		if (list == null || list.size() == 0) {
			return new String();
		}

		StringBuffer sb = new StringBuffer();
		for (Iterator i = list.iterator(); i.hasNext();) {
			String next = String.valueOf(i.next());
			if (encloser != null) {
				next = encloser + next + encloser;
			}
			sb.append(next);
			if (i.hasNext()) {
				sb.append(delimiter);
			}
		}

		return sb.toString();
	}

	/**
	 * Converts string to list. The string is assumed to be a sequence of some
	 * elements separated with delimiter.
	 *
	 * @param string
	 *            String to convert to list
	 * @param delimiter
	 *            Delimiter of list elements
	 * @param trim
	 *            Whether or not to trim tokens befor putting them in list
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertStringToList(String string, String delimiter, boolean trim) {
		if (string == null || string.length() == 0) {
			return new LinkedList();
		}
		String[] members = string.split(delimiter);
		List list = Collections.synchronizedList(new LinkedList());
		for (int i = 0; i < members.length; i++) {
			String member = members[i];
			if (trim) {
				member = member.trim();
			}
			list.add(member);
		}
		return list;
	}

	/**
	 * Creates set from array of objects
	 *
	 * @param anArray
	 *            Array of objects to create set from
	 * @return Set that contains objects from array
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set convertArrayToSet(Object[] anArray) {
		Set set = new HashSet();
		for (int i = 0; i < anArray.length; i++) {
			set.add(anArray[i]);
		}
		return set;
	}

	/**
	 * Converts all String values in specified list to
	 * <code>java.lang.Long</code>. If there is instance of another than
	 * <code>java.lang.String</code> class is found,
	 * <code>java.lang.ClassCastException</code> will be thrown.
	 *
	 * @param values
	 *            List of values to convert to <code>java.lang.Long[]</code>
	 * @return <code>java.lang.Long[]</code>
	 */
	@SuppressWarnings("rawtypes")
	public static Long[] convertToLongArray(List values) {
		Long[] result = new Long[values.size()];
		for (int i = 0; i < values.size(); i++) {
			Object value = values.get(i);
			if (!(value instanceof String)) {
				throw new ClassCastException(
						"Unable to convert instance of class " + value.getClass().getName() + " to java.lang.Long");
			}
			result[i] = Long.valueOf((String) value);
		}
		return result;
	}

	/**
	 * Converts primitive array to array of objects. Each element in returned
	 * array will have run-time class equivalent to its primitive type (e.g.
	 * <code>java.lang.Integer</code> is object equivalent to <code>int</code>,
	 * <code>java.lang.Boolean</code> is object equivalent to
	 * <code>boolean</code>, etc.)
	 *
	 * @param primitiveArray
	 *            Array of primitives which needs to be converted to objects
	 * @return Array of object, each element is object equivalent to
	 *         corresponding primitive value
	 * @throws IllegalArgumentException
	 *             if specified argument is not a primitive array
	 */
	public static Object[] convertPrimitivesToObjects(Object primitiveArray) {
		if (primitiveArray == null) {
			return null;
		}

		if (!primitiveArray.getClass().isArray()) {
			throw new IllegalArgumentException("Specified object is not array");
		}

		if (primitiveArray instanceof Object[]) {
			throw new IllegalArgumentException("Specified object is not primitive array");
		}

		int length = Array.getLength(primitiveArray);
		Object[] result = new Object[length];
		for (int i = 0; i < length; i++) {
			result[i] = Array.get(primitiveArray, i);
		}

		return result;
	}

	/**
	 * Converts collection, specified in argument to the instance of
	 * <code>java.util.List</code>. Supported types include:
	 * <ul type="disc">
	 * <li>
	 * <code>java.util.Collection, java.util.Set, java.util.SortedSet, java.util.List</code>
	 * - result list will contain all elements from specified collection, set or
	 * list</li>
	 * <li><code>java.util.Enumeration</code> - result list will contain all
	 * elements from this enumeration in the same order</li>
	 * <li><code>java.util.Iterator</code> - result list will contain all
	 * elements from collection, iterated by this iterator in the same order
	 * </li>
	 * <li><code>java.util.Map, java.util.SortedMap</code> - result list will
	 * contain all entries (instances of <code>java.util.Map$Entry</code>)</li>
	 * <li><code>java.lang.String</code> - result list will contain all
	 * characters, each one wrapped in <code>java.lang.Character</code></li>
	 * <li><code>java.lang.Object[]</code> - result list will be dynamic
	 * equivalent for specified array</li>
	 * <li>any primitive array - result list will contain elements from array,
	 * each wrapped in instance of equivalent class (e.g.
	 * <code>java.lang.Integer</code> is object equivalent to <code>int</code>,
	 * <code>java.lang.Boolean</code> is object equivalent to
	 * <code>boolean</code>, etc.)</li>
	 * </ul>
	 * 
	 * @param collection
	 *            Collection to convert to list
	 * @return List, containing all elements from collection according to rules,
	 *         specified above
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertCollectionToList(Object collection) {

		if (collection == null) {
			return null;
		}

		List list = null;

		if (collection instanceof Collection) {
			list = new ArrayList((Collection) collection);
		} else if (collection instanceof Enumeration) {
			list = new ArrayList();
			Enumeration e = (Enumeration) collection;
			while (e.hasMoreElements()) {
				list.add(e.nextElement());
			}
		} else if (collection instanceof Iterator) {
			list = new ArrayList();
			Iterator i = (Iterator) collection;
			while (i.hasNext()) {
				list.add(i.next());
			}
		} else if (collection instanceof Map) {
			list = new ArrayList(((Map) collection).entrySet());
		} else if (collection instanceof String) {
			list = Arrays.asList(convertPrimitivesToObjects(((String) collection).toCharArray()));
		} else if (collection instanceof Object[]) {
			list = Arrays.asList((Object[]) collection);
		} else if (collection.getClass().isArray()) {
			list = Arrays.asList(convertPrimitivesToObjects(collection));
		} else {
			// type is not supported
			throw new IllegalArgumentException(
					"Class '" + collection.getClass().getName() + "' is not convertable to java.util.List");
		}

		return list;
	}

	/**
	 * 색상 정보 형태 변환
	 * 
	 * @param strColorValue(RGB
	 *            16진수) ex)ffffff -> java.awt.Color
	 * @return
	 */
	public static Color convertColorType1(String strColorValue) {
		int r = Integer.parseInt(strColorValue.substring(0, 2), 16);
		int g = Integer.parseInt(strColorValue.substring(2, 4), 16);
		int b = Integer.parseInt(strColorValue.substring(4, 6), 16);
		return new Color(r, g, b);
	}

	public static String convertToHtml(String text) {
		if (text == null)
			return null;
		return text.replaceAll("&", "&amp;").replaceAll("\u0020", "&nbsp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;").replaceAll("\n", "<br>");
	}

	public static StringBuffer convertToXmlString(String title, Map<String, Object> map)
			throws UnsupportedEncodingException {
		if (map == null)
			return null;
		StringBuffer buffy = new StringBuffer();
		buffy.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffy.append("<xml").append(" type=\"").append(title).append("\">");
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			if (map.get(key) instanceof Map) {
				buffy.append("<").append(key).append(">");
				@SuppressWarnings("unchecked")
				Map<String, Object> childMap = (Map<String, Object>) map.get(key);
				buffy.append(convertToTagString(childMap));
				buffy.append("</").append(key).append(">");
			} else {
				buffy.append("<").append(key).append(">");
				buffy.append(map.get(key) == null ? "" : URLUtil.encode(map.get(key).toString()));
				buffy.append("</").append(key).append(">");
			}
		}
		buffy.append("</xml>");
		return buffy;
	}

	private static StringBuffer convertToTagString(Map<String, Object> map) throws UnsupportedEncodingException {
		if (map == null)
			return null;
		StringBuffer buffy = new StringBuffer();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			if (map.get(key) instanceof Map) {
				buffy.append("<").append(key).append(">");
				@SuppressWarnings("unchecked")
				Map<String, Object> childMap = (Map<String, Object>) map.get(key);
				buffy.append(convertToTagString(childMap));
				buffy.append("</").append(key).append(">");
			} else {
				buffy.append("<").append(key).append(">");
				buffy.append(map.get(key) == null ? "" : URLUtil.encode(map.get(key).toString()));
				buffy.append("</").append(key).append(">");
			}
		}
		return buffy;
	}

	public static String convertToJSONString(Map<?, ?> map) {
		com.amazonaws.util.json.JSONObject json = new com.amazonaws.util.json.JSONObject(map);
		return json.toString();
	}

	public static String convertToJSONString(List<?> list) {
		com.amazonaws.util.json.JSONArray json = new com.amazonaws.util.json.JSONArray(list);
		return json.toString();
	}

	public static JSONArray jsonArrayFromUrl(String urlStr) throws IOException, JSONException {
		InputStream is = new URL(urlStr).openStream();
		JSONArray json = null;
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			String jsonText = sb.toString();
			json = new JSONArray(jsonText);
		} finally {
			is.close();
		}
		return json;
	}

	public static String stringFromUrl(String urlStr) throws MalformedURLException, IOException {
		InputStream is = new URL(urlStr).openStream();
		String str = null;
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			str = sb.toString();
		} finally {
			is.close();
		}
		return str;
	}
}
