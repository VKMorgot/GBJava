package model;

public class ColoredPoint {
    private final int x;
    private final int y;
    private final String color;
    private final String name;
    private final boolean isMovable;

    private ColoredPoint(Builder builder) {

        //any validation
        if (builder.name == null) {
            throw new IllegalStateException("lapki");
        }
        this.x = builder.x;
        this.y = builder.y;
        this.color = builder.color;
        this.name = builder.name;
        this.isMovable = builder.isMovable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int x;
        private int y;
        private String color;
        private String name;
        private boolean isMovable;

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setMovable(boolean movable) {
            isMovable = movable;
            return this;
        }

        public ColoredPoint build() {
            return new ColoredPoint(this);
        }
    }

}
