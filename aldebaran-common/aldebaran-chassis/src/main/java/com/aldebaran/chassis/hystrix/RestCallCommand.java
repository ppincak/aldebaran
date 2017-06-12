package com.aldebaran.chassis.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rx.Observable;


public class RestCallCommand<T> extends HystrixObservableCommand<T> {

    private static final RestTemplate restTemplate =
            new RestTemplate();

    private final RestCall<T> restCall;

    public RestCallCommand(RestCall<T> restCall) {
        this(restCall, 800);
    }

    public RestCallCommand(RestCall<T> restCall, int timeout) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RestCallGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                            .withExecutionTimeoutInMilliseconds(timeout)));

        this.restCall = restCall;
    }

    @Override
    protected Observable<T> construct() {
        return Observable.create(observer -> {
            try {
                if (observer.isUnsubscribed()) {
                    return;
                }

                ResponseEntity<T> responseEntity =
                        restTemplate.exchange(restCall.getUrl(),
                                      restCall.getMethod(),
                                      restCall.getRequestEntity(),
                                      restCall.getResponseType(),
                                      restCall.getUriVariables());

                observer.onNext(responseEntity.getBody());
                observer.onCompleted();
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }
}