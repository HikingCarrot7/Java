package utilidades;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Mohammed
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface SmartPhone
{

    String os() default "Android";

    double version() default 1.0;

}

@Inherited
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface NotNull
{

    String name() default " ";

}

class IPhone
{

    @SmartPhone(os = "IOS", version = 12.0)
    void f(@NotNull int x)
    {

    }

}

class x extends IPhone
{

    @Override
    void f(@NotNull int x)
    {
    }

}

public class Anotaciones
{

    public static void main(String[] args)
    {
        IPhone cel = new IPhone();

        /*Class c = cel.getClass();

        Annotation an = c.getAnnotation(SmartPhone.class);

        SmartPhone s = (SmartPhone) an;*/
        System.out.println(cel.getClass().getAnnotation(SmartPhone.class).os());

    }

    void A()
    {

    }

}

class B extends Anotaciones
{

    @Override
    void A()
    {

    }

}
