package com.productdock.beerrating.exception;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.ServerHttpObservationFilter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Tracer tracer;

    @ExceptionHandler(Throwable.class)
    ProblemDetail handleBeerNotFoundException(HttpServletRequest request, Throwable error) {
        return handleError(request, error, INTERNAL_SERVER_ERROR);
    }

    private ProblemDetail handleError(HttpServletRequest request, Throwable error, HttpStatus status) {
        logger.error(error.getMessage(), error);
        ServerHttpObservationFilter.findObservationContext(request)
                .ifPresent(context -> context.setError(error));

        return createProblemDetail(status, error);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, Throwable error) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(status.getReasonPhrase());
        problemDetail.setDetail(error.getMessage());
        problemDetail.setProperty("series", status.series());
        problemDetail.setProperty("cause", error.getCause());
        problemDetail.setProperty("traceparent", getTraceParent());

        return problemDetail;
    }

    private String getTraceParent() {
        Span span = tracer.currentSpan();
        if (span != null) {
            String sampledFlag = Boolean.TRUE.equals(span.context().sampled()) ? "01" : "00";
            return "00-%s-%s-%s".formatted(span.context().traceId(), span.context().spanId(), sampledFlag);
        }
        else {
            return  "00-00000000000000000000000000000000-0000000000000000-00";
        }
    }
}
