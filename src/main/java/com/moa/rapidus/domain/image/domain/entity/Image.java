package com.moa.rapidus.domain.image.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private int likes;
    private String theme;
    private int type;

    public void like(){
        likes+=1;
    }
}
