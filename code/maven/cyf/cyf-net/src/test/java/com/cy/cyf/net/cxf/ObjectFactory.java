
package com.cy.cyf.net.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cy.cyf.net.cxf package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Request3_QNAME = new QName("http://service.ccic.com/prpall/certinotice/intf", "request3");
    private final static QName _Test3Response_QNAME = new QName("http://service.ccic.com/prpall/certinotice/intf", "test3Response");
    private final static QName _Request1_QNAME = new QName("http://service.ccic.com/prpall/certinotice/intf", "request1");
    private final static QName _Request2_QNAME = new QName("http://service.ccic.com/prpall/certinotice/intf", "request2");
    private final static QName _Test2Response_QNAME = new QName("http://service.ccic.com/prpall/certinotice/intf", "test2Response");
    private final static QName _Test1Response_QNAME = new QName("http://service.ccic.com/prpall/certinotice/intf", "test1Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cy.cyf.net.cxf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CertiNoInfoDTO }
     * 
     */
    public CertiNoInfoDTO createCertiNoInfoDTO() {
        return new CertiNoInfoDTO();
    }

    /**
     * Create an instance of {@link CertiNoticeRequestBodyDTO }
     * 
     */
    public CertiNoticeRequestBodyDTO createCertiNoticeRequestBodyDTO() {
        return new CertiNoticeRequestBodyDTO();
    }

    /**
     * Create an instance of {@link CertiNoticeRequest }
     * 
     */
    public CertiNoticeRequest createCertiNoticeRequest() {
        return new CertiNoticeRequest();
    }

    /**
     * Create an instance of {@link CertiNoticeResponse }
     * 
     */
    public CertiNoticeResponse createCertiNoticeResponse() {
        return new CertiNoticeResponse();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/prpall/certinotice/intf", name = "request3")
    public JAXBElement<String> createRequest3(String value) {
        return new JAXBElement<String>(_Request3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/prpall/certinotice/intf", name = "test3Response")
    public JAXBElement<String> createTest3Response(String value) {
        return new JAXBElement<String>(_Test3Response_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/prpall/certinotice/intf", name = "request1")
    public JAXBElement<String> createRequest1(String value) {
        return new JAXBElement<String>(_Request1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/prpall/certinotice/intf", name = "request2")
    public JAXBElement<String> createRequest2(String value) {
        return new JAXBElement<String>(_Request2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/prpall/certinotice/intf", name = "test2Response")
    public JAXBElement<String> createTest2Response(String value) {
        return new JAXBElement<String>(_Test2Response_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/prpall/certinotice/intf", name = "test1Response")
    public JAXBElement<String> createTest1Response(String value) {
        return new JAXBElement<String>(_Test1Response_QNAME, String.class, null, value);
    }

}
