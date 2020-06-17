package com.shortener.jaedmono.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricService {

    @Autowired
    private MeterRegistry registry;

    private List<String> statusList;

    private List<ArrayList<Integer>> statusMetricsByMinute;


    public void increaseCount(final int status) {
        String counterName = "counter.status." + status;
        registry.counter(counterName).increment(1);
        if (!statusList.contains(counterName)) {
            statusList.add(counterName);
        }
    }

    @Scheduled(fixedDelay = 60000)
    private void exportMetrics() {
        ArrayList<Integer> statusCount = new ArrayList<Integer>();
        for (String status : statusList) {
            Search search = registry.find(status);
            if (search != null) {
                Counter counter = search.counter();
                statusCount.add(counter != null ? ((int) counter.count()) : 0);
                registry.remove(counter);
            } else {
                statusCount.add(0);
            }
        }
        statusMetricsByMinute.add(statusCount);
    }
}
