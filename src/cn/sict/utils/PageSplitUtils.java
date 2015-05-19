package cn.sict.utils;

public class PageSplitUtils
{
	/**
	 * @return 每页书籍显示的行数
	 */
	public static int getRowPerPage()
	{
		return 3;
	}
	
	/**
	 * @return 每页显示的书籍的数
	 */
	public static int getPageSize()
	{
		return 15;
	}
	public static int getTopPage()
	{
		return 1;
	}

	public static int getPreviousCurPageNum(int curPageNum)
	{
		if (curPageNum <= 1)
		{
			return 1;
		} else
		{
			return curPageNum - 1;
		}
	}

	public static int getNextCurPageNum(int curPageNum, int totalPages)
	{
		if (curPageNum >= totalPages)
		{
			return totalPages == 0 ? 1 : totalPages;
		} else
		{
			return curPageNum + 1;
		}
	}

	public static int getBottomPage(int totalRecords, int totalPages)
	{
		return totalRecords == 0 ? 1 : totalPages;
	}

	/**
	 * @return 每一页显示书籍的第一个索引值
	 */
	public static int getPosition(int curPageNum, int totalPages, int pageSize)
	{
		if (curPageNum < 1 || curPageNum > totalPages)
		{
			return 1;
		}

		return (curPageNum - 1) * pageSize + 1;
	}
	
	public static int getTotalPages(int totalRecords,int pageSize)
	{
		return totalRecords==0?1:((totalRecords+pageSize-1)/pageSize);
	}

}
