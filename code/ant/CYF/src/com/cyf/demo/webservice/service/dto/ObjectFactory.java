
package com.cyf.demo.webservice.service.dto;

import javax.xml.bind.annotation.XmlRegistry;

import com.cyf.demo.webservice.service.common.RequestHeadDTO;
import com.cyf.demo.webservice.service.common.ResponseHeadDTO;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the test.tit.ybt.webService.policy.certinotice package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: test.tit.ybt.webService.policy.certinotice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
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
     * Create an instance of {@link CertiNoticeResponse }
     * 
     */
    public CertiNoticeResponse createCertiNoticeResponse() {
        return new CertiNoticeResponse();
    }

    /**
     * Create an instance of {@link CertiNoticeRequest }
     * 
     */
    public CertiNoticeRequest createCertiNoticeRequest() {
        return new CertiNoticeRequest();
    }

}
