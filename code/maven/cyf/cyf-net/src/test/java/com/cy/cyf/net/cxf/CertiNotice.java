
package com.cy.cyf.net.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "CertiNotice", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CertiNotice {


    /**
     * 
     * @param request1
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "test1Response", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf", partName = "test1Response")
    public String test1(
        @WebParam(name = "request1", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf", partName = "request1")
        String request1);

    /**
     * 
     * @param request2
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "test2Response", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf", partName = "test2Response")
    public String test2(
        @WebParam(name = "request2", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf", partName = "request2")
        String request2);

    /**
     * 
     * @param request3
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "test3Response", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf", partName = "test3Response")
    public String test3(
        @WebParam(name = "request3", targetNamespace = "http://service.ccic.com/prpall/certinotice/intf", partName = "request3")
        String request3);

    /**
     * 
     * @param certiNoticeRequest
     * @return
     *     returns com.cy.cyf.net.cxf.CertiNoticeResponse
     */
    @WebMethod(action = "/prpall/certinotice")
    @WebResult(name = "CertiNoticeResponse", targetNamespace = "http://service.ccic.com/prpall/certinotice/bean", partName = "CertiNoticeResponse")
    public CertiNoticeResponse certinotice(
        @WebParam(name = "CertiNoticeRequest", targetNamespace = "http://service.ccic.com/prpall/certinotice/bean", partName = "CertiNoticeRequest")
        CertiNoticeRequest certiNoticeRequest);

}
