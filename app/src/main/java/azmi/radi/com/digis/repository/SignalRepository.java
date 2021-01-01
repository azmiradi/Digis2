package azmi.radi.com.digis.repository;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import azmi.radi.com.digis.model.SignalStatus;
import azmi.radi.com.digis.network.ApiDataService;
import azmi.radi.com.digis.network.RetrofitClient;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignalRepository {
    private static final String TAG = "DeveloperRepository";
    private ArrayList<SignalStatus> signalStatuses = new ArrayList<>();
    private MutableLiveData<SignalStatus> mutableLiveData = new MutableLiveData<>();
    private ApiDataService apiRequest;
    private Disposable disposable;

    public SignalRepository() {
        apiRequest = RetrofitClient.getService();
    }

    public MutableLiveData<SignalStatus> getSignals() {
        Observable<SignalStatus>
                observable = apiRequest.getSignalStatus();
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(result -> result)
                .repeatWhen(completed -> completed.delay(2, TimeUnit.SECONDS))
                .subscribe(new Observer<SignalStatus>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(SignalStatus signalStatus) {
                        mutableLiveData.setValue(signalStatus);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mutableLiveData;
    }

    public void dispose() {
        disposable.dispose();
     }


}
