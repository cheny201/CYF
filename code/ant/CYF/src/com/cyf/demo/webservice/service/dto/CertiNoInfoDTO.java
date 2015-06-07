
package com.cyf.demo.webservice.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for certiNoInfoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certiNoInfoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certiNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certiType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="policyNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compulsoryFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="effectiveTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createPolicyTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="underWriteEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payWay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payWayNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validNoBZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validNoCIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certiNoInfoDTO", propOrder = {
    "certiNo",
    "certiType",
    "policyNo",
    "compulsoryFlag",
    "status",
    "effectiveTime",
    "createPolicyTime",
    "chargeTime",
    "underWriteEndTime",
    "payWay",
    "payWayNo",
    "validNoBZ",
    "validNoCIP"
})
public class CertiNoInfoDTO {

    @XmlElement(required = true)
    protected String certiNo;
    @XmlElement(required = true)
    protected String certiType;
    @XmlElement(required = true)
    protected String policyNo;
    protected String compulsoryFlag;
    @XmlElement(required = true)
    protected String status;
    protected String effectiveTime;
    protected String createPolicyTime;
    protected String chargeTime;
    protected String underWriteEndTime;
    protected String payWay;
    protected String payWayNo;
    protected String validNoBZ;
    protected String validNoCIP;

    /**
     * Gets the value of the certiNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertiNo() {
        return certiNo;
    }

    /**
     * Sets the value of the certiNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertiNo(String value) {
        this.certiNo = value;
    }

    /**
     * Gets the value of the certiType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertiType() {
        return certiType;
    }

    /**
     * Sets the value of the certiType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertiType(String value) {
        this.certiType = value;
    }

    /**
     * Gets the value of the policyNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyNo() {
        return policyNo;
    }

    /**
     * Sets the value of the policyNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyNo(String value) {
        this.policyNo = value;
    }

    /**
     * Gets the value of the compulsoryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompulsoryFlag() {
        return compulsoryFlag;
    }

    /**
     * Sets the value of the compulsoryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompulsoryFlag(String value) {
        this.compulsoryFlag = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the effectiveTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Sets the value of the effectiveTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveTime(String value) {
        this.effectiveTime = value;
    }

    /**
     * Gets the value of the createPolicyTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatePolicyTime() {
        return createPolicyTime;
    }

    /**
     * Sets the value of the createPolicyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatePolicyTime(String value) {
        this.createPolicyTime = value;
    }

    /**
     * Gets the value of the chargeTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTime() {
        return chargeTime;
    }

    /**
     * Sets the value of the chargeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTime(String value) {
        this.chargeTime = value;
    }

    /**
     * Gets the value of the underWriteEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderWriteEndTime() {
        return underWriteEndTime;
    }

    /**
     * Sets the value of the underWriteEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderWriteEndTime(String value) {
        this.underWriteEndTime = value;
    }

    /**
     * Gets the value of the payWay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * Sets the value of the payWay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayWay(String value) {
        this.payWay = value;
    }

    /**
     * Gets the value of the payWayNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayWayNo() {
        return payWayNo;
    }

    /**
     * Sets the value of the payWayNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayWayNo(String value) {
        this.payWayNo = value;
    }

    /**
     * Gets the value of the validNoBZ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidNoBZ() {
        return validNoBZ;
    }

    /**
     * Sets the value of the validNoBZ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidNoBZ(String value) {
        this.validNoBZ = value;
    }

    /**
     * Gets the value of the validNoCIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidNoCIP() {
        return validNoCIP;
    }

    /**
     * Sets the value of the validNoCIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidNoCIP(String value) {
        this.validNoCIP = value;
    }

	@Override
	public String toString() {
		return "CertiNoInfoDTO [certiNo=" + certiNo + ", certiType="
				+ certiType + ", policyNo=" + policyNo + ", compulsoryFlag="
				+ compulsoryFlag + ", status=" + status + ", effectiveTime="
				+ effectiveTime + ", createPolicyTime=" + createPolicyTime
				+ ", chargeTime=" + chargeTime + ", underWriteEndTime="
				+ underWriteEndTime + ", payWay=" + payWay + ", payWayNo="
				+ payWayNo + ", validNoBZ=" + validNoBZ + ", validNoCIP="
				+ validNoCIP + "]";
	}

}
