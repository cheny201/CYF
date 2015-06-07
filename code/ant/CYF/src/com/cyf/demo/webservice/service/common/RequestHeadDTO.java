
package com.cyf.demo.webservice.service.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;name xmlns="http://service.ccic.com/common/bean" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;������Ϣͷ&lt;/name&gt;
 * </pre>
 * 
 * 
 * <p>Java class for RequestHeadDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestHeadDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerSeqNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="providerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riskCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeadDTO", namespace = "http://service.ccic.com/common/bean", propOrder = {
    "seqNo",
    "consumerSeqNo",
    "consumerID",
    "providerID",
    "classCode",
    "riskCode",
    "regionCode",
    "version"
})
public class RequestHeadDTO {

    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String seqNo;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String consumerSeqNo;
    @XmlElement(namespace = "http://service.ccic.com/common/bean", required = true)
    protected String consumerID;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String providerID;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String classCode;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String riskCode;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String regionCode;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String version;

    /**
     * Gets the value of the seqNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqNo(String value) {
        this.seqNo = value;
    }

    /**
     * Gets the value of the consumerSeqNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerSeqNo() {
        return consumerSeqNo;
    }

    /**
     * Sets the value of the consumerSeqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerSeqNo(String value) {
        this.consumerSeqNo = value;
    }

    /**
     * Gets the value of the consumerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerID() {
        return consumerID;
    }

    /**
     * Sets the value of the consumerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerID(String value) {
        this.consumerID = value;
    }

    /**
     * Gets the value of the providerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderID() {
        return providerID;
    }

    /**
     * Sets the value of the providerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderID(String value) {
        this.providerID = value;
    }

    /**
     * Gets the value of the classCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Sets the value of the classCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassCode(String value) {
        this.classCode = value;
    }

    /**
     * Gets the value of the riskCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskCode() {
        return riskCode;
    }

    /**
     * Sets the value of the riskCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskCode(String value) {
        this.riskCode = value;
    }

    /**
     * Gets the value of the regionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * Sets the value of the regionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionCode(String value) {
        this.regionCode = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

	@Override
	public String toString() {
		return "RequestHeadDTO [seqNo=" + seqNo + ", consumerSeqNo="
				+ consumerSeqNo + ", consumerID=" + consumerID
				+ ", providerID=" + providerID + ", classCode=" + classCode
				+ ", riskCode=" + riskCode + ", regionCode=" + regionCode
				+ ", version=" + version + "]";
	}
    
    

}
