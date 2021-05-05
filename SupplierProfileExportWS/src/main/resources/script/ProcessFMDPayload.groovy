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
        Sample Prepared XML - Reference
        
<n0:MasterDataReplReqMsg xmlns:n0="http://sap.com/xi/ARBCIG1" xmlns:soap-env="http://schemas.xmlsoap.org/soap/envelope/" xmlns:prx="urn:sap.com:proxy:QE2:/1SAI/TASD374B56E2305E7E70622:752">
	<Header>
		<Parameters>
			<Parameter name="UUID">
				<value>000D3A1813701EEBA3C2BE9360A269B8</value>
			</Parameter>
			<Parameter name="realm">
				<value>XXXXX-T</value>
			</Parameter>
			<Parameter name="fullload">
				<value>true</value>
			</Parameter>
			<Parameter name="event">
				<value>Import Batch Data</value>
			</Parameter>
			<Parameter name="clienttype">
				<value>DirectConnect</value>
			</Parameter>
			<Parameter name="clientversion">
				<value>Addon- Direct Connectivity</value>
			</Parameter>
			<Parameter name="clientinfo">
				<value>Multiple Terminal Id or No Terminal</value>
			</Parameter>
			<Parameter name="SystemId">
				<value>XXXXXX200</value>
			</Parameter>
			<Parameter name="Solution">
				<value>Buyer</value>
			</Parameter>
		</Parameters>
		<AttachmentFolder contentID="FOL33000000000004EXT39000000000138" contentLength="" contentType="application/zip" fileName="processing20210326093052.zip">
			<Content xmlns="">UEsDBBQAAAAIANpLelK2F/W0iAIAAEMZAAARAAAAUmVsYXRpb25FbnRyeS5jc3ad10tP4zAUBeA9En+CdWdEHApl2WcaUrVRHpvsKjUzUwlaQJ1F//1knHZGuj6Lc5CQMBd/sms7zmldLb6Nbm9W7Y9T1p7jwaXhBkX7uj3tj4fq/N5+rw/7j9/tevvWDor9z199j0vX6F/pfyvuW+nOd0p3tzd3d4O/P3ldHafHt/ft4Tw97trF/vXUfvb/Gs+j++7X/D7q/+4bvkrxCvKK5BM4+oQdfZJAnrC8iPxsr7xv+CrDp2M/uuG+SvElmryvUnwDR9+wvIGjNySfwY2bsRvXdYwg51Z+lsHRM3L0eYm4rzJ8kSLuqxQvIC9InkwQ91WSg5X3VYrDySfs5JeQL2leQ16TPIWHNmUPbQoPbcoe2nQFR1+xo8ObNmVv2pfc85cr7xu+SnIHuaP4Gn72NfvZu45g5X2V5A5yevIPkD+wfAj5kOMbuHTsPZ/Dlc/Zlc/hqcvZU1fAyRfs5Ev4wJbsA1vCi7pkL+oSvmVK9i1TwbuuYu+6ukTv95p9SXUdQbbxVZI7yLlHpusYQx6zfAg598h0HR8hf6R4M0Yb17C57pKokytPvpSoLRcTteFqorZcTNSWi4nacDVRWy4masvFRG25mKgNVxN1yKVEbbmYqA1XE7XhaqK2XEzUhquJOuRSorZcTNSGq4nacjFRG64m6pBLidpyMVFbLiZqw9VEHXLuDZvBfc/YfV/DpVMDecilQB5yKZCHXArkIZcCueViIDdcDeSWi4HccDWQG64GcsvFQG65GMgNVwO54WogD7kUyEMuBfKQS4E85FIgDzkXyOvSwaVz7NI5uHSOXToHbhtfJfkT5E8sH0E+Yvkz5M8kj+GZj9kzH8ONi7mNa+DXgcsXsT9QSwECEwAUAAAACADaS3pSthf1tIgCAABDGQAAEQAAAAAAAAAAAAAAAAAAAAAAUmVsYXRpb25FbnRyeS5jc3ZQSwUGAAAAAAEAAQA/AAAAtwIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</Content>
		</AttachmentFolder>
	</Header>
</n0:MasterDataReplReqMsg>
        */    

        keyStr.append("<n0:MasterDataReplReqMsg xmlns:n0="http://sap.com/xi/ARBCIG1" xmlns:soap-env="http://schemas.xmlsoap.org/soap/envelope/" xmlns:prx="urn:sap.com:proxy:QE2:/1SAI/TASD374B56E2305E7E70622:752">")
        keyStr.append("<Header>")
        keyStr.append("<Parameters>");
        keyStr.append("<Parameter name=\"UUID\">");
        keyStr.append("<value>000D3A1813701EEBA3C2BE9360A269B8</value>");
        keyStr.append("</Parameter>");
        keyStr.append("<Parameter name \"realm\">");
        keyStr.append("<value>XXXXX-T</value>");
        keyStr.append("</Parameter>");
        keyStr.append("<Parameter name=\"fullload\">");
        keyStr.append("<value>true</value>");
		keyStr.append("</Parameter>");
		keyStr.append("<Parameter name=\"event\">");
		keyStr.append("<value>Import Batch Data</value>");
		keyStr.append("</Parameter>");
		keyStr.append("<Parameter name=\"clienttype\">");
		keyStr.append("<value>DirectConnect</value>");
		keyStr.append("</Parameter>");
		keyStr.append("<Parameter name=\"clientversion\">");
		keyStr.append("<value>Addon- Direct Connectivity</value>");
		keyStr.append("</Parameter>");
		keyStr.append("<Parameter name=\"clientinfo\">");
		keyStr.append("<value>Multiple Terminal Id or No Terminal</value>");
		keyStr.append("</Parameter>");
		keyStr.append("<Parameter name=\"SystemId\">");
		keyStr.append("<value>XXXXXX200</value>");
		keyStr.append("</Parameter>");
		keyStr.append("<Parameter name=\"Solution\">");
		keyStr.append("<value>Buyer</value>");
		keyStr.append("</Parameter>");
		keyStr.append("</Parameters>");
		keyStr.append("<AttachmentFolder contentID="FOL33000000000004EXT39000000000138" contentLength="" contentType="application/zip" fileName="processing20210326093052.zip">");
		keyStr.append("<Content xmlns="">$encodedCSVString</Content>");
		keyStr.append("</AttachmentFolder>");
	    keyStr.append("</Header>");
        keyStr.append("</n0:MasterDataReplReqMsg>");
		
        //keyStr.append("</soapenv:Body>");
        //keyStr.append("</soapenv:Envelope>");

        message.setHeader("Content-Type", "text/plain");
    
        message.setBody(keyStr.toString());

        return message;
}