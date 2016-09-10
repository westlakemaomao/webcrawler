/**
* @author hzyuyongmao
* @version 创建时间：2016年9月10日 下午4:04:15
* 类说明
*/
package maoamo.WebCrawler.pipeLine.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;


public class Result {
	String url = null;
	String keywords = null;
	String description = null;
	String title = null;
	String time = null;
	String content = null;
	String author = null;
	String category_navigation = null;
	String site = null;
	String tag = null;
	String source = null;
	String table = null;
	String summ = null;
	private String imgSrc = null;
	int forward = 0;
	int read = 0;
	int comment = 0;
	int applaud = 0;
	int deep = 0;
	Date timeD;

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

	Map<String, String> map;
	private HashSet<String> stock_id = new HashSet<String>();
	ArrayList<String> stockList;

	public HashSet<String> getStock_id() {
		return stock_id;
	}

	public void setStock_id(HashSet<String> stock_id) {
		this.stock_id = stock_id;
	}

	public ArrayList<String> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<String> stockList) {
		this.stockList = stockList;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getCategory_navigation() {
		return category_navigation;
	}

	public void setCategory_navigation(String category_navigation) {
		this.category_navigation = category_navigation;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getApplaud() {
		return applaud;
	}

	public void setApplaud(int applaud) {
		this.applaud = applaud;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}


	public Date getTimeD() {
		return timeD;
	}

	public void setTimeD(Date timeD) {
		this.timeD = timeD;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
}
