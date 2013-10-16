package de.plushnikov.intellij.lombok.util;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiType;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuilderUtil {
  public final static String ANNOTATION_BUILDER_CLASS_NAME = "builderClassName";
  public static final String ANNOTATION_BUILD_METHOD_NAME = "buildMethodName";
  public static final String ANNOTATION_BUILDER_METHOD_NAME = "builderMethodName";
  public static final String ANNOTATION_FLUENT = "fluent";
  public static final String ANNOTATION_CHAIN = "chain";

  public final static String BUILDER_CLASS_NAME = "Builder";
  public final static String BUILD_METHOD_NAME = "build";
  public final static String BUILDER_METHOD_NAME = "builder";
  public final static String SETTER_PREFIX = "set";

  public static String createBuilderClassName(@NotNull PsiAnnotation psiAnnotation, @Nullable PsiType psiType) {
    return createBuilderClassName(psiAnnotation, psiType != null ? psiType.getPresentableText() : "Void");
  }

  public static String createBuilderClassName(@NotNull PsiAnnotation psiAnnotation, @NotNull String type) {
    final String builderClassName = PsiAnnotationUtil.getAnnotationValue(psiAnnotation, ANNOTATION_BUILDER_CLASS_NAME, String.class);
    return StringUtils.isNotBlank(builderClassName) ? builderClassName : StringUtils.capitalize(type) + BUILDER_CLASS_NAME;
  }

  @NotNull
  public static String createSetterName(@NotNull PsiAnnotation psiAnnotation, @NotNull String fieldName) {
    Boolean fluentAnnotationValue = PsiAnnotationUtil.getAnnotationValue(psiAnnotation, ANNOTATION_FLUENT, Boolean.class);
    final boolean isFluent = fluentAnnotationValue != null ? fluentAnnotationValue : true;
    return isFluent ? fieldName : SETTER_PREFIX + StringUtils.capitalize(fieldName);
  }

  @NotNull
  public static PsiType createSetterReturnType(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiType fieldType) {
    Boolean chainAnnotationValue = PsiAnnotationUtil.getAnnotationValue(psiAnnotation, ANNOTATION_CHAIN, Boolean.class);
    final boolean isChain = chainAnnotationValue != null ? chainAnnotationValue : true;
    return isChain ? fieldType : PsiType.VOID;
  }

  @NotNull
  public static String createBuildMethodName(@NotNull PsiAnnotation psiAnnotation) {
    final String buildMethodName = PsiAnnotationUtil.getAnnotationValue(psiAnnotation, ANNOTATION_BUILD_METHOD_NAME, String.class);
    return StringUtils.isNotBlank(buildMethodName) ? buildMethodName : BUILD_METHOD_NAME;
  }

  @NotNull
  public static String createBuilderMethodName(@NotNull PsiAnnotation psiAnnotation) {
    final String builderMethodName = PsiAnnotationUtil.getAnnotationValue(psiAnnotation, ANNOTATION_BUILDER_METHOD_NAME, String.class);
    return StringUtils.isNotBlank(builderMethodName) ? builderMethodName : BUILDER_METHOD_NAME;
  }
}
