
package com.cy.cyf.net.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="responseHead" type="{http://service.ccic.com/common/bean}ResponseHeadDTO"/>
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
    "responseHead"
})
@XmlRootElement(name = "CertiNoticeResponse")
public class CertiNoticeResponse {

    @XmlElement(required = true)
    protected ResponseHeadDTO responseHead;

    /**
     * Gets the value of the responseHead property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public ResponseHeadDTO getResponseHead() {
        return responseHead;
    }

    /**
     * Sets the value of the responseHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public void setResponseHead(ResponseHeadDTO value) {
        this.responseHead = value;
    }

}
