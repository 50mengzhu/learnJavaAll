package me.mica.learn;

public class ValueTransport {


    static class A {
        B b;

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    static class B {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    static void func(A a) {
        B b = a.getB();
        System.out.println(b);
        b = new B();
        b.setName("new");
        System.out.println(b);
    }


    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        b.setName("old");
        a.setB(b);
        System.out.println(a.getB());
        func(a);
        System.out.println(a.getB().getName());
    }
}
