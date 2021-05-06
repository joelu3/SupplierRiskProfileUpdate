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
   
        def xmlString = message.getBody(String.class);

        def SupplierProfileRequestEntry = new XmlSlurper().parseText(xmlString);

        StringBuilder keyStr = new StringBuilder();

        def loginid=SupplierProfileRequestEntry.LoginId
        def fullname=SupplierProfileRequestEntry.FullName
        def emailaddress=SupplierProfileRequestEntry.EmailAddress
        def phone=SupplierProfileRequestEntry.Phone
        def defaultcurrency=SupplierProfileRequestEntry.DefaultCurrency
        def preferredlocale=SupplierProfileRequestEntry.PreferredLocale
        def organization=SupplierProfileRequestEntry.Organization
        def orgId=SupplierProfileRequestEntry.OrgId
        def migrationstatus=SupplierProfileRequestEntry.MigrationStatus
        def isuserapproved=SupplierProfileRequestEntry.IsUserApproved
        def timeupdated=SupplierProfileRequestEntry.TimeUpdated
        def timecreated=SupplierProfileRequestEntry.TimeCreated
        def createdby=SupplierProfileRequestEntry.CreatedBy
        def environmentalrisk=SupplierProfileRequestEntry.EnvironmentalRisk
        def financialrisk=SupplierProfileRequestEntry.FinancialRisk
        def legalrisk=SupplierProfileRequestEntry.LegalRisk
        def operationalrisk=SupplierProfileRequestEntry.OperationalRisk
        
        keyStr.append("{");
        keyStr.append("\"loginid\":\"$loginid\", ");
        keyStr.append("\"fullname\":\"$fullname\", ");
        keyStr.append("\"emailaddress\":\"$emailaddress\", ");
        keyStr.append("\"phone\":\"$phone\", ");
        keyStr.append("\"defaultcurrency\":\"$defaultcurrency\", ");
        keyStr.append("\"preferredlocale\":\"$preferredlocale\", ");
        keyStr.append("\"organization\":\"$organization\", ");
        keyStr.append("\"originalorgid\":\"$orgId\", ");
        keyStr.append("\"migrationstatus\":\"$migrationstatus\", ");
        keyStr.append("\"isuserapproved\":\"$isuserapproved\", ");
        keyStr.append("\"timeupdated\":\"$timeupdated\", ");
        keyStr.append("\"timecreated\":\"$timecreated\", ");
        keyStr.append("\"createdby\":\"$createdby\", ");
        keyStr.append("\"environmentalrisk\":\"$environmentalrisk\", ");
        keyStr.append("\"financialrisk\":\"$financialrisk\", ");
        keyStr.append("\"legalrisk\":\"$legalrisk\", ");
        keyStr.append("\"operationalrisk\":\"$operationalrisk\", ");
        
        //Make call to ext db for profileid to vendorid map
        def get=new URL("https://temp");
        def smvendorid = "[]";
        
            get = new URL("https://fairestdb.p.rapidapi.com/suppliervendoridmap1/sandboxatlanticmap1/supplierprofileid/$orgId").openConnection();
            get.setRequestMethod("GET");
            get.setDoOutput(true);
            get.setRequestProperty("Content-Type", "application/json");
            get.setRequestProperty("Accept", "application/json")
            get.setRequestProperty("useQueryString", "true");
            get.setRequestProperty("x-rapidapi-key", "");
            get.setRequestProperty("x-rapidapi-host", "fairestdb.p.rapidapi.com");

            if(get.getResponseCode().equals(200)){
                def output = get.getInputStream().getText()
        
                def jsonSlurper = new JsonSlurper()
                def object = jsonSlurper.parseText(output);
                smvendorid = object.suppliervendorid.toString();
                if(smvendorid!="[]"){
                    smvendorid=smvendorid.replaceAll("[\\[\\]]", "");
                }
            }


/*
Old Hard Code - Obsolete - Reference

        if(orgId=="ACM_6023084"){
            Dell
            keyStr.append("\"orgId\":");
            keyStr.append("\"S26824176\" ");
                
        }       
        else if(orgId=="USSU-CIG10"){
            Sonic
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931936\" ");

        }
        else if(orgId=="USSU-CIG04"){
            Sky Mac
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931939\" ");            
        }
        else if(orgId=="USSU-CIG05"){
            Windy Corp
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931931\" ");            
        }
        else if(orgId=="USSU-CIG08"){
            Airy Corp.
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931933\" ");            
        }
        else if(orgId=="USSU-CIG09"){
            Startrek Limited
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931932\" ");            
        }
        else if(orgId=="USSU-CIG02"){
            Nube Corporation
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931938\" ");            
        }
        else if(orgId=="USSU-CIG03"){
            Mrak Enterprises
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17924906\" ");            
        }
        else if(orgId=="USSU-CIG12"){
            Graig Industries
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931941\" ");            
        }
        else if(orgId=="USSU-CIG17"){
            Kroschke
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931929\" ");            
        }  
        else if(orgId=="USSU-CIG07"){
            Cloudy Corporation
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931940\" ");            
        }
        else if(orgId=="USSU-CIG06"){
            Rany Corp. - (Sandbox)
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17943000\" ");            
        }
        else if(orgId=="USSU-CIG01"){
            Oblako Industries
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931934\" ");            
        }
        else if(orgId=="USSU-CIG13"){
            Umbrella Corp
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931928\" ");            
        }
        else if(orgId=="ACM_6023083"){
            NTT Data Inc
            keyStr.append("\"orgId\":");
            keyStr.append("\"S26824175\" ");            
        }
        else if(orgId=="ACM_6023082"){
            Microsoft Inc
            keyStr.append("\"orgId\":");
            keyStr.append("\"S26824174\" ");            
        }
        else if(orgId=="USSU-CIG18"){
            Spot Buy
            keyStr.append("\"orgId\":");
            keyStr.append("\"S17931937\" ");            
        }
        else 
                if(orgId!=null){
            keyStr.append("\"orgId\":\"$smvendorid\", ");"
        }
 */       
        if(smvendorid!="[]"){
            keyStr.append("\"orgId\":");
            keyStr.append("\"$smvendorid\" ");            
        }
        else{
            keyStr.append("\"orgId\":");
            keyStr.append("\"null\" ");
        }

        keyStr.append("}");
        message.setHeader("Content-Type", "application/json;charset=UTF-8");
    
        message.setBody(keyStr.toString());

        return message;
}
