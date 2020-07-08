package com.chaozhuo.routeprocess;

import com.chaozhuo.rounte_annotation.MtRoute;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by fewwind on 19-2-21.
 */

@AutoService(Processor.class)
public class RouteProcessor extends AbstractProcessor{

    Messager mMessager;
    Filer mFiler;
    String modeName = "";
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        Map<String, String> options = processingEnvironment.getOptions();
        for (String key:options.keySet()){
            modeName = options.get("fewwind");
            System.out.println(key+"--> 注解可选参数，arouter根据此参数获取模块名称  "+options.get(key));
        }
        System.out.println("--> 注解可选参数模块名称  "+modeName);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(MtRoute.class);
        try {
            TypeSpec typeSpec = processRouterTable(routeElements);
            System.out.println(mFiler+"--> process  "+typeSpec);
            if (typeSpec != null) {
                JavaFile.builder("com.fewwind.learn", typeSpec).build().writeTo(mFiler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error(e.getMessage());
        }
        return true;
    }

    private TypeSpec processRouterTable(Set<? extends Element> elements) {
        //注意这里一定要做判空处理,因为apt会多次扫描项目所有文件包括javaPoet生成的文件.
        //不做这步处理的话,第一次生成你需要的文件,第二次再进行扫描的时候,又会去生成文件,那时系统就会报一个异常,提示不能生成相同的文件
        if (elements == null || elements.size() == 0) {
            return null;
        }
        ParameterizedTypeName mapTypeName = ParameterizedTypeName
                .get(ClassName.get(HashMap.class), ClassName.get(String.class),
                        ClassName.get(Class.class));
        ParameterSpec mapParameterSpec = ParameterSpec.builder(mapTypeName, "activityMap")
                .build();
        MethodSpec.Builder routerInitBuilder = MethodSpec.methodBuilder("initActivityMap")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(mapParameterSpec);

        for (Element element : elements) {
            MtRoute route = element.getAnnotation(MtRoute.class);
            String[] routerUrls = route.value();
            for (String url : routerUrls) {
                //核心逻辑  将字符与类做映射关联
                routerInitBuilder.addStatement("activityMap.put($S, $T.class)", url, ClassName.get((TypeElement) element));
            }
        }

        return TypeSpec.classBuilder(modeName+"AutoCreateModuleActivityMap_app")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(routerInitBuilder.build())
                .build();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> ret = new HashSet<>();
        ret.add(MtRoute.class.getCanonicalName());
        return ret;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(String error) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, error);
    }
}
