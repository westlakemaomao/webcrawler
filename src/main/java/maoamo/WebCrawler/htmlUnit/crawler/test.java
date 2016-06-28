/**
* @author hzyuyongmao
* @version 创建时间：2016年6月28日 下午8:11:19
* 类说明
*/
package maoamo.WebCrawler.htmlUnit.crawler;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class test {
	public static void main(String args[]) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "http://www.19lou.com";

		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		// 访问https
		webClient.getOptions().setUseInsecureSSL(false);
		// 解决SSLProtocolException：handshake alert: unrecognized_name
		System.setProperty("jsse.enableSNIExtension", "false");

		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setTimeout(3000);
		// webClient.setJavaScriptTimeout(100000);
		// webClient.waitForBackgroundJavaScript(10000);
		// 3 启动客户端重定向
		webClient.getOptions().setRedirectEnabled(true);
		HtmlPage htmlpage = webClient.getPage(url);
		
	}

	
	
}
