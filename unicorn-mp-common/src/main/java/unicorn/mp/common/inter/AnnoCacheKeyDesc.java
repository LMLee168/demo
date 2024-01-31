package unicorn.mp.common.inter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoCacheKeyDesc {
    String desc();
}
