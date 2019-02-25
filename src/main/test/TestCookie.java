import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TestCookie {
    public static void main(String[] args) {
        List<Entiy> list=new ArrayList<Entiy>();
        list.add(new Entiy(1,"zhang"));

        list.add(new Entiy(2,"li"));
        System.out.println(JSON.toJSONString(list));

    }
}
