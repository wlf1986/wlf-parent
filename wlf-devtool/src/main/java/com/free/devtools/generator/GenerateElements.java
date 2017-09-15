package com.free.devtools.generator;

/**
 * @author liuzeke
 * @version 1.0
 */
public class GenerateElements {

	private boolean controller;
	private boolean service;
	private boolean serviceImpl;
	private boolean form;
	private final GenerateElements generateElements = this;

	public GenerateElements() {
		this.controller = true;
		this.service = true;
		this.serviceImpl = true;
		this.form = true;
	}

	public boolean isController() {
		return controller;
	}

	public GenerateElements setController(boolean controller) {
		this.controller = controller;
		return generateElements;
	}

	public boolean isService() {
		return service;
	}

	public GenerateElements setService(boolean service) {
		this.service = service;
		return generateElements;
	}

	public boolean isServiceImpl() {
		return serviceImpl;
	}

	public GenerateElements setServiceImpl(boolean serviceImpl) {
		this.serviceImpl = serviceImpl;
		return generateElements;
	}

	public boolean isForm() {
		return form;
	}

	public GenerateElements setForm(boolean form) {
		this.form = form;
		return generateElements;
	}
}
