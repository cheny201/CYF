package com.cyf.demo.jfreechart.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.TimeSeriesURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * JFreeChart控制层示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:27:16
 */
@Controller
public class ExampleController {

	@RequestMapping(value = "/demo/showChart", method = RequestMethod.GET)
	public String showChart(@RequestParam("type") String type,
			HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体  
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体  
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体  
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));

		// 应用主题样式  
		ChartFactory.setChartTheme(standardChartTheme);

		if (type != null) {
			if (type.startsWith("ZZT")) {
				createZZT(type, request);
			} else if (type.startsWith("BZT")) {
				createBZT(type, request);
			} else if (type.startsWith("QXT")) {
				createQXT(type, request, resp);
			}
		}
		return "view/demo/jfreechart/showChart";
	}

	/**
	 * 创建柱状图
	 * 
	 * @param type
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	private void createZZT(String type, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		String filename = null;
		String graphURL = null;
		if ("ZZT1".equals(type)) {// 柱状图1
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.addValue(610, "广州", "猪肉");
			dataset.addValue(220, "广州", "牛肉");
			dataset.addValue(530, "广州", "鸡肉");
			dataset.addValue(340, "广州", "鱼肉");

			JFreeChart chart = ChartFactory.createBarChart3D("肉类销量统计图", "分类",
					"数量", dataset, PlotOrientation.VERTICAL, false, false,
					false);

			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, null,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;
		} else if ("ZZT2".equals(type)) {// 柱状图2
			double[][] data = new double[][] { { 1310 }, { 720 }, { 1130 },
					{ 440 } };
			String[] rowKeys = { "猪肉", "牛肉", "鸡肉", "鱼肉" };
			String[] columnKeys = { "" };
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
					rowKeys, columnKeys, data);
			JFreeChart chart = ChartFactory
					.createBarChart3D("广州肉类销量统计图", "分类", "数量", dataset,
							PlotOrientation.VERTICAL, true, false, false);
			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, null,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;

		} else if ("ZZT3".equals(type)) {// 柱状图3
			double[][] data = new double[][] { { 1310, 1220, 1110, 1000 },
					{ 720, 700, 680, 640 }, { 1130, 1020, 980, 800 },
					{ 440, 400, 360, 300 } };
			String[] rowKeys = { "猪肉", "牛肉", "鸡肉", "鱼肉" };
			String[] columnKeys = { "广州", "深圳", "东莞", "佛山" };

			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
					rowKeys, columnKeys, data);
			JFreeChart chart = ChartFactory
					.createBarChart3D("广州肉类销量统计图", "分类", "数量", dataset,
							PlotOrientation.VERTICAL, true, false, false);
			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, null,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;
		} else if ("ZZT4".equals(type)) {// 柱状图4
			double[][] data = new double[][] { { 1310, 1220, 1110, 1000 },
					{ 720, 700, 680, 640 }, { 1130, 1020, 980, 800 },
					{ 440, 400, 360, 400 } };
			String[] rowKeys = { "猪肉", "牛肉", "鸡肉", "鱼肉" };
			String[] columnKeys = { "广州", "深圳", "东莞", "佛山" };
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
					rowKeys, columnKeys, data);
			JFreeChart chart = ChartFactory.createBarChart3D("肉类销量统计图", "分类",
					"数量", dataset, PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot plot = chart.getCategoryPlot();
			// 设置网格背景颜色
			plot.setBackgroundPaint(Color.white);
			// 设置网格竖线颜色
			plot.setDomainGridlinePaint(Color.pink);
			// 设置网格横线颜色
			plot.setRangeGridlinePaint(Color.pink);
			// 显示每个柱的数值，并修改该数值的字体属性
			BarRenderer3D renderer = new BarRenderer3D();
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);
			// 默认的数字显示在柱子中，通过如下两句可调整数字的显示
			// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
			renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			renderer.setItemLabelAnchorOffset(10D);
			// 设置每个地区所包含的平行柱的之间距离
			// renderer.setItemMargin(0.3);
			plot.setRenderer(renderer);
			// 设置地区、销量的显示位置
			// 将下方的“肉类”放到上方
			plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
			// 将默认放在左边的“销量”放到右方
			plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
			filename = ServletUtilities.saveChartAsPNG(chart, 700, 400, null,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;
		}
		request.setAttribute("graphURL", graphURL);
		request.setAttribute("filename", filename);
	}

	/**
	 * 创建饼状图
	 * 
	 * @param type
	 * @param request
	 * @throws IOException
	 */
	private void createBZT(String type, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		String filename = null;
		String graphURL = null;

		// 设置数据集
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("初中高级程序员", 0.55);
		dataset.setValue("项目经理", 0.1);
		dataset.setValue("系统分析师", 0.1);
		dataset.setValue("软件架构师", 0.1);
		dataset.setValue("其他", 0.2);
		if ("BZT1".equals(type)) {// 饼状图1

			// 通过工厂类生成JFreeChart对象
			JFreeChart chart = ChartFactory.createPieChart3D("IT行业职业分布图",
					dataset, true, false, false);
			PiePlot pieplot = (PiePlot) chart.getPlot();
			pieplot.setLabelFont(new Font("宋体", 0, 12));
			// 没有数据的时候显示的内容
			pieplot.setNoDataMessage("无数据显示");
			pieplot.setCircular(false);
			pieplot.setLabelGap(0.02D);
			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, null,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;
		} else if ("BZT2".equals(type)) {// 饼状图2
			// 通过工厂类生成JFreeChart对象
			JFreeChart chart = ChartFactory.createPieChart3D("IT行业职业分布图",
					dataset, true, true, false);
			// 获得3D的水晶饼图对象
			PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot();
			// 设置开始角度
			pieplot3d.setStartAngle(150D);
			// 设置方向为”顺时针方向“
			pieplot3d.setDirection(Rotation.CLOCKWISE);
			// 设置透明度，0.5F为半透明，1为不透明，0为全透明
			pieplot3d.setForegroundAlpha(0.5F);
			pieplot3d.setNoDataMessage("无数据显示");
			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, null,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;
		}
		request.setAttribute("graphURL", graphURL);
		request.setAttribute("filename", filename);
	}

	/**
	 * 创建折线图
	 * @param type
	 * @param request
	 * @param resp
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private void createQXT(String type, HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		HttpSession session = request.getSession();
		String filename = null;
		String graphURL = null;

		// 在矩形框中显示信息
		Shape shape = new Rectangle(20, 10);
		ChartEntity entity = new ChartEntity(shape);
		StandardEntityCollection coll = new StandardEntityCollection();
		coll.add(entity);
		// 该工具类上面没有介绍，在鼠标移动到图片时显示提示信息是用Map实现的，这些Map是用该类生成的。
		ChartRenderingInfo info = new ChartRenderingInfo(coll);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		StandardXYToolTipGenerator ttg = new StandardXYToolTipGenerator(
				StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, sdf,
				NumberFormat.getInstance());

		TimeSeriesURLGenerator urlg = new TimeSeriesURLGenerator(sdf,
				"test", "series", "hitDate");

		StandardXYItemRenderer renderer = new StandardXYItemRenderer(
				StandardXYItemRenderer.LINES, ttg, urlg);
		
		if ("QXT1".equals(type)) {
			// 访问量统计时间线
			TimeSeries timeSeries = new TimeSeries("阿蜜果blog访问量统计", Month.class);
			// 时间曲线数据集合
			TimeSeriesCollection lineDataset = new TimeSeriesCollection();

			// 构造数据集合
			timeSeries.add(new Month(1, 2007), 11200);
			timeSeries.add(new Month(2, 2007), 9000);
			timeSeries.add(new Month(3, 2007), 6200);
			timeSeries.add(new Month(4, 2007), 8200);
			timeSeries.add(new Month(5, 2007), 8200);
			timeSeries.add(new Month(6, 2007), 12200);
			timeSeries.add(new Month(7, 2007), 13200);
			timeSeries.add(new Month(8, 2007), 8300);
			timeSeries.add(new Month(9, 2007), 12400);
			timeSeries.add(new Month(10, 2007), 12500);
			timeSeries.add(new Month(11, 2007), 13600);
			timeSeries.add(new Month(12, 2007), 12500);

			lineDataset.addSeries(timeSeries);
			JFreeChart chart = ChartFactory.createTimeSeriesChart("访问量统计时间线",
					"month", "visit amount", lineDataset, true, true, true);

			// 设置子标题
			TextTitle subtitle = new TextTitle("2007年度", new Font("黑体",
					Font.BOLD, 12));
			chart.addSubtitle(subtitle);

			// 设置主标题
			chart.setTitle(new TextTitle("阿蜜果blog访问量统计", new Font("隶书",
					Font.ITALIC, 15)));
			chart.setAntiAlias(true);
			
			
			
			chart.getXYPlot().setRenderer(renderer);
			

			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, info,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;
		} else if ("QXT2".equals(type)) {
			// 访问量统计时间线
			TimeSeries timeSeries = new TimeSeries("阿蜜果blog访问量统计", Month.class);
			// 时间曲线数据集合
			TimeSeriesCollection lineDataset = new TimeSeriesCollection();

			// 构造数据集合
			timeSeries.add(new Month(1, 2007), 11200);
			timeSeries.add(new Month(2, 2007), 9000);
			timeSeries.add(new Month(3, 2007), 6200);
			timeSeries.add(new Month(4, 2007), 8200);
			timeSeries.add(new Month(5, 2007), 8200);
			timeSeries.add(new Month(6, 2007), 12200);
			timeSeries.add(new Month(7, 2007), 13200);
			timeSeries.add(new Month(8, 2007), 8300);
			timeSeries.add(new Month(9, 2007), 12400);
			timeSeries.add(new Month(10, 2007), 12500);
			timeSeries.add(new Month(11, 2007), 13600);
			timeSeries.add(new Month(12, 2007), 12500);

			lineDataset.addSeries(timeSeries);
			JFreeChart chart = ChartFactory.createTimeSeriesChart("访问量统计时间线",
					"month", "visit amount", lineDataset, true, true, true);

			XYPlot plot = (XYPlot) chart.getPlot();
			
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) plot
					.getRenderer();
			// 设置网格背景颜色
			plot.setBackgroundPaint(Color.white);
			// 设置网格竖线颜色
			plot.setDomainGridlinePaint(Color.pink);
			// 设置网格横线颜色
			plot.setRangeGridlinePaint(Color.pink);
			// 设置曲线图与xy轴的距离
			plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));
			// 设置曲线是否显示数据点
			xylineandshaperenderer.setBaseShapesVisible(true);
			// 设置曲线显示各数据点的值
			XYItemRenderer xyitem = plot.getRenderer();
			xyitem.setBaseItemLabelsVisible(true);
			xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(
					ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
			xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
			xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 14));
			plot.setRenderer(xyitem);

			// 设置子标题
			TextTitle subtitle = new TextTitle("2007年度", new Font("黑体",
					Font.BOLD, 12));
			chart.addSubtitle(subtitle);
			// 设置主标题
			chart.setTitle(new TextTitle("阿蜜果blog访问量统计", new Font("隶书",
					Font.ITALIC, 15)));
			chart.setAntiAlias(true);

			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, info,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;

		} else if ("QXT3".equals(type)) {
			// 访问量统计时间线
			TimeSeries timeSeries2006 = new TimeSeries("2006年度", Month.class);
			TimeSeries timeSeries2007 = new TimeSeries("2007年度", Month.class);
			// 时间曲线数据集合
			TimeSeriesCollection lineDataset = new TimeSeriesCollection();

			// 构造数据集合
			timeSeries2006.add(new Month(1, 2007), 7200);
			timeSeries2006.add(new Month(2, 2007), 7000);
			timeSeries2006.add(new Month(3, 2007), 4200);
			timeSeries2006.add(new Month(4, 2007), 8200);
			timeSeries2006.add(new Month(5, 2007), 7300);
			timeSeries2006.add(new Month(6, 2007), 8200);
			timeSeries2006.add(new Month(7, 2007), 9200);
			timeSeries2006.add(new Month(8, 2007), 7300);
			timeSeries2006.add(new Month(9, 2007), 9400);
			timeSeries2006.add(new Month(10, 2007), 7500);
			timeSeries2006.add(new Month(11, 2007), 6600);
			timeSeries2006.add(new Month(12, 2007), 3500);

			timeSeries2007.add(new Month(1, 2007), 10200);
			timeSeries2007.add(new Month(2, 2007), 9000);
			timeSeries2007.add(new Month(3, 2007), 6200);
			timeSeries2007.add(new Month(4, 2007), 8200);
			timeSeries2007.add(new Month(5, 2007), 8200);
			timeSeries2007.add(new Month(6, 2007), 11200);
			timeSeries2007.add(new Month(7, 2007), 13200);
			timeSeries2007.add(new Month(8, 2007), 8300);
			timeSeries2007.add(new Month(9, 2007), 10400);
			timeSeries2007.add(new Month(10, 2007), 12500);
			timeSeries2007.add(new Month(11, 2007), 10600);
			timeSeries2007.add(new Month(12, 2007), 10500);

			lineDataset.addSeries(timeSeries2006);
			lineDataset.addSeries(timeSeries2007);
			JFreeChart chart = ChartFactory.createTimeSeriesChart("访问量统计时间线",
					"month", "visit amount", lineDataset, true, true, true);

			// 设置子标题
			TextTitle subtitle = new TextTitle("2006/2007年度访问量对比", new Font(
					"黑体", Font.BOLD, 12));
			chart.addSubtitle(subtitle);
			// 设置主标题
			chart.setTitle(new TextTitle("阿蜜果blog访问量统计", new Font("隶书",
					Font.ITALIC, 15)));
			chart.setAntiAlias(true);

			filename = ServletUtilities.saveChartAsPNG(chart, 900, 400, info,
					session);
			graphURL = request.getContextPath() + "/DisplayChart?filename="
					+ filename;

		}

		ChartUtilities.writeImageMap(resp.getWriter(), filename, info, false);
		resp.getWriter().flush();

		request.setAttribute("graphURL", graphURL);
		request.setAttribute("filename", filename);
	}

}
