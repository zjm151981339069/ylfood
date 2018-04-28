package com.spfood.oms.order.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面分页显示类
 * @author lizekun
 *
 */

public class Pagination {
	
		//页面显示数据
	    private List list;
	    //页面显示分页
	    private List pageView;
	    //开始页
	    private Integer pageNum;
	    //每页显示记录
	    private Integer pageSize;
	    //总记录数
	    private Long total;
	    //总页数
	    private Integer pages;
	    //条件参数
	    private String params;
	 
	    public Pagination() {
			super();
		}

		public Pagination( Integer pageNum,Integer pageSize, Long total) {
			super();
			this.pageNum = pageNum;
			this.pageSize = pageSize;
			this.total = total;
			if(total%pageSize>0){
				 pages =(int) ((total/pageSize)+1);
			 }else {
				 pages = (int) (total/pageSize);
			}
		}



		public List getList() {
	        return list;
	    }
	 
	    public void setList(List list) {
	        this.list = list;
	    }
	 
	    public List getPageView() {
	        return pageView;
	    }
	 
	    public void setPageView(List pageView) {
	        this.pageView = pageView;
	    }
	 
	    public void pageView(String url, String params) {
	    	this.params = params;
	        pageView = new ArrayList();
	        if (pageNum != 1) {
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params)
	                    .append("&pageNum=1'\"><font size=2>\u9996\u9875</font></a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pageNum - 1)
	                    .append("'\"><font size=2>\u4E0A\u4E00\u9875</font></a>").toString());
	        } else {
	            pageView.add("<font size=2>\u9996\u9875</font>");
	            pageView.add("<font size=2>\u4E0A\u4E00\u9875</font>");
	        }
	        if (pages <= 10) {
	            for (int i = 0; i < pages; i++) {
	                if (i + 1 == pageNum) {
	                    pageView.add((new StringBuilder("<strong>")).append(pageNum).append("</strong>")
	                            .toString());
	                    i++;
	                    if (pageNum == pages)
	                        break;
	                }
	                pageView.add((new StringBuilder(
	                        "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                        .append(url).append("?").append(params).append("&pageNum=").append(i + 1)
	                        .append("'\">").append(i + 1).append("</a>").toString());
	            }
	 
	        } else if (pages <= 20) {
	            int l = 0;
	            int r = 0;
	            if (pageNum < 5) {
	                l = pageNum - 1;
	                r = 10 - l - 1;
	            } else if (pages - pageNum < 5) {
	                r = pages - pageNum;
	                l = 9 - r;
	            } else {
	                l = 4;
	                r = 5;
	            }
	            int tmp = pageNum - l;
	            for (int i = tmp; i < tmp + 10; i++) {
	                if (i == pageNum) {
	                    pageView.add((new StringBuilder("<strong>")).append(pageNum).append("</strong>")
	                            .toString());
	                    i++;
	                    if (pageNum == pages)
	                        break;
	                }
	                pageView.add((new StringBuilder(
	                        "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                        .append(url).append("?").append(params).append("&pageNum=").append(i).append("'\">")
	                        .append(i).append("</a>").toString());
	            }
	 
	        } else if (pageNum < 7) {
	            for (int i = 0; i < 8; i++) {
	                if (i + 1 == pageNum) {
	                    pageView.add((new StringBuilder("<strong>")).append(pageNum).append("</strong>")
	                            .toString());
	                    i++;
	                }
	                pageView.add((new StringBuilder(
	                        "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                        .append(url).append("?").append(params).append("&pageNum=").append(i + 1)
	                        .append("'\">").append(i + 1).append("</a>").toString());
	            }
	 
	            pageView.add("...");
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pages - 1)
	                    .append("'\">").append(pages - 1).append("</a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pages)
	                    .append("'\">").append(pages).append("</a>").toString());
	        } else if (pageNum > pages - 6) {
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(1).append("'\">")
	                    .append(1).append("</a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(2).append("'\">")
	                    .append(2).append("</a>").toString());
	            pageView.add("...");
	            for (int i = pages - 8; i < pages; i++) {
	                if (i + 1 == pageNum) {
	                    pageView.add((new StringBuilder("<strong>")).append(pageNum).append("</strong>")
	                            .toString());
	                    i++;
	                    if (pageNum == pages)
	                        break;
	                }
	                pageView.add((new StringBuilder(
	                        "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                        .append(url).append("?").append(params).append("&pageNum=").append(i + 1)
	                        .append("'\">").append(i + 1).append("</a>").toString());
	            }
	 
	        } else {
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(1).append("'\">")
	                    .append(1).append("</a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(2).append("'\">")
	                    .append(2).append("</a>").toString());
	            pageView.add("...");
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pageNum - 2)
	                    .append("'\">").append(pageNum - 2).append("</a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pageNum - 1)
	                    .append("'\">").append(pageNum - 1).append("</a>").toString());
	            pageView.add((new StringBuilder("<strong>")).append(pageNum).append("</strong>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pageNum + 1)
	                    .append("'\">").append(pageNum + 1).append("</a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pageNum + 2)
	                    .append("'\">").append(pageNum + 2).append("</a>").toString());
	            pageView.add("...");
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pages - 1)
	                    .append("'\">").append(pages - 1).append("</a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pages)
	                    .append("'\">").append(pages).append("</a>").toString());
	        }
	        if (pageNum != pages) {
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pageNum + 1)
	                    .append("'\"><font size=2>\u4E0B\u4E00\u9875</font></a>").toString());
	            pageView.add((new StringBuilder(
	                    "<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='"))
	                    .append(url).append("?").append(params).append("&pageNum=").append(pages)
	                    .append("'\"><font size=2>\u5C3E\u9875</font></a>").toString());
	        } else {
	            pageView.add("<font size=2>\u4E0B\u4E00\u9875</font>");
	            pageView.add("<font size=2>\u5C3E\u9875</font>");
	        }
	        pageView.add((new StringBuilder("\u5171<var>"))
	                .append(pages)
	                .append("</var>\u9875 \u5230\u7B2C<input type='text' id='pageNum'  size='3' onkeyup="+"this.value=this.value.replace(/[^0-9]/g,'')"+" />\u9875 <input type='button' id='skip' class='hand btn60x20' value='\u786E\u5B9A' onclick=\"javascript:window.location.href = '")
	                .append(url).append("?").append(params).append("&pageNum=' + $('#pageNum').val() \"/>")
	                .toString());
	        /*pageView.add((new StringBuilder("\u6BCF\u9875\u663E\u793A"))
	                .append("<select id=\"u163_input\"  onchange=\"javascript:window.location.href='").append(url).append("?").append(params).append("&pageSize=")
	                .append("'").append("+this.value").append("\"><option selected value=\"\u2014\u8BF7\u9009\u62E9\u2014\">\u2014\u8BF7\u9009\u62E9\u2014</option>")
	                .append("<option value=\"5\">5</option>")
	                .append("<option value=\"10\">10</option>")
	                .append("<option value=\"15\">15</option>")
	                .append("<option value=\"20\">20</option>")
	                .append("<option value=\"25\">25</option>")
	                .append("<option value=\"30\">30</option>")
	                .append("</select>")
	                .toString());*/
	        /*pageView.add((new StringBuilder("\u6BCF\u9875\u663E\u793A"))
	                .append("<select id=\"u163_input\"  onchange=\"javascript:window.location.href='").append(url).append("?").append(params).append("&pageSize=")
	                .append("'").append("+this.value").append("\"><option selected value=\"\u2014\u8BF7\u9009\u62E9\u2014\">\u2014\u8BF7\u9009\u62E9\u2014</option>")
	                .append("<option  <c:if test=\"${").append(pageSize).append(" == 5 }\">selected=\"selected\"</c:if>  value=\"5\">5</option>")
	                .append("<option  <c:if test=\"${").append(pageSize).append(" == 10 }\">selected=\"selected\"</c:if>  value=\"10\">10</option>")
	                .append("<option  <c:if test=\"${").append(pageSize).append(" == 15 }\">selected=\"selected\"</c:if>  value=\"15\">15</option>")
	                .append("<option  <c:if test=\"${").append(pageSize).append(" == 20 }\">selected=\"selected\"</c:if>  value=\"20\">20</option>")
	                .append("<option  <c:if test=\"${").append(pageSize).append(" == 25 }\">selected=\"selected\"</c:if>  value=\"25\">25</option>")
	                .append("<option  <c:if test=\"${").append(pageSize).append(" == 30 }\">selected=\"selected\"</c:if>  value=\"30\">30</option>")
	                .append("</select>")
	                .toString());*/
	    }

		public static Integer testPageNum(Integer pageNum) {
			// TODO Auto-generated method stub
			if (pageNum < 1) {
				return 1;
			}
			return pageNum;
		}

		public Integer getPageNum() {
			return pageNum;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public Long getTotal() {
			return total;
		}

		public Integer getPages() {
			return pages;
		}

		public String getParams() {
			return params;
		}

	 
	}

