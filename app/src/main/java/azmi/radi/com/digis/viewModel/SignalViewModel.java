package azmi.radi.com.digis.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import azmi.radi.com.digis.model.SignalStatus;
import azmi.radi.com.digis.repository.SignalRepository;

public class SignalViewModel extends ViewModel  {
    private SignalRepository signalRepository=new SignalRepository();
    public LiveData<SignalStatus> getSignal() {
        return signalRepository.getSignals();
    }
    public void stopGet(){
        signalRepository.dispose();
    }
}