package cn.sict.web.formbean;

public class PageBean
{
private int totalRecords;//�鼮������
private int curPageNum;//��ǰҳ
private int pageSize;//ÿҳ��ʾ���鼮
private int rowsPerPage;//ÿҳ��ʾ������

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
