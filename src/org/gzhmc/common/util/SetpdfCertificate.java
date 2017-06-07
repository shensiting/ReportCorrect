package org.gzhmc.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.gzhmc.report4gzhmc.model.ReportRelative;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 生成pdf证书
 * @author stShen
 * @date 2017年2月5日
 */
public class SetpdfCertificate {

	private static String getScore(double sum) {
		String sumchar;
		if (sum >= 0 && sum < 1) {
			sumchar = "不合格";
		} else if (sum >= 1 && sum < 2) {
			sumchar = "合格";
		} else if (sum >= 2 && sum < 3) {
			sumchar = "良好";
		} else {
			sumchar = "优秀";
		}
		return sumchar;
	}

	private static String getGrade(double sum) {
		String sumchar;
		if (sum >= 0 && sum < 1) {
			sumchar = "D";
		} else if (sum >= 1 && sum < 2) {
			sumchar = "C";
		} else if (sum >= 2 && sum < 3) {
			sumchar = "B";
		} else {
			sumchar = "A";
		}
		return sumchar;
	}

	public static void setCertificate(ReportRelative reportRelative, HttpServletRequest request)
			throws FileNotFoundException {
		// System.out.println("开始生成pdf");
		// String fileName = reportRelative.getcId().toString() +
		// reportRelative.getcStudentId();
		String theory = SetpdfCertificate.getScore(reportRelative.getScoreSheet().getcTherory());
		String labresult = SetpdfCertificate.getScore(reportRelative.getScoreSheet().getcLabresult());
		String conclusion = SetpdfCertificate.getGrade(reportRelative.getScoreSheet().getcConclution());
		String conclusion2 = SetpdfCertificate.getScore(reportRelative.getScoreSheet().getcConclution());
		String operation = SetpdfCertificate
				.getScore((reportRelative.getScoreSheet().getcReagen() + reportRelative.getScoreSheet().getcInserument()
						+ reportRelative.getScoreSheet().getcExperiment()) / 3);
		// （2）创建写入器
		// 第一个参数是对文档对象的引用，第二个参数是输出的文件，将out和document连接起来
		try {
			// （1）实例化文档对象
			// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
			Document document = new Document(PageSize.A4, 95, 95, 85, 0);
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportRelative.getcPdfPath()));

