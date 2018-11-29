package org.primefaces.ultima.application;


import javax.faces.context.ExceptionHandlerFactory;

public abstract class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {


	private ExceptionHandlerFactory wrapped;


	public DefaultExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandlerFactory getWrapped() {
		return wrapped;
	}

}