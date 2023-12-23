package flyweight;


/**
 * @author goodtime
 * @create 2023-12-23 20:30
 */
public class WhiteChess implements Chess {

    private static final Color color = Color.WHITE;

    @Override
    public void draw(Point point) {
        System.out.println("白棋子下在了（" + point.x + "," + point.y + ")处");
    }
}
