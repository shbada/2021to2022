package item;

import item.decorator.AudiDecorator;

public class A5 extends AudiDecorator {
    public A5(ICar audi, String modelName) {
        super(audi, modelName, 3000);
    }
}
