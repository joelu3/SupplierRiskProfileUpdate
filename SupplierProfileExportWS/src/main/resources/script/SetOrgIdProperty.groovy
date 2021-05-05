/*
 The integration developer needs to create the method processData 
 This method takes Message object of package com.sap.gateway.ip.core.customdev.util 
which includes helper methods useful for the content developer:
The methods available are:
    public java.lang.Object getBody()
	public void setBody(java.lang.Object exchangeBody)
    public java.util.Map<java.lang.String,java.lang.Object> getHeaders()
    public void setHeaders(java.util.Map<java.lang.String,java.lang.Object> exchangeHeaders)
    public void setHeader(java.lang.String name, java.lang.Object value)
    public java.util.Map<java.lang.String,java.lang.Object> getProperties()
    public void setProperties(java.util.Map<java.lang.String,java.lang.Object> exchangeProperties) 
    public void setProperty(java.lang.String name, java.lang.Object value)
    public java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> getSoapHeaders()
    public void setSoapHeaders(java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> soapHeaders) 
       public void clearSoapHeaders()
 */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonSlurper;
import groovy.util.XmlSlurper;
import groovy.json.JsonOutput;
import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

def Message processData(Message message) {
    //Body 
        println("hello there");
        def xmlString = message.getBody(String.class);

        def SupplierProfileRequestEntry = new XmlSlurper().parseText(xmlString);

       ///def body = message.getBody();
       ///message.setBody(body + "Body is modified");
       //Headers 
       ///def map = message.getHeaders();
       ///def value = map.get("oldHeader");
       ///message.setHeader("oldHeader", value + "modified");
       ///message.setHeader("newHeader", "newHeader");
       //Properties 
       ///map = message.getProperties();
       ///value = map.get("oldProperty");
       ///message.setProperty("oldProperty", value + "modified");
       ///message.setProperty("newProperty", "newProperty");
       ///return message;    

        def orgId=SupplierProfileRequestEntry.OrgId
        
        message.setProperty("suporgid", orgId);

        return message;
}