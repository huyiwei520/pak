package com.pak.common;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.pak.filter.OriginFilter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * Created by huyiwei on 2018/4/22.
 */
@Configuration
public class BeanConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter fastConver = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConver.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConver);
    }

    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new OriginFilter());
        frBean.addUrlPatterns("/*");
        frBean.setOrder(2);
        return frBean;
    }

//    @Bean
//    public FilterRegistrationBean accessLoginFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
//        registrationBean.setFilter(httpBasicFilter);
//        Map<String,String> m = new HashMap<String,String>();
//        m.put("targetBeanName","acessFilter");
//        m.put("targetFilterLifecycle","true");
//        registrationBean.setInitParameters(m);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
}
