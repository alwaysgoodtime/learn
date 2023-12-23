package flyweight;

/**
 * @author goodtime
 * @create 2023-12-23 20:32
 */
public class BlackChess implements Chess {

    //color被抽象为棋子的内部状态
    private static final Color color = Color.BLACK;

    //坐标本来也是棋子的属性，但其随时都变，故抽象为了外部状态
    @Override
    public void draw(Point point) {
        System.out.println("黑棋子下在了（" + point.x + "," + point.y + ")处");

    }
}
