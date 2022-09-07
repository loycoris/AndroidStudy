package com.android.compiler;

import com.android.annotation.Inject;
import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;


@AutoService(Processor.class)
@SupportedAnnotationTypes("com.android.annotation.Inject")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {
    Messager messager;
    Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "init......");
        typeUtils = processingEnv.getTypeUtils();
    }

    /**
     * @param set              指定注解的集合
     * @param roundEnvironment 提供当前的注解的对象信息获取
     * @return
     */

    /*
    * 注: user
    注: com.android.apt.User
    注: com.android.apt.MainActivity
    注: zhangsan
* */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process......");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Inject.class);
        for (Element element : elements) {
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());

            VariableElement variableElement = (VariableElement) element;
            TypeMirror typeMirror = variableElement.asType();
            TypeElement variableTypeElement = (TypeElement) typeUtils.asElement(typeMirror);
            messager.printMessage(Diagnostic.Kind.NOTE, variableTypeElement.getQualifiedName());

            TypeElement parentElement = (TypeElement) variableElement.getEnclosingElement();
            messager.printMessage(Diagnostic.Kind.NOTE, parentElement.getQualifiedName());

            for (Element subElement : parentElement.getEnclosedElements()) {
                messager.printMessage(Diagnostic.Kind.NOTE, subElement.getSimpleName());
            }

            Inject inject = variableElement.getAnnotation(Inject.class);
            String value = inject.value();
            messager.printMessage(Diagnostic.Kind.NOTE, value);
        }
        return false;
    }
}