			// 生成pdf需要用的中文字体
			BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			BaseFont bf1 = BaseFont.createFont("c://windows//fonts//simhei.ttf", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			Font f = new Font(bf);
			// System.out.println("开始写入");
			Font front = new Font(bf, 18, Font.NORMAL);
			Font front1 = new Font(bf, 14, Font.NORMAL);
			Font front2 = new Font(bf, 11, Font.NORMAL);
			Font front3 = new Font(bf, 18, Font.BOLD);
			//页头
			//HeaderFooter header = new HeaderFooter(
			//		new Phrase("附件1：生物技术                                             Biotechnology", front2), false);
			//header.setBorder(Rectangle.NO_BORDER);
			//document.setHeader(header);
			// 打开文档准备写入内容
			document.open();
			// 插入开头证书名字
			Paragraph p0 = new Paragraph(30);
			Chunk c0 = new Chunk("" + reportRelative.getExperimental().getcExperimentName() + " \n 良 好 证 书",
					front3);
			p0.add(c0);
			p0.setAlignment(1);
			document.add(p0);
			// 获取picture相对地址
			String path = request.getServletContext().getRealPath("gzhmc/picture");
			// 获取report相对地址
			String pathQR = request.getServletContext().getRealPath("gzhmc/report");
			//System.out.println(path);
			// 插入头像
			String pString = reportRelative.getStudent().getcPicturePath();
			pString = pString.replace('/', '\\');
			Image wmf = Image.getInstance(path + "\\" + pString);
			wmf.setAlignment(Image.MIDDLE);
			wmf.scaleAbsolute(80, 90);
			document.add(wmf);
			// 插入成绩等
			Paragraph p1 = new Paragraph(30);
			Chunk c1 = new Chunk("" + reportRelative.getStudent().getcName() + " 于 "
					+ reportRelative.getExperimental().getcExperimentTime() + " 参加 生物技术实践能力考试（"
					+ reportRelative.getExperimental().getcExperimentName() + "），成绩合格（理论 " + theory + "，实验操作 "
					+ operation + "，实验结果 " + labresult + "，综评 " + conclusion2 + "），特发此证。", front1);
			p1.add(c1);
			document.add(p1);
			Paragraph p3 = new Paragraph(30);
			//若要插入身份证号，必须使用解密，convertMD5
			Chunk c3 = new Chunk("学生学号："+reportRelative.getStudent().getcStudentNumber()+ " \n证书编号："
					+  reportRelative.getcReportNum()  + "", front1);
			p3.add(c3);
			document.add(p3);

			// 分割线
			Image wmf1 = Image.getInstance(path + "\\分割线.jpg");
			wmf1.setAlignment(Image.MIDDLE);
			wmf.scaleAbsolute(80, 0);
			document.add(wmf1);

			Paragraph p11 = new Paragraph(" \n");
			document.add(p11);
			// 英文证书名字
			Paragraph p4 = new Paragraph(30);
			Chunk c4 = new Chunk(
					"" + reportRelative.getExperimental().getcExperimentEnglishName() + "\nEXAMINATION CERTIFICATE",
					new Font(Font.TIMES_NEW_ROMAN, 18));
			p4.add(c4);
			p4.setAlignment(1);
			document.add(p4);

			Paragraph p6 = new Paragraph(30);
			Chunk c6 = new Chunk(
					"Rank：Basic \nGrade: " + conclusion + "\nCertificate Number: " + reportRelative.getcReportNum() + "",
					new Font(Font.TIMES_NEW_ROMAN, 15));
			p6.setAlignment(1);
			p6.add(c6);
			document.add(p6);

			Paragraph p7 = new Paragraph(30);
			Chunk c7 = new Chunk("Biotechnology Department, Guangzhou Medical University Authority",
					new Font(Font.TIMES_NEW_ROMAN, 13));
			p7.setAlignment(1);
			p7.add(c7);
			document.add(p7);

			// 插入的校徽的地址
			Image wmf2 = Image.getInstance(path + "\\校徽.JPG");
			wmf2.setAlignment(Image.MIDDLE);
			wmf2.scaleAbsolute(50, 50);
			document.add(wmf2);
			
			
			
//			// 插入的微信公众号二维码的地址
//			Image wmf3 = Image.getInstance(path + "\\公众号.jpg");
//			wmf3.setAlignment(Image.RIGHT);
//			wmf3.scaleAbsolute(40, 40);
//
//			// 插入的证书二维码的地址
//			String qrcode = pathQR + reportRelative.getcQRcode();
//			qrcode = qrcode.replace("/", "\\");
//			Image wmf4 = Image.getInstance(qrcode);
//			wmf4.setAlignment(Image.RIGHT);
//			wmf4.scaleAbsolute(40, 40);
//			// 通过控制表格来放置二维码
//			Table t = new Table(7);
//			t.setBorderColor(Color.white);
//			t.setAlignment(Element.ALIGN_RIGHT);
//			Cell cel1 = new Cell(wmf4);
//			Cell cel2 = new Cell(wmf3);
//			Cell cel3 = new Cell();
//			Cell cel4 = new Cell(new Chunk("更多请访问", new Font(bf, 8, Font.NORMAL)));
//			cel1.setBorderColor(Color.white);
//			cel2.setBorderColor(Color.white);
//			cel3.setBorderColor(Color.white);
//			cel4.setBorderColor(Color.white);
//			t.addCell(cel3);
//			t.addCell(cel3);
//			t.addCell(cel3);
//			t.addCell(cel4);
//			t.addCell(cel1);
//			t.addCell(cel3);
//			t.addCell(cel2);
//			document.add(t);
			document.close();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
