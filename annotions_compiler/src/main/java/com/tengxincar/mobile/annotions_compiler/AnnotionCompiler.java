package com.tengxincar.mobile.annotions_compiler;

import com.google.auto.service.AutoService;
import com.tengxincar.mobile.annotations.BindPath;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * Created by wxs on 2019/7/12.
 * 注解处理器
 * AbstractProcessor父类
 */
@AutoService(Processor.class)//注册注解处理器
public class AnnotionCompiler extends AbstractProcessor {

    //生成文件的对象
    Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    /**
     * 声明我们这个注解处理器需要处理哪些注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();

        types.add(BindPath.class.getCanonicalName());


        return types;
    }

    /**
     * 声明我们支持的java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 这个方法是注解处理的核心方法，写文件就放在这里进行写  element节点  TypeElement就是类节点
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //通过这个API能拿到所有这个模块中所有的用到了BindPath注解的节点
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(BindPath.class);


        Map<String, String> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            //找到类节点
            TypeElement typeElement = (TypeElement) element;
            //获取到activity集合的key
            String key = typeElement.getAnnotation(BindPath.class).value();//找到key BindPath（）中的值
            String value = typeElement.getQualifiedName().toString();//带路径的名字
            map.put(key, value);
        }

        if (map.size() > 0) {
            Writer writer = null;
            String utilName = "ActivityUtils" + System.currentTimeMillis();
            try {
                JavaFileObject sourceFile = filer.createSourceFile("com.tengxincar.mobile.utils." + utilName);
                writer = sourceFile.openWriter();
                writer.write("package com.tengxincar.mobile.utils;\n" +
                        "\n" +
                        "import com.tengxincar.mobile.arouter.ARouter;\n" +
                        "import com.tengxincar.mobile.arouter.IRouter;\n" +
                        "\n" +
                        "public class " + utilName + " implements IRouter {\n" +
                        "    @Override\n" +
                        "    public void putActivity() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = map.get(key);
                    writer.write("ARouter.getInstance().putActivity(\"" + key + "\"," + value + ".class);\n");
                }
                writer.write("}\n}");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return false;
    }
}
