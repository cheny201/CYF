
package com.cyf.demo.webservice.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.cyf.demo.webservice.service.common.RequestHeadDTO;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestHead" type="{http://service.ccic.com/common/bean}RequestHeadDTO"/>
 *         &lt;element name="requestBody" type="{http://service.ccic.com/prpall/certinotice/bean}CertiNoticeRequestBodyDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestHead",
    "requestBody"
})
@XmlRootElement(name = "CertiNoticeRequest")
public class CertiNoticeRequest {

    @XmlElement(required = true)
    protected RequestHeadDTO requestHead;
    @XmlElement(required = true)
    protected CertiNoticeRequestBodyDTO requestBody;

    /**
     * Gets the value of the requestHead property.
     * 
     * @return
     *     possible object is
     *     {@link RequestHeadDTO }
     *     
     */
    public RequestHeadDTO getRequestHead() {
        return requestHead;
    }

    /**
     * Sets the value of the requestHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeadDTO }
     *     
     */
    public void setRequestHead(RequestHeadDTO value) {
        this.requestHead = value;
    }

    /**
     * Gets the value of the requestBody property.
     * 
     * @return
     *     possible object is
     *     {@link CertiNoticeRequestBodyDTO }
     *     
     */
    public CertiNoticeRequestBodyDTO getRequestBody() {
        return requestBody;
    }

    /**
     * Sets the value of the requestBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertiNoticeRequestBodyDTO }
     *     
     */
    public void setRequestBody(CertiNoticeRequestBodyDTO value) {
        this.requestBody = value;
    }

	@Override
	public String toString() {
		return "CertiNoticeRequest [requestHead=" + requestHead
				+ ", requestBody=" + requestBody + "]";
	}

}
