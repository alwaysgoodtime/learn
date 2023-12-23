package flyweight;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2023-12-23 19:15
 */
public class FlyweightFactory {

    private HashMap<Color, Chess> map = new HashMap<>();

    public Chess getFlyweight(Color color) {
        if (!map.containsKey(color)) {
            if(color == Color.WHITE){
                map.put(color,new WhiteChess());
            }else {
                map.put(color,new BlackChess());
            }
        }
        return map.get(color);
    }

}
