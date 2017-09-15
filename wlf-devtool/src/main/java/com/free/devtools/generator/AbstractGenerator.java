package com.free.devtools.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liuzeke
 * @version 1.0
 */
public abstract class AbstractGenerator implements Generator {
//C:\Users\Administrator\Desktop\wlf-parent\wlf-devtool\src\main\resources\model\ModelController.data
	private static final String CONTROLLER_PATH = ".\\wlf-devtool\\src\\main\\resources\\model\\ModelController.data";
	private static final String CONTROLLER_W_PATH = "./target/controller";
	private static final String CONTROLLER_SUFFIX = "Controller.java";
//C:\Users\Administrator\Desktop\wlf-parent\wlf-devtool\src\main\resources\model\ModelService.data
	private static final String SERVICE_PATH = ".\\wlf-devtool\\src\\main\\resources\\model\\ModelService.data";
	private static final String SERVICE_W_PATH = "./target/service";
	private static final String SERVICE_SUFFIX = "Service.java";
//C:\Users\Administrator\Desktop\wlf-parent\wlf-devtool\src\main\resources\model\ModelServiceImpl.data
	private static final String SERVICE_IMPL_PATH = ".\\wlf-devtool\\src\\main\\resources\\model\\ModelServiceImpl.data";
	private static final String SERVICE_IMPL_W_PATH = "./target/service/impl";
	private static final String SERVICE_IMPL_SUFFIX = "ServiceImpl.java";

	private static final String FORM_W_PATH = "./target/model";
	private static final String FORM_SUFFIX = "Form.java";

	private final String absolutePath;

	private String packag;
	private String simpleName;
	private String lowerSimpleName;

	protected final Class<?> clazz;
	protected final GenerateElements elements;

	protected AbstractGenerator(Class<?> clazz, String absolutePath, GenerateElements elements) {

		this.clazz = clazz;
		this.elements = elements;
		this.absolutePath = absolutePath;
		initAttribute();
	}

	/**
	 * generate Controller method
	 */
	protected void generateController() {

		if (elements != null && !elements.isController())
			return;

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(new File(CONTROLLER_PATH)));
			File file = new File(getControllerFilePath());
			if (!file.exists())
				file.mkdirs();
			writer = new PrintWriter(new File(getControllerFilePath() + "/" + simpleName + CONTROLLER_SUFFIX));

			String line = "";
			while ((line = reader.readLine()) != null) {
				String str = line.replace("_package_", packag).replace("_simpleName_", simpleName)
						.replace("_lowerSimpleName_", lowerSimpleName);
				writer.println(str);
				writer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * generate Service method
	 */
	protected void generateService() {

		if (elements != null && !elements.isService())
			return;

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(new File(SERVICE_PATH)));
			File file = new File(getServiceFilePath());
			if (!file.exists())
				file.mkdirs();
			writer = new PrintWriter(new File(getServiceFilePath() + "/" + simpleName + SERVICE_SUFFIX));

			String line = "";
			while ((line = reader.readLine()) != null) {
				String str = line.replace("_package_", packag).replace("_simpleName_", simpleName)
						.replace("_lowerSimpleName_", lowerSimpleName);
				writer.println(str);
				writer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * generate ServiceImpl method
	 */
	protected void generateServiceImpl() {

		if (elements != null && !elements.isServiceImpl())
			return;

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(new File(SERVICE_IMPL_PATH)));
			File file = new File(getServiceImplFilePath());
			if (!file.exists())
				file.mkdirs();
			writer = new PrintWriter(new File(getServiceImplFilePath() + "/" + simpleName + SERVICE_IMPL_SUFFIX));

			String line = "";
			while ((line = reader.readLine()) != null) {
				String str = line.replace("_package_", packag).replace("_simpleName_", simpleName)
						.replace("_lowerSimpleName_", lowerSimpleName);
				writer.println(str);
				writer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * generate Form bean
	 */
	protected void generateForm() {

		if (elements != null && !elements.isForm())
			return;

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			File file = new File(getFormFilePath());
			if (!file.exists())
				file.mkdirs();
			writer = new PrintWriter(new File(getFormFilePath() + "/" + simpleName + FORM_SUFFIX));

			writer.println("package " + packag + ".model;");
			writer.print("\n");
			writer.println("public class " + simpleName + "Form {");
			writer.print("\n\n");
			writer.println("}");
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * initalization attribute
	 */
	private void initAttribute() {
		this.packag = clazz.getPackage().toString().replace("package", "").replace(".entity", "");
		this.simpleName = clazz.getSimpleName();
		this.lowerSimpleName = ((Character) simpleName.charAt(0)).toString().toLowerCase() + simpleName.substring(1);
	}

	/**
	 * @author liuzeke
	 * @return
	 */
	private String getControllerFilePath() {
		return absolutePath == null ? CONTROLLER_W_PATH : absolutePath + "/controller";
	}

	/**
	 * @author liuzeke
	 * @return
	 */
	private String getServiceFilePath() {
		return absolutePath == null ? SERVICE_W_PATH : absolutePath + "/service";
	}

	/**
	 * @author liuzeke
	 * @return
	 */
	private String getServiceImplFilePath() {
		return absolutePath == null ? SERVICE_IMPL_W_PATH : absolutePath + "/service/impl";
	}

	/**
	 * @author liuzeke
	 * @return
	 */
	private String getFormFilePath() {
		return absolutePath == null ? FORM_W_PATH : absolutePath + "/model";
	}
}
