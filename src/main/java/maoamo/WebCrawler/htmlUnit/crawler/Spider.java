/**
* @author hzyuyongmao
* @version 创建时间：2016年6月28日 上午9:34:13
* 类说明
*/
package maoamo.WebCrawler.htmlUnit.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import maoamo.WebCrawler.htmlUnit.domain.HtmlResult;
import maoamo.WebCrawler.htmlUnit.domain.ParserElementsDomain;
import us.codecraft.webmagic.Request;
import us.codecraft.xsoup.Xsoup;

public class Spider {

	private static String regex = "http://www.19lou.com/forum.*";

	private static BlockingQueue<String> anchors = new LinkedBlockingQueue<String>();
	private static Set<String> uniqueUrl = new HashSet<String>();

	public static void addAnchors(String url) throws InterruptedException {
		if (uniqueUrl.add(url)) {
			synchronized (anchors) {
				anchors.put(url);
			}
		}

	}

	public static void addAnchors(List<String> urls) throws InterruptedException {
		synchronized (anchors) {
			for (String url : urls) {
				if (uniqueUrl.add(url)) {
					anchors.put(url);
				}
			}
		}
	}

	public static void addAnchors(HtmlPage page, String parttern) throws InterruptedException {
		List<HtmlAnchor> htmlAnchors = page.getAnchors();
		for (HtmlAnchor htmlAnchor : htmlAnchors) {
			String url = htmlAnchor.getHrefAttribute();
			if (url.matches(parttern)) {
				synchronized (anchors) {
					if (uniqueUrl.add(url)) {
						anchors.put(url);
					} else {
						System.out.println(">>duplicate url");
					}
				}
			}

		}

	}

	public static boolean urlFilter(String url, String parttern) {
		return url.matches(parttern);
	}

	private static WebClient webClient = null;

	static {
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
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
	}

	public static HtmlResult process(String url, ParserElementsDomain parser)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		HtmlPage page = webClient.getPage(url);
		addAnchors(page, regex);
		return getHtmlContent(page, parser);
	}

	public static HtmlResult getHtmlContent(HtmlPage page, ParserElementsDomain parser) {
		HtmlResult htmlResult = new HtmlResult();

		if (parser.getTitle() != null) {
			htmlResult.setTitle(Xsoup.select(page.asXml(), parser.getTitle()).get());
		}
		if (parser.getContent() != null) {
			htmlResult.setContent(Xsoup.select(page.asXml(), parser.getContent()).get());
		}
		if (parser.getPublishTime() != null) {
			htmlResult.setPublishTime(Xsoup.select(page.asXml(), parser.getPublishTime()).get());
		}
		if (parser.getEditor() != null) {
			htmlResult.setEditor(Xsoup.select(page.asXml(), parser.getEditor()).get());
		}

		if (parser.getSource() != null) {
			htmlResult.setSource(Xsoup.select(page.asXml(), parser.getSource()).get());
		}

		return htmlResult;
	}

	public static void main(String[] args) {

		ParserElementsDomain parser = new ParserElementsDomain();
		// xpath add /text() can get the text
		parser.setTitle("//*[@id='postForm']/div[1]/div[1]/h1/a/span/text()");
		parser.setContent("//*[@id='12881466997838675']");
		try {
			process("http://www.19lou.com", parser);
			System.out.println("anchors:" + anchors.size());
			while (anchors.size() != 0) {
				System.out.println("anchors:" + anchors.size());
				String url = anchors.poll();
				System.out.println("url:" + url);
				HtmlResult htmlResult = process(url, parser);
				htmlResult.setUrl(url);
				htmlResult.setDomain("www.19lou.com");
				System.out.println(htmlResult.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}