package builder;

/**
 * @author goodtime
 * @create 2023-12-23 03:12
 */
public class Computer {

    private final String core;//必须
    private final String keyBoard;
    private final String screen;
    private final String mouse;
    private final int usb;

    public Computer(ComputerBuilder builder) {
        this.core = builder.core;
        this.keyBoard = builder.keyBoard;
        this.screen = builder.screen;
        this.mouse = builder.mouse;
        this.usb = builder.usb;
    }

    public static class ComputerBuilder {

        private String core;
        private String keyBoard;
        private String screen;
        private String mouse;
        private int usb;

        public ComputerBuilder setScreen(String screen) {
            this.screen = screen;
            return this;
        }

        public ComputerBuilder setKeyBoard(String keyBoard) {
            this.keyBoard = keyBoard;
            return this;
        }

        public ComputerBuilder setMouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public ComputerBuilder setUsb(int usb) {
            this.usb = usb;
            return this;
        }


        public ComputerBuilder(String core) {
            this.core = core;
        }

        public Computer build() {
            return new Computer(this);
        }
    }


    //更现代的builder写法
    public static void main(String[] args) {
        ComputerBuilder computerBuilder = new ComputerBuilder("intel");
        computerBuilder.setKeyBoard("cherry").setMouse("罗技");
        System.out.println(computerBuilder.build());


    }

}