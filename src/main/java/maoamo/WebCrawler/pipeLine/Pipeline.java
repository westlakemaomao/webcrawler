/**
* @author hzyuyongmao
* @version 创建时间：2016年6月29日 下午1:54:27
* 类说明
*/
package maoamo.WebCrawler.pipeLine;

import maoamo.WebCrawler.htmlUnit.domain.HtmlResult;

public interface Pipeline {
  public void process(HtmlResult hr,String tableName);
}
