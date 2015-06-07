
package com.cyf.demo.webservice.client.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseHeadDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseHeadDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerSeqNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="providerSeqNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="esbCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esbMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseHeadDTO", namespace = "http://service.ccic.com/common/bean", propOrder = {
    "seqNo",
    "consumerSeqNo",
    "providerSeqNo",
    "status",
    "esbCode",
    "esbMessage",
    "appCode",
    "appMessage"
})
public class ResponseHeadDTO {

    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String seqNo;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String consumerSeqNo;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String providerSeqNo;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected int status;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String esbCode;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String esbMessage;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String appCode;
    @XmlElement(namespace = "http://service.ccic.com/common/bean")
    protected String appMessage;

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
     * Gets the value of the providerSeqNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderSeqNo() {
        return providerSeqNo;
    }

    /**
     * Sets the value of the providerSeqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderSeqNo(String value) {
        this.providerSeqNo = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the esbCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsbCode() {
        return esbCode;
    }

    /**
     * Sets the value of the esbCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsbCode(String value) {
        this.esbCode = value;
    }

    /**
     * Gets the value of the esbMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsbMessage() {
        return esbMessage;
    }

    /**
     * Sets the value of the esbMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsbMessage(String value) {
        this.esbMessage = value;
    }

    /**
     * Gets the value of the appCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * Sets the value of the appCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppCode(String value) {
        this.appCode = value;
    }

    /**
     * Gets the value of the appMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppMessage() {
        return appMessage;
    }

    /**
     * Sets the value of the appMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppMessage(String value) {
        this.appMessage = value;
    }

}
