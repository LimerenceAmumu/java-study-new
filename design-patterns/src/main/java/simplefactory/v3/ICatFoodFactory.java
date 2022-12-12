package simplefactory.v3;

import simplefactory.catfoodimpl.CatFood;

public interface ICatFoodFactory {
    CatFood create(String flavor);
}