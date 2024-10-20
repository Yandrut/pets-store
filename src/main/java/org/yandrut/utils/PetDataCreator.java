package org.yandrut.utils;

import org.yandrut.data.PetData;

import java.util.List;

public class PetDataCreator {
    private static final int id = Integer.parseInt(DataReader.getTestData("pet.id"));
    private static final String name = DataReader.getTestData("pet.id");
    private static final List<String> photoURLs = List.of(DataReader.getTestData("pet.photoURLs"));
    private static final String status = DataReader.getTestData("pet.status");

    private PetDataCreator() {}

    public static PetData createNewPet() {

        PetData.Category category = new PetData.Category();
        category.setId(id);
        category.setName(name);

        PetData.Tag tag = new PetData.Tag();
        tag.setId(id);
        tag.setName(name);

        PetData pet = new PetData();

        pet.setId(id);
        pet.setCategory(category);
        pet.setName(name);
        pet.setPhotoUrls(photoURLs);
        pet.setTags(List.of(tag));
        pet.setStatus(status);

        return pet;
    }
}
