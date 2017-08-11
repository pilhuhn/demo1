package io.openshift.booster;

import com.uber.jaeger.samplers.ProbabilisticSampler;
import io.opentracing.util.GlobalTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentracing.Tracer;

@Configuration
public class TracerSetup {

	@Bean
	public Tracer jaegerTracer() {
		return new com.uber.jaeger.Configuration("spring-boot",
				new com.uber.jaeger.Configuration.SamplerConfiguration(
						ProbabilisticSampler.TYPE, 1),
				new com.uber.jaeger.Configuration.ReporterConfiguration()).getTracer();
	}
}
