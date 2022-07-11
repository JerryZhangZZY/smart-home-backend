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

public class DistributionChart4 {
	ChartPanel frame1;
    int a;
    int b;
    int c;
    int d;
    float min;
    float max;
    float[] f = new float[100];

    public DistributionChart4(int a1,int b1,int c1,int d1,float min1,float max1){
        a = a1;
        b = b1;
        c = c1;
        d = d1;
        min = min1;
        max = max1;

        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Distribution Chart", // ͼ�����
                "Data", // Ŀ¼�����ʾ��ǩ
                "Number", // ��ֵ�����ʾ��ǩ
                dataset, // ���ݼ�
                PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                false,      // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
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
        //chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
        //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������
        frame1=new ChartPanel(chart,true);    //����Ҳ������chartFrame,����ֱ������һ��������Frame
    }
    private CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        float gap = (max - min)/4;
        float gap0 = (float)(Math.round(gap*10))/10;
        for(int i = 0; i < 4; i++){
            f[i] = min + i*gap0;
        }
        dataset.addValue(a, "Data", f[0] +"~"+f[1] );
        dataset.addValue(b, "Data", f[1] +"~"+f[2] );
        dataset.addValue(c, "Data", f[2] +"~"+f[3] );
        dataset.addValue(d, "Data", f[3] +"~"+max );
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}
