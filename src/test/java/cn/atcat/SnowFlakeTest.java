package cn.atcat;

//import cn.atcat.utils.SnowFlake;
import cn.hutool.core.lang.Snowflake;
import org.junit.jupiter.api.Test;

public class SnowFlakeTest {
    @Test
    public void test1(){
        Snowflake snowFlake = new Snowflake(1, 1);
        for (int i = 0; i < 100; i++)
            System.out.println(snowFlake.nextId());
    }
}
