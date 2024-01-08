package org.yandrut.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
public class PetDataResponse {
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
}
