package com.free.devtools.generator.impl;

import com.free.devtools.generator.AbstractGenerator;
import com.free.devtools.generator.GenerateElements;
import com.free.devtools.generator.Generator;

/**
 * @author liuzeke
 * @version 1.0
 */
public class GeneratorImpl extends AbstractGenerator implements Generator {

	public GeneratorImpl(Class<?> clazz, String sbsolutePath, GenerateElements elements) {
		super(clazz, sbsolutePath, elements);
	}

	/**
	 * Generate the specified method
	 * 
	 * @author liuzeke
	 */
	public void generate() {
		super.generateController();
		super.generateService();
		super.generateServiceImpl();
		super.generateForm();
		System.out.println("SUCESS.");
	}
}
