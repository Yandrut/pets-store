package org.yandrut.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PetData {
    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @Data
    @Getter
    @Setter
    public static class Category {
        private Integer id;
        private String name;
    }

    @Data
    @Getter
    @Setter
    public static class Tag {
        private Integer id;
        private String name;
    }

    public static PetData createNewPet(Integer id, String name, List<String> photoURLs, String status) {

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

    public void updatePetsInformation(List <String> photoUrls) {
        setPhotoUrls(photoUrls);
    }

    public void updatePetsInformation(String name, String status) {
        setName(name);
        setStatus(status);
    }
}
