package com.itrexgroup.orderplanning.service.distance.listener;

import com.itrexgroup.orderplanning.service.DistanceService;
import com.itrexgroup.orderplanning.service.distance.event.CalculateDistanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DistanceEventListener {

    private final DistanceService distanceService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCalculateDistanceEvent(CalculateDistanceEvent calculateDistanceEvent) {
        distanceService.calculateDistance(
          calculateDistanceEvent.getCustomer(),
          calculateDistanceEvent.getWarehouses());
    }

}
