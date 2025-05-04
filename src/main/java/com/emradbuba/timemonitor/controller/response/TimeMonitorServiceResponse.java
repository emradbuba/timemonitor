package com.emradbuba.timemonitor.controller.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class TimeMonitorServiceResponse<T> {

    private final LocalDateTime responseTime;
    private final T responseBody;
    private final List<String> messages;

    public static <T> ResponseBuilder<T> builder() {
        return new ResponseBuilder<>();
    }

    public static class ResponseBuilder<T> {
        private LocalDateTime responseTime;
        private T responseBody;
        private List<String> messages;

        public ResponseBuilder<T> withResponseTime(LocalDateTime responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public ResponseBuilder<T> withResponseBody(T responseTime) {
            this.responseBody = responseTime;
            return this;
        }

        public ResponseBuilder<T> withMessages(List<String> messages) {
            this.messages = List.copyOf(messages);
            return this;
        }

        public TimeMonitorServiceResponse<T> build() {
            return new TimeMonitorServiceResponse<>(this.responseTime, this.responseBody, this.messages);
        }
    }
}