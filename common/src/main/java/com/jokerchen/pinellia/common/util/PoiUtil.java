package com.jokerchen.pinellia.common.util;

import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author Joker Chen
 * @date 2019-07-22 11:24:54
 */
@Slf4j
public class PoiUtil {

	public static void excelDownload(List<?> data, String filePath, String[] excelFileds, String fileName,
			HttpServletResponse response) {
		excelDownload(data, filePath, excelFileds, fileName, 0, true, null, true, response);
	}

	@SuppressWarnings("resource")
	public static void excelDownload(List<?> data, String filePath, String[] excelFileds, String fileName,
			int sheetIndex, boolean needBorder, Integer startIndex, boolean needIndex, HttpServletResponse response) {
		try {
			InputStream inputStream = null;
			inputStream = new ClassPathResource(filePath).getInputStream();
			// 根据模板创建excel工作簿
			XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			// 获取创建的工作簿第一页
			XSSFSheet sheet = workBook.getSheetAt(sheetIndex);

			if (data != null && data.size() > 0) {
				XSSFCellStyle cellStyle = null;
				if (needBorder) {
					cellStyle = workBook.createCellStyle(); // 单元格样式
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderRight(BorderStyle.THIN);
					cellStyle.setBorderTop(BorderStyle.THIN);
				}

				// 获取当前sheet最后一行数据对应的行索引
				int currentLastRowIndex = sheet.getLastRowNum();
				if (startIndex != null) {
					currentLastRowIndex = startIndex;
				}
				for (int i = 0; i < data.size(); i++) {
					int newRowIndex = currentLastRowIndex + i + 1;
					XSSFRow newRow = sheet.createRow(newRowIndex);
					// 开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
					int cellIndex = 0;
					XSSFCell cell;
					if (needIndex) {
						cell = newRow.createCell(cellIndex++);
						cell.setCellValue(i + 1);
						if (needBorder) {
							cell.setCellStyle(cellStyle);
						}
					}
					JSONObject object = (JSONObject) JSONObject.toJSON(data.get(i));
					for (int j = 0; j < excelFileds.length; j++) {
						// 创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
						cell = newRow.createCell(cellIndex++);
						if (StringUtil.isNotEmpty(excelFileds[j])) {
							Object obj = ((JSONObject) object).get(excelFileds[j]);
							if (obj != null) {
								if (obj instanceof Integer) {
									cell.setCellValue((Integer) obj);
								} else if (obj instanceof Float) {
									cell.setCellValue((Float) obj);
								} else if (obj instanceof Double) {
									cell.setCellValue((Double) obj);
								} else {
									cell.setCellValue(obj.toString());
								}
							}
						}
						if (needBorder) {
							cell.setCellStyle(cellStyle);
						}
					}
				}
			}
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			// test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			ServletOutputStream out = response.getOutputStream();
			workBook.write(out);
			// 关闭流
			inputStream.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}
}
