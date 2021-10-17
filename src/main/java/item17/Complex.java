package item17;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * https://devfunny.tistory.com/543
 */
@EqualsAndHashCode
@ToString
public class Complex {
    public static final Complex ZERO = new Complex(0,0);
    public static final Complex ONE = new Complex(1,0);
    public static final Complex I = new Complex(0,1);

    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

//    private Complex(double re, double im) {
//        this.re = re;
//        this.im = im;
//    }

    public double realPart() {
        return re;
    }

    public double imagingPart() {
        return im;
    }

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

//    public static Complex valueOf(double re, double im) {
//        return new Complex(re, im);
//    }
}
