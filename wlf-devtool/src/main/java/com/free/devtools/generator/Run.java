package com.free.devtools.generator;

import com.free.devtools.generator.impl.GeneratorImpl;
import com.reroleresource.entity.ReRoleResources;

/**
 * @author liuzeke
 * @version 1.0
 */
public class Run {

	public static void main(String[] args) {

		String path = "C:\\Users\\Administrator\\Desktop\\wlf-parent\\wlf-base\\src\\main\\java\\com\\reroleresource";
		Generator gen = new GeneratorImpl(ReRoleResources.class, path, new GenerateElements().setForm(false));
		gen.generate();
	}
}
