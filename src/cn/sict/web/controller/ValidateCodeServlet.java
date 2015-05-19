package cn.sict.web.controller;

import javax.servlet.http.HttpServlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ValidateCodeServlet extends HttpServlet
{

private static final long serialVersionUID = 1L;
private int x=0;
//������֤��ͼƬ����ʾ������߶�
private int fontHeight;
private int codeY;

//�����ﶨ������֤��ͼƬ�Ŀ��
private int width=60;
//������֤��ͼƬ�ĸ߶ȡ�
private int height=20;
//������֤���ַ��������˴�����Ϊ5λ
private int codeNum=5;

char[] codeSequence= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ,'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
'X', 'Y', 'Z',};

/**
* ����֤ͼƬ���Խ��г�ʼ��
*/
public void init() throws ServletException
{
//�Ӳ����ļ�web.xml�л�ȡ�����ʼ����Ϣ��ͼƬ��ȸ��߶ȣ��ַ�������Ϣ
//��ȡ��ʼ���ַ�����
String strCodeNums=this.getInitParameter("codeNum");
//��ȡ��֤��ͼƬ��Ȳ���
String strW=this.getInitParameter("w");
//��ȡ��֤��ͼƬ�߶Ȳ���
String strH=this.getInitParameter("h");

//�����õ��ַ�����Ϣת������ֵ��������
try
{
if(strH!=null && strH.length()!=0)
{
height=Integer.parseInt(strH);
}
if(strW!=null && strW.length()!=0)
{
width=Integer.parseInt(strW);
}
if(strCodeNums!=null && strCodeNums.length()!=0)
{
codeNum=Integer.parseInt(strCodeNums);
}
}
catch(NumberFormatException e)
{}

x=width/(codeNum+1);
fontHeight=height-2;
codeY=height-4;

}

protected void service(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, java.io.IOException
{
//������֤��ͼ��Ļ�����
BufferedImage buffImg = new BufferedImage(
width, height,BufferedImage.TYPE_INT_RGB);
//����ͼ��������
Graphics2D g = buffImg.createGraphics();

//�����������������
Random random = new Random();

//����֤��ͼ�񱳾����Ϊ��ɫ
g.setColor(Color.WHITE);
g.fillRect(0, 0, width, height);

//���������ʽ������Ĵ�С�������֤��ͼƬ�ĸ߶����趨��
Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
//�������塣
g.setFont(font);

//Ϊ��֤��ͼƬ���߿�Ϊһ�����ء�
g.setColor(Color.black);
g.drawRect(0, 0, width - 1, height - 1);

//�������222��ͼƬ����������ʹ��֤��ͼƬ�е��ַ���������ʶ��
//g.setColor(Color.yellow);
//for(int i = 0; i<222; i++)
//{
//int x = random.nextInt(width);
//int y = random.nextInt(height);
//int xl = random.nextInt(12);
//int yl = random.nextInt(12);
//g.drawLine(x, y, x + xl, y + yl);
//}

//randomCode���������������֤��
StringBuffer randomCode = new StringBuffer();

//������ɫ����
int red = 0, green = 0, blue = 0;

//�������codeNum��������֤��
for (int i = 0; i<codeNum; i++) {
//�õ������������֤��
String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
//ʹ��������������������ɫ������������ɫֵ�����������ÿλ���ֵ���ɫֵ������ͬ��
red = random.nextInt(255);
green = random.nextInt(255);
blue = random.nextInt(255);

//�������������ɫ����֤����Ƶ�ͼ���С�
g.setColor(new Color(red, green, blue));
g.drawString(strRand, (i + 1) * x, codeY);

//���������ĸ�����������һ��
randomCode.append(strRand);
}
// ����������֤�뱣�浽Session��
HttpSession session = req.getSession();
session.setAttribute("validate", randomCode.toString());

// ����ͼ�񻺴�Ϊno-cache��
resp.setHeader("Pragma", "no-cache");
resp.setHeader("Cache-Control", "no-cache");
resp.setDateHeader("Expires", 0);
resp.setContentType("image/jpeg");

//��������������֤��ͼƬ�����Servlet���������
JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(resp.getOutputStream());  
encoder.encode(buffImg); 
}

} 