package com.demo.common.utils;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class XmlUtils {

    /**
     * 根据xml消息体转化为Map
     *
     * @param xml         xml消息
     * @param rootElement 根元素
     * @return Map
     */
    public static Map<String, Object> xmlBody2map(String xml, String rootElement) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        Element body = (Element) doc.selectSingleNode("/" + rootElement);
        Map<String, Object> vo = __buildXmlBody2map(body);
        return vo;
    }

    public static  String bean2XmlString(Object b){
        XStream xStream = new XStream();
        xStream.alias(b.toString(), b.getClass());
        xStream.processAnnotations(b.getClass());
        return xStream.toXML(b);

    }

    private static Map<String, Object> __buildXmlBody2map(Element body) {
        Map<String, Object> vo = new HashMap<>();
        if (body != null) {
            List<Element> elements = body.elements();
            for (Element element : elements) {
                String key = element.getName();
                if (StringUtils.isNotEmpty(key)) {
                    String type = element.attributeValue("type", "java.lang.String");
                    String text = element.getText().trim();
                    Object value = null;
                    if (String.class.getCanonicalName().equals(type)) {
                        value = text;
                    } else if (Character.class.getCanonicalName().equals(type)) {
                        value = new Character(text.charAt(0));
                    } else if (Boolean.class.getCanonicalName().equals(type)) {
                        value = new Boolean(text);
                    } else if (Short.class.getCanonicalName().equals(type)) {
                        value = Short.parseShort(text);
                    } else if (Integer.class.getCanonicalName().equals(type)) {
                        value = Integer.parseInt(text);
                    } else if (Long.class.getCanonicalName().equals(type)) {
                        value = Long.parseLong(text);
                    } else if (Float.class.getCanonicalName().equals(type)) {
                        value = Float.parseFloat(text);
                    } else if (Double.class.getCanonicalName().equals(type)) {
                        value = Double.parseDouble(text);
                    } else if (java.math.BigInteger.class.getCanonicalName().equals(type)) {
                        value = new java.math.BigInteger(text);
                    } else if (java.math.BigDecimal.class.getCanonicalName().equals(type)) {
                        value = new java.math.BigDecimal(text);
                    } else if (Map.class.getCanonicalName().equals(type)) {
                        value = __buildXmlBody2map(element);
                    } else {
                    }
                    vo.put(key, value);
                }
            }
        }
        return vo;
    }

    public static String map2xmlBody(Map<String, Object> vo, String rootElement) {
        org.dom4j.Document doc = DocumentHelper.createDocument();
        Element body = DocumentHelper.createElement(rootElement);
        doc.add(body);
        __buildMap2xmlBody(body, vo);
        return doc.asXML();
    }

    private static void __buildMap2xmlBody(Element body, Map<String, Object> vo) {
        if (vo != null) {
            Iterator<String> it = vo.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                if (StringUtils.isNotEmpty(key)) {
                    Object obj = vo.get(key);
                    Element element = DocumentHelper.createElement(key);
                    if (obj != null) {
                        if (obj instanceof String) {
                            element.setText((String) obj);
                        } else {
                            if (obj instanceof Character || obj instanceof Boolean || obj instanceof Number
                                    || obj instanceof java.math.BigInteger || obj instanceof java.math.BigDecimal) {
                                org.dom4j.Attribute attr = DocumentHelper.createAttribute(element, "type", obj.getClass().getCanonicalName());
                                element.add(attr);
                                element.setText(String.valueOf(obj));
                            } else if (obj instanceof Map) {
                                org.dom4j.Attribute attr = DocumentHelper.createAttribute(element, "type", Map.class.getCanonicalName());
                                element.add(attr);
                                __buildMap2xmlBody(element, (Map<String, Object>) obj);
                            } else {
                            }
                        }
                    }
                    body.add(element);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String xml = "<xml>    <ToUserName><![CDATA[gh_8ae8a6441fb5]]></ToUserName>    <FromUserName><![CDATA[oUpSlwFzfuDlwdE728D_NyX6c7TY]]></FromUserName>    <CreateTime>1611749594</CreateTime>    <MsgType><![CDATA[event]]></MsgType>    <Event><![CDATA[unsubscribe]]></Event>    <EventKey><![CDATA[]]></EventKey>    <Encrypt><![CDATA[lvquZCXP1YFsc50kI72WceSHhGDKX1tDnfL0aH89P3kCdeKGNB9G79V0xtf7NvflKlsztpjSZFE4DgGgHALq1CnAncqtv76vqm8izKPTikR9G6/Jfj3xVZctuzktT+GONexzJXq2aCjTqU4T8mnfpoXqemz9Txesn4J7dBK8T+s6sg6chZOplFH7iggDOE2e780rui4GgxZlPgLPW0H6lZepLgggVJdv5lloSexDvQ4+JMZLetvfI5tFjyVdreJ1L9R5RoBCOGa+bFLj0I0Non8JSAxloC6YyZUH6awz3Eee0k22HNQDsePHIErGzemttyxKlFYN5hCEhvzyoskXBo+EjnguigdP9hLXSMo2ezZ7AvbHMD92rSkrw5bxdejuEKWCIrEJ+j2IJ085LS1cDalnv0f2lEPIxvkF4Mjc4po=]]></Encrypt></xml>";
        Map<String, Object> xml1 = xmlBody2map(xml, "xml");
        String openId = xml1.get("FromUserName").toString();
        String  event = xml1.get("Event").toString();
        System.out.println(openId);
        System.out.println(event);
    }
}
