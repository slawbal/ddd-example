package com.sb.shop.shop.shopping.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;


@Component
public class EventPublisher implements ApplicationEventPublisherAware {

	static ApplicationEventPublisher applicationEventPublisher;

	public static void publishEvent(final ApplicationEvent applicationEvent) {
		applicationEventPublisher.publishEvent(applicationEvent);
	}

	@Override
	public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
