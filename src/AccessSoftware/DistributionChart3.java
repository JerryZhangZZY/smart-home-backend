package AccessSoftware;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class DistributionChart3 {
	ChartPanel frame1;
    int a;
    int b;
    int c;
    float min;
    float max;
    float[] f = new float[100];

    public DistributionChart3(int a1, int b1, int c1, float min1, float max1) {
        a = a1;
        b = b1;
        c = c1;
        min = min1;
        max = max1;

        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Distribution Chart", // 图表标题
                "Data", // 目录轴的显示标签
                "Number", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false,      // 是否显示图例(对于简单的柱状图必须是false)
                false,     // 是否生成工具
                false      // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();     //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));     //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12)); //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        //chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
        frame1 = new ChartPanel(chart,true);    //这里也可以用chartFrame,可以直接生成一个独立的Frame
    }
    private CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        float gap = (max - min)/3;
        float gap0 = (float)(Math.round(gap*10))/10;
        for(int i = 0; i < 3; i++){
            f[i] = min + i*gap0;
        }
        dataset.addValue(a, "Data", f[0] +"~"+f[1] );
        dataset.addValue(b, "Data", f[1] +"~"+f[2] );
        dataset.addValue(c, "Data", f[2] +"~"+max );
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}
