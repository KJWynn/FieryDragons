package com.example.demo.Animals;

import com.example.demo.Config;

public class Spider extends Animal{
    public Spider(int count) {
        super(Config.ANIMAL_IMAGE_IMAGE_PATH_PREFIX_MAPPINGS.get(AnimalType.SPIDER), count);
    }
}

