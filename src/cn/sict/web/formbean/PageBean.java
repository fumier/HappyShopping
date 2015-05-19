package cn.sict.web.formbean;

public class PageBean
{
private int totalRecords;//书籍的总数
private int curPageNum;//当前页
private int pageSize;//每页显示的书籍
private int rowsPerPage;//每页显示多少行

public int getRowsPerPage()
{
	return rowsPerPage;
}

public void setRowsPerPage(int rowsPerPage)
{
	this.rowsPerPage = rowsPerPage;
}

public int getTotalRecords()
{
	return totalRecords;
}

public void setTotalRecords(int totalRecords)
{
	this.totalRecords = totalRecords;
}

public int getCurPageNum()
{
	return curPageNum;
}

public void setCurPageNum(int curPageNum)
{
	this.curPageNum = curPageNum;
}

public int getPageSize()
{
	return pageSize;
}

public void setPageSize(int pageSize)
{
	this.pageSize = pageSize;
}
}
