package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.smarthomes.smarthomes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReportFragment OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReportFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    private boolean startHighlightDone = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Creating charts!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    private void setChartData(BarChart chart) {
        List<BarEntry> xData = new ArrayList<>();
        Random random = new Random();
        int startVal = 50;
        for (int x = 0; x < 5; x++) {
            int val = startVal - random.nextInt(6) + random.nextInt(4);
            xData.add(new BarEntry(x, val));
            startVal -= 2;
        }
        BarDataSet dataSet = new BarDataSet(xData, "€ per month");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        List<String> labels = new ArrayList<>();
        labels.add("MON");
        labels.add("TUE");
        labels.add("WED");
        labels.add("THU");
        labels.add("FRI");

        chart.setDrawValueAboveBar(false);
        chart.getXAxis().setDrawLabels(false);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);

        BarData data = new BarData(dataSet);
        chart.setData(data);
        chart.getXAxis().setAxisMaximum(4.5f);
        chart.getXAxis().setAxisMinimum(-0.5f);
        chart.getXAxis().setLabelCount(5,  true);


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                setDayData(e);
            }

            @Override
            public void onNothingSelected() {
                setDayData(new Entry(5, 0));
            }
        });

        Description desc = new Description();
        chart.setDescription(null);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        //chart.getXAxis().setDrawLabels(false);
        chart.invalidate();

        System.out.println("chart set");
    }

    private void setDayData(Entry e) {
        int x = Math.round(e.getX());

        TextView dayText = (TextView) getView().findViewById(R.id.dayText);
        String[] days = {"MON", "TUE", "WED", "THU", "FRI"};

        TextView usageText = (TextView) getView().findViewById(R.id.usageText);
        TextView costText = (TextView) getView().findViewById(R.id.costText);

        Random random = new Random();

        float multiplier = 4.6f;
        int value = Math.round(e.getY());
        int usage = Math.round(value * (4.6f));

        if (value == 0 && usage == 0) {
            dayText.setText("-");
        } else if (x > -1 && x < 5) {
            dayText.setText(days[x]);
            costText.setText(String.valueOf(value));
            usageText.setText(String.valueOf(usage));
        } else if (x == 5){
            costText.setText(String.valueOf(value));
            usageText.setText(String.valueOf(usage));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BarChart chart = (BarChart) getView().findViewById(R.id.chart);
        setChartData(chart);
        if (!startHighlightDone) {
            chart.highlightValue(4, 0);
        }
    }
}
