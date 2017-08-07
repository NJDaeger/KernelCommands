package com.njdaeger.kernel.process;

import com.njdaeger.kernel.core.command.CommandContext;
import com.njdaeger.kernel.core.command.base.Command;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.njdaeger.kernel.core.command.base.Command")
public class CommandProcessor extends AbstractProcessor {
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Command.class);
		for (Element e : elements) {
			if (e.getKind() == ElementKind.METHOD) {
				
				ExecutableType method = (ExecutableType)e.asType();
				out(Diagnostic.Kind.WARNING, method.getKind().name());
				
				if (method.getReturnType().getKind() != TypeKind.VOID) {
					out(Diagnostic.Kind.ERROR,
							"Cannot Process Command annotation for method: " + e.getSimpleName() +
									"Methods annotated with the Command annotation should " +
									"always be void.");
				}
				if (method.getParameterTypes().size() != 1) {
					out(Diagnostic.Kind.ERROR,
							"Cannot Process Command annotation for method: " + e.getSimpleName() +
									"Methods annotated with the Command annotation should only " +
									"have the CommandContext parameter.");
				}
				
				Types types = processingEnv.getTypeUtils();
				Elements elems = processingEnv.getElementUtils();
				TypeMirror contextParam = method.getParameterTypes().get(0);
				TypeMirror contextType = elems.getTypeElement(CommandContext.class.getCanonicalName()).asType();
				
				if (!types.isSameType(contextParam, contextType)) {
					out(Diagnostic.Kind.ERROR,
							"Cannot Process Command annotation for method: " + e.getSimpleName() +
									"... Methods annotated with the Command annotation should " +
									"only have the CommandContext parameter.");
				}
				
			}
			else processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Command Annotation is for methods only");
		}
		return true;
	}
	
	private void out(Diagnostic.Kind kind, String message) {
		processingEnv.getMessager().printMessage(kind, message);
	}
	
}
