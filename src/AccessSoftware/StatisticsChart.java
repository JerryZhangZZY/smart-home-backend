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

public class StatisticsChart {
	ChartPanel frame1;
    int a;
    int b;
    int c;
    float x;
    public StatisticsChart(int a1,int b1,int c1,float x1){
        a = a1;
        b = b1;
        c = c1;
        x = x1;
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Statistics Chart", // ͼ�����
                "", // Ŀ¼�����ʾ��ǩ
                "Number", // ��ֵ�����ʾ��ǩ
                dataset, // ���ݼ�
                PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                true,      // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                false,     // �Ƿ����ɹ���
                false      // �Ƿ�����URL����
        );
        //�����￪ʼ
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
        CategoryAxis domainAxis=plot.getDomainAxis();     //ˮƽ�ײ��б�
        domainAxis.setLabelFont(new Font("����",Font.BOLD,14));     //ˮƽ�ײ�����
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12)); //��ֱ����
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
        //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������
        frame1 = new ChartPanel(chart,true);    //����Ҳ������chartFrame,����ֱ������һ��������Frame
    }
    private CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(a, "Below","< "+x);
        dataset.addValue(b, "Equals", "= "+x);
        dataset.addValue(c, "Above", "> "+x);
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}
