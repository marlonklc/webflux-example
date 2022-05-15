package com.webfluxexample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webfluxexample.api.response.SummaryResponse;
import com.webfluxexample.service.SummaryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/summary")
public class SummaryApi {

    private SummaryService service;
    private ObjectMapper objectMapper;

    public SummaryApi(SummaryService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SummaryResponse> listenSummary() {
        return Flux.interval(Duration.ofSeconds(3))
            .flatMap(i -> service.getSummary())
            .map(summary -> objectMapper.convertValue(summary, SummaryResponse.class));
    }
}
