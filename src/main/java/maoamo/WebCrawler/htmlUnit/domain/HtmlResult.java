/**
* @author hzyuyongmao
* @version 创建时间：2016年6月28日 下午7:53:14
* 类说明
*/
package maoamo.WebCrawler.htmlUnit.domain;

public class HtmlResult {
	private String title;
	private String content;
	private String publishTime;
	private String source;
	private String editor;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
  @Override
  public String toString(){
	  return "title:\n"+title+"\ncontent:\n"+content+"\neditor:\n"+editor+"\nsource:\n"+source+"\npublishTime:\n"+publishTime;
  }
}
