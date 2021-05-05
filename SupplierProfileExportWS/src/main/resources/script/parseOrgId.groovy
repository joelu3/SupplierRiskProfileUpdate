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
import groovy.util.XmlSlurper;
def Message processData(Message message) {
    //Body 
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
       //return message;

    message.clearSoapHeaders();
    def body=message.getBody(String.class);
    def document = new XmlSlurper().parseText(body);

    def suppProfileReqEntry = document.SupplierProfileRequestList.SupplierProfileRequestEntry
    //def orgid=document.SupplierProfileRequestList.SupplierProfileRequestEntry[0].OrgId.text()
    //println "orgid= $orgid"
    def size=suppProfileReqEntry.size()
    println "size= $size"
    //Map<String, String> mp= new HashMap<>();
    StringBuilder keyStr = new StringBuilder();
    keyStr.append("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
    keyStr.append("<document>");
    keyStr.append("<SupplierProfileRequestList>");
    
    for (int i=0; i<size; i++){
        
        def loginid=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].LoginId.text()
        def fullname=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].FullName.text()
        def emailaddress=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].EmailAddress.text()
        def phone=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].Phone.text()
        def defaultcurrency=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].DefaultCurrency.text()
        def preferredlocale=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].PreferredLocale.text()
        def organization=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].Organization.text()
        def orgid=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].OrgId.text()
        def migrationstatus=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].MigrationStatus.text()
        def isuserapproved=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].IsUserApproved.text()
        def timeupdated=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].TimeUpdated.text()
        def timecreated=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].TimeCreated.text()
        def createdby=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].CreatedBy.text()
        def environmentalrisk=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].EnvironmentalRisk.text()
        def financialrisk=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].FinancialRisk.text()
        def legalrisk=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].LegalRisk.text()
        def operationalrisk=document.SupplierProfileRequestList.SupplierProfileRequestEntry[i].OperationalRisk.text()
        
        if(orgid!="[Unspecified]"&&orgid!="[System]"&&orgid!="OrgId"&&orgid!="[Unknown]"){
            //println "orgid= $orgid"
            keyStr.append("<SupplierProfileRequestEntry>");
            keyStr.append("<LoginId>$loginid</LoginId>");
            keyStr.append("<FullName>$fullname</FullName>");
            keyStr.append("<EmailAddress>$emailaddress</EmailAddress>");
            keyStr.append("<Phone>$phone</Phone>");
            keyStr.append("<DefaultCurrency>$defaultcurrency</DefaultCurrency>");
            keyStr.append("<PreferredLocale>$preferredlocale</PreferredLocale>");
            keyStr.append("<Organization>$organization</Organization>");
            keyStr.append("<OrgId>$orgid</OrgId>");
            keyStr.append("<MigrationStatus>$migrationstatus</MigrationStatus>");
            keyStr.append("<IsUserApproved>$isuserapproved</IsUserApproved>");
            keyStr.append("<TimeUpdated>$timeupdated</TimeUpdated>");
            keyStr.append("<TimeCreated>$timecreated</TimeCreated>");
            keyStr.append("<CreatedBy>$createdby</CreatedBy>");
            keyStr.append("<EnvironmentalRisk>$environmentalrisk</EnvironmentalRisk>");
            keyStr.append("<FinancialRisk>$financialrisk</FinancialRisk>");
            keyStr.append("<LegalRisk>$legalrisk</LegalRisk>");
            keyStr.append("<OperationalRisk>$operationalrisk</OperationalRisk>");
            keyStr.append("</SupplierProfileRequestEntry>");
        }
    }
    keyStr.append("</SupplierProfileRequestList>");
    keyStr.append("</document>");
    //def result = JsonOutput.prettyPrint(parameter.toString());
    def result=keyStr.toString();
    //message.setProperties(mp);
    message.setBody(result);
    return message;
    
}