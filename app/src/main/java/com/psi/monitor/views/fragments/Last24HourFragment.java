package com.psi.monitor.views.fragments;

import android.graphics.Color;
import android.view.View;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.psi.monitor.R;
import com.psi.monitor.controllers.apidata.PsiByDates;
import com.psi.monitor.models.PrefHelper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hamdhanywijaya@gmail.com on 8/20/17.
 */

public class Last24HourFragment extends BaseFragment {

    private LineChart lineChart;

    @Override
    public void initView(View view) {
        lineChart = (LineChart) view.findViewById(R.id.chart);

        lineChart.setOnChartValueSelectedListener(onChartValueSelectedListener);

        lineChart.getDescription().setEnabled(false);

        lineChart.setDrawBorders(true);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);

        // no description text
        lineChart.setNoDataText("No data statistic for this psi");

        // enable value highlighting
        lineChart.setHighlightPerDragEnabled(true);
        lineChart.setHighlightPerTapEnabled(true);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);

        // set an alternative background color
        int colorGray = Color.parseColor("#e9e9e9");
        lineChart.setBackgroundColor(colorGray);

        Legend l = lineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
    }

    @Override
    public void setUICallbacks() {

    }

    @Override
    public void updateUI() {
        PsiByDates.Response psiByDate = new PsiByDates.Response().fromJson(PrefHelper.getInstance(context).getPsiData());

        final ArrayList<String> arrayTime = new ArrayList<>();
        ArrayList<Entry> arrayCentral = new ArrayList<>();
        ArrayList<Entry> arrayNorth = new ArrayList<>();
        ArrayList<Entry> arrayWest = new ArrayList<>();
        ArrayList<Entry> arraySouth = new ArrayList<>();
        ArrayList<Entry> arrayEast = new ArrayList<>();
        ArrayList<Entry> arrayNational = new ArrayList<>();
        for (int i = 0; i<psiByDate.getItems().size(); i++){
            String time = getDate("hh:mm:ss", psiByDate.getItems().get(i).getTimestamp().substring(11, 19), "KK a");
            arrayTime.add(time);

            float psiCentral = (float) psiByDate.getItems().get(i).getReadings().getPsiTwentyFourHourly().getCentral();
            float psiNorth = (float) psiByDate.getItems().get(i).getReadings().getPsiTwentyFourHourly().getNorth();
            float psiWest = (float) psiByDate.getItems().get(i).getReadings().getPsiTwentyFourHourly().getWest();
            float psiSouth = (float) psiByDate.getItems().get(i).getReadings().getPsiTwentyFourHourly().getSouth();
            float psiEast = (float) psiByDate.getItems().get(i).getReadings().getPsiTwentyFourHourly().getEast();
            float psiNational = (float) psiByDate.getItems().get(i).getReadings().getPsiTwentyFourHourly().getNational();

            arrayCentral.add(new Entry(i, psiCentral));
            arrayNorth.add(new Entry(i, psiNorth));
            arrayWest.add(new Entry(i, psiWest));
            arraySouth.add(new Entry(i, psiSouth));
            arrayEast.add(new Entry(i, psiEast));
            arrayNational.add(new Entry(i, psiNational));
        }
        int colorGreen = Color.parseColor("#63C261");
        int colorBlue = Color.parseColor("#5279C7");
        int colorOrange = Color.parseColor("#FF7939");
        int colorRed = Color.parseColor("#FD373E");
        int colorYellow = Color.parseColor("#FAD419");
        int colorPurple = Color.parseColor("#2C2B6F");

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(arrayCentral, "PSI-24h Central");
        set1.setColor(colorPurple);
        set1.setCircleColor(colorPurple);
        set1.setLineWidth(2f);
        set1.setCircleSize(4f);
        set1.setFillAlpha(65);
        set1.setFillColor(colorPurple);
        set1.setHighLightColor(Color.rgb(244, 117, 117));

        // create a dataset and give it a type
        LineDataSet set2 = new LineDataSet(arrayNorth, "PSI-24h North");
        set2.setColor(colorGreen);
        set2.setCircleColor(colorGreen);
        set2.setLineWidth(2f);
        set2.setCircleSize(4f);
        set2.setFillAlpha(65);
        set2.setFillColor(colorGreen);
        set2.setHighLightColor(Color.rgb(244, 117, 117));

        // create a dataset and give it a type
        LineDataSet set3 = new LineDataSet(arrayWest, "PSI-24h West");
        set3.setColor(colorOrange);
        set3.setCircleColor(colorOrange);
        set3.setLineWidth(2f);
        set3.setCircleSize(4f);
        set3.setFillAlpha(65);
        set3.setFillColor(colorOrange);
        set3.setHighLightColor(Color.rgb(244, 117, 117));

        // create a dataset and give it a type
        LineDataSet set4 = new LineDataSet(arraySouth, "PSI-24h South");
        set4.setColor(colorRed);
        set4.setCircleColor(colorRed);
        set4.setLineWidth(2f);
        set4.setCircleSize(4f);
        set4.setFillAlpha(65);
        set4.setFillColor(colorRed);
        set4.setHighLightColor(Color.rgb(244, 117, 117));

        // create a dataset and give it a type
        LineDataSet set5 = new LineDataSet(arrayEast, "PSI-24h East");
        set5.setColor(colorGreen);
        set5.setCircleColor(colorGreen);
        set5.setLineWidth(2f);
        set5.setCircleSize(4f);
        set5.setFillAlpha(65);
        set5.setFillColor(colorGreen);
        set5.setHighLightColor(Color.rgb(244, 117, 117));

        // create a dataset and give it a type
        LineDataSet set6 = new LineDataSet(arrayNational, "PSI-24h National");
        set6.setColor(colorYellow);
        set6.setCircleColor(colorYellow);
        set6.setLineWidth(2f);
        set6.setCircleSize(4f);
        set6.setFillAlpha(65);
        set6.setFillColor(colorYellow);
        set6.setHighLightColor(Color.rgb(244, 117, 117));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the datasets 1
        dataSets.add(set2); // add the datasets 2
        dataSets.add(set3); // add the datasets 3
        dataSets.add(set4); // add the datasets 4
        dataSets.add(set5); // add the datasets 5
        dataSets.add(set6); // add the datasets 6

        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        // set data
        lineChart.setData(data);

        lineChart.animateX(2500);
        lineChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        int colorText = Color.parseColor("#5f5f5f");
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(colorText);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return arrayTime.get((int) value);
            }

        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    }

    @Override
    public String getPageTitle() {
        return null;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_last_24_hour;
    }

    private OnChartValueSelectedListener onChartValueSelectedListener = new OnChartValueSelectedListener() {
        @Override
        public void onValueSelected(Entry e, Highlight h) {

        }

        @Override
        public void onNothingSelected() {

        }
    };

    /**
     * method to get date with new format
     * @param dateFormat
     * @param date
     * @param toFormat
     * @return
     */
    public String getDate(String dateFormat, String date, String toFormat) {
        String formatted = "";
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date formatDate = new Date();
        try {
            Date dateStr = formatter.parse(date);
            formatted = formatter.format(dateStr);
            formatDate = formatter.parse(formatted);
            formatter = new SimpleDateFormat(toFormat);
            formatted = formatter.format(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatted;
    }
}
