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
    public static class Category {
        private Integer id;
        private String name;
    }

    @Data
    public static class Tag {
        private Integer id;
        private String name;
    }



    public void updatePetsInformation(List <String> photoUrls) {
        setPhotoUrls(photoUrls);
    }

    public void updatePetsInformation(String name, String status) {
        setName(name);
        setStatus(status);
        tags.get(0).setName(name);
    }
}