package com.gipl.track32.app.repository;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.gipl.track32.app.AppExecutors;
import com.gipl.track32.app.api.ApiEmptyResponse;
import com.gipl.track32.app.api.ApiErrorResponse;
import com.gipl.track32.app.api.ApiResponse;
import com.gipl.track32.app.api.ApiSuccessResponse;
import com.gipl.track32.app.vo.Resource;


// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
public abstract class NetworkBoundResource<ResultType, RequestType> {
    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<Resource<ResultType>>();
    private AppExecutors appExecutors;

    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.<ResultType>loading(null));

        LiveData<ResultType> liveDataDB = loadFromDb();
        result.addSource(liveDataDB, data -> {
            result.removeSource(liveDataDB);

            if (shouldFetch(data)) {
                fetchFromNetwork(liveDataDB);
            } else {
                //result.addSource(liveDataDB, resultType -> result.postValue(Resource.success(resultType)));
                result.postValue(Resource.success(data));
            }
        });

    }


    private void fetchFromNetwork(LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        result.addSource(dbSource, newData ->
                setValue(Resource.loading(newData)));

        result.addSource(apiResponse, requestTypeApiResponse -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            if (requestTypeApiResponse instanceof ApiSuccessResponse) {
                appExecutors.diskIO().execute(() -> {
                    saveCallResult(processResponse((ApiSuccessResponse<RequestType>) requestTypeApiResponse));
                    appExecutors.mainThread().execute(() -> {
                        result.addSource(loadFromDb(), resultType -> {
                            setValue(Resource.success(resultType));
                        });
                    });
                });
            } else if (requestTypeApiResponse instanceof ApiEmptyResponse) {
                appExecutors.mainThread().execute(() -> {
                    // reload from disk whatever we had
                    result.addSource(loadFromDb(), newData -> {
                        setValue(Resource.success(newData));
                    });
                });

            } else if (requestTypeApiResponse instanceof ApiErrorResponse) {
                onFetchFailed();
                result.addSource(dbSource, resultType -> {
                    setValue(Resource.error(((ApiErrorResponse) requestTypeApiResponse).getErrorMessage(), resultType));
                });
            }
        });
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (result.getValue() != newValue) {
            result.setValue(newValue);
        }
    }

    // Called to save the result of the API response into the database.
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    // Called to get the cached data from the database.
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // Called to create the API call.
    @NonNull
    @MainThread
    //protected abstract Call<RequestType> createCall();
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected void onFetchFailed() {

    }

    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiSuccessResponse<RequestType> response) {
        return response.getBody();
    }

}
