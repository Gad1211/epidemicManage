package com.gad.epidemicmanage.common;

import com.gad.epidemicmanage.common.GlobalConstant;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;


/**
 * 用于爬取页面
 */
@Slf4j
public class HttpUnitUtil {

    /**
     * 返回页面字符串
     */
    public static String getHtmlPage(String url){
        String res = "";
        try{
            //构造一个webClient 模拟Chrome 浏览器
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            //支持JavaScript
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setActiveXNative(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(8000);
            HtmlPage rootPage = webClient.getPage(url);
            //设置一个运行JavaScript的时间
            webClient.waitForBackgroundJavaScript(6000);
            res = rootPage.asXml();
            log.info("htmlUnit爬取页面成功");
            webClient.close();
        }catch (Exception e){
            log.error("htmlUnit爬取页面异常"+e);
        }
        return res;
    }

}
