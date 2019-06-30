package br.com.exemplo.Annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Intervalo {
    /**
     * Usando a simbologia Cron
     * basicamente usa-se "0 0 0 ? * *" para representar que será rodado 1 vez por dia as 00:00 todos os dias
     * documentação: https://www.freeformatter.com/cron-expression-generator-quartz.html#cronexpressionexamples
     * @return
     */
    String value() default "";
}
