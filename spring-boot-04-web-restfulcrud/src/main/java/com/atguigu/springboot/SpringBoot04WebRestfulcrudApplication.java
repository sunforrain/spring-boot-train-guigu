package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * 视频29 尚硅谷_SpringBoot_web开发-webjars&静态资源映射规则
 *     有关mvc的可配置项可见WebMvcAutoConfiguration.java和WebMvcProperties.java
 *     静态资源映射见 addResourceHandlers()方法
 *     首页映射见 welcomePageHandlerMapping()方法
 *     配置喜欢的图标见 FaviconConfiguration()方法
 *
 *  视频30 尚硅谷_SpringBoot_web开发-引入thymeleaf
 *      因为springBoot打包为jar,内置tomcat的特性,导致无法使用jsp,需要使用其他的模板引擎
 *      springBoot推荐thymeleaf
 *
 *  视频32 尚硅谷_SpringBoot_web开发-SpringMVC自动配置原理
 *  官方文档
 *  https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications
 *
 *  一,Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
 *      1)ContentNegotiatingViewResolver
 *          1))ContentNegotiatingViewResolver：组合所有的视图解析器的；
 *              WebMvcAutoConfiguration => 搜索ContentNegotiatingViewResolver
 *                  找到
 *                  org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 *                  #viewResolver(org.springframework.beans.factory.BeanFactory)
 *                  ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
 *                  点进去看ContentNegotiatingViewResolver
 *                       ContentNegotiatingViewResolver => 搜索resolveViewName()
 *                       org.springframework.web.servlet.view.ContentNegotiatingViewResolver
 *                       #resolveViewName(java.lang.String, java.util.Locale)点进去看
 *                          org.springframework.web.servlet.view.ContentNegotiatingViewResolver
 *                          #getCandidateViews(java.lang.String, java.util.Locale, java.util.List)
 *                              挨个获取视图解析器并挑选 for (ViewResolver viewResolver : this.viewResolvers)
 *
 *                          获取到最佳视图并返回
 *                           View bestView = getBestView(candidateViews, requestedMediaTypes, attrs);
*           2))视图解析器都是怎么来的?
 *              还是看ContentNegotiatingViewResolver
 *                  org.springframework.web.servlet.view.ContentNegotiatingViewResolver
 *                  #initServletContext(javax.servlet.ServletContext)
 *                      方法会去读取所有ViewResolver的实现
 *                      Collection<ViewResolver> matchingBeans =
 * 				        BeanFactoryUtils.beansOfTypeIncludingAncestors(obtainApplicationContext(), ViewResolver.class).values();
 * 		    3))如何定制：我们可以自己给容器中添加一个视图解析器；ContentNegotiatingViewResolver自动的将其组合进来；
 *
 *  二,Support for serving static resources, including support for WebJars (see below).
 *      静态资源和webjars资源的设置,见视频29
 *          看org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 *          #addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
 *
 *  三,Static index.html support. 静态首页访问,见视频29
 *      org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 *      #welcomePageHandlerMapping(org.springframework.context.ApplicationContext)
 *
 *  四,Custom Favicon support (see below). favicon.ico设置,见视频29
 *      org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.
 *      FaviconConfiguration
 *
 *  五,自动注册了 of Converter , GenericConverter , Formatter beans.
 *      可以搜索WebMvcAutoConfiguration内的 xxxFormatter方法,
 *      或者自定义formatter,或者Converter
 *      org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 *      #addFormatters(org.springframework.format.FormatterRegistry) ,会帮助我们将自定义的格式化器或者转换器加入到容器中
 *
 *  六,Support for HttpMessageConverters (see below).
 *      HttpMessageConverter：SpringMVC用来转换Http请求和响应的；User---Json；
 *      看静态内部类的构造:
 *          org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
*           由private final ObjectProvider<HttpMessageConverters> messageConvertersProvider;
 *           HttpMessageConverters 是从容器中确定；获取所有的HttpMessageConverter;
 *           看HttpMessageConverter的构造器,也可以发现,返回的是个数组
 *              public HttpMessageConverters(HttpMessageConverter<?>... additionalConverters) {
 *		            this(Arrays.asList(additionalConverters));
 *              }
 *      结论是:可以自己给容器中添加HttpMessageConverter，只需要将自己的组件注册容器中（@Bean,@Component）
 *
 *  七,Automatic registration of MessageCodesResolver (see below).定义错误代码生成规则
 *      org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 *      #getMessageCodesResolver()
 *          关注下getMessageCodesResolverFormat() =>
 *              private DefaultMessageCodesResolver.Format messageCodesResolverFormat; 点Format进去,可以看到能定义错误代码的前后缀等
 *
 *  八,Automatic use of a ConfigurableWebBindingInitializer bean (see below).
 *      org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration
 *      #getConfigurableWebBindingInitializer()
 *      由return (ConfigurableWebBindingInitializer)this.beanFactory.getBean(ConfigurableWebBindingInitializer.class);
 *      我们可以配置一个ConfigurableWebBindingInitializer来替换默认的；（添加到容器）
 *
 *      初始化WebDataBinder；web数据绑定器
 *      目的是请求数据绑定到JavaBean的属性；
 *
 *   org.springframework.boot.autoconfigure.web包内：
 *      找所有autoConfiguration类,包含web的所有自动场景；
 *
 *    模式：
 *      1)、SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component）如
 *          果有就用用户配置的，如果没有(@ConditionalOnMissingBean)，才自动配置；如果有些组件可以有多个（ViewResolver）将用户配置的和自己
 *          默认的组合起来；
 *      2)、在SpringBoot中会有非常多的xxxConfigurer帮助我们进行扩展配置
 *      3)、在SpringBoot中会有很多的xxxCustomizer帮助我们进行定制配置,在第八章出现
 */

@SpringBootApplication
public class SpringBoot04WebRestfulcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04WebRestfulcrudApplication.class, args);
    }

    // 视频32 尚硅谷_SpringBoot_web开发-SpringMVC自动配置原理
    // 自定义一个ViewResolver,看是否启动时被加到容器中
    // 怎么看?
    //  在org.springframework.web.servlet.DispatcherServlet.doDispatch 方法上打断点,启动应用后访问一个地址,
    //  看this => viewResolvers => contentNegotiationViewResolver => viewResolvers
    @Bean
    public ViewResolver myViewResolver() {
        return new myViewResolver();
    }

    private static class myViewResolver implements ViewResolver {

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
