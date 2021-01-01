package azmi.radi.com.digis.Adapter;

  import android.graphics.Color;
  import android.view.LayoutInflater;
  import android.view.ViewGroup;

import java.util.List;

  import androidx.databinding.DataBindingUtil;
  import androidx.recyclerview.widget.RecyclerView;
  import azmi.radi.com.digis.constants.Constants;
  import azmi.radi.com.digis.R;
  import azmi.radi.com.digis.model.SignalStatus;
  import azmi.radi.com.digis.databinding.SignalListItemBinding;

  import static azmi.radi.com.digis.constants.Constants.RSRQ_COLORS;
  import static azmi.radi.com.digis.constants.Constants.SINR_COLORS;
  import static azmi.radi.com.digis.constants.Constants.TRANSFER_TO_POSITIVE;

public class SignalAdapter extends RecyclerView.Adapter<SignalAdapter.MyViewHolder> {

    private List<SignalStatus> signalStatuses;
    private LayoutInflater layoutInflater;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SignalListItemBinding signalListItemBinding;

        public MyViewHolder(final SignalListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.signalListItemBinding = itemBinding;
         }
    }


    public SignalAdapter(List<SignalStatus> signalStatuses) {
        this.signalStatuses = signalStatuses;
       // this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        SignalListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.signal_list_item, parent, false);
        return new MyViewHolder(binding);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        SignalStatus signalStatus = signalStatuses.get(position);
        holder.signalListItemBinding.rsrp.setProgress(signalStatus.getRSRP()+TRANSFER_TO_POSITIVE);
        holder.signalListItemBinding.rsrpText.setText(String.valueOf(signalStatus.getRSRP()));
        if (signalStatus.getRSRP()<=-110)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[0]));
        }
        else if (signalStatus.getRSRP()>-110&&signalStatus.getRSRP()<=-100)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[1]));
        }
        else if (signalStatus.getRSRP()>-100&&signalStatus.getRSRP()<=-90)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[2]));
        }
        else if (signalStatus.getRSRP()>-90&&signalStatus.getRSRP()<=-80)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[3]));
        }
        else if (signalStatus.getRSRP()>-80&&signalStatus.getRSRP()<=-70)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[4]));

        }
        else if (signalStatus.getRSRP()>-70&&signalStatus.getRSRP()<-60)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[5]));
        }
        else if (signalStatus.getRSRP()>=-60)
        {
            holder.signalListItemBinding.rsrp.setProgressColors(R.color.white,Color.parseColor(Constants.RSRP_COLORS[6]));
        }

        // RSRQ Initialization value
        holder.signalListItemBinding.rsrq.setProgress(signalStatus.getRSRQ()+TRANSFER_TO_POSITIVE);
        holder.signalListItemBinding.rsrqText.setText(String.valueOf(signalStatus.getRSRQ()));
        if (signalStatus.getRSRQ()<=-19.5)
        {
            holder.signalListItemBinding.rsrq.setProgressColors(R.color.white,Color.parseColor(RSRQ_COLORS[0]));
        }
        else if (signalStatus.getRSRQ()>-19.5&&signalStatus.getRSRQ()<=-14)
        {
            holder.signalListItemBinding.rsrq.setProgressColors(R.color.white,Color.parseColor(RSRQ_COLORS[1]));
        }
        else if (signalStatus.getRSRQ()>-14&&signalStatus.getRSRQ()<=-9)
        {
            holder.signalListItemBinding.rsrq.setProgressColors(R.color.white,Color.parseColor(RSRQ_COLORS[2]));
        }
        else if (signalStatus.getRSRQ()>-9&&signalStatus.getRSRQ()<-3)
        {
            holder.signalListItemBinding.rsrq.setProgressColors(R.color.white,Color.parseColor(RSRQ_COLORS[3]));
        }
        else if (signalStatus.getRSRQ()>=-3)
        {
            holder.signalListItemBinding.rsrq.setProgressColors(R.color.white,Color.parseColor(RSRQ_COLORS[4]));
        }

        // SINR Initialization value
        holder.signalListItemBinding.sinr.setProgress(signalStatus.getSINR());
        holder.signalListItemBinding.sinrText.setText(String.valueOf(signalStatus.getSINR()));

        if (signalStatus.getSINR()<=0)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[0]));
        }
        else if (signalStatus.getSINR()>0&&signalStatus.getSINR()<=5)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[1]));
        }
        else if (signalStatus.getSINR()>5&&signalStatus.getSINR()<=10)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[2]));
        }
        else if (signalStatus.getSINR()>10&&signalStatus.getSINR()<=15)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[3]));
        }
        else if (signalStatus.getSINR()>15&&signalStatus.getSINR()<=20)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[4]));

        }
        else if (signalStatus.getSINR()>20&&signalStatus.getSINR()<=25)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[5]));
        }
        else if (signalStatus.getSINR()>25&&signalStatus.getSINR()<30)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[6]));
        }
        else if (signalStatus.getSINR()>=30)
        {
            holder.signalListItemBinding.sinr.setProgressColors(R.color.white,Color.parseColor(SINR_COLORS[7]));
        }
    }

    @Override
    public int getItemCount() {
        return signalStatuses.size();
    }


}


