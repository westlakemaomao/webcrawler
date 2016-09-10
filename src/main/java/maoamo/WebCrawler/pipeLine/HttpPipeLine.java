/**
* @author hzyuyongmao
* @version 创建时间：2016年6月29日 下午1:54:03
* http pipeline
*/
package maoamo.WebCrawler.pipeLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;

import maoamo.WebCrawler.htmlUnit.domain.HtmlResult;

public class HttpPipeLine implements Pipeline {

	static HttpClient httpclient = HttpClients.createDefault();
	private static String URL = "http://ldt.myqnapcloud.com:32770/crawler/";

	public static String post(String url, String msg) {
		BufferedReader br = null;
		StringBuilder buf = new StringBuilder();
		HttpPost post = new HttpPost(url);
		try {
			HttpEntity entity = new StringEntity(msg, Charset.forName("UTF-8"));
			post.setEntity(entity);
			post.addHeader("Content-Type", "application/json;charset=UTF-8");
			HttpResponse response = httpclient.execute(post);

			// 获取status
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != 201) {
				System.err.println("url:" + url + " status:" + statusLine.getStatusCode());
				throw new Exception("10001错误");
			}
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			buf = new StringBuilder();
			String line;
			while (null != (line = br.readLine())) {
				buf.append(line);
			}
			System.out.println("url:" + url + "\t返回值:" + buf.toString());

		} catch (Exception e) {

		} finally {
			post.abort();
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			return buf.toString();
		}
	}

	public void process(HtmlResult hr, String tableName) {
		// TODO Auto-generated method stub
		System.out.println("调用写入到ES程序");
		String url = URL + tableName;
		String json = JSON.toJSONString(hr);
		HttpPipeLine.post(url, json);
	}

	public static void main(String args[]) {
		HtmlResult rs = new HtmlResult();
		rs.setTitle("测试1");
		String json = JSON.toJSONString(rs);
		URL = URL + "19lou";
		HttpPipeLine.post(URL, json);
	}

}
