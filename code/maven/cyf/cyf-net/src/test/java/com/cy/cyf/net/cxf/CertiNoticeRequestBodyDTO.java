
package com.cy.cyf.net.cxf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CertiNoticeRequestBodyDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertiNoticeRequestBodyDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessAttribute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certiNoInfo" type="{http://service.ccic.com/prpall/certinotice/bean}certiNoInfoDTO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertiNoticeRequestBodyDTO", propOrder = {
    "businessAttribute",
    "certiNoInfo"
})
public class CertiNoticeRequestBodyDTO {

    @XmlElement(required = true)
    protected String businessAttribute;
    @XmlElement(required = true)
    protected List<CertiNoInfoDTO> certiNoInfo;

    /**
     * Gets the value of the businessAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessAttribute() {
        return businessAttribute;
    }

    /**
     * Sets the value of the businessAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessAttribute(String value) {
        this.businessAttribute = value;
    }

    /**
     * Gets the value of the certiNoInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certiNoInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertiNoInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertiNoInfoDTO }
     * 
     * 
     */
    public List<CertiNoInfoDTO> getCertiNoInfo() {
        if (certiNoInfo == null) {
            certiNoInfo = new ArrayList<CertiNoInfoDTO>();
        }
        return this.certiNoInfo;
    }

}
