package azmi.radi.com.digis.network;
import azmi.radi.com.digis.model.SignalStatus;
import retrofit2.http.GET;
import io.reactivex.Observable;

public interface ApiDataService {
    @GET("random")
    Observable<SignalStatus> getSignalStatus();
}