package azmi.radi.com.digis.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import azmi.radi.com.digis.utils.NavigationIconClickListener;
import azmi.radi.com.digis.R;
import azmi.radi.com.digis.model.SignalStatus;
import azmi.radi.com.digis.databinding.ActivityMainBinding;
import azmi.radi.com.digis.Adapter.SignalAdapter;
import azmi.radi.com.digis.viewModel.SignalViewModel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    //View Model
    private SignalViewModel signalViewModel;
    //for charts seeting
    private LineChart rsrpChart,rsrqChart,sinrChart;
    ArrayList<Entry> RSRPvalues;
    ArrayList<Entry> RSRQvalues;
    ArrayList<Entry> SINRvalues;
    ArrayList<String> time;
    LineData rsrpData,rsrqData,sinrData;
    LineDataSet rsrpDataSet,rsrqDataSet,sinrDataSet;
    //Data Represent in Table
    ArrayList<SignalStatus> signalStatuses;
    SignalAdapter adapter;
    //Binding
    ActivityMainBinding binding;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use Data Binding
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        signalViewModel = new ViewModelProvider(this)
                .get(SignalViewModel.class);
        initialization();
        // Set up tool bar
        setUpToolbar();
        //By Use RXJava To Get Data From Server Every 2 Second By Interval
        callSignal();
        // Set up Chart
        setUpChart(rsrpChart);
        setUpChart(rsrqChart);
        setUpChart(sinrChart);

    }


    private void initialization() {
        //Chart
        time=new ArrayList<>();
        rsrpChart = binding.rsrp;
        rsrqChart=binding.rsrq;
        sinrChart=binding.sinr;
        RSRPvalues = new ArrayList<>();
        RSRQvalues = new ArrayList<>();
        SINRvalues = new ArrayList<>();
           // Set up the RecyclerView
        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        signalStatuses = new ArrayList<>();
        adapter = new SignalAdapter(signalStatuses);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void callSignal() {
        signalViewModel.getSignal().observe(this, new Observer<SignalStatus>() {
            @Override
            public void onChanged(SignalStatus signalStatus) {
                // get Time
                Calendar c=Calendar.getInstance();
                if (signalStatuses.size() == 0) {
                    signalStatuses.add(signalStatus);
                    adapter.notifyDataSetChanged();
                } else {
                    signalStatuses.add(signalStatus);
                    adapter.notifyItemInserted(signalStatuses.size() - 1);

                }
                RSRPvalues.add(new Entry(time.size(), signalStatus.getRSRP()));
                RSRQvalues.add(new Entry(time.size(), signalStatus.getRSRQ()));
                SINRvalues.add(new Entry(time.size(), signalStatus.getSINR()));
                time.add( c.get(Calendar.MINUTE)+":"+ c.get(Calendar.SECOND));
                rsrpChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(time));
                rsrqChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(time));
                sinrChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(time));
                if (RSRPvalues.size()==1)
               {
                   addChart();
               }
               else if (RSRPvalues.size()<15)
               {
                  updateCharts();
               }

            }

        });
    }
    private void updateCharts() {
        rsrpDataSet.notifyDataSetChanged();
        rsrqDataSet.notifyDataSetChanged();
        sinrDataSet.notifyDataSetChanged();
        rsrpData.notifyDataChanged();
        rsrqData.notifyDataChanged();
        sinrData.notifyDataChanged();
        rsrpChart.notifyDataSetChanged();
        rsrqChart.notifyDataSetChanged();
        sinrChart.notifyDataSetChanged();
        rsrpChart.invalidate();
        rsrqChart.invalidate();
        sinrChart.invalidate();
    }
    private void setUpChart(LineChart chart) {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        // enable touch gestures
        chart.setTouchEnabled(true);
        // enable scaling and dragging
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
    }
    private void onError(Throwable throwable) {
        // if any error when Observable interval return here
        System.out.println("error:");

    }
    private void addChart() {
        rsrpChart.resetTracking();
        ArrayList<ILineDataSet> rsrpDataSetList = new ArrayList<>();
        rsrpDataSet= new LineDataSet(RSRPvalues,"RSRP");
        rsrpDataSet.setLineWidth(2.5f);
        rsrpDataSet.setCircleRadius(4f);
        rsrpDataSet.setColor(R.color.red);
        rsrpDataSet.setCircleColor(R.color.white);
        rsrpDataSetList.add(rsrpDataSet);
        rsrpData = new LineData(rsrpDataSetList);
        rsrpChart.setData(rsrpData);
        rsrpChart.invalidate();

        rsrqChart.resetTracking();
        ArrayList<ILineDataSet> rsrqDataSetList = new ArrayList<>();
        rsrqDataSet= new LineDataSet(RSRQvalues,"RSRQ");
        rsrqDataSet.setLineWidth(2.5f);
        rsrqDataSet.setCircleRadius(4f);
        rsrqDataSet.setColor(R.color.green);
        rsrqDataSet.setCircleColor(R.color.white);
        rsrqDataSetList.add(rsrqDataSet);
        rsrqData = new LineData(rsrqDataSetList);
        rsrqChart.setData(rsrqData);
        rsrqChart.invalidate();

        sinrChart.resetTracking();
        ArrayList<ILineDataSet> sinrDataSetList = new ArrayList<>();
        sinrDataSet= new LineDataSet(SINRvalues,"SINR");
        sinrDataSet.setLineWidth(2.5f);
        sinrDataSet.setCircleRadius(4f);
        sinrDataSet.setColor(R.color.blue);
        sinrDataSet.setCircleColor(R.color.white);
        sinrDataSetList.add(sinrDataSet);
        sinrData = new LineData(sinrDataSetList);
        sinrChart.setData(sinrData);
        sinrChart.invalidate();
    }
    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                this,
                findViewById(R.id.counter),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.menu), // Menu open icon
                getResources().getDrawable(R.drawable.close_menu))); // Menu close icon
    }

    @Override
    protected void onPause() {
        super.onPause();
        signalViewModel.stopGet();
     }

}
