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
import org.codehaus.groovy.runtime.EncodingGroovyMethods;

def Message processData(Message message) {
    //Body 
        println("hello there");
        def encodedCSVString = message.getBody(String.class);

        StringBuilder keyStr = new StringBuilder();
        
        /*
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:Ariba:Sourcing:vrealm_350041">
   <soapenv:Header>
      <urn:Headers>
         <!--You may enter the following 2 items in any order-->
         <!--Optional:-->
         <urn:variant>vrealm_350041</urn:variant>
         <!--Optional:-->
         <urn:partition>prealm_350041</urn:partition>
      </urn:Headers>
   </soapenv:Header>
   <soapenv:Body>
      <urn:SupplierProfileImportRequest partition="prealm_350041" variant="vrealm_350041">
         <!--Optional:-->
         <urn:WSSupplierProfileImportInputBean_Item>
            <!--Optional:-->
            <urn:item>
               <urn:ProfileFile>Ik9yZ2FuaXphdGlvbiIsIk9yZ0lkIiwiS0lfMTAwOTA3NzggKEVudmlyb25tZW50YWwgYW5kIFNv
Y2lhbCBSaXNrIFNjb3JlKSIsIktJXzEwMDkwNzgwIChGaW5hbmNpYWwgUmlzayBTY29yZSkiLCJL
SV8xMDA5MDc4MiAoTGVnYWwgYW5kIFJlZ3VsYXRvcnkgUmlzayBTY29yZSkiLCJLSV8xMDA5MDc4
NCAoT3BlcmF0aW9uYWwgUmlzayBTY29yZSkiCiJTdGFydHJlayBMaW1pdGVkIiwiVVNTVS1DSUcw
OSIsIjEiLCIxIiwiMSIsIjIiCiJVbWJyZWxsYSBDb3JwIiwiVVNTVS1DSUcxMyIsIjIiLCIzIiwi
NCIsIjUi
                </urn:ProfileFile>
            </urn:item>
         </urn:WSSupplierProfileImportInputBean_Item>
      </urn:SupplierProfileImportRequest>
   </soapenv:Body>
</soapenv:Envelope>
        */    

        //todo: add mapping conversion logic
        //keyStr.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:Ariba:Sourcing:vrealm_350041\">");
        //keyStr.append("<soapenv:Header>");
        //keyStr.append("<urn:Headers>");
        //keyStr.append("<urn:variant>vrealm_350041</urn:variant>");
        //keyStr.append("<urn:partition>prealm_350041</urn:partition>");
        //keyStr.append("</urn:Headers>");
        keyStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
        keyStr.append("<urn:SupplierProfileImportRequest partition=\"prealm_350041\" variant=\"vrealm_350041\" xmlns:urn=\"urn:Ariba:Sourcing:vrealm_350041\">")
        //keyStr.append("</soapenv:Header>");
        //keyStr.append("<soapenv:Body>");
        //keyStr.append("<urn:SupplierProfileImportRequest partition=\"prealm_350041\" variant=\"vrealm_350041\">");
        keyStr.append("<urn:WSSupplierProfileImportInputBean_Item>");
        keyStr.append("<urn:item>");
        keyStr.append("<urn:ProfileFile>$encodedCSVString</urn:ProfileFile>");
        keyStr.append("</urn:item>");
        keyStr.append("</urn:WSSupplierProfileImportInputBean_Item>");
        keyStr.append("</urn:SupplierProfileImportRequest>");
        //keyStr.append("</soapenv:Body>");
        //keyStr.append("</soapenv:Envelope>");

        message.setHeader("Content-Type", "text/plain");
    
        message.setBody(keyStr.toString());

        return message;
}