package com.example;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;
import io.opentracing.util.GlobalTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentracing.Tracer;

@Configuration
public class TracerSetup {

	@Bean
	public Tracer jaegerTracer() {
		return new Configuration("spring-boot",
				new Configuration.SamplerConfiguration(
						ProbabilisticSampler.TYPE, 1),
				new Configuration.ReporterConfiguration()).getTracer();
	}
}