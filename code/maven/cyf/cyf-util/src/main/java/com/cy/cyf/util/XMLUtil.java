package com.cy.cyf.util;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(Object obj) throws DocumentException{
		Map<String,String> map = new HashMap<String,String>();
		SAXReader sr = new SAXReader();
		Document doc = createDocument(sr,obj);
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		for (int i = 0; i < elements.size(); i++) {
			Element e = elements.get(i);
			map.put(e.getName(), e.getTextTrim());
		}
		return map;
	}
	
	private static Document createDocument(SAXReader sr,Object obj) throws DocumentException{
		Document doc = null;
		if(obj instanceof String){
			doc = sr.read((String) obj);
		}else if(obj instanceof File){
			doc = sr.read((File)obj);
		}else if(obj instanceof InputStream){
			doc = sr.read((InputStream)obj);
		}else if(obj instanceof Reader){
			doc = sr.read((Reader)obj);
		}else if(obj instanceof URL){
			doc = sr.read((URL)obj);
		}
		return doc;
	}

}
