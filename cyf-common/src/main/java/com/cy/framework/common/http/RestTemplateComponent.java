package com.cy.framework.common.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhanglei
 */
@Component
public class RestTemplateComponent {
    /**
     * 连接池的最大连接数默认为0
     */
    @Value("${restTemplate.remote.maxConnTotal:10000}")
    private int maxConnTotal;
    /**
     * 单个主机的最大连接数
     */
    @Value("${restTemplate.remote.maxConnPerRoute:500}")
    private int maxConnPerRoute;
    /**
     * 连接超时默认2s
     */
    @Value("${restTemplate.remote.connectTimeout:2000}")
    private int connectTimeout;
    /**
     * 读取超时默认30s
     */
    @Value("${restTemplate.remote.readTimeout:30000}")
    private int readTimeout;
    /**
     * 连接请求超时默认2s
     */
    @Value("${restTemplate.remote.connectionRequestTimeout:2000}")
    private int connectionRequestTimeout;

    private RestTemplate restTemplate;

    @PostConstruct
    public void initRestTemplate() throws Exception {
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
                .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        poolingConnectionManager.setMaxTotal(this.maxConnTotal);
        poolingConnectionManager.setDefaultMaxPerRoute(this.maxConnPerRoute);
        poolingConnectionManager.setValidateAfterInactivity(30000);
        poolingConnectionManager.closeIdleConnections(30, TimeUnit.SECONDS);
        poolingConnectionManager.closeExpiredConnections();

        HttpClient httpClient = HttpClientBuilder.create()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .setMaxConnTotal(this.maxConnTotal)
                .setMaxConnPerRoute(this.maxConnPerRoute)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectionRequestTimeout(connectionRequestTimeout);

        restTemplate = new RestTemplate(requestFactory);

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        ListIterator<HttpMessageConverter<?>> it = messageConverters.listIterator();
        while (it.hasNext()) {
            HttpMessageConverter<?> messageConverter = it.next();
            if (messageConverter instanceof StringHttpMessageConverter) {
                it.remove();
                it.add(new StringHttpMessageConverter(Charsets.UTF_8));
                break;
            }
        }
    }

    public <T> T get(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        return this.getResponse(url, parameters, responseType, headers).getBody();
    }

    /**
     * 如果不想url被RestTemplate的底层自动转码，那么可以自己URI.create(url)来构建一个URI对象
     *
     * @param uri
     * @param responseType
     * @param headers
     * @param <T>
     * @return
     */
    public <T> T get(URI uri, Class<T> responseType, HttpHeaders headers) {
        return this.getResponse(uri, responseType, headers).getBody();
    }

    public <T> T postForm(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        return this.postFormResponse(url, parameters, responseType, headers).getBody();
    }

    public <T> T postJson(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        return this.postJsonResponse(url, parameters, responseType, headers).getBody();
    }

    public <T> ResponseEntity<T> getResponse(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (Objects.nonNull(headers)) {
            httpHeaders.putAll(headers);
        }

        httpHeaders.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        httpHeaders.setAcceptCharset(Lists.newArrayList(Charsets.UTF_8));

        Map<String, String> paramMap = this.buildParamMap(parameters);

        url = this.buildUrl(url, paramMap);

        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType, paramMap);
    }

    /**
     * 如果不想url被RestTemplate的底层自动转码，那么可以自己URI.create(url)来构建一个URI对象
     *
     * @param uri
     * @param responseType
     * @param headers
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> getResponse(URI uri, Class<T> responseType, HttpHeaders headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (Objects.nonNull(headers)) {
            httpHeaders.putAll(headers);
        }

        httpHeaders.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        httpHeaders.setAcceptCharset(Lists.newArrayList(Charsets.UTF_8));

        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> postFormResponse(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (Objects.nonNull(headers)) {
            httpHeaders.putAll(headers);
        }
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return this.post(url, parameters, responseType, httpHeaders);
    }

    public <T> ResponseEntity<T> postJsonResponse(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (Objects.nonNull(headers)) {
            httpHeaders.putAll(headers);
        }
        if (Objects.isNull(httpHeaders.getContentType())) {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }

        return this.post(url, parameters, responseType, httpHeaders);
    }

    private <T> ResponseEntity<T> post(String url, Object parameters, Class<T> responseType, HttpHeaders headers) {
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        headers.setAcceptCharset(Lists.newArrayList(Charsets.UTF_8));

        HttpEntity httpEntity;

        if (MediaType.APPLICATION_JSON.equals(headers.getContentType()) || MediaType.APPLICATION_JSON_UTF8.equals(headers.getContentType())) {
            httpEntity = new HttpEntity(JSONObject.toJSONString(parameters), headers);
        } else {
            MultiValueMap<String, String> request = new LinkedMultiValueMap();
            request.setAll(this.buildParamMap(parameters));
            httpEntity = new HttpEntity(request, headers);
        }

        return restTemplate.postForEntity(url, httpEntity, responseType);
    }

    private String buildUrl(String url, Map<String, String> paramMap) {
        if (MapUtils.isNotEmpty(paramMap)) {
            String parametersUrl = paramMap.keySet().stream().filter(r -> StringUtils.isNotBlank(r)).map(r -> r + "={" + r + "}").collect(Collectors.joining("&"));
            url = url + "?" + parametersUrl;
        }
        return url;
    }

    private Map<String, String> buildParamMap(Object parameters) {
        Map<String, Object> paramMap;
        if (parameters instanceof Map) {
            paramMap = (Map<String, Object>) parameters;
        } else if (Objects.nonNull(parameters)) {
            paramMap = BeanMap.create(parameters);
        } else {
            paramMap = Maps.newHashMap();
        }

        Map<String, String> map = Maps.newHashMap();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (entry.getValue() instanceof Collection) {
                Collection collection = (Collection) entry.getValue();
                if (CollectionUtils.isEmpty(collection)) {
                    map.put(entry.getKey(), null);
                    continue;
                }

                List<String> values = Lists.newArrayList();
                for (Object value : collection) {
                    if (Objects.nonNull(value)) {
                        values.add(Objects.toString(value));
                    }
                }
                map.put(entry.getKey(), values.stream().collect(Collectors.joining(",")));
            } else {
                map.put(entry.getKey(), Objects.toString(entry.getValue(), null));
            }
        }

        return map;
    }
}